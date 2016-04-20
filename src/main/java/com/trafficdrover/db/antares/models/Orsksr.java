package com.trafficdrover.db.antares.models;

import java.sql.Date;

import com.trafficdrover.db.models.Entity;

public class Orsksr extends Entity{
	private int idZloch;
	private int idTypeOrsksr;
	private int idOkrug;
	private String numOrsksr;
	private Date dateOrsksr;
	
	public String toString(){
		return getId() + " " + getIdZloch() + " " + getNumOrsksr() + " " + getDateOrsksr(); 
	}
	
	public int getIdZloch() {
		return idZloch;
	}
	public void setIdZloch(int idZloch) {
		this.idZloch = idZloch;
	}
	public int getIdTypeOrsksr() {
		return idTypeOrsksr;
	}
	public void setIdTypeOrsksr(int idTypeOrsksr) {
		this.idTypeOrsksr = idTypeOrsksr;
	}
	public int getIdOkrug() {
		return idOkrug;
	}
	public void setIdOkrug(int idOkrug) {
		this.idOkrug = idOkrug;
	}
	public String getNumOrsksr() {
		return numOrsksr;
	}
	public void setNumOrsksr(String numOrsksr) {
		this.numOrsksr = numOrsksr;
	}
	public Date getDateOrsksr() {
		return dateOrsksr;
	}
	public void setDateOrsksr(Date dateOrsksr) {
		this.dateOrsksr = dateOrsksr;
	}
	
}
