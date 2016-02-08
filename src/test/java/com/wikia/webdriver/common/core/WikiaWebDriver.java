package com.wikia.webdriver.common.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class WikiaWebDriver extends EventFiringWebDriver {

  public WikiaWebDriver(WebDriver driver) {
    super(driver);
  }

  public boolean isChrome(){
    return true;
  }

  public boolean isFirefox(){
    return false;
  }
}
