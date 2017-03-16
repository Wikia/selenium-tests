package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;


import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaseAuthPage extends WikiBasePageObject {

  @FindBy(css = ".signup-provider-facebook")
  private WebElement facebookSignUpButton;
  @FindBy(css = ".signup-providers li a")
  private WebElement connectWithFacebookButton;
  @FindBy(css = "#loginSubmit")
  private WebElement signInButton;
  @FindBy(css = "#loginSubmit:disabled")
  private WebElement disabledSignInButton;
  @FindBy(css = ".error")
  private WebElement errorMessage;
  @FindBy(css = "#loginUsername")
  private WebElement usernameField;
  @FindBy(css = "#signupPassword")
  private WebElement passwordField;
  @FindBy(className = "auth-header")
  private WebElement header;


  public FacebookSignupModalComponentObject clickFacebookSignUp() {
    wait.forElementClickable(facebookSignUpButton);
    facebookSignUpButton.click();
    PageObjectLogging.log("clickFacebookSignUp", "clicked on sign up with facebok button", true);
    return new FacebookSignupModalComponentObject();
  }

  public BaseAuthPage isConnetctWithFacebookButtonVisible() {
    wait.forElementVisible(connectWithFacebookButton);
    return this;
  }

  public BaseAuthPage clickSignInButton() {
    wait.forElementClickable(signInButton).click();
    return this;
  }

  private String getErrorMessage() {
    return wait.forElementVisible(errorMessage).getText();
  }

  public BaseAuthPage verifyErrorMessage(String errorMessage) {
    Assertion.assertEquals(getErrorMessage(), errorMessage);
    return this;
  }

  public BaseAuthPage typeUsername(String username) {
    fillInput(usernameField, username);
    return this;
  }

  public BaseAuthPage typePassword(String password) {
    fillInput(passwordField, password);
    return this;
  }

  public void verifySignInButtonNotClickable() {
    wait.forElementVisible(disabledSignInButton);
  }

}
