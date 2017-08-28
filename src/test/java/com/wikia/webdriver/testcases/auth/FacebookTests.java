package com.wikia.webdriver.testcases.auth;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
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
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookSettingsPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static org.testng.Assert.assertTrue;

@Test(groups = {"auth-facebook"})
public class FacebookTests extends NewTestTemplate {

  private GraphApi facebookApi = new GraphApi();
  private FacebookUser facebookUser;

  @BeforeSuite
  private void setUp(){
    facebookUser = facebookApi.createFacebookTestUser();
  }

  @AfterSuite
  private void cleanUp() {
    facebookApi.deleteFacebookTestUser(facebookUser.getId());
  }

  public void facebookButtonIsVisibleOnSignUpPage() {
    AttachedRegisterPage registerPage = new WikiBasePageObject().openSpecialUserSignUpPage(wikiURL);
    assertTrue(registerPage.isConnectWithFacebookButtonVisible());
  }

  public void facebookButtonIsVisibleOnLoginPage() {
    AttachedSignInPage signInPage = new WikiBasePageObject().openSpecialUserLogin(wikiURL);
    assertTrue(signInPage.isConnectWithFacebookButtonVisible());
  }

  public void facebookButtonIsVisibleOnForcedLoginModal() {
    new WikiBasePageObject().openSpecialNewFiles(wikiURL).addPhoto();
    assertTrue(new DetachedRegisterPage().isConnectWithFacebookButtonVisible());
  }


  @Execute(asUser = User.USER)
  public void facebookButtonIsVisibleOnUserPreferencesPage() {
    PreferencesPageObject prefsPage = new WikiBasePageObject().openSpecialPreferencesPage(wikiURL);
    prefsPage.selectTab(PreferencesPageObject.tabNames.FACEBOOK);
    assertTrue(prefsPage.isFacebookButtonVisible());
  }

  @RelatedIssue(issueID = "IRIS-4714")
  public void userCanSignUpViaFacebook() {

    new FacebookSettingsPageObject(driver).open();
    new FacebookMainPageObject(driver).login(facebookUser.getEmail(), facebookUser.getPassword());
    AttachedRegisterPage signUp = new AttachedRegisterPage().open();
    FacebookSignupModalComponentObject fbModal = signUp.clickFacebookSignUp();

    String userName = String.format("QA%s", LocalDateTime.now());
    String password = String.format("Pass%s", LocalDateTime.now());

    fbModal.createAccountNoEmail(facebookUser.getEmail(), userName, password);
    new WikiBasePageObject().verifyUserLoggedIn(userName);
  }

}
