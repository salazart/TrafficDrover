package com.trafficdrover.gui.model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.trafficdrover.utils.PropService;

public class ReviewPanel extends JPanel{
	private static final String PROPERTY_FOLDER = "folder";
	private String folder = PropService.getValue(PROPERTY_FOLDER);
	
	private JTextField reviewText;
	
	public ReviewPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JButton reviewButton = new JButton("Обзор");
		reviewButton.addActionListener(new ActionReviewButton());
		add(reviewButton, BorderLayout.EAST);
		
		reviewText = new JTextField();
		add(reviewText, BorderLayout.CENTER);
		reviewText.setText(folder);
		
		reviewText.setEditable(false);
	}

	public class ActionReviewButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser(); 
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setCurrentDirectory(new File(folder));
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				if(chooser.getSelectedFile().isDirectory()){
					folder = chooser.getSelectedFile().toString();
				} else {
					folder = chooser.getCurrentDirectory().toString();
				}
			}
			reviewText.setText(folder);
			PropService.setValue(PROPERTY_FOLDER, folder);
		}
	}
}
