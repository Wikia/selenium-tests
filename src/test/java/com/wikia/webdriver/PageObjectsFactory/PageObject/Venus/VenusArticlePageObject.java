package com.wikia.webdriver.PageObjectsFactory.PageObject.Venus;

import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VenusArticlePageObject extends WikiBasePageObject {

	@FindBy(css = ".table-scrollable-wrapper")
	private WebElement scrollableTable;

	public VenusArticlePageObject(WebDriver driver) {
		super(driver);
	}

	public Boolean isScrollableTablePresent() {
		return checkIfElementOnPage(scrollableTable);
	}
}
