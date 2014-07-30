package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 * @author: Lukasz Jedrzejczak
 *
 */

public class CreatePinTypesComponentObject extends BasePageObject{

	public CreatePinTypesComponentObject(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "input[name='poiCategoryNames[]']")
	private List<WebElement> pinTypeTitleInputs;
	@FindBy(css = ".button.normal.primary")
	private WebElement saveButton;
	
	public void typePinTypeTitle(String pinTypeName) {
		WebElement firstPin = pinTypeTitleInputs.get(0);
		waitForElementByElement(firstPin);
		firstPin.sendKeys(pinTypeName);
		PageObjectLogging.log("typePinTypeTitle", pinTypeName + " title for pin type is typed in", true, driver);
	}
	
	public InteractiveMapPageObject clickSave() {
		waitForElementByElement(saveButton);
		saveButton.click();
		PageObjectLogging.log("clickSave", "clicked save button in create pin types modal", true);
		return new InteractiveMapPageObject(driver);
	}
}
