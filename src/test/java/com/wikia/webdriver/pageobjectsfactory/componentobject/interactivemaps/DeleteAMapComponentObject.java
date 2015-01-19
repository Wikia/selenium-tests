package com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Robert 'Rochan' Chan
 */

public class DeleteAMapComponentObject extends BasePageObject {

	@FindBy(css = "#intMapsDeleteMapModal .button.primary")
	private WebElement deleteMapButton;
	@FindBy(css = "#intMapsDeleteMapModal")
	private WebElement deleteMapModal;
	@FindBy(css = "#intMapError")
	private WebElement deleteMapError;

	public DeleteAMapComponentObject(WebDriver driver) {
		super(driver);
	}

	public InteractiveMapsPageObject deleteMap() {
		clickDeleteMap();
		waitForElementNotVisibleByElement(deleteMapModal);
		return new InteractiveMapsPageObject(driver);
	}

	public void clickDeleteMap() {
		waitForElementVisibleByElement(deleteMapModal);
		waitForElementClickableByElement(deleteMapButton);
		deleteMapButton.click();
	}

	public String getDeleteMapError() {
		waitForElementVisibleByElement(deleteMapError);
		return deleteMapError.getText();
	}
}
