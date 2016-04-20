package com.trafficdrover.db.antares.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.trafficdrover.db.antares.models.ZajNum;
import com.trafficdrover.db.dao.AbstractDao;
import com.trafficdrover.db.interfaces.IConnectionService;

public class ZajNumService extends AbstractDao<ZajNum>{
	
	@Override
	protected String queryInsert() {
		return "INSERT INTO zaj_num_tbl "
				+ "(id_zaj_num, id_zaj_det, date_b, date_e, number, id_typ_analiz, id_niv, text_num_zaj, id_zloch) "
				+ "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected void prepareInsert(PreparedStatement ps, ZajNum object) throws Exception {
		ps.setInt(1, object.getIdZajDet());
		ps.setDate(2, object.getDateB());
		ps.setDate(3, object.getDateE());
		ps.setString(4, object.getNumber());
		ps.setInt(5, object.getIdTypAnaliz());
		ps.setInt(6, object.getIdNiv());
		ps.setString(7, object.getTextNumZaj());
		ps.setInt(8, object.getIdZloch());
	}

	@Override
	protected String queryGet() {
		return null;
	}

	@Override
	protected void prepareGet(PreparedStatement ps, ZajNum object) throws Exception {
	}

	@Override
	protected void resultGet(ResultSet rs, ZajNum object) throws Exception {
	}

	@Override
	protected IConnectionService getConnectionService() {
		return new AntaresConnection();
	}
}
