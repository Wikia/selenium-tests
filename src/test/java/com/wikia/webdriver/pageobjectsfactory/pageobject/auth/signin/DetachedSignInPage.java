package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FacebookAuthContext;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.DetachedForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.ForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.RegisterPage;

public class DetachedSignInPage extends BasePageObject implements SignInPage, FacebookAuthContext {

  private AttachedSignInPage signInPage;

  public DetachedSignInPage() {
    this.signInPage = new AttachedSignInPage();
  }

  public DetachedSignInPage(AttachedSignInPage signInPage) {
    this.signInPage = signInPage;
  }

  @Override public ForgotPasswordPage clickForgotPasswordLink() {
    ForgotPasswordPage forgotPassword = this.signInPage.clickForgotPasswordLink();
    return new DetachedForgotPasswordPage(forgotPassword);
  }

  @Override public SignInPage typePassword(String password) {
    this.signInPage.typePassword(password);
    return this;
  }

  @Override public void login(String username, String password) {
    this.signInPage.login(username, password);
  }

  @Override public void login(User user) {
    this.login(user.getUserName(), user.getPassword());
  }

  @Override public RegisterPage navigateToRegister() {
    return this.signInPage.navigateToRegister();
  }

  @Override public FacebookSignupModalComponentObject clickFacebookSignUp() {
    return this.signInPage.clickFacebookSignUp();
  }

  @Override public boolean isConnetctWithFacebookButtonVisible() {
    return this.signInPage.isConnetctWithFacebookButtonVisible();
  }
}
