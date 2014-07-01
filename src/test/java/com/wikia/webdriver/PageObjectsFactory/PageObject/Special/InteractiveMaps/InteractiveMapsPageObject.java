package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateAMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

/**
* @author Rodrigo 'RodriGomez' Molinero
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
	
	
}




