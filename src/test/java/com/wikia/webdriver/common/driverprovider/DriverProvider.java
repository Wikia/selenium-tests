package com.wikia.webdriver.common.driverprovider;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.BrowserType;

public class DriverProvider {

  private static final List<WikiaWebDriver> drivers = new ArrayList<>();

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
    try {
      for (WebDriver webDriver : drivers) {
        if (webDriver != null) {
          webDriver.quit();
        }
      }
    }catch (Error e){

    }
    drivers.clear();
  }
}
