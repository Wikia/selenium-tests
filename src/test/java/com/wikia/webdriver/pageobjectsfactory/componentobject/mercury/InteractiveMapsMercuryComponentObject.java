package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import java.io.File;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.imageutilities.ImageComparison;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InteractiveMapsMercuryComponentObject extends MercuryBasePageObject {

  @FindBy(css = ".current")
  private WebElement mapFrame;
  @FindBy(css = "div[id='map']")
  private WebElement mapDiv;
  @FindBy(css = ".lightbox-close-wrapper")
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
  private WebElement leafletTile;
  @FindBy(css = ".leaflet-marker-icon")
  private WebElement poiPin;
  @FindBy(css = ".leaflet-popup")
  private WebElement poiPopUp;
  @FindBy(css = ".lightbox-content")
  private WebElement lightbox;

  public InteractiveMapsMercuryComponentObject(WebDriver driver) {
    super(driver);
  }

  public void clickCloseButton() {
//    driver.switchTo().activeElement();
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
    while (amountOfClicks != 0) {
      waitForElementClickableByElement(zoomInButton);
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
    while (amountOfClicks != 0) {
      waitForElementClickableByElement(zoomOutButton);
      tapOnElement(zoomOutButton);
      amountOfClicks--;
    }
    PageObjectLogging.log("clickZoomOut", "Zoom out button was clicked", true);
  }

  public void clickPin() {
    waitForElementVisibleByElement(poiPin);
    tapOnElement(poiPin);
    PageObjectLogging.log("clickPin", "Pin was clicked", true);
  }

  public String getMapLeafletSrc() {
    return leafletTile.getAttribute("src");
  }

  public void verifyFilterBoxWasExpanded() {
    waitForElementVisibleByElement(filterBoxPoints);
    Assertion.assertTrue(checkIfElementOnPage(filterBoxPoints));
  }

  public void verifyMapModalIsNotVisible() {
//    driver.switchTo().activeElement();
    Assertion.assertFalse(checkIfElementOnPage(lightbox));
  }

  public void verifyMapModalIsVisible() {
//    driver.switchTo().frame(mapFrame);
    waitForElementByElement(lightbox);
    Assertion.assertTrue(checkIfElementOnPage(lightbox));
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
    Assertion.assertFalse(leaflet.contains(newLeaflet));
    PageObjectLogging.log("verifyMapZoomChangedView", "Map view was changed", true, driver);
  }

  public void verifyPinPopUpAppeared() {
    Assertion.assertTrue(checkIfElementOnPage(poiPopUp));
    PageObjectLogging.log("verifyPinPopUpAppeared", "Pin popup appeared after click", true, driver);
  }
  
  public void verifyZoomButtons() {
    String methodName = "verifyZoomButtons";
    Shooter shooter = new Shooter();
    ImageComparison ic = new ImageComparison();
    File beforeZooming = shooter.capturePage(driver);
    clickZoomOut();
    waitMillisecons(5000, methodName);
    File afterZoomOut = shooter.capturePage(driver);
    clickZoomIn();
    waitMillisecons(5000, methodName);
    File afterZoomIn = shooter.capturePage(driver);
    if (ic.areFilesTheSame(beforeZooming, afterZoomOut)) {
      PageObjectLogging.log(methodName, "Zoom out doesn't work", false);
    } else {
      PageObjectLogging.log(methodName, "Zoom out works", true);
    }
    if (ic.areFilesTheSame(beforeZooming, afterZoomIn)) {
      PageObjectLogging.log(methodName, "Zoom in works", true);
    } else {
      PageObjectLogging.log(methodName, "Zoom in doesn't work", false);
    }
  }
}
