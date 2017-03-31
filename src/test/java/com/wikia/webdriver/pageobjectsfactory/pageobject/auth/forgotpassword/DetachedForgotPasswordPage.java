package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword;

import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.DetachedWindow;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormPage;

public class DetachedForgotPasswordPage extends DetachedWindow implements ForgotPasswordPage {

  private AttachedForgotPasswordPage forgotPasswordPage;

  private final static String TITLE = "Forgot password";

  public DetachedForgotPasswordPage(AttachedForgotPasswordPage page) {
    forgotPasswordPage = page;
  }

  public DetachedForgotPasswordPage() {
    forgotPasswordPage = new AttachedForgotPasswordPage();
  }

  @Override public String getError() {
    gainFocus(TITLE);
    return forgotPasswordPage.getError();

  }

  @Override public void submit() {
    gainFocus(TITLE);
    forgotPasswordPage.submit();
    loseFocus(TITLE);
  }

  @Override public FormPage open() {
    throw new UnsupportedOperationException("Error trying to open a detached window in old tab");
  }

  @Override public boolean isDisplayed() {
    gainFocus(TITLE);
    return forgotPasswordPage.isDisplayed();
  }

  @Override public boolean submitButtonNotClickable() {
    return forgotPasswordPage.submitButtonNotClickable();
  }

  @Override public void requestLinkForUsername(String username) {
    gainFocus(TITLE);
    forgotPasswordPage.requestLinkForUsername(username);
    if(forgotPasswordPage.isConfirmationDisplayed()) {
      loseFocus(TITLE);
    }
  }

}
