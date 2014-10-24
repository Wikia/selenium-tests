package com.wikia.webdriver.PageObjectsFactory.PageObject.GlobalNavigation;

import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.NoSuchElementException;

public class GlobalNavigationPageObject extends WikiBasePageObject {

	@FindBy(css = ".gamestar-logo")
	private WebElement gameStarLink;

	public GlobalNavigationPageObject(WebDriver driver) {
		super(driver);
	}

	public Boolean GameStarLinkDisplay() {
		if (checkIfElementOnPage(gameStarLink)) {
			return gameStarLink.isDisplayed();
		} else {
			throw new NoSuchElementException("Game Star logo not present.");
		}
	}
}
