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
import com.wikia.webdriver.common.properties.Credentials;
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

  Credentials credentials = Configuration.getCredentials();


  public void anonCanLoginOnAuthModalFromGlobalNavigation() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(wikiURL);
    NavigationBar signInLink = new NavigationBar(driver);
    DetachedSignInPage authModal = new DetachedSignInPage(signInLink.clickOnSignIn());

    authModal.login(credentials.userName10, credentials.password10);
    base.verifyUserLoggedIn(credentials.userName10);
  }


  public void anonCanLoginAsStaffOnAuthModalFromGlobalNavigation() {
    WikiBasePageObject base = new WikiBasePageObject();
    NavigationBar signInLink = new NavigationBar(driver);
    base.openWikiPage(wikiURL);

    DetachedSignInPage authModal = new DetachedSignInPage(signInLink.clickOnSignIn());

    //we are using userNameStaff2 because of PLATFORM-2502 and PLATFORM-2508
    authModal.login(credentials.userNameStaff2, credentials.passwordStaff2);
    base.verifyUserLoggedIn(credentials.userNameStaff2);
  }

  @Test(enabled = false)
  @Execute(onWikia = "ja.ja-test")
  public void anonCanLoginAsJapaneseUserOnAuthModalFromGlobalNavigation() {
    WikiBasePageObject base = new WikiBasePageObject();
    NavigationBar signInLink = new NavigationBar(driver);
    base.openWikiPage(wikiURL);

    DetachedSignInPage authModal = new DetachedSignInPage(signInLink.clickOnSignIn());

    authModal.login(credentials.userNameJapanese2, credentials.passwordJapanese2);
    base.verifyUserLoggedIn(credentials.userNameJapanese2);
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
    loginPageObject.logUserIn(Configuration.getCredentials().userName10, "thisIsWrongPassword");

    Assertion.assertEquals(loginPageObject.getErrorMessage(), ERROR_MESSAGE);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void invalidUserCanNotLogIn() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn("notExistingUserName", Configuration.getCredentials().password10);

    Assertion.assertEquals(loginPageObject.getErrorMessage(), ERROR_MESSAGE);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void notPossibleToLogInWhenUsernameFieldBlank() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn("", Configuration.getCredentials().password10);

    Assertion.assertTrue(loginPageObject.isSubmitButtonDisabled());
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void notPossibleToLogInWhenPasswordFieldBlank() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn(Configuration.getCredentials().userName10, "");

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
  public void registerNowLinkWorks() {
    SignupPageObject registrationPage = new SignupPageObject(driver);
    registrationPage.openRegisterPage();
    String expectedHeader = registrationPage.getRegisterHeaderText();

    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.clickOnRegisterLink();
    String currentHeader = registrationPage.getRegisterHeaderText();
    Assertion.assertEquals(expectedHeader, currentHeader);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userIsTakenToJoinPage() {
    JoinPageObject joinPageObject = new JoinPageObject(driver).get();
    String expectedMessage = joinPageObject.getJoinTodayText();

    driver.get(wikiURL);

    new TopBar().openNavigation();
    new Navigation(driver).clickOnSignInRegisterButton();

    Assertion.assertEquals(joinPageObject.getJoinTodayText(), expectedMessage);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void registerButtonWorksOnJoinPage() {
    SignupPageObject registrationPage = new SignupPageObject(driver);
    registrationPage.openRegisterPage();
    String expectedHeader = registrationPage.getRegisterHeaderText();

    JoinPageObject joinPageObject = new JoinPageObject(driver).get();
    joinPageObject.clickRegisterWithEmail();

    Assertion.assertEquals(registrationPage.getRegisterHeaderText(), expectedHeader);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void signInLinkWorksOnJoinPage() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    String expectedHeader = loginPageObject.getLoginHeaderText();

    JoinPageObject joinPageObject = new JoinPageObject(driver).get();
    joinPageObject.clickSignInLink();

    Assertion.assertEquals(loginPageObject.getLoginHeaderText(), expectedHeader);
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
    loginPageObject.typePassword(Configuration.getCredentials().password10);

    Assertion
      .assertTrue(loginPageObject.isPasswordTogglerDisabled(), "password should be disabled");

    loginPageObject.clickOnPasswordToggler();

    Assertion.assertTrue(loginPageObject.isPasswordTogglerEnabled(), "password should be enabled");
  }

  private static final String EXPECTED_ERROR_MESSAGE =
    "We don't recognize these credentials. Try again or register a new account.";

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonCanLogInAsRegisteredUser() {
    ArticlePage article = new ArticlePage();

    article.open(MercurySubpages.MAIN_PAGE)
      .getTopbar()
      .openNavigation()
      .clickOnSignInRegisterButton()
      .navigateToSignIn()
      .login(Configuration.getCredentials().userName10,
        Configuration.getCredentials().password10);

    assertTrue(article.userLoggedInMobile(Configuration.getCredentials().userName10));
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonCanNotLogInWithInvalidPassword() {
    ArticlePage article = new ArticlePage();

    SignInPage signIn = article.open(MercurySubpages.MAIN_PAGE)
      .getTopbar()
      .openNavigation()
      .clickOnSignInRegisterButton()
      .navigateToSignIn();

    signIn.login(Configuration.getCredentials().userName10, "someinvalidpassw0rd");
    assertTrue(signIn.getError().contains(EXPECTED_ERROR_MESSAGE));
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonCanNotLogInWithBlankPassword() {
    ArticlePage article = new ArticlePage();

    SignInPage signIn = article.open(MercurySubpages.MAIN_PAGE)
      .getTopbar()
      .openNavigation()
      .clickOnSignInRegisterButton()
      .navigateToSignIn();

    signIn.login(Configuration.getCredentials().userName10, "someinvalidpassw0rd");
    assertTrue(signIn.submitButtonNotClickable());
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonCanNotLogInWithInvalidUsername() {
    SignInPage signIn = new ArticlePage()
      .open(MercurySubpages.MAIN_PAGE)
      .getTopbar()
      .openNavigation()
      .clickOnSignInRegisterButton()
      .navigateToSignIn();

    signIn.login(String.valueOf(DateTime.now().getMillis()), Configuration.getCredentials().password10);
    assertTrue(signIn.getError().contains(EXPECTED_ERROR_MESSAGE));
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonCanNotLogInWithBlankUsername() {
    SignInPage signIn = new ArticlePage()
      .open(MercurySubpages.MAIN_PAGE)
      .getTopbar()
      .openNavigation()
      .clickOnSignInRegisterButton()
      .navigateToSignIn();
    signIn.typePassword(Configuration.getCredentials().password10);
    assertTrue(signIn.submitButtonNotClickable());
  }
}
