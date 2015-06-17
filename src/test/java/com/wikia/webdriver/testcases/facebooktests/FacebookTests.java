package com.wikia.webdriver.testcases.facebooktests;

import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.dropdowncomponentobject.DropDownComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookMainPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookUserPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.RemoveFacebookPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.AlmostTherePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.SignUpPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;

import org.testng.annotations.Test;

public class FacebookTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();
  private String userName;
  private String password;

  /**
   * dependent method: Signup_007_signUpWithFacebook and Facebook_002_noEmailPerms Steps: 1. Log in
   * to facebook 2. Open Facebook settings 3. Remove Wikia and Wikia Development App
   */


  /**
   * depends on method Facebook_001_removeWikiaApps Steps: 1. Log in to facebook 2. Click facebook
   * login on signup page 3. Deny permission to user's facebook email address 4. manually enter
   * email address and create account 5. confirm account and login, 6. Verify user can login via
   * facebook
   */
  @RelatedIssue(issueID = "QAART-624", comment = "Automation test is broken. Please test manually")
  @Test(groups = {"Facebook_001", "Facebook", "Facebook_002"})
  @UseUnstablePageLoadStrategy
  public void Facebook_001_noEmailPerms() {
    new RemoveFacebookPageObject(driver).removeWikiaApps(credentials.emailFB,
        credentials.passwordFB);
    WikiBasePageObject base = new WikiBasePageObject(driver);
    FacebookMainPageObject fbLogin = base.openFacebookMainPage();
    FacebookUserPageObject userFB = fbLogin.login(credentials.emailFB, credentials.passwordFB);
    userFB.verifyPageLogo();

    SignUpPageObject signUp = userFB.openSpecialSignUpPage(wikiURL);
    FacebookSignupModalComponentObject fbModal = signUp.clickFacebookSignUp();

    userName = "QA" + signUp.getTimeStamp();
    password = "Pass" + signUp.getTimeStamp();
    String email = credentials.emailQaart2;
    String emailPassword = credentials.emailPasswordQaart2;
    fbModal.createAccountNoEmail(email, emailPassword, userName, password);
    signUp.verifyUserLoggedIn(userName);

    AlmostTherePageObject almostThere = new AlmostTherePageObject(driver);
    almostThere.confirmAccountAndLogin(email, emailPassword, userName, password, wikiURL);
    almostThere.logOut(wikiURL);
  }

  /**
   * depends on method Facebook_001_removeWikiaApps Steps: 1. Click facebook login in dropdown menu
   * 2. Enter existing wikia credentials to link facebook and wikia accounts 3. login 4. Verify user
   * can login via wikia username/pwd 5. Disconnect facebook via prefs for cleanup
   */
  @Test(groups = {"Facebook_002", "Facebook", "Facebook_003"},
      dependsOnMethods = {"Facebook_001_noEmailPerms"})
  @UseUnstablePageLoadStrategy
  public void Facebook_002_linkFBwithWikia() {
    new RemoveFacebookPageObject(driver).removeWikiaApps(credentials.emailFB,
        credentials.passwordFB);
    String winHandleBefore = driver.getWindowHandle();

    DropDownComponentObject dropDown = new DropDownComponentObject(driver);
    dropDown.openWikiPage(wikiURL);
    dropDown.appendToUrl("noads=1");
    dropDown.openDropDown();
    dropDown.logInViaFacebook(credentials.emailFB, credentials.passwordFB);

    Object[] windows = driver.getWindowHandles().toArray();
    driver.switchTo().window(windows[0].toString());
    FacebookSignupModalComponentObject fbModal =
        new FacebookSignupModalComponentObject(driver, windows[0].toString());
    fbModal.acceptWikiaAppPolicy();
    fbModal.loginExistingAccount(credentials.userName, credentials.password);
    fbModal.verifyUserLoggedIn(credentials.userName);
    WikiBasePageObject base = new WikiBasePageObject(driver);
    PreferencesPageObject disconnectFBPrefs = base.openSpecialPreferencesPage(wikiURL);
    disconnectFBPrefs.selectTab(PreferencesPageObject.tabNames.FACEBOOK);
    disconnectFBPrefs.disconnectFromFacebook();
  }

  @Test(groups = {"Facebook_003", "Facebook"}, dependsOnMethods = {"Facebook_002_linkFBwithWikia"})
  @UseUnstablePageLoadStrategy
  public void Facebook_003_connectFBfromPrefs() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    PreferencesPageObject prefsPage = base.openSpecialPreferencesPage(wikiURL);
    prefsPage.selectTab(PreferencesPageObject.tabNames.FACEBOOK);
    prefsPage.connectFacebook(credentials.emailFB, credentials.passwordFB);
    prefsPage.verifyFBButtonVisible();
    base.logOut(wikiURL);
    SignUpPageObject signUp = base.openSpecialSignUpPage(wikiURL);
    signUp.clickFacebookSignUp();
    base.verifyUserLoggedIn(credentials.userName);
    PreferencesPageObject disconnectFBPrefs = base.openSpecialPreferencesPage(wikiURL);
    disconnectFBPrefs.selectTab(PreferencesPageObject.tabNames.FACEBOOK);
    disconnectFBPrefs.disconnectFromFacebook();
  }

}
