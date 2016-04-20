package com.trafficdrover.db.local.services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.trafficdrover.TrafficDrover;
import com.trafficdrover.db.dao.AbstractDao;
import com.trafficdrover.db.interfaces.IConnectionService;
import com.trafficdrover.db.local.models.BaseStation;
import com.trafficdrover.db.local.models.Traffic;
import com.trafficdrover.folder.services.SaveService;
import com.trafficdrover.folder.services.WriteXLSXService;
import com.trafficdrover.traffic.services.DublicateService;

public class TrafficService extends AbstractDao<Traffic>{
	private SaveService saveService;
	private WriteXLSXService writeXLSXService = new WriteXLSXService();
	
	protected static final Logger log = LogManager.getRootLogger();
	
	public TrafficService() {
		
	}
	
	public void createTraffic(){
		executeUpdate("CREATE TABLE IF NOT EXISTS traffic "
				+ "(id INT NOT NULL AUTO_INCREMENT, "
				+ "type VARCHAR(255) NOT NULL DEFAULT '', "
				+ "date VARCHAR(255) NOT NULL DEFAULT '', "
				+ "time VARCHAR(255) NOT NULL DEFAULT '', "
				+ "duration VARCHAR(255) NOT NULL DEFAULT '', "
				+ "phoneA VARCHAR(255) NOT NULL DEFAULT '', "
				+ "imeiA VARCHAR(255) NOT NULL DEFAULT '', "
				+ "phoneB VARCHAR(255) NOT NULL DEFAULT '', "
				+ "imeiB VARCHAR(255) NOT NULL DEFAULT '', "
				+ "lac VARCHAR(255) NOT NULL DEFAULT '', "
				+ "cid VARCHAR(255) NOT NULL DEFAULT '', "
				+ "azimuth VARCHAR(255) NOT NULL DEFAULT '', "
				+ "adressBS VARCHAR(255) NOT NULL DEFAULT '', "
				+ "defaultDate TIMESTAMP, PRIMARY KEY (ID));");
	}
	
	public List<String> getAbonentsA(){
		List<String> abonents = new ArrayList<String>();
		try (ResultSet rs = getConnection().createStatement().executeQuery(saveService.queryGetAbonents())){
			while(rs.next()){
				abonents.add(rs.getString(1));
			}
		} catch (Exception e) {
			log.error(e);
		}
		return abonents; 
	}
	
