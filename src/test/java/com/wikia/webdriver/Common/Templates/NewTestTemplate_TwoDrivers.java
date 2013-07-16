package com.wikia.webdriver.Common.Templates;

import java.io.File;
import java.lang.reflect.Method;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.Core.Configuration.AbstractConfiguration;
import com.wikia.webdriver.Common.Core.Configuration.ConfigurationFactory;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DriverProvider.NewDriverProvider;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;

public class NewTestTemplate_TwoDrivers {

	public NewTestTemplate_TwoDrivers() {
		config = ConfigurationFactory.getConfig();
	}

	protected WebDriver driver;
	protected WebDriver driver2;
	protected AbstractConfiguration config;

	protected String wikiURL;

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		//Needed because a lot of tests uses GLOBAL object
		//Not calling causes NUllPointerException
		Properties.setProperties();
		CommonUtils.deleteDirectory("." + File.separator + "logs");
		CommonUtils.createDirectory("." + File.separator + "logs");
	}

	@BeforeMethod(alwaysRun = true)
	public void start(Method method, Object[] data) {
		startBrowser();
		UrlBuilder urlBuilder = new UrlBuilder(config.getEnv());
		wikiURL = urlBuilder.getUrlForWiki(config.getWikiName());
		driver.get(wikiURL);
		driver2.get(wikiURL);
	}

	@AfterMethod(alwaysRun = true)
	public void stop() {
		stopBrowser();
	}

	protected void startBrowser() {
		PageObjectLogging listener = new PageObjectLogging();
		EventFiringWebDriver eventDriver = NewDriverProvider.getDriverInstanceForBrowser(
			config.getBrowser()
		);
		EventFiringWebDriver eventDriver2 = NewDriverProvider.getDriverInstanceFirefox();
		eventDriver.register(listener);
		eventDriver2.register(listener);
		driver = eventDriver;
		driver2 = eventDriver2;
	}

	protected void stopBrowser() {
		driver = NewDriverProvider.getWebDriver();
		driver2 = NewDriverProvider.getWebDriverFirefox();
		if (driver != null) {
			driver.quit();
		}
		if (driver2 != null) {
			driver2.quit();
		}
	}
	protected void switchToWindow(WebDriver maximized)
	{
		Dimension min = new Dimension(10,10);
		driver.manage().window().setSize(min);
		driver2.manage().window().setSize(min);
		maximized.manage().window().maximize();
	}

}
