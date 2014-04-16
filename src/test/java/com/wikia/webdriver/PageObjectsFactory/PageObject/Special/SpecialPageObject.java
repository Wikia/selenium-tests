package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
* @author Bogna 'bognix' Knychala
*/
public class SpecialPageObject extends WikiBasePageObject {

	@FindBy(css = "#WikiaPageHeader > h2")
	protected WebElement specialPageHeader;

	private final String headerTextSelector = "//h1[contains(text(), '%s')]";
	public SpecialPageObject(WebDriver driver) {
		super(driver);
	}

	public void verifySpecialPage() {
		waitForTextToBePresentInElementByElement(
			specialPageHeader, "Special page"
		);
		PageObjectLogging.log(
			"SpecialPageLoaded",
			"Special Page is loaded",
			true, driver
		);
	}

	public void verifyPageHeader(String expectedHeader) {
		waitForElementByXPath(String.format(headerTextSelector, expectedHeader));
	}
}
