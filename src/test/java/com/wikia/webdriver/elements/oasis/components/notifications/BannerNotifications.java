package com.wikia.webdriver.elements.oasis.components.notifications;


import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialRestorePageObject;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

  public boolean isNotificationMessageVisible() {
    try {
      wait.forElementVisible(bannerNotification, 10);
      return true;
    } catch(TimeoutException e) {
      PageObjectLogging.logInfo("Banner notification is not visible", e);
      return false;
    }
  }

  public String getBannerNotificationText() {
    return bannerNotification.getText();
  }

  public String getBannerNotificationTextEmailNotConfirmed() {
    return bannerNotificationEmailNotConfirmed.getText();
  }

}
