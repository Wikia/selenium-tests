package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps;

import com.wikia.webdriver.Common.Core.Assertion;
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
public class InteractiveMapPageObject extends BasePageObject {

	public InteractiveMapPageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "#map")
	private WebElement map;
	@FindBy(css = ".enable-edit")
	private WebElement mapBody;
	@FindBy(css = ".WikiaPageHeader>h1")
	private WebElement createdMapTitle;
	@FindBy(css = ".point-type.enabled > span")
	private List<WebElement> createdPinNames;
	@FindBy(css = ".filter-menu.shown-box")
	private WebElement filterBox;
	@FindBy(css = "iframe[name=wikia-interactive-map]")
	private static WebElement mapFrame;
	@FindBy(css = ".error-wrapper")
	private WebElement mapBeingProcessedModal;
	@FindBy(css = "#refresh")
	private WebElement refreshButton;
	@FindBy(css = ".leaflet-draw-draw-marker")
	private WebElement addPinButton;
	@FindBy(css = ".leaflet-control-embed-map-code-button")
	private WebElement embedMapCodeButton;
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
	@FindBy(css = ".code-sample")
	private WebElement embedCode;
	@FindBy(css = ".edit-point-types")
	private WebElement editPinTypesButton;
	@FindBy(css = "#intMapPoiCategories")
	private WebElement mapPoiCategoriesDialog;
	@FindBy(css = ".leaflet-marker-pane > img")
	private List<WebElement> pinCollection;
	@FindBy(css = ".leaflet-popup-content")
	private WebElement popUpContent;
	@FindBy(css = ".description > h3")
	private WebElement pinTitle;
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
	@FindBy(css = ".description")
	private WebElement pinDescription;
	@FindBy(css = ".edit-poi-link")
	private WebElement pinEditLink;
	@FindBy(css = ".photo")
	private WebElement pinPopupImage;
	@FindBy(css = ".wikia-interactive-map-wrapper")
	private WebElement mapPane;
	@FindBy(css = ".leaflet-tile-loaded")
	private List<WebElement> mapImagesCollection;

	public enum embedMapDialogButtons {
		small, medium, large;
	}

	public void clickEmbedMapCodeButton() {
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(embedMapCodeButton);
		scrollAndClick(embedMapCodeButton);
		PageObjectLogging.log("clickEmbedMapCodeButton", "Embed map code button clicked", true, driver);
		driver.switchTo().defaultContent();
	}

	public void closeMapBeingProcessedModalIfVisible() {
		int i = 0;
		while (checkIfElementOnPage(mapBeingProcessedModal)) {
			refreshButton.click();
			i += 1;
			if (i > 10) {
				PageObjectLogging.log("closeMapBeingProcessedModalIfVisible", "Close map being processed dialog cannot be closed", false);
				return;
			}
		}
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

	public void clickEditPinTypesButton() {
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(editPinTypesButton);
		editPinTypesButton.click();
		PageObjectLogging.log("clickEditPinTypesButton", "Edit Pin Types button were clicked", true, driver);
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

	public void clickOnPin(Integer pinListPosition) {
		waitForElementByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(pinCollection.get(pinListPosition));
		pinCollection.get(pinListPosition).click();
		PageObjectLogging.log("clickOnPin", "Pin was clicked", true, driver);
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
		PageObjectLogging.log("clickOnAllCategories", "All categories were clicked", true);
		driver.switchTo().activeElement();
	}

	public AddPinComponentObject clickOnEditPin() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(pinEditLink);
		pinEditLink.click();
		driver.switchTo().defaultContent();
		PageObjectLogging.log("clickOnEditPin", "Pin edit link was clicked", true);
		return new AddPinComponentObject(driver);
	}

	public String getEmbedMapWikiCode() {
		return "<imap map-id='" + mapFrame.getAttribute("data-mapid") + "'/>";
	}

	public String getEmbedMapID() {
		return mapFrame.getAttribute("data-mapid");
	}

	public String getOpenPinName() {
		return pinTitle.getText();
	}

	public String getEmbedMapCode() {
		waitForElementByElement(embedCode);
		return embedCode.getText();
	}

	public AddPinComponentObject placePinInMap() {
		waitForElementByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(addPinButton);
		addPinButton.click();
		waitForElementByElement(mapImagesCollection.get(0));
		mapImagesCollection.get(0).click();
		driver.switchTo().defaultContent();
		return new AddPinComponentObject(driver);
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
		Assertion.assertEquals(1, enabledPinTypesCollection.size());
		driver.switchTo().defaultContent();
	}

	public void verifyEmbedMapDialog() {
		waitForElementByElement(embedMapDialog);
		checkIfElementOnPage(embedMapCodeSmall);
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

	public void verifyPopUpVisible() {
		waitForElementByElement(pinDescription);
		Assertion.assertEquals(checkIfElementOnPage(pinDescription), true);
	}

	public void verifyZoomMap() {
		waitForElementByElement(zoomAnim);
		PageObjectLogging.log("verifyZoomMap", "Map was zoomed", true, driver);
	}

	public void verifyAllPinTypesIsCheck() {
		try {
			waitForElementByElement(allPinTypes);
			waitForElementByElement(enabledPinTypesCollection.get(InteractiveMapsContent.pinTypeIndex));
			if (allPinTypes.getAttribute("class").contains("enabled")) {
				PageObjectLogging.log("verifyAllPointTypesIsCheck", "All pin types were checked", true);
			}
		}catch (Exception e) {
			PageObjectLogging.log("verifyAllPointTypesIsCheck", e.toString(), false);
		}
	}

	public void verifyAllPinTypesIsUncheck() {
		try {
			waitForElementByElement(allPinTypes);
			if (!allPinTypes.getAttribute("class").contains("enabled")) {
				PageObjectLogging.log("verifyAllPointTypesIsUnCheck", "All pin types were unchecked", true);
			}
		}catch (Exception e) {
			PageObjectLogging.log("verifyAllPointTypesIsUnCheck", "All pin types were checked", false);
		}
	}

	public void verifyPinTypesAreUncheck() {
		waitForElementByElement(disabledPinTypesCollection.get(InteractiveMapsContent.pinTypeIndex));
		Assertion.assertEquals(0, enabledPinTypesCollection.size(), "Pin types were unchecked");
	}

	public void verifyPinNotExist(String pinTitle) {
		Integer pinSize = pinCollection.size() - 1;
		while (pinSize >= 0) {
			if (pinCollection.get(pinSize).getText().contains(pinTitle)) {
				break;
			}
			pinSize--;
		}
		Assertion.assertEquals(pinSize.intValue(), -1, "Pin was deleted correctly");
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

	public void verifyControButtonsAreVisible() {
		waitForElementByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(embedMapCodeButton);
		waitForElementByElement(zoomInButton);
		waitForElementByElement(zoomOutButton);
		PageObjectLogging.log("verifyControlButtonsAreVisible", "embedMap, zoom in/out buttons were visible", true);
	}

	public void verifyPinPopupImageIsVisible() {
		waitForElementByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		Assertion.assertEquals(checkIfElementOnPage(pinPopupImage), true);
	}

	public void verifyPinPopupImageNotExist() {
		waitForElementByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		Assertion.assertEquals(checkIfElementOnPage(pinPopupImage), false);
	}

	public void verifyPinPopUp() {
		Assertion.assertEquals(checkIfElementOnPage(pinDescription), true);
		Assertion.assertEquals(checkIfElementOnPage(pinEditLink), true);
	}

	public void verifyMapWasNotLoaded() {
		Assertion.assertEquals(checkIfElementOnPage(map), false);
	}

}
