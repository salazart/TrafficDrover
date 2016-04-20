package com.trafficdrover.folder.services;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.trafficdrover.TrafficDrover;
import com.trafficdrover.db.local.models.BaseStation;
import com.trafficdrover.db.local.services.BsService;
import com.trafficdrover.db.local.services.TrafficService;
import com.trafficdrover.utils.PropService;

public class ConversionProcess implements Runnable{
	private static final String FLAG_PART_PHONE = "partPhoneA";
	private static final String FLAG_PART_IMEI = "partImeiA";
	
	private boolean isPhoneA = Boolean.valueOf(PropService.getValue(FLAG_PART_PHONE));
	private boolean isImeiA = Boolean.valueOf(PropService.getValue(FLAG_PART_IMEI));
	
	protected static final Logger log = LogManager.getRootLogger();
	
	public void startProcess(){
		TrafficService trafficService = new TrafficService();
		trafficService.dropTraffic();
		trafficService.createTraffic();
		
		FolderService folderService = new FolderService();
		List<File> files = folderService.getFiles();
		TrafficDrover.instance.addLog("Знайдено файлів: " + files.size());
		
		for (File file : files) {
			TrafficDrover.instance.addLog("Читаю файл: " + file);
			FileFactory.readFile(file.toString());
		}
		
		TrafficDrover.instance.addLog("Пошук унікальних БС...");
		List<BaseStation> uniqueBsOfTraffic = trafficService.getUniqueBsOfTraffic();
		log.debug("Count unique base stations: " + uniqueBsOfTraffic.size());
		
		TrafficDrover.instance.addLog("Прописування БС...");
		BsService bsService = new BsService();
		for (int i = 0; i < uniqueBsOfTraffic.size(); i++) {
			uniqueBsOfTraffic.set(i, bsService.get(uniqueBsOfTraffic.get(i)));
		}
		log.debug("Count unique base stations with adresses: " + uniqueBsOfTraffic.size());
		
		TrafficDrover.instance.addLog("Збереження результату роботи...");
		try {
			trafficService.saveTrafficToFile(uniqueBsOfTraffic, isPhoneA, isImeiA);
		} catch (Exception e) {
			TrafficDrover.instance.addLog("Помилка збереження результату роботи...");
			log.error(e);
		}
		
		AntaresService antaresService = new AntaresService();
		antaresService.addToAntares(
				trafficService.getMinDate(), 
				trafficService.getMaxDate(), 
				trafficService.getAbonentsA());
		
		trafficService.dropTraffic();
		TrafficDrover.instance.addLog("Закінчено.");
	}
	
	@Override
	public void run() {
		startProcess();
	}
}
