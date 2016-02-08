package com.wikia.webdriver.common.driverprovider;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.BrowserType;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DriverProvider {

  private static List<WikiaWebDriver> drivers = new ArrayList<>();

  private DriverProvider() {
  }

  private static void newInstance() {
    drivers.add(BrowserType.lookup(Configuration.getBrowser()).getInstance());
  }

  public static WikiaWebDriver getBrowserDriver(int index) {
    for (; drivers.size() <= index; ) {
      newInstance();
    }

    return drivers.get(index);
  }

  public static WikiaWebDriver getBrowserDriver() {
    return getBrowserDriver(0);
  }

  public static void close() {
    for (WebDriver webDriver : drivers) {
      if (webDriver != null) {
        webDriver.quit();
      }
    }
    drivers.clear();
  }
}
