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
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.DetachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.SignInPage;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static com.wikia.webdriver.common.core.Assertion.assertTrue;
import static org.testng.Assert.assertFalse;

@Test(groups = "auth-login")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
public class LoginTests extends NewTestTemplate {

  private User user = User.LOGIN_USER;
  private User japaneseUser = User.USER_JAPAN;
  private User staff = User.LOGIN_STAFF;

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCanCloseJoinPage() {
    ArticlePage article = openArticleOnMobile();
    String title = article.getArticleTitle();
    article
      .getTopbar()
      .openNavigation()
      .clickOnSignInRegisterButton()
      .close();
    Assertion.assertEquals(article.getArticleTitle(), title);
  }

  private ArticlePage openArticleOnMobile() {
    return new ArticlePage().open(MercurySubpages.MAIN_PAGE);
  }

  private ArticlePageObject openArticleOnDesktop() {
    return new ArticlePageObject().open(MercurySubpages.MAIN_PAGE);
  }
  public void userCanLogInAsStaffOnDesktop() {
    ArticlePageObject article = openArticleOnDesktop();
    loginOnDesktopAs(staff);
    article.verifyUserLoggedIn(staff);
  }

  private void loginOnDesktopAs(User user) {
    openLoginModalOnDesktop().login(user);
  }

  private SignInPage openLoginModalOnDesktop() {
    return new DetachedSignInPage(new NavigationBar().clickOnSignIn());
  }

  private void loginOnMobileAs(ArticlePage article, User user) {
    navigateToSignInOnMobile(article).login(user);
  }

  private SignInPage navigateToSignInOnMobile() {
    return navigateToSignInOnMobile(openArticleOnMobile());
  }

  private SignInPage navigateToSignInOnMobile(ArticlePage article) {
    return article
      .getTopbar()
      .openNavigation()
      .clickOnSignInRegisterButton()
      .navigateToSignIn();
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCanLogInAsStaffOnMobile() {
    ArticlePage article = openArticleOnMobile();
    loginOnMobileAs(article, staff);
    article.verifyUserLoggedIn(staff);
  }

  public void japaneseUserCanLogInOnDesktop() {
    ArticlePageObject article = openArticleOnDesktop();
    loginOnDesktopAs(japaneseUser);
    article.verifyUserLoggedIn(japaneseUser);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void japaneseUserCanLogInOnMobile() {
    ArticlePage article = openArticleOnMobile();
    loginOnMobileAs(article, japaneseUser);
    article.verifyUserLoggedIn(japaneseUser);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void passwordTogglerChangesPasswordVisibilityOnMobile() {
    SignInPage signInPage = navigateToSignInOnMobile();
    signInPage.typePassword(user.getPassword());

    Assertion.assertTrue(signInPage.isPasswordMasked(), "password should be masked");
    signInPage.togglePasswordVisibility();
    assertFalse(signInPage.isPasswordMasked(), "password should be readable");
  }

  public void passwordTogglerChangesPasswordVisibilityOnDesktop() {
    SignInPage signInPage = openLoginModalOnDesktop();
    signInPage.typePassword(user.getPassword());

    Assertion.assertTrue(signInPage.isPasswordMasked(), "password should be masked");
    signInPage.togglePasswordVisibility();
    assertFalse(signInPage.isPasswordMasked(), "password should be readable");
  }

  @Execute(asUser = User.INVALIDATED_TOKEN_USER_1)
  public void userWithoutAValidTokenGetsLoggedOutOnDesktop() {
    ArticlePageObject article = openArticleOnDesktop();
    Helios.deleteAllTokens(User.INVALIDATED_TOKEN_USER_1);
    article.refreshPageAddingCacheBuster();
    assertTrue(article.getGlobalNavigation().isUserLoggedOut());
  }

  @Execute(asUser = User.INVALIDATED_TOKEN_USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userWithoutAValidTokenGetsLoggedOutOnMobile() {
    ArticlePage article = openArticleOnMobile();
    Helios.deleteAllTokens(User.INVALIDATED_TOKEN_USER_2);
    article.refreshPageAddingCacheBuster();
    assertTrue(article.isUserLoggedOutMobile());
  }

  private static final String ERROR_MESSAGE =
    "We don't recognize these credentials. Try again or register a new account.";

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void nonexistentUserCannotLogInOnMobile() {
    SignInPage signIn = navigateToSignInOnMobile();
    String nonexistingUsername = String.format("QA_%s", LocalDateTime.now());
    signIn.login(nonexistingUsername, user.getPassword());
    Assertion.assertEquals(signIn.getError(), ERROR_MESSAGE);
  }

  public void nonexistentUserCannotLogInOnDesktop() {
    SignInPage signIn = openLoginModalOnDesktop();
    String nonexistingUsername = String.format("QA_%s", LocalDateTime.now());
    signIn.login(nonexistingUsername, user.getPassword());
    Assertion.assertEquals(signIn.getError(), ERROR_MESSAGE);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotLogInWithInvalidPasswordOnMobile() {
    SignInPage signIn = navigateToSignInOnMobile();
    String invalidPassword = String.format("P@55_%s", LocalDateTime.now());
    signIn.login(user.getUserName(), invalidPassword);
    Assertion.assertEquals(signIn.getError(), ERROR_MESSAGE);
  }

  public void userCannotLogInWithInvalidPasswordOnDesktop() {
    SignInPage signIn = openLoginModalOnDesktop();
    String invalidPassword = String.format("P@55_%s", LocalDateTime.now());
    signIn.login(user.getUserName(), invalidPassword);
    Assertion.assertEquals(signIn.getError(), ERROR_MESSAGE);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotLogInWithBlankUsernameOnMobile() {
    SignInPage signIn = navigateToSignInOnMobile();
    signIn.login("", user.getPassword());
    assertFalse(signIn.isSubmitButtonEnabled(), "Submit button should be disabled");
  }

  public void userCannotLogInWithBlankUsernameOnDesktop() {
    SignInPage signIn = openLoginModalOnDesktop();
    signIn.login("", user.getPassword());
    assertFalse(signIn.isSubmitButtonEnabled(), "Submit button should be disabled");
  }

  public void userCannotLogInWithBlankPasswordOnDesktop() {
    SignInPage signIn = openLoginModalOnDesktop();
    signIn.login(user.getUserName(), "");
    assertFalse(signIn.isSubmitButtonEnabled(), "Submit button should be disabled");
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotLogInWithBlankPasswordOnMobile() {
    SignInPage signIn = navigateToSignInOnMobile();
    signIn.login(user.getUserName(), "");
    assertFalse(signIn.isSubmitButtonEnabled(), "Submit button should be disabled");
  }
}
