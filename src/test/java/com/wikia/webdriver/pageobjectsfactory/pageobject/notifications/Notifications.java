package com.wikia.webdriver.pageobjectsfactory.pageobject.notifications;

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

  public Notifications() {
    wait.forElementVisible(getNotificationsList());
  }

  private List<Notification> nofitications;

  public boolean isEmptyStateMessageVisible() {
    return wait.forElementVisible(emptyState).isDisplayed();
  }

  public boolean contains(Notification notification) {
    List<WebElement> notifications = notificationCards
      .stream()
      .filter(card -> card
        .getText()
        .contains(notification.getContent()))
      .collect(Collectors.toList());
    return !notifications.isEmpty();
  }

}
