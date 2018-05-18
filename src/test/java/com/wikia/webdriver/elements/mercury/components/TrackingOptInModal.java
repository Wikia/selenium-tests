package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TrackingOptInModal extends WikiBasePageObject {
  @FindBy(css="div[data-tracking-opt-in-overlay]")
  private WebElement optInModalOverlay;

  public boolean isVisible() {
    try {
      wait.forElementVisible(optInModalOverlay);
      PageObjectLogging.logInfo("Tracking modal visible");

      return true;
    } catch (TimeoutException e) {
      PageObjectLogging.logInfo("Tracking modal not visible");

      return false;
    }
  }
}
