package com.trafficdrover.db.antares.services;

import java.sql.Connection;
import java.sql.DriverManager;

import com.trafficdrover.db.interfaces.IConnectionService;
import com.trafficdrover.utils.PropService;

public class AntaresConnection implements IConnectionService{
	private static final String URL = "antaresUrl";
	private static final String USER = "antaresUser";
	private static final String PASS = "antaresPass";
	
	private String url = PropService.getValue(URL);
	private String user = PropService.getValue(USER);
    private String password = PropService.getValue(PASS);

	public Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection(url, user, password);
	}
}
