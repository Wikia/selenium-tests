package com.wikia.webdriver.Common.DriverProvider.MobileProvider;

import com.wikia.webdriver.Common.Core.Configuration.AbstractConfiguration;
import com.wikia.webdriver.Common.DriverProvider.NewDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Bogna 'bognix' Knychala
 */
public class MobileDriverProvider {

	private MobileDriversRegistry mobileDriversRegistry;
	private String platform;
	private String platformVersion;
	private String deviceId;
	private String mobileConfig;
	private String browser;

	public MobileDriverProvider(AbstractConfiguration config) {
		platform = config.getPlatform();
		platformVersion = config.getPlatformVersion();
		deviceId = config.getDeviceId();
		mobileConfig = config.geMobileConfig();
		browser = config.getBrowser();
	}

	public WebDriver getDriverInstance() {
		WebDriver driver = null;

		// If browser equals to CHROMEMOBILE
		// return CHROMEMOBILE instance from NewDriverProvider
		if (browser.equalsIgnoreCase("CHROMEMOBILE")) {
			return NewDriverProvider.getDriverInstanceForBrowser("CHROMEMOBILE");
		}

		switch (platform.toUpperCase()) {
			case "ANDROID":
				mobileDriversRegistry = new MobileDriversRegistry(platform, mobileConfig);
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
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("androidPackage", "com.android.chrome");
		if (deviceId != null) {
			chromeOptions.setExperimentalOption(
				"androidDeviceSerial", deviceId
			);
		} else if (platformVersion != null) {
			chromeOptions.setExperimentalOption(
					"androidDeviceSerial",
					mobileDriversRegistry.getDeviceForAndroidVersion(platformVersion)
			);
		}
		return new ChromeDriver(chromeOptions);
	}
}
