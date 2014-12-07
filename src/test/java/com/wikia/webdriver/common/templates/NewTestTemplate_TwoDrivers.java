package com.wikia.webdriver.common.templates;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;


public class NewTestTemplate_TwoDrivers extends NewTestTemplate {

	protected WebDriver driverOne;
	protected WebDriver driverTwo;

	@Override
	@BeforeMethod(alwaysRun = true)
	public void start(Method method, Object[] data) {
		driverOne = startCustomBrowser("FF");
		logOutCustomDriver(driverOne);
		driverTwo = startCustomBrowser("FF");
		logOutCustomDriver(driverTwo);
		this.driver = driverOne;
	}

	@Override
	@AfterMethod(alwaysRun = true)
	public void stop() {
		stopCustomBrowser(driverOne);
		stopCustomBrowser(driverTwo);
	}

	protected void switchToWindow(WebDriver maximized) {
		setWindowSize(10, 10, driverOne);
		setWindowSize(10, 10, driverTwo);
		maximized.manage().window().maximize();

		String driverName = maximized.equals(driverOne) ? "primary window" : "secondary window";
		PageObjectLogging.log("switchToWindow", "================ " + driverName + " ================", true);
	}

}
