package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.Notifications;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserProfile extends BasePageObject {

  @FindBy(css = ".wds-sign-out__button")
  private WebElement logoutButton;

  @Getter
  public Notifications notifications;

  public UserProfile() {
    notifications = new Notifications();
  }

  public boolean isLogoutButtonVisible() {
    return wait.forElementVisible(logoutButton).isDisplayed();
  }

}
