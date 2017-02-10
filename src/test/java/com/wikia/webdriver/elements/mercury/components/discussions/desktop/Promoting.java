package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Promoting extends BasePageObject {

  @FindBy(css = ".discussion-app-join-text")
  private WebElement appPromotionText;

  @FindBy(css = ".apple-store-badge")
  private WebElement appleAppLink;

  @FindBy(css = ".google-play-badge")
  private WebElement googlePlayAppLink;

  @FindBy(css = ".smart-banner-android")
  private WebElement mobileBanner;

  @FindBy(css = ".smart-banner-android .sb-button")
  private WebElement mobileInstallButton;

  public boolean isAppleLinkDisplayed() {
    return appleAppLink.isDisplayed();
  }

  public boolean isMobileBannerDisplayed() {
    return mobileBanner.isDisplayed();
  }

  public boolean isGooglePlayLinkDisplayed() {
    return googlePlayAppLink.isDisplayed();
  }

  public String getPromotionAppText() {
    return appPromotionText.getText();
  }

  public String getPromotionAppMobileText() {
    return mobileBanner.getText();
  }

  public void clickInstallOnMobileBanner() {
    openLinkInNewTab(mobileInstallButton);
  }

  public void clickAppleLinkInAppPromotion() {
    openLinkInNewTab(appleAppLink);
  }

  public void clickGooglePlayLinkInAppPromotion() {
    openLinkInNewTab(googlePlayAppLink);
  }

}
