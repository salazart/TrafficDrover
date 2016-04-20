package com.trafficdrover.traffic.models;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("header") 
public class THeader implements Serializable{
	public final static String NULL = "NULL";
	public final static String TYPE = "TYPE";
	public final static String DATE = "DATE";
	public final static String DURATION = "DURATION";
	public final static String PHONE_A = "PHONE_A";
	public final static String IMEI_A = "IMEI_A";
	public final static String PHONE_B = "PHONE_B";
	public final static String LAC = "LAC";
	public final static String CID = "CID";
	public final static String LACCID = "LACCID";
	public final static String AZIMUTH = "AZIMUTH";
	public final static String BS = "BS";
	
	public enum Head{
		NULL,TYPE,DATE,DURATION,PHONE_A,
		IMEI_A,PHONE_B,LAC,LACCID,CID,AZIMUTH,BS
	}
	
	@XStreamAlias("name")
	private String nameHeader;
	
	@XStreamAlias("type")
	private Head typeHeader;
	
	public void setHeader(String nameHeader, Head typeHeader){
		this.nameHeader = nameHeader;
		this.typeHeader = typeHeader;
	}
	
	public String getNameHeader(){
		return nameHeader;
	}
	
	public Head getTypeHeader(){
		return typeHeader;
	}
}
