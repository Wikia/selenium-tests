package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps;

import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.AddPinComponentObject;
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

}
