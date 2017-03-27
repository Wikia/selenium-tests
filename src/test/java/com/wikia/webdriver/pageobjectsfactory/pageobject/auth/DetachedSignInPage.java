package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class DetachedSignInPage extends BasePageObject {

  private AttachedSignInPage signIn = new AttachedSignInPage();

  public ForgotPasswordPage clickForgotPasswordLink() {
    AttachedForgotPasswordPage forgotPassword = this.signIn.clickForgotPasswordLink();
    return new DetachedForgotPasswordPage(forgotPassword);
  }

  public DetachedSignInPage typePassword(String password) {
    this.signIn.typePassword(password);
    return this;
  }
}
