package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.AttachedForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.DetachedForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.ForgotPasswordPage;

public class DetachedSignInPage extends BasePageObject implements SignInPage {

  private AttachedSignInPage signIn = new AttachedSignInPage();

  @Override public ForgotPasswordPage clickForgotPasswordLink() {
    ForgotPasswordPage forgotPassword = this.signIn.clickForgotPasswordLink();
    return new DetachedForgotPasswordPage(forgotPassword);
  }

  @Override public SignInPage typePassword(String password) {
    this.signIn.typePassword(password);
    return this;
  }

  @Override public void login(String username, String password) {

  }

  @Override public void login(User user) {

  }
}
