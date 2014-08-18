package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps;

import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.AddPinComponentObject;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.ContentPatterns.InteractiveMapsContent;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import java.util.List;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * @author lukaszjedrzejczak
 * @author Łukasz Nowak (Dyktus)
 */
public class InteractiveMapPageObject extends BasePageObject{
	
	public InteractiveMapPageObject(WebDriver driver) {
		super(driver);
	}
	
	//UI Mapping
	@FindBy(css = "#map")
	private WebElement map;
	@FindBy(css = ".enable-edit")
	private WebElement mapBody;
	@FindBy(css = ".WikiaPageHeader>h1")
	private WebElement createdMapTitle;
	@FindBy(css = ".point-type.enabled")
	private List<WebElement> createdPinCells;
	@FindBy(css = ".point-type.enabled > span")
	private List<WebElement> createdPinNames;
	@FindBy(css = ".filter-menu.shown-box")
	private WebElement filterBox;
	@FindBy(css = "iframe[name=wikia-interactive-map]")
	private WebElement mapFrame;
	@FindBy(css = ".error-wrapper")
	private WebElement mapBeingProcessedModal;
	@FindBy(css = "#refresh")
	private WebElement refreshButton;
	@FindBy(css = ".leaflet-draw-draw-marker")
	private WebElement addPin;
	@FindBy(css = ".leaflet-control-embed-map-code-button")
	private WebElement embedMapCodeButton;
	@FindBy(css = ".leaflet-draw-draw-marker")
	private WebElement addPinButton;
	@FindBy(css = "#intMapEmbedMap")
	private WebElement embedMapDialog;
	@FindBy(css = ".code-sample.small")
	private WebElement embedMapCodeSmall;
	@FindBy(css = ".code-sample.medium")
	private WebElement embedMapCodeMedium; 
	@FindBy(css = ".code-sample.large")
	private WebElement embedMapCodeLarge;
	@FindBy(css = "button[data-size='small']")
	private WebElement embedMapCodeSmallButton;
	@FindBy(css = "button[data-size='medium']")
	private WebElement embedMapCodeMediumButton; 
	@FindBy(css = "button[data-size='large']")
	private WebElement embedMapCodeLargeButton;
	@FindBy(css = ".edit-point-types")
	private WebElement editPinTypesButton;
	@FindBy(css = "#intMapPoiCategories")
	private WebElement mapPoiCategoriesDialog;
	@FindBy(css = ".leaflet-marker-icon")
	private List<WebElement> pinCollection;
	@FindBy(css = ".edit-poi-link")
	private WebElement editPoiLinkForActivePin;
	@FindBy(css = ".leaflet-popup-content")
	private WebElement popUpContent;
	@FindBy(css = ".description > h3")
	private WebElement titlePinForActivePin;
	@FindBy(css = "#intMapEditPOI")
	private WebElement intMapEditPOI;
	@FindBy(css = "#allPointTypes")
	private WebElement allPinTypes;
	@FindBy(css = ".point-type.enabled")
	private List<WebElement> enabledPinTypesCollection;
	@FindBy(css = ".point-type")
	private List<WebElement> disabledPinTypesCollection;
	@FindBy(css = ".leaflet-control-zoom-in")
	private WebElement zoomInButton;
	@FindBy(css = ".leaflet-control-zoom-out")
	private WebElement zoomOutButton;
	@FindBy(css = ".leaflet-map-pane.leaflet-zoom-anim")
	private WebElement zoomAnim;
	@FindBy(css = ".leaflet-marker-pane > img")
	private List<WebElement> pinCollections;
	@FindBy(css = ".description>h3")
	private WebElement pinTitle;
	@FindBy(css = ".description>p")
	private WebElement pinDescription;
	@FindBy(css = ".edit-poi-link")
	private WebElement pinEditLink;
	@FindBy(css = ".photo")
	private WebElement pinPopupImage;
	@FindBy(css = ".wikia-interactive-map-wrapper")
	private WebElement mapPane;
	
	public void clickEmbedMapCodeButton() {
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(embedMapCodeButton);
		scrollAndClick(embedMapCodeButton);
		PageObjectLogging.log("clickEmbedMapCodeButton", "Embed map code button clicked", true, driver);
		driver.switchTo().defaultContent();
	}

	public void verifyMapOpened() {
		driver.switchTo().frame(mapFrame);
		closeMapBeingProcessedModalIfVisible();
		waitForElementByElement(map);
		driver.switchTo().defaultContent();
	}
	
	public void verifyCreatedMapTitle(String mapTitle) {
		waitForElementByElement(createdMapTitle);
		Assertion.assertEquals(mapTitle, createdMapTitle.getText());
	}
	
	public void verifyCreatedPinTypesForNewMap() {
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(filterBox);
		Assertion.assertEquals(1, createdPinCells.size());
		driver.switchTo().defaultContent();
	}

	public void verifyMapIsBeingProcessedMessage() {
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(mapBeingProcessedModal);
		driver.switchTo().defaultContent();
	}
	
	public void verifyEmbedMapDialog() {
		waitForElementByElement(embedMapDialog);
		checkIfElementOnPage(embedMapCodeSmall);
	}
	
	public void closeMapBeingProcessedModalIfVisible() {
		while(checkIfElementOnPage(mapBeingProcessedModal)) {
			refreshButton.click();
		}
	}
	
	public AddPinComponentObject placePinInMap() {
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(addPin);
		addPin.click();
		Actions actions = new Actions(driver);
//		actions.moveByOffset(-240, -240).click();
		actions.moveToElement(map).click();
		actions.release().perform();
//		map.click();
		driver.switchTo().defaultContent();
		return new AddPinComponentObject(driver);
	}
	
