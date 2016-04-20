package com.trafficdrover.db.antares.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.trafficdrover.db.antares.models.Orsksr;
import com.trafficdrover.db.dao.AbstractDao;
import com.trafficdrover.db.interfaces.IConnectionService;

public class OrsksrService extends AbstractDao<Orsksr>{

	@Override
	protected String queryGet() {
		return "SELECT id_zloch_orsksr, id_zloch, id_okrug, date_orsksr FROM zloch_orsksr_tbl "
				+ "WHERE num_orsksr=? LIMIT 1";
	}

	@Override
	protected void prepareGet(PreparedStatement ps, Orsksr object) throws Exception {
		ps.setString(1, object.getNumOrsksr());
	}

	@Override
	protected void resultGet(ResultSet rs, Orsksr object) throws Exception {
		object.setId(rs.getInt("id_zloch_orsksr"));
		object.setIdZloch(rs.getInt("id_zloch"));
		object.setIdOkrug(rs.getInt("id_okrug"));
		object.setDateOrsksr(rs.getDate("date_orsksr"));
	}

	@Override
	protected String queryInsert() {
		return "INSERT INTO zloch_orsksr_tbl "
				+ "(id_zloch, id_typ_orsksr, id_okrug, num_orsksr, date_orsksr) "
				+ "VALUES (?, ?, ?, ?, ?)";
	}

	@Override
	protected void prepareInsert(PreparedStatement ps, Orsksr object) throws Exception {
		ps.setInt(1, object.getIdZloch());
		ps.setInt(2, object.getIdTypeOrsksr());
		ps.setInt(3, object.getIdOkrug());
		ps.setString(4, object.getNumOrsksr());
		ps.setDate(5, object.getDateOrsksr());
	}

	@Override
	protected IConnectionService getConnectionService() {
		return new AntaresConnection();
	}
}
