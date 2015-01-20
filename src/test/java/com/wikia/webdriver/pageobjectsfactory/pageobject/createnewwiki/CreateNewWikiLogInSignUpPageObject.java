package com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.SignUpPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class CreateNewWikiLogInSignUpPageObject extends WikiBasePageObject {

	public CreateNewWikiLogInSignUpPageObject(WebDriver driver) {
		super(driver);
	}

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

	private static final String ERROR_MESSAGE_CSS = "div.UserLoginModal div.input-group div.error-msg";
	@FindBy(css = ERROR_MESSAGE_CSS)
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
		waitForElementByCss(ERROR_MESSAGE_CSS);
		Assertion.assertEquals(CreateWikiMessages.BLANK_USERNAME_ERROR_MESSAGE, errorMessage.getText());
	}

	public void verifyInvalidUserNameValidation() {
		waitForElementByCss(ERROR_MESSAGE_CSS);
		Assertion.assertEquals(CreateWikiMessages.INVALID_USERNAME_ERROR_MESSAGE, errorMessage.getText());
	}

	public void verifyBlankPasswordValidation() {
		waitForElementByCss(ERROR_MESSAGE_CSS);
		Assertion.assertEquals(CreateWikiMessages.BLANK_PASSWORD_ERROR_MESSAGE, errorMessage.getText());
	}

	public void verifyInvalidPasswordValidation() {
		waitForElementByCss(ERROR_MESSAGE_CSS);
		Assertion.assertEquals(CreateWikiMessages.INVALID_PASSWORD_ERROR_MESSAGE, errorMessage.getText());
	}
}
