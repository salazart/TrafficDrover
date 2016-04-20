package com.trafficdrover.gui.model;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TrafficDroverUI{
	private static final Logger log = LogManager.getRootLogger();
	private static JFrame mainFrame;
	private JPanel contentPane;
	private ReviewPanel reviewPanel;
	private LogPanel logScrollPane;
	private PropertyPanel propertyPanel;
	
	private static TrafficDroverUI instance;
	
    public static TrafficDroverUI getInstance() {
    	log.info("TrafficDroverUI is running");
        if (instance == null) {
        	synchronized(TrafficDroverUI.class) {
        		if(instance == null) {
        			instance = new TrafficDroverUI();
        		}
        	}
        }
        return instance;
    }
    
	/**
	 * Launch the application.
	 */
	public static void showFrame(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					javax.swing.UIManager.setLookAndFeel( javax.swing.UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					log.error(e.getMessage());
				}
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private TrafficDroverUI() {
		mainFrame = new JFrame();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		mainFrame.setTitle("TrafficDrover");
		mainFrame.setBounds(100, 100, 600, 500);
		mainFrame.setContentPane(contentPane);
		
		reviewPanel = new ReviewPanel();
		contentPane.add(reviewPanel, BorderLayout.NORTH);

		logScrollPane = new LogPanel();
		contentPane.add(logScrollPane, BorderLayout.CENTER);
		
		propertyPanel = new PropertyPanel();
		contentPane.add(propertyPanel, BorderLayout.SOUTH);
		
		showFrame(null);
	}
	
	public void addLog(String logText) {
		log.info(logText);
		logScrollPane.addText(logText);
	}
}