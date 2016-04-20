package com.trafficdrover.gui.model;

import java.awt.Checkbox;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.trafficdrover.gui.services.ActionChangeCheckBox;
import com.trafficdrover.utils.PropService;

public class AdditionalPropertyPanel extends JPanel {
	/**
	 * This property panel
	 */
	private static final String FLAG_PART_PHONE = "partPhoneA";
	private static final String FLAG_PART_IMEI = "partImeiA";
	private static final String FLAG_ADD_TRAFFIC = "addTraffic";
	//private static final String FLAG_ADD_MONITORING = "addMonitoring";

	private Checkbox partPhone;
	private Checkbox partImei;
	private Checkbox addTraffic;
	//private Checkbox addMonitoring;

	private boolean flagPartPhone = Boolean.valueOf(PropService.getValue(FLAG_PART_PHONE));
	private boolean flagPartImei = Boolean.valueOf(PropService.getValue(FLAG_PART_IMEI));
	private boolean flagAddTraffic = Boolean.valueOf(PropService.getValue(FLAG_ADD_TRAFFIC));
	//private boolean flagAddMonitoring = Boolean.valueOf(PropService.getValue(FLAG_ADD_MONITORING));

	public AdditionalPropertyPanel() {
		setLayout(new GridLayout(3, 1));
		setBorder(BorderFactory.createTitledBorder("Додаткові можливості:"));

		partPhone = new Checkbox("Розбити по абоненту А");
		add(partPhone);
		partPhone.setState(flagPartPhone);
		partPhone.addItemListener(new ActionChangeCheckBox(FLAG_PART_PHONE));
		
		partImei = new Checkbox("Розбити по Imei A");
		add(partImei);
		partImei.setState(flagPartImei);
		partImei.addItemListener(new ActionChangeCheckBox(FLAG_PART_IMEI));

		addTraffic = new Checkbox("Додати траффик");
		add(addTraffic);
		addTraffic.setState(flagAddTraffic);
		addTraffic.addItemListener(new ActionChangeCheckBox(FLAG_ADD_TRAFFIC));
		
//		addMonitoring = new Checkbox("Додати мониторинг");
//		add(addMonitoring);
//		addMonitoring.setState(flagAddMonitoring);
//		addMonitoring.addItemListener(new ActionChangeCheckBox(FLAG_ADD_MONITORING));
	}

}
