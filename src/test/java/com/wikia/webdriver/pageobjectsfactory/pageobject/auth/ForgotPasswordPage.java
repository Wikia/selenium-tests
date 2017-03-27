package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

public interface ForgotPasswordPage {
  void submit();

  void requestLinkForUsername(String username);
}
