package com.wikia.webdriver.testcases.logintests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.AuthModal;
import com.wikia.webdriver.pageobjectsfactory.componentobject.dropdowncomponentobject.DropDownComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.login.SpecialUserLoginPageObject;

import junit.framework.Assert;
import org.testng.annotations.Test;

/**
 * @ownership Social
 */

@Test(groups = "Login")
public class LoginTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();
  String jaTestWiki = "ja.ja-test";

  @Test(groups = {"Login_001", "Smoke5"})
  public void Login_001_specialPageUser() {
    SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
    login.loginAndVerify(credentials.userName, credentials.password, wikiURL);
  }

  @Test(groups = {"Login_002", "Smoke5"})
  public void Login_002_dropDownUser() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.openWikiPage(wikiURL);
    DropDownComponentObject dropDown = new DropDownComponentObject(driver);
    dropDown.openDropDown();
    dropDown.logIn(credentials.userName2, credentials.password2);
    base.verifyUserLoggedIn(credentials.userName2);
  }

  @Test(groups = {"Login_003", "Smoke5"})
  @Execute(onWikia = "agas")
  public void Login_003_authModalInGlobalNav() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.openWikiPage(wikiURL);
    NavigationBar signInLink = new NavigationBar(driver);
    signInLink.clickOnSignIn();
    AuthModal authModal = signInLink.getAuthModal();
    Assert.assertTrue(authModal.isOpened());

    authModal.login(credentials.userName, credentials.password);
    base.verifyUserLoggedIn(credentials.userName);
  }

  @Test(groups = "Login_004")
  public void Login_004_specialPageStaff() {
    SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
    login.loginAndVerify(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
  }

  @Test(groups = "Login_005")
  public void Login_005_dropDownStaff() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.openWikiPage(wikiURL);
    DropDownComponentObject dropDown = new DropDownComponentObject(driver);
    dropDown.openDropDown();
    dropDown.logIn(credentials.userNameStaff, credentials.passwordStaff);
    base.verifyUserLoggedIn(credentials.userNameStaff);
  }

  @Test(groups = "Login_006")
  @Execute(onWikia = "ja.ja-test")
  public void Login_006_japaneseUserLogin() {
    SpecialUserLoginPageObject specialLogin = new SpecialUserLoginPageObject(driver);
    specialLogin.loginAndVerify(credentials.userNameJapanese2, credentials.passwordJapanese2, wikiURL);
  }
}
