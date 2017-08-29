package com.wikia.webdriver.testcases.auth;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.Helios;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.elements.mercury.old.JoinPageObject;
import com.wikia.webdriver.elements.mercury.old.LoginPageObject;
import com.wikia.webdriver.elements.mercury.old.SignupPageObject;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.DetachedSignInPage;

import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.SignInPage;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import static com.wikia.webdriver.common.core.Assertion.assertTrue;

@Test(groups = "auth-login")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
public class LoginTests extends NewTestTemplate {

  User user = User.LOGIN_USER;
  User japaneseUser = User.USER_JAPAN;
  User staff = User.LOGIN_STAFF;


  public void userCanLoginOnAuthModalFromGlobalNavigation() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(wikiURL);
    NavigationBar signInLink = new NavigationBar(driver);
    DetachedSignInPage authModal = new DetachedSignInPage(signInLink.clickOnSignIn());

    authModal.login(user.getUserName(), user.getPassword());
    base.verifyUserLoggedIn(user.getUserName());
  }


  public void userCanLoginAsStaffOnAuthModalFromGlobalNavigation() {
    WikiBasePageObject base = new WikiBasePageObject();
    NavigationBar signInLink = new NavigationBar(driver);
    base.openWikiPage(wikiURL);

    DetachedSignInPage authModal = new DetachedSignInPage(signInLink.clickOnSignIn());

    authModal.login(staff.getUserName(), staff.getPassword());
    base.verifyUserLoggedIn(staff.getUserName());
  }

  public void japaneseUserCanLogInOnAuthModalFromGlobalNavigation() {
    WikiBasePageObject base = new WikiBasePageObject();
    NavigationBar signInLink = new NavigationBar(driver);
    base.openWikiPage(wikiURL);

    DetachedSignInPage authModal = new DetachedSignInPage(signInLink.clickOnSignIn());

    authModal.login(japaneseUser.getUserName(), japaneseUser.getPassword());
    base.verifyUserLoggedIn(japaneseUser.getUserName());
  }

  @Execute(asUser = User.USER_12)
  public void userWithoutAValidTokenGetsLoggedOut() {
    new ArticleContent().clear();

    ArticlePageObject article = new ArticlePageObject().open();
    Helios.deleteAllTokens(User.USER_12);

    article.refreshPageAddingCacheBuster();
    assertTrue(article.getGlobalNavigation().isUserLoggedOut());
  }

  private static final String ERROR_MESSAGE =
    "We don't recognize these credentials. Try again or register a new account.";

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotLogInWithWrongPassword() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn(user.getUserName(), "thisIsWrongPassword");

    Assertion.assertEquals(loginPageObject.getErrorMessage(), ERROR_MESSAGE);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void invalidUserCanNotLogIn() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn("notExistingUserName", user.getPassword());

    Assertion.assertEquals(loginPageObject.getErrorMessage(), ERROR_MESSAGE);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void notPossibleToLogInWhenUsernameFieldBlank() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn("", user.getPassword());

    Assertion.assertTrue(loginPageObject.isSubmitButtonDisabled());
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void notPossibleToLogInWhenPasswordFieldBlank() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn(user.getUserName(), "");

    Assertion.assertTrue(loginPageObject.isSubmitButtonDisabled());
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void closeButtonWorksAndRedirectsProperly() {
    com.wikia.webdriver.elements.mercury.old.ArticlePageObject
      homePage = new com.wikia.webdriver.elements.mercury.old.ArticlePageObject(driver);
    driver.get(wikiURL);
    String expectedHomePageTitle = homePage.getArticleTitle();

    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.clickOnCloseButton();

    homePage.isFooterLogoVisible();
    Assertion.assertEquals(expectedHomePageTitle, homePage.getArticleTitle());
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void japaneseUserLogIn() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn(Configuration.getCredentials().userNameJapanese2,
      Configuration.getCredentials().passwordJapanese2);
    //    Assertion.assertTrue(loginPageObject.getNav().isUserLoggedIn(
    //        Configuration.getCredentials().userNameJapanese2));
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void passwordTogglerWorks() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.typePassword(user.getPassword());

    Assertion
      .assertTrue(loginPageObject.isPasswordTogglerDisabled(), "password should be disabled");

    loginPageObject.clickOnPasswordToggler();

    Assertion.assertTrue(loginPageObject.isPasswordTogglerEnabled(), "password should be enabled");
  }

  private static final String EXPECTED_ERROR_MESSAGE =
    "We don't recognize these credentials. Try again or register a new account.";

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCanNotLogInWithInvalidPassword() {
    ArticlePage article = new ArticlePage();

    SignInPage signIn = article.open(MercurySubpages.MAIN_PAGE)
      .getTopbar()
      .openNavigation()
      .clickOnSignInRegisterButton()
      .navigateToSignIn();

    signIn.login(user.getUserName(), "someinvalidpassw0rd");
    assertTrue(signIn.getError().contains(EXPECTED_ERROR_MESSAGE));
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCanNotLogInWithBlankPassword() {
    ArticlePage article = new ArticlePage();

    SignInPage signIn = article.open(MercurySubpages.MAIN_PAGE)
      .getTopbar()
      .openNavigation()
      .clickOnSignInRegisterButton()
      .navigateToSignIn();

    signIn.login(user.getUserName(), "someinvalidpassw0rd");
    assertTrue(signIn.submitButtonNotClickable());
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCanNotLogInWithInvalidUsername() {
    SignInPage signIn = new ArticlePage()
      .open(MercurySubpages.MAIN_PAGE)
      .getTopbar()
      .openNavigation()
      .clickOnSignInRegisterButton()
      .navigateToSignIn();

    signIn.login(String.valueOf(DateTime.now().getMillis()), user.getPassword());
    assertTrue(signIn.getError().contains(EXPECTED_ERROR_MESSAGE));
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCanNotLogInWithBlankUsername() {
    SignInPage signIn = new ArticlePage()
      .open(MercurySubpages.MAIN_PAGE)
      .getTopbar()
      .openNavigation()
      .clickOnSignInRegisterButton()
      .navigateToSignIn();
    signIn.typePassword(user.getPassword());
    assertTrue(signIn.submitButtonNotClickable());
  }
}
