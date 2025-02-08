package com.ui.tests;

import java.net.MalformedURLException;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.CustomerPage;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;

import com.utility.LoggerUtility;

public class TestBase {

	protected HomePage homePage;
	
	public CustomerPage customerPage; 
	
	Logger logger = LoggerUtility.getLogger(this.getClass());



	
	@Parameters({"browser", "isHeadless"})
	@BeforeMethod(description = "Load the Homepage of the Application")
	public void setUp(
			@Optional("chrome")String browser,
			@Optional("true")boolean isHeadless, ITestResult result) throws MalformedURLException {
		
				logger.info("Load the Homepage of the Application");
			homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
			customerPage=new CustomerPage(homePage.getDriver());
			
		}



	public BrowserUtility getInstance() {
		return homePage;
	}

	@AfterMethod(description = "Tear Down the Browser")
	public void tearDown() {
	
			homePage.quit(); // close the local machine session
			homePage.getDriver().quit(); 
		}

	}