	@Override
	protected String queryInsert() {
		return "INSERT INTO traffic " 
				+ "(id, type, date, time, duration, phoneA, imeiA, phoneB, lac, cid, azimuth, adressBS, defaultDate) "
				+ "VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	}

	@Override
	protected void prepareInsert(PreparedStatement ps, Traffic object) throws Exception {
		ps.setString(1, object.getType());
		ps.setString(2, object.getDate());
		ps.setString(3, object.getTime());
		ps.setString(4, object.getDuration());
		ps.setString(5, object.getPhoneA());
		ps.setString(6, object.getImeiA());
		ps.setString(7, object.getPhoneB());
		ps.setInt(8, object.getLac());
		ps.setInt(9, object.getCid());
		ps.setString(10, object.getAzimuth());
		ps.setString(11, object.getAdressBS());
		ps.setTimestamp(12, new Timestamp(object.getDefaultDate().getTime()));
	}
	
	public void saveTrafficToFile(List<BaseStation> baseStations, boolean isPhoneA, boolean isImeiA) throws Exception{
		log.debug("Start process selectting traffic form DB traffic");
		this.saveService = new SaveService(isPhoneA, isImeiA);
		
		if(isPhoneA || isImeiA){
			List<String> abonents = getAbonentsA();
			for(int i = 0; i < abonents.size(); i++){
				ResultSet rs = saveService.prepareSaveTraffic(getConnection(), abonents.get(i));
				
				writeXLSXService.createWorkBook();
				writeWorkBook(baseStations, rs);
				writeXLSXService.saveWorkBook(saveService.getFileName(abonents.get(i)));
				TrafficDrover.instance.addLog("Файл збережений успішно: " + saveService.getFileName(abonents.get(i)));
			}
		} else {
			ResultSet rs = saveService.prepareSaveTraffic(getConnection());

			writeXLSXService.createWorkBook();
			writeWorkBook(baseStations, rs);
			writeXLSXService.saveWorkBook(saveService.getFileName());
		}
		
		log.debug("End process selecting traffic form DB traffic");
	}

	private void writeWorkBook(List<BaseStation> baseStations, ResultSet rs) throws SQLException {
		Traffic postTraffic = new Traffic();
		while (rs.next()) {
			Traffic traffic = getTraffic(rs);
			writeBaseStation(traffic, baseStations);

			if (!traffic.isEmpty()) {
				if (rs.isFirst() || rs.isLast()) {
					writeXLSXService.addRow(traffic);
				} else {
					if (!DublicateService.isEqualTraffic(postTraffic, traffic)) {
						writeXLSXService.addRow(traffic);
					}
				}
			}
			postTraffic = traffic;
		}
	}

	private void writeBaseStation(Traffic traffic, List<BaseStation> baseStations){
		for (BaseStation baseStation : baseStations) {
			if(baseStation.getLac() == traffic.getLac()
					&& baseStation.getCid() == traffic.getCid()){
				
				traffic.setAzimuth(baseStation.getAzimuth());
				traffic.setAdressBs(baseStation.getAdressBs());
				break;
			}
		}
	}
	
	public void dropTraffic() {
		executeUpdate("DROP TABLE IF EXISTS traffic;");
		log.info("Table traffic dropped succesffully");
	}

	private Traffic getTraffic(ResultSet rs) throws SQLException {
		Traffic traffic = new Traffic();
		traffic.setType(rs.getString(1));
		traffic.setDate(rs.getString(2));
		traffic.setTime(rs.getString(3));
		traffic.setDuration(rs.getString(4));
		traffic.setPhoneA(rs.getString(5));
		traffic.setImeiA(rs.getString(6));
		traffic.setPhoneB(rs.getString(7));
		traffic.setLac(rs.getInt(8));
		traffic.setCid(rs.getInt(9));
		traffic.setAzimuth(rs.getString(10));
		traffic.setAdressBs(rs.getString(11));
		return traffic;
	}

	public List<BaseStation> getUniqueBsOfTraffic() {
		log.debug("Start find unique base station");

		String query = "SELECT lac, cid, azimuth, adressBS FROM traffic GROUP BY lac, cid;";
		
		List<BaseStation> uniqueBsOfTraffic = new ArrayList<BaseStation>();
		try (ResultSet rs = getConnection().createStatement().executeQuery(query)){
			while (rs.next()) {
				BaseStation baseStation = new BaseStation(
						rs.getInt("lac"),
						rs.getInt("cid"),
						rs.getString("azimuth"),
						rs.getString("adressBS"));
				
				uniqueBsOfTraffic.add(baseStation);
			}			
		} catch (Exception e) {
			log.error(e);
		}

		log.debug("Finish find unique base station");
		return uniqueBsOfTraffic;
	}

	@Override
	protected IConnectionService getConnectionService() {
		return new LocalConnection();
	}
	
	public Date getMaxDate(){
		String query = "SELECT MAX(defaultDate) FROM traffic";
		return getDate(query);
	}
	
	public Date getMinDate(){
		String query = "SELECT MIN(defaultDate) FROM traffic";
		return getDate(query);
	}
	
	private Date getDate(String query){
		Date date = null;
		try (ResultSet rs = getConnection().createStatement().executeQuery(query)){
			if(rs.next()){
				date = rs.getDate(1);
			}
		} catch (Exception e) {
			log.error(e);
		}
		return date;
	}
	
	@Override
	protected String queryGet() {
		return null;
	}

	@Override
	protected void prepareGet(PreparedStatement ps, Traffic object) throws Exception {
	}

	@Override
	protected void resultGet(ResultSet rs, Traffic object) throws Exception {
	}
}
