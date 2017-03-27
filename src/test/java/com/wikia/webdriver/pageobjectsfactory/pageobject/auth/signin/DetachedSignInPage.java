package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.DetachedForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.ForgotPasswordPage;

public class DetachedSignInPage extends BasePageObject implements SignInPage {

  private SignInPage signInPage;

  public DetachedSignInPage() {
    this.signInPage = new AttachedSignInPage();
  }

  public DetachedSignInPage(SignInPage signInPage) {
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

  }

  @Override public void login(User user) {

  }
}
