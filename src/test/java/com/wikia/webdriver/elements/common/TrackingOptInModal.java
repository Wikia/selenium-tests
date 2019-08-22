package com.wikia.webdriver.elements.common;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TrackingOptInModal extends BasePageObject {

  private static final By MODAL_SELECTOR = By.cssSelector("div[data-tracking-opt-in-overlay]");
  private static final By
      ACCEPT_BUTTON_SELECTOR
      = By.cssSelector("div[data-tracking-opt-in-accept]");
  private static final By LEARN_MORE_BUTTON_SELECTOR = By.cssSelector(
      "div[data-tracking-opt-in-learn-more]");
  private final BasePageObject page;
  private static final By
      SAVE_CHANGES_BUTTON_SELECTOR
      = By.cssSelector("div[data-tracking-opt-in-save]");
  @FindBy(css = "div[data-tracking-opt-in-overlay] [for]")
  private List<WebElement> allFeatureSwitches;

  public TrackingOptInModal(BasePageObject page) {
    this.page = page;
    PageFactory.initElements(driver, this);
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
  public void clickSaveChangesButton() {
    try {
      page.wait.forElementClickable(SAVE_CHANGES_BUTTON_SELECTOR).click();
    } catch (Exception e) {
      Log.log("Save changes button clicked", e, false);
    }
  }

  public void clickLearnMoreButton() {
    try {
      page.wait.forElementClickable(LEARN_MORE_BUTTON_SELECTOR).click();
    } catch (Exception e) {
      Log.log("Reject button clicked", e, false);
    }
  }

  public void rejectAllFeatures() {
    try {
      for (WebElement featureSwitch : allFeatureSwitches) {
        featureSwitch.click();
      }
    } catch (TimeoutException e) {
      Log.log("Feature switches clicked", e, false);
    }
  }
}
