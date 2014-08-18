package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;

import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;
/**
 * @author Łukasz Nowak (Dyktus)
 */

public class EmbedMapComponentObject extends InteractiveMapPageObject{
	
	public EmbedMapComponentObject(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = ".leaflet-control-embed-map-code-button")
	private WebElement embedMapButton;
	@FindBy(css = ".leaflet-draw-draw-marker")
	private WebElement addPinButton;
	@FindBy(css = ".leaflet-control-zoom-in")
	private WebElement zoomInButton;
	@FindBy(css = ".leaflet-control-zoom-out")
	private WebElement zoomOutButton;
	@FindBy(css = ".filter-menu-header")
	private WebElement filterBox;
	@FindBy(css = "a[title=close]")
	private WebElement closeModalButton;
	@FindBy(css = "header > h3")
	private WebElement mapTitle;
	@FindBy(css = "iframe[name=wikia-interactive-map]")
	private WebElement mapFrame;
	@FindBy(css = ".modal.large")
	private WebElement mapModal;
	@FindBy(css = "#attr")
	private WebElement brandFooter;
	
	public void verifyMapTitlePresented() {
		waitForElementByElement(mapTitle);
		Assertion.assertEquals(checkIfElementOnPage(mapTitle), true);
	}
	
	public void verifyCloseButtonPresented() {
		waitForElementByElement(closeModalButton);
		Assertion.assertEquals(checkIfElementOnPage(closeModalButton), true);
	}
	
	public void verifyEmbedMapModalOpened() {
		waitForElementByElement(mapModal);
		Assertion.assertEquals(checkIfElementOnPage(mapModal),true);
	}
	
	public void verifyMapElementsPresented() {
		driver.switchTo().frame(mapFrame);
		waitForElementByElement(filterBox);
		Assertion.assertEquals(checkIfElementOnPage(filterBox), true);
		waitForElementByElement(zoomInButton);
		Assertion.assertEquals(checkIfElementOnPage(zoomInButton), true);
		waitForElementByElement(zoomOutButton);
		Assertion.assertEquals(checkIfElementOnPage(zoomOutButton), true);
		PageObjectLogging.log("verifyMapElementsPresented", "Map elements was presented", true, driver);
	}
	
	public void verifyBranFooterNotVisible() {
		Assertion.assertEquals(checkIfElementOnPage(brandFooter), false);
	}
	
	public void verifyBranFooterVisible() {
		Assertion.assertEquals(checkIfElementOnPage(brandFooter), true);
	}
	
}
