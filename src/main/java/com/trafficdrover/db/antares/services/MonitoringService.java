package com.trafficdrover.db.antares.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.trafficdrover.db.antares.models.Monitoring;
import com.trafficdrover.db.dao.AbstractDao;
import com.trafficdrover.db.interfaces.IConnectionService;

public class MonitoringService extends AbstractDao<Monitoring>{

	@Override
	protected String queryInsert() {
		return "INSERT INTO monitor_tbl "
				+ "(id_monitor, id_viddil, date_monitor, id_zloch, date_b, date_e, text_monitor) "
				+ "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected void prepareInsert(PreparedStatement ps, Monitoring object) throws Exception {
		ps.setInt(1, object.getIdViddil());
		ps.setTimestamp(2, new Timestamp(object.getDateMonitor().getTime()));
		ps.setInt(3, object.getIdZloch());
		ps.setTimestamp(4, new Timestamp(object.getDateB().getTime()));
		ps.setTimestamp(5, new Timestamp(object.getDateE().getTime()));
		ps.setString(6, object.getTextMonitor());
	}

	@Override
	protected String queryGet() {
		return null;
	}

	@Override
	protected void prepareGet(PreparedStatement ps, Monitoring object) throws Exception {
	}

	@Override
	protected void resultGet(ResultSet rs, Monitoring object) throws Exception {
	}

	@Override
	protected IConnectionService getConnectionService() {
		return new AntaresConnection();
	}
}
