package com.wikia.webdriver.testcases.auth;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.Helios;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.old.LoginPageObject;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.DetachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.SignInPage;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import static com.wikia.webdriver.common.core.Assertion.assertTrue;

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
    SignInPage signInPage = navigateToSignInOnMobile(openArticleOnMobile());
    signInPage.typePassword(user.getPassword());

    Assertion.assertTrue(signInPage.isPasswordMasked(), "password should be masked");
    signInPage.togglePasswordVisibility();
    Assertion.assertFalse(signInPage.isPasswordMasked(), "password should be readable");
  }

  public void passwordTogglerChangesPasswordVisibilityOnDesktop() {
    SignInPage signInPage = openLoginModalOnDesktop();
    signInPage.typePassword(user.getPassword());

    Assertion.assertTrue(signInPage.isPasswordMasked(), "password should be masked");
    signInPage.togglePasswordVisibility();
    Assertion.assertFalse(signInPage.isPasswordMasked(), "password should be readable");
  }

  @Execute(asUser = User.USER_12)
  public void userWithoutAValidTokenGetsLoggedOutOnDesktop() {
    ArticlePageObject article = openArticleOnDesktop();
    Helios.deleteAllTokens(User.USER_12);
    article.refreshPageAddingCacheBuster();
    assertTrue(article.getGlobalNavigation().isUserLoggedOut());
  }

  @Execute(asUser = User.USER_12)
  public void userWithoutAValidTokenGetsLoggedOutOnMobile() {
    new ArticleContent().clear();

    ArticlePageObject article = new ArticlePageObject().open();
    Helios.deleteAllTokens(User.USER_12);

    article.refreshPageAddingCacheBuster();
    assertTrue(article.getGlobalNavigation().isUserLoggedOut());
  }

  private static final String ERROR_MESSAGE =
    "We don't recognize these credentials. Try again or register a new account.";



  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void nonexistentUserCannotLogInOnMobile() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn("notExistingUserName", user.getPassword());

    Assertion.assertEquals(loginPageObject.getErrorMessage(), ERROR_MESSAGE);
  }

  public void nonexistentUserCannotLogInOnDesktop() {
    SignInPage signIn = new ArticlePage()
      .open(MercurySubpages.MAIN_PAGE)
      .getTopbar()
      .openNavigation()
      .clickOnSignInRegisterButton()
      .navigateToSignIn();

    signIn.login(String.valueOf(DateTime.now().getMillis()), user.getPassword());
    assertTrue(signIn.getError().contains(EXPECTED_ERROR_MESSAGE));
  }

  private static final String EXPECTED_ERROR_MESSAGE =
    "We don't recognize these credentials. Try again or register a new account.";

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotLogInWithInvalidPasswordOnMobile() {
    ArticlePage article = new ArticlePage();

    SignInPage signIn = article.open(MercurySubpages.MAIN_PAGE)
      .getTopbar()
      .openNavigation()
      .clickOnSignInRegisterButton()
      .navigateToSignIn();

    signIn.login(user.getUserName(), "someinvalidpassw0rd");
    assertTrue(signIn.getError().contains(EXPECTED_ERROR_MESSAGE));
  }

  public void userCannotLogInWithInvalidPasswordOnDesktop() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn(user.getUserName(), "thisIsWrongPassword");

    Assertion.assertEquals(loginPageObject.getErrorMessage(), ERROR_MESSAGE);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotLogInWithBlankUsernameOnMobile() {
    SignInPage signIn = new ArticlePage()
      .open(MercurySubpages.MAIN_PAGE)
      .getTopbar()
      .openNavigation()
      .clickOnSignInRegisterButton()
      .navigateToSignIn();
    signIn.typePassword(user.getPassword());
    assertTrue(signIn.submitButtonNotClickable());
  }

  public void userCannotLogInWithBlankUsernameOnDesktop() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn("", user.getPassword());

    Assertion.assertTrue(loginPageObject.isSubmitButtonDisabled());
  }

  public void userCannotLogInWithBlankPasswordOnDesktop() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn(user.getUserName(), "");

    Assertion.assertTrue(loginPageObject.isSubmitButtonDisabled());
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotLogInWithBlankPasswordOnMobile() {
    ArticlePage article = new ArticlePage();

    SignInPage signIn = article.open(MercurySubpages.MAIN_PAGE)
      .getTopbar()
      .openNavigation()
      .clickOnSignInRegisterButton()
      .navigateToSignIn();

    signIn.login(user.getUserName(), "someinvalidpassw0rd");
    assertTrue(signIn.submitButtonNotClickable());
  }
}
