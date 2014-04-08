package com.wikia.webdriver.Common.DriverProvider.MobileProvider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Bogna 'bognix' Knychala
 */
public class MobileDriverProvider {

	private final String chromeDriverDefaultPort = "9515";
	private final String remoteDefaultURL = "http://localhost";

	private MobileDriversRegistry driversRegistry;

	public WebDriver getDriverInstance(String platform, String version) {
		WebDriver driver = null;

		switch (platform.toUpperCase()) {
			case "ANDROID":
				driver = getChromeDriver(version);
				break;
			case "IOS":
				//@TODO
				break;
			default:
				throw new WebDriverException("Bad platform provided");
		}
		return driver;
	}

	private WebDriver getChromeDriver(String version) {
		driversRegistry = new MobileDriversRegistry();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("androidPackage", "com.android.chrome");
		if (version != null) {
			chromeOptions.setExperimentalOption(
					"androidDeviceSerial", driversRegistry.getDeviceForAndroidVersion(version)
			);
		}
		DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
		desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		URL url;
		try {
			url = new URL(remoteDefaultURL + ":" + chromeDriverDefaultPort);
		} catch (MalformedURLException ex) {
			throw new RuntimeException(ex);
		}
		RemoteWebDriver remoteDriver = new RemoteWebDriver(url, desiredCapabilities);
		return remoteDriver;
	}
}
