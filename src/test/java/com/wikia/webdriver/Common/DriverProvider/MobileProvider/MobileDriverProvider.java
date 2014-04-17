package com.wikia.webdriver.Common.DriverProvider.MobileProvider;

import com.wikia.webdriver.Common.Core.Configuration.AbstractConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Bogna 'bognix' Knychala
 */
public class MobileDriverProvider {

	private MobileDriversRegistry driversRegistry;
	private String platform;
	private String platformVersion;
	private String deviceId;

	public MobileDriverProvider(AbstractConfiguration config) {
		platform = config.getPlatform();
		platformVersion = config.getPlatformVersion();
		deviceId = config.getDeviceId();
	}

	public WebDriver getDriverInstance() {
		WebDriver driver = null;

		switch (platform.toUpperCase()) {
			case "ANDROID":
				driver = getChromeDriver(platformVersion, deviceId);
				break;
			case "IOS":
				//@TODO
				break;
			default:
				throw new WebDriverException(
					"Unknown platform provided \n" +
					"Available platforms:" +
					"\n\t android" +
					"\n\t ios"
				);
		}
		return driver;
	}

	private WebDriver getChromeDriver(String platformVersion, String deviceId) {
		driversRegistry = new MobileDriversRegistry();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("androidPackage", "com.android.chrome");
		if (deviceId != null) {
			chromeOptions.setExperimentalOption(
				"androidDeviceSerial", deviceId
			);
		} else if (platformVersion != null) {
			chromeOptions.setExperimentalOption(
				"androidDeviceSerial",
				driversRegistry.getDeviceForAndroidVersion(platformVersion)
			);
		}
		return new ChromeDriver(chromeOptions);
	}
}
