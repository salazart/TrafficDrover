package com.trafficdrover.db.local.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.trafficdrover.db.interfaces.IConnectionService;

public class LocalConnection implements IConnectionService {
	private static final String PATH_TO_DB = "/config/local";
	private static final String user = "sa";
    private static final String password = "";
    private String url = "jdbc:h2:file:" + System.getProperty("user.dir") + PATH_TO_DB;
    
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}
}
