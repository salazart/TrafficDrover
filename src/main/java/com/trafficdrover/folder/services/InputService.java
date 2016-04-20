package com.trafficdrover.folder.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.trafficdrover.TrafficDrover;
import com.trafficdrover.db.local.models.BaseStation;
import com.trafficdrover.db.local.models.Traffic;
import com.trafficdrover.db.local.services.BsService;
import com.trafficdrover.db.local.services.TrafficService;
import com.trafficdrover.traffic.models.THeader;
import com.trafficdrover.traffic.models.THeader.Head;
import com.trafficdrover.traffic.services.HeaderService;
import com.trafficdrover.traffic.services.TrafficFactory;

public class InputService extends TrafficFactory{
	protected static final Logger log = LogManager.getRootLogger();
	
	private List<THeader> tHeaders = new HeaderService().getHeaderRule();
	private List<Head> headers = new ArrayList<Head>();
	
	private BsService bsService = new BsService();
	private TrafficService trafficService = new TrafficService();
	
	protected void parseInputRow(List<String> inputRow){
		if(headers.isEmpty()){
			searchHeaders(inputRow);
		} else {
			Traffic traffic = parseTraffic(inputRow);
//System.out.println(traffic.toString());
			BaseStation baseStation = traffic.getBaseStation();
			if (!baseStation.isEmpty()) {
				bsService.addBaseStation(baseStation);
			}

			if (!traffic.isEmpty()) {
				try {
					trafficService.insert(traffic);
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
	}
	
	private Traffic parseTraffic(List<String> inputRow) {
		Traffic traffic = new Traffic();
		for (int i = 0; i < inputRow.size(); i++) {
			factoryTraffic(traffic, inputRow.get(i), headers.get(i));
		}
		return traffic;
	}

	public void searchHeaders(List<String> inputRow){
		int countValidateHeader = 0;
		for (int i = 0; i < inputRow.size(); i++) {
			String value = inputRow.get(i);
			Head head = getTypeHeader(value);
			headers.add(head);
			if(!head.equals(Head.NULL)){
				countValidateHeader++;
			}
		}
		
		if (countValidateHeader * 2 > inputRow.size()) {
	        TrafficDrover.instance.addLog("Розпізнані колонки: " + headers.toString());
		} else {
			headers.clear();
		}
	}

	private Head getTypeHeader(String value) {
		for (THeader theader : tHeaders) {
			if(theader.getNameHeader().equals(value)){
				return theader.getTypeHeader();
			}
		}
		return Head.NULL;
	}
	
	protected void cleanTrafficBase() {
		trafficService.dropTraffic();
		trafficService.createTraffic();
	}
}
