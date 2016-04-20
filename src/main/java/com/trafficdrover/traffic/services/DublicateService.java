package com.trafficdrover.traffic.services;

import com.trafficdrover.db.local.models.Traffic;

/**
 * This class remove duplicates for Traffic
 * @author dr
 *
 */
public class DublicateService {
	private static final String IN_TIME_FORMAT = "HH:mm:ss";
	
	private static final String OUT_SECONDS_FORMAT = "ss";
	
	private static final String OUT_TIME_FORMAT = "HH:mm";
	
	private static int dublicateSecondDifference = 2;
	
	public static boolean isEqualTraffic(Traffic firstTraffic, Traffic secondTraffic){
		if(isEqualDate(firstTraffic, secondTraffic)){
			if(isEqualTime(firstTraffic, secondTraffic)){
				if(isEqualDuration(firstTraffic, secondTraffic)){
					if(isEqualType(firstTraffic, secondTraffic)){
						if(isEqualPhone(firstTraffic, secondTraffic)){
							if(isEqualBS(firstTraffic, secondTraffic)){
								return true;
							} else 
								return false;
						} else
							return false;
					} else 
						return false;
				} else 
					return false;
			} else 
				return false;
		} else 
			return false;
	}
	
	private static boolean isEqualDate(Traffic firstTraffic, Traffic secondTraffic){
		if(firstTraffic.getDate().equals(secondTraffic.getDate())){
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isEqualTime(Traffic firstTraffic, Traffic secondTraffic){
		if(isEqualTimeWithoutSeconds(firstTraffic.getTime(), secondTraffic.getTime())
				&& isEqualSeconds(firstTraffic.getTime(), secondTraffic.getTime())){
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isEqualDuration(Traffic firstTraffic, Traffic secondTraffic){
		if(isEqualTimeWithoutSeconds(firstTraffic.getDuration(), secondTraffic.getDuration())
				&& isEqualSeconds(firstTraffic.getDuration(), secondTraffic.getDuration())){
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isEqualType(Traffic firstTraffic, Traffic secondTraffic){
		if(firstTraffic.getType().equals(secondTraffic.getType())){
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isEqualPhone(Traffic firstTraffic, Traffic secondTraffic){
		if(firstTraffic.getPhoneA().equals(secondTraffic.getPhoneA()) && firstTraffic.getPhoneB().equals(secondTraffic.getPhoneB())){
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isEqualBS(Traffic firstTraffic, Traffic secondTraffic){
		if(firstTraffic.getLac() == secondTraffic.getLac() && firstTraffic.getCid() == secondTraffic.getCid()){
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isEqualTimeWithoutSeconds(String firstTime, String secondTime){
		DateService dateService = new DateService();
		firstTime = dateService.parseDateToString(firstTime, IN_TIME_FORMAT, OUT_TIME_FORMAT);
		secondTime = dateService.parseDateToString(secondTime, IN_TIME_FORMAT, OUT_TIME_FORMAT);
		if(firstTime.equals(secondTime)){
			return true;
		} else{
			return false;
		}
	}
	
	private static boolean isEqualSeconds(String firstTime, String secondTime){
		if(firstTime.isEmpty() || secondTime.isEmpty()){
			return false;
		} 
		
		DateService dateService = new DateService();
		
		firstTime = dateService.parseDateToString(firstTime, IN_TIME_FORMAT, OUT_SECONDS_FORMAT);        
		secondTime = dateService.parseDateToString(secondTime, IN_TIME_FORMAT, OUT_SECONDS_FORMAT);
		
		int firstSeconds = Integer.valueOf(firstTime);
		int secondSeconds = Integer.valueOf(secondTime);
		
		int difference = Math.abs(firstSeconds - secondSeconds);
		
		if(difference <= dublicateSecondDifference){
			return true;
		} else {
			return false;
		}
	}
}
