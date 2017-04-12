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

  @Test
  public void anonCanRemindPasswordFromAuthModal() {
    performResetPasswordFlowForUser(User.FORGOTTEN_PASSWORD);
  }

  @Test
  public void anonCanResetPasswordForUsernameWithSpace() {
    performResetPasswordFlowForUser(User.FORGOTTEN_PASSWORD_SPACES);
  }

  @Test
  public void anonCanRemindPasswordOnUserLoginSpecialPage() {
    EmailUtils.deleteAllEmails(credentials.email, credentials.emailPassword);
    WikiBasePageObject base = new WikiBasePageObject();
    AttachedSignInPage signIn = new AttachedSignInPage().open();
    signIn.clickForgotPasswordLink().requestLinkForUsername(User.FORGOTTEN_PASSWORD.getUserName());
    String resetLink = base.getPasswordResetLink(credentials.email, credentials.emailPassword);
    ResetPasswordPage resetPass = new ResetPasswordPage(resetLink);
    resetPass.setNewPassword(User.FORGOTTEN_PASSWORD.getPassword());

    assertTrue(resetPass.newPasswordSetSuccessfully());
  }

  @Test
  public void anonCanRemindPasswordOnUserLoginSpecialPageUsingLowerCaseUserName() {
    String username = User.FORGOTTEN_PASSWORD.getUserName();
    String lowercaseUsername = Character.toLowerCase(username.charAt(0)) + username.substring(1);
    EmailUtils.deleteAllEmails(credentials.email, credentials.emailPassword);
    WikiBasePageObject base = new WikiBasePageObject();
    AttachedSignInPage signIn = new AttachedSignInPage().open();
    signIn.clickForgotPasswordLink().requestLinkForUsername(lowercaseUsername);
    String resetLink = base.getPasswordResetLink(credentials.email, credentials.emailPassword);
    ResetPasswordPage resetPass = new ResetPasswordPage(resetLink);
    resetPass.setNewPassword(User.FORGOTTEN_PASSWORD.getPassword());

    assertTrue(resetPass.newPasswordSetSuccessfully());
  }

  private void performResetPasswordFlowForUser(User user) {
    EmailUtils.deleteAllEmails(credentials.email, credentials.emailPassword);
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(wikiURL);
    DetachedSignInPage loginModal = new DetachedSignInPage(new NavigationBar(driver).clickOnSignIn());
    loginModal
      .clickForgotPasswordLink()
      .requestLinkForUsername(user.getUserName());

    String resetLink = base.getPasswordResetLink(credentials.email, credentials.emailPassword);
    ResetPasswordPage resetPass = new ResetPasswordPage(resetLink);
    resetPass.setNewPassword(user.getPassword());

    assertTrue(resetPass.newPasswordSetSuccessfully());
  }
}
