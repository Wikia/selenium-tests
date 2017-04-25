package com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NotificationsDropdown extends BasePageObject implements Dropdown  {

  @FindBy(id = "onSiteNotificationsDropdown")
  private WebElement notificationsBell;

  @Override public Dropdown expand() {
    return null;
  }

  @Override public Dropdown collapse() {
    return null;
  }
}
