package com.wikia.webdriver.testcases.desktop.auth;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.GraphApi;
import com.wikia.webdriver.common.core.helpers.FacebookUser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.AttachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.DetachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookMainPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Instant;

import static org.testng.Assert.assertTrue;

@Test(groups = {"auth-facebook"})
public class FacebookTests extends NewTestTemplate {

  private GraphApi facebookApi = new GraphApi();
  private FacebookUser facebookUser;

  @BeforeSuite
  private void setUp() {
    facebookUser = facebookApi.createFacebookTestUser();
  }

  @AfterSuite
  private void cleanUp() {
    facebookApi.deleteFacebookTestUser(facebookUser.getId());
  }

  public void facebookButtonIsVisibleOnSignUpPage() {
    AttachedRegisterPage registerPage = new WikiBasePageObject().openUserSignUpPage(wikiURL);
    assertTrue(registerPage.isConnectWithFacebookButtonVisible());
  }

  public void facebookButtonIsVisibleOnLoginPage() {
    AttachedSignInPage signInPage = new WikiBasePageObject().openUserLoginPage(wikiURL);
    assertTrue(signInPage.isConnectWithFacebookButtonVisible());
  }

  public void facebookButtonIsVisibleOnForcedLoginModal() {
    new WikiBasePageObject().openSpecialNewFiles(wikiURL).addPhoto();
    assertTrue(new DetachedRegisterPage().isConnectWithFacebookButtonVisible());
  }

  @Execute(asUser = User.USER)
  public void facebookButtonIsVisibleOnUserPreferencesPage() {
    PreferencesPageObject prefsPage = new WikiBasePageObject().openSpecialPreferencesPage(wikiURL);
    prefsPage.selectTab(PreferencesPageObject.tabNames.CONNECTIONS);
    assertTrue(prefsPage.isFacebookButtonVisible());
  }

  public void userCanSignUpViaFacebook() {
    String userName = String.format("QA%s", Instant.now().getEpochSecond());
    String redirectUrl = driver.getCurrentUrl();

    new FacebookMainPageObject().login(facebookUser.getEmail(), facebookUser.getPassword());
    AttachedRegisterPage signUp = new AttachedRegisterPage().open(redirectUrl);
    FacebookSignupModalComponentObject fbModal = signUp.clickFacebookSignUp();

    fbModal.createAccountNoEmail(facebookUser.getEmail(), userName, 1, 1, 1970);
    new WikiBasePageObject().verifyUserLoggedIn(userName);
  }
}
