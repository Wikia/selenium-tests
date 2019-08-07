package com.wikia.webdriver.elements.communities.desktop.pages;

import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class PrivacyPolicyPage extends WikiBasePageObject {

  private static final String PRIVACY_POLICY_PAGE = "https://www.fandom.com/privacy-policy";
  @FindBy(css = "#privacy-settings-button")
  private WebElement resetTrackingButton;

  public void navigateToPrivacyPolicyPage() {
    new Navigate().toUrl(PRIVACY_POLICY_PAGE);
  }

  public void clickResetTrackingButton() {
    new Actions(driver).moveToElement(resetTrackingButton,0,-250);
    wait.forElementClickable(resetTrackingButton);
    waitAndClick(resetTrackingButton);
  }
}
