package com.trafficdrover.db.armor.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.trafficdrover.db.interfaces.IConnectionService;
import com.trafficdrover.utils.PropService;

public class ArmorConnection implements IConnectionService {
	private static final String URL = "armorUrl";
	private static final String USER = "armorUser";
	private static final String PASS = "armorPass";
	
	private String url = PropService.getValue(URL);
	private String user = PropService.getValue(USER);
    private String password = PropService.getValue(PASS);

	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}

}
