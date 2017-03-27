package com.wikia.webdriver.testcases.auth;

import com.wikia.webdriver.common.core.MailFunctions;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.AttachedSignInPage;
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
    AttachedSignInPage loginModal = new AttachedSignInPage();
    loginModal
      .clickForgotPasswordLink()
      .requestLinkForUsername(userName);
    loginModal.looseFocus();

    String resetLink = base.getPasswordResetLink(credentials.email, credentials.emailPassword);
    driver.get(resetLink);
    loginModal.isModalOpen();
    //loginModal.login(userName, newPassword);
    //loginModal.verifyUserLoggedIn(userName);
  }

  @Test(groups = "ForgottenPassword_anonCanRemindPasswordOnUserLoginSpecialPage")
  public void anonCanRemindPasswordOnUserLoginSpecialPage() {
    String userName = credentials.userNameForgottenPassword2;
    MailFunctions.deleteAllEmails(credentials.email, credentials.emailPassword);
    WikiBasePageObject base = new WikiBasePageObject();
    AttachedSignInPage signIn = new AttachedSignInPage();
    signIn.clickForgotPasswordLink();
    String newPassword = base.getPasswordResetLink(credentials.email, credentials.emailPassword);

    signIn
        .typeUsername(userName)
        .typePassword(newPassword)
        .clickSignInButton();

    signIn.verifyUserLoggedIn(userName);
  }

  @Test(groups = "ForgottenPassword_anonCanRemindPasswordOnUserLoginSpecialPageUsingLowerCaseUserName")
  public void anonCanRemindPasswordOnUserLoginSpecialPageUsingLowerCaseUserName() {
    String userNameUC = credentials.userNameForgottenPassword3;
    String userName = userNameUC.toLowerCase();
    MailFunctions.deleteAllEmails(credentials.email, credentials.emailPassword);
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(wikiURL);
    AttachedSignInPage login = base.openSpecialUserLogin(wikiURL);
    AttachedSignInPage signIn = new AttachedSignInPage();
    signIn.clickForgotPasswordLink();
    String
        newPassword =
        login.getPasswordResetLink(credentials.email, credentials.emailPassword);
    String verifyString = userName.substring(0, 1).toUpperCase() + userName.substring(1);

    signIn
        .typeUsername(userName)
        .typePassword(newPassword)
        .clickSignInButton()
        .verifyUserLoggedIn(verifyString);
  }
}
