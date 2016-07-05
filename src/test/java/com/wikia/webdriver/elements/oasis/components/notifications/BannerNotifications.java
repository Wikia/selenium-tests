package com.wikia.webdriver.elements.oasis.components.notifications;


import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialRestorePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class BannerNotifications extends BasePageObject{

  @FindBy(css = ".banner-notification div.msg a")
  private WebElement undeleteLink;
  @FindBy(css = ".banner-notification")
  private WebElement bannerNotification;
  @FindBy(css = ".banner-notification.non-dismissible")
  private WebElement bannerNotificationEmailNotConfirmed;


  public SpecialRestorePageObject clickUndeleteLinkInBannerNotification() {
    wait.forElementVisible(undeleteLink);
    undeleteLink.click();
    return new SpecialRestorePageObject(driver);
  }

  public void verifyNotificationMessage() {
    driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
    try {
      wait.forElementVisible(bannerNotification);
    } finally {
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
  }

  public String getBannerNotificationText() {
    return bannerNotification.getText();
  }

  public String getBannerNotificationTextEmailNotConfirmed() {
    return bannerNotificationEmailNotConfirmed.getText();
  }

}
