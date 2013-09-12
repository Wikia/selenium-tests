package com.wikia.webdriver.PageObjectsFactory.PageObject.Actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class DeletePageObject extends WikiBasePageObject {

	@FindBy(css=".mw-submit input")
	private WebElement submitButton;

	public DeletePageObject(WebDriver driver) {
		super(driver);
	}

	public WikiBasePageObject submitDeletion() {
		submitButton.click();
		PageObjectLogging.log("submitDeletion", "page deleted", true);
		return new WikiBasePageObject(driver);
	}
}
