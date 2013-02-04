package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class SpecialContributionsPageObject extends BasePageObject{

	public SpecialContributionsPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mw-contributions-table #user")
	private WebElement userNameRadio;
	@FindBy(css="[name='target']")
	private WebElement userNameField;
	@FindBy(css=".mw-contributions-table [type='submit']")
	private WebElement searchButton;

	public SpecialContributionsPageObject openContributionsPage(){
		getUrl(Global.DOMAIN+"wiki/Special:Contributions");
		PageObjectLogging.log("openContributionsPage", "contributions page is opened", true, driver);
		return new SpecialContributionsPageObject(driver);
	}
	
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
		clickAndWait(searchButton);
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
