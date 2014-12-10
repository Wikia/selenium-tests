package com.wikia.webdriver.pageobjectsfactory.pageobject.wikipagemonobook;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BaseMonoBookPageObject;

/**
 * @author lukaszk
 */
public class WikiArticleMonoBookPageObject extends BaseMonoBookPageObject {

	@FindBy(css = "#wpTextbox1")
	private WebElement editionArea;
	@FindBy(css = "#ca-edit a")
	private WebElement editLink;
	@FindBy(css = ".oasis-only-warning")
	private WebElement oasisOnly;

	public WikiArticleMonoBookPageObject(WebDriver driver) {
		super(driver);
	}

	public void clickEdit() {
		scrollAndClick(editLink);
		PageObjectLogging.log("clickEdit", "click on Edit link", true);
	}

	public void verifyEditionArea() {
		waitForElementByElement(editionArea);
		PageObjectLogging.log("verifyEditArea", "verify that edition area is present", true);
	}

	public void verifyOasisOnly() {
		waitForElementByElement(oasisOnly);
		PageObjectLogging.log("verifyOasisOnly", "Oasis only warning is present", true);
	}
}
