package com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.SignUpPageObject;

public class CreateNewWikiLogInSignUpPageObject extends WikiBasePageObject {

  private static final String ERROR_MESSAGE_CSS =
      "div.UserLoginModal div.input-group div.error-msg";
  @FindBy(css = "div.UserLoginModal input[name='username']")
  WebElement userNameField;
  @FindBy(css = "div.UserLoginModal input[name='password']")
  WebElement passwordField;
  @FindBy(css = "div.UserLoginModal input[type='submit']")
  WebElement submitButton;
  @FindBy(css = "div.UserLoginModal div.error-msg")
  WebElement usernameValidationText;
  @FindBy(css = "form#SignupRedirect input[type='submit']")
  WebElement signUpSubmitButton;
  @FindBy(css = ".UserLoginModal .forgot-password")
  WebElement forgotPasswordLink;
  @FindBy(css = ERROR_MESSAGE_CSS)
  WebElement errorMessage;

  public CreateNewWikiLogInSignUpPageObject(WebDriver driver) {
    super();
  }

  public void typeInUserName(String userName) {
    userNameField.sendKeys(userName);
    PageObjectLogging.log("typeInUserName", "user name was typed", true);
  }

  public void typeInPassword(String password) {
    passwordField.sendKeys(password);
    PageObjectLogging.log("typeInPassword", "password name was typed", true);
  }

  public CreateNewWikiPageObjectStep2 submitLogin() {
    wait.forElementVisible(submitButton);
    submitButton.click();
    PageObjectLogging.log("submitLogin", "submit button was clicked", true, driver);
    return new CreateNewWikiPageObjectStep2(driver);
  }

  public SignUpPageObject submitSignup() {
    wait.forElementVisible(signUpSubmitButton);
    signUpSubmitButton.click();
    PageObjectLogging.log("submitSignUp", "signup submit button was clicked", true, driver);
    return new SignUpPageObject(driver);
  }

  public void verifyEmptyUserNameValidation() {
    wait.forElementVisible(By.cssSelector(ERROR_MESSAGE_CSS));
    Assertion.assertEquals(errorMessage.getText(), CreateWikiMessages.BLANK_USERNAME_ERROR_MESSAGE);
  }

  public void verifyInvalidUserNameValidation() {
    wait.forElementVisible(By.cssSelector(ERROR_MESSAGE_CSS));
    Assertion.assertEquals(errorMessage.getText(),
        CreateWikiMessages.INVALID_USERNAME_ERROR_MESSAGE);
  }

  public void verifyBlankPasswordValidation() {
    wait.forElementVisible(By.cssSelector(ERROR_MESSAGE_CSS));
    Assertion.assertEquals(errorMessage.getText(), CreateWikiMessages.BLANK_PASSWORD_ERROR_MESSAGE);
  }

  public void verifyInvalidPasswordValidation() {
    wait.forElementVisible(By.cssSelector(ERROR_MESSAGE_CSS));
    Assertion.assertEquals(errorMessage.getText(),
        CreateWikiMessages.INVALID_PASSWORD_ERROR_MESSAGE);
  }

  public void verifyMessageAboutNewPassword(String userName) {
    wait.forElementVisible(usernameValidationText);
    String newPasswordMsg = PageContent.NEW_PASSWORD_SENT_MESSAGE.replace("%userName%", userName);
    wait.forTextInElement(usernameValidationText, newPasswordMsg);
    PageObjectLogging.log("MessageAboutPasswordSent", "Message about new password sent present",
        true);
  }


}
