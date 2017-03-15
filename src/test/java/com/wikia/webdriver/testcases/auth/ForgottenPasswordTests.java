package com.wikia.webdriver.testcases.auth;

import com.wikia.webdriver.common.core.MailFunctions;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.SignInPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.AuthModal;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.testng.annotations.Test;

@Test(groups = "auth-forgottenPassword")
public class
ForgottenPasswordTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = "ForgottenPassword_anonCanRemindPasswordFromAuthModal")
  public void anonCanRemindPasswordFromAuthModal() {
    String userName = credentials.userNameForgottenPassword;
    MailFunctions.deleteAllEmails(credentials.email, credentials.emailPassword);
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(wikiURL);
    NavigationBar signInLink = new NavigationBar(driver);
    signInLink.clickOnSignIn();
    AuthModal loginModal = new AuthModal();
    loginModal.clickForgotPasswordLink();
    String newPassword = base.getPasswordResetLink(credentials.email, credentials.emailPassword);
    loginModal.login(userName, newPassword);
    loginModal.verifyUserLoggedIn(userName);
  }

  @Test(groups = "ForgottenPassword_anonCanRemindPasswordOnUserLoginSpecialPage")
  public void anonCanRemindPasswordOnUserLoginSpecialPage() {
    String userName = credentials.userNameForgottenPassword2;
    MailFunctions.deleteAllEmails(credentials.email, credentials.emailPassword);
    WikiBasePageObject base = new WikiBasePageObject();
    SignInPage signIn = new SignInPage(driver);
    signIn.clickForgotPasswordLink();
    String newPassword = base.getPasswordResetLink(credentials.email, credentials.emailPassword);

    signIn
        .getLoginArea()
        .typeUsername(userName)
        .typePassword(newPassword)
        .clickSignInButtonToSignIn()
        .verifyUserLoggedIn(userName);
  }

  @Test(groups = "ForgottenPassword_anonCanRemindPasswordOnUserLoginSpecialPageUsingLowerCaseUserName")
  public void anonCanRemindPasswordOnUserLoginSpecialPageUsingLowerCaseUserName() {
    String userNameUC = credentials.userNameForgottenPassword3;
    String userName = userNameUC.toLowerCase();
    MailFunctions.deleteAllEmails(credentials.email, credentials.emailPassword);
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(wikiURL);
    SignInPage login = base.openSpecialUserLogin(wikiURL);
    SignInPage signIn = new SignInPage(driver);
    signIn.clickForgotPasswordLink();
    String
        newPassword =
        login.getPasswordResetLink(credentials.email, credentials.emailPassword);
    String verifyString = userName.substring(0, 1).toUpperCase() + userName.substring(1);

    signIn
        .getLoginArea()
        .typeUsername(userName)
        .typePassword(newPassword)
        .clickSignInButtonToSignIn()
        .verifyUserLoggedIn(verifyString);
  }
}
