package com.trafficdrover.gui.services;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.trafficdrover.utils.PropService;

public class ActionChangeRadioButton implements ActionListener{
	private static final String FLAG_USE_LOCAL_BASE = "useLocalBaseStation";
	private boolean flagLocalBase;
	
	public ActionChangeRadioButton(boolean flagLocalBase){
		this.flagLocalBase = flagLocalBase;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		PropService.setValue(FLAG_USE_LOCAL_BASE, String.valueOf(flagLocalBase));
	}
}
