package com.wikia.webdriver.testcases.facebooktests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.AttachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.DetachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject.tabNames;
import org.testng.annotations.Test;

import static com.wikia.webdriver.common.core.Assertion.assertTrue;

/* 
 * Check for facebook button on the page
 */
@Test(groups = {"auth-facebookButton"})
public class FacebookButtonTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = "FacebookButton_facebookButtonIsVisibleOnSignUpPage")
  public void facebookButtonIsVisibleOnSignUpPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    AttachedRegisterPage registerPage = base.openSpecialUserSignUpPage(wikiURL);
    assertTrue(registerPage.isConnectWithFacebookButtonVisible());

  }

  @Test(groups = "FacebookButton_facebookButtonIsVisibleOnLoginPage")
  public void facebookButtonIsVisibleOnLoginPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    AttachedSignInPage signInPage = base.openSpecialUserLogin(wikiURL);
    assertTrue(signInPage.isConnectWithFacebookButtonVisible());
  }

  @Test(groups = "FacebookButton_facebookButtonIsVisibleOnForcedLoginModal")
  public void facebookButtonIsVisibleOnForcedLoginModal() {
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialNewFilesPage specialPage = base.openSpecialNewFiles(wikiURL);
    specialPage.verifyPageHeader(specialPage.getTitle());
    specialPage.addPhoto();

    DetachedRegisterPage registerPage = new DetachedRegisterPage();
    assertTrue(registerPage.isConnectWithFacebookButtonVisible());
  }


  @Test(groups = "FacebookButton_facebookButtonIsVisibleOnUserPreferencesPage")
  @Execute(asUser = User.USER)
  public void facebookButtonIsVisibleOnUserPreferencesPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    PreferencesPageObject prefsPage = base.openSpecialPreferencesPage(wikiURL);
    prefsPage.selectTab(tabNames.FACEBOOK);
    prefsPage.verifyFBButtonVisible();
  }

}

