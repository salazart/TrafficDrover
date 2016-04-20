package com.trafficdrover.folder.services;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.trafficdrover.utils.PropService;

public class SaveService {
	private static final String QUERY_GET_PHONE_A = "SELECT DISTINCT phoneA FROM traffic;";
	private static final String QUERY_GET_IMEI_A = "SELECT DISTINCT imeiA FROM traffic;";
	
	private boolean isPhoneA;
	private boolean isImeiA;
	
	private final static String PROPERTIES_FOLDER = "folder";
	
	private String folder = PropService.getValue(PROPERTIES_FOLDER);
	
	protected static final Logger log = LogManager.getRootLogger();
	
	public SaveService(boolean isPhoneA, boolean isImeiA){
		this.isPhoneA = isPhoneA;
		this.isImeiA = isImeiA;
	}
	
	public String queryGetAbonents(){
		String query = isPhoneA ? QUERY_GET_PHONE_A : QUERY_GET_IMEI_A;
		return query;
	}
	
	private String querySaveTraffic(){
		String query = "SELECT type, date, time, duration, phoneA, imeiA, phoneB, lac, cid, azimuth, adressBS "
				+ "FROM traffic ";
		if(isPhoneA){
			query += "WHERE phoneA=? ";
		} else if(isImeiA){
			query += "WHERE imeiA=? ";
		} 
		query += "ORDER BY defaultDate, duration, type, phoneA, imeiA, phoneB;";
		return query;
	}
	
	public ResultSet prepareSaveTraffic(Connection connection){
		try {
			log.debug("Try get connection for request: " + querySaveTraffic());
			return connection.createStatement().executeQuery(querySaveTraffic());
		} catch (SQLException e) {
			log.error(e);
			return null;
		}
	}	

	public ResultSet prepareSaveTraffic(Connection connection, String param){
		try {
			log.debug("Try get connection for request: " + querySaveTraffic());
			PreparedStatement ps = connection.prepareStatement(querySaveTraffic());
			ps.setString(1, param);
			return ps.executeQuery();
		} catch (SQLException e) {
			log.error(e);
			return null;
		}
		
	}
	
	public String getFileName(String prefix) {
		return folder + File.separator + prefix + "_out.xlsx";
	}
	
	public String getFileName() {
		return folder + File.separator + "_out.xlsx";
	}
}
