package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.AuthPageContext;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FacebookAuthContext;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.AttachedForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.ForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.RegisterPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttachedSignInPage extends BasePageObject implements SignInPage, FacebookAuthContext {

  @FindBy(css = ".forgotten-password")
  private WebElement forgottenPasswordLink;
  @FindBy(css = "#loginUsername")
  private WebElement usernameField;
  @FindBy(css = "#loginPassword")
  private WebElement passwordField;
  @FindBy(css = "#loginSubmit")
  private WebElement signInButton;

  private AuthPageContext authContext;

  public AttachedSignInPage() {
    this.authContext = new AuthPageContext();
  }

  public ForgotPasswordPage clickForgotPasswordLink() {
    waitAndClick(forgottenPasswordLink);
    return new AttachedForgotPasswordPage();
  }

  public SignInPage typePassword(String password) {
    fillInput(passwordField, password);
    return this;
  }

  public SignInPage typeUsername(String username) {
    fillInput(usernameField, username);
    return this;
  }

  private SignInPage clickSignInButton() {
    waitAndClick(signInButton);
    return this;
  }

  @Override public void login(String username, String password) {
    typeUsername(username);
    typePassword(password);
    clickSignInButton();
  }

  @Override public void login(User user) {
    login(user.getUserName(), user.getPassword());

  }

  @Override public RegisterPage navigateToRegister() {
    return this.authContext.navigateToSignUp();
  }

  @Override public FacebookSignupModalComponentObject clickFacebookSignUp() {
    return this.authContext.clickFacebookSignUp();
  }

  @Override public boolean isConnetctWithFacebookButtonVisible() {
    return this.authContext.isConnetctWithFacebookButtonVisible();
  }
}


