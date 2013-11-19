/**
 *
 */
package com.wikia.webdriver.Common.Templates;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class NewTestTemplate_TwoDrivers extends NewTestTemplate {

	@BeforeMethod(alwaysRun = true)
	public void startFF(Method method, Object[] data) {
		startBrowserFirefox();
		logOutFirefox();
	}

	@AfterMethod(alwaysRun = true)
	public void stopFF() {
		stopBrowserFirefox();
	}

	protected void switchToWindow(WebDriver maximized) {
		setWindowSize(10, 10, driver);
		setWindowSize(10, 10, driverFF);
		maximized.manage().window().maximize();

		String driverName = maximized.equals(driver) ? "primary window" : "secondary window";
		PageObjectLogging.log("switchToWindow", "================ " + driverName + " ================", true);
	}

}
