package com.wikia.webdriver.testcases.desktop.auth;

import com.wikia.webdriver.common.contentpatterns.MobileSubpages;
import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.Helios;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.desktop.components.navigation.global.GlobalNavigation;
import com.wikia.webdriver.elements.communities.mobile.components.navigation.global.GlobalNavigationMobile;
import com.wikia.webdriver.elements.communities.mobile.pages.ArticlePage;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.PostsListPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.SignInPage;
import org.testng.annotations.Test;

import java.time.Instant;

import static com.wikia.webdriver.common.core.Assertion.assertTrue;
import static org.testng.Assert.assertFalse;

@Execute(onWikia = MobileWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
public class LoginTests extends NewTestTemplate {

  private static final User USER = User.USER_11;
  private static final User JAPANESE_USER = User.REGULAR_USER_JAPAN;

  private static final User STAFF = User.STAFF;

  private static final String DESKTOP = "auth-login-desktop";
  private static final String MOBILE = "auth-login-mobile";

  private static final String ERROR_MESSAGE = "We don't recognize these credentials. Your username may have been changed as a result of login system changes, adding -fduser or -gpuser as a suffix. Try again or register a new account.";

  private static final  String PASSWORD_FORM = "P@55_%s";

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCanCloseJoinPage() {
    ArticlePage article = openArticleOnMobile();
    String previousTitle = article.getArticleTitle();
    article
      .getGlobalNavigationMobile()
      .clickOnAnonAvatar()
      .close();

    Assertion.assertEquals(article.getArticleTitle(), previousTitle);
  }

  @Test(groups = DESKTOP)
  public void userCanLogInAsStaffOnDesktop() {
    ArticlePageObject article = openArticleOnDesktop();
    loginOnDesktopAs(STAFF);
    article.verifyUserLoggedIn(STAFF);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCanLogInAsStaffOnMobile() {
    ArticlePage article = openArticleOnMobile();
    loginOnMobileAs(STAFF);
    article.waitForPageReload();
    article.verifyUserLoggedIn(STAFF);
  }

  @Test(groups = DESKTOP)
  public void japaneseUserCanLogInOnDesktop() {
    ArticlePageObject article = openArticleOnDesktop();
    loginOnDesktopAs(JAPANESE_USER);
    article.verifyUserLoggedIn(JAPANESE_USER);
  }

  @Test(groups = DESKTOP)
  @Execute(onWikia = MobileWikis.DISCUSSIONS_5)
  public void userIsRedirectedToFeedsPageUponLogInFromFeedsPageOnDesktop() {
    PostsListPage feedsPage = new PostsListPage().open();
    loginOnDesktopFromDiscussionPageAs(USER);
    feedsPage.waitForPageReload();
    Assertion.assertStringContains(driver.getCurrentUrl(), String.format("%s/f", Configuration.getEnvType().getDomain()));
  }

  @Test(groups = MOBILE)
  @Execute(onWikia = MobileWikis.DISCUSSIONS_5)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userIsRedirectedToDiscussionPageUponLogInFromDiscussionPageOnMobile() {
    PostsListPage feedsPage = new PostsListPage().open();
    loginOnDiscussionMobilePageAs(USER);
    feedsPage.waitForPageReload();
    Assertion.assertStringContains(driver.getCurrentUrl(), String.format("%s/f", Configuration.getEnvType().getDomain()));
  }

  @Test(groups = DESKTOP)
  public void passwordTogglerChangesPasswordVisibilityOnDesktop() {
    SignInPage signInPage = openLoginPageFromGlobalnavOnDesktop();
    signInPage.typePassword(USER.getPassword());

    Assertion.assertTrue(signInPage.isPasswordMasked(), "password should be masked");
    signInPage.togglePasswordVisibility();
    assertFalse(signInPage.isPasswordMasked(), "password should be readable");
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void passwordTogglerChangesPasswordVisibilityOnMobile() {
    openArticleOnMobile();
    SignInPage signInPage = navigateToSignInOnMobile();
    signInPage.typePassword(USER.getPassword());

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
    article.waitForPageReload();
    assertTrue(article.getGlobalNavigation().isUserLoggedOut());
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
  public void invalidPasswordErrorMessagesIsPresentedOnDesktop() {
    SignInPage signIn = openLoginPageFromGlobalnavOnDesktop();
    String invalidPassword = String.format(PASSWORD_FORM, Instant.now().getEpochSecond());
    signIn.login(USER.getUserName(), invalidPassword);
    Assertion.assertEquals(signIn.getError(), ERROR_MESSAGE);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void invalidPasswordErrorMessagesIsPresentedOnMobile() {
    openArticleOnMobile();
    SignInPage signIn = navigateToSignInOnMobile();
    String invalidPassword = String.format(PASSWORD_FORM, Instant.now().getEpochSecond());
    signIn.login(USER.getUserName(), invalidPassword);
    Assertion.assertEquals(signIn.getError(), ERROR_MESSAGE);
  }

  /**
   * HELPER METHODS
   */

  private ArticlePage openArticleOnMobile() {
    return new ArticlePage().open(MobileSubpages.MAIN_PAGE);
  }

  private ArticlePageObject openArticleOnDesktop() {
    return new ArticlePageObject().open(MobileSubpages.MAIN_PAGE);
  }

  private SignInPage navigateToSignInOnMobile() {
    return new GlobalNavigationMobile().clickOnAnonAvatar().navigateToRegister().navigateToSignIn();
  }

  private SignInPage openLoginPageFromGlobalnavOnDesktop() {
    return new GlobalNavigation().clickOnSignIn();
  }

  private void loginOnDesktopAs(User user) {
    openLoginPageFromGlobalnavOnDesktop().login(user);
  }

  private void loginOnDesktopFromDiscussionPageAs(User user) {
    new GlobalNavigation().clickOnSignIn().login(user);
  }

  private void loginOnMobileAs(User user) {
    navigateToSignInOnMobile().login(user);
  }

  private void loginOnDiscussionMobilePageAs(User user) {
    navigateToSignInOnMobile().login(user);
  }

}
