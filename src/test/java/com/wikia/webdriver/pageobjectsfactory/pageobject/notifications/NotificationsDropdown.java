package com.wikia.webdriver.pageobjectsfactory.pageobject.notifications;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NotificationsDropdown extends BasePageObject implements Dropdown  {

  @FindBy(className = "notifications-scroll-menu")
  private WebElement notificationsBell;

  @Getter
  private Notifications notifications = new Notifications();

  @Override public Dropdown expand() {
    hover(notificationsBell);
    wait.forElementVisible(notifications.getNotificationsList());
    return this;
  }

  @Override public Dropdown collapse() {
    moveAway(notificationsBell);
    wait.forElementNotVisible(notifications.getNotificationsList());
    return this;
  }
}
