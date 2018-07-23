package com.wikia.webdriver.elements.fandom.components;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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
