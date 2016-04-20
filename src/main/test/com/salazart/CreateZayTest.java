package com.salazart;

import java.sql.Date;
import java.util.Calendar;

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

public class CreateZayTest {
	private static final String ERDR = "erdr";
	private static final String ZAJ_NUMBER = "zajNumber";
	
	private static final int ID_SECTION = 22;
	
	private static final int ID_OKRUG = 33;
	private static final int TYPE_ORSKSR = 8;
	
	private static final Logger log = LogManager.getRootLogger();

	private static final Date toDay = new Date(Calendar.getInstance().getTimeInMillis());
	
	public static void main(String[] args) {
		Orsksr orsksr = getOrsksr();
		System.out.println("orsksr " + orsksr);
		
		Section section = new Section();
		section.setIdOkrug(orsksr.getIdOkrug());
		SectionService sectionService = new SectionService();
		section = sectionService.get(section);
		System.out.println("section " + section);
		
		Initiator initiator = new Initiator();
		initiator.setIdSection(section.getId());
		InitiatorService initiatorService = new InitiatorService();
		initiator = initiatorService.get(initiator);
		System.out.println("initiator " + initiator);
		
		int zajNumber = Integer.valueOf(PropService.getValue(ZAJ_NUMBER));
		
		Zaj zaj = new Zaj(zajNumber, ID_SECTION, initiator.getId());
		ZajService zajService = new ZajService();
		try {
			zaj = zajService.insert(zaj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("zaj " + zaj);
		
		ZajDet zajDet = new ZajDet(zaj.getId(), orsksr.getIdZloch(), orsksr.getId());
		ZayDetService zayDetService = new ZayDetService();
		try {
			zajDet = zayDetService.insert(zajDet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("zajDet " + zajDet);
		
		Date date = new Date(Calendar.getInstance().getTimeInMillis());
		ZajNum zajNum = new ZajNum(zajDet.getId(), orsksr.getIdZloch());
		zajNum.setData(date, date, 2, "380977776080");
		ZajNumService zajNumService = new ZajNumService();
		try {
			zajNumService.insert(zajNum);
		} catch (Exception e) {
			log.error(e);
		}
		System.out.println("zajNum " + zajNum);
	}

	private static Orsksr getOrsksr() {
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

	private static Orsksr createOrsksr(Orsksr orsksr, OrsksrService zlochService, Crime crime){
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

	private static Crime createCrime(){
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
}
