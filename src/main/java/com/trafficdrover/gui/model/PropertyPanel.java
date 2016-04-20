package com.trafficdrover.gui.model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.trafficdrover.folder.services.ConversionProcess;

public class PropertyPanel extends JPanel {

  public PropertyPanel() {
    setLayout(new BorderLayout(0, 0));

    JPanel crimePanel = new CrimePanel();
    add(crimePanel, BorderLayout.WEST);
    
    JPanel additionalPanel = new AdditionalPropertyPanel();
    add(additionalPanel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    add(buttonPanel, BorderLayout.EAST);

    JButton runButton = new JButton("Старт");
    buttonPanel.add(runButton);
    runButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			new Thread(new ConversionProcess()).start();
		}
    });
  }
}
