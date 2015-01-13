package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

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
	@FindBy(css = ".leaflet-tile")
	private List<WebElement> leafletTile;


	public void clickCloseButton() {
		driver.switchTo().activeElement();
		waitForElementVisibleByElement(closeMapLightbox);
		closeMapLightbox.click();
		PageObjectLogging.log("clickCloseButton", "Close button was clicked", true);
	}

	public void clickFilterBox() {
		waitForElementVisibleByElement(filterBoxHeader);
		tapOnElement(filterBoxHeader);
		PageObjectLogging.log("clickFilterBox", "Filter box caption was clicked", true);
	}

	public void clickZoomIn() {
		waitForElementVisibleByElement(zoomInButton);
		tapOnElement(zoomInButton);
		PageObjectLogging.log("clickZoomIn", "Zoom in button was clicked", true);
	}

	public void clickZoomIn(int amountOfClicks) {
		waitForElementVisibleByElement(zoomInButton);
		while(amountOfClicks!=0){
			tapOnElement(zoomInButton);
			amountOfClicks--;
		}
		PageObjectLogging.log("clickZoomIn", "Zoom in button was clicked", true);
	}

	public void clickZoomOut() {
		waitForElementVisibleByElement(zoomOutButton);
		tapOnElement(zoomOutButton);
		PageObjectLogging.log("clickZoomOut", "Zoom out button was clicked", true);
	}


	public void clickZoomOut(int amountOfClicks) {
		waitForElementVisibleByElement(zoomOutButton);
		while(amountOfClicks!=0) {
			tapOnElement(zoomOutButton);
			amountOfClicks--;
		}
		PageObjectLogging.log("clickZoomOut", "Zoom out button was clicked", true);
	}

	public String getMapLeafletSrc() {
		return leafletTile.get(0).getAttribute("src");
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

	public void verifyMapTitleInHeader() {
		driver.switchTo().defaultContent();
		waitForElementVisibleByElement(mapTitle);
		Assertion.assertFalse(mapTitle.getText().isEmpty());
	}

	public void verifyMapIdInUrl() {
		driver.switchTo().defaultContent();
		Assertion.assertTrue(driver.getCurrentUrl().toString().contains("?map="));
	}

	public void verifyMapZoomChangedView(String leaflet, String newLeaflet) {
		System.out.println(leaflet);
		System.out.println(newLeaflet);
		Assertion.assertFalse(leaflet.contains(newLeaflet));
	}
}
