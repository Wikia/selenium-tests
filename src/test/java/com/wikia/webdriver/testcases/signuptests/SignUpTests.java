package com.wikia.webdriver.testcases.signuptests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.loginAndSignup.RegisterArea;
import com.wikia.webdriver.elements.oasis.components.notifications.BannerNotifications;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.componentobject.toolbars.CustomizedToolbarComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
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
@Test(groups = {"auth-register"})
public class SignUpTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"Signup_anonCanNotSignUpIfSheIsYoungerThanTwelve", "SignUp"})
  @RelatedIssue(issueID = "SOC-2670", comment = "fails after product change, SOC team on it")
  public void anonCanNotSignUpIfSheIsYoungerThanTwelve() {
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

  @Test(groups = {"Signup_anonCanNotSignUpIfTheUsernameAlreadyExists", "SignUp"})
  @RelatedIssue(issueID = "SOC-2670", comment = "fails after product change, SOC team on it")
  public void anonCanNotSignUpIfTheUsernameAlreadyExists() {
    WikiBasePageObject base = new WikiBasePageObject();
    SignUpPageObject signUp = base.navigateToSpecialSignUpPage(wikiURL);
    signUp.typeUserName(credentials.userName);
    signUp.verifyUserExistsMessage();
  }

  @Test(groups = "Signup_anonCanSignUpOnNewAuthModalFromGlobalNav")
  public void anonCanSignUpOnNewAuthModalFromGlobalNav(){
    WikiBasePageObject base = new WikiBasePageObject();
    NavigationBar registerLink = new NavigationBar(driver);
    registerLink.clickOnRegister();
    RegisterArea register = new RegisterArea(driver);
    register.switchToAuthModalHandle();
    String userName = "User" + register.getTimeStamp();
    String password = "Pass" + register.getTimeStamp();
    String email = credentials.emailQaart2;
    register.typeEmailAddress(email);
    register.typeUsername(userName);
    register.typePassword(password);
    register.typeBirthdate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
                           PageContent.WIKI_SIGN_UP_BIRTHYEAR);
    register.clickSignUpSubmitButton();

    base.verifyUserLoggedIn(userName);
  }

  @Test(groups = "Signup_anonCanSignUp")
  public void anonCanSignUpWithoutConfirmingVerificationEmail() {
    WikiBasePageObject base = new WikiBasePageObject();
    SignUpPageObject signUp = base.navigateToSpecialSignUpPage(wikiURL);

    String userName = "User" + signUp.getTimeStamp();
    String password = "Pass" + signUp.getTimeStamp();
    String email = credentials.emailQaart2;
    RegisterArea register = new RegisterArea(driver);
    register.typeEmailAddress(email);
    register.typeUsername(userName);
    register.typePassword(password);
    register.typeBirthdate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
                           PageContent.WIKI_SIGN_UP_BIRTHYEAR);
    register.clickSignUpSubmitButton();
    base.verifyUserLoggedIn(userName);
    BannerNotifications notification = new BannerNotifications();
    Assertion.assertEquals(
        "Oh no! Your email address has not yet been confirmed. You should have a confirmation message in your inbox. Didn't get it? Click here and we'll send a new one. If you need to change your address, head to your Preferences page.",
        notification.getBannerNotificationTextEmailNotConfirmed());
    CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
    toolbar.verifyUserToolBar();
  }

  @Test(groups = {"Signup_userCanLoginWithoutConfirmingVerificationEmail", "SignUp"})
  @RelatedIssue(issueID = "SOC-2670", comment = "fails after product change, SOC team on it")
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

    SpecialUserLoginPageObject login = base.openSpecialUserLoginOld(wikiURL);
    login.login(userName, password);
    almostTherePage.verifyUserLoggedIn(userName);
  }

  /**
   * pre-conditions: Facebook_001 test removes Wikia and Wikia Development App from Facebook
   * Facebook_001 test stored in TestCases/FacebookTests/FacebookTests.java path Steps: 1. Log in to
   * Facebook 2. Open finish signup with facebook modal 3. create and verify account 4. disconnect
   * created account from facebook
   */

  @Test(groups = {"Signup_anonCanSignUpWithUsernameContainingJapaneseSpecialCharacters", "SignUp"})
  @Execute(onWikia = "ja.ja-test")
  @RelatedIssue(issueID = "QAART-744", comment = "Mail timeout causes the test to fail. Monitor the ticket status")
  public void anonCanSignUpWithUsernameContainingJapaneseSpecialCharacters() {
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
