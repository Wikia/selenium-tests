package com.wikia.webdriver.common.driverprovider.mobileprovider;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.driverprovider.NewDriverProvider;

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

  public MobileDriverProvider() {
    platform = Configuration.getPlatform();
    platformVersion = Configuration.getPlatformVersion();
    deviceId = Configuration.getDeviceId();
    mobileConfig = Configuration.geMobileConfig();
    browser = Configuration.getBrowser();
  }

  public WebDriver getDriverInstance() {
    WebDriver driver = null;

    if ("CHROMEMOBILEMERCURY".equals(browser)) {
      return NewDriverProvider.getDriverInstanceForBrowser(browser);
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
