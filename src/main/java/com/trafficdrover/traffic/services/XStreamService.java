package com.trafficdrover.traffic.services;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class XStreamService <T extends Serializable>{
	protected static final Logger log = LogManager.getRootLogger();
	
	protected List<T> getRules(T type, String listTitle, String fileName){
		XStream xstream = new XStream(new StaxDriver());
		
		xstream.alias(listTitle, List.class);
		xstream.processAnnotations(type.getClass());
		
		try{
			return (List<T>) xstream.fromXML(new File(fileName));
		} catch (Exception e) {
			log.error(e);
			return new ArrayList<T>();
		}
	}
}
