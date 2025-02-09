package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;
import com.constants.WaitStrategy;

public  class BrowserUtility {

	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		// super();
		this.driver.set(driver); // Initialize the instance variable driver
	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("Launching Browser for " + browserName);
		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless=new");  // or "--headless"
				chromeOptions.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(chromeOptions));

			} else {
				driver.set(new ChromeDriver());

			}
		} else if (browserName == Browser.EDGE) {
			if (isHeadless) {
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.addArguments("--headless=old"); // headless mode launch of browser
				edgeOptions.addArguments("--window-size=1920,1800");
				driver.set(new EdgeDriver(edgeOptions));
			} else {
				driver.set(new EdgeDriver());

			}
		} else if (browserName == Browser.FIREFOX) {
			if (isHeadless) {
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addArguments("--headless=old"); // headless mode launch of browser
				firefoxOptions.addArguments("--window-size=1920,1800");
				driver.set(new FirefoxDriver(firefoxOptions));
			} else {
				driver.set(new FirefoxDriver());
			}

		}
	}

	public void goToWebsite(String url) {
		logger.info("Visiting the website.." + url);
		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximizing the browser window");

		driver.get().manage().window().maximize();
	}

	public void clickOn(WaitStrategy waitStrategy, By locator) {
		logger.info("Finding element with the locator" + locator);
		ExplicitWaitFactory.performExplicitWait(waitStrategy, locator).click();

		logger.info("Element found and performing the click");

	}

	public void enterText(WaitStrategy waitStrategy, By locator, String textToEnter) {
		logger.info("Finding element with the locator" + locator);
		ExplicitWaitFactory.performExplicitWait(waitStrategy, locator).sendKeys(textToEnter);

		logger.info("Element found and now enter text" + textToEnter);

	}

	public String getVisibleText(WaitStrategy waitStrategy,By locator) {

		logger.info("Finding element with the locator" + locator);
		String availablText=ExplicitWaitFactory.performExplicitWait(waitStrategy, locator).getText();
		
		logger.info("Element found and returing the visible test" + availablText);

		return availablText;
	}

	public String takeScreenShot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();

		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);

		String path = System.getProperty("user.dir") + "//screenshots//" + name + "  -  " + timeStamp + ".png";

		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return path;

	}

	public String handleAlert(String action) {
		Alert alert = getDriver().switchTo().alert();
		if (action.equals("accept")) {
			alert.accept();
		} else if (action.equals("reject")) {
			alert.accept();
		} else if (action.equals("text")) {
			return alert.getText();
		}
		return null;
	}

}
