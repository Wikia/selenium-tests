package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.TopBarComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.JoinPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.LoginPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.SignupPageObject;

import org.testng.annotations.Test;

/**
 * @ownership Social
 */
@Test(groups = {"MercuryLoginTests", "Mercury"})
public class LoginTests extends NewTestTemplate {

  private static final String ERROR_MESSAGE =
      "Hm, we don't recognize these credentials. Please try again or register a new account.";

  @Test(groups = {"MercuryLoginTest_001"})
  @Execute(onWikia = "mobileregressiontesting")
  public void MercuryLoginTest_001_validUserCanLogIn() {
    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.logUserIn(Configuration.getCredentials().userName10,
                        Configuration.getCredentials().password10);

    Assertion.assertTrue(loginPage.getNav().isUserLoggedIn(
        Configuration.getCredentials().userName10));
  }

  @Test(groups = {"MercuryLoginTest_002"})
  @Execute(onWikia = "mobileregressiontesting")
  public void MercuryLoginTest_002_userCanNotLogInWithWrongPassword() {
    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.logUserIn(Configuration.getCredentials().userName10, "thisIsWrongPassword");

    Assertion.assertEquals(loginPage.getErrorMessage(), ERROR_MESSAGE);
  }

  @Test(groups = {"MercuryLoginTest_003"})
  @Execute(onWikia = "mobileregressiontesting")
  public void MercuryLoginTest_003_invalidUserCanNotLogIn() {
    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.logUserIn("notExistingUserName", Configuration.getCredentials().password10);

    Assertion.assertEquals(loginPage.getErrorMessage(), ERROR_MESSAGE);
  }

  @Test(groups = {"MercuryLoginTest_004"})
  @Execute(onWikia = "mobileregressiontesting")
  public void MercuryLoginTest_004_notPossibleToLogInWhenUsernameFieldBlank() {
    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.logUserIn("", Configuration.getCredentials().password10);

    Assertion.assertTrue(loginPage.isSubmitButtonDisabled(2));
  }

  @Test(groups = {"MercuryLoginTest_005"})
  @Execute(onWikia = "mobileregressiontesting")
  public void MercuryLoginTest_005_notPossibleToLogInWhenPasswordFieldBlank() {
    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.logUserIn(Configuration.getCredentials().userName10, "");

    Assertion.assertTrue(loginPage.isSubmitButtonDisabled(2));
  }

  @Test(groups = {"MercuryLoginTest_006"})
  @Execute(onWikia = "mobileregressiontesting")
  public void MercuryLoginTest_006_closeButtonWorksAndRedirectsProperly() {
    ArticlePageObject homePage = new ArticlePageObject(driver);
    homePage.openMainPage(wikiURL);
    String expectedHomePageTitle = homePage.getArticleTitle();

    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.clickOnCloseButton();

    homePage.isFooterLogoVisible();
    Assertion.assertEquals(expectedHomePageTitle, homePage.getArticleTitle());
  }

  @Test(groups = {"MercuryLoginTest_007"})
  @Execute(onWikia = "mobileregressiontesting")
  public void MercuryLoginTest_007_registerNowLinkWorks() {
    SignupPageObject registrationPage = new SignupPageObject(driver);
    registrationPage.openRegisterPage();
    String expectedHeader = registrationPage.getRegisterHeaderText();

    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.clickOnRegisterLink();
    String currentHeader = registrationPage.getRegisterHeaderText();
    Assertion.assertEquals(expectedHeader, currentHeader);
  }

  @Test(groups = {"MercuryLoginTest_008"})
  @Execute(onWikia = "mobileregressiontesting")
  public void MercuryLoginTest_008_userIsTakenToJoinPage() {
    JoinPage joinPage = new JoinPage(driver).get();
    String expectedMessage = joinPage.getJoinTodayText();

    ArticlePageObject homePage = new ArticlePageObject(driver);
    homePage.openMainPage(wikiURL);
    TopBarComponentObject loginIcon = new TopBarComponentObject(driver);
    loginIcon.clickLogInIcon();

    Assertion.assertEquals(joinPage.getJoinTodayText(), expectedMessage);
  }

  @Test(groups = {"MercuryLoginTest_009"})
  @Execute(onWikia = "mobileregressiontesting")
  public void MercuryLoginTest_009_registerButtonWorksOnJoinPage() {
    SignupPageObject registrationPage = new SignupPageObject(driver);
    registrationPage.openRegisterPage();
    String expectedHeader = registrationPage.getRegisterHeaderText();

    JoinPage joinPage = new JoinPage(driver).get();
    joinPage.clickRegisterWithEmail();

    Assertion.assertEquals(registrationPage.getRegisterHeaderText(), expectedHeader);
  }

  @Test(groups = {"MercuryLoginTest_010"})
  @Execute(onWikia = "mobileregressiontesting")
  public void MercuryLoginTest_010_signInLinkWorksOnJoinPage() {
    LoginPage loginPage = new LoginPage(driver).get();
    String expectedHeader = loginPage.getLoginHeaderText();

    JoinPage joinPage = new JoinPage(driver).get();
    joinPage.clickSignInLink();

    Assertion.assertEquals(loginPage.getLoginHeaderText(), expectedHeader);
  }

  @Test(groups = {"MercuryLoginTest_011"})
  @Execute(onWikia = "mobileregressiontesting")
  public void MercuryLoginTest_011_japaneseUserLogIn() {
    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.logUserIn(Configuration.getCredentials().userNameJapanese2,
                        Configuration.getCredentials().passwordJapanese2);
    Assertion.assertTrue(loginPage.getNav().isUserLoggedIn(
        Configuration.getCredentials().userNameJapanese2));
  }

  @Test(groups = {"MercuryLoginTest_012"})
  @Execute(onWikia = "mobileregressiontesting")
  public void MercuryLoginTest_012_passwordTogglerWorks() {
    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.typePassword(Configuration.getCredentials().password10);

    Assertion.assertTrue(loginPage.isPasswordTogglerDisabled(), "password should be disabled");

    loginPage.clickOnPasswordToggler();

    Assertion.assertTrue(loginPage.isPasswordTogglerEnabled(), "password should be enabled");
  }
}
