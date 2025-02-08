package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.WaitStrategy;
import com.utility.BrowserUtility;

public class CustomerPage extends BrowserUtility {

	private static final By input_FirstName = By.xpath("//input[@placeholder='First Name']");
	private static final By input_LastName = By.xpath("//input[@placeholder='Last Name']");
	private static final By input_PostCode = By.xpath("//input[@placeholder='Post Code']");
	private static final By btntAddCustomer = By.xpath("//button[contains(text(), 'Add Customer')]");
	private static final By btntSaveAddCustomer = By.xpath("//button[text()='Add Customer']");

	public CustomerPage(WebDriver driver) {
		super(driver);
	}

	public void enterCustomerInfo(String FName, String lName, String pstCode) throws InterruptedException {

		clickOn(WaitStrategy.CLICKABLE, btntAddCustomer);

		enterText(WaitStrategy.PRESENCE, input_FirstName, FName);
		enterText(WaitStrategy.PRESENCE, input_LastName, lName);
		enterText(WaitStrategy.PRESENCE, input_PostCode, pstCode);
		clickOn(WaitStrategy.CLICKABLE, btntSaveAddCustomer);

	}

	public String verifySuccessMessage() {
		return handleAlert("text");
	}

	public CustomerListPage acceptSuccessMessage() {
		handleAlert("accept");
		CustomerListPage clp = new CustomerListPage(getDriver());
		return clp;

	}
}
