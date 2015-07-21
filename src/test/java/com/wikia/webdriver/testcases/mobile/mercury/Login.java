package com.wikia.webdriver.testcases.mobile.mercury;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.ArticlePageObject;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.LoginPage;

/**
 * Created by Qaga on 2015-06-29.
 */
@Test(groups = {"MercuryMobileLogin"})
public class Login extends NewTestTemplate {

  private static final String ERROR_MESSAGE =
      "Hm, we don't recognize these credentials. Please try again or register a new account.";

  @Test
  @Execute(onWikia = "mobileregressiontesting")
  public void validUserCanLogIn() {
    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.logUserIn(Configuration.getCredentials().userName10,
        Configuration.getCredentials().password10);

    Assertion.assertTrue(loginPage.getNav().isUserLoggedIn(
        Configuration.getCredentials().userName10));
  }

  @Test
  @Execute(onWikia = "mobileregressiontesting")
  public void userCanNotLogInWithWrongPassword() {
    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.logUserIn(Configuration.getCredentials().userName10, "thisIsWrongPassword");

    Assertion.assertEquals(loginPage.getErrorMessage(), ERROR_MESSAGE);
  }

  @Test
  @Execute(onWikia = "mobileregressiontesting")
  public void invalidUserCanNotLogIn() {
    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.logUserIn("notExistingUserName", Configuration.getCredentials().password10);

    Assertion.assertEquals(loginPage.getErrorMessage(), ERROR_MESSAGE);
  }

  @Test
  @Execute(onWikia = "mobileregressiontesting")
  public void notPossibleToLogInWhenUsernameFieldBlank() throws InterruptedException {
    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.logUserIn("", Configuration.getCredentials().password10);

    Assertion.assertTrue(loginPage.isSubmitButtonDisabled(2));
  }

  @Test
  @Execute(onWikia = "mobileregressiontesting")
  public void notPossibleToLogInWhenPasswordFieldBlank() throws InterruptedException {
    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.logUserIn(Configuration.getCredentials().userName10, "");

    Assertion.assertTrue(loginPage.isSubmitButtonDisabled(2));
  }

  @Test
  @Execute(onWikia = "mobileregressiontesting")
  public void closeButtonWorksAndRedirectsProperly(){
    ArticlePageObject homePage = new ArticlePageObject(driver);
    homePage.openMainPage(wikiURL);
    String expectedHomePageTitle =  homePage.getArticleTitle();

    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.clickOnCloseButton();

    Assertion.assertEquals(expectedHomePageTitle, homePage.getArticleTitle());

  }
}
