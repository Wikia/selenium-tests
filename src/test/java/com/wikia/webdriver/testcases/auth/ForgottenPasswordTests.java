package com.wikia.webdriver.testcases.auth;

import com.wikia.webdriver.common.core.helpers.ForgottenPasswordUserFactory;
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

  private ForgottenPasswordUserFactory.ForgottenPasswordUser user = ForgottenPasswordUserFactory.user1();
  private ForgottenPasswordUserFactory.ForgottenPasswordUser userWithSpaces = ForgottenPasswordUserFactory.user2();

  @Test
  public void anonCanRemindPasswordFromAuthModal() {

    executeResetPasswordFlow(user);
  }

  @Test
  public void anonCanResetPasswordForUsernameWithSpaces() {
    executeResetPasswordFlow(userWithSpaces);
  }

  @Test
  public void anonCanRemindPasswordOnUserLoginSpecialPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    AttachedSignInPage signIn = new AttachedSignInPage().open();
    signIn.clickForgotPasswordLink().requestLinkForUsername(user.getUsername());
    String resetLink = base.getPasswordResetLink(user.getEmail(), user.getEmailPassword());
    ResetPasswordPage resetPass = new ResetPasswordPage(resetLink);
    resetPass.setNewPassword(user.getPassword());

    assertTrue(resetPass.newPasswordSetSuccessfully());
  }

  @Test
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

  private void executeResetPasswordFlow(ForgottenPasswordUserFactory.ForgottenPasswordUser user) {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(wikiURL);
    DetachedSignInPage loginModal = new DetachedSignInPage(new NavigationBar(driver).clickOnSignIn());
    loginModal
      .clickForgotPasswordLink()
      .requestLinkForUsername(user.getUsername());

    String resetLink = base.getPasswordResetLink(user.getEmail(), user.getEmailPassword());
    ResetPasswordPage resetPass = new ResetPasswordPage(resetLink);
    resetPass.setNewPassword(user.getPassword());

    assertTrue(resetPass.newPasswordSetSuccessfully());
  }
}
