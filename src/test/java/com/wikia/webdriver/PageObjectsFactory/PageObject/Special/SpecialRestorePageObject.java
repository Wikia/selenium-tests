package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class SpecialRestorePageObject extends WikiBasePageObject {

	@FindBy(css=".mw-undelete-pagetitle .new")
	private WebElement articleToRestore;
	@FindBy(css="#wpComment")
	private WebElement reasonInput;
	@FindBy(css="#mw-undelete-submit")
	private WebElement submitRestore;

	public SpecialRestorePageObject(WebDriver driver) {
		super(driver);
	}

	public void verifyArticleName(String articleName) {
		waitForTextToBePresentInElementByElement(articleToRestore, articleName);
		Assertion.assertStringContains(articleToRestore.getText(), articleName);
	}

	public void giveReason(String reason) {
		reasonInput.sendKeys(reason);
	}

	public void restorePage() {
		submitRestore.click();
	}
}
