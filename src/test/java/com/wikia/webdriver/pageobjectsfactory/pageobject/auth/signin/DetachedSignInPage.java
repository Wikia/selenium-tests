package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.DetachedWindow;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FacebookAuthContext;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.AttachedForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.DetachedForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.ForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.RegisterPage;

public class DetachedSignInPage extends DetachedWindow implements SignInPage, FacebookAuthContext {

  private AttachedSignInPage signInPage;
  private static final String TITLE = "Sign in";

  public DetachedSignInPage() {
    signInPage = new AttachedSignInPage();
  }

  public DetachedSignInPage(AttachedSignInPage page) {
    signInPage = page;
  }

  @Override public ForgotPasswordPage clickForgotPasswordLink() {
    gainFocus(TITLE);
    AttachedForgotPasswordPage forgotPassword = signInPage.clickForgotPasswordLink();
    return new DetachedForgotPasswordPage(forgotPassword);
  }

  @Override public SignInPage typePassword(String password) {
    gainFocus(TITLE);
    signInPage.typePassword(password);
    return this;
  }

  @Override public void login(String username, String password) {
    gainFocus(TITLE);
    signInPage.login(username, password);
    loseFocus(TITLE);
  }

  @Override public void login(User user) {
    login(user.getUserName(), user.getPassword());
  }

  @Override public RegisterPage navigateToRegister() {
    gainFocus(TITLE);
    return signInPage.navigateToRegister();
  }

  @Override public String getError() {
    gainFocus(TITLE);
    return signInPage.getError();
  }

  @Override public void submit() {
    gainFocus(TITLE);
    signInPage.submit();
  }

  @Override public FormPage open() {
    throw new UnsupportedOperationException("Error trying to open a detached window in old tab");
  }

  @Override public boolean isDisplayed() {
    gainFocus(TITLE);
    return signInPage.isDisplayed();
  }

  @Override public boolean submitButtonNotClickable() {
    gainFocus(TITLE);
    return signInPage.submitButtonNotClickable();
  }

  @Override public FacebookSignupModalComponentObject clickFacebookSignUp() {
    gainFocus(TITLE);
    return signInPage.clickFacebookSignUp();
  }

  @Override public boolean isConnetctWithFacebookButtonVisible() {
    gainFocus(TITLE);
    return signInPage.isConnetctWithFacebookButtonVisible();
  }
}
