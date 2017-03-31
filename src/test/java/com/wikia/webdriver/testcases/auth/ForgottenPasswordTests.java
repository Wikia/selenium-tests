package com.wikia.webdriver.testcases.auth;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.MailFunctions;
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

import javax.jws.soap.SOAPBinding;

@Test(groups = "auth-forgottenPassword")
public class
ForgottenPasswordTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = "ForgottenPassword_anonCanRemindPasswordFromAuthModal")
  public void anonCanRemindPasswordFromAuthModal() {
        MailFunctions.deleteAllEmails(credentials.email, credentials.emailPassword);
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(wikiURL);
    DetachedSignInPage loginModal = new DetachedSignInPage(new NavigationBar(driver).clickOnSignIn());
    loginModal
      .clickForgotPasswordLink()
      .requestLinkForUsername(User.FORGOTTEN_PASSWORD.getUserName());

    String resetLink = base.getPasswordResetLink(credentials.email, credentials.emailPassword);
    ResetPasswordPage resetPass = new ResetPasswordPage(resetLink);
    resetPass.setNewPassword(User.FORGOTTEN_PASSWORD.getPassword());

    Assertion.assertTrue(resetPass.newPasswordSetSuccessfully());

  }

  @Test(groups = "ForgottenPassword_anonCanRemindPasswordOnUserLoginSpecialPage")
  public void anonCanRemindPasswordOnUserLoginSpecialPage() {
    MailFunctions.deleteAllEmails(credentials.email, credentials.emailPassword);
    WikiBasePageObject base = new WikiBasePageObject();
    AttachedSignInPage signIn = new AttachedSignInPage().open();
    signIn.clickForgotPasswordLink().requestLinkForUsername(User.FORGOTTEN_PASSWORD.getUserName());
    String resetLink = base.getPasswordResetLink(credentials.email, credentials.emailPassword);
    ResetPasswordPage resetPass = new ResetPasswordPage(resetLink);
    resetPass.setNewPassword(User.FORGOTTEN_PASSWORD.getPassword());

    Assertion.assertTrue(resetPass.newPasswordSetSuccessfully());

  }

  @Test(groups = "ForgottenPassword_anonCanRemindPasswordOnUserLoginSpecialPageUsingLowerCaseUserName")
  public void anonCanRemindPasswordOnUserLoginSpecialPageUsingLowerCaseUserName() {
    String username = User.FORGOTTEN_PASSWORD.getUserName();
    String lowercaseUsername = Character.toLowerCase(username.charAt(0)) + username.substring(1);
    MailFunctions.deleteAllEmails(credentials.email, credentials.emailPassword);
    WikiBasePageObject base = new WikiBasePageObject();
    AttachedSignInPage signIn = new AttachedSignInPage().open();
    signIn.clickForgotPasswordLink().requestLinkForUsername(lowercaseUsername);
    String resetLink = base.getPasswordResetLink(credentials.email, credentials.emailPassword);
    ResetPasswordPage resetPass = new ResetPasswordPage(resetLink);
    resetPass.setNewPassword(User.FORGOTTEN_PASSWORD.getPassword());

    Assertion.assertTrue(resetPass.newPasswordSetSuccessfully());
  }
}
