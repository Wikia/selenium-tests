package com.wikia.webdriver.pageobjectsfactory.pageobject.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.contentpatterns.MobilePageContent;
import com.wikia.webdriver.common.core.Assertion;

public class MobileSpecialUserLogin extends MobileBasePageObject {

	@FindBy(css=".wkErr")
	private WebElement errorMessage;

	public MobileSpecialUserLogin(WebDriver driver) {
		super(driver);
	}

	public void verifyWrongPasswordErrorMessage() {
		waitForElementByElement(errorMessage);
		Assertion.assertEquals(
				MobilePageContent.loginWrongPasswordErrorMessage,
				errorMessage.getText()
		);
	}

	public void verifyWrongLoginErrorMessage() {
		waitForElementByElement(errorMessage);
		Assertion.assertEquals(
				MobilePageContent.loginWrongLoginErrorMessage,
				errorMessage.getText()
		);
	}

	public void verifyEmptyPasswordErrorMessage() {
		waitForElementByElement(errorMessage);
		Assertion.assertEquals(
				MobilePageContent.loginEmptyPasswordErrorMessage,
				errorMessage.getText()
		);
	}
}
