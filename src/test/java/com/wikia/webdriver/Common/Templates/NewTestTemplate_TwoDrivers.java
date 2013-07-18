package com.wikia.webdriver.Common.Templates;

import java.lang.reflect.Method;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeMethod;

import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DriverProvider.NewDriverProvider;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class NewTestTemplate_TwoDrivers extends NewTestTemplate {

	protected WebDriver driver2;

	@Override
	@BeforeMethod(alwaysRun = true)
	public void start(Method method, Object[] data) {
		startBrowser();
		UrlBuilder urlBuilder = new UrlBuilder(config.getEnv());
		wikiURL = urlBuilder.getUrlForWiki(config.getWikiName());
		driver.get(wikiURL);
		driver2.get(wikiURL);
	}

	@Override
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

	@Override
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
