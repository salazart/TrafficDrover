package com.trafficdrover.db.antares.models;

import com.trafficdrover.db.models.Entity;

public class Initiator extends Entity {
	private int idSection;

	public int getIdSection() {
		return idSection;
	}

	public void setIdSection(int idSection) {
		this.idSection = idSection;
	}
	
	public String toString(){
		return getId() + " " + getIdSection();
	}
}
