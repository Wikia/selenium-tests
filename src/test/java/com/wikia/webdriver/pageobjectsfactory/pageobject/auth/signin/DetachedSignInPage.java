package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.DetachedWindow;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.AttachedForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.DetachedForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.ForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.RegisterPage;

public class DetachedSignInPage extends DetachedWindow implements SignInPage {

  private AttachedSignInPage signInPage;
  private static final String TITLE = "Sign in";

  public DetachedSignInPage() {
    signInPage = new AttachedSignInPage();
  }

  public DetachedSignInPage(AttachedSignInPage page) {
    signInPage = page;
  }

  @Override public ForgotPasswordPage clickForgotPasswordLink() {
    gainFocus();
    AttachedForgotPasswordPage forgotPassword = signInPage.clickForgotPasswordLink();
    return new DetachedForgotPasswordPage(forgotPassword);
  }

  @Override public SignInPage typePassword(String password) {
    gainFocus();
    signInPage.typePassword(password);
    return this;
  }

  @Override public SignInPage typeUsername(String username) {
    gainFocus();
    signInPage.typeUsername(username);
    return this;
  }

  @Override public void login(String username, String password) {
    gainFocus();
    signInPage.login(username, password);
    loseFocus();
  }

  @Override public void login(User user) {
    login(user.getUserName(), user.getPassword());
  }

  @Override public RegisterPage navigateToRegister() {
    gainFocus();
    return signInPage.navigateToRegister();
  }

  @Override public String getError() {
    gainFocus();
    return signInPage.getError();
  }

  @Override public boolean isPasswordMasked() {
    gainFocus();
    return signInPage.isPasswordMasked();
  }

  @Override public void togglePasswordVisibility() {
    gainFocus();
    signInPage.togglePasswordVisibility();
  }

  @Override public boolean isSubmitButtonEnabled() {
    gainFocus();
    return signInPage.isSubmitButtonEnabled();
  }

  @Override public void submit() {
    gainFocus();
    signInPage.submit();
  }

  @Override public FormPage open() {
    throw new UnsupportedOperationException("Error trying to open a detached window in old tab");
  }

  @Override public boolean isDisplayed() {
    gainFocus();
    return signInPage.isDisplayed();
  }

  @Override public boolean submitButtonNotClickable() {
    gainFocus();
    return signInPage.submitButtonNotClickable();
  }

  public FacebookSignupModalComponentObject clickFacebookSignUp() {
    gainFocus();
    return signInPage.clickFacebookSignUp();
  }

  public boolean isConnectWithFacebookButtonVisible() {
    gainFocus();
    return signInPage.isConnectWithFacebookButtonVisible();
  }

  @Override protected String getTitle() {
    return TITLE;
  }
}
