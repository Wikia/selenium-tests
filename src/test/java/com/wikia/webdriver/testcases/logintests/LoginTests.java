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
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import junit.framework.Assert;
import org.testng.annotations.Test;

@Test(groups = "auth-login")
public class LoginTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();
  String jaTestWiki = "ja.ja-test";


  @Test(groups = "Login_anonCanLoginOnAuthModalFromGlobalNavigation")
  public void anonCanLoginOnAuthModalFromGlobalNavigation() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(wikiURL);
    NavigationBar signInLink = new NavigationBar(driver);
    signInLink.clickOnSignIn();
    AuthModal authModal = signInLink.getAuthModal();
    Assert.assertTrue(authModal.isSignInOpened());

    authModal.login(credentials.userName10, credentials.password10);
    base.verifyUserLoggedIn(credentials.userName10);
  }


  @Test(groups = "Login_anonCanLoginAsStaffOnAuthModalFromGlobalNavigation")
  public void anonCanLoginAsStaffOnAuthModalFromGlobalNavigation() {
    WikiBasePageObject base = new WikiBasePageObject();
    NavigationBar signInLink = new NavigationBar(driver);
    base.openWikiPage(wikiURL);

    signInLink.clickOnSignIn();
    AuthModal authModal = signInLink.getAuthModal();
    Assert.assertTrue(authModal.isSignInOpened());

    authModal.login(credentials.userNameStaff2, credentials.passwordStaff2);
    base.verifyUserLoggedIn(credentials.userNameStaff);
  }

  @Test(groups = "Login_anonCanLoginAsJapaneseUserOnUserLoginSpecialPage")
  @Execute(onWikia = "ja.ja-test")
  public void anonCanLoginAsJapaneseUserOnAuthModalFromGlobalNavigation() {
    WikiBasePageObject base = new WikiBasePageObject();
    NavigationBar signInLink = new NavigationBar(driver);
    base.openWikiPage(wikiURL);

    signInLink.clickOnSignIn();
    AuthModal authModal = signInLink.getAuthModal();
    Assert.assertTrue(authModal.isSignInOpened());

    authModal.login(credentials.userNameJapanese2, credentials.passwordJapanese2);
    base.verifyUserLoggedIn(credentials.userNameJapanese2);
  }

  @Test(groups = "Login_userWithoutAValidTokenGetsLoggedOut")
  @Execute(asUser = User.USER_12)
  public void userWithoutAValidTokenGetsLoggedOut() {
    new ArticleContent().clear();

    ArticlePageObject article = new ArticlePageObject().open();
    Helios.deleteAllTokens(User.USER_12);

    article.refreshPageAddingCacheBuster();
    Assertion.assertTrue(article.getGlobalNavigation().isUserLoggedOut());
  }
}
