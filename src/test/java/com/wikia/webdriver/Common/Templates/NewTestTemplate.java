package com.wikia.webdriver.Common.Templates;

import java.io.File;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.Core.Configuration.AbstractConfiguration;
import com.wikia.webdriver.Common.Core.Configuration.ConfigurationFactory;
import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxyServer;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DriverProvider.NewDriverProvider;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;


public class NewTestTemplate {

	protected WebDriver driver;
	protected AbstractConfiguration config;

	protected String wikiURL;

	public NewTestTemplate() {
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

	@BeforeMethod(alwaysRun = true)
	public void start(Method method, Object[] data) {
		startBrowser();
		UrlBuilder urlBuilder = new UrlBuilder(config.getEnv());
		wikiURL = urlBuilder.getUrlForWiki(config.getWikiName());
		driver.get(wikiURL);
	}

	@AfterMethod(alwaysRun = true)
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

	protected DesiredCapabilities getCapsWithProxyServerSet(GeoEdgeProxyServer server) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		try {
			capabilities.setCapability(
				CapabilityType.PROXY, server.seleniumProxy()
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return capabilities;
	}
}
