package com.wikia.webdriver.PageObjectsFactory.PageObject.Venus;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VenusArticlePageObject extends WikiBasePageObject {

	@FindBy(css = ".table-scrollable-wrapper")
	private WebElement scrollableTable;

	public VenusArticlePageObject(WebDriver driver) {
		super(driver);
	}

	public void verifyScrollableTablePresent() {
		if (checkIfElementOnPage(scrollableTable)) {
			PageObjectLogging.log("ScrollableTablePresent", "Scrollable table is present on page", true);
		} else {
			throw new NoSuchElementException("Scrollable table not found on page");
		}
	}

	public void verifyScrollableTableNotPresent() {
		if (!checkIfElementOnPage(scrollableTable)) {
			PageObjectLogging.log("ScrollableTableNotPresent", "Scrollable table isn't present on page", true);
		} else {
			throw new WebDriverException("Scrollable table found on page");
		}
	}
}
