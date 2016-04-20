package com.trafficdrover.db.antares.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.trafficdrover.db.antares.models.ZajDet;
import com.trafficdrover.db.dao.AbstractDao;
import com.trafficdrover.db.interfaces.IConnectionService;

public class ZayDetService extends AbstractDao<ZajDet>{
	
	@Override
	protected String queryInsert() {
		return "INSERT INTO zaj_det_tbl "
				+ "(id_zaj_det, id_zloch_orsksr, tezis_zaj_det, id_zaj, id_zloch) "
				+ "VALUES (DEFAULT, ?, ?, ?, ?)";
	}

	@Override
	protected void prepareInsert(PreparedStatement ps, ZajDet object) throws Exception {
		ps.setInt(1, object.getIdZlochOrsksr());
		ps.setString(2, object.getTezisZajDet());
		ps.setInt(3, object.getIdZaj());
		ps.setInt(4, object.getIdZloch());
	}
	
	@Override
	protected String queryGet() {
		return "SELECT id_zaj_det FROM zaj_det_tbl "
				+ "WHERE id_zaj=? AND id_zloch=? AND id_zloch_orsksr=?";
	}

	@Override
	protected void prepareGet(PreparedStatement ps, ZajDet object) throws Exception {
		ps.setInt(1, object.getIdZaj());
		ps.setInt(2, object.getIdZloch());
		ps.setInt(3, object.getIdZlochOrsksr());
	}

	@Override
	protected void resultGet(ResultSet rs, ZajDet object) throws Exception {
		object.setId(rs.getInt("id_zaj_det"));
	}

	@Override
	protected IConnectionService getConnectionService() {
		return new AntaresConnection();
	}
}
