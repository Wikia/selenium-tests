package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword;

import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormPage;

public interface ForgotPasswordPage extends FormPage {

  void requestLinkForUsername(String username);
}
