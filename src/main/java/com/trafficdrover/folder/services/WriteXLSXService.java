package com.trafficdrover.folder.services;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.trafficdrover.TrafficDrover;
import com.trafficdrover.db.local.models.Traffic;
/**
 * This class write the ready traffic into file of format XLS with name fileName
 */
public class WriteXLSXService {
	private static final Logger log = LogManager.getRootLogger();
	
	private Workbook wb;
	private SXSSFSheet sheet;
	
	public void createWorkBook(){
		wb = new SXSSFWorkbook();
		createSheet();
	}

	private void createSheet() {
		sheet = (SXSSFSheet) wb.createSheet("New sheet");
		sheet.setRandomAccessWindowSize(100);
		
		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 2800);
		sheet.setColumnWidth(2, 2400);
		sheet.setColumnWidth(3, 2200);
		sheet.setColumnWidth(4, 3500);
		sheet.setColumnWidth(5, 4200);
		sheet.setColumnWidth(6, 3500);
		sheet.setColumnWidth(7, 1800);
		sheet.setColumnWidth(8, 1800);
		sheet.setColumnWidth(9, 1800);
		sheet.setColumnWidth(10, 10000);
	}
	
	public void addRow(Traffic traffic){
		Row row = sheet.createRow(sheet.getLastRowNum() + 1);
		
		String[] elements = traffic.toStringArray();
		
		for (int j = 0; j < elements.length; j++) {
			row.createCell(j).setCellValue(elements[j]);
		}
	}
	
	public void saveWorkBook(String fileName){
		try (FileOutputStream out = new FileOutputStream(fileName)){
			wb.write(out);
			TrafficDrover.instance.addLog("Файл " + fileName + " успішно збережений");
			log.info("File " + fileName + " saved succusffully");
		} catch (IOException e) {
			log.error(e);
		}
	}
}
