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
  @FindBy(css = ".footer-callout-emphasis")
  private WebElement signInButton;
  @FindBy(css = ".error")
  private WebElement errorMessage;


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

  public SignInPage clickSignInButton() {
    wait.forElementClickable(signInButton);
    signInButton.click();

    return new SignInPage();
  }

  private String getErrorMessage() {
    return wait.forElementVisible(errorMessage).getText();
  }

  public BaseAuthPage verifyErrorMessage(String errorMessage) {
    Assertion.assertEquals(getErrorMessage(), errorMessage);
    return this;
  }

}
