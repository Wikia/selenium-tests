package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.ContentPatterns.ApiActions;
import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialPageObject;

/**
 *
 * @author Karol 'kkarolk' Kujawiak
 *
 */

public class SpecialUserLoginPageObject extends SpecialPageObject {

	public SpecialUserLoginPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".WikiaArticle input[name='username']")
	private WebElement userName;
	@FindBy(css=".WikiaArticle input[name='password']")
	private WebElement password;
	@FindBy(css=".WikiaArticle input[name='newpassword']")
	private WebElement newPassword;
	@FindBy(css=".WikiaArticle input[name='retype']")
	private WebElement retypeNewPassword;
	@FindBy(css=".WikiaArticle input.login-button.big")
	private WebElement loginButton;
	@FindBy(css=".WikiaArticle .forgot-password")
	private WebElement forgotPasswordLink;
	@FindBy (css=".UserLogin .error-msg")
	private WebElement messagePlaceholder;

	private final String disabledAccountMessage = "Your account has been disabled by Wikia.";

	private void typeInUserName(String name){
		waitForElementByElement(userName);
		userName.clear();
		userName.sendKeys(name);
		PageObjectLogging.log("typeInUserName", name+" user name typed", true);
	}

	private void typeInPassword(String pass){
		waitForElementByElement(password);
		password.clear();
		password.sendKeys(pass);
		PageObjectLogging.log("typeInUserPassword", "password typed", true);
	}

	private void typeInNewPassword(String pass){
		waitForElementByElement(newPassword);
		newPassword.sendKeys(pass);
		PageObjectLogging.log("typeInNewPassword", "new password retyped", true, driver);
	}

	private void retypeInNewPassword(String pass){
		waitForElementByElement(retypeNewPassword);
		retypeNewPassword.sendKeys(pass);
		PageObjectLogging.log("typeInNewPassword", "new password retyped", true, driver);
	}

	private void clickLoginButton(){
		waitForElementByElement(loginButton);
		loginButton.click();
		PageObjectLogging.log("clickLoginButton", "login button clicked", true);
	}

	private void clickForgotPasswordLink(){
		waitForElementByElement(forgotPasswordLink);
		scrollAndClick(forgotPasswordLink);
	}

	public void loginAndVerify(String name, String password){
		openSpecialUserLogin();
		login(name, password);
		verifyUserLoggedIn(name);
	}

	public void loginAndVerify(String name, String password, String wikiURL){
		openSpecialUserLogin(wikiURL);
		login(name, password);
		verifyUserLoggedIn(name);
	}

	public void loginAndVerifyOnWiki(String name, String pass, String url) {
		openSpecialUserLoginOnWiki(url);
		login(name, pass);
		verifyUserLoggedIn(name);
	}

	public void login(String name, String pass){
		typeInUserName(name);
		typeInPassword(pass);
		clickLoginButton();
	}

	public void remindPassword(String name){
		Assertion.assertEquals(
			ApiActions.apiActionForgotPasswordResponse,
			resetForgotPasswordTime(name));
		typeInUserName(name);
		clickForgotPasswordLink();
	}

	public String setNewPassword() {
		String password = Properties.password + getTimeStamp();
		typeInNewPassword(password);
		retypeInNewPassword(password);
		clickLoginButton();
		PageObjectLogging.log(
			"setNewPassword",
			"new password is set",
			true, driver
		);
		return password;
	}

	public void verifyMessageAboutNewPassword(String userName) {
		waitForElementByElement(messagePlaceholder);
		String message = PageContent.newPasswordSentMessage.replace("%userName%", userName);
		waitForTextToBePresentInElementByElement(messagePlaceholder, message);
		PageObjectLogging.log(
			"newPasswordSentMessage",
			"Message about new password sent present",
			true, driver
		);
	}

	public void verifyClosedAccountMessage() {
		waitForElementByElement(messagePlaceholder);
		Assertion.assertEquals(
				disabledAccountMessage,
				messagePlaceholder.getText()
		);
	}
}
