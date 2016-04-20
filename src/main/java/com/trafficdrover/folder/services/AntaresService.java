package com.trafficdrover.folder.services;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.trafficdrover.db.antares.models.Crime;
import com.trafficdrover.db.antares.models.Initiator;
import com.trafficdrover.db.antares.models.Orsksr;
import com.trafficdrover.db.antares.models.Section;
import com.trafficdrover.db.antares.models.Zaj;
import com.trafficdrover.db.antares.models.ZajDet;
import com.trafficdrover.db.antares.models.ZajNum;
import com.trafficdrover.db.antares.services.CrimeService;
import com.trafficdrover.db.antares.services.InitiatorService;
import com.trafficdrover.db.antares.services.OrsksrService;
import com.trafficdrover.db.antares.services.SectionService;
import com.trafficdrover.db.antares.services.ZajNumService;
import com.trafficdrover.db.antares.services.ZajService;
import com.trafficdrover.db.antares.services.ZayDetService;
import com.trafficdrover.utils.PropService;

public class AntaresService {
	private static final Logger log = LogManager.getRootLogger();
	
	private static final String ADD_TRAFFIC = "addTraffic";
//	private static final String ADD_MONITORING = "addMonitoring";
	
	private boolean addTraffic = Boolean.valueOf(PropService.getValue(ADD_TRAFFIC));
	
//	private boolean addMonitoring = Boolean.valueOf(PropService.getValue(ADD_MONITORING));
	
	private static final String ERDR = "erdr";
	private static final String ZAJ_NUMBER = "zajNumber";
	
	private static final int ID_SECTION = 22;
	private static final int ID_OKRUG = 33;
	private static final int TYPE_ORSKSR = 8;

	private static final Date toDay = new Date(Calendar.getInstance().getTimeInMillis());
	
	public void addToAntares(Date startDate, Date maxDate, List<String> abonents){
		if(addTraffic){
			int n = JOptionPane.showConfirmDialog(
				    new JFrame(),
				    "Do you want to add traffic into Antares?",
				    "Antares",
				    JOptionPane.YES_NO_OPTION);
			if(n == 0){
				addTrafficToAntares(startDate, maxDate, abonents);
			}
		}
	}
	
	private void addTrafficToAntares(Date startDate, Date maxDate, List<String> abonents) {
		Orsksr orsksr = getOrsksr();
		System.out.println("orsksr " + orsksr);
		
		Section section = getSection(orsksr);
		System.out.println("section " + section);
		
		Initiator initiator = getInitiator(section);
		System.out.println("initiator " + initiator);
		
		Zaj zaj = getZaj(initiator);
		System.out.println("zaj " + zaj);
		
		ZajDet zajDet = getZajDet(orsksr, zaj);
		System.out.println("zajDet " + zajDet);
		
		ZajNumService zajNumService = new ZajNumService();
		for (int i = 0; i < abonents.size(); i++) {
			int numberType = abonents.get(i).length() < 15 ? 1 : 2;
			ZajNum zajNum = new ZajNum(zajDet.getId(), orsksr.getIdZloch());
			zajNum.setData(startDate, maxDate, numberType, abonents.get(i));
			try {
				zajNumService.insert(zajNum);
				System.out.println("zajNum " + zajNum);
			} catch (Exception e) {
				log.error(e);
			}
		}
	}

	private ZajDet getZajDet(Orsksr orsksr, Zaj zaj) {
		ZajDet zajDet = new ZajDet(zaj.getId(), orsksr.getIdZloch(), orsksr.getId());
		ZayDetService zayDetService = new ZayDetService();
		try {
			zajDet = zayDetService.insert(zajDet);
		} catch (Exception e) {
			log.error(e);
		}
		return zajDet;
	}

	private Zaj getZaj(Initiator initiator) {
		int zajNumber = Integer.valueOf(PropService.getValue(ZAJ_NUMBER));
		
		Zaj zaj = new Zaj(zajNumber, ID_SECTION, initiator.getId());
		ZajService zajService = new ZajService();
		try {
			zaj = zajService.insert(zaj);
		} catch (Exception e) {
			log.error(e);
		}
		return zaj;
	}

	private Initiator getInitiator(Section section) {
		Initiator initiator = new Initiator();
		initiator.setIdSection(section.getId());
		InitiatorService initiatorService = new InitiatorService();
		initiator = initiatorService.get(initiator);
		return initiator;
	}

	private Section getSection(Orsksr orsksr) {
		Section section = new Section();
		section.setIdOkrug(orsksr.getIdOkrug());
		SectionService sectionService = new SectionService();
		section = sectionService.get(section);
		return section;
	}

	private Orsksr getOrsksr() {
		Orsksr orsksr = new Orsksr();
		orsksr.setNumOrsksr(PropService.getValue(ERDR));
		
		OrsksrService zlochService = new OrsksrService();
		orsksr = zlochService.get(orsksr);
		
		if(orsksr.getId() == 0){
			Crime crime = createCrime();
			
			orsksr = createOrsksr(orsksr, zlochService, crime);
		}
		return orsksr;
	}

	private Orsksr createOrsksr(Orsksr orsksr, OrsksrService zlochService, Crime crime){
		orsksr.setIdOkrug(ID_OKRUG);
		orsksr.setIdTypeOrsksr(TYPE_ORSKSR);
		orsksr.setIdZloch(crime.getId());
		orsksr.setDateOrsksr(toDay);
		
		try {
			orsksr = zlochService.insert(orsksr);
		} catch (Exception e) {
			log.error(e);
		}
		return orsksr;
	}

	private Crime createCrime(){
		Crime crime = new Crime();
		crime.setThesis("autogenerate");
		crime.setDateCrime(toDay);
		
		CrimeService crimeService = new CrimeService();
		try {
			crime = crimeService.insert(crime);
		} catch (Exception e) {
			log.error(e);
		}
		return crime;
	}

	
//	private void addMonitoringToAntares(){
//		Orsksr orsksr = getOrsksr();
//		
//		Section section = new Section();
//		section.setIdOkrug(orsksr.getIdOkrug());
//		section = sectionService.get(section);
//		section = sectionService.get(section);
//		
//		monitoring.setIdSection(section.getId());
//		monitoring.setIdZloch(orsksr.getIdZloch());
//		
//		MonitoringService monitoringService = new MonitoringService();
//		monitoring = monitoringService.insert(monitoring);
//	}


	
}
