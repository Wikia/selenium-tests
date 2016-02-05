package com.wikia.webdriver.common.driverprovider;


import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.BrowserType;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class DriverProvider {

  private static EventFiringWebDriver driver;

  private DriverProvider() {}

  public static EventFiringWebDriver getDriverInstanceForBrowser() {
    driver = BrowserType.lookup(Configuration.getBrowser()).getInstance();

    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    return driver;
  }

  public static WebDriver getWebDriver() {
    return driver;
  }
}
