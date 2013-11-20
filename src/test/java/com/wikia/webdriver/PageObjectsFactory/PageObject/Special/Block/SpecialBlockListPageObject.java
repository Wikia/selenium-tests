package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Block;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

public class SpecialBlockListPageObject extends WikiBasePageObject{

	public SpecialBlockListPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="#mw-input-wpTarget")
	private WebElement userNameField;
	@FindBy(css="input.mw-htmlform-submit")
	private WebElement searchButton;
	@FindBy(css=".mw-blocklist")
	private WebElement blockListTable;
	@FindBy(xpath="//p[contains(text(), 'The requested IP address or username is not blocked.')]")
	private WebElement userUnblockedMessage;

	private void typeInUserName(String userName){
		waitForElementByElement(userNameField);
		userNameField.sendKeys(userName);
		PageObjectLogging.log("Special:BlockList typeInUserName", userName + " typed in username field", true);
	}

	private void clickSearchButton(){
		waitForElementByElement(searchButton);
		scrollAndClick(searchButton);
		PageObjectLogging.log("Special:BlockList clickSearchButton", "search button clicked", true);
	}

	public void searchForUser(String userName){
		typeInUserName(userName);
		clickSearchButton();
	}

	public void verifyUserUnblocked(){
		waitForElementByElement(userUnblockedMessage);
		PageObjectLogging.log("Special:BlockList verifyUSerUnblocked", "verified that user is not on blocked users list", true, driver);
	}

	public void verifyUserBlocked(String userName){
		waitForElementByCss("table td.TablePager_col_ipb_target a[href='/wiki/User:"+userName+"']");
		PageObjectLogging.log("Special:BlockList verifyUSerUnblocked", "verified that user is on blocked users list", true, driver);
	}
}
