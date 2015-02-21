package com.wikia.webdriver.testcases.facebooktests;

import com.wikia.webdriver.common.core.MailFunctions;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookMainPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookSettingsPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookUserPageObject;

import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.AlmostTherePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.ConfirmationPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.SignUpPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.UserProfilePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.login.SpecialUserLoginPageObject;
import org.testng.annotations.Test;

public class FacebookTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  /**
   * dependent method: Signup_007_signUpWithFacebook <p/> Steps: 1. Log in to facebook 2. Open
   * Facebook settings 3. Remove Wikia and Wikia Development App
   */
  @Test(groups = {"Facebook_001", "Facebook"})
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

  @Test(groups = {"Facebook_002", "Facebook"})
  public void Facebook_002_noEmailPerms() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    FacebookMainPageObject fbLogin = base.openFacebookMainPage();
    FacebookUserPageObject userFB;
    userFB = fbLogin.login(credentials.emailFB, credentials.passwordFB);
    userFB.verifyPageLogo();
    SignUpPageObject signUp = userFB.openSpecialSignUpPage(wikiURL);
    FacebookSignupModalComponentObject fbModal = signUp.clickFacebookSignUp();
    fbModal.acceptWikiaAppPolicyNoEmail();
    String userName = "QA" + signUp.getTimeStamp();
    String password = "Pass" + signUp.getTimeStamp();
    String email = credentials.emailQaart2;
    String emailPassword = credentials.emailPasswordQaart2;
    MailFunctions.deleteAllEmails(email, emailPassword);
    fbModal.typeUserName(userName);
    fbModal.typePassword(password);
    fbModal.typeEmail(email);
    fbModal.createAccount();
    signUp.verifyUserLoggedIn(userName);
    signUp.verifyEmailNotConfirmed();
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      PageObjectLogging.log("noEmailPerms", e.getMessage(), false);
    }
    AlmostTherePageObject almostThere = new AlmostTherePageObject(driver);
    ConfirmationPageObject confirmation = almostThere.enterActivationLink(email, emailPassword, wikiURL);
    confirmation.typeInUserName(userName);
    confirmation.typeInPassword(password);
    UserProfilePageObject
            userProfile =
            confirmation.clickSubmitButton(email, emailPassword);
    userProfile.verifyUserLoggedIn(userName);
    userProfile.logOut(driver);
    SpecialUserLoginPageObject login = base.openSpecialUserLogin(wikiURL);
    login.login(userName, password);
    login.logOut(driver);
    SignUpPageObject signUp2 = userFB.openSpecialSignUpPage(wikiURL);
    FacebookSignupModalComponentObject fbModal2 = signUp2.clickFacebookSignUp();
    signUp2.verifyUserLoggedIn(userName);
  }
}
