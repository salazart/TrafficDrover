package com.trafficdrover.gui.model;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LogPanel extends JScrollPane{
	
	private JTextArea textArea;
	
	public LogPanel() {
		setBorder(BorderFactory.createTitledBorder("Лог подій:"));
		textArea = new JTextArea();

		textArea.setEditable(false);
		setViewportView(textArea);
	}
	
	public void addText(String text){
		textArea.append(text + "\r\n");
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	
}

