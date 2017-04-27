package com.wikia.webdriver.pageobjectsfactory.pageobject.notifications;


import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Builder
public class Notification extends BasePageObject {

  @FindBy(css = ".wds-notification-card")
  private WebElement notificationCard;

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

}
