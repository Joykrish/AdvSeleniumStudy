package com.utility;
import java.nio.file.Paths;
public class FilePathUtil {
	

	
	    public static String getExcelFilePath(String subFolder,String fileName) {
	        String rootDir = "D:\\DataDriven\\NewFrameworkFromLinkedIn\\automation-framework";
	        return Paths.get(rootDir, subFolder, fileName).toString();
	    }

	   
	}