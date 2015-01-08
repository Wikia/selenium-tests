package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
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
	private WebElement filterBoxHeader;
	@FindBy(css = ".point-types")
	private WebElement filterBoxPoints;


	public void clickCloseButton() {
		driver.switchTo().activeElement();
		waitForElementVisibleByElement(closeMapLightbox);
		closeMapLightbox.click();
		PageObjectLogging.log("clickCloseButton", "Close button was clicked", true);
	}

	public void clickFilterBox() {
		waitForElementVisibleByElement(filterBoxHeader);
		filterBoxHeader.click();
		PageObjectLogging.log("clickFilterBox", "Filter box caption was clicked", true);
	}

	public void verifyFilterBoxWasExpanded() {
		waitForElementVisibleByElement(filterBoxPoints);
		Assertion.assertTrue(checkIfElementOnPage(filterBoxPoints));
	}

	public void verifyMapModalIsNotVisible() {
		driver.switchTo().activeElement();
		Assertion.assertFalse(checkIfElementOnPage(mapDiv));
	}

	public void verifyMapModalIsVisible() {
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(mapDiv);
		Assertion.assertTrue(checkIfElementOnPage(mapDiv));
	}



}
