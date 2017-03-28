package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword;

import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormPage;

public class DetachedForgotPasswordPage implements ForgotPasswordPage {

  ForgotPasswordPage forgotPasswordPage;

  public DetachedForgotPasswordPage(ForgotPasswordPage forgotPasswordPage) {
    this.forgotPasswordPage = forgotPasswordPage;
  }

  public DetachedForgotPasswordPage() {
    this.forgotPasswordPage = new AttachedForgotPasswordPage();
  }

  @Override public String getError() {
    return this.forgotPasswordPage.getError();
  }

  @Override public void submit() {
    this.forgotPasswordPage.submit();
  }

  @Override public FormPage open() {
    return this.forgotPasswordPage.open();
  }

  @Override public void requestLinkForUsername(String username) {
    this.forgotPasswordPage.requestLinkForUsername(username);
  }
}
