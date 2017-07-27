package com.wikia.webdriver.testcases.auth;

import com.wikia.webdriver.common.core.EmailUtils;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.ResetPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.DetachedSignInPage;
import org.testng.annotations.Test;

import static com.wikia.webdriver.common.core.Assertion.assertTrue;

@Test(groups = "auth-forgottenPassword")
public class ForgottenPasswordTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  // Email credentials for User.FORGOTTEN_PASSWORD
  String EMAIL1 = credentials.forgottenPasswordEmail1Address;
  String PASSWORD1 = credentials.forgottenPasswordEmail1Password;

  // Email credentials for User.FORGOTTEN_PASSWORD_SPACES
  String EMAIL2 = credentials.forgottenPasswordEmail2Address;
  String PASSWORD2 = credentials.forgottenPasswordEmail2Password;

  @Test
  public void anonCanRemindPasswordFromAuthModal() {
    executeResetPasswordFlow(User.FORGOTTEN_PASSWORD, EMAIL1, PASSWORD1);
  }

  @Test
  public void anonCanResetPasswordForUsernameWithSpaces() {
    executeResetPasswordFlow(User.FORGOTTEN_PASSWORD_SPACES, EMAIL2, PASSWORD2);
  }

  @Test
  public void anonCanRemindPasswordOnUserLoginSpecialPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    AttachedSignInPage signIn = new AttachedSignInPage().open();
    signIn.clickForgotPasswordLink().requestLinkForUsername(User.FORGOTTEN_PASSWORD.getUserName());
    String resetLink = base.getPasswordResetLink(EMAIL1, PASSWORD1);
    ResetPasswordPage resetPass = new ResetPasswordPage(resetLink);
    resetPass.setNewPassword(User.FORGOTTEN_PASSWORD.getPassword());

    assertTrue(resetPass.newPasswordSetSuccessfully());
  }

  @Test
  public void anonCanRemindPasswordOnUserLoginSpecialPageUsingLowerCaseUserName() {
    String username = User.FORGOTTEN_PASSWORD.getUserName();
    String lowercaseUsername = Character.toLowerCase(username.charAt(0)) + username.substring(1);

    WikiBasePageObject base = new WikiBasePageObject();
    AttachedSignInPage signIn = new AttachedSignInPage().open();
    signIn.clickForgotPasswordLink().requestLinkForUsername(lowercaseUsername);
    String resetLink = base.getPasswordResetLink(EMAIL1, PASSWORD1);
    ResetPasswordPage resetPass = new ResetPasswordPage(resetLink);
    resetPass.setNewPassword(User.FORGOTTEN_PASSWORD.getPassword());

    assertTrue(resetPass.newPasswordSetSuccessfully());
  }

  private void executeResetPasswordFlow(User user, String email, String password) {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(wikiURL);
    DetachedSignInPage loginModal = new DetachedSignInPage(new NavigationBar(driver).clickOnSignIn());
    loginModal
      .clickForgotPasswordLink()
      .requestLinkForUsername(user.getUserName());

    String resetLink = base.getPasswordResetLink(email, password);
    ResetPasswordPage resetPass = new ResetPasswordPage(resetLink);
    resetPass.setNewPassword(user.getPassword());

    assertTrue(resetPass.newPasswordSetSuccessfully());
  }
}
