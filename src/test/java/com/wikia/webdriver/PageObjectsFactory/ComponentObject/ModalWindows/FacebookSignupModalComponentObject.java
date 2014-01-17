package com.wikia.webdriver.PageObjectsFactory.ComponentObject.ModalWindows;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
* @author Michal 'justnpT' Nowierski
*/
public class FacebookSignupModalComponentObject extends WikiBasePageObject {

	@FindBy(css = "button[name='__CONFIRM__']")
	private WebElement appTermsConfirmButton;
	@FindBy(css = "#u_1_7")
	private WebElement postingPolicyWindowIndicator;
	@FindBy(css = "#FacebookSignUp input[name='username']")
	private WebElement usernameField;
	@FindBy(css = "#FacebookSignUp input[name='password']")
	private WebElement passwordField;
	@FindBy(css = "#FacebookSignUp input[type='submit']")
	private WebElement createAccountButton;

	String winHandleBefore;

	public FacebookSignupModalComponentObject(WebDriver driver, String winHandleBeforeFBClick) {
		super(driver);
		winHandleBefore = winHandleBeforeFBClick;
	}

	public void acceptWikiaAppPolicy() {
		//Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		waitForElementByElement(appTermsConfirmButton);
		appTermsConfirmButton.click();
		PageObjectLogging.log("acceptWikiaAppPolicy", "confirmed wikia apps privacy policy", true);
		waitForElementByElement(postingPolicyWindowIndicator);
		waitForElementByElement(appTermsConfirmButton);
		appTermsConfirmButton.click();
		PageObjectLogging.log("acceptWikiaAppPolicy", "confirmed wikia apps posting policy", true);
		//Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);
	}

	public void typeUserName(String userName) {
		waitForElementByElement(usernameField);
		usernameField.sendKeys(userName);
		PageObjectLogging.log("typeUserName", "username "+userName+" typed into the field", true);
	}

	public void typePassword(String password) {
		waitForElementByElement(passwordField);
		usernameField.sendKeys(password);
		PageObjectLogging.log("typePassword", "password typed into the field", true);
	}

	public void createAccount() {
		waitForElementByElement(createAccountButton);
		createAccountButton.click();
		PageObjectLogging.log("createAccount", "Create account button clicked", true);
	}

}
