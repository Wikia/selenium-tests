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

  public void removeWikiaApps(String email, String password) {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    FacebookMainPageObject fbLogin = base.openFacebookMainPage();
    FacebookUserPageObject userFB = fbLogin.login(email, password);
    userFB.verifyPageLogo();
    FacebookSettingsPageObject settingsFB = userFB.fbOpenSettings();
    settingsFB.openApps();
    settingsFB.removeAppIfPresent();
    settingsFB.openApps();
    settingsFB.logOutFB();
  }
}
