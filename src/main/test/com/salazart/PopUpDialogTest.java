package com.salazart;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PopUpDialogTest {
	public static void main(String[] args) {
		int n = JOptionPane.showConfirmDialog(
			    new JFrame(),
			    "Do you want to add traffic into Antares?",
			    "Antares",
			    JOptionPane.YES_NO_OPTION);
		System.out.println(n);
	}
}
