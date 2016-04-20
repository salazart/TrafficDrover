package com.trafficdrover.db.local.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.trafficdrover.db.dao.AbstractDao;
import com.trafficdrover.db.interfaces.IConnectionService;
import com.trafficdrover.db.local.models.BaseStation;
import com.trafficdrover.utils.PropService;

public class BsService extends AbstractDao<BaseStation> {
	private static final String TEMP_TABLE_NAME = "temp_table";
	private static final String TABLE_NAME = "bs";

	private static final String FLAG_PROPERTY_VALUE = "useLocalBaseStation";

	private String tableName;

	private boolean useLocalBaseStation = Boolean.valueOf(PropService.getValue(FLAG_PROPERTY_VALUE));

	public BsService() {
		if (useLocalBaseStation) {
			tableName = TABLE_NAME;
		} else {
			tableName = TEMP_TABLE_NAME;
			createBaseStation();
		}
	}

	public void addBaseStation(BaseStation baseStation) {
		try {
			insert(baseStation);
		} catch (Exception e) {
		}
	};

	@Override
	protected String queryInsert() {
		return "INSERT INTO " + tableName
				+ " (sumlaccid, lac, cid, azimuth, adress)"
				+ "VALUES(?, ?, ?, ?, ?);";
	}

	@Override
	protected void prepareInsert(PreparedStatement ps, BaseStation object) throws Exception {
		ps.setString(1, object.getSumlacCid());
		ps.setInt(2, object.getLac());
		ps.setInt(3, object.getCid());
		ps.setString(4, object.getAzimuth());
		ps.setString(5, object.getAdressBs());
	}
	
	public void createBaseStation() {
		String query = "CREATE TABLE IF NOT EXISTS " + tableName + " "
				+ "(id SERIAL AUTO_INCREMENT, " + "lac INTEGER NOT NULL, "
				+ "cid INTEGER NOT NULL, " + "azimuth INTEGER NOT NULL, "
				+ "adress VARCHAR(255) NOT NULL, " + "PRIMARY KEY (ID));";
		executeUpdate(query);
	}
	
	@Override
	protected String queryGet() {
		return "SELECT azimuth, adress FROM " + tableName
				+ " WHERE lac=? AND cid=? LIMIT 1";
	}

	@Override
	protected void prepareGet(PreparedStatement ps, BaseStation object) throws Exception {
		ps.setInt(1, object.getLac());
		ps.setInt(2, object.getCid());
	}

	@Override
	protected void resultGet(ResultSet rs, BaseStation object) throws Exception {
		object.setAzimuth(rs.getString("azimuth"));
		object.setAdressBs(rs.getString("adress"));
	}

	@Override
	protected IConnectionService getConnectionService() {
		return new LocalConnection();
	}
}
