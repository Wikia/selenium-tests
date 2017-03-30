package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword;

import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.DetachedWindow;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormPage;

public class DetachedForgotPasswordPage extends DetachedWindow implements ForgotPasswordPage {

  private ForgotPasswordPage forgotPasswordPage;

  private String title = ForgotPasswordPage.pageTitle;

  public DetachedForgotPasswordPage(ForgotPasswordPage page) {
    forgotPasswordPage = page;
  }

  public DetachedForgotPasswordPage() {
    forgotPasswordPage = new AttachedForgotPasswordPage();
  }

  @Override public String getError() {
    gainFocus(title);
    return forgotPasswordPage.getError();

  }

  @Override public void submit() {
    gainFocus(title);
    forgotPasswordPage.submit();
  }

  @Override public FormPage open() {
    throw new UnsupportedOperationException("Error trying to open a detached window in old tab");
  }

  @Override public boolean isDisplayed() {
    gainFocus(title);
    return forgotPasswordPage.isDisplayed();
  }

  @Override public boolean submitButtonNotClickable() {
    return forgotPasswordPage.submitButtonNotClickable();
  }

  @Override public void requestLinkForUsername(String username) {
    gainFocus(title);
    forgotPasswordPage.requestLinkForUsername(username);
    loseFocus();
  }

}
