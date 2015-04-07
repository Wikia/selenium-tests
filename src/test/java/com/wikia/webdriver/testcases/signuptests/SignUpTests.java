package com.wikia.webdriver.testcases.signuptests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.toolbars.CustomizedToolbarComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiLogInSignUpPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookMainPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookUserPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.RemoveFacebookPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.AlmostTherePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.ConfirmationPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.SignUpPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.UserProfilePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.login.SpecialUserLoginPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject.tabNames;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Calendar;

/*
 * 1. Attempt to sign up wrong blurry word, 2. Attempt to sign up of too young user, 3. Attempt to
 * sign up with existing user name, 4. Sign up, 5. Sign up during CNW process, 6. Login in using not
 * verified user
 */
public class SignUpTests extends NewTestTemplate {

  private static String userName;
  private static String password;
  Credentials credentials = config.getCredentials();
  File captchaFile = config.getCaptchaFile();

  @Test(groups = {"SignUp_001", "SignUp"})
  public void SignUp_001_captchaNotChecked_QAART_563() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SignUpPageObject signUp = base.openSpecialSignUpPage(wikiURL);
    signUp.typeUserName(signUp.getTimeStamp());
    signUp.typeEmail(credentials.emailQaart1);
    signUp.typePassword(signUp.getTimeStamp());
    signUp.enterBirthDate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
        PageContent.WIKI_SIGN_UP_BIRTHYEAR);
    signUp.submit();
    signUp.verifyCaptchaInvalidMessage();
  }

  @Test(groups = {"SignUp_002", "SignUp"})
  public void SignUp_002_tooYoungUser() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SignUpPageObject signUp = base.openSpecialSignUpPage(wikiURL);
    signUp.typeUserName(signUp.getTimeStamp());
    signUp.typeEmail(credentials.emailQaart1);
    signUp.typePassword(signUp.getTimeStamp());
    Calendar currentDate = Calendar.getInstance();
    signUp.enterBirthDate(
        // +1 because months are numerated from 0
        Integer.toString(currentDate.get(Calendar.MONTH) + 1),
        Integer.toString(currentDate.get(Calendar.DAY_OF_MONTH)),
        Integer.toString(currentDate.get(Calendar.YEAR) - PageContent.MIN_AGE));
    signUp.verifyTooYoungMessage();
  }

  @Test(groups = {"SignUp_003", "SignUp"})
  public void SignUp_003_existingUserName() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SignUpPageObject signUp = base.openSpecialSignUpPage(wikiURL);
    signUp.typeUserName(credentials.userName);
    signUp.verifyUserExistsMessage();
  }

  @Test(groups = {"SignUp_004", "SignUp", "Smoke4"})
  public void SignUp_004_signup_SOC_599() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SignUpPageObject signUp = base.openSpecialSignUpPage(wikiURL);
    signUp.disableCaptcha();
    String userName = "User" + signUp.getTimeStamp();
    String password = "Pass" + signUp.getTimeStamp();
    String email = credentials.emailQaart2;
    String emailPassword = credentials.emailPasswordQaart2;

    signUp.typeEmail(email);
    signUp.typeUserName(userName);
    signUp.typePassword(password);
    signUp.enterBirthDate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
        PageContent.WIKI_SIGN_UP_BIRTHYEAR);
    AlmostTherePageObject almostTherePage = signUp.submit(email, emailPassword);
    almostTherePage.verifyAlmostTherePage();
    ConfirmationPageObject confirmPageAlmostThere =
        almostTherePage.enterActivationLink(email, emailPassword, wikiURL);
    confirmPageAlmostThere.typeInUserName(userName);
    confirmPageAlmostThere.typeInPassword(password);
    UserProfilePageObject userProfile =
        confirmPageAlmostThere.clickSubmitButton(email, emailPassword);
    userProfile.verifyUserLoggedIn(userName);
    CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
    toolbar.verifyUserToolBar();
    PreferencesPageObject preferences = userProfile.openSpecialPreferencesPage(wikiURL);
    preferences.selectTab(tabNames.EMAIL);
    preferences.verifyEmailMeSection();
  }

  @Test(groups = {"SignUp_005_Forced_Signup_CNW", "SignUp"})
  public void SignUp_005_forced_signup_CNW_QAART_513() {
    HomePageObject home = new HomePageObject(driver);
    home.openWikiPage(wikiCorporateURL);
    CreateNewWikiPageObjectStep1 createNewWiki1 = home.startAWiki(wikiCorporateURL);
    createNewWiki1.disableCaptcha();
    String wikiName = createNewWiki1.getWikiName();
    createNewWiki1.typeInWikiName(wikiName);
    createNewWiki1.verifySuccessIcon();
    CreateNewWikiLogInSignUpPageObject cnwSignUpPage = createNewWiki1.submitToLogInSignUp();
    SignUpPageObject signUp = cnwSignUpPage.submitSignup();
    String userName = "User" + signUp.getTimeStamp();
    String password = "Pass" + signUp.getTimeStamp();
    String email = credentials.emailQaart2;
    String emailPassword = credentials.emailPasswordQaart2;

    signUp.typeEmail(email);
    signUp.typeUserName(userName);
    signUp.typePassword(password);
    signUp.enterBirthDate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
        PageContent.WIKI_SIGN_UP_BIRTHYEAR);
    AlmostTherePageObject almostTherePage = signUp.submit(email, emailPassword);
    almostTherePage.verifyAlmostTherePage();
    ConfirmationPageObject confirmPageAlmostThere =
        almostTherePage.enterActivationLink(email, emailPassword, wikiCorporateURL);
    confirmPageAlmostThere.typeInUserName(userName);
    confirmPageAlmostThere.typeInPassword(password);
    createNewWiki1 = confirmPageAlmostThere.CNWSubmitButton(email, emailPassword);
    createNewWiki1.verifyWikiName(wikiName);
  }

  @Test(groups = {"SignUp_006", "SignUp"})
  public void SignUp_006_loginNotVerifiedUser_SOC_603() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SignUpPageObject signUp = base.openSpecialSignUpPage(wikiURL);
    signUp.disableCaptcha();
    String userName = "User" + signUp.getTimeStamp();
    String password = "Pass" + signUp.getTimeStamp();
    String email = credentials.emailQaart2;
    String emailPassword = credentials.emailPasswordQaart2;

    signUp.typeEmail(email);
    signUp.typeUserName(userName);
    signUp.typePassword(password);
    signUp.enterBirthDate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
        PageContent.WIKI_SIGN_UP_BIRTHYEAR);
    AlmostTherePageObject almostTherePage = signUp.submit(email, emailPassword);
    almostTherePage.verifyAlmostTherePage();

    SpecialUserLoginPageObject login = base.openSpecialUserLogin(wikiURL);
    login.login(userName, password);
    almostTherePage.verifyUserLoggedIn(userName);
  }

  /**
   * pre-conditions: Facebook_001 test removes Wikia and Wikia Development App from Facebook
   * Facebook_001 test stored in TestCases/FacebookTests/FacebookTests.java path Steps: 1. Log in to
   * Facebook 2. Open finish signup with facebook modal 3. create and verify account 4. disconnect
   * created account from facebook
   */
  @Test(groups = {"SignUp_007", "SignUp", "Modals"})
  public void SignUp_007_signUpWithFacebook_QAART_564() {
    new RemoveFacebookPageObject(driver).removeWikiaApps(credentials.emailFB,
        credentials.passwordFB);
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.openWikiPage(wikiURL);
    FacebookMainPageObject fbLogin = base.openFacebookMainPage();
    FacebookUserPageObject userFB;
    userFB = fbLogin.login(credentials.emailFB, credentials.passwordFB);
    userFB.verifyPageLogo();
    SignUpPageObject signUp = userFB.navigateToSpecialSignUpPage(wikiURL);
    FacebookSignupModalComponentObject fbModal = signUp.clickFacebookSignUp();
    fbModal.acceptWikiaAppPolicy();
    userName = "QA" + signUp.getTimeStamp();
    password = "Pass" + signUp.getTimeStamp();
    fbModal.typeUserName(userName);
    fbModal.typePassword(password);
    fbModal.createAccount();
    base.openWikiPage(wikiURL);
    base.appendToUrl("noads=1");
    base.verifyUserLoggedIn(userName);
    base.logOut(wikiURL);
  }

  @AfterGroups(groups = {"SignUp_007"}, alwaysRun = true)
  public void disconnectFromFB() {
    startBrowser();
    if (userName != null) {
      WikiBasePageObject base = new WikiBasePageObject(driver);
      base.openWikiPage(wikiURL);
      base.logInCookie(userName, password, wikiURL);
      base.verifyUserLoggedIn(userName);
      PreferencesPageObject preferences = base.openSpecialPreferencesPage(wikiURL);
      preferences.selectTab(tabNames.FACEBOOK);
      preferences.disconnectFromFacebook();
    } else {
      stopBrowser();
    }
    driver.quit();
  }
}
