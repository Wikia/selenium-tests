package com.wikia.webdriver.PageObjects.PageObject.WikiPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjects.PageObject.WikiBasePageObject;

public class Top_10_list_EditMode extends Top_10_list_EditonCore{
	@FindBy(css=".FormButtons input[type='Submit']")
	WebElement savelistButton;

	public Top_10_list_EditMode(WebDriver driver, String Domain) {
		super(driver, Domain);
		// TODO Auto-generated constructor stub
	}

	public Top_10_list clickSaveList() {
		waitForElementByElement(savelistButton);
		clickAndWait(savelistButton);
		PageObjectLogging.log("clickCreateList", "click on Create List button", true, driver);		
		return new Top_10_list(driver, Domain, this.articlename);
	}



}
