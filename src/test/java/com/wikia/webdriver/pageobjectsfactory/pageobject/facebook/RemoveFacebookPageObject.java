package com.wikia.webdriver.pageobjectsfactory.pageobject.facebook;

import org.openqa.selenium.WebDriver;

public class RemoveFacebookPageObject extends FacebookMainPageObject {

  public RemoveFacebookPageObject(WebDriver driver) {
    super(driver);
  }

  public FacebookSettingsPageObject removeWikiaApps(String email, String password) {
    FacebookSettingsPageObject settingsFB = new FacebookSettingsPageObject(driver).open();
    new FacebookMainPageObject(driver).login(email, password);
    return settingsFB.removeAppIfPresent();
  }
}
