package com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.CreateWikiMessages;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.SignUpPageObject;


/**
 *
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class CreateNewWikiLogInSignUpPageObject extends WikiBasePageObject{

	public CreateNewWikiLogInSignUpPageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="div.UserLoginModal input[name='username']")
	WebElement userNameField;
	@FindBy(css="div.UserLoginModal input[name='password']")
	WebElement passwordField;
	@FindBy(css="div.UserLoginModal input[type='submit']")
	WebElement submitButton;
	@FindBy(css="div.UserLoginModal div.error-msg")
	WebElement usernameValidationText;
	@FindBy(css="form#SignupRedirect input[type='submit']")
	WebElement signUpSubmitButton;
	@FindBy(css="div.UserLoginModal div.input-group div.error-msg")
	WebElement errorMessage;

	public void typeInUserName(String userName) {
		userNameField.sendKeys(userName);
		PageObjectLogging.log("typeInUserName", "user name was typed", true);
	}

	public void typeInPassword(String password) {
		passwordField.sendKeys(password);
		PageObjectLogging.log("typeInPassword", "password name was typed", true);
	}

	public CreateNewWikiPageObjectStep2 submitLogin() {
		waitForElementByElement(submitButton);
		submitButton.click();
		PageObjectLogging.log("submitLogin", "submit button was clicked", true, driver);
		return new CreateNewWikiPageObjectStep2(driver);
	}

	public SignUpPageObject submitSignup() {
		waitForElementByElement(signUpSubmitButton);
		signUpSubmitButton.click();
		PageObjectLogging.log("submitSignUp", "signup submit button was clicked", true, driver);
		return new SignUpPageObject(driver);
	}

	public void verifyEmptyUserNameValidation() {
		waitForElementByElement(errorMessage);
		Assertion.assertEquals(CreateWikiMessages.blankUserNameErrorMessage, errorMessage.getText());
	}

	public void verifyInvalidUserNameValidation() {
		waitForElementByElement(errorMessage);
		Assertion.assertEquals(CreateWikiMessages.invalidUserNameErrorMessage, errorMessage.getText());
	}

	public void verifyBlankPasswordValidation() {
		waitForElementByElement(errorMessage);
		Assertion.assertEquals(CreateWikiMessages.blankPasswordErrorMessage, errorMessage.getText());
	}

	public void verifyInvalidPasswordValidation() {
		waitForElementByElement(errorMessage);
		Assertion.assertEquals(CreateWikiMessages.invalidPasswordErrorMessage, errorMessage.getText());
	}
}
