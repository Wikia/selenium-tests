package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BaseAuthPage {

  @FindBy(css = ".forgotten-password")
  private WebElement forgottenPasswordLink;

  @FindBy(css = "#loginUsername")
  private WebElement usernameField;

  @FindBy(css = "#loginPassword")
  private WebElement passwordField;

  @FindBy(css = "#loginSubmit")
  private WebElement signInButton;

  @FindBy(css = "#loginSubmit:disabled")
  private WebElement disabledSignInButton;

  public ForgotPasswordPage clickForgotPasswordLink() {
    wait.forElementClickable(forgottenPasswordLink);
    forgottenPasswordLink.click();

    return new ForgotPasswordPage();
  }

  public SignInPage typeUsername(String username) {
    wait.forElementVisible(usernameField).sendKeys(username);
    return this;
  }

  public SignInPage typePassword(String password) {
    wait.forElementVisible(passwordField).sendKeys(password);
    return this;
  }

  public ArticlePage clickSignInButtonToSignIn() {
    wait.forElementClickable(signInButton).click();
    return new ArticlePage();
  }

  public SignInPage clickSignInButtonToGetError() {
    wait.forElementClickable(signInButton);
    signInButton.click();
    return this;
  }

  public SignInPage verifySignInButtonNotClickable() {
    wait.forElementVisible(disabledSignInButton);
    return this;
  }
}


