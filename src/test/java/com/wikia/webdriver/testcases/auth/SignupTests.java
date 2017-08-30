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
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.AttachedRegisterPage;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.util.Calendar;

import static com.wikia.webdriver.common.core.Assertion.assertEquals;
import static com.wikia.webdriver.common.core.Assertion.assertStringContains;

@Test(groups = {"auth-signup-desktop", "auth-signup-mobile"})
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
public class SignupTests extends NewTestTemplate {

  private UserWithEmailPool userPool = new UserWithEmailPool();
  private UserWithEmail user1 = userPool.getEmailOnlyUser1();
  private UserWithEmail user2 = userPool.getEmailOnlyUser2();
  private User existingUser = User.LOGIN_USER;

  public void newUserCanSignUpDesktop() {
    init();
    signUp(new CreateUser().create()).verifyAvatarAfterSignup();
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void newUserCanSignUpMobile() {
    init();
    signUp(new CreateUser().create()).verifyAvatarAfterSignup();
  }

  public void userCanSignUpWithExistingEmailDesktop() {
    init();
    signUp(new CreateUser().withEmail("qaart001@gmail.com").create()).verifyAvatarAfterSignup();
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCanSignUpWithExistingEmailMobile() {
    init();
    signUp(new CreateUser().withEmail("qaart001@gmail.com").create()).verifyAvatarAfterSignup();
  }

  public void userCannotSignUpWithExistingUsernameDesktop() {
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

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotSignUpWithExistingUsernameMobile() {
    init();
    String userNameTaken = "bekcunning";

    signUp(new CreateUser().withName(userNameTaken).create()).verifyUsernameTakenError();
  }

  public void userCannotSignUpWithPasswordMatchingUsernameDesktop() {
    init();
    String random = "User" + DateTime.now().getMillis();

    signUp(new CreateUser().withName(random).withPass(random).create()).verifyPasswordError();
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotSignUpWithPasswordMatchingUsernameMobile() {
    init();
    String random = "User" + DateTime.now().getMillis();

    signUp(new CreateUser().withName(random).withPass(random).create()).verifyPasswordError();
  }

  public void userCannotSignUpWithMismatchedPasswordDesktop() {

  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotSignUpWithMismatchedPasswordMobile() {

  }

  public void userCannotSignUpWhenTooYoungDesktop() {
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

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotSignUpWhenTooYoungMobile() {
    init();
    DateTime wrongBirthDate = new DateTime(2009, 12, 12, 12, 0, 0);

    signUp(new CreateUser().withBirthday(wrongBirthDate).create()).verifyBirthdateError();
  }

  @Execute(onWikia = "ja.ja-test")
  public void userWithSpecialCharactersInUsernameCanSignUpDesktop() {
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

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(onWikia = "ja.ja-test")
  public void userWithSpecialCharactersInUsernameCanSignUpMobile() {
    init();
    String japanName = "ユーザー" + DateTime.now().getMillis();
    String japanPAssword = "ユーザザー" + DateTime.now().getMillis();

    signUp(new CreateUser().withName(japanName).withPass(japanPAssword).create())
      .verifyAvatarAfterSignup();
  }

  public void userIsRedirectedToDiscussionPageUponSignUpFromDiscussionPageDesktop() {

  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userIsRedirectedToDiscussionPageUponSignUpFromDiscussionPageMobile() {

  }

  public void passwordTogglerChangesPasswordVisibilityDesktop() {

  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void passwordTogglerChangesPasswordVisibilityMobile() {

  }

  /**
   * HELPER METHODS
   */

  private SignupPageObject signUp(TestUser user) {
    return new SignupPageObject(driver).openMobileSignupPage().signUp(user.getUserName(),
      user.getPassword(), user.getEmail(), user.getBirthdate());
  }

  private void init() {
    new Navigate().toPageByPath(MercurySubpages.MAIN_PAGE);
  }

}
