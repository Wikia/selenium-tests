package com.wikia.webdriver.common.driverprovider.mobileprovider;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.driverprovider.DriverProvider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MobileDriverProvider {

  private String platform;
  private String browser;

  public MobileDriverProvider() {
    platform = Configuration.getPlatform();
    browser = Configuration.getBrowser();
  }

  public WebDriver getDriverInstance() {
    WebDriver driver = null;

    if (Browser.CHROME_MOBILE_MERCURY.equals(browser)) {
      return DriverProvider.getDriverInstanceForBrowser();
    }

    switch (platform.toUpperCase()) {
      case Browser.CHROME_ANDROID:
        driver = getChromeDriver();
        break;
      default:
        throw new WebDriverException(
            "Unknown platform provided \n" +
            "Available platforms:" +
            "\n\t android"
        );
    }
    return driver;
  }

  private WebDriver getChromeDriver() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setExperimentalOption("androidPackage", "com.android.chrome");
    return new ChromeDriver(chromeOptions);
  }
}
