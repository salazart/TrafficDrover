package com.salazart;

import com.trafficdrover.db.antares.models.Section;
import com.trafficdrover.db.antares.services.SectionService;

public class Okrug {
	private static final int idOkrug = 33;
	
	public static void main(String[] args) {
		Section section = new Section();
		section.setIdOkrug(idOkrug);
		
		SectionService sectionService = new SectionService();
		section = sectionService.get(section);
		
		System.out.println(section);
	}
}
