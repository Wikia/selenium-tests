package com.wikia.webdriver.Common.Templates;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.Core.Configuration.AbstractConfiguration;
import com.wikia.webdriver.Common.Core.Configuration.ConfigurationFactory;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DriverProvider.NewDriverProvider;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;

@Listeners({ com.wikia.webdriver.Common.Logging.PageObjectLogging.class })
public class VisualEditorTestTemplate {

	protected WebDriver driver;
	protected AbstractConfiguration config;
	protected String wikiURL;

	public VisualEditorTestTemplate() {
		config = ConfigurationFactory.getConfig();
	}

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		//Needed because a lot of tests uses GLOBAL object
		//Not calling causes NUllPointerException
		Properties.setProperties();
		CommonUtils.deleteDirectory("." + File.separator + "logs");
		CommonUtils.createDirectory("." + File.separator + "logs");
	}

	@BeforeClass(alwaysRun = true)
	public void start() {
		startBrowser();
		UrlBuilder urlBuilder = new UrlBuilder(config.getEnv());
		wikiURL = urlBuilder.getUrlForWiki(config.getWikiName());
		driver.get(wikiURL + URLsContent.logout);
	}

	@AfterClass(alwaysRun = true)
	public void stop() {
		stopBrowser();
	}

	protected void startBrowser() {
		EventFiringWebDriver eventDriver = NewDriverProvider.getDriverInstanceForBrowser(
			config.getBrowser()
		);
		eventDriver.register(new PageObjectLogging());
		driver = eventDriver;
	}

	protected void stopBrowser() {
		driver = NewDriverProvider.getWebDriver();
		if (driver != null) {
			driver.quit();
		}
	}
}
