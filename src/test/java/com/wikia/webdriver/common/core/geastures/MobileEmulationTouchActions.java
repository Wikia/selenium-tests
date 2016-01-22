package com.wikia.webdriver.common.core.geastures;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class MobileEmulationTouchActions {

  WebDriver driver;

  public MobileEmulationTouchActions(WebDriver driver) {
    this.driver = driver;
  }

  public void scrollBy(int x, int y) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(arguments[0], arguments[1])", x, y);
  }
}
