package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Block;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

public class SpecialUnblockPageObject extends WikiBasePageObject{

	@FindBy(css="#mw-input-wpTarget")
	private WebElement userNameField;
	@FindBy(css=".mw-htmlform-submit")
	private WebElement submitButton;
	@FindBy(xpath="//h1[contains(text(), 'Unblock')]")
	private WebElement unblockedUserHead;
//	@FindBy(xpath="//a[@class='new' and @href='/wiki/User:TooManyLogInAttempts' and contains(text(), 'TooManyLogInAttempts')]")
//	private WebElement unblockedUserMassage1;
//	@FindBy(xpath="//p[contains(text(), 'has been unblocked (')]")
//	private WebElement unblockedUserMassage2;
//	@FindBy(xpath="//a[@href='/wiki/Special:Block/TooManyLogInAttempts' and contains(text(), 're-block')]")
//	private WebElement unblockedUserMassage3;

	public SpecialUnblockPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
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
		waitForElementByXPath("//div[@id='mw-content-text']//p[contains(text(), 'has been unblocked (')]");
		waitForElementByXPath("//div[@id='mw-content-text']//a[@href='/wiki/Special:Block/"+userName+"' and contains(text(), 're-block')]");
		PageObjectLogging.log("verifyUnblockMessage", "unblock user messages verified", true, driver);
	}

}
