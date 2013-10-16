package com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.SignUpPageObject;


/**
 *
 * @author Karol
 *
 */
public class CreateNewWikiLogInSignUpPageObject extends BasePageObject{

	public CreateNewWikiLogInSignUpPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
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

	public void typeInUserName(String userName) {
		waitForElementByElement(userNameField);
		userNameField.sendKeys(userName);
		PageObjectLogging.log("typeInUserName", "user name was typed", true);
	}

	public void typeInPassword(String password) {
		waitForElementByElement(passwordField);
		passwordField.sendKeys(password);
		PageObjectLogging.log("typeInPassword", "password name was typed", true);
	}

	public CreateNewWikiPageObjectStep2 submitLogin() {
		waitForElementByElement(submitButton);
		scrollAndClick(submitButton);
		PageObjectLogging.log("submitLogin", "submit button was clicked", true, driver);
		return new CreateNewWikiPageObjectStep2(driver);
	}

	public SignUpPageObject submitSignup() {
		waitForElementByElement(signUpSubmitButton);
		scrollAndClick(signUpSubmitButton);
		PageObjectLogging.log("submitSignUp", "signup submit button was clicked", true, driver);
		return new SignUpPageObject(driver);
	}

	public void verifyEmptyUserNameValidation() {
		waitForElementByBy(By.cssSelector("div.UserLoginModal div.input-group div.error-msg"));
		waitForElementByElement(usernameValidationText);
		String text = usernameValidationText.getText();
		Assertion.assertEquals("Oops, please fill in the username field.", text);
		userNameField.clear();
	}

	public void verifyInvalidUserNameValidation() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.UserLoginModal div.input-group div.error-msg")));
		Assertion.assertEquals("Hm, we don't recognize this name. Don't forget usernames are case sensitive.", usernameValidationText.getText());
		userNameField.clear();
	}

	public void verifyBlankPasswordValidation() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.UserLoginModal div.input-group div.error-msg")));
		Assertion.assertEquals("Oops, please fill in the password field.", usernameValidationText.getText());
		userNameField.clear();
	}

	public void verifyInvalidPasswordValidation() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.UserLoginModal div.input-group div.error-msg")));
		Assertion.assertEquals("Oops, wrong password. Make sure caps lock is off and try again.", usernameValidationText.getText());
		userNameField.clear();
		passwordField.clear();
	}
}
