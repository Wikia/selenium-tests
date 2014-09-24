package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;

/**
 * @author Robert 'Rochan' Chan
 *
 */

public class DeleteAMapComponentObject extends BasePageObject {

	@FindBy(css = "#intMapsDeleteMapModal .button.primary")
	private WebElement deleteMapButton;

	public DeleteAMapComponentObject(WebDriver driver) {
		super(driver);
	}

	public InteractiveMapsPageObject deleteMap() {
		waitForElementClickableByElement(deleteMapButton);
		deleteMapButton.click();
		return new InteractiveMapsPageObject(driver);
	}
}
