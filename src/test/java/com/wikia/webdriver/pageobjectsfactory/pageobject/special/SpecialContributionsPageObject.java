package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;

public class SpecialContributionsPageObject extends SpecialPageObject {

	public SpecialContributionsPageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css=".mw-contributions-table #user")
	private WebElement userNameRadio;
	@FindBy(css="[name='target']")
	private WebElement userNameField;
	@FindBy(css=".mw-contributions-table [type='submit']")
	private WebElement searchButton;

	private void selectContributorUserName(){
		waitForElementByElement(userNameRadio);
		userNameRadio.click();
		PageObjectLogging.log("selectContributorUserName", "by username selected", true);
	}

	private void typeInUserName(String userName){
		waitForElementByElement(userNameField);
		userNameField.sendKeys(userName);
		PageObjectLogging.log("typeInUserName", userName+" username typed in", true);
	}

	private void clickSearchButton()
	{
		waitForElementByElement(searchButton);
		scrollAndClick(searchButton);
		PageObjectLogging.log("clickSearchButton", "search button clicked", true);
	}

	public void searchContributions(String userName){
		selectContributorUserName();
		typeInUserName(userName);
		clickSearchButton();
	}

	public void verifyNewPageOnList(String pageName, String pageContent){
		waitForElementByXPath("//a[@title='"+pageName+"' and contains(text(), '"+pageName+"')]");
		waitForElementByXPath("//span[@class='comment' and contains(text(), '(Created page with \""+pageContent+"\")')]");
		PageObjectLogging.log("verifyNewPageOnList", pageName+" page verified on the contribution list", true);
	}
}
