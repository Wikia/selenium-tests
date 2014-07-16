package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateAMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import java.util.List;
import org.junit.Assert;

/**
* @author Rodrigo 'RodriGomez' Molinero
* @author: Lukasz Jedrzejczak
*
*/

public class InteractiveMapsPageObject extends BasePageObject{

	public InteractiveMapsPageObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//UI Mapping
	@FindBy(css = "#createMap")
	private WebElement createAMapButton;
	@FindBy(css = "iframe[name=wikia-interactive-map]")
	private WebElement mapFrame;
	@FindBy(css = ".error-wrapper")
	private WebElement mapBeingProcessedModal;
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
	
	
	public CreateAMapComponentObject clickCreateAMap() {
		waitForElementByElement(createAMapButton);
		scrollAndClick(createAMapButton);
		PageObjectLogging.log("clickCreateAMap", "create a map button clicked",  true, driver);
		return new CreateAMapComponentObject(driver);
	}
	
	public void verifyMapIsBeingProcessedMessage() {
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(mapBeingProcessedModal);
		driver.switchTo().defaultContent();
	}
	
	public void verifyMapAdded() {
		driver.switchTo().frame(mapFrame);
		checkIfElementOnPage(map);
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
	
}
