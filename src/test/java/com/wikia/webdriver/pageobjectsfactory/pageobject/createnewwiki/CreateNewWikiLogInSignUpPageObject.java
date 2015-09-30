package com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki;

import com.wikia.webdriver.common.contentpatterns.ApiActions;
import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.SignUpPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
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
    super(driver);
  }

  public void typeInUserName(String userName) {
    userNameField.sendKeys(userName);
    LOG.success("typeInUserName", "user name was typed");
  }

  public void typeInPassword(String password) {
    passwordField.sendKeys(password);
    LOG.success("typeInPassword", "password name was typed");
  }

  public void clickForgotPassword(String userName, String apiToken) {
    Assertion.assertEquals(resetForgotPasswordTime(userName, apiToken),
        ApiActions.API_ACTION_FORGOT_PASSWORD_RESPONSE);
    wait.forElementVisible(forgotPasswordLink);
    forgotPasswordLink.click();
  }

  public CreateNewWikiPageObjectStep2 submitLogin() {
    wait.forElementVisible(submitButton);
    submitButton.click();
    LOG.logResult("submitLogin", "submit button was clicked", true, driver);
    return new CreateNewWikiPageObjectStep2(driver);
  }

  public SignUpPageObject submitSignup() {
    wait.forElementVisible(signUpSubmitButton);
    signUpSubmitButton.click();
    LOG.logResult("submitSignUp", "signup submit button was clicked", true, driver);
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
    LOG.result("MessageAboutPasswordSent",
               "Message about new password sent present",
               true);
  }


}
