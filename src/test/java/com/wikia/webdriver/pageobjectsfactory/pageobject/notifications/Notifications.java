package com.wikia.webdriver.pageobjectsfactory.pageobject.notifications;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class Notifications extends BasePageObject {

  private static final String UNREAD_CLASS = "wds-is-unread";
  @Getter
  @FindBy(css = ".wds-notifications__notification-list")
  private WebElement notificationsList;
  @FindBy(css = ".wds-notification-card")
  private List<WebElement> notificationCards;
  @FindBy(css = ".wds-notifications__mark-all-as-read-button")
  private WebElement markAllAsRead;

  public void markAllAsRead() {
    wait.forElementVisible(markAllAsRead).click();
  }

  public boolean isMarkAllAsReadButtonVisible() {
    return isVisible(markAllAsRead);
  }

  public boolean isAnyNotificationUnread() {
    return notificationCards.stream()
        .anyMatch(card -> card.getAttribute("class").contains(UNREAD_CLASS));
  }

  public boolean contains(Notification notification) {
    Log.log("Searching for notification", notification.getContent(), true);
    List<WebElement> notifications = notificationCards.stream()
        .filter(card -> card.getText().contains(notification.getContent()))
        .collect(Collectors.toList());
    return !notifications.isEmpty();
  }
}
