package com.trafficdrover.db.local.models;

import com.trafficdrover.db.models.Entity;

public class BaseStation extends Entity{

	private String sumlaccid;
	
	private int lac;

	private int cid;

	private String azimuth;

	private String adressBs = new String();

	public BaseStation() {
	}
	
	public BaseStation(int lac, int cid, String azimuth, String adressBs) {
		setLac(lac);
		setCid(cid);
		setAzimuth(azimuth);
		setAdressBs(adressBs);
	}
	
	public boolean isEmpty() {
		if (lac != 0 && cid != 0 && !adressBs.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public String toString() {
		return lac + "\t" + cid + "\t" + azimuth + "\t" + adressBs;
	}
	
	private int convertValue(String value) {
		try {
			return Integer.valueOf(value);
		} catch (Exception e) {
			return 0;
		}
	}

	public int getLac() {
		return lac;
	}

	public void setLac(int lac) {
		this.lac = lac;
	}
	
	public void setLac(String lac) {
		this.lac = convertValue(lac);
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}
	
	public void setCid(String cid) {
		this.cid = convertValue(cid);
	}

	public String getAzimuth() {
		return azimuth;
	}

	public void setAzimuth(String azimuth) {
		this.azimuth = azimuth;
	}
	
	public String getAdressBs() {
		return adressBs;
	}

	public void setAdressBs(String adressBs) {
		this.adressBs = adressBs;
	}

	public String getSumlacCid() {
		return String.valueOf(lac) + String.valueOf(cid);
	}

	public void setSumlaccid(String sumlaccid) {
		this.sumlaccid = sumlaccid;
	}

}
