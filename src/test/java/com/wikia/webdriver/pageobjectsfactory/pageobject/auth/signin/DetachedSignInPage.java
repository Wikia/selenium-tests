package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.DetachedWindow;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FacebookAuthContext;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.DetachedForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.ForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.RegisterPage;

public class DetachedSignInPage extends DetachedWindow implements SignInPage, FacebookAuthContext {

  private AttachedSignInPage signInPage;
  private String title = SignInPage.pageTitle;

  public DetachedSignInPage() {
    signInPage = new AttachedSignInPage();
  }

  public DetachedSignInPage(AttachedSignInPage page) {
    signInPage = page;
  }

  @Override public ForgotPasswordPage clickForgotPasswordLink() {
    gainFocus(title);
    ForgotPasswordPage forgotPassword = signInPage.clickForgotPasswordLink();
    return new DetachedForgotPasswordPage(forgotPassword);
  }

  @Override public SignInPage typePassword(String password) {
    gainFocus(title);
    signInPage.typePassword(password);
    return this;
  }

  @Override public void login(String username, String password) {
    gainFocus(title);
    signInPage.login(username, password);
  }

  @Override public void login(User user) {
    login(user.getUserName(), user.getPassword());
  }

  @Override public RegisterPage navigateToRegister() {
    gainFocus(title);
    return signInPage.navigateToRegister();
  }

  @Override public String getError() {
    gainFocus(title);
    return signInPage.getError();
  }

  @Override public void submit() {
    gainFocus(title);
    signInPage.submit();
  }

  @Override public FormPage open() {
    throw new UnsupportedOperationException("Error trying to open a detached window in old tab");
  }

  @Override public boolean isDisplayed() {
    gainFocus(title);
    return signInPage.isDisplayed();
  }

  @Override public boolean submitButtonNotClickable() {
    gainFocus(title);
    return signInPage.submitButtonNotClickable();
  }

  @Override public FacebookSignupModalComponentObject clickFacebookSignUp() {
    gainFocus(title);
    return signInPage.clickFacebookSignUp();
  }

  @Override public boolean isConnetctWithFacebookButtonVisible() {
    gainFocus(title);
    return signInPage.isConnetctWithFacebookButtonVisible();
  }
}
