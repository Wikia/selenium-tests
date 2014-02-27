package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Top10;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class Top_10_list_EditMode extends Top10listEditonCore{
	@FindBy(css=".FormButtons input[type='Submit']")
	WebElement savelistButton;

	public Top_10_list_EditMode(WebDriver driver) {
		super(driver);
	}

	public Top_10_list clickSaveList() {
		waitForElementByElement(savelistButton);
		scrollAndClick(savelistButton);
		PageObjectLogging.log("clickCreateList", "click on Create List button", true, driver);		
		return new Top_10_list(driver);
	}
}
