package com.wikia.webdriver.pageobjectsfactory.pageobject.special.block;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class SpecialUnblockPageObject extends WikiBasePageObject{

	@FindBy(css="#mw-input-wpTarget")
	private WebElement userNameField;
	@FindBy(css=".mw-htmlform-submit")
	private WebElement submitButton;
	@FindBy(xpath="//h1[contains(text(), 'Unblock')]")
	private WebElement unblockedUserHead;

	public SpecialUnblockPageObject(WebDriver driver) {
		super(driver);
	}

	private void typeInUserName(String userName){
		waitForElementByElement(userNameField);
		userNameField.sendKeys(userName);
		PageObjectLogging.log("typeInUserName", userName + "typed into username field", true);
	}

	private void clickSubmitButton(){
		waitForElementByElement(submitButton);
		scrollAndClick(submitButton);
		PageObjectLogging.log("clickSubmitButton", "submit button clicked", true);
	}

	public void unblockUser(String userName){
		typeInUserName(userName);
		clickSubmitButton();
	}

	public void verifyUnblockMessage(String userName){
		waitForElementByElement(unblockedUserHead);
		waitForElementByXPath("//div[@id='mw-content-text']//a[@href='/wiki/User:"+userName+"' and contains(text(), '"+userName+"')]");
		waitForElementByXPath("//div[@id='mw-content-text']//p[contains(text(), 'has been unblocked')]");
		PageObjectLogging.log("verifyUnblockMessage", "unblock user messages verified", true, driver);
	}

}
