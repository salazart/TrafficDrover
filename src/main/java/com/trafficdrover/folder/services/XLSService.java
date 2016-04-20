package com.trafficdrover.folder.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Reading .xlsx and .xls document and getting class person and array with number and name
 */
public class XLSService extends InputService{
	private static final Logger log = LogManager.getRootLogger();
	
	private List<String> inputRow = new ArrayList<String>();
	
	public void readFile(String fileName) {
		
		try(FileInputStream file = new FileInputStream(fileName)) {

			HSSFWorkbook xlsWorkbook = new HSSFWorkbook(file);
			
			for(int i = 0; i < xlsWorkbook.getNumberOfSheets(); i++){
				readSheet(xlsWorkbook.getSheetAt(i));
			}
			
		} catch (IOException e) {
			log.error(e);
		} 
	}
	
	public void readSheet(Sheet sheet) {
		
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				inputRow.add(textOfCell(cell));
			}
			
			if(!inputRow.isEmpty()){
				parseInputRow(inputRow);
				inputRow.clear();
			}
		}
	}

	private static String textOfCell(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
				return date.toString();
			} else {
				long numeric = (long) cell.getNumericCellValue();
				return String.valueOf(numeric);
			}
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		default:
			return "";
		}
	}
}
