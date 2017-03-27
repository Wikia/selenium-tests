package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword;

public interface ForgotPasswordPage {
  void submit();

  void requestLinkForUsername(String username);
}
