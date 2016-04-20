package com.trafficdrover.db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.trafficdrover.db.models.Entity;

public abstract class AbstractDao<T extends Entity> extends AbstractDaoConnection{
	
	protected abstract String queryInsert();
	protected abstract void prepareInsert(PreparedStatement ps, T object) throws Exception;
	
	public T insert(T object) throws Exception{
		try (PreparedStatement ps = getConnection().prepareStatement(queryInsert(), Statement.RETURN_GENERATED_KEYS)) {
			prepareInsert(ps, object);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				object.setId(rs.getInt(1));
			}
        }
		return object;
	}
	
	protected abstract String queryGet();
	protected abstract void prepareGet(PreparedStatement ps, T object) throws Exception;
	protected abstract void resultGet(ResultSet rs, T object) throws Exception;
	
	public T get(T object) {
		try (PreparedStatement ps = getConnection().prepareStatement(queryGet())){
			prepareGet(ps, object);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resultGet(rs, object);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return object;
	}
}
