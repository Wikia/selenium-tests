package com.wikia.webdriver.testcases.logintests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.Helios;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.AuthModal;
import com.wikia.webdriver.pageobjectsfactory.componentobject.dropdowncomponentobject.DropDownComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.login.SpecialUserLoginPageObject;

import junit.framework.Assert;
import org.testng.annotations.Test;

@Test(groups = "Login")
public class LoginTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();
  String jaTestWiki = "ja.ja-test";

  @Test(groups = {"Login_001", "Smoke5"})
  public void anonCanLoginOnUserLoginSpecialPage() {
    SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
    login.loginAndVerify(credentials.userName10, credentials.password10, wikiURL);
  }

  @Test(groups = {"Login_002", "Smoke5"})
  public void anonCanLoginFromDropdown() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(wikiURL);
    DropDownComponentObject dropDown = new DropDownComponentObject(driver);
    dropDown.openDropDown();
    dropDown.logIn(credentials.userName10, credentials.password10);
    base.verifyUserLoggedIn(credentials.userName10);
  }

  @Test(groups = {"Login_003", "Smoke5"})
  @Execute(onWikia = "agas")
  public void anonCanLoginOnAuthModalFromGlobalNavigation() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(wikiURL);
    NavigationBar signInLink = new NavigationBar(driver);
    signInLink.clickOnSignIn();
    AuthModal authModal = signInLink.getAuthModal();
    Assert.assertTrue(authModal.isOpened());

    authModal.login(credentials.userName10, credentials.password10);
    base = new WikiBasePageObject();
    base.verifyUserLoggedIn(credentials.userName10);
  }

  @Test(groups = "Login_004")
  public void anonCanLoginAsStaffOnUserLoginSpecialPage() {
    SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
    login.loginAndVerify(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
  }

  @Test(groups = "Login_005")
  public void anonCanLoginAsStaffFromDropdown() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(wikiURL);
    DropDownComponentObject dropDown = new DropDownComponentObject(driver);
    dropDown.openDropDown();
    dropDown.logIn(credentials.userNameStaff, credentials.passwordStaff);
    base.verifyUserLoggedIn(credentials.userNameStaff);
  }

  @Test(groups = "Login_006")
  @Execute(onWikia = "agas")
  public void anonCanLoginAsStaffOnAuthModalFromGlobalNavigation() {
    WikiBasePageObject base = new WikiBasePageObject();
    NavigationBar signInLink = new NavigationBar(driver);
    base.openWikiPage(wikiURL);

    signInLink.clickOnSignIn();
    AuthModal authModal = signInLink.getAuthModal();
    Assert.assertTrue(authModal.isOpened());

    authModal.login(credentials.userNameStaff, credentials.passwordStaff);
    base.verifyUserLoggedIn(credentials.userNameStaff);
  }

  @Test(groups = "Login_007")
  @Execute(onWikia = "ja.ja-test")
  public void anonCanLoginAsJapaneseUserOnUserLoginSpecialPage() {
    SpecialUserLoginPageObject specialLogin = new SpecialUserLoginPageObject(driver);
    specialLogin.loginAndVerify(credentials.userNameJapanese2, credentials.passwordJapanese2,
        wikiURL);
  }

  @Test(groups = "Login_008")
  @Execute(asUser = User.USER_12)
  public void userWithoutAValidTokenGetsLoggedOut() {
    new ArticleContent().clear();

    ArticlePageObject article = new ArticlePageObject().open();
    Helios.deleteAllTokens(User.USER_12);

    article.refreshPageAddingCacheBuster();
    Assertion.assertTrue(article.getGlobalNavigation().isUserLoggedOut());
  }
}
