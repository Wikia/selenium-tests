package com.wikia.webdriver.elements.communities.mobile.components;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.notifications.Notifications;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserProfile extends BasePageObject {

  @Getter(lazy = true)
  private final Notifications notifications = new Notifications();
  @FindBy(css = ".wds-sign-out__button")
  private WebElement logoutButton;

  public boolean isLogoutButtonVisible() {
    return wait.forElementVisible(logoutButton).isDisplayed();
  }
}
