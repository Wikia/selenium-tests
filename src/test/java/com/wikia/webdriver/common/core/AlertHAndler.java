package com.wikia.webdriver.common.core;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class AlertHandler {

  public static boolean isAlertPresent(WebDriver driver) {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
