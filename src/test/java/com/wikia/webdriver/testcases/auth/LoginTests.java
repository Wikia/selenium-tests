package com.wikia.webdriver.testcases.auth;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.Helios;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.DetachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.SignInPage;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static com.wikia.webdriver.common.core.Assertion.assertTrue;
import static org.testng.Assert.assertFalse;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
public class LoginTests extends NewTestTemplate {

  private User user = User.LOGIN_USER;
  private User japaneseUser = User.USER_JAPAN;
  private User staff = User.LOGIN_STAFF;

  private static final String DESKTOP = "auth-login-desktop";
  private static final String MOBILE = "auth-login-mobile";

  private static final String ERROR_MESSAGE =
    "We don't recognize these credentials. Try again or register a new account.";

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCanCloseJoinPage() {
    ArticlePage article = openArticleOnMobile();
    String title = article.getArticleTitle();
    article
      .getTopBar()
      .openNavigation()
      .clickOnSignInRegisterButton()
      .close();
    Assertion.assertEquals(article.getArticleTitle(), title);
  }

  @Test(groups = DESKTOP)
  public void userCanLogInAsStaffOnDesktop() {
    ArticlePageObject article = openArticleOnDesktop();
    loginOnDesktopAs(staff);
    article.verifyUserLoggedIn(staff);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCanLogInAsStaffOnMobile() {
    ArticlePage article = openArticleOnMobile();
    loginOnMobileAs(article, staff);
    article.waitForPageReload().verifyUserLoggedIn(staff);
  }

  @Test(groups = DESKTOP)
  public void japaneseUserCanLogInOnDesktop() {
    ArticlePageObject article = openArticleOnDesktop();
    loginOnDesktopAs(japaneseUser);
    article.verifyUserLoggedIn(japaneseUser);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void japaneseUserCanLogInOnMobile() {
    ArticlePage article = openArticleOnMobile();
    loginOnMobileAs(article, japaneseUser);
    article.waitForPageReload().verifyUserLoggedIn(japaneseUser);
  }

  @Test(groups = DESKTOP)
  @Execute(onWikia = MercuryWikis.DISCUSSIONS_5)
  public void userIsRedirectedToDiscussionPageUponLogInFromDiscussionPageOnDesktop() {
    PostsListPage discussionPage = new PostsListPage().open();
    loginOnDesktopFromDiscussionPageAs(user);
    assertTrue(discussionPage.waitForPageReload().isStringInURL(PostsListPage.FULL_PATH),
      "User should be redirected to discussion post list view upon log in");
  }

  @Test(groups = MOBILE)
  @Execute(onWikia = MercuryWikis.DISCUSSIONS_5)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userIsRedirectedToDiscussionPageUponLogInFromDiscussionPageOnMobile() {
    PostsListPage discussionPage = new PostsListPage().open();
    loginOnDiscussionMobilePageAs(discussionPage, user);
    assertTrue(discussionPage.waitForPageReload().isStringInURL(PostsListPage.FULL_PATH),
      "User should be redirected to discussion post list view upon log in");
  }

  @Test(groups = DESKTOP)
  public void passwordTogglerChangesPasswordVisibilityOnDesktop() {
    SignInPage signInPage = openLoginModalOnDesktop();
    signInPage.typePassword(user.getPassword());

    Assertion.assertTrue(signInPage.isPasswordMasked(), "password should be masked");
    signInPage.togglePasswordVisibility();
    assertFalse(signInPage.isPasswordMasked(), "password should be readable");
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void passwordTogglerChangesPasswordVisibilityOnMobile() {
    SignInPage signInPage = navigateToSignInOnMobile();
    signInPage.typePassword(user.getPassword());

    Assertion.assertTrue(signInPage.isPasswordMasked(), "password should be masked");
    signInPage.togglePasswordVisibility();
    assertFalse(signInPage.isPasswordMasked(), "password should be readable");
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.INVALIDATED_TOKEN_USER_1)
  public void userWithoutAValidTokenGetsLoggedOutOnDesktop() {
    ArticlePageObject article = openArticleOnDesktop();
    Helios.deleteAllTokens(User.INVALIDATED_TOKEN_USER_1);
    article.refreshPageAddingCacheBuster();
    assertTrue(article.waitForPageReload().getGlobalNavigation().isUserLoggedOut());
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.INVALIDATED_TOKEN_USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userWithoutAValidTokenGetsLoggedOutOnMobile() {
    ArticlePage article = openArticleOnMobile();
    Helios.deleteAllTokens(User.INVALIDATED_TOKEN_USER_2);
    article.refreshPageAddingCacheBuster();
    assertTrue(article.isUserLoggedOutMobile());
  }

  @Test(groups = DESKTOP)
  public void nonexistentUserCannotLogInOnDesktop() {
    SignInPage signIn = openLoginModalOnDesktop();
    String nonexistingUsername = String.format("QA_%s", LocalDateTime.now());
    signIn.login(nonexistingUsername, user.getPassword());
    Assertion.assertEquals(signIn.getError(), ERROR_MESSAGE);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void nonexistentUserCannotLogInOnMobile() {
    SignInPage signIn = navigateToSignInOnMobile();
    String nonexistingUsername = String.format("QA_%s", LocalDateTime.now());
    signIn.login(nonexistingUsername, user.getPassword());
    Assertion.assertEquals(signIn.getError(), ERROR_MESSAGE);
  }

  @Test(groups = DESKTOP)
  public void userCannotLogInWithInvalidPasswordOnDesktop() {
    SignInPage signIn = openLoginModalOnDesktop();
    String invalidPassword = String.format("P@55_%s", LocalDateTime.now());
    signIn.login(user.getUserName(), invalidPassword);
    Assertion.assertEquals(signIn.getError(), ERROR_MESSAGE);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotLogInWithInvalidPasswordOnMobile() {
    SignInPage signIn = navigateToSignInOnMobile();
    String invalidPassword = String.format("P@55_%s", LocalDateTime.now());
    signIn.login(user.getUserName(), invalidPassword);
    Assertion.assertEquals(signIn.getError(), ERROR_MESSAGE);
  }

  @Test(groups = DESKTOP)
  public void userCannotLogInWithBlankUsernameOnDesktop() {
    SignInPage signIn = openLoginModalOnDesktop();
    signIn.typeUsername("").typePassword(user.getPassword());
    assertFalse(signIn.isSubmitButtonEnabled(), "Submit button should be disabled");
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotLogInWithBlankUsernameOnMobile() {
    SignInPage signIn = navigateToSignInOnMobile();
    signIn.typeUsername("").typePassword(user.getPassword());
    assertFalse(signIn.isSubmitButtonEnabled(), "Submit button should be disabled");
  }

  @Test(groups = DESKTOP)
  public void userCannotLogInWithBlankPasswordOnDesktop() {
    SignInPage signIn = openLoginModalOnDesktop();
    signIn.typeUsername(user.getUserName()).typePassword("");
    assertFalse(signIn.isSubmitButtonEnabled(), "Submit button should be disabled");
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotLogInWithBlankPasswordOnMobile() {
    SignInPage signIn = navigateToSignInOnMobile();
    signIn.typeUsername(user.getUserName()).typePassword("");
    assertFalse(signIn.isSubmitButtonEnabled(), "Submit button should be disabled");
  }

  /**
   * HELPER METHODS
   */

  private ArticlePage openArticleOnMobile() {
    return new ArticlePage().open(MercurySubpages.MAIN_PAGE);
  }

  private ArticlePageObject openArticleOnDesktop() {
    return new ArticlePageObject().open(MercurySubpages.MAIN_PAGE);
  }

  private void loginOnDesktopAs(User user) {
    openLoginModalOnDesktop().login(user);
  }

  private void loginOnDesktopFromDiscussionPageAs(User user) {
    new NavigationBar().clickOnSignIn().login(user);
  }

  private SignInPage openLoginModalOnDesktop() {
    return new DetachedSignInPage(new NavigationBar().clickOnSignIn());
  }

  private void loginOnMobileAs(ArticlePage article, User user) {
    navigateToSignInOnMobile(article.getTopBar()).login(user);
  }

  private void loginOnDiscussionMobilePageAs(PostsListPage page, User user) {
    navigateToSignInOnMobile(page.getTopBar()).login(user);
  }

  private SignInPage navigateToSignInOnMobile() {
    return navigateToSignInOnMobile(openArticleOnMobile().getTopBar());
  }

  private SignInPage navigateToSignInOnMobile(TopBar topBar) {
    return topBar
      .openNavigation()
      .clickOnSignInRegisterButton()
      .navigateToSignIn();
  }

}
