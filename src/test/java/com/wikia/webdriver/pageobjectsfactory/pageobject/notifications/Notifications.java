package com.wikia.webdriver.pageobjectsfactory.pageobject.notifications;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class Notifications extends BasePageObject {

  @Getter
  @FindBy(css = ".wds-notifications__notification-list")
  private WebElement notificationsList;

  @FindBy(css = ".wds-notification-card")
  private List<WebElement> notificationCards;

  @FindBy(css = ".wds-notifications__zero-state")
  private WebElement emptyState;

  @FindBy(css = ".wds-notifications__mark-all-as-read")
  private WebElement markAllAsRead;

  private static final String UNREAD_CLASS = "wds-is-unread";

  public boolean isEmptyStateMessageVisible() {
    return wait.forElementVisible(emptyState).isDisplayed();
  }

  public void markAllAsRead() {
    waitAndClick(markAllAsRead);
    wait.forElementNotVisible(markAllAsRead);
  }

  public boolean isAnyNotificationUnread() {
    List<WebElement> notifications = notificationCards
      .stream()
      .filter(card -> card
        .getAttribute("class")
        .contains(UNREAD_CLASS))
      .collect(Collectors.toList());
    return !notifications.isEmpty();
  }

  public boolean contains(Notification notification) {
    PageObjectLogging.log("Searching for notification", notification.getContent(), true);
    List<WebElement> notifications = notificationCards
      .stream()
      .filter(card -> card
        .getText()
        .contains(notification.getContent()))
      .collect(Collectors.toList());
    return !notifications.isEmpty();
  }

}
