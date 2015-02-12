package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import java.io.File;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.imageutilities.ImageComparison;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

import org.openqa.selenium.NoSuchElementException;
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

  private int waitTime = 5000;

  public InteractiveMapsMercuryComponentObject(WebDriver driver) {
    super(driver);
  }

  public void clickCloseButton() {
    waitForElementVisibleByElement(closeMapLightbox);
    closeMapLightbox.click();
    PageObjectLogging.log("clickCloseButton", "Close button was clicked", true);
  }

  public void clickFilterBox() {
    driver.switchTo().frame(mapFrame);
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
    String methodName = "clickPin";
    try {
      driver.switchTo().frame(mapFrame);
      waitForElementVisibleByElement(poiPin);
      tapOnElement(poiPin);
    } catch (NoSuchElementException e) {
      PageObjectLogging.log(methodName, "There is no pins, add them manualy", false);
      PageObjectLogging.log(methodName, e.getMessage(), false);
    }
    PageObjectLogging.log(methodName, "Pin was clicked", true);
  }

  public String getMapLeafletSrc() {
    return leafletTile.getAttribute("src");
  }

  public void verifyFilterBoxWasExpanded() {
    waitForElementVisibleByElement(filterBoxPoints);
    Assertion.assertTrue(checkIfElementOnPage(filterBoxPoints));
  }

  public void verifyMapModalIsNotVisible() {
    Assertion.assertFalse(checkIfElementOnPage(lightbox));
  }

  public void verifyMapModalIsVisible() {
    waitForElementByElement(lightbox);
    Assertion.assertTrue(checkIfElementOnPage(lightbox));
  }

  public void verifyMapTitleInHeader() {
    waitForElementVisibleByElement(mapTitle);
    Assertion.assertFalse(mapTitle.getText().isEmpty());
  }

  public void verifyMapIdInUrl() {
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
    driver.switchTo().frame(mapFrame);
    String methodName = "verifyZoomButtons";
    Shooter shooter = new Shooter();
    ImageComparison ic = new ImageComparison();
    File beforeZooming = shooter.capturePage(driver);
    clickZoomOut();
    waitMilliseconds(waitTime, methodName);
    File afterZoomOut = shooter.capturePage(driver);
    clickZoomIn();
    waitMilliseconds(waitTime, methodName);
    File afterZoomIn = shooter.capturePage(driver);
    Assertion.assertFalse(ic.areFilesTheSame(beforeZooming, afterZoomOut), "Zoom out doesn't work");
    Assertion.assertTrue(ic.areFilesTheSame(beforeZooming, afterZoomIn), "Zoom in doesn't work");
  }

  public void verifyZoomByGesture(PerformTouchAction touchAction) {
    driver.switchTo().frame(mapFrame);
    String methodName = "verifyZoomByGesture";
    try {
      Assertion.assertTrue(zoomInButton.getAttribute("class").contains("disabled"),
          "Zoom in button is enabled");
      touchAction.zoomInOutPointXY(50, 50, 50, 100, PerformTouchAction.ZOOM_WAY_OUT, waitTime);
      Assertion.assertFalse(zoomInButton.getAttribute("class").contains("disabled"),
          "Zoom in button is disabled");
      touchAction.zoomInOutPointXY(50, 50, 50, 100, PerformTouchAction.ZOOM_WAY_IN, waitTime);
      Assertion.assertTrue(zoomInButton.getAttribute("class").contains("disabled"),
          "Zoom in button is enabled");
    } catch (NoSuchElementException e) {
      PageObjectLogging.log(methodName, e.getMessage(), false);
    }
  }

  public void verifyScrollableFilterList(PerformTouchAction touchAction) {
    String methodName = "verifyScrollableFilterList";
    Shooter shooter = new Shooter();
    ImageComparison ic = new ImageComparison();
    File beforeScrolling = shooter.capturePage(driver);
    try {
      clickFilterBox();
      waitMilliseconds(waitTime, methodName);
      touchAction.swipeFromPointToPoint(40, 80, 40, 40, 500, waitTime);
      File afterScrolling = shooter.capturePage(driver);
      Assertion.assertFalse(ic.areFilesTheSame(beforeScrolling, afterScrolling),
          "Scrolling in filter box doesn't work");
    } catch (NoSuchElementException e) {
      PageObjectLogging.log(methodName, e.getMessage(), false);
    }
  }
}
