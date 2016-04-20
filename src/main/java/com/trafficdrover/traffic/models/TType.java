package com.trafficdrover.traffic.models;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("type") 
public class TType implements Serializable{
	
	@XStreamAlias("inType") 
	private String inType;
	
	@XStreamAlias("outType")
	private String outType;
	
	public String getInType() {
		return inType;
	}
	public void setInType(String inType) {
		this.inType = inType;
	}
	public String getOutType() {
		return outType;
	}
	public void setOutType(String outType) {
		this.outType = outType;
	}
}
