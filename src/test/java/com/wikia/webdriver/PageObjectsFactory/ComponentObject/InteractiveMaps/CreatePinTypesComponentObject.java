package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;
import java.util.List;
import com.wikia.webdriver.Common.Core.Assertion;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;


/**
 * @author Rodrigo 'RodriGomez' Molinero
 * @author: Lukasz Jedrzejczak
 * @author Łukasz Nowak (Dyktus)
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
	private List<WebElement> uploadInputsCollection;
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
	@FindBy(css = "#intMapError")
	private WebElement pinTypesError;
	
	private int amountPinTypeTitleInputs,amountUploadMarker,amountParentCatElements; 
	
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
	
	public void verifyPinTypesDialog() {
		waitForElementByElement(creatingPinDialog);
		PageObjectLogging.log("verifyPinTypesDialog", "Pin types dialog was showed", true);
	}
	
	public void savePinTypesListState() {
		amountPinTypeTitleInputs = pinTypeTitleInputs.size();
		amountUploadMarker = uploadMarker.size();
		amountParentCatElements = parentCatElements.size();
		PageObjectLogging.log("savePinTypesListState", "State of pin types list is saved", true);
	}
	

	public void verifyAddAnotherPinType() {
		 Assertion.assertEquals(amountPinTypeTitleInputs+1, pinTypeTitleInputs.size());
		 Assertion.assertEquals(amountUploadMarker+1, uploadMarker.size());
		 Assertion.assertEquals(amountParentCatElements+1, parentCatElements.size());
	}

	
	public int checkPinTypeTitleId(String pinTypeName){
		int i=0;
		for(i=0;i<pinTypeTitleInputs.size();i++){
			if(pinTypeName.equals(pinTypeTitleInputs.get(i).getText())){ 	break;	}
		}
		return i-1; 
	}
	
	public void selectFileToUpload(String file,String typeOfFile){
		uploadInputsCollection.get(0).sendKeys(getAbsolutePathForFile(PageContent.resourcesPath+file));
		PageObjectLogging.log("selectFileToUpload","Tried to upload "+typeOfFile, true);
	}
	
	
	
	public void verifyErrorsExist(){
		waitForElementByElement(pinTypesError);
		Assertion.assertEquals(false,pinTypesError.getText().isEmpty());
	}
	
	public void verifyErrorsNotExist(){
		Assertion.assertEquals(true,pinTypesError.getText().isEmpty());
	}
}
	