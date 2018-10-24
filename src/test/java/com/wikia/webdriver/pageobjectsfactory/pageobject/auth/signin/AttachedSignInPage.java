package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.GoogleSignupModalComponent;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.AttachedPageBase;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.AuthPageContext;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormError;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.AttachedForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.RegisterPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttachedSignInPage extends AttachedPageBase<AttachedSignInPage> implements SignInPage {

  @FindBy(css = ".forgotten-password")
  private WebElement forgottenPasswordLink;
  @FindBy(id = "loginUsername")
  private WebElement usernameField;
  @FindBy(id = "loginPassword")
  private WebElement passwordField;
  @FindBy(id = "loginSubmit")
  private WebElement signInButton;
  @FindBy(css = ".password-toggler")
  private WebElement passwordToggler;
  @FindBy(css = ".close")
  private WebElement closeButton;

  private AuthPageContext authContext;

  public AttachedSignInPage() {
    super(URLsContent.USER_LOGIN);
    authContext = new AuthPageContext();
  }

  @Override
  protected AttachedSignInPage getThis() { return this; }

  public AttachedForgotPasswordPage clickForgotPasswordLink() {
    waitAndClick(forgottenPasswordLink);
    return new AttachedForgotPasswordPage();
  }

  public SignInPage typePassword(String password) {
    wait.forElementClickable(passwordField).click();
    fillInput(passwordField, password);
    return this;
  }

  @Override
  public void close() {
    wait.forElementClickable(closeButton).click();
  }

  @Override
  public SignInPage typeUsername(String username) {
    wait.forElementClickable(usernameField).click();
    fillInput(usernameField, username);
    return this;
  }

  @Override
  public void login(String username, String password) {
    typeUsername(username);
    typePassword(password);
    submit();
  }

  @Override
  public void login(User user) {
    login(user.getUserName(), user.getPassword());
  }

  @Override
  public RegisterPage navigateToRegister() {
    return authContext.navigateToSignUp();
  }

  @Override
  public String getError() {
    return new FormError().getError();
  }

  @Override
  public boolean isPasswordMasked() {
    return "password".equals(passwordField.getAttribute("type"));
  }

  @Override
  public void togglePasswordVisibility() {
    waitAndClick(passwordToggler);
  }

  @Override
  public void submit() {
    waitAndClick(signInButton);
  }

  @Override
  public boolean isDisplayed() {
    return authContext.isHeaderDisplayed();
  }

  FacebookSignupModalComponentObject clickFacebookSignUp() {
    return authContext.clickFacebookSignUp();
  }

  public boolean isConnectWithFacebookButtonVisible() {
    return authContext.isConnectWithFacebookButtonVisible();
  }

  GoogleSignupModalComponent clickGoogleSignUp() {
    return authContext.clickGoogleSignUp();
  }

  public boolean isConnectWithGoogleButtonVisible() {
    return authContext.isConnectWithGoogleButtonVisible();
  }

  @Override
  public boolean submitButtonNotClickable() {
    return !wait.forElementVisible(signInButton).isEnabled();
  }
}
