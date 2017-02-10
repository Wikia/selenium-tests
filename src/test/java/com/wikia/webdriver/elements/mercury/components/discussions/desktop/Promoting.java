package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.google.common.base.Predicate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

import static java.lang.Thread.sleep;


public class Promoting extends BasePageObject {

  private static final int TIMEOUT = 3000;
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
