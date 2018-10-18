package com.wikia.webdriver.testcases.desktop.auth;

import static org.testng.Assert.assertTrue;

import com.wikia.webdriver.common.core.annotations.Execute;

import com.wikia.webdriver.common.core.api.GraphApi;
import com.wikia.webdriver.common.core.helpers.GoogleUser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.AttachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.DetachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


@Test(groups = {"auth-google"})
public class GoogleTests extends NewTestTemplate {

  private GraphApi googleApi = new GraphApi();
  private GoogleUser googleUser;

  @BeforeSuite
  private void setUp() {
    //TODO
  }

  @AfterSuite
  private void cleanUp() {
    //TODO
  }

  public void googleButtonIsVisibleOnSignUpPage() {
    AttachedRegisterPage registerPage = new WikiBasePageObject().openUserSignUpPage(wikiURL);
    assertTrue(registerPage.isConnectWithGoogleButtonVisible());
  }

  public void googleButtonIsVisibleOnLoginPage() {
    AttachedSignInPage signInPage = new WikiBasePageObject().openUserLoginPage(wikiURL);
    assertTrue(signInPage.isConnectWithGoogleButtonVisible());
  }

  public void googleButtonIsVisibleOnForcedLoginModal() {
    new WikiBasePageObject().openSpecialNewFiles(wikiURL).addPhoto();
    assertTrue(new DetachedRegisterPage().isConnectWithGoogleButtonVisible());
  }

  @Execute(asUser = User.USER)
  public void googleButtonIsVisibleOnUserPreferencesPage() {
    PreferencesPageObject prefsPage = new WikiBasePageObject().openSpecialPreferencesPage(wikiURL);
    prefsPage.selectTab(PreferencesPageObject.tabNames.GOOGLE);
    assertTrue(prefsPage.isGoogleButtonVisible());
  }
}
