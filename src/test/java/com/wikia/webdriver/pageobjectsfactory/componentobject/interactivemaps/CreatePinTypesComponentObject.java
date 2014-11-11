package com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 * @author Lukasz Jedrzejczak
 * @author Łukasz Nowak (Dyktus)
 *
 */

public class CreatePinTypesComponentObject extends BasePageObject {

	public CreatePinTypesComponentObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "input[name='poiCategories[][name]']")
	private List<WebElement> pinTypeTitleInputs;
	@FindBy(css = "input[name='poiCategories[][marker]']")
	private List<WebElement> uploadMarker;
	@FindBy(css = "input[name='wpUploadFile']")
	private List<WebElement> uploadInputsCollection;
	@FindBy(css = ".button.normal.primary")
	private WebElement saveButton;
	@FindBy(css = ".addPoiCategory")
	private WebElement addMorePinTypesLink;
	@FindBy(css = ".poi-category-dropdown")
	private WebElement mainParentCategorySelector;
	@FindBy(css = ".poi-category-dropdown > option")
	private List<WebElement> parentCatOption;
	@FindBy(css = "select[name='poiCategories[][parent_poi_category_id]']")
	private List<WebElement> parentCatElements;
	@FindBy(css = ".modal.medium.int-map-modal")
	private WebElement creatingPinDialog;
	@FindBy(css = "#intMapError")
	private WebElement pinTypesError;
	@FindBy(css = ".delete-icon")
	private WebElement deletePinTypeButton;

	private int amountPinTypeTitleInputs, amountUploadMarker, amountParentCatElements;

	public InteractiveMapPageObject clickSave() {
		waitForElementByElement(saveButton);
		saveButton.click();
		PageObjectLogging.log("clickSave", "clicked save button in create pin types modal", true);
		driver.switchTo().defaultContent();
		return new InteractiveMapPageObject(driver);
	}

	public void clickAddAnotherPinType() {
		waitForElementByElement(addMorePinTypesLink);
		addMorePinTypesLink.click();
		PageObjectLogging.log("clickAddAnotherPinType", "clicked add more pin types link in create pin types modal", true);
	}

	public void savePinTypesListState() {
		amountPinTypeTitleInputs = pinTypeTitleInputs.size();
		amountUploadMarker = uploadMarker.size();
		amountParentCatElements = parentCatElements.size();
		PageObjectLogging.log("savePinTypesListState", "State of pin types list is saved", true);
	}

	public void selectParentCategory(int catValue) {
		waitForElementClickableByElement(mainParentCategorySelector);
		mainParentCategorySelector.click();
		WebElement parentSelected = parentCatOption.get(catValue);
		waitForElementVisibleByElement(parentSelected);
		parentSelected.click();
		PageObjectLogging.log("selectParentCategory", "Parent category selected", true);
	}

	public void selectFileToUpload(String file, String typeOfFile) {
		unhideElementByClassChange("wpUploadFile", "poi-category-marker-image-upload");
		uploadInputsCollection.get(0).sendKeys(getAbsolutePathForFile(PageContent.resourcesPath + file));
		PageObjectLogging.log("selectFileToUpload", "Tried to upload " + typeOfFile, true, driver);

	}

	public void typePinTypeTitle(String pinTypeName, int index) {
		WebElement firstPin = pinTypeTitleInputs.get(index);
		waitForElementByElement(firstPin);
		firstPin.clear();
		firstPin.sendKeys(pinTypeName);
		PageObjectLogging.log("typePinTypeTitle", pinTypeName + " title for pin type was typed in", true, driver);
	}

	public void typeManyPinTypeTitle(String pinTypeName, int amountFields) {
		for (Integer $i = 0; $i < amountFields; $i++) {
			clickAddAnotherPinType();
			waitForElementByElement(pinTypeTitleInputs.get(pinTypeTitleInputs.size() - 1));
			pinTypeTitleInputs.get(pinTypeTitleInputs.size() - 1).sendKeys(pinTypeName);
		}
		PageObjectLogging.log("typeManyPinTypeTitle", "Added " + amountFields + " pin types", true, driver);
	}

	public void verifyPinTypesDialog() {
		driver.switchTo().activeElement();
		waitForElementByElement(creatingPinDialog);
		PageObjectLogging.log("verifyPinTypesDialog", "Pin types dialog was showed", true);
	}

	public void verifyAddAnotherPinType() {
		Assertion.assertEquals(amountPinTypeTitleInputs + 1, pinTypeTitleInputs.size());
		Assertion.assertEquals(amountUploadMarker + 1, uploadMarker.size());
		Assertion.assertEquals(amountParentCatElements + 1, parentCatElements.size());
		driver.switchTo().defaultContent();
	}

	public void verifyErrorExists() {
		waitForElementVisibleByElement(pinTypesError);
		scrollToElement(pinTypesError);
		Assertion.assertEquals(pinTypesError.getText().isEmpty(), false);
	}

	public void deletePinTypes() {
		waitForElementByElement(deletePinTypeButton);
		while (pinTypeTitleInputs.size() >1) {
			deletePinTypeButton.click();
		}
		PageObjectLogging.log("deletePinTypes", "Only one pin type is displayed", true);
	}
}
