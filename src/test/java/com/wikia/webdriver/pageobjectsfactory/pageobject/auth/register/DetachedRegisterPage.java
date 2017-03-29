package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register;

import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FacebookAuthContext;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.SignInPage;

public class DetachedRegisterPage extends BasePageObject implements RegisterPage,
  FacebookAuthContext {

  private AttachedRegisterPage registerPage;

  public DetachedRegisterPage() {
    this.registerPage = new AttachedRegisterPage();
  }

  public DetachedRegisterPage(AttachedRegisterPage registerPage) {
    this.registerPage = registerPage;
  }

  @Override public AttachedRegisterPage open() {
    return null;
  }

  @Override public boolean isDisplayed() {
    return this.registerPage.isDisplayed();
  }

  @Override public AttachedRegisterPage typeEmailAddress(String email) {
    return null;
  }

  @Override public AttachedRegisterPage typeUsername(String username) {
    return null;
  }

  @Override public AttachedRegisterPage typePassword(String password) {
    return null;
  }

  @Override public AttachedRegisterPage typeBirthdate(String month, String day, String year) {
    return null;
  }

  @Override public String getError() {
    return this.registerPage.getError();
  }

  @Override public void submit() {
    this.registerPage.submit();
  }

  @Override public SignInPage navigateToSignIn() {
    return this.registerPage.navigateToSignIn();
  }

  @Override public FacebookSignupModalComponentObject clickFacebookSignUp() {
    return this.registerPage.clickFacebookSignUp();
  }

  @Override public boolean isConnetctWithFacebookButtonVisible() {
    return this.registerPage.isConnetctWithFacebookButtonVisible();
  }
}
