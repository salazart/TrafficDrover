package com.trafficdrover.traffic.services;

import java.util.List;

import com.trafficdrover.traffic.models.Phone;


/**
 * This service modify phone number and returning it;
 * @author Dr
 *
 */
public class PhoneService extends XStreamService<Phone>{
	private final int MAX_LENGHT_FIELD_PHONE = 20;
	private final int MIN_LENGHT_PHONE = 9;
	private final int MAX_LENGHT_PHONE = 12;
	private final int IMEI_LENGHT = 14;
	private final String PATH_PHONE_BASE = "config/PhoneBase.xml";
	
	private List<Phone> phones;
	
	public PhoneService() {
		phones = getRules(new Phone(), "phones", PATH_PHONE_BASE);
	}
	
	public boolean isPhoneModel(String phone){
		phone = clearPhone(phone);
		return (phone.length() >= MIN_LENGHT_PHONE && phone.length() <= MAX_LENGHT_PHONE);
	}
	
	public String modifyPhone(String value){
		if(isPhoneModel(value)){
			String tempPhone = clearPhone(value);
			
			for (int i = 0; i < phones.size(); i++) {
				int lenght = phones.get(i).getLenght();
				String kod = phones.get(i).getCode();
				String prefix = phones.get(i).getPrefix();
				
				if(tempPhone.length() == lenght && tempPhone.startsWith(prefix)){
					return kod + tempPhone;
				}
			}
			//log.debug("Невідомий формат номеру телефону: " + value);
			return value;
		} else {
			if(value.length() > MAX_LENGHT_FIELD_PHONE){
				return value.substring(0, MAX_LENGHT_FIELD_PHONE);
			} else {
				return value;
			}
		}
	}
	
	/**
	 * This method check imei of phone
	 */
	public String modifyImei(String imei) {
		imei = clearPhone(imei);
		
		if(imei.startsWith("12") || imei.startsWith("13")){
			return "0" + imei;
		} else if (imei.length() >= IMEI_LENGHT) {
			imei = imei.substring(0, IMEI_LENGHT);
			return imei + "0";
		} else
			return "";
	}
	
	public String clearPhone(String phone){
		return phone.replaceAll("[^0-9]+","");
	}
}
