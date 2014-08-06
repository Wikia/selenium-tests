package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Iterator;

import junit.framework.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;
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
	@FindBy(css = "input[name='wpUploadFile']")
	private List<WebElement> uploadInputs;
	@FindBy(css = ".button.normal.primary")
	private WebElement saveButton;
	@FindBy(css = ".addPoiCategory.modalEvent")
	private WebElement addMorePinTypesLink;
	@FindBy(css = ".poi-category-dropdown")
	private WebElement mainParentCategorySelector;
	@FindBy(css = ".poi-category-dropdown > option")
	private List<WebElement> parentCatOption;
	@FindBy(css = "select[name='poiCategoryParents[]']")
	private List<WebElement> parentCatElements;
	@FindBy(css = ".modal.medium.int-map-modal")
	private WebElement creatingPinDialog;
	@FindBy(css = "")
	
	private int sizepinTypeTitleInputs,sizeuploadMarker,sizeparentCatElements; 
	
	public void typePinTypeTitle(String pinTypeName, int index) {
		WebElement firstPin = pinTypeTitleInputs.get(index);
		waitForElementByElement(firstPin);
		firstPin.clear();
		firstPin.sendKeys(pinTypeName);
		PageObjectLogging.log("typePinTypeTitle", pinTypeName + " title for pin type is typed in", true, driver);
	}
	
	
	public InteractiveMapPageObject clickSave() {
		waitForElementByElement(saveButton);
		saveButton.click();
		PageObjectLogging.log("clickSave", "clicked save button in create pin types modal", true);
		driver.switchTo().defaultContent();
		return new InteractiveMapPageObject(driver);
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
	
	public void verifyPinTypesDialog(){
		waitForElementByElement(creatingPinDialog);
		PageObjectLogging.log("verifyPinTypesDialog", "Pin types dialog was showed", true);
	}
	
	public void savePinTypesListState(){
		sizepinTypeTitleInputs = pinTypeTitleInputs.size();
		sizeuploadMarker = uploadMarker.size();
		sizeparentCatElements = parentCatElements.size();
		PageObjectLogging.log("savePinTypesListState", "State of pin types list is saved", true);
	}
	
	public void verifyAddAnotherPinType(){
		 Assert.assertEquals(sizepinTypeTitleInputs+1, pinTypeTitleInputs.size());
		 Assert.assertEquals(sizeuploadMarker+1, uploadMarker.size());
		 Assert.assertEquals(sizeparentCatElements+1, parentCatElements.size());
         PageObjectLogging.log("verifyAddAnotherPinType","New line and fields was displayed after clicking Add another pin type", true);
	}
	
	public int checkPinTypeTitleId(String pinTypeName){
		int i=0;
		for(i=0;i<pinTypeTitleInputs.size();i++){
			if(pinTypeName.equals(pinTypeTitleInputs.get(i).getText())){ 	break;	}
		}
		return i-1; 
	}
	
}
