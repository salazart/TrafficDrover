package com.trafficdrover.db.antares.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.trafficdrover.db.antares.models.Initiator;
import com.trafficdrover.db.dao.AbstractDao;
import com.trafficdrover.db.interfaces.IConnectionService;

public class InitiatorService extends AbstractDao<Initiator>{
	@Override
	protected String queryInsert() {
		return null;
	}
	@Override
	protected void prepareInsert(PreparedStatement ps, Initiator object) throws Exception {
		
	}
	@Override
	protected String queryGet() {
		return "SELECT id_init FROM init_tbl WHERE id_viddil=? LIMIT 1";
	}
	@Override
	protected void prepareGet(PreparedStatement ps, Initiator object) throws Exception {
		ps.setInt(1, object.getIdSection());
	}
	@Override
	protected void resultGet(ResultSet rs, Initiator object) throws Exception {
		object.setId(rs.getInt("id_init"));
	}
	@Override
	protected IConnectionService getConnectionService() {
		return new AntaresConnection();
	}
}
