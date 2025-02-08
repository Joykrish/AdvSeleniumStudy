package com.ui.tests;

import static org.testng.Assert.*;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.constants.FrameworkConstants;
import com.ui.pages.CustomerPage;
import com.ui.pojos.Customer;
import com.ui.pojos.User;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;

@Listeners(com.ui.listerners.TestListener.class)
public class LoginTest extends TestBase {

	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	@Test(description = "To Verify valid user is able to enter the customer data successfully Data driven terminology", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataprovider.LoginDataProvider.class, dataProvider = "customerTestExcelDatProvider", enabled = false)
	public void loginTest(Customer customer) throws InterruptedException {
		homePage.goToBankManager().enterCustomerInfo(customer.getfName(), customer.getlName(),
				customer.getPostNumber());
		Thread.sleep(2000);
		 
  
	}

	@Test(description = "To Verify valid user is able to enter the customer data successfully from Json", groups = { "e2e",
			"sanity" }, enabled = true)
	public void verifyAddCustomer() throws InterruptedException {

		
		homePage.goToBankManager().enterCustomerInfo(JSONUtility.getAttribute(FrameworkConstants.getTestdatajson(), "custlist", 1, "fName"), 
				JSONUtility.getAttribute(FrameworkConstants.getTestdatajson(), "custlist", 1, "lName"),
				JSONUtility.getAttribute(FrameworkConstants.getTestdatajson(), "custlist", 1, "postNumber"));
		
		Assert.assertTrue(customerPage.verifySuccessMessage().matches("Customer added successfully with customer id :\\d+"), "Customer is not added successfully");
		
		customerPage.acceptSuccessMessage();
	}

}
