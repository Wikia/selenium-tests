package com.wikia.webdriver.common.core;

import com.wikia.webdriver.common.core.configuration.ConfigurationFactory;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class AlertHandler {

  public static boolean isAlertPresent(WebDriver driver) {
    try {
      if ("GHOST".equals(ConfigurationFactory.getConfig().getBrowser())){
        return false;
      }
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
