package com.trafficdrover.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class created for work with file config.properties
 * @author salazart
 *
 */
public class PropService {
	private final static String PATH_PROPERTIES = "config/config.properties";
	
	private static final Logger log = LogManager.getRootLogger();
	
	public static void setValue(String typeProperties, String valueProperties) {
		Properties properties = readFromFile();
		properties.setProperty(typeProperties, valueProperties);
		log.debug("Property " + typeProperties + " chanded to: " + valueProperties);
		writeToFile(properties);
	}

	private static void writeToFile(Properties properties) {
		createDirectory();
			
		try {
			properties.store(new FileOutputStream(PATH_PROPERTIES), null);
			log.debug("File saved successfully: " + PATH_PROPERTIES);
		} catch (IOException e) {
			log.error(e);
		} 
	}

	private static void createDirectory() {
		File parentFolder = new File(new File(PATH_PROPERTIES).getParent());
		if(!parentFolder.isDirectory()){
			if(parentFolder.mkdirs()){
				log.debug("Folder created successfully: " + parentFolder);
			};
		}
	}

	public static Properties readFromFile() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(PATH_PROPERTIES));
		} catch (Exception e) {
			log.error(e);
		}
		return properties;
	}

	public static String getValue(String typeProperties) {
		String valueProperties = readFromFile().getProperty(typeProperties);
		if (valueProperties == null) {
			setValue(typeProperties, "");
			return "";
		} else {
			return valueProperties;
		}
	}
}
