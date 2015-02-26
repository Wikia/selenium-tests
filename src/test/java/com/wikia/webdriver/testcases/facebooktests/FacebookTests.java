package com.wikia.webdriver.testcases.facebooktests;

import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookMainPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookSettingsPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookUserPageObject;

import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.AlmostTherePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.SignUpPageObject;
import org.testng.annotations.Test;

public class FacebookTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  /**
   * dependent method: Signup_007_signUpWithFacebook and Facebook_002_noEmailPerms
   * Steps: 1. Log in to facebook
   * 2. Open Facebook settings
   * 3. Remove Wikia and Wikia Development App
   */
  @Test(groups = {"Facebook_001", "Facebook"})
  @UseUnstablePageLoadStrategy
  public void Facebook_001_removeWikiaApps() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    FacebookMainPageObject fbLogin = base.openFacebookMainPage();
    FacebookUserPageObject userFB;
    userFB = fbLogin.login(credentials.emailFB, credentials.passwordFB);
    userFB.verifyPageLogo();
    FacebookSettingsPageObject settingsFB = userFB.fbOpenSettings();
    settingsFB.openApps();
    settingsFB.removeAppIfPresent();
  }

  /**
   * depends on method Facebook_001_removeWikiaApps
   * Steps:
   * 1. Log in to facebook
   * 2. Click facebook login on signup page
   * 3. Deny permission to user's facebook email address
   * 4. manually enter email address and create account
   * 5. confirm account and login,
   * 6. Verify user can login via facebook
   */
  @Test(
          groups = {"Facebook_002", "Facebook"},
          dependsOnMethods = {"Facebook_001_removeWikiaApps"}
  )
@UseUnstablePageLoadStrategy
  public void Facebook_002_noEmailPerms() {

    WikiBasePageObject base = new WikiBasePageObject(driver);
    FacebookMainPageObject fbLogin = base.openFacebookMainPage();
    FacebookUserPageObject userFB = fbLogin.login(credentials.emailFB, credentials.passwordFB);
    userFB.verifyPageLogo();

    SignUpPageObject signUp = userFB.openSpecialSignUpPage(wikiURL);
    FacebookSignupModalComponentObject fbModal = signUp.clickFacebookSignUp();

    String userName = "QA" + signUp.getTimeStamp();
    String password = "Pass" + signUp.getTimeStamp();
    String email = credentials.emailQaart2;
    String emailPassword = credentials.emailPasswordQaart2;
    fbModal.createAccountNoEmail(email, emailPassword, userName, password);
    signUp.verifyUserLoggedIn(userName);

    AlmostTherePageObject almostThere = new AlmostTherePageObject(driver);
    almostThere.confirmAccountAndLogin(email, emailPassword, userName, password, wikiURL);
    almostThere.logOut(wikiURL);

    SignUpPageObject signUp2 = userFB.openSpecialSignUpPage(wikiURL);
    signUp2.clickFacebookSignUp();
    signUp2.verifyUserLoggedIn(userName);
  }
}
