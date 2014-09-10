package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.AddPinComponentObject;
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
	@FindBy(css = "#filterMenu")
	private WebElement filterBox;
	@FindBy(css = "iframe[name=wikia-interactive-map]")
	private WebElement mapFrame;
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
	@FindBy(css = ".leaflet-marker-icon")
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
	@FindBy(css = "li[class=point-type]")
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
	@FindBy(css = ".filter-menu-header > span")
	private WebElement filterBoxTitle;

	public enum embedMapDialogButtons {
		small, medium, large;
	}

	public void clickEmbedMapCodeButton() {
		waitForElementVisibleByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementVisibleByElement(embedMapCodeButton);
		embedMapCodeButton.click();
		PageObjectLogging.log("clickEmbedMapCodeButton", "Embed map code button clicked", true, driver);
		driver.switchTo().defaultContent();
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
		waitForElementVisibleByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementVisibleByElement(editPinTypesButton);
		editPinTypesButton.click();
		PageObjectLogging.log("clickEditPinTypesButton", "Edit Pin Types button were clicked", true, driver);
		driver.switchTo().defaultContent();
	}

	public void clickZoomInButton() {
		waitForElementByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementClickableByElement(zoomInButton);
		zoomInButton.click();
		waitForElementByElement(zoomAnim);
		driver.switchTo().defaultContent();
		PageObjectLogging.log("clickZoomInButton", "Map zoom in was clicked", true, driver);
	}

	public void clickZoomOutButton() {
		waitForElementVisibleByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementClickableByElement(zoomOutButton);
		zoomOutButton.click();
		waitForElementByElement(zoomAnim);
		driver.switchTo().defaultContent();
		PageObjectLogging.log("clickZoomOutButton", "Map zoom out was clicked", true, driver);
	}

	public void clickOnPin(Integer pinListPosition) {
		waitForElementVisibleByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementVisibleByElement(pinCollection.get(pinListPosition));
		scrollToElement(pinCollection.get(pinListPosition));
		Actions actions = new Actions(driver);
		actions.moveToElement(pinCollection.get(pinListPosition));
		actions.click().perform();
		PageObjectLogging.log("clickOnPin", "Pin was clicked", true, driver);
	}

	public void clickOnSingleEnabledCategory() {
		waitForElementVisibleByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementVisibleByElement(enabledPinTypesCollection.get(InteractiveMapsContent.pinTypeIndex));
		enabledPinTypesCollection.get(InteractiveMapsContent.pinTypeIndex).click();
		PageObjectLogging.log("clickOnSingleEnabledCategory", "Single enabled category was clicked", true);
		driver.switchTo().defaultContent();
	}

	public void clickOnSingleDisabledCategory() {
		driver.switchTo().defaultContent();
		waitForElementByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(disabledPinTypesCollection.get(InteractiveMapsContent.pinTypeIndex));
		disabledPinTypesCollection.get(0).click();
		PageObjectLogging.log("clickOnSingleDisabledCategory", "Single disabled category was clicked", true);
		driver.switchTo().defaultContent();
	}

	public void clickOnAllPinTypes() {
		driver.switchTo().defaultContent();
		waitForElementVisibleByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementVisibleByElement(allPinTypes);
		allPinTypes.click();
		PageObjectLogging.log("clickOnAllCategories", "All categories were clicked", true);
		driver.switchTo().defaultContent();
	}

	public AddPinComponentObject clickOnEditPin() {
		waitForElementByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(pinEditLink);
		pinEditLink.click();
		driver.switchTo().defaultContent();
		PageObjectLogging.log("clickOnEditPin", "Pin edit link was clicked", true);
		return new AddPinComponentObject(driver);
	}

	public void clickOnFilterBoxTitle() {
		waitForElementVisibleByElement(mapFrame);
		scrollToElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(filterBoxTitle);
		Actions actions = new Actions(driver);
		actions.moveToElement(filterBoxTitle).click().perform();
		driver.switchTo().defaultContent();
	}

	public String getEmbedMapWikiCode() {
		return "<imap map-id='" + mapFrame.getAttribute("data-mapid") + "'/>";
	}

	public String getEmbedMapID() {
		return mapFrame.getAttribute("data-mapid");
	}

	public String getOpenPinName() {
		waitForElementByElement(pinTitle);
		String pinName = pinTitle.getText();
		return pinName;
	}

	public String getEmbedMapCode() {
		waitForElementByElement(embedCode);
		return embedCode.getText();
	}

	public AddPinComponentObject placePinInMap() {
		waitForElementByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementVisibleByElement(addPinButton);
		addPinButton.click();
		Actions actions = new Actions(driver);
		actions.moveToElement(mapImagesCollection.get(0));
		actions.click().perform();
		driver.switchTo().defaultContent();
		return new AddPinComponentObject(driver);
	}

	public void verifyMapOpened() {
		waitForElementVisibleByElement(mapFrame);
		scrollToElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		driver.switchTo().defaultContent();
	}

	public void verifyCreatedMapTitle(String mapTitle) {
		waitForElementByElement(createdMapTitle);
		Assertion.assertEquals(mapTitle, createdMapTitle.getText());
	}

	public void verifyCreatedPinTypesForNewMap() {
		waitForElementVisibleByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementVisibleByElement(filterBox);
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
		waitForElementVisibleByElement(pinDescription);
		Assertion.assertEquals(checkIfElementOnPage(pinDescription), true);
		driver.switchTo().defaultContent();
	}

	public void verifyZoomMap() {
		waitForElementByElement(zoomAnim);
		PageObjectLogging.log("verifyZoomMap", "Map was zoomed", true, driver);
	}

	public void verifyAllPinTypesIsCheck() {
		try {
			waitForElementVisibleByElement(mapFrame);
			driver.switchTo().frame(mapFrame);
			waitForElementByElement(allPinTypes);
			waitForElementByElement(enabledPinTypesCollection.get(InteractiveMapsContent.pinTypeIndex));
			if (allPinTypes.getAttribute("class").contains("enabled")) {
				PageObjectLogging.log("verifyAllPointTypesIsCheck", "All pin types were checked", true);
			}
			driver.switchTo().defaultContent();
		}catch (Exception e) {
			PageObjectLogging.log("verifyAllPointTypesIsCheck", e.toString(), false);
		}
	}

	public void verifyAllPinTypesIsUncheck() {
		try {
			waitForElementVisibleByElement(mapFrame);
			driver.switchTo().frame(mapFrame);
			waitForElementVisibleByElement(allPinTypes);
			if (!allPinTypes.getAttribute("class").contains("enabled")) {
				PageObjectLogging.log("verifyAllPointTypesIsUnCheck", "All pin types were unchecked", true);
			}
			driver.switchTo().defaultContent();
		}catch (Exception e) {
			PageObjectLogging.log("verifyAllPointTypesIsUnCheck", "All pin types were checked", false);
		}
	}

	public void verifyPinTypesAreUncheck() {
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
		waitForElementVisibleByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementVisibleByElement(enabledPinTypesCollection.get(InteractiveMapsContent.pinTypeIndex));
		Assertion.assertEquals(disabledPinTypesCollection.size(), 0);
		driver.switchTo().defaultContent();
	}

	public void verifyPinDataWasChanged(String pinName, String pinDesc) {
		waitForElementVisibleByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementVisibleByElement(popUpContent);
		scrollToElement(popUpContent);
		waitForElementVisibleByElement(pinTitle);
		waitForElementVisibleByElement(pinDescription);
		String actualTitle = pinTitle.getText();
		String actualDesc = pinDescription.getText();
		Assertion.assertNotEquals(pinName, actualTitle);
		Assertion.assertNotEquals(pinDesc, actualDesc);
	}

	public void verifyControButtonsAreVisible() {
		waitForElementByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementVisibleByElement(embedMapCodeButton);
		Assertion.assertEquals(checkIfElementOnPage(embedMapCodeButton), true);
		Assertion.assertEquals(checkIfElementOnPage(zoomInButton), true);
		Assertion.assertEquals(checkIfElementOnPage(zoomOutButton), true);
		PageObjectLogging.log("verifyControlButtonsAreVisible", "embedMap, zoom in/out buttons were visible", true);
		driver.switchTo().defaultContent();
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
