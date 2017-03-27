package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin;

import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.ForgotPasswordPage;

/**
 * Created by ogolec on 27.03.17.
 */
public interface SignInPage {
  ForgotPasswordPage clickForgotPasswordLink();

  DetachedSignInPage typePassword(String password);
}
