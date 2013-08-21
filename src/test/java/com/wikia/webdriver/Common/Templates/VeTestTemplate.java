package com.wikia.webdriver.Common.Templates;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.Core.Configuration.AbstractConfiguration;
import com.wikia.webdriver.Common.Core.Configuration.ConfigurationFactory;
import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxyServer;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DriverProvider.NewDriverProvider;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;

@Listeners({ com.wikia.webdriver.Common.Logging.PageObjectLogging.class })
public class VeTestTemplate {

	protected WebDriver driver;
	protected AbstractConfiguration config;
	protected String wikiURL;

	public VeTestTemplate() {
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

	}

	@AfterClass(alwaysRun = true)
	public void stop() {
		stopBrowser();
	}

	@BeforeMethod()
	public void setup() {
		driver.get("http://public.inez.wikia-dev.com/VisualEditor/demos/ve/");
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
