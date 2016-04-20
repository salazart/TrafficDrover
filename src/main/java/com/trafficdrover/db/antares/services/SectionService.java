package com.trafficdrover.db.antares.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.trafficdrover.db.antares.models.Section;
import com.trafficdrover.db.dao.AbstractDao;
import com.trafficdrover.db.interfaces.IConnectionService;

public class SectionService extends AbstractDao<Section>{
	@Override
	protected String queryInsert() {
		return null;
	}

	@Override
	protected void prepareInsert(PreparedStatement ps, Section object) throws Exception {
	}

	@Override
	protected String queryGet() {
		return "SELECT id_viddil, text_viddil, id_okrug FROM viddil_tbl "
				+ "WHERE id_okrug=? "
				+ "LIMIT 1";
	}

	@Override
	protected void prepareGet(PreparedStatement ps, Section object) throws Exception {
		ps.setInt(1, object.getIdOkrug());
	}

	@Override
	protected void resultGet(ResultSet rs, Section object) throws Exception {
		object.setId(rs.getInt("id_viddil"));
		object.setTextSection(rs.getString("text_viddil"));
		object.setIdOkrug(rs.getInt("id_okrug"));
	}

	@Override
	protected IConnectionService getConnectionService() {
		return new AntaresConnection();
	}
}
