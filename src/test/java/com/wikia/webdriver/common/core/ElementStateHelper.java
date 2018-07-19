package com.wikia.webdriver.common.core;

import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

public class ElementStateHelper {

  public static final int TIMEOUT = 30;

  private ElementStateHelper() {

  }

  public static boolean isElementVisible(WebElement element, WebDriver webDriver) {
    webDriver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

    try {
      return element.isDisplayed();
    } catch (NoSuchElementException | StaleElementReferenceException e) {
      return false;
    } finally {
      webDriver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
    }
  }
}
