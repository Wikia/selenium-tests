package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register;

import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.DetachedWindow;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.DetachedSignInPage;

public class DetachedRegisterPage extends DetachedWindow implements RegisterPage {

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
    gainFocus();
    return registerPage.isDisplayed();
  }

  @Override public boolean submitButtonNotClickable() {
    return registerPage.submitButtonNotClickable();
  }

  @Override public RegisterPage typeEmailAddress(String email) {
    gainFocus();
    return registerPage.typeEmailAddress(email);
  }

  @Override public RegisterPage typeUsername(String username) {
    gainFocus();
    return registerPage.typeUsername(username);
  }

  @Override public RegisterPage typePassword(String password) {
    gainFocus();
    return registerPage.typePassword(password);
  }

  @Override public RegisterPage typeBirthdate(String month, String day, String year) {
    gainFocus();
    return registerPage.typeBirthdate(month, day, year);
  }

  @Override public String getError() {
    gainFocus();
    return registerPage.getError();
  }

  @Override public void submit() {
    gainFocus();
    registerPage.submit();
    loseFocus();
  }

  @Override public DetachedSignInPage navigateToSignIn() {
    gainFocus();
    return new DetachedSignInPage(registerPage.navigateToSignIn());
  }

  public FacebookSignupModalComponentObject clickFacebookSignUp() {
    gainFocus();
    return registerPage.clickFacebookSignUp();
  }

  public boolean isConnectWithFacebookButtonVisible() {
    gainFocus();
    return registerPage.isConnectWithFacebookButtonVisible();
  }

  @Override protected String getTitle() {
    return TITLE;
  }
}
