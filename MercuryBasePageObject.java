package com.wikia.webdriver.PageObjectsFactory.PageObject.Mercury;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

public class MercuryBasePageObject extends WikiBasePageObject{

	public MercuryBasePageObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//UI Mapping
	
		@FindBy(css=".wiki-title")
		private WebElement wikiTitleRedirect;
		@FindBy(css=".nav")
		private WebElement sideMenuNav;
		@FindBy(css=".site-logo")
		private WebElement topBar;
		@FindBy(css=".contributors")
		private WebElement topContributorsModule;
		@FindBy (css=".wikia-footer")
		private WebElement footer;
		
	
}
