package com.trafficdrover.traffic.models;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("phone") 
public class Phone  implements Serializable{
	@XStreamAlias("lenght")
	private int lenght;
	
	@XStreamAlias("prefix")
	private String prefix;
	
	@XStreamAlias("code")
	private String code;

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
