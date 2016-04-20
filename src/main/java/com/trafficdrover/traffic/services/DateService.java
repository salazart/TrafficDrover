package com.trafficdrover.traffic.services;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.poi.ss.usermodel.DateUtil;

import com.trafficdrover.TrafficDrover;

public class DateService {
	private static final String[] sampleDate = {
		"dd.MM.yyyy HH:mm:ss",
		"EEE MMM d HH:mm:ss zzz yyyy",
		"EEE MMM dd HH:mm:ss zzzz yyyy",
		"yyyy-MM-dd HH:mm:ss"};
	private static final String OUT_DATE = "dd.MM.yyyy";
	private static final String OUT_TIME = "HH:mm:ss";
	private static final String SECONDS = "ss";
	private static final String DATE_WITHOUT_SECONDS = "dd.MM.yyyy HH:mm";
	private Date defaultDate;
	
	/**
	 * Method parse duration of traffic
	 * @param textDate
	 * @return
	 */
	public String getDuration(String textDate){
		Date inDate = parseTextDate(textDate, SECONDS);
		
		if(inDate != null){
			SimpleDateFormat ft = new SimpleDateFormat(OUT_TIME, 
					new DateFormatSymbols(new Locale("UNICODE_LOCALE_EXTENSION")));
			return ft.format(inDate);
		} else {
			return "";
		}
	}
	
	/**
	 * parse date with select type
	 * @param textDate
	 */
	public void parseDate(String textDate){
		if(textDate.matches("-?\\d+(\\.\\d+)?")){
			defaultDate = DateUtil.getJavaDate(Double.valueOf(textDate));
		} else {
			for(int i = 0; i < sampleDate.length; i++){
				defaultDate = parseTextDate(textDate, sampleDate[i]);
				if(defaultDate != null){
					break;
				}
			}
		}
	}
	
	public String parseDateToString(String textDate, String inFormatDate, String outFormatDate){
		Date inDate = parseTextDate(textDate, inFormatDate);
		if(inDate != null){
			SimpleDateFormat ft = new SimpleDateFormat(outFormatDate, 
					new DateFormatSymbols(new Locale("UNICODE_LOCALE_EXTENSION")));
			return ft.format(inDate);
		} else {
			TrafficDrover.instance.addLog("Не можу опрацювати дату: " + textDate 
					+ " формату: " + inFormatDate + " у формат: " + outFormatDate);
			return textDate;
		}
	}
	
	public Date parseTextDate(String textDate, String dateFormat){
		SimpleDateFormat ft = new SimpleDateFormat(dateFormat, 
				new DateFormatSymbols(new Locale("UNICODE_LOCALE_EXTENSION")));
		Date t = null;
		try {
			t = ft.parse(textDate);
			return t;
		} catch (ParseException e) {
			return null;
		}
	}
	
	public String getDate(){
		if(defaultDate != null){
			SimpleDateFormat ft = new SimpleDateFormat(OUT_DATE);
			return ft.format(defaultDate);
		} else {
			return "";
		}
	}
	
	public String getTime(){
		if(defaultDate != null){
			SimpleDateFormat ft = new SimpleDateFormat(OUT_TIME);
			return ft.format(defaultDate);
		} else {
			return "";
		}
	}
	
	public Date getDefaultDate(){
		if(defaultDate != null){
			return defaultDate;
		} else {
			return null;
		}
	}
}
