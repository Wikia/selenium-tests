package com.webdriver.common.driverprovider;

import java.util.ArrayList;
import java.util.List;

import com.webdriver.common.core.PageWebDriver;
import com.webdriver.common.core.configuration.Configuration;
import com.webdriver.common.core.drivers.Browser;
import com.webdriver.common.logging.PageObjectLogging;

public class DriverProvider {

  private static final List<PageWebDriver> drivers = new ArrayList<>();
  private static int ACTIVE_BROWSER_INDEX = 0;

  private DriverProvider() {}

  private static void newInstance() {
    drivers.add(Browser.lookup(Configuration.getBrowser()).getInstance());
  }

  private static PageWebDriver getBrowserDriver(int index) {
    for (; drivers.size() <= index;) {
      newInstance();
    }

    return drivers.get(index);
  }

  public static PageWebDriver getActiveDriver() {
    return getBrowserDriver(ACTIVE_BROWSER_INDEX);
  }

  public static PageWebDriver switchActiveWindow(int index) {
    ACTIVE_BROWSER_INDEX = index;
    return getActiveDriver();
  }

  public static void close() {
    for (PageWebDriver webDriver : drivers) {
      if (webDriver != null) {
        try {
          String path = System.getenv("PATH");
          System.out.println(path);
          webDriver.quit();
        }catch (UnsatisfiedLinkError | NoClassDefFoundError | NullPointerException e){

          PageObjectLogging.log("Closing Browser", e, true);
        }
      }
    }
    drivers.clear();
    ACTIVE_BROWSER_INDEX = 0;
  }
}
