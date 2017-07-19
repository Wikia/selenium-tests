package com.wikia.webdriver.pageobjectsfactory.pageobject.notifications;


import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import lombok.Builder;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public class Notification extends BasePageObject {

  @FindBy(css = ".wds-notification-card")
  private List<WebElement> notificationCards;

  @FindBy(css = ".wds-avatar-stack")
  private WebElement avatars;

  @FindBy(css = ".wds-notification-card__community")
  private WebElement community;

  @FindBy(css = "li.wds-notification-card__context-item:first-child")
  private WebElement timestamp;

  private NotificationType type;
  private String actor;
  private String contentObject;

  public boolean isAvatarStackDisplayed() {
    return wait.forElementVisible(avatars).isDisplayed();
  }

  private String getFormat() {
    return type.getFormat();
  }

  public String getContent() {
    return type == NotificationType.REPLY_UPVOTE ?
      String.format(getFormat(), actor) :
      String.format(getFormat(), actor, contentObject);
  }
}
