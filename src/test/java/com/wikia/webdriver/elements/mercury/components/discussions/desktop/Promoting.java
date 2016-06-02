package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Promoting extends BasePageObject {

  @FindBy(css = ".discussion-app-join-text")
  private WebElement appPromotionText;

  @FindBy(css = ".apple-store-logo")
  private WebElement appleAppLink;

  @FindBy(css = ".google-play-logo")
  private WebElement googlePlayAppLink;

  public boolean isAppleLinkDisplayed() {
    return appleAppLink.isDisplayed();
  }

  public boolean isGooglePlayLinkDisplayed() {
    return googlePlayAppLink.isDisplayed();
  }

  public String isPromotionAppTextDisplayed() {
    return appPromotionText.getText();
  }

  public void clickAppleLinkInAppPromotion() {
    appleAppLink.click();
  }

  public void clickGooglePlayLinkInAppPromotion() {
    googlePlayAppLink.click();
  }
}
