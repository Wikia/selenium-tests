package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.ForgotPasswordPage;

public interface SignInPage {

  ForgotPasswordPage clickForgotPasswordLink();

  SignInPage typePassword(String password);

  void login(String username, String password);

  void login(User user);


}
