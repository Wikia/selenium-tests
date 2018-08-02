package com.wikia.webdriver.testcases.desktop.auth;

import com.wikia.webdriver.common.core.EmailUtils;
import com.wikia.webdriver.common.core.helpers.UserWithEmail;
import com.wikia.webdriver.common.core.helpers.UserWithEmailFactory;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.ResetPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.elements.communities.desktop.components.navigation.global.GlobalNavigation;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.wikia.webdriver.common.core.Assertion.assertTrue;

@Test(groups = "auth-forgotten-password")
public class ForgottenPasswordTests extends NewTestTemplate {

  private UserWithEmail user = UserWithEmailFactory.getUser();
  private UserWithEmail userWithSpaces = UserWithEmailFactory.getUserWithSpaces();

  @BeforeMethod
  @AfterMethod
  private void cleanUpEmails() {
    EmailUtils.deleteAllEmails(user.getEmail(), user.getEmailPassword());
    EmailUtils.deleteAllEmails(userWithSpaces.getEmail(), userWithSpaces.getEmailPassword());
  }

  public void anonCanRemindPasswordFromAuthModal() {
    executeResetPasswordFlow(user);
    assertTrue(setNewPasswordForUser(user).newPasswordSetSuccessfully());
  }

  public void anonCanRemindPasswordOnUserLoginSpecialPage() {
    requestPasswordResetOnModal(user.getUsername());
    assertTrue(setNewPasswordForUser(user).newPasswordSetSuccessfully());
  }

  public void anonCanRemindPasswordOnUserLoginSpecialPageUsingLowerCaseUserName() {
    String lowercaseUsername = getLowercaseFirstLetterUsername(user);
    requestPasswordResetOnModal(lowercaseUsername);
    assertTrue(setNewPasswordForUser(user).newPasswordSetSuccessfully());
  }

  private String getLowercaseFirstLetterUsername(UserWithEmail user) {
    return String.format("%s%s",
                         Character.toLowerCase(user.getUsername().charAt(0)),
                         user.getUsername().substring(1)
    );
  }

  private void requestPasswordResetOnModal(String username) {
    new AttachedSignInPage().open().clickForgotPasswordLink().requestLinkForUsername(username);
  }

  private ResetPasswordPage setNewPasswordForUser(UserWithEmail user) {
    String resetLink = BasePageObject.getPasswordResetLink(user.getEmail(),
                                                           user.getEmailPassword()
    );
    ResetPasswordPage resetPass = new ResetPasswordPage(resetLink);
    resetPass.setNewPassword(user.getPassword());
    return resetPass;
  }

  private void executeResetPasswordFlow(UserWithEmail user) {
    new GlobalNavigation().clickOnSignIn();
    new AttachedSignInPage().clickForgotPasswordLink().requestLinkForUsername(user.getUsername());
  }
}
