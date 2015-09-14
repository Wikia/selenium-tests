package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.TopBarComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.JoinPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.LoginPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.SignupPageObject;

import org.testng.annotations.Test;

/**
 * @ownership Social
 */
@Test(groups = {"MercuryLoginTests", "Mercury"})
public class LoginTests extends NewTestTemplate {

  private static final String ERROR_MESSAGE =
      "We don't recognize these credentials. Try again or register a new account.";

  @Test(groups = {"MercuryLoginTest_001"}, enabled = false)
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryLoginTest_001_validUserCanLogIn() {
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);

    nav.navigateToUrlWithPath(wikiURL, "Map");
    String url = driver.getCurrentUrl();
    new TopBarComponentObject(driver).clickLogInIcon();
    new LoginPageObject(driver).clickOnSignInButton().logUserIn(
        Configuration.getCredentials().userName10,
        Configuration.getCredentials().password10);

    new ArticlePageObject(driver).waitForFooterToBeVisible();
    boolean result = url.equals(driver.getCurrentUrl());
    PageObjectLogging.log("url", "was redirected correctly", result);

    Assertion.assertTrue(nav.isUserLoggedIn(Configuration.getCredentials().userName10));
  }

  @Test(groups = {"MercuryLoginTest_002"})
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryLoginTest_002_userCanNotLogInWithWrongPassword() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn(Configuration.getCredentials().userName10, "thisIsWrongPassword");

    Assertion.assertEquals(loginPageObject.getErrorMessage(), ERROR_MESSAGE);
  }

  @Test(groups = {"MercuryLoginTest_003"})
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryLoginTest_003_invalidUserCanNotLogIn() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn("notExistingUserName", Configuration.getCredentials().password10);

    Assertion.assertEquals(loginPageObject.getErrorMessage(), ERROR_MESSAGE);
  }

  @Test(groups = {"MercuryLoginTest_004"})
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryLoginTest_004_notPossibleToLogInWhenUsernameFieldBlank() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn("", Configuration.getCredentials().password10);

    Assertion.assertTrue(loginPageObject.isSubmitButtonDisabled(2));
  }

  @Test(groups = {"MercuryLoginTest_005"})
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryLoginTest_005_notPossibleToLogInWhenPasswordFieldBlank() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn(Configuration.getCredentials().userName10, "");

    Assertion.assertTrue(loginPageObject.isSubmitButtonDisabled(2));
  }

  @Test(groups = {"MercuryLoginTest_006"})
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryLoginTest_006_closeButtonWorksAndRedirectsProperly() {
    ArticlePageObject homePage = new ArticlePageObject(driver);
    homePage.openMainPage(wikiURL);
    String expectedHomePageTitle = homePage.getArticleTitle();

    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.clickOnCloseButton();

    homePage.isFooterLogoVisible();
    Assertion.assertEquals(expectedHomePageTitle, homePage.getArticleTitle());
  }

  @Test(groups = {"MercuryLoginTest_007"})
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryLoginTest_007_registerNowLinkWorks() {
    SignupPageObject registrationPage = new SignupPageObject(driver);
    registrationPage.openRegisterPage();
    String expectedHeader = registrationPage.getRegisterHeaderText();

    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.clickOnRegisterLink();
    String currentHeader = registrationPage.getRegisterHeaderText();
    Assertion.assertEquals(expectedHeader, currentHeader);
  }

  @Test(groups = {"MercuryLoginTest_008"})
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryLoginTest_008_userIsTakenToJoinPage() {
    JoinPageObject joinPageObject = new JoinPageObject(driver).get();
    String expectedMessage = joinPageObject.getJoinTodayText();

    ArticlePageObject homePage = new ArticlePageObject(driver);
    homePage.openMainPage(wikiURL);
    TopBarComponentObject loginIcon = new TopBarComponentObject(driver);
    loginIcon.clickLogInIcon();

    Assertion.assertEquals(joinPageObject.getJoinTodayText(), expectedMessage);
  }

  @Test(groups = {"MercuryLoginTest_009"})
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryLoginTest_009_registerButtonWorksOnJoinPage() {
    SignupPageObject registrationPage = new SignupPageObject(driver);
    registrationPage.openRegisterPage();
    String expectedHeader = registrationPage.getRegisterHeaderText();

    JoinPageObject joinPageObject = new JoinPageObject(driver).get();
    joinPageObject.clickRegisterWithEmail();

    Assertion.assertEquals(registrationPage.getRegisterHeaderText(), expectedHeader);
  }

  @Test(groups = {"MercuryLoginTest_010"})
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryLoginTest_010_signInLinkWorksOnJoinPage() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    String expectedHeader = loginPageObject.getLoginHeaderText();

    JoinPageObject joinPageObject = new JoinPageObject(driver).get();
    joinPageObject.clickSignInLink();

    Assertion.assertEquals(loginPageObject.getLoginHeaderText(), expectedHeader);
  }

  @Test(groups = {"MercuryLoginTest_011"}, enabled = false)
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryLoginTest_011_japaneseUserLogIn() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn(Configuration.getCredentials().userNameJapanese2,
                        Configuration.getCredentials().passwordJapanese2);
    Assertion.assertTrue(loginPageObject.getNav().isUserLoggedIn(
        Configuration.getCredentials().userNameJapanese2));
  }

  @Test(groups = {"MercuryLoginTest_012"})
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryLoginTest_012_passwordTogglerWorks() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.typePassword(Configuration.getCredentials().password10);

    Assertion
        .assertTrue(loginPageObject.isPasswordTogglerDisabled(), "password should be disabled");

    loginPageObject.clickOnPasswordToggler();

    Assertion.assertTrue(loginPageObject.isPasswordTogglerEnabled(), "password should be enabled");
  }
}
