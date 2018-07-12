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

  @Override
  public ForgotPasswordPage clickForgotPasswordLink() {
    AttachedForgotPasswordPage forgotPassword = signInPage.clickForgotPasswordLink();
    return new DetachedForgotPasswordPage(forgotPassword);
  }

  @Override
  public SignInPage typePassword(String password) {
    signInPage.typePassword(password);
    return this;
  }

  @Override
  public SignInPage typeUsername(String username) {
    signInPage.typeUsername(username);
    return this;
  }

  @Override
  public void login(String username, String password) {
    signInPage.login(username, password);
  }

  @Override
  public void login(User user) {
    login(user.getUserName(), user.getPassword());
  }

  @Override
  public RegisterPage navigateToRegister() {
    return signInPage.navigateToRegister();
  }

  @Override
  public String getError() {
    return signInPage.getError();
  }

  @Override
  public boolean isPasswordMasked() {
    return signInPage.isPasswordMasked();
  }

  @Override
  public void togglePasswordVisibility() {
    signInPage.togglePasswordVisibility();
  }

  @Override
  public void submit() {
    signInPage.submit();
  }

  @Override
  public FormPage open() {
    throw new UnsupportedOperationException("Error trying to open a detached window in old tab");
  }

  @Override
  public boolean isDisplayed() {
    return signInPage.isDisplayed();
  }

  @Override
  public boolean submitButtonNotClickable() {
    return signInPage.submitButtonNotClickable();
  }

  public FacebookSignupModalComponentObject clickFacebookSignUp() {
    return signInPage.clickFacebookSignUp();
  }

  public boolean isConnectWithFacebookButtonVisible() {
    return signInPage.isConnectWithFacebookButtonVisible();
  }

  @Override
  protected String getTitle() {
    return TITLE;
  }
}
