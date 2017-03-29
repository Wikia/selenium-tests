package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword;

import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormPage;

public interface ForgotPasswordPage extends FormPage {

  String pageTitle = "Forgot password";

  void requestLinkForUsername(String username);
}
