package com.trafficdrover.db.local.models;

import java.util.Date;

import com.trafficdrover.db.models.Entity;

public class Traffic extends Entity{
	private String type = "";

	private String date = "";

	private String time = "";

	private String duration = "";

	private String phoneA = "";

	private String imeiA = "";

	private String phoneB = "";

	private String imeiB = "";

	private int lac;

	private int cid;

	private String azimuth = "";

	private String adressBS = "";

	private Date defaultDate;
	
	public void clearTraffic(){
		type = "";
		date = "";
		time = "";
		duration = "";
		phoneA = "";
		imeiA = "";
		phoneB = "";
		imeiB = "";
		lac = 0;
		cid = 0;
		azimuth = "";
		adressBS = "";
		defaultDate = null;
	}

	public BaseStation getBaseStation() {
		BaseStation baseStation = new BaseStation();
		baseStation.setLac(getLac());
		baseStation.setCid(getCid());
		baseStation.setAzimuth(azimuth);
		baseStation.setAdressBs(adressBS);
		return baseStation;
	}

	public void setBaseStation(BaseStation baseStation) {
		setLac(baseStation.getLac());
		cid = baseStation.getCid();
		azimuth = baseStation.getAzimuth();
		adressBS = String.valueOf(baseStation.getAdressBs());
	}

	private Integer integerOfText(String text) {
		try {
			return Integer.valueOf(text);
		} catch (Exception e) {
			return 0;
		}
	}

	public String toString() {
		return type + "\t" + date + "\t"
				+ time + "\t" + duration + "\t" + phoneA + "\t" + imeiA + "\t"
				+ phoneB + "\t" + lac + "\t" + cid + "\t" + azimuth + "\t"
				+ adressBS + "\t";
	}
	
	public String[] toStringArray() {
		return new String[] {type,date,time,duration,phoneA,imeiA,phoneB,
				String.valueOf(lac),String.valueOf(cid),azimuth,adressBS};
	}

	public boolean isEmpty() {
		return type.isEmpty()
				|| date.isEmpty()
				|| time.isEmpty()
				|| duration.isEmpty()
				|| phoneA.isEmpty()
				|| phoneB.isEmpty();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getPhoneA() {
		return phoneA;
	}

	public void setPhoneA(String phoneA) {
		this.phoneA = phoneA;
	}

	public String getImeiA() {
		return imeiA;
	}

	public void setImeiA(String imeiA) {
		this.imeiA = imeiA;
	}

	public String getPhoneB() {
		return phoneB;
	}

	public void setPhoneB(String phoneB) {
		this.phoneB = phoneB;
	}

	public String getImeiB() {
		return imeiB;
	}

	public void setImeiB(String imeiB) {
		this.imeiB = imeiB;
	}

	public int getLac() {
		return lac;
	}

	public void setLac(int lac) {
		this.lac = lac;
	}
	
	public void setLac(String lac) {
		this.lac = getInt(lac);
	}
	
	private int getInt(String value){
		try {
			value = value.replaceAll("[^0-9]+", "");
			return Integer.valueOf(value);
		} catch (Exception e) {
			return 0;
		}
	}

	public int getCid() {
		return cid;
	}
	
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	public void setCid(String cid) {
		this.cid = getInt(cid);
	}

	public String getAzimuth() {
		return azimuth;
	}

	public String getAdressBS() {
		return adressBS;
	}

	public void setAdressBs(String adressBS) {
		this.adressBS = adressBS;
	}

	public Date getDefaultDate() {
		return defaultDate;
	}

	public void setDefaultDate(Date defaultDate) {
		this.defaultDate = defaultDate;
	}

	public void setAzimuth(String azimuth) {
		this.azimuth = azimuth;
	}
}
