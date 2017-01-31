package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Loading {

  private By loadingOverlay = By.cssSelector(".loading-overlay");

  private Wait wait;

  public Loading(WebDriver driver) {
    this.wait = new Wait(driver);

    PageFactory.initElements(driver, this);
  }

  /**
   * This method helps handling async page reload on Mercury
   */
  public void handleAsyncPageReload() {
    boolean spinnerPresent = false;

    try {
      wait.forElementVisible(loadingOverlay, 4, 1000);
      spinnerPresent = true;
      PageObjectLogging.logInfo("Loading overlay is present");
    } catch (TimeoutException e) {
      PageObjectLogging.logInfo("Loading overlay is not present");
    }

    if (spinnerPresent) {
      wait.forElementNotVisible(loadingOverlay, 8, 3000);
      PageObjectLogging.logInfo("Loading overlay is not present");
    }
  }
}
