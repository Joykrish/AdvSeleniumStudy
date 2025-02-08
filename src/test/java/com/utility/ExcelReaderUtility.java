package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojos.Customer;


public class ExcelReaderUtility {
	public static Iterator<Customer> readExcelFile(String fileName) {
		
		String filepath=FilePathUtil.getExcelFilePath("testData", fileName);
		System.out.println("File Path: " + filepath);
		System.out.println("File Exists: " + new File(filepath).exists());

		//D:\DataDriven\NewFrameworkFromLinkedIn\automation-framework\testData\LoginData.xlsx
		File xlsxFile = new File(filepath);
		// To read XLSX File -> XSSFWorkbook -> it creates reference
		XSSFWorkbook xssfWorkbook = null;
		List<Customer> custList = null;
		Row row;
		Cell fName;
		Cell lName;
		Cell postName;
		Customer customer;
		Iterator<Row> rowIterator;
		XSSFSheet xssfSheet;
		try {
			xssfWorkbook = new XSSFWorkbook(xlsxFile);
			custList = new ArrayList<Customer>();

			// Get the sheet
			xssfSheet = xssfWorkbook.getSheet("LoginTestData");
			// Read the sheet Iterator used here
			rowIterator = xssfSheet.iterator();
			//skip first row
			if (rowIterator.hasNext()) {
			    rowIterator.next();  // Move to the second row
			}
			// read data from iterator
			while (rowIterator.hasNext()) {
			    row = rowIterator.next();
			    
			    // Skip if row is empty
			    if (row == null || row.getCell(0) == null) {
			        continue; 
			    }

			    fName = row.getCell(0);
			    lName = row.getCell(1);
			    postName = row.getCell(2);

			    // Prevent NullPointerException when calling toString()
			    customer = new Customer(
			        (fName != null) ? fName.toString() : "",
			        (lName != null) ? lName.toString() : "",
			        (postName != null) ? postName.toString() : ""
			    );

			    custList.add(customer);
			}

//			while (rowIterator.hasNext()) {
//				row = rowIterator.next();
//
//				fName = row.getCell(0);
//				lName = row.getCell(1);
//				postName=row.getCell(2);
//				customer = new Customer(fName.toString(), lName.toString(),postName.toString());
//				custList.add(customer);
//				xssfWorkbook.close();
//			}

		} catch (InvalidFormatException | IOException e) {

			e.printStackTrace();
		}
		return custList.iterator();
	}
}
