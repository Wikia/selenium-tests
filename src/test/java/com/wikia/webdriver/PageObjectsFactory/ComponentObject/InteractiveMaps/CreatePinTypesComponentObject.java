package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 * @author: Lukasz Jedrzejczak
 *
 */

public class CreatePinTypesComponentObject extends BasePageObject{

	public CreatePinTypesComponentObject(WebDriver driver) {
		super(driver);
	}
	
	//UI Mapping
	@FindBy(css = "input[name='poiCategoryNames[]']")
	private List<WebElement> pinTypeTitleInputs;
	@FindBy(css = "input[name='poiCategoryMarkers[]']")
	private List<WebElement> uploadMarker;
	@FindBy(css = ".button.normal.primary")
	private WebElement saveButton;
	@FindBy(css = ".addPoiCategory.modalEvent")
	private WebElement addMorePinTypesLink;
	@FindBy(css = ".poi-category-dropdown")
	private WebElement mainParentCategorySelector;
	@FindBy(css = ".poi-category-dropdown > option")
	private List<WebElement> parentCatOption;
	@FindBy(css = ".modal.medium.int-map-modal")
	private WebElement creatingPinDialog;
	
	public void typePinTypeTitle(String pinTypeName) {
		WebElement firstPin = pinTypeTitleInputs.get(0);
		waitForElementByElement(firstPin);
		firstPin.sendKeys(pinTypeName);
		PageObjectLogging.log("typePinTypeTitle", pinTypeName + " title for pin type is typed in", true, driver);
	}
	
	public InteractiveMapsPageObject clickSave() {
		waitForElementByElement(saveButton);
		saveButton.click();
		PageObjectLogging.log("clickSave", "clicked save button in create pin types modal", true);
		return new InteractiveMapsPageObject(driver);
	}
	
	public void selectParentCategory(int catValue) {
		waitForElementClickableByElement(mainParentCategorySelector);
		mainParentCategorySelector.click();
		WebElement parentSelected = parentCatOption.get(catValue);
		waitForElementVisibleByElement(parentSelected);
		parentSelected.click();
	}
	
	public void clickAddAnotherPinType() {
		waitForElementByElement(addMorePinTypesLink);
		addMorePinTypesLink.click();
		PageObjectLogging.log("clickAddAnotherPinType", "clicked add more pin types link in create pin types modal", true);
	}
		
}
