package com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;

public class MobileCategoryPageObject extends MobileBasePageObject {

	public MobileCategoryPageObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// UI Mapping

	@FindBy(css = "")
	private WebElement showHideAllButton;
	@FindBy(css = "h2.collSec")
	private List<WebElement> chevronList;



	public void clickShowHideAllButton(){
		waitForElementByElement(showHideAllButton);
		showHideAllButton.click();
	}

	public void verifyChevronOpened(){
		for(WebElement elem:chevronList){
			Assertion.assertEquals("collSec open", elem.getAttribute("class"));
		}
	}

	public void verifyChevronClosed(){
		for(WebElement elem:chevronList){
			Assertion.assertNotEquals("collSec open", elem.getAttribute("class"));
		}
	}
}
