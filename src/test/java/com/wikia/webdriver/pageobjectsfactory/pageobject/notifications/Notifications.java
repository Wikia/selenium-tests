package com.wikia.webdriver.pageobjectsfactory.pageobject.notifications;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Notifications extends BasePageObject {

  @Getter
  @FindBy(css = ".wds-notifications__notification-list")
  private WebElement notificationsList;

  @FindBy(css = ".wds-notifications__zero-state")
  private WebElement emptyState;

  public boolean isEmptyStateMessageVisible() {
    return wait.forElementVisible(emptyState).isDisplayed();
  }

}
