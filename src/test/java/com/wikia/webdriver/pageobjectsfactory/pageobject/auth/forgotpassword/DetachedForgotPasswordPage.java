package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword;

import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.DetachedWindow;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormPage;

public class DetachedForgotPasswordPage extends DetachedWindow implements ForgotPasswordPage {

  private ForgotPasswordPage forgotPasswordPage;

  private String title = ForgotPasswordPage.pageTitle;

  public DetachedForgotPasswordPage(ForgotPasswordPage forgotPasswordPage) {
    this.forgotPasswordPage = forgotPasswordPage;
  }

  public DetachedForgotPasswordPage() {
    this.forgotPasswordPage = new AttachedForgotPasswordPage();
  }

  @Override public String getError() {
    gainFocus(this.title);
    return this.forgotPasswordPage.getError();

  }

  @Override public void submit() {
    gainFocus(this.title);
    this.forgotPasswordPage.submit();
  }

  @Override public FormPage open() {
    throw new UnsupportedOperationException("Error trying to open a detached window in old tab");
  }

  @Override public boolean isDisplayed() {
    gainFocus(this.title);
    return this.forgotPasswordPage.isDisplayed();
  }

  @Override public void requestLinkForUsername(String username) {
    gainFocus(this.title);
    this.forgotPasswordPage.requestLinkForUsername(username);
  }

}
