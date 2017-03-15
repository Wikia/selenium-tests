package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends BaseAuthPage {

  @Getter(lazy = true)
  private final LoginArea loginArea = new LoginArea(driver);

  @FindBy(css = ".error")
  private WebElement errorMessage;
  @FindBy(css = ".forgotten-password")
  private WebElement forgottenPasswordLink;
  @FindBy(css = ".signup-providers li a")
  private WebElement connectWithFacebookButton;

  private Wait wait;

  public SignInPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.wait = new Wait(driver);
  }

  public String getErrorMessage() {
    wait.forElementVisible(errorMessage);

    return errorMessage.getText();
  }

  public SignInPage verifyErrorMessage(String errorMessage) {
    Assertion.assertEquals(getErrorMessage(), errorMessage);

    return this;
  }


  public SignInPage clickForgotPasswordLink() {
    wait.forElementClickable(forgottenPasswordLink);
    forgottenPasswordLink.click();

    return this;
  }

  public SignInPage isConnetctWithFacebookButtonVisible() {
    wait.forElementVisible(connectWithFacebookButton);

    return this;
  }

  public static class LoginArea extends BasePageObject {

      @FindBy(css = "#loginUsername")
      private WebElement usernameField;

      @FindBy(css = "#loginPassword")
      private WebElement passwordField;

      @FindBy(css = "#loginSubmit")
      private WebElement signInButton;

      @FindBy(css = "#loginSubmit:disabled")
      private WebElement disabledSignInButton;

      private WebDriver driver;
      private Wait wait;

      public LoginArea(WebDriver driver) {
          this.driver = driver;
          this.wait = new Wait(driver);

          PageFactory.initElements(driver, this);
      }

      public LoginArea typeUsername(String username) {
          wait.forElementVisible(usernameField);
          usernameField.sendKeys(username);

          return this;
      }

      public LoginArea typePassword(String password) {
          wait.forElementVisible(passwordField);
          passwordField.sendKeys(password);

          return this;
      }

      public ArticlePage clickSignInButtonToSignIn() {
          wait.forElementClickable(signInButton);
          signInButton.click();

          return new ArticlePage();
      }

      public SignInPage clickSignInButtonToGetError() {
          wait.forElementClickable(signInButton);
          signInButton.click();

          return new SignInPage(driver);
      }

      public LoginArea verifySignInButtonNotClickable() {
          wait.forElementVisible(disabledSignInButton);
          return this;
      }
  }
}

