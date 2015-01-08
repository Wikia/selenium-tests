package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InteractiveMapsMercuryComponentObject extends MercuryBasePageObject {

	public InteractiveMapsMercuryComponentObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = ".current")
	private WebElement mapFrame;
	@FindBy(css = "div[id='map']")
	private WebElement mapDiv;
	@FindBy(css =".lightbox-close-button")
	private WebElement closeMapLightbox;
	@FindBy(css = ".lightbox-header-title")
	private WebElement mapTitle;
	@FindBy(css = ".leaflet-control-zoom-in")
	private WebElement zoomInButton;
	@FindBy(css = ".leaflet-control-zoom-out")
	private WebElement zoomOutButton;
	@FindBy(css = ".filter-menu-header")
	private WebElement filterBox;


	public void verifyMapModalIsVisible() {
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(mapDiv);
		Assertion.assertTrue(checkIfElementOnPage(mapDiv));
	}


}
