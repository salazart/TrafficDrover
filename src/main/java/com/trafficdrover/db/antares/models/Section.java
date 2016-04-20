package com.trafficdrover.db.antares.models;

import com.trafficdrover.db.models.Entity;

public class Section extends Entity{
	private String textSection;
	private int idOkrug;
	
	public String toString(){
		return getId() + " " + getTextSection() + " " + getIdOkrug();
	}
	
	public String getTextSection() {
		return textSection;
	}
	public void setTextSection(String textSection) {
		this.textSection = textSection;
	}
	public int getIdOkrug() {
		return idOkrug;
	}
	public void setIdOkrug(int idOkrug) {
		this.idOkrug = idOkrug;
	}
}
