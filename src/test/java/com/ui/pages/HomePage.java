package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import com.constants.Env;
import com.constants.WaitStrategy;

import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;

import static com.utility.PropertiesUtil.*;


public final class HomePage extends BrowserUtility{

	Logger logger = LoggerUtility.getLogger(this.getClass());

	private static final By SIGN_IN_Bank_Manager = By.xpath("//button[text()='Bank Manager Login']");
	
	public HomePage(Browser  browserName, boolean isHeadless) {
		super(browserName, isHeadless); // to call the parent class constructor from the child class constructor
		 		goToWebsite(JSONUtility.readJSON(QA));
	}

	public HomePage(WebDriver driver) {
		super(driver); 
		goToWebsite(JSONUtility.readJSON(Env.UAT));
	}
	

	
	public CustomerPage goToBankManager() throws InterruptedException { // page functions
		
		logger.info("Trying to perform click to Bank Manager Login Button");
		
		clickOn(WaitStrategy.CLICKABLE,SIGN_IN_Bank_Manager);
		CustomerPage customerPagePage = new CustomerPage(getDriver());
		return customerPagePage;
	}



	public void quit() {
		// TODO Auto-generated method stub
		
	}
	
	

}
