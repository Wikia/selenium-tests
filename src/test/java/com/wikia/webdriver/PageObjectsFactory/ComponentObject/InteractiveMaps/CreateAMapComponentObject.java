package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 * @author: Lukasz Jedrzejczak
 *
 */

public class CreateAMapComponentObject extends BasePageObject {

	public CreateAMapComponentObject(WebDriver driver) {
		super(driver);
	}

	// UI Mapping
	@FindBy(css = ".int-map-icon-geo-tile-set-blue")
	private WebElement realMapLink;
	@FindBy(css = ".int-map-icon-custom-tile-set-blue")
	private WebElement customMapLink;
	@FindBy(css = "#userForceLoginModal")
	private WebElement loginModal;
	@FindBy(css = ".tip > a")
	private WebElement learnMoreLink;
	@FindBy(css = ".close")
	private WebElement closeButton;
	@FindBy(css = "#intMapCreateMapModal")
	private WebElement mapModal;

	public CreateACustomMapComponentObject clickCustomMap() {
		waitForElementByElement(customMapLink);
		customMapLink.click();
		PageObjectLogging.log("clickCustomMap", "custom map link clicked", true, driver);
		return new CreateACustomMapComponentObject(driver);
	}

	public CreateRealMapComponentObject clickRealMap() {
		waitForElementByElement(realMapLink);
		realMapLink.click();
		PageObjectLogging.log("clickRealMap", "Real Map link clicked", true, driver);
		return new CreateRealMapComponentObject(driver);
	}

	public InteractiveMapsPageObject clickCloseButton() {
		waitForElementByElement(closeButton);
		closeButton.click();
		return new InteractiveMapsPageObject(driver);
	}
	
	public void verifyLearnMoreLinkRedirect(String link) {
		waitForElementByElement(learnMoreLink);
		Assertion.assertEquals(learnMoreLink.getAttribute("href").contains(link), true);
	}

	public void verifyLoginModal() {
		waitForElementByElement(loginModal);
		PageObjectLogging.log("verifyLoginModal", "Login modal is displayed", true);
	}
}
