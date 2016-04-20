package com.salazart;

import java.util.Date;

import com.trafficdrover.db.antares.models.Monitoring;
import com.trafficdrover.db.antares.models.Orsksr;
import com.trafficdrover.db.antares.models.Section;
import com.trafficdrover.db.antares.services.MonitoringService;
import com.trafficdrover.db.antares.services.SectionService;
import com.trafficdrover.db.antares.services.OrsksrService;
import com.trafficdrover.utils.PropService;

public class TestMonitoring {
	private static final String ERDR = "erdr";
	
	public static void main(String[] args) {
		Orsksr orsksr = new Orsksr();
		orsksr.setNumOrsksr(PropService.getValue(ERDR));
		orsksr = new OrsksrService().get(orsksr);
		System.out.println(orsksr);
		
		Section section = new Section();
		section.setIdOkrug(orsksr.getIdOkrug());
		section = new SectionService().get(section);
		System.out.println(section);
		
		Monitoring monitoring = new Monitoring(section.getId(), new Date(), orsksr.getIdZloch(), new Date(), new Date());
		
		MonitoringService monitoringService = new MonitoringService();
		try {
			monitoring = monitoringService.insert(monitoring);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(monitoring.getId());
	}

}
