package com.wikia.webdriver.PageObjectsFactory.ComponentObject.ModalWindows;

import java.util.Set;

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
		// If policies are already accepted, give facebook popup window time to disappear
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			PageObjectLogging.log("acceptWikiaAppPolicy", e.getMessage(), false);
		}

		Set<String> handles = driver.getWindowHandles();

		if (handles.size()>1) {
			for(String winHandle : handles){
				//Switch to new window opened
				driver.switchTo().window(winHandle);
			}
			waitForElementByElement(appTermsConfirmButton);
			appTermsConfirmButton.click();
			PageObjectLogging.log("acceptWikiaAppPolicy", "confirmed wikia apps privacy policy", true);
			waitForElementByElement(postingPolicyWindowIndicator);
			waitForElementByElement(appTermsConfirmButton);
			appTermsConfirmButton.click();
			PageObjectLogging.log("acceptWikiaAppPolicy", "confirmed wikia apps posting policy", true);
			// Switch back to original browser (first window)
			driver.switchTo().window(winHandleBefore);
		}
		else {
			PageObjectLogging.log("acceptWikiaAppPolicy", "wikia apps policies allready accepted", true);
		}
	}

	public void typeUserName(String userName) {
		waitForElementByElement(usernameField);
		usernameField.sendKeys(userName);
		PageObjectLogging.log("typeUserName", "username "+userName+" typed into the field", true);
	}

	public void typePassword(String password) {
		waitForElementByElement(passwordField);
		passwordField.sendKeys(password);
		PageObjectLogging.log("typePassword", "password typed into the field", true);
	}

	public void createAccount() {
		waitForElementByElement(createAccountButton);
		createAccountButton.click();
		PageObjectLogging.log("createAccount", "Create account button clicked", true);
	}

}
