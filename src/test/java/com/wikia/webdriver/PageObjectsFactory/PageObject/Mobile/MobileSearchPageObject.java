package com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class MobileSearchPageObject extends MobileBasePageObject{

	public MobileSearchPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="ul#wkResultUl li.result p a")
	WebElement searchResultList;
	
	public void verifySearchResultsList(){
		waitForElementByElement(searchResultList);
		PageObjectLogging.log("verifySearchResultsList", "search results list verified", true, driver);
	}

	
}
