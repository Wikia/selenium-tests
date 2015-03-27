package com.wikia.webdriver.common.core;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.wikia.webdriver.common.core.configuration.ConfigurationFactory;

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
  
  public static void acceptPopupWindow(WebDriver driver) {
      Alert alert = driver.switchTo().alert();
      alert.accept();
  }
  
  public static void dismissPopupWindow(WebDriver driver) {
      Alert alert = driver.switchTo().alert();
      alert.dismiss();
  }
  
}
