package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.AuthPageContext;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormError;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.AttachedForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.RegisterPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttachedSignInPage extends BasePageObject implements SignInPage {

  @FindBy(css = ".forgotten-password")
  private WebElement forgottenPasswordLink;
  @FindBy(id = "loginUsername")
  private WebElement usernameField;
  @FindBy(id = "loginPassword")
  private WebElement passwordField;
  @FindBy(id = "loginSubmit")
  private WebElement signInButton;

  private AuthPageContext authContext;

  public AttachedSignInPage() {
    authContext = new AuthPageContext();
  }

  public AttachedForgotPasswordPage clickForgotPasswordLink() {
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

  @Override public void login(String username, String password) {
    typeUsername(username);
    typePassword(password);
    submit();
  }

  @Override public void login(User user) {
    login(user.getUserName(), user.getPassword());

  }

  @Override public RegisterPage navigateToRegister() {
    return authContext.navigateToSignUp();
  }

  @Override public String getError() {
    return new FormError().getError();
  }

  @Override public void submit() {
    waitAndClick(signInButton);
  }

  @Override public AttachedSignInPage open() {
     getUrl(getWikiUrl() + URLsContent.USER_LOGIN);
     return this;
  }

  @Override public boolean isDisplayed() {
    return authContext.isHeaderDisplayed();
  }

  public FacebookSignupModalComponentObject clickFacebookSignUp() {
    return authContext.clickFacebookSignUp();
  }

  public boolean isConnectWithFacebookButtonVisible() {
    return authContext.isConnectWithFacebookButtonVisible();
  }

  @Override public boolean submitButtonNotClickable() {
    return !wait.forElementVisible(signInButton).isEnabled();
  }
}
