package com.wikia.webdriver.testcases.mercurytests.old;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.elements.mercury.old.ArticlePageObject;
import com.wikia.webdriver.elements.mercury.old.JoinPageObject;
import com.wikia.webdriver.elements.mercury.old.LoginPageObject;
import com.wikia.webdriver.elements.mercury.old.SignupPageObject;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class LoginTestsOld extends NewTestTemplate {

  private static final String ERROR_MESSAGE =
      "We don't recognize these credentials. Try again or register a new account.";

  @Test(groups = "MercuryLoginTest_002")
  public void MercuryLoginTest_002_userCanNotLogInWithWrongPassword() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn(Configuration.getCredentials().userName10, "thisIsWrongPassword");

    Assertion.assertEquals(loginPageObject.getErrorMessage(), ERROR_MESSAGE);
  }

  @Test(groups = "MercuryLoginTest_003")
  public void MercuryLoginTest_003_invalidUserCanNotLogIn() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn("notExistingUserName", Configuration.getCredentials().password10);

    Assertion.assertEquals(loginPageObject.getErrorMessage(), ERROR_MESSAGE);
  }

  @Test(groups = "MercuryLoginTest_004")
  public void MercuryLoginTest_004_notPossibleToLogInWhenUsernameFieldBlank() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn("", Configuration.getCredentials().password10);

    Assertion.assertTrue(loginPageObject.isSubmitButtonDisabled());
  }

  @Test(groups = "MercuryLoginTest_005")
  public void MercuryLoginTest_005_notPossibleToLogInWhenPasswordFieldBlank() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn(Configuration.getCredentials().userName10, "");

    Assertion.assertTrue(loginPageObject.isSubmitButtonDisabled());
  }

  @Test(groups = "MercuryLoginTest_006")
  public void MercuryLoginTest_006_closeButtonWorksAndRedirectsProperly() {
    ArticlePageObject homePage = new ArticlePageObject(driver);
    driver.get(wikiURL);
    String expectedHomePageTitle = homePage.getArticleTitle();

    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.clickOnCloseButton();

    homePage.isFooterLogoVisible();
    Assertion.assertEquals(expectedHomePageTitle, homePage.getArticleTitle());
  }

  @Test(groups = "MercuryLoginTest_007")
  public void MercuryLoginTest_007_registerNowLinkWorks() {
    SignupPageObject registrationPage = new SignupPageObject(driver);
    registrationPage.openRegisterPage();
    String expectedHeader = registrationPage.getRegisterHeaderText();

    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.clickOnRegisterLink();
    String currentHeader = registrationPage.getRegisterHeaderText();
    Assertion.assertEquals(expectedHeader, currentHeader);
  }

  @Test(groups = "MercuryLoginTest_008")
  public void MercuryLoginTest_008_userIsTakenToJoinPage() {
    JoinPageObject joinPageObject = new JoinPageObject(driver).get();
    String expectedMessage = joinPageObject.getJoinTodayText();

    driver.get(wikiURL);

    new TopBar(driver).openNavigation();
    new Navigation(driver).clickOnSignInRegisterButton();

    Assertion.assertEquals(joinPageObject.getJoinTodayText(), expectedMessage);
  }

  @Test(groups = "MercuryLoginTest_009")
  public void MercuryLoginTest_009_registerButtonWorksOnJoinPage() {
    SignupPageObject registrationPage = new SignupPageObject(driver);
    registrationPage.openRegisterPage();
    String expectedHeader = registrationPage.getRegisterHeaderText();

    JoinPageObject joinPageObject = new JoinPageObject(driver).get();
    joinPageObject.clickRegisterWithEmail();

    Assertion.assertEquals(registrationPage.getRegisterHeaderText(), expectedHeader);
  }

  @Test(groups = "MercuryLoginTest_010")
  public void MercuryLoginTest_010_signInLinkWorksOnJoinPage() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    String expectedHeader = loginPageObject.getLoginHeaderText();

    JoinPageObject joinPageObject = new JoinPageObject(driver).get();
    joinPageObject.clickSignInLink();

    Assertion.assertEquals(loginPageObject.getLoginHeaderText(), expectedHeader);
  }

  @Test(groups = "MercuryLoginTest_011")
  public void MercuryLoginTest_011_japaneseUserLogIn() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.logUserIn(Configuration.getCredentials().userNameJapanese2,
                              Configuration.getCredentials().passwordJapanese2);
//    Assertion.assertTrue(loginPageObject.getNav().isUserLoggedIn(
//        Configuration.getCredentials().userNameJapanese2));
  }

  @Test(groups = "MercuryLoginTest_012")
  public void MercuryLoginTest_012_passwordTogglerWorks() {
    LoginPageObject loginPageObject = new LoginPageObject(driver).get();
    loginPageObject.typePassword(Configuration.getCredentials().password10);

    Assertion
        .assertTrue(loginPageObject.isPasswordTogglerDisabled(), "password should be disabled");

    loginPageObject.clickOnPasswordToggler();

    Assertion.assertTrue(loginPageObject.isPasswordTogglerEnabled(), "password should be enabled");
  }
}
