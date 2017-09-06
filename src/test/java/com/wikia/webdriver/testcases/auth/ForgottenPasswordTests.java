package com.wikia.webdriver.testcases.auth;

import com.wikia.webdriver.common.core.EmailUtils;
import com.wikia.webdriver.common.core.helpers.UserWithEmailPool;
import com.wikia.webdriver.common.core.helpers.UserWithEmail;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.ResetPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.DetachedSignInPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.wikia.webdriver.common.core.Assertion.assertTrue;

@Test(groups = "auth-forgotten-password")
public class ForgottenPasswordTests extends NewTestTemplate {

  private UserWithEmailPool userPool = new UserWithEmailPool();
  private UserWithEmail user = userPool.getUser();
  private UserWithEmail userWithSpaces = userPool.getUserWithSpaces();

  @BeforeMethod
  @AfterMethod
  private void cleanUpEmails() {
    EmailUtils.deleteAllEmails(user.getEmail(), user.getEmailPassword());
    EmailUtils.deleteAllEmails(userWithSpaces.getEmail(), userWithSpaces.getEmailPassword());
  }

  public void anonCanRemindPasswordFromAuthModal() {
    executeResetPasswordFlow(user);
  }

  public void anonCanResetPasswordForUsernameWithSpaces() {
    executeResetPasswordFlow(userWithSpaces);
  }

  public void anonCanRemindPasswordOnUserLoginSpecialPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    AttachedSignInPage signIn = new AttachedSignInPage().open();
    signIn.clickForgotPasswordLink().requestLinkForUsername(user.getUsername());
    String resetLink = base.getPasswordResetLink(user.getEmail(), user.getEmailPassword());
    ResetPasswordPage resetPass = new ResetPasswordPage(resetLink);
    resetPass.setNewPassword(user.getPassword());

    assertTrue(resetPass.newPasswordSetSuccessfully());
  }

  public void anonCanRemindPasswordOnUserLoginSpecialPageUsingLowerCaseUserName() {
    String lowercaseUsername = Character.toLowerCase(user.getUsername().charAt(0))
          + user.getUsername().substring(1);

    WikiBasePageObject base = new WikiBasePageObject();
    AttachedSignInPage signIn = new AttachedSignInPage().open();
    signIn.clickForgotPasswordLink().requestLinkForUsername(lowercaseUsername);
    String resetLink = base.getPasswordResetLink(user.getEmail(), user.getEmailPassword());
    ResetPasswordPage resetPass = new ResetPasswordPage(resetLink);
    resetPass.setNewPassword(user.getPassword());

    assertTrue(resetPass.newPasswordSetSuccessfully());
  }

  private void executeResetPasswordFlow(UserWithEmail user) {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(wikiURL);
    DetachedSignInPage loginModal = new DetachedSignInPage(new NavigationBar().clickOnSignIn());
    loginModal
      .clickForgotPasswordLink()
      .requestLinkForUsername(user.getUsername());

    String resetLink = base.getPasswordResetLink(user.getEmail(), user.getEmailPassword());
    ResetPasswordPage resetPass = new ResetPasswordPage(resetLink);
    resetPass.setNewPassword(user.getPassword());

    assertTrue(resetPass.newPasswordSetSuccessfully());
  }
}
