package com.trafficdrover.folder.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class XLSXStreamService extends InputService{
	private List<String> inputRow = new ArrayList<String>();
	
	public void readFile(String fileName) {
		log.debug( "Start servise for reading stream xlsx file: " + fileName);
		try (OPCPackage pkg = OPCPackage.open(fileName)) {
			XSSFReader r = new XSSFReader(pkg);
			
			XMLReader parser = XMLReaderFactory
					.createXMLReader("org.apache.xerces.parsers.SAXParser");
			ContentHandler handler = new SheetHandler(r.getSharedStringsTable());
			parser.setContentHandler(handler);
			
			Iterator<InputStream> sheets = r.getSheetsData();
			while (sheets.hasNext()) {
				InputStream sheet = sheets.next();
				parser.parse(new InputSource(sheet));
				sheet.close();
			}
		} catch (Exception e) {
			log.info(e);
		} 
		log.debug("Finish servise for reading stream xlsx file: " + fileName);
	}

	private class SheetHandler extends DefaultHandler {
		private SharedStringsTable sst;
		private String lastContents;
		private boolean nextIsString;

		private SheetHandler(SharedStringsTable sst) {
			this.sst = sst;
		}

		public void startElement(String uri, String localName, String name,
				Attributes attributes) throws SAXException {
			if (name.equals("c")) {
				String cellType = attributes.getValue("t");
				if (cellType != null && cellType.equals("s")) {
					nextIsString = true;
				} else {
					nextIsString = false;
				}
			}
			lastContents = "";
		}

		public void endElement(String uri, String localName, String name)
				throws SAXException {
			if (nextIsString) {
				int idx = Integer.parseInt(lastContents);
				lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
				nextIsString = false;
			}
			if (name.equals("v")) {
				inputRow.add(lastContents);
			}
			
			if("row".equals(localName)){
				parseInputRow(inputRow);
				inputRow.clear();
			}
		}

		public void characters(char[] ch, int start, int length)
				throws SAXException {
			lastContents += new String(ch, start, length);
		}
	}
}
