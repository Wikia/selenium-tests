package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.BaseAuthPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.AttachedForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.ForgotPasswordPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttachedSignInPage extends BasePageObject implements SignInPage {

  @FindBy(css = ".forgotten-password")
  private WebElement forgottenPasswordLink;
  @FindBy(css = "#loginUsername")
  private WebElement usernameField;
  @FindBy(css = "#loginPassword")
  private WebElement passwordField;
  @FindBy(css = "#loginSubmit")
  private WebElement signInButton;

  public ForgotPasswordPage clickForgotPasswordLink() {
    wait.forElementClickable(forgottenPasswordLink);
    forgottenPasswordLink.click();

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
    wait.forElementClickable(signInButton).click();
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
}


