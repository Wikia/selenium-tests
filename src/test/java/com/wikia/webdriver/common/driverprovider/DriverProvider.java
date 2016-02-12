package com.wikia.webdriver.common.driverprovider;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.BrowserType;

public class DriverProvider {

  private static final List<WikiaWebDriver> drivers = new ArrayList<>();
  private static int ACTIVE_BROWSER_INDEX = 0;

  private DriverProvider() {
  }

  private static void newInstance() {
    drivers.add(BrowserType.lookup(Configuration.getBrowser()).getInstance());
  }

  private static WikiaWebDriver getBrowserDriver(int index) {
    for (; drivers.size() <= index; ) {
      newInstance();
    }

    return drivers.get(index);
  }

  private static WikiaWebDriver getBrowserDriver() {
    return getBrowserDriver(0);
  }

  public static WikiaWebDriver getActiveDriver(){
    return getBrowserDriver(ACTIVE_BROWSER_INDEX);
  }

  public static WikiaWebDriver switchActiveWindow(int index){
    ACTIVE_BROWSER_INDEX = index;
    return getActiveDriver();
  }

  public static void close() {
    try {
      for (WikiaWebDriver webDriver : drivers) {
        if (webDriver.getProxy() != null){
          webDriver.getProxy().stop();
        }
        if (webDriver != null) {
          webDriver.quit();
        }
      }
    }catch (Error e){

    }
    drivers.clear();
    ACTIVE_BROWSER_INDEX=0;
  }
}
