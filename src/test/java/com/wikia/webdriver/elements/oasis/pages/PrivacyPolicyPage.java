package com.wikia.webdriver.elements.oasis.pages;

import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivacyPolicyPage extends WikiBasePageObject {

  @FindBy(css = "#privacy-settings-button")
  private WebElement resetTrackingButton;

  private static final String PRIVACY_POLICY_PAGE = "http://www.wikia.com/wiki/Privacy_Policy";


  public void navigateToPrivacyPolicyPage() {
    new Navigate().toUrl(PRIVACY_POLICY_PAGE);
  }

  public void clickResetTrackingButton () {
    scrollTo(resetTrackingButton);
    wait.forElementClickable(resetTrackingButton);
    resetTrackingButton.click();

  }

}
