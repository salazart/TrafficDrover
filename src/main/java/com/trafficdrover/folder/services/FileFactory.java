package com.trafficdrover.folder.services;

import org.apache.commons.lang3.StringUtils;

public class FileFactory {
	private final static String XLSX = "xlsx";
	private final static String XLS = "xls";
	private final static String XML = "xml";
	
	public static void readFile(String fileName){
		switch (StringUtils.substringAfterLast(fileName, ".")) {
		case XLSX:
			new XLSXStreamService().readFile(fileName);
			break;
		case XLS:
			new XLSService().readFile(fileName);
			break;
		case XML:
			new XMLService().readFile(fileName);
			break;
		}
	}
}
