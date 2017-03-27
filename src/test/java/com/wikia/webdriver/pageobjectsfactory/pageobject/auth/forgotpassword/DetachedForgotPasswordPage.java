package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword;

public class DetachedForgotPasswordPage implements ForgotPasswordPage {

  ForgotPasswordPage forgotPasswordPage;

  public DetachedForgotPasswordPage(ForgotPasswordPage forgotPasswordPage) {
    this.forgotPasswordPage = forgotPasswordPage;
  }

  public DetachedForgotPasswordPage() {
    this.forgotPasswordPage = new AttachedForgotPasswordPage();
  }

  @Override public void submit() {
    this.forgotPasswordPage.submit();
  }

  @Override public void requestLinkForUsername(String username) {
    this.forgotPasswordPage.requestLinkForUsername(username);
  }
}
