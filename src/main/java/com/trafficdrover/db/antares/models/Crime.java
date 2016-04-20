package com.trafficdrover.db.antares.models;

import java.sql.Date;

import com.trafficdrover.db.models.Entity;

public class Crime extends Entity{
	private Date dateCrime;
	private String thesis;
	private String story;
	private String victim;
	private String suspect;
	private int idActive = 1;
	
	public Date getDateCrime() {
		return dateCrime;
	}
	public void setDateCrime(Date dateCrime) {
		this.dateCrime = dateCrime;
	}
	public String getThesis() {
		return thesis;
	}
	public void setThesis(String thesis) {
		this.thesis = thesis;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public String getVictim() {
		return victim;
	}
	public void setVictim(String victim) {
		this.victim = victim;
	}
	public String getSuspect() {
		return suspect;
	}
	public void setSuspect(String suspect) {
		this.suspect = suspect;
	}
	public int getIdActive() {
		return idActive;
	}
	public void setIdActive(int idActive) {
		this.idActive = idActive;
	}
}
