package com.wikia.webdriver.testcases.mercurytests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.LoginPage;

/**
 * Created by Qaga on 2015-06-29.
 */
@Test(groups = {"MercuryMobileLogin"})
public class LoginTests extends NewTestTemplate {

  private static final String ERROR_MESSAGE =
      "Hm, we don't recognize these credentials. Please try again or register a new account.";

  @Test
  public void validUserCanLogIn() {
    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.logUserIn(Configuration.getCredentials().userName10,
        Configuration.getCredentials().password10);

    Assertion.assertTrue(loginPage.getNav().isUserLoggedIn(
        Configuration.getCredentials().userName10));
  }

  @Test
  public void userCanNotLogInWithWrongPassword() {
    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.logUserIn(Configuration.getCredentials().userName10, "thisIsWrongPassword");

    Assertion.assertEquals(loginPage.getErrorMessage(), ERROR_MESSAGE);
  }

  @Test
  public void invalidUserCanNotLogIn() {
    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.logUserIn("notExistingUserName", Configuration.getCredentials().password10);

    Assertion.assertEquals(loginPage.getErrorMessage(), ERROR_MESSAGE);
  }

  @Test
  public void notPossibleToLogInWhenUsernameFieldBlank() throws InterruptedException {
    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.logUserIn("", Configuration.getCredentials().password10);

    Assertion.assertTrue(loginPage.isSubmitButtonDisabled(2));
  }

  @Test
  public void notPossibleToLogInWhenPasswordFieldBlank() throws InterruptedException {
    LoginPage loginPage = new LoginPage(driver).get();
    loginPage.logUserIn(Configuration.getCredentials().userName10, "");

    Assertion.assertTrue(loginPage.isSubmitButtonDisabled(2));
  }
}
