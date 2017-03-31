package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register;

import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.DetachedWindow;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FacebookAuthContext;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.DetachedSignInPage;

public class DetachedRegisterPage extends DetachedWindow implements RegisterPage,
  FacebookAuthContext {

  private AttachedRegisterPage registerPage;
  private static final String TITLE = "Join Fandom Today";

  public DetachedRegisterPage() {
    registerPage = new AttachedRegisterPage();
  }

  public DetachedRegisterPage(AttachedRegisterPage page) {
    registerPage = page;
  }

  @Override public RegisterPage open() {
    throw new UnsupportedOperationException("Error trying to open a detached window in old tab");
  }

  @Override public boolean isDisplayed() {
    gainFocus(TITLE);
    return registerPage.isDisplayed();
  }

  @Override public boolean submitButtonNotClickable() {
    return registerPage.submitButtonNotClickable();
  }

  @Override public RegisterPage typeEmailAddress(String email) {
    gainFocus(TITLE);
    return registerPage.typeEmailAddress(email);
  }

  @Override public RegisterPage typeUsername(String username) {
    gainFocus(TITLE);
    return registerPage.typeUsername(username);
  }

  @Override public RegisterPage typePassword(String password) {
    gainFocus(TITLE);
    return registerPage.typePassword(password);
  }

  @Override public RegisterPage typeBirthdate(String month, String day, String year) {
    gainFocus(TITLE);
    return registerPage.typeBirthdate(month, day, year);
  }

  @Override public String getError() {
    gainFocus(TITLE);
    return registerPage.getError();
  }

  @Override public void submit() {
    gainFocus(TITLE);
    registerPage.submit();
    loseFocus(TITLE);
  }

  @Override public DetachedSignInPage navigateToSignIn() {
    gainFocus(TITLE);
    return new DetachedSignInPage(registerPage.navigateToSignIn());
  }

  @Override public FacebookSignupModalComponentObject clickFacebookSignUp() {
    gainFocus(TITLE);
    return registerPage.clickFacebookSignUp();
  }

  @Override public boolean isConnetctWithFacebookButtonVisible() {
    gainFocus(TITLE);
    return registerPage.isConnetctWithFacebookButtonVisible();
  }
}
