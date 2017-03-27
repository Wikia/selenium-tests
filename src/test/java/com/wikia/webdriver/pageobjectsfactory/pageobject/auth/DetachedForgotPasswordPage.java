package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

public class DetachedForgotPasswordPage {

  ForgotPasswordPage forgotPasswordPage;

  public DetachedForgotPasswordPage(ForgotPasswordPage forgotPasswordPage) {
    this.forgotPasswordPage = forgotPasswordPage;
  }

  public DetachedForgotPasswordPage() {
    this.forgotPasswordPage = new ForgotPasswordPage();
  }

  public void submit() {
    this.forgotPasswordPage.submit();
  }

  public void requestLinkForUsername(String username) {
    this.forgotPasswordPage.requestLinkForUsername(username);
  }
}
