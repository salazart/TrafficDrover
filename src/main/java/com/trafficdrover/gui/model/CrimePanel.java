package com.trafficdrover.gui.model;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.trafficdrover.utils.PropService;

public class CrimePanel extends JPanel {
	private static final String ERDR = "erdr";
	private static final String ZAJ_NUMBER = "zajNumber";
	
	private JTextField crimeField;
	private JTextField zajField;

	/**
	 * Create the panel.
	 */
	public CrimePanel() {
		
		JLabel label = new JLabel("ЄРДР:");
		
		JLabel label_1 = new JLabel("№ заявки:");
		
		crimeField = new JTextField();
		crimeField.setText(PropService.getValue(ERDR));
		
		zajField = new JTextField();
		zajField.setText(PropService.getValue(ZAJ_NUMBER));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(label))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(crimeField, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
						.addComponent(zajField, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(crimeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(zajField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		crimeField.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e) {
			}
			public void focusLost(FocusEvent e) {
				PropService.setValue(ERDR, crimeField.getText());
			}
		});
		
		zajField.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e) {
			}
			public void focusLost(FocusEvent e) {
				PropService.setValue(ZAJ_NUMBER, zajField.getText());
			}
		});

	}
}