	public enum embedMapDialogButtons {
		small, medium, large;
	}
	
	public void clickEmbedMapCodeButton(embedMapDialogButtons button) {
		switch (button) {
			case small:
				embedMapCodeSmallButton.click();
				break;
			case medium:
				embedMapCodeMediumButton.click();
				break;
			case large:
				embedMapCodeLargeButton.click();
				break;
		}
	}
	
	public void verifyEmbedMapCode(embedMapDialogButtons button) {
		switch (button) {
			case small:
				waitForElementVisibleByElement(embedMapCodeSmall);
				break;
			case medium:
				waitForElementVisibleByElement(embedMapCodeMedium);
				break;
			case large:
				waitForElementVisibleByElement(embedMapCodeLarge);
				break;
		}
	}
	
	public void clickEditPinTypesButton() {
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(editPinTypesButton);
	    editPinTypesButton.click();
		PageObjectLogging.log("clickEditPinTypesButton", "Edit Pin Types button clicked", true, driver);
		driver.switchTo().defaultContent();
	}
	
	public void clickZoomInButton() {
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(zoomInButton);
		zoomInButton.click();
		waitForElementByElement(zoomAnim);
		PageObjectLogging.log("clickZoomInButton", "Map zoom in was clicked", true, driver);
	}
	
	public void clickZoomOutButton() {
		waitForElementByElement(zoomOutButton);
		zoomOutButton.click();
		waitForElementByElement(zoomAnim);
		PageObjectLogging.log("clickZoomOutButton", "Map zoom out was clicked", true, driver);
	}
	
	public void verifyZoomMap() {
		waitForElementByElement(zoomAnim);
		PageObjectLogging.log("verifyZoomMap", "Map was zoomed", true, driver);
	}
	
	public String getEmbedMapWikiCode() {
		return "<imap map-id='"+mapFrame.getAttribute("data-mapid")+"'/>";
	}
	
	public String getEmbedMapID() {
		return mapFrame.getAttribute("data-mapid");
	}
	
	public void clickOnSingleEnabledCategory() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mapFrame);		
		waitForElementByElement(enabledPinTypesCollection.get(InteractiveMapsContent.pinTypeIndex));
		enabledPinTypesCollection.get(InteractiveMapsContent.pinTypeIndex).click();
		PageObjectLogging.log("clickOnSingleEnabledCategory", "Single enabled category was clicked", true);
		driver.switchTo().activeElement();
	}
	
	public void clickOnSingleDisabledCategory() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mapFrame);		
		waitForElementByElement(disabledPinTypesCollection.get(InteractiveMapsContent.pinTypeIndex));
		disabledPinTypesCollection.get(0).click();
		PageObjectLogging.log("clickOnSingleDisabledCategory", "Single disabled category was clicked", true);
		driver.switchTo().activeElement();
	}
	
	public void clickOnAllPinTypes() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mapFrame);		
		waitForElementByElement(allPinTypes);
		allPinTypes.click();
		PageObjectLogging.log("clickOnAllCategories", "All categories was clicked", true);
		driver.switchTo().activeElement();
	}
	
	public void verifyAllPinTypesIsCheck() {
		waitForElementByElement(allPinTypes);
		waitForElementByElement(enabledPinTypesCollection.get(InteractiveMapsContent.pinTypeIndex));
		if(allPinTypes.getAttribute("class").contains("enabled")) {
			PageObjectLogging.log("verifyAllPointTypesIsCheck", "All pin types was checked", true, driver);
		}else {
			PageObjectLogging.log("verifyAllPointTypesIsCheck", "All pin types was unchecked", false, driver);
		}
	}
	
	public void verifyAllPinTypesIsUncheck() {
		waitForElementByElement(allPinTypes);
		if(allPinTypes.getAttribute("class").contains("enabled")) {
			PageObjectLogging.log("verifyAllPointTypesIsUnCheck", "All pin types was checked", false, driver);
		}else {
			PageObjectLogging.log("verifyAllPointTypesIsUnCheck", "All pin types was unchecked", true, driver);
		}
	}
	
	public void verifyPinTypesAreUncheck() {
		waitForElementByElement(disabledPinTypesCollection.get(InteractiveMapsContent.pinTypeIndex));
		Assertion.assertEquals(0, enabledPinTypesCollection.size());
	}
	
	public void verifyPinTypesAreCheck() {
		waitForElementByElement(enabledPinTypesCollection.get(InteractiveMapsContent.pinTypeIndex));
		Assertion.assertEquals(0, disabledPinTypesCollection.size());		
	}	
	
	public void verifyPinDataWasChanged(String pinName, String pinDesc) {
		waitForElementByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(pinTitle);
		Assertion.assertNotEquals(pinName, pinTitle.getText());
		waitForElementByElement(pinDescription);
		Assertion.assertNotEquals(pinDesc, pinDescription.getText());
	}
	
	public AddPinComponentObject editLastAddedPin() {
		waitForElementByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(pinEditLink);
		pinEditLink.click();
		driver.switchTo().defaultContent();
		return new AddPinComponentObject(driver);
	}
	
	public void verifyControButtonsAreVisible() {
		waitForElementByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(embedMapCodeButton);
		waitForElementByElement(zoomInButton);
		waitForElementByElement(zoomOutButton);
		PageObjectLogging.log("verifyControlButtonsAreVisible", "embedMap, zoom in/out buttons are visible", true);
	}
	
	public void verifyPinPopupImageIsVisible(){
		waitForElementByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		Assertion.assertEquals(checkIfElementOnPage(pinPopupImage), true);
	}
	
	public void verifyPinPopupImageNotExist(){
		waitForElementByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		Assertion.assertEquals(checkIfElementOnPage(pinPopupImage), false);
	}
}
