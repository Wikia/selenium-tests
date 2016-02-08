package com.wikia.webdriver.common.core.drivers;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public abstract class BrowserAbstract {

  protected static DesiredCapabilities caps = new DesiredCapabilities();

  public WikiaWebDriver getInstance(){
    WikiaWebDriver webdriver = setInstance();
    setTimeputs(webdriver);
    setListeners(webdriver);

    return webdriver;
  }

  public abstract WikiaWebDriver setInstance();

  protected void setBrowserLogging(Level logLevel) {
    LoggingPreferences loggingprefs = new LoggingPreferences();
    loggingprefs.enable(LogType.BROWSER, logLevel);
    caps.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);
  }

  protected void setTimeputs(WebDriver webDriver){
    webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  }

  protected void setListeners(WikiaWebDriver webDriver){
    webDriver.register(new PageObjectLogging());
  }

  public static void setDriverCapabilities(DesiredCapabilities newCaps) {
    caps = newCaps;
  }
}
