package com.trafficdrover.traffic.services;

import java.util.ArrayList;
import java.util.List;

import com.trafficdrover.TrafficDrover;
import com.trafficdrover.traffic.models.TType;

/**
 * This class modify the type call phone
 * @author home
 *
 */
public class TypeService extends XStreamService<TType>{
	private final String PATH_TYPE_BASE = "config/TypeBase.xml";
	private List<String> warningList = new ArrayList<String>();

	private List<TType> tTypes;
	
	public TypeService() {
		tTypes = getRules(new TType(), "types", PATH_TYPE_BASE);
	}
	
	public String modyfiValue(String value) {
		for(int i = 0; i < tTypes.size(); i++){
			TType tType = tTypes.get(i);
			if(value.equals(tType.getInType())){
				return tType.getOutType();
			}
		}
		warningMessage("Не знайдено тип зєднання: " + value);
		return value;
	}
	
	private void warningMessage(String message){
		if(!warningList.contains(message)){
			warningList.add(message);
			TrafficDrover.instance.addLog(message);
		}
	}
}
