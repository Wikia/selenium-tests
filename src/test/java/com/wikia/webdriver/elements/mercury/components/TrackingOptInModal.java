package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TrackingOptInModal extends WikiBasePageObject {
  @FindBy(css="div[data-tracking-opt-in-overlay]")
  private WebElement optInModalOverlay;

  public boolean isVisible() {
    wait.forElementVisible(optInModalOverlay);

    return optInModalOverlay.isDisplayed();
  }
}
