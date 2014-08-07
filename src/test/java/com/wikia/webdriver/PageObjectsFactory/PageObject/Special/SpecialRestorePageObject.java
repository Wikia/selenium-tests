package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class SpecialRestorePageObject extends WikiBasePageObject {

	@FindBy(css=".mw-undelete-pagetitle")
	private WebElement articleToRestore;
	@FindBy(css="#wpComment")
	private WebElement reasonInput;
	@FindBy(css="#mw-undelete-submit")
	private WebElement submitRestore;

	public SpecialRestorePageObject(WebDriver driver) {
		super(driver);
	}

	public void verifyArticleName(String articleName) {
		waitForElementVisibleByElement(articleToRestore);
		Assertion.assertStringContains(articleName, articleToRestore.getText());
	}

	public void giveReason(String reason) {
		reasonInput.sendKeys(reason);
	}

	public void restorePage() {
		scrollAndClick(submitRestore);
		PageObjectLogging.log("ArticleRestored", "Article restored", true);
	}
}
