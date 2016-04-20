package com.trafficdrover.db.antares.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.trafficdrover.db.antares.models.Crime;
import com.trafficdrover.db.dao.AbstractDao;
import com.trafficdrover.db.interfaces.IConnectionService;

public class CrimeService extends AbstractDao<Crime>{

	@Override
	protected String queryInsert() {
		return "INSERT INTO zloch_tbl "
				+ "(date_zloch, tezis_zloch, fabula_zloch, pib_poterp, pib_zloch, id_active) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected void prepareInsert(PreparedStatement ps, Crime object) throws Exception {
		ps.setDate(1, object.getDateCrime());
		ps.setString(2, object.getThesis());
		ps.setString(3, object.getStory());
		ps.setString(4, object.getVictim());
		ps.setString(5, object.getSuspect());
		ps.setInt(6, object.getIdActive());
	}

	@Override
	protected String queryGet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareGet(PreparedStatement ps, Crime object) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void resultGet(ResultSet rs, Crime object) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected IConnectionService getConnectionService() {
		return new AntaresConnection();
	}

}
