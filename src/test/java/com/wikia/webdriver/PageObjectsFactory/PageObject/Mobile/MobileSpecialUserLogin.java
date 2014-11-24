package com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.MobilePageContent;
import com.wikia.webdriver.Common.Core.Assertion;

public class MobileSpecialUserLogin extends MobileBasePageObject {

	@FindBy(css=".wkErr")
	private WebElement errorMessage;

	public MobileSpecialUserLogin(WebDriver driver) {
		super(driver);
	}

	public void verifyWrongPasswordErrorMessage() {
		waitForElementByElement(errorMessage);
		Assertion.assertEquals(
				MobilePageContent.LOGIN_WRONG_PASSWORD_ERROR_MESSAGE,
				errorMessage.getText()
		);
	}

	public void verifyWrongLoginErrorMessage() {
		waitForElementByElement(errorMessage);
		Assertion.assertEquals(
				MobilePageContent.LOGIN_WRONG_LOGIN_ERROR_MESSAGE,
				errorMessage.getText()
		);
	}

	public void verifyEmptyPasswordErrorMessage() {
		waitForElementByElement(errorMessage);
		Assertion.assertEquals(
				MobilePageContent.LOGIN_EMPTY_PASSWORD_ERROR_MESSAGE,
				errorMessage.getText()
		);
	}
}
