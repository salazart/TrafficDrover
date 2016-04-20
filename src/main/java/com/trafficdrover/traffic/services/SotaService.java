package com.trafficdrover.traffic.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.trafficdrover.TrafficDrover;
import com.trafficdrover.db.local.models.Traffic;

public class SotaService {
	private List<String> warningList = new ArrayList<String>();
	private int cid;
	private int lac;

	public static void modifyCid(String textCid, Traffic traffic) {
		if(isInteger(textCid)){
			traffic.setCid(textCid);
		} else {
			textCid = StringUtils.lowerCase(textCid);
			textCid = textCid.replaceAll("a", "1");
			textCid = textCid.replaceAll("b", "2");
			textCid = textCid.replaceAll("c", "3");
			textCid = textCid.replaceAll("e", "1");
			textCid = textCid.replaceAll("g", "2");
			textCid = textCid.replaceAll("f", "3");
			textCid = textCid.replaceAll("[^0-9]+", "");
			traffic.setCid(textCid);
		}
	}

	public static void modifyLacCid(String textLac, Traffic traffic) {
		textLac = textLac.replaceAll("[^0-9^A-Z^a-z]+", " ");
		List<String> sota = Arrays.asList(StringUtils.split(textLac, null, 2));
		if(sota.size() > 1){
			traffic.setLac(getInt(sota.get(0)));
			traffic.setCid(getInt(sota.get(1)));
		}
	}
	
	private static int getInt(String value){
		try {
			return Integer.valueOf(value);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public int getCid() {
		return cid;
	}
	
	public int getLac() {
		return lac;
	}

	public String modifyAdressBS(String adressBS) {
		if (adressBS.equals("null") || adressBS.isEmpty()) {
			return "";
		} else {
			return adressBS;
		}
	}

	public String modifyAzimuth(String azimuth) {
		if (azimuth != null
				&& !azimuth.isEmpty()
				&& !azimuth.equals("null")) {
			return handleAzimuth(azimuth);
		} else {
			return "";
		}
	}
	
	private String handleAzimuth(String azimuth){
		String outAzimuth = "";
		if(!isInteger(azimuth)){
			azimuth = azimuth.replaceAll("[^0-9.,]+", "");
			String[] outText = StringUtils.splitByCharacterType(azimuth);
			outAzimuth = (outText.length > 0) ? outText[0] : "";
		} else {
			return String.valueOf(Integer.valueOf(azimuth));
		}
		
		if(isInteger(outAzimuth)){
			return String.valueOf(Integer.valueOf(outAzimuth));
		} else {
			return "";
		}
	}
	
	
	
	private static boolean isInteger(String text){
		try {
			Integer.valueOf(text);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void warningMessage(String message) {
		boolean warningFlag = false;
		for (int i = 0; i < warningList.size(); i++) {
			if (message.equals(warningList.get(i))) {
				warningFlag = true;
			}
		}

		if (warningFlag == false) {
			warningList.add(message);
			TrafficDrover.instance.addLog(message);
		}
	}
}
