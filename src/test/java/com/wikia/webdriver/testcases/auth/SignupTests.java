package com.wikia.webdriver.testcases.auth;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.helpers.UserWithEmail;
import com.wikia.webdriver.common.core.helpers.UserWithEmailPool;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.common.users.CreateUser;
import com.wikia.webdriver.common.users.TestUser;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.old.SignupPageObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.AttachedRegisterPage;

import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.DetachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.DetachedSignInPage;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.util.Calendar;

import static  com.wikia.webdriver.common.core.Assertion.assertEquals;
import static  com.wikia.webdriver.common.core.Assertion.assertStringContains;

@Test(groups = {"auth-signup-desktop", "auth-signup-mobile"})
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
public class SignupTests extends NewTestTemplate {

  UserWithEmailPool userPool = new UserWithEmailPool();
  UserWithEmail user1 = userPool.getEmailOnlyUser1();
  UserWithEmail user2 = userPool.getEmailOnlyUser2();
  User existingUser = User.LOGIN_USER;

  public void anonCanNotSignUpIfYoungerThanTwelve() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openSpecialUserSignUpPage(wikiURL);
    AttachedRegisterPage register = new AttachedRegisterPage();
    register.typeUsername(register.getTimeStamp() + " some letters");
    register.typeEmailAddress(user1.getEmail());
    register.typePassword(register.getTimeStamp());
    Calendar currentDate = Calendar.getInstance();
    register.typeBirthdate(
        // +1 because months are numerated from 0
        Integer.toString(currentDate.get(Calendar.MONTH) + 1),
        Integer.toString(currentDate.get(Calendar.DAY_OF_MONTH)),
        Integer.toString(currentDate.get(Calendar.YEAR) - PageContent.MIN_AGE));
    register.submit();
    assertEquals(register.getError(), "We cannot complete your registration at this time");
  }

  public void anonCanNotSignUpIfTheUsernameAlreadyExists() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openSpecialUserSignUpPage(wikiURL);
    AttachedRegisterPage register = new AttachedRegisterPage();
    String password = "Pass" + register.getTimeStamp();
    register.typeEmailAddress(user2.getEmail());
    register.typeUsername(existingUser.getUserName());
    register.typePassword(password);
    register.typeBirthdate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
                           PageContent.WIKI_SIGN_UP_BIRTHYEAR);

    register.submit();
    assertStringContains(register.getError(), "Username is taken");

  }

  public void anonCanSignUpOnNewBaseAuthPageFromGlobalNav() {
    WikiBasePageObject base = new WikiBasePageObject();
    NavigationBar registerLink = new NavigationBar();
    DetachedRegisterPage register = new DetachedRegisterPage(registerLink.clickOnRegister());
    String userName = "User" + register.getTimeStamp();
    String password = "Pass" + register.getTimeStamp();
    register.typeEmailAddress(user2.getEmail());
    register.typeUsername(userName);
    register.typePassword(password);
    register.typeBirthdate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
                           PageContent.WIKI_SIGN_UP_BIRTHYEAR);

    register.submit();

    base.verifyUserLoggedIn(userName);
  }

  public void anonCanSignUpWithoutConfirmingVerificationEmail() {
    WikiBasePageObject base = new WikiBasePageObject();
    AttachedRegisterPage signUp = base.openSpecialUserSignUpPage(wikiURL);

    String userName = "User" + signUp.getTimeStamp();
    String password = "Pass" + signUp.getTimeStamp();
    AttachedRegisterPage register = new AttachedRegisterPage();
    register.typeEmailAddress(user2.getEmail());
    register.typeUsername(userName);
    register.typePassword(password);
    register.typeBirthdate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
                           PageContent.WIKI_SIGN_UP_BIRTHYEAR);
    register.submit();
    base.verifyUserLoggedIn(userName);
  }

  public void userCanLoginWithoutConfirmingVerificationEmail() {
    WikiBasePageObject base = new WikiBasePageObject();
    AttachedRegisterPage signUp = base.openSpecialUserSignUpPage(wikiURL);

    String userName = "User" + signUp.getTimeStamp();
    String password = "Pass" + signUp.getTimeStamp();
    AttachedRegisterPage register = new AttachedRegisterPage();
    register.typeEmailAddress(user2.getEmail());
    register.typeUsername(userName);
    register.typePassword(password);
    register.typeBirthdate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
                           PageContent.WIKI_SIGN_UP_BIRTHYEAR);
    register.submit();
    base.verifyUserLoggedIn(userName);
    base.logoutFromAnywhere();
    NavigationBar signInLink = new NavigationBar();
    DetachedSignInPage page = new DetachedSignInPage(signInLink.clickOnSignIn());
    page.login(userName, password);
    base.verifyUserLoggedIn(userName);
  }

  @Execute(onWikia = "ja.ja-test")
  public void anonCanSignUpWithUsernameContainingJapaneseSpecialCharacters() {
    WikiBasePageObject base = new WikiBasePageObject();
    AttachedRegisterPage signUp = base.openSpecialUserSignUpPage(wikiURL);
    base.disableCaptcha();
    String userName = "ユーザー" + signUp.getTimeStamp();
    String password = "パス" + signUp.getTimeStamp();

    AttachedRegisterPage register = new AttachedRegisterPage();
    register.typeEmailAddress(user2.getEmail());
    register.typeUsername(userName);
    register.typePassword(password);
    register.typeBirthdate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
      PageContent.WIKI_SIGN_UP_BIRTHYEAR);
    register.submit();
    base.verifyUserLoggedIn(userName);
  }

  private void init() {
    new Navigate().toPageByPath(MercurySubpages.MAIN_PAGE);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void successfulSignup() {
    init();
    signUp(new CreateUser().create()).verifyAvatarAfterSignup();
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonCanSignUpWithEmailAlreadyInUse() {
    init();
    signUp(new CreateUser().withEmail("qaart001@gmail.com").create()).verifyAvatarAfterSignup();
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void signupErrorUsernameTaken() {
    init();
    String userNameTaken = "bekcunning";

    signUp(new CreateUser().withName(userNameTaken).create()).verifyUsernameTakenError();
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void signupErrorBadPassword() {
    init();
    String random = "User" + DateTime.now().getMillis();

    signUp(new CreateUser().withName(random).withPass(random).create()).verifyPasswordError();
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void signupErrorTooYoungUser() {
    init();
    DateTime wrongBirthDate = new DateTime(2009, 12, 12, 12, 0, 0);

    signUp(new CreateUser().withBirthday(wrongBirthDate).create()).verifyBirthdateError();
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(onWikia = "ja.ja-test")
  public void japaneseUserSignup() {
    init();
    String japanName = "ユーザー" + DateTime.now().getMillis();
    String japanPAssword = "ユーザザー" + DateTime.now().getMillis();

    signUp(new CreateUser().withName(japanName).withPass(japanPAssword).create())
      .verifyAvatarAfterSignup();
  }

  private SignupPageObject signUp(TestUser user) {
    return new SignupPageObject(driver).openMobileSignupPage().signUp(user.getUserName(),
      user.getPassword(), user.getEmail(), user.getBirthdate());
  }
}
