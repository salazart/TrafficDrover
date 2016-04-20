package com.salazart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.trafficdrover.db.local.models.BaseStation;
import com.trafficdrover.db.local.services.LocalConnection;

public class BsTest {
	protected static final Logger log = LogManager.getRootLogger();
	public static void main(String[] args) {
		String query = "SELECT lac, cid, azimuth, adressbs FROM BASE_STATIONS WHERE id = ?";
		String query2 = "INSERT INTO bs (sumlaccid, lac, cid, azimuth, adress) VALUES (?,?,?,?,?)";
		
		LocalConnection connService = new LocalConnection();
		
		for(int i = 1; i < 224573; i++){
			BaseStation bs = new BaseStation();
			bs.setId(i);
			getBS(query, connService, bs);
			
			System.out.println(i + " " + bs);
			
			insertBS(query2, connService, bs);
		}
		

		
	}
	private static void insertBS(String query2, LocalConnection connService, BaseStation bs) {
		try (PreparedStatement ps = connService.getConnection().prepareStatement(query2)) {
			ps.setString(1, String.valueOf(bs.getLac()) + String.valueOf(bs.getCid()));
			ps.setInt(2, bs.getLac());
			ps.setInt(3, bs.getCid());
			ps.setString(4, bs.getAzimuth());
			ps.setString(5, bs.getAdressBs());
			ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
	}
	private static void getBS(String query, LocalConnection connService, BaseStation bs) {
		try (PreparedStatement ps = connService.getConnection().prepareStatement(query)) {
			ps.setInt(1, bs.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				bs.setLac(rs.getInt("lac"));
				bs.setCid(rs.getInt("cid"));
				bs.setAzimuth(rs.getString("azimuth"));
				bs.setAdressBs(rs.getString("adressbs"));
			}
        } catch (Exception e) {
        	System.out.println(e);
        }
	}

}
