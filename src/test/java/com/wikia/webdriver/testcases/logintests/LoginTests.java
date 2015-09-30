package com.wikia.webdriver.testcases.logintests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.dropdowncomponentobject.DropDownComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.login.SpecialUserLoginPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *         <p/>
 *         1. Login user using Special:UserLogin page, 2. Login user using drop-down 3. Login staff
 *         user using Special:UserLogin page, 4. Login staff user using drop-down 5. Login japanese
 *         user
 */
public class LoginTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();
  String jaTestWiki = "ja.ja-test";

  @Test(groups = {"Login_001", "Login", "Smoke5"})
  public void Login_001_specialPageUser() {
    SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
    login.loginAndVerify(credentials.userName, credentials.password, wikiURL);
  }

  @Test(groups = {"Login_002", "Login", "Smoke5"})
  public void Login_002_dropDownUser() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.openWikiPage(wikiURL);
    DropDownComponentObject dropDown = new DropDownComponentObject(driver);
    dropDown.openDropDown();
    dropDown.logIn(credentials.userName2, credentials.password2);
    base.verifyUserLoggedIn(credentials.userName2);
  }

  @Test(groups = {"Login_003", "Login"})
  public void Login_003_specialPageStaff() {
    SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
    login.loginAndVerify(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
  }

  @Test(groups = {"Login_004", "Login"})
  public void Login_004_dropDownStaff() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.openWikiPage(wikiURL);
    DropDownComponentObject dropDown = new DropDownComponentObject(driver);
    dropDown.openDropDown();
    dropDown.logIn(credentials.userNameStaff, credentials.passwordStaff);
    base.verifyUserLoggedIn(credentials.userNameStaff);
  }

  @Test(groups = {"Login_005", "Login"})
  @Execute(onWikia = "ja.ja-test")
  public void Login_005_japaneseUserLogin() {
    SpecialUserLoginPageObject specialLogin = new SpecialUserLoginPageObject(driver);
    specialLogin.loginAndVerify(credentials.userNameJapanese2, credentials.passwordJapanese2,
        wikiURL);
  }
}
