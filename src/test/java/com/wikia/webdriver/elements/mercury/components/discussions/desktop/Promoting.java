package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Promoting extends BasePageObject {

  @FindBy(css = ".smart-banner-android")
  private WebElement mobileBanner;

  @FindBy(css = ".smart-banner-android .sb-button")
  private WebElement mobileInstallButton;

  public boolean isMobileBannerDisplayed() {
    return mobileBanner.isDisplayed();
  }

  public String getPromotionAppMobileText() {
    return mobileBanner.getText();
  }

  public void clickInstallOnMobileBanner() {
    openLinkInNewTab(mobileInstallButton);
  }


}
