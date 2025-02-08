package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.constants.Env;
import com.constants.FrameworkConstants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ui.pojos.Config;
import com.ui.pojos.Environment;

public class JSONUtility {
	
	public static String readJSON(Env env)  {
		
		Gson gson = new Gson();
		File jsonFile = new File(System.getProperty("user.dir") + "/config/config.json");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(jsonFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Config config = gson.fromJson(fileReader, Config.class);
		Environment environment = config.getEnvironments().get("QA");
		//System.out.println(environment.getUrl());
		return environment.getUrl();
	}
	
	 public static Map<String, Object> convertJsonFileToMap(String filePath) {
	        ObjectMapper objectMapper = new ObjectMapper();
	        try {
	            return objectMapper.readValue(new File(filePath), new TypeReference<Map<String, Object>>() {});
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	 
	 
	 public static String getAttribute(String filePath,String value,int index,String actualValue) {
		 Map<String, Object> MapOne = JSONUtility.convertJsonFileToMap(filePath);
					
		 List<Map<String, String>> ListOne = (List<Map<String, String>>) MapOne.get(value);
			return ListOne.get(index).get(actualValue);

	 }

}
