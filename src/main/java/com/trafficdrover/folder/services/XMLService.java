package com.trafficdrover.folder.services;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import com.trafficdrover.TrafficDrover;

public class XMLService extends InputService{
	private static final String ERROR_MESSAGE_1 = "(Unicode: 0x11)";
	private static final String ERROR_MESSAGE_2 = "Character reference \"&#";
	
	private static final int code1 = 17;
	private static final int code2 = 38;
	
	private final String ROW = "Row";
	private final String CELL = "Cell";
	
	private List<String> inputRow = new ArrayList<String>();
	
	private String tempValue = "";
	
	public void readFile(String fileName){
		byte[] data = getDataByteOfFile(fileName);
		
		int charCode = 0;
		try {
			startReadFile2(data);
		} catch (IOException | XMLStreamException e) {
			TrafficDrover.instance.addLog("Невірний формат данних. Намагаюсь виправити...");
			log.error(e);
			charCode = e.getMessage().contains(ERROR_MESSAGE_2) ? code2 : code1;
		}
		
		if(charCode != 0){
			filterData(data, charCode);
			try {
				startReadFile2(data);
			} catch (IOException | XMLStreamException e1) {
				TrafficDrover.instance.addLog("Не вдалось опрацювати повністю файл: " + fileName);
				log.error(e1);
			}
		}
		data = null;
	}

	private byte[] getDataByteOfFile(String fileName) {
		byte[] data = null;
		Path path = Paths.get(fileName);
		try {
			data = Files.readAllBytes(path);
		} catch (IOException e2) {
			log.error(e2);
		}
		return data;
	}
	
	private void startReadFile2(byte[] data) throws FileNotFoundException, XMLStreamException, UnsupportedEncodingException{
		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		XMLStreamReader xmlr = xmlif.createXMLStreamReader(new ByteArrayInputStream(data), "UTF-8");
		while(xmlr.hasNext()) {
			switch (xmlr.next()) {
	        case XMLEvent.START_ELEMENT:
				if(CELL.equals(xmlr.getLocalName())){
					tempValue = "";
				} else if (ROW.equals(xmlr.getLocalName())){
	        		inputRow.clear();
	        	}
	        	break;

	        case XMLEvent.END_ELEMENT:
				if (CELL.equals(xmlr.getLocalName())) {
					inputRow.add(tempValue);
				} else if (ROW.equals(xmlr.getLocalName())) {
					parseInputRow(inputRow);
				}
	        	break;
	        	
	        case XMLEvent.CHARACTERS:
	        	tempValue += xmlr.getText();
	        	break;
			}
		}
	}

	private void filterData(byte[] data, int charCode){
		for (int i = 0; i < data.length; i++) {
			if(data[i] == charCode){
				data[i] = (byte) 0x75;
			}
		}
	}
}
