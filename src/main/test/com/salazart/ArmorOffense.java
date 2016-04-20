package com.salazart;

import java.sql.Connection;
import java.sql.SQLException;

import com.trafficdrover.db.armor.services.ArmorConnection;

public class ArmorOffense {
	public static void main(String[] args) {
		Connection connection = null;
		try {
			connection = new ArmorConnection().getConnection();
			System.out.println(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
