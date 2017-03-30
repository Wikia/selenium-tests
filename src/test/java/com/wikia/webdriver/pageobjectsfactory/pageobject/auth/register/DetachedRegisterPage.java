package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register;

import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.DetachedWindow;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FacebookAuthContext;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.SignInPage;

public class DetachedRegisterPage extends DetachedWindow implements RegisterPage,
  FacebookAuthContext {

  private AttachedRegisterPage registerPage;
  private String title = RegisterPage.pageTitle;

  public DetachedRegisterPage() {
    registerPage = new AttachedRegisterPage();
  }

  public DetachedRegisterPage(AttachedRegisterPage registerPage) {
    registerPage = registerPage;
  }

  @Override public RegisterPage open() {
    throw new UnsupportedOperationException("Error trying to open a detached window in old tab");
  }

  @Override public boolean isDisplayed() {
    gainFocus(title);
    return registerPage.isDisplayed();
  }

  @Override public boolean submitButtonNotClickable() {
    return registerPage.submitButtonNotClickable();
  }

  @Override public RegisterPage typeEmailAddress(String email) {
    gainFocus(title);
    return registerPage.typeEmailAddress(email);
  }

  @Override public RegisterPage typeUsername(String username) {
    gainFocus(title);
    return registerPage.typeUsername(username);
  }

  @Override public RegisterPage typePassword(String password) {
    gainFocus(title);
    return registerPage.typePassword(password);
  }

  @Override public RegisterPage typeBirthdate(String month, String day, String year) {
    gainFocus(title);
    return registerPage.typeBirthdate(month, day, year);
  }

  @Override public String getError() {
    gainFocus(title);
    return registerPage.getError();
  }

  @Override public void submit() {
    gainFocus(title);
    registerPage.submit();
  }

  @Override public SignInPage navigateToSignIn() {
    gainFocus(title);
    return registerPage.navigateToSignIn();
  }

  @Override public FacebookSignupModalComponentObject clickFacebookSignUp() {
    gainFocus(title);
    return registerPage.clickFacebookSignUp();
  }

  @Override public boolean isConnetctWithFacebookButtonVisible() {
    gainFocus(title);
    return registerPage.isConnetctWithFacebookButtonVisible();
  }
}
