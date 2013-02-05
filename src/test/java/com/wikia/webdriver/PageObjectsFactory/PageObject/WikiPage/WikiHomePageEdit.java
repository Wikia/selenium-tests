package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Factory;

import com.google.sitebricks.client.Web;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class WikiHomePageEdit extends BasePageObject{

	@FindBy(css="li.start-a-wiki")
	private WebElement startWikiButton;
	
	@FindBy(css="input#wpSave")
	private WebElement publishButton;
	
	@FindBy(xpath="//li[@class='notice-item' and contains(text(), 'Learn more about editing your main page.')]")
	private WebElement learnMoreNotice;
	
	public WikiHomePageEdit(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void openHomePage(){
		driver.get(Global.DOMAIN);
		waitForElementByElement(startWikiButton);
	}
	
	public void editPage()
	{
		String currentUrl = driver.getCurrentUrl();
		driver.get(currentUrl+"?action=edit");
		waitForElementByElement(publishButton);
	}
	
	public void verifyLearnMoreNotice()
	{
		waitForElementByElement(learnMoreNotice);
	}

}
