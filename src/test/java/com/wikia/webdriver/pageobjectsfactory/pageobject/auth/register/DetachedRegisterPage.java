package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register;

import com.wikia.webdriver.common.core.helpers.SignUpUser;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.DetachedWindow;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.DetachedSignInPage;

import java.time.LocalDate;

public class DetachedRegisterPage extends DetachedWindow implements RegisterPage {

  private AttachedRegisterPage registerPage;
  private static final String TITLE = "Join FANDOM Today";

  public DetachedRegisterPage() {
    registerPage = new AttachedRegisterPage();
  }

  public DetachedRegisterPage(AttachedRegisterPage page) {
    registerPage = page;
  }

  @Override
  public RegisterPage open() {
    throw new UnsupportedOperationException("Error trying to open a detached window in old tab");
  }

  @Override
  public boolean isDisplayed() {
    return registerPage.isDisplayed();
  }

  @Override
  public boolean submitButtonNotClickable() {
    return registerPage.submitButtonNotClickable();
  }

  @Override
  public boolean isPasswordMasked() {
    return registerPage.isPasswordMasked();
  }

  @Override
  public void togglePasswordVisibility() {
    registerPage.togglePasswordVisibility();
  }

  @Override
  public RegisterPage typeEmailAddress(String email) {
    return registerPage.typeEmailAddress(email);
  }

  @Override
  public RegisterPage typeUsername(String username) {
    return registerPage.typeUsername(username);
  }

  @Override
  public RegisterPage typePassword(String password) {
    return registerPage.typePassword(password);
  }

  @Override
  public RegisterPage typeBirthday(int month, int day, int year) {
    return registerPage.typeBirthday(month, day, year);
  }

  @Override
  public String getError() {
    return registerPage.getError();
  }

  @Override
  public void submit() {
    registerPage.submit();
  }

  @Override
  public DetachedSignInPage navigateToSignIn() {
    return new DetachedSignInPage(registerPage.navigateToSignIn());
  }

  @Override
  public void signUp(String email, String username, String password, LocalDate birthday) {
    registerPage.signUp(email, username, password, birthday);
  }

  @Override
  public void signUp(SignUpUser user) {
    signUp(user.getEmail(), user.getUsername(), user.getPassword(), user.getBirthday());
  }

  @Override
  public RegisterPage fillForm(SignUpUser user) {
    return registerPage.fillForm(user);
  }

  public FacebookSignupModalComponentObject clickFacebookSignUp() {
    return registerPage.clickFacebookSignUp();
  }

  public boolean isConnectWithFacebookButtonVisible() {
    return registerPage.isConnectWithFacebookButtonVisible();
  }

  @Override
  protected String getTitle() {
    return TITLE;
  }
}
