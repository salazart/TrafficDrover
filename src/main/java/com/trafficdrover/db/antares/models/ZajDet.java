package com.trafficdrover.db.antares.models;

import com.trafficdrover.db.models.Entity;

public class ZajDet extends Entity{
	private int idZlochOrsksr;
	private String tezisZajDet = "";
	private int idZaj;
	private int idZloch;
	
	public ZajDet(int idZaj, int idZloch, int idZlochOrsksr) {
		setIdZaj(idZaj);
		setIdZloch(idZloch);
		setIdZlochOrsksr(idZlochOrsksr);
	}
	
	public String toString(){
		return getIdZaj() + " " + getIdZloch() + " " + getIdZlochOrsksr() + " " + getTezisZajDet();
	}
	public int getIdZlochOrsksr() {
		return idZlochOrsksr;
	}
	public void setIdZlochOrsksr(int idZlochOrsksr) {
		this.idZlochOrsksr = idZlochOrsksr;
	}
	public String getTezisZajDet() {
		return tezisZajDet;
	}
	public void setTezisZajDet(String tezisZajDet) {
		this.tezisZajDet = tezisZajDet;
	}
	public int getIdZaj() {
		return idZaj;
	}
	public void setIdZaj(int idZaj) {
		this.idZaj = idZaj;
	}
	public int getIdZloch() {
		return idZloch;
	}
	public void setIdZloch(int idZloch) {
		this.idZloch = idZloch;
	}
}
