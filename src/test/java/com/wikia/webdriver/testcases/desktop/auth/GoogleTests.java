package com.wikia.webdriver.testcases.desktop.auth;

import static org.testng.Assert.assertTrue;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.AttachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.DetachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;

import org.testng.annotations.Test;

@Test(groups = {"auth-google"})
public class GoogleTests extends NewTestTemplate {

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

  @Test
  @Execute(asUser = User.USER)
  public void googleButtonIsVisibleOnUserPreferencesPage() {
    PreferencesPageObject prefsPage = new WikiBasePageObject().openSpecialPreferencesPage(wikiURL);
    prefsPage.selectTab(PreferencesPageObject.tabNames.CONNECTIONS);
    assertTrue(prefsPage.isGoogleButtonVisible());
  }
}
