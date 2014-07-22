package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps;

import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.AddPinComponentObject;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author lukaszjedrzejczak
 * 
 */
public class InteractiveMapPageObject extends BasePageObject{
	
	public InteractiveMapPageObject(WebDriver driver) {
		super(driver);
	}
	
	//UI Mapping
	@FindBy(css = "#map")
	private WebElement map;
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
	@FindBy(css = "")
	private WebElement tile;
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
		Assert.assertEquals(mapTitle, createdMapTitle.getText());
	}
	
	public void verifyCreatedPins(String pinName) {
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(filterBox);
		Assert.assertEquals(1, createdPinCells.size());
		for(int i = 0; i < createdPinCells.size(); i++) {
			Assert.assertEquals(pinName, createdPinNames.get(i).getText());
		}
		driver.switchTo().defaultContent();
	}
	
	public void verifyCreatedPins(List<String> pinNamesList) {
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(filterBox);
		Assert.assertEquals(pinNamesList.size(), createdPinCells.size());
		for(int i = 0; i < createdPinCells.size(); i++) {
			Assert.assertEquals(pinNamesList.get(i), createdPinNames.get(i).getText());
		}
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
		waitForElementByElement(map);
		map.click();
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
}
