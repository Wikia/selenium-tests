package com.wikia.webdriver.testcases.auth;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.*;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.DetachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.RegisterPage;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.LocalDate;

import static com.wikia.webdriver.common.core.Assertion.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
public class SignupTests extends NewTestTemplate {

  private static final String USERNAME_TAKEN_MSG = "Username is taken";
  private static final String PASSWORD_MATCHING_USERNAME_MSG = "Password and username cannot match";
  private static final String GENERIC_ERROR_MSG =
    "We cannot complete your registration at this time";

  private static final String DESKTOP = "auth-signup-desktop";
  private static final String MOBILE = "auth-signup-mobile";
  private static final String PASS_PATTERN = "pass_%s";
  private static final String USERNAME_PATTERN = "QA%s";

  private UserWithEmail userWithEmail = UserWithEmailFactory.getEmailOnlyUser1();
  private User existingUser = User.LOGIN_USER;
  private static final LocalDate BIRTH_DATE = LocalDate.of(1993, 3, 19);

  @Test(groups = DESKTOP)
  public void newUserCanSignUpDesktop() {
    performSignUpOnDesktopAs(createNewUser());
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void newUserCanSignUpMobile() {
    performSignUpOnMobileAs(createNewUser());
  }

  @Test(groups = DESKTOP)
  public void userCanSignUpWithExistingEmailDesktop() {
    performSignUpOnDesktopAs(createUserWithExistingEmail());
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCanSignUpWithExistingEmailMobile() {
    performSignUpOnMobileAs(createUserWithExistingEmail());
  }

  @Test(groups = DESKTOP)
  public void userCannotSignUpWithExistingUsernameDesktop() {
    RegisterPage form = performSignUpExpectingFailureOnDesktopAs(createUserWithExistingUsername());
    assertEquals(form.getError(), USERNAME_TAKEN_MSG);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotSignUpWithExistingUsernameMobile() {
    RegisterPage form = performSignUpExpectingFailureOnMobileAs(createUserWithExistingUsername());
    assertEquals(form.getError(), USERNAME_TAKEN_MSG);
  }

  @Test(groups = DESKTOP)
  public void userCannotSignUpWithPasswordMatchingUsernameDesktop() {
    RegisterPage form = performSignUpExpectingFailureOnDesktopAs(createUserWithPasswordMatchingUsername());
    assertEquals(form.getError(), PASSWORD_MATCHING_USERNAME_MSG);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotSignUpWithPasswordMatchingUsernameMobile() {
    RegisterPage form = performSignUpExpectingFailureOnMobileAs(createUserWithPasswordMatchingUsername());
    assertEquals(form.getError(), PASSWORD_MATCHING_USERNAME_MSG);
  }

  @Test(groups = DESKTOP)
  public void userCannotSignUpWhenTooYoungDesktop() {
    RegisterPage form = performSignUpExpectingFailureOnDesktopAs(createTooYoungUser());
    assertEquals(form.getError(), GENERIC_ERROR_MSG);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotSignUpWhenTooYoungMobile() {
    RegisterPage form = performSignUpExpectingFailureOnMobileAs(createTooYoungUser());
    assertEquals(form.getError(), GENERIC_ERROR_MSG);
  }

  @Test(groups = DESKTOP)
  public void userWithSpecialCharactersInUsernameCanSignUpDesktop() {
    performSignUpOnDesktopAs(createNewUserWithSpecialCharacters());
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userWithSpecialCharactersInUsernameCanSignUpMobile() {
    performSignUpOnMobileAs(createNewUserWithSpecialCharacters());
  }

  @Test(groups = DESKTOP)
  @Execute(onWikia = MercuryWikis.DISCUSSIONS_2)
  public void userIsRedirectedToDiscussionPageUponSignUpFromDiscussionPageDesktop() {
    PostsListPage discussionPage = new PostsListPage().open();
    signUpOnDesktopFromDiscussionPageAs(createNewUser());
    assertTrue(discussionPage.waitForPageReload().isStringInURL(PostsListPage.PATH),
      "User should be redirected to discussion post list view upon sign up");
  }

  @Test(groups = MOBILE)
  @Execute(onWikia = MercuryWikis.DISCUSSIONS_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userIsRedirectedToDiscussionPageUponSignUpFromDiscussionPageMobile() {
    PostsListPage discussionPage = new PostsListPage().open();
    signUpOnDiscussionMobilePageAs(discussionPage, createNewUser());
    assertTrue(discussionPage.waitForPageReload().isStringInURL(PostsListPage.PATH),
      "User should be redirected to discussion post list view upon sign up");
  }

  @Test(groups = DESKTOP)
  public void passwordTogglerChangesPasswordVisibilityDesktop() {
    performPasswordToggleTest(openSignUpModalOnDesktop());
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void passwordTogglerChangesPasswordVisibilityMobile() {
    performPasswordToggleTest(navigateToSignUpOnMobile());
  }

  /**
   * HELPER METHODS
   */

  private SignUpUser createNewUser() {

    return new SignUpUser(
      String.format(USERNAME_PATTERN, Instant.now().getEpochSecond()),
      getEmailAlias(userWithEmail.getEmail()),
      String.format(PASS_PATTERN, Instant.now().getEpochSecond()),
      BIRTH_DATE
    );
  }

  private SignUpUser createUserWithExistingEmail() {
    return new SignUpUser(
      String.format(USERNAME_PATTERN, Instant.now().getEpochSecond()),
      userWithEmail.getEmail(),
      String.format(PASS_PATTERN, Instant.now().getEpochSecond()),
      BIRTH_DATE
    );
  }

  private SignUpUser createUserWithExistingUsername() {
    return new SignUpUser(
      existingUser.getUserName(),
      getEmailAlias(userWithEmail.getEmail()),
      String.format(PASS_PATTERN, Instant.now().getEpochSecond()),
      BIRTH_DATE
    );
  }

  private SignUpUser createUserWithPasswordMatchingUsername() {
    String username = String.format(USERNAME_PATTERN, Instant.now().getEpochSecond());
    return new SignUpUser(
      username,
      getEmailAlias(userWithEmail.getEmail()),
      username,
      BIRTH_DATE
    );
  }

  private SignUpUser createTooYoungUser() {
    return new SignUpUser(
      String.format(USERNAME_PATTERN, Instant.now().getEpochSecond()),
      getEmailAlias(userWithEmail.getEmail()),
      String.format(PASS_PATTERN, Instant.now().getEpochSecond()),
      LocalDate.now().minusYears(11)
    );
  }

  private SignUpUser createNewUserWithSpecialCharacters() {
    return new SignUpUser(
      String.format("ユーザー%s", Instant.now().getEpochSecond()),
      getEmailAlias(userWithEmail.getEmail()),
      String.format("ユーザザー_%s", Instant.now().getEpochSecond()),
      BIRTH_DATE
    );
  }

  private String getEmailAlias(String email) {
    return email.replace("@", String.format("+%s@", Instant.now().getEpochSecond()));
  }

  private ArticlePage openArticleOnMobile() {
    return new ArticlePage().open(MercurySubpages.MAIN_PAGE);
  }

  private ArticlePageObject openArticleOnDesktop() {
    return new ArticlePageObject().openArticleByPath(MercurySubpages.MAIN_PAGE);
  }

  private RegisterPage navigateToSignUpOnMobile() {
    return navigateToSignUpOnMobile(openArticleOnMobile().getTopBar());
  }

  private RegisterPage navigateToSignUpOnMobile(TopBar topBar) {
    return topBar
      .openNavigation()
      .clickOnSignInRegisterButton()
      .navigateToSignUp();
  }

  private RegisterPage openSignUpModalOnDesktop() {
    return new DetachedRegisterPage(new NavigationBar().clickOnRegister());
  }

  private void signUpOnDesktopAs(SignUpUser user) {
    openSignUpModalOnDesktop().signUp(user);
  }

  private void signUpOnDesktopFromDiscussionPageAs(SignUpUser user) {
    new NavigationBar().clickOnRegister().signUp(user);
  }

  private void signUpOnMobileAs(ArticlePage article, SignUpUser user) {
    navigateToSignUpOnMobile(article.getTopBar()).signUp(user);
  }

  private void signUpOnDiscussionMobilePageAs(PostsListPage page, SignUpUser user) {
    navigateToSignUpOnMobile(page.getTopBar()).signUp(user);
  }

  private void performSignUpOnMobileAs(SignUpUser user) {
    ArticlePage article = openArticleOnMobile();
    signUpOnMobileAs(article, user);
    article.waitForPageReload();
    article.verifyUserLoggedIn(user.getUsername());
  }

  private void performSignUpOnDesktopAs(SignUpUser user) {
    ArticlePageObject article = openArticleOnDesktop();
    signUpOnDesktopAs(user);
    article.verifyUserLoggedIn(user.getUsername());
  }

  private RegisterPage performSignUpExpectingFailureOnDesktopAs(SignUpUser user) {
    RegisterPage form = openSignUpModalOnDesktop().fillForm(user);
    form.submit();
    return form;
  }

  private RegisterPage performSignUpExpectingFailureOnMobileAs(SignUpUser user) {
    RegisterPage form = navigateToSignUpOnMobile();
    form.fillForm(user).submit();
    return form;
  }

  private void performPasswordToggleTest(RegisterPage signUpPage) {
    signUpPage.typePassword(existingUser.getPassword());

    assertTrue(signUpPage.isPasswordMasked(), "password should be masked");
    signUpPage.togglePasswordVisibility();
    assertFalse(signUpPage.isPasswordMasked(), "password should be readable");
  }

}
