package com.trafficdrover.gui.services;

import java.awt.Checkbox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.trafficdrover.utils.PropService;

public class ActionChangeCheckBox implements ItemListener{
	private String valueProperty;
	
	public ActionChangeCheckBox(String valueProperty) {
		this.valueProperty = valueProperty;
	}
	
	public void itemStateChanged(ItemEvent arg0) {
		if(arg0.getItemSelectable() instanceof Checkbox){
			Object source = (Checkbox) arg0.getItemSelectable();
			
			PropService.setValue(valueProperty, String.valueOf(((Checkbox) source).getState()));		
		}
	}
}
