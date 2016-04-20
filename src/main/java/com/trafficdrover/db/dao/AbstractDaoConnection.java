package com.trafficdrover.db.dao;

import java.sql.Connection;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.trafficdrover.db.interfaces.IConnectionService;

public abstract class AbstractDaoConnection {
	protected static final Logger log = LogManager.getRootLogger();
	
	protected abstract IConnectionService getConnectionService();
	
	protected Connection getConnection() throws Exception{
		return getConnectionService().getConnection();
	}
	
	protected void executeUpdate(String query){
		try (Statement stat = getConnection().createStatement()){
			stat.executeUpdate(query);
		} catch (Exception e) {
			log.error(e);
		}
	}
}	
