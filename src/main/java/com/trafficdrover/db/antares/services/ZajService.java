package com.trafficdrover.db.antares.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.trafficdrover.db.antares.models.Zaj;
import com.trafficdrover.db.dao.AbstractDao;
import com.trafficdrover.db.interfaces.IConnectionService;

public class ZajService extends AbstractDao<Zaj>{
	
	@Override
	protected String queryInsert() {
		return "INSERT INTO zaj_tbl "
				+ "(id_zaj, id_viddil, date_in, num_in, date_in_v, num_in_v, name_init, stan_zaj, zapit_stan, "
				+ "id_active, id_init, id_typ_zaj_rule, text_viddil, boo_cas) "
				+ "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected void prepareInsert(PreparedStatement ps, Zaj object) throws Exception {
		ps.setInt(1, object.getIdSection());
		ps.setDate(2, object.getInDate());
		ps.setInt(3, object.getInNum());
		ps.setDate(4, object.getInDateV());
		ps.setInt(5, object.getInNumV());
		ps.setString(6, object.getNameInit());
		ps.setInt(7, object.getStanZay());
		ps.setInt(8, object.getZapitStan());
		ps.setInt(9, object.getIdActive());
		ps.setInt(10, object.getIdInit());
		ps.setInt(11, object.getIdTypZajRule());
		ps.setString(12, object.getTextViddil());
		ps.setBoolean(13, object.getBooCas());
	}

	@Override
	protected String queryGet() {
		return "SELECT id_viddil, date_in, num_in, date_in_v, num_in_v, name_init, stan_zaj, zapit_stan, "
				+ "id_active, id_init, id_typ_zaj_rule, text_viddil, boo_cas FROM zaj_tbl "
				+ "WHERE id_zaj=?";
	}

	@Override
	protected void prepareGet(PreparedStatement ps, Zaj object) throws Exception {
		ps.setInt(1, object.getId());
	}

	@Override
	protected void resultGet(ResultSet rs, Zaj object) throws Exception {
		object.setIdSection(rs.getInt("id_viddil"));
		object.setInDate(rs.getDate("date_in"));
		object.setInNum(rs.getInt("num_in"));
		object.setInDateV(rs.getDate("date_in_v"));
		object.setInNumV(rs.getInt("num_in_v"));
		object.setNameInit(rs.getString("name_init"));
		object.setStanZay(rs.getInt("stan_zaj"));
		object.setZapitStan(rs.getInt("zapit_stan"));
		object.setIdActive(rs.getInt("id_active"));
		object.setIdInit(rs.getInt("id_init"));
		object.setIdTypZajRule(rs.getInt("id_typ_zaj_rule"));
		object.setTextViddil(rs.getString("text_viddil"));
		object.setBooCas(rs.getBoolean("boo_cas"));
	}

	@Override
	protected IConnectionService getConnectionService() {
		return new AntaresConnection();
	}
	
}
