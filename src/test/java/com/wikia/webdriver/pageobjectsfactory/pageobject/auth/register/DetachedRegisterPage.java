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

  @Override public boolean isPasswordMasked() {
    gainFocus();
    return registerPage.isPasswordMasked();
  }

  @Override public void togglePasswordVisibility() {
    gainFocus();
    registerPage.togglePasswordVisibility();
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

  @Override public RegisterPage typeBirthday(int month, int day, int year) {
    gainFocus();
    return registerPage.typeBirthday(month, day, year);
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

  @Override public void signUp(String email, String username, String password, LocalDate birthday) {
    gainFocus();
    registerPage.signUp(email, username, password, birthday);
    loseFocus();
  }

  @Override public void signUp(SignUpUser user) {
    signUp(user.getEmail(), user.getUsername(), user.getPassword(), user.getBirthday());
  }

  @Override public RegisterPage fillForm(SignUpUser user) {
    gainFocus();
    return registerPage.fillForm(user);
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
