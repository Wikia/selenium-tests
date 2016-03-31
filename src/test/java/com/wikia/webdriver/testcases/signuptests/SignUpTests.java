package com.wikia.webdriver.testcases.signuptests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.toolbars.CustomizedToolbarComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiLogInSignUpPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.AlmostTherePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.ConfirmationPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.SignUpPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.UserProfilePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.login.SpecialUserLoginPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject.tabNames;

import org.testng.annotations.Test;

import java.util.Calendar;

/**
 * 1. Attempt to sign up wrong blurry word, 2. Attempt to sign up of too young user, 3. Attempt to
 * sign up with existing user name, 4. Sign up, 5. Sign up during CNW process, 6. Login in using not
 * verified user 7. signup using facebook account 8. signup with japanese language
 */
public class SignUpTests extends NewTestTemplate {
  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"Signin_anonCanNotSignInWithoutFillingCaptcha", "SignUp"})
  public void anonCanNotSignInWithoutFillingCaptcha() {
    WikiBasePageObject base = new WikiBasePageObject();
    SignUpPageObject signUp = base.navigateToSpecialSignUpPage(wikiURL);
    signUp.typeUserName(signUp.getTimeStamp());
    signUp.typeEmail(credentials.emailQaart1);
    signUp.typePassword(signUp.getTimeStamp());
    signUp.enterBirthDate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
        PageContent.WIKI_SIGN_UP_BIRTHYEAR);
    signUp.submit();
    signUp.verifyCaptchaInvalidMessage();
  }

  @Test(groups = {"Signin_anonCanNotSignInIfSheIsYoungerThanTwelve", "SignUp"})
  public void anonCanNotSignInIfSheIsYoungerThanTwelve() {
    WikiBasePageObject base = new WikiBasePageObject();
    SignUpPageObject signUp = base.navigateToSpecialSignUpPage(wikiURL);
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

  @Test(groups = {"Signin_anonCanNotSignInIfTheUsernameAlreadyExists", "SignUp"})
  public void anonCanNotSignInIfTheUsernameAlreadyExists() {
    WikiBasePageObject base = new WikiBasePageObject();
    SignUpPageObject signUp = base.navigateToSpecialSignUpPage(wikiURL);
    signUp.typeUserName(credentials.userName);
    signUp.verifyUserExistsMessage();
  }

  @Test(groups = {"Signin_anonCanSignIn", "SignUp", "Smoke4"})
  public void anonCanSignIn() {
    WikiBasePageObject base = new WikiBasePageObject();
    SignUpPageObject signUp = base.navigateToSpecialSignUpPage(wikiURL);
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

  @Test(groups = {"Signin_anonCanSignInWhenCreatingNewWiki", "SignUp"})
  public void anonCanSignInWhenCreatingNewWiki() {
    CreateNewWikiPageObjectStep1 createNewWiki1 = new CreateNewWikiPageObjectStep1(driver).open();
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

  @Test(groups = {"Signin_userCanLoginWithoutConfirmingVerificationEmail", "SignUp"})
  public void userCanLoginWithoutConfirmingVerificationEmail() {
    WikiBasePageObject base = new WikiBasePageObject();
    SignUpPageObject signUp = base.navigateToSpecialSignUpPage(wikiURL);
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

  @Test(groups = {"Signin_anonCanSignInWithUsernameContainingJapaneseSpecialCharacters", "SignUp"})
  @Execute(onWikia = "ja.ja-test")
  @RelatedIssue(issueID = "QAART-744", comment = "Mail timeout causes the test to fail. Monitor the ticket status")
  public void anonCanSignInWithUsernameContainingJapaneseSpecialCharacters() {
    SignUpPageObject signUp = new WikiBasePageObject().navigateToSpecialSignUpPage(wikiURL);
    signUp.disableCaptcha();
    String userName = "ユーザー" + signUp.getTimeStamp();
    String password = "パス" + signUp.getTimeStamp();
    String email = credentials.emailQaart2;
    String emailPassword = credentials.emailPasswordQaart2;

    signUp.typeEmail(email);
    signUp.typeUserName(userName);
    signUp.typePassword(password);
    signUp.enterBirthDate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
        PageContent.WIKI_SIGN_UP_BIRTHYEAR);
    AlmostTherePageObject almostTherePage = signUp.submit(email, emailPassword);
    ConfirmationPageObject confirmPageAlmostThere =
        almostTherePage.enterActivationLink(email, emailPassword, wikiURL, "ja");
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

}
