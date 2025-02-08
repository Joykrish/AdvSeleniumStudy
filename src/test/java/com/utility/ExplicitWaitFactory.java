package com.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.constants.FrameworkConstants;
import com.constants.WaitStrategy;


public class ExplicitWaitFactory {
	 
	

	public static WebElement performExplicitWait(WaitStrategy waitStrategy, By by) {
		WebElement element=null;
		if (waitStrategy == WaitStrategy.CLICKABLE) {
			element=new WebDriverWait(BrowserUtility.driver.get(), FrameworkConstants.getExplicitwait())
					.until(ExpectedConditions.elementToBeClickable(by));

		} else if (waitStrategy == WaitStrategy.PRESENCE) {
			element=new WebDriverWait(BrowserUtility.driver.get(), FrameworkConstants.getExplicitwait())
					.until(ExpectedConditions.presenceOfElementLocated(by));

		} else if (waitStrategy == WaitStrategy.VISIBLE) {
			element=new WebDriverWait(BrowserUtility.driver.get(), FrameworkConstants.getExplicitwait())
					.until(ExpectedConditions.visibilityOfElementLocated(by));
		} else if (waitStrategy == WaitStrategy.NONE) {
			element=BrowserUtility.driver.get().findElement(by);

		}
		return element;

	}

}
