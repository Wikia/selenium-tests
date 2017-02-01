package com.wikia.webdriver.elements.fandom.components;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class WPNotifications extends BasePageObject {

  @FindBy(css = ".notice:not(.hidden)")
  List<WebElement> notifications;

  public boolean isNotificationVisible(String notificationText) {
    for (WebElement element : notifications) {
      if (element.findElement(By.cssSelector("p")).getText().contains(notificationText)) {
        return true;
      }
    }

    return false;
  }
}
