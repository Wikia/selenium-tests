package com.wikia.webdriver.Common.Templates;

import java.io.File;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DriverProvider.DriverProvider;
import com.wikia.webdriver.Common.Properties.Properties;

@Listeners({ com.wikia.webdriver.Common.Logging.PageObjectLogging.class })
public class TestTemplate {

	public WebDriver driver;
	protected String improvedPageUrl;
	protected UrlBuilder urlBuilder;
	protected String page;



	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		CommonUtils.deleteDirectory("." + File.separator + "logs");
		CommonUtils.createDirectory("." + File.separator + "logs");
		Properties.setProperties();
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
	}

	@BeforeMethod(alwaysRun = true)
	public void start(Method method) {
		startBrowser();
	}

	@AfterMethod(alwaysRun = true)
	public void stop() {
		stopBrowser();
	}

	protected void startBrowser() {
		DriverProvider.getInstance();
		driver = DriverProvider.getWebDriver();
	}

	protected void stopBrowser() {
		driver = DriverProvider.getWebDriver();
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
