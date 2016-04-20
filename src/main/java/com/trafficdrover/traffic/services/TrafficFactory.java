package com.trafficdrover.traffic.services;

import com.trafficdrover.db.local.models.Traffic;
import com.trafficdrover.traffic.models.THeader;
import com.trafficdrover.traffic.models.THeader.Head;

/**
 * This is factory traffic, that modify data and write is to main table.
 * 
 * @param value
 *            - current value, that modifing
 * @param dataType
 *            - type current data value
 * @return
 */
public class TrafficFactory {
	private TypeService typeService = new TypeService();
	private PhoneService phoneService = new PhoneService();
	private SotaService lacCidServise = new SotaService();
	private DateService dateService = new DateService();

	protected void factoryTraffic(Traffic traffic, String value, Head type) {
		switch (type.toString()) {
		case THeader.TYPE:
			traffic.setType(typeService.modyfiValue(value));
			break;
		case THeader.DATE:
			dateService.parseDate(value);
			traffic.setDate(dateService.getDate());
			traffic.setTime(dateService.getTime());
			traffic.setDefaultDate(dateService.getDefaultDate());
			break;
		case THeader.DURATION:
			traffic.setDuration(dateService.getDuration(value));
			break;
		case THeader.PHONE_A:
			traffic.setPhoneA(phoneService.modifyPhone(value));
			break;
		case THeader.IMEI_A:
			traffic.setImeiA(phoneService.modifyImei(value));
			break;
		case THeader.PHONE_B:
			String phoneB = phoneService.modifyPhone(value);
			if (traffic.getPhoneB().isEmpty()) {
				if (!traffic.getPhoneB().equals(traffic.getPhoneA())) {
					traffic.setPhoneB(phoneB);
				}
			} else {
				if (traffic.getPhoneA().equals(traffic.getPhoneB())) {
					traffic.setPhoneB(phoneB);
				}
			}
			break;
		case THeader.LAC:
			traffic.setLac(value);
			break;
		case THeader.LACCID:
			SotaService.modifyLacCid(value, traffic);
			break;
		case THeader.CID:
			SotaService.modifyCid(value, traffic);
			break;
		case THeader.AZIMUTH:
			traffic.setAzimuth(lacCidServise.modifyAzimuth(value));
			break;
		case THeader.BS:
			traffic.setAdressBs(lacCidServise.modifyAdressBS(value));
			break;
		default:
			break;
		}
	}
}
