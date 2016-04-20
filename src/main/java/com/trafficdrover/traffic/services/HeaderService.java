package com.trafficdrover.traffic.services;

import java.util.List;

import com.trafficdrover.traffic.models.THeader;

public class HeaderService extends XStreamService<THeader>{
	private final String PATH_HEADERS_BASE = "config/HeaderBase.xml";
	
	public List<THeader> getHeaderRule(){
		return getRules(new THeader(), "headers", PATH_HEADERS_BASE);
	}
}
