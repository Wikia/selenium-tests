package com.wikia.webdriver.Common.Templates;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.Core.Configuration;
import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxyServer;
import com.wikia.webdriver.Common.DriverProvider.NewDriverProvider;
import com.wikia.webdriver.Common.Properties.Properties;


public class NewTestTemplate {

	protected WebDriver driver;
	protected String testedWiki;
	protected HashMap<String, Object> config;

	public NewTestTemplate() {
		config = Configuration.getConfiguration();
	}

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
	public void start(Method method, Object[] data) {
		startBrowser();
	}

	@AfterMethod(alwaysRun = true)
	public void stop() {
		stopBrowser();
	}

	protected void startBrowser() {
		driver = NewDriverProvider.getDriverIntstanceForConfig(config);
	}

	protected void startBrowserWithCapabilities(DesiredCapabilities caps) {
        NewDriverProvider.setDriverCapabilities(caps);
        startBrowser();
    }

	protected void stopBrowser() {
		driver = NewDriverProvider.getWebDriver();
		if (driver != null) {
			driver.quit();
			driver = null;
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
