package com.wikia.webdriver.common.core.drivers;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.logging.Level;

public abstract class BrowserAbstract {

  protected static DesiredCapabilities caps = new DesiredCapabilities();


  public abstract EventFiringWebDriver getInstance();

  protected static void setBrowserLogging(Level logLevel) {
    LoggingPreferences loggingprefs = new LoggingPreferences();
    loggingprefs.enable(LogType.BROWSER, logLevel);
    caps.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);
  }

  public static void setDriverCapabilities(DesiredCapabilities newCaps) {
    caps = newCaps;
  }
}
