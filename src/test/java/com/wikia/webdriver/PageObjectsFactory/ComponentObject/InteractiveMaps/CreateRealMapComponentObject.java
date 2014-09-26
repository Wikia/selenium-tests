package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;

;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 *
 */

public class CreateRealMapComponentObject extends BasePageObject {

	public CreateRealMapComponentObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "input[name=map-title]")
	private WebElement mapTitleField;
	@FindBy(css = "#intMapPreviewImage")
	private WebElement realMapImagePreview;
	@FindBy(css = "#intMapBack")
	private WebElement backButton;
	@FindBy(css = "#intMapNext")
	private WebElement nextButton;
	@FindBy(css = ".close")
	private WebElement closeButton;
	@FindBy(css = "#intMapError")
	private WebElement mapErrorField;
	
	public CreateAMapComponentObject clickBack() {
		waitForElementByElement(backButton);
		backButton.click();
		PageObjectLogging.log("clickCustomMap", "custom map link clicked", true, driver);
		return new CreateAMapComponentObject(driver);
	}

	public CreatePinTypesComponentObject clickNext() {
		waitForElementByElement(nextButton);
		nextButton.click();
		PageObjectLogging.log("clickCustomMap", "custom map link clicked", true, driver);
		return new CreatePinTypesComponentObject(driver);
	}

	public InteractiveMapsPageObject clickClose() {
		waitForElementByElement(closeButton);
		closeButton.click();
		return new InteractiveMapsPageObject(driver);
	}

	public void typeMapName(String mapName) {
		waitForElementByElement(mapTitleField);
		mapTitleField.sendKeys(mapName);
		PageObjectLogging.log("typeMapName", mapName + " title for map is typed in", true);
	}

	public void verifyRealMapPreviewImage() {
		waitForElementByElement(realMapImagePreview);
		PageObjectLogging.log("verifyRealMapPreviewImage", "Real map preview image is visible", true);
	}
	
	public void verifyErrorAppear() {
		waitForElementVisibleByElement(mapErrorField);
		Assertion.assertFalse(mapErrorField.getText().isEmpty());
	}
}
