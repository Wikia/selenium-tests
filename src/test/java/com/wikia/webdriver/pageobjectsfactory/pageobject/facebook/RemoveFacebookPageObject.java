package com.wikia.webdriver.pageobjectsfactory.pageobject.facebook;

import org.openqa.selenium.WebDriver;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 * Created by rcunningham on 4/2/15.
 */
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
