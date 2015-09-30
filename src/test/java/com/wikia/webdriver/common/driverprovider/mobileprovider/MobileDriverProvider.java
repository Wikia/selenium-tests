package com.wikia.webdriver.common.driverprovider.mobileprovider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.driverprovider.NewDriverProvider;

public class MobileDriverProvider {

  private String platform;
  private String browser;

  public MobileDriverProvider() {
    platform = Configuration.getPlatform();
    browser = Configuration.getBrowser();
  }

  public WebDriver getDriverInstance() {
    WebDriver driver = null;

    if ("CHROMEMOBILEMERCURY".equals(browser)) {
      return NewDriverProvider.getDriverInstanceForBrowser(browser);
    }

    switch (platform.toUpperCase()) {
      case "ANDROID":
        driver = getChromeDriver();
        break;
      case "IOS":
        // @TODO
        break;
      default:
        throw new WebDriverException("Unknown platform provided \n" + "Available platforms:"
            + "\n\t android" + "\n\t ios");
    }
    return driver;
  }

  private WebDriver getChromeDriver() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setExperimentalOption("androidPackage", "com.android.chrome");
    return new ChromeDriver(chromeOptions);
  }
}
