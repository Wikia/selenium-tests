package com.wikia.webdriver.testcases.facebooktests;

import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
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

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = {"Facebook"})
public class FacebookTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();
  String emailFB;
  String passwordFB = credentials.passwordFB;

  /**
   * 1. Log in to facebook 2. Click facebook login on signup page 3. Deny permission to user's
   * facebook email address 4. manually enter email address and create account 5. confirm account
   * and login, 6. Verify user can login via facebook
   */
  @Test
  @UseUnstablePageLoadStrategy
  public void linkWithNewAccountNoEmailPermission() {
    new RemoveFacebookPageObject(driver).removeWikiaApps(emailFB, passwordFB);

    SignUpPageObject signUp = new SignUpPageObject(driver).open();
    FacebookSignupModalComponentObject fbModal = signUp.clickFacebookSignUp();

    String userName = "QA" + signUp.getTimeStamp();
    String password = "Pass" + signUp.getTimeStamp();
    String email = credentials.emailQaart2;
    String emailPassword = credentials.emailPasswordQaart2;
    fbModal.createAccountNoEmail(email, emailPassword, userName, password);

    AlmostTherePageObject almostThere = new AlmostTherePageObject(driver);
    almostThere.confirmAccountAndLogin(email, emailPassword, userName, password, wikiURL);
  }

  /**
   * 1. Click facebook login in dropdown menu 2. Enter existing wikia credentials to link facebook
   * and wikia accounts 3. login 4. Verify user can login via wikia username/pwd 5. Disconnect
   * facebook via prefs for cleanup
   */
  @Test
  @UseUnstablePageLoadStrategy
  public void linkWithExistingWikiaAccount() {
    new RemoveFacebookPageObject(driver).removeWikiaApps(emailFB, passwordFB).logOutFB();

    DropDownComponentObject dropDown = new DropDownComponentObject(driver);
    dropDown.openWikiPage(wikiURL);
    dropDown.appendToUrl("noads=1");
    dropDown.openDropDown();
    dropDown.logInViaFacebook(emailFB, passwordFB);

    FacebookSignupModalComponentObject fbModal = new FacebookSignupModalComponentObject(driver);
    fbModal.acceptWikiaAppPolicy();
    fbModal.loginExistingAccount(credentials.userName13, credentials.password13);
    dropDown.verifyUserLoggedIn(credentials.userName);
  }

  @Test
  @UseUnstablePageLoadStrategy
  public void connectUsingUserPreferences() {
    new RemoveFacebookPageObject(driver).removeWikiaApps(emailFB, passwordFB).logOutFB();

    PreferencesPageObject prefsPage = new PreferencesPageObject(driver).open();
    prefsPage.loginAs(User.USER);
    prefsPage.selectTab(PreferencesPageObject.tabNames.FACEBOOK);
    prefsPage.connectFacebook(emailFB, passwordFB);
    prefsPage.verifyFBButtonVisible();
    prefsPage.logOut(wikiURL);
    SignUpPageObject signUp = new SignUpPageObject(driver).open();
    signUp.clickFacebookSignUp();
    prefsPage.verifyUserLoggedIn(credentials.userName);
  }

  @Test
  @UseUnstablePageLoadStrategy
  public void signUpWithFacebook() {
    new RemoveFacebookPageObject(driver).removeWikiaApps(emailFB,
                                                         credentials.passwordFB).logOutFB();
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.openWikiPage(wikiURL);
    FacebookMainPageObject fbLogin = base.openFacebookMainPage();
    FacebookUserPageObject userFB;
    userFB = fbLogin.login(emailFB, credentials.passwordFB);
    userFB.verifyPageLogo();
    SignUpPageObject signUp = userFB.navigateToSpecialSignUpPage(wikiURL);
    FacebookSignupModalComponentObject fbModal = signUp.clickFacebookSignUp();
    fbModal.acceptWikiaAppPolicy();
    String userName = "QA" + signUp.getTimeStamp();
    String password = "Pass" + signUp.getTimeStamp();
    fbModal.typeUserName(userName);
    fbModal.typePassword(password);
    fbModal.createAccount();
    base.openWikiPage(wikiURL);
    base.appendToUrl("noads=1");
    base.verifyUserLoggedIn(userName);
    base.logOut(wikiURL);
  }

  @BeforeMethod(alwaysRun = true)
  public void setEnvironmentCredentials() {
    String env = Configuration.getEnv();
    switch (env) {
      case "prod":
      case "sandbox":
        emailFB = credentials.emailFB;
        break;
      case "preview":
        emailFB = credentials.emailFBPreview;
        break;
      default:
        emailFB = credentials.emailFBDev;
        break;
    }
  }
}
