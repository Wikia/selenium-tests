package com.wikia.webdriver.pageobjectsfactory.pageobject.notifications;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NotificationsDropdown extends BasePageObject {

  @FindBy(className = "wds-notifications")
  private WebElement notificationsBell;

  @Getter
  private Notifications notifications = new Notifications();

  public NotificationsDropdown expand() {
    wait.forElementVisible(notificationsBell);
    hover(notificationsBell);
    wait.forElementVisible(notifications.getNotificationsList());
    return this;
  }

  public NotificationsDropdown collapse() {
    moveAway(notificationsBell);
    wait.forElementNotVisible(notifications.getNotificationsList());
    return this;
  }
}
