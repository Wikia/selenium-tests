package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 * @author: Lukasz Jedrzejczak
 *
 */

public class CreateAMapComponentObject extends BasePageObject{

	public CreateAMapComponentObject(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = ".int-map-icon-geo-tile-set-blue")
	private WebElement realMapLink;
	@FindBy(css = ".int-map-icon-custom-tile-set-blue")
	private WebElement customMapLink;
	@FindBy(css = "#userForceLoginModal")
	private WebElement loginModal;
	
	public CreateACustomMapComponentObject clickCustomMap() {
		waitForElementByElement(customMapLink);
		customMapLink.click();
		PageObjectLogging.log("clickCustomMap", "custom map link clicked",  true, driver);
		return new CreateACustomMapComponentObject(driver);
	}

	public CreateRealMapComponentObject clickRealMap() {
		waitForElementByElement(realMapLink);
		realMapLink.click();
		PageObjectLogging.log("clickRealMap", "Real Map link clicked",  true, driver);
		return new CreateRealMapComponentObject(driver);
	}
	
	public void verifyLoginModal() {
		waitForElementByElement(loginModal);
		PageObjectLogging.log("verifyLoginModal", "Login modal is displayed", true);
	}

	public void verifyRealMapAndCustomMapButtons() {
		waitForElementByElement(realMapLink);
		waitForElementByElement(customMapLink);
		PageObjectLogging.log(
			"verifyRealMapAndCustomMapButtons",
			"Real Map and Custom Map links are visible",
			true
		);
	}
}
