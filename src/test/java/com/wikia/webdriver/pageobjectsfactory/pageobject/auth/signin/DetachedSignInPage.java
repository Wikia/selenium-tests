package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.AttachedForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.DetachedForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.ForgotPasswordPage;

public class DetachedSignInPage extends BasePageObject implements SignInPage {

  private AttachedSignInPage signIn = new AttachedSignInPage();

  @Override public ForgotPasswordPage clickForgotPasswordLink() {
    AttachedForgotPasswordPage forgotPassword = this.signIn.clickForgotPasswordLink();
    return new DetachedForgotPasswordPage(forgotPassword);
  }

  @Override public DetachedSignInPage typePassword(String password) {
    this.signIn.typePassword(password);
    return this;
  }
}
