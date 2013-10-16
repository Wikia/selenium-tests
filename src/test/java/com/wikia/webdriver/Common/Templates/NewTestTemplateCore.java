package com.wikia.webdriver.Common.Templates;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.Core.Configuration.AbstractConfiguration;
import com.wikia.webdriver.Common.Core.Configuration.ConfigurationFactory;
import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxyServer;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DriverProvider.NewDriverProvider;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
@Listeners({ com.wikia.webdriver.Common.Logging.PageObjectLogging.class })
public class NewTestTemplateCore {

	protected WebDriver driver;
	protected AbstractConfiguration config;
	protected String wikiURL;
	protected String wikiCorporateURL;

	public NewTestTemplateCore() {
		config = ConfigurationFactory.getConfig();
	}

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		prepareDirectories();
	}

	protected void prepareDirectories() {
		Properties.setProperties();
		CommonUtils.deleteDirectory("." + File.separator + "logs");
		CommonUtils.createDirectory("." + File.separator + "logs");
	}

	protected void prepareURLs() {
		UrlBuilder urlBuilder = new UrlBuilder(config.getEnv());
		wikiURL = urlBuilder.getUrlForWiki(config.getWikiName());
		wikiCorporateURL = urlBuilder.getUrlForWiki("wikia");
	}

	protected void startBrowser() {
		EventFiringWebDriver eventDriver = NewDriverProvider.getDriverInstanceForBrowser (
			config.getBrowser()
		);
		eventDriver.register(new PageObjectLogging());
		driver = eventDriver;
		driver.get(wikiURL + URLsContent.logout);
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
