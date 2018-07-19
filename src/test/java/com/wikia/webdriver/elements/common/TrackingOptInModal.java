package com.wikia.webdriver.elements.common;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class TrackingOptInModal {

  private static final By MODAL_SELECTOR = By.cssSelector("div[data-tracking-opt-in-overlay]");
  private static final By
      ACCEPT_BUTTON_SELECTOR
      = By.cssSelector("div[data-tracking-opt-in-accept]");
  private static final By
      REJECT_BUTTON_SELECTOR
      = By.cssSelector("div[data-tracking-opt-in-reject]");
  private final BasePageObject page;

  public TrackingOptInModal(BasePageObject page) {
    this.page = page;
  }

  public boolean isVisible() {
    try {
      page.wait.forElementVisible(MODAL_SELECTOR);
      Log.info("Tracking modal visible");

      return true;
    } catch (TimeoutException e) {
      Log.info("Tracking modal not visible", e);

      return false;
    }
  }

  public void clickAcceptButton() {
    try {
      page.wait.forElementClickable(ACCEPT_BUTTON_SELECTOR).click();
    } catch (Exception e) {
      Log.log("Accept button clicked", e, false);
    }
  }

  public void clickRejectButton() {
    try {
      page.wait.forElementClickable(REJECT_BUTTON_SELECTOR).click();
    } catch (Exception e) {
      Log.log("Reject button clicked", e, false);
    }
  }
}
