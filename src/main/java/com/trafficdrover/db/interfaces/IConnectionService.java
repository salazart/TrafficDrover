package com.trafficdrover.db.interfaces;

import java.sql.Connection;

public interface IConnectionService {
	public Connection getConnection() throws Exception;
}
