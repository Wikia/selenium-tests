package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import java.io.File;

import com.wikia.webdriver.common.core.imageutilities.ImageComparison;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InteractiveMapsMercuryComponentObject extends MercuryBasePageObject {

  @FindBy(css = ".current")
  private WebElement mapFrame;
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
  @FindBy(css = ".leaflet-marker-icon")
  private WebElement poiPin;
  @FindBy(css = ".leaflet-popup")
  private WebElement poiPopUp;
  @FindBy(css = ".lightbox-content")
  private WebElement lightbox;
  @FindBy(css = ".view-map")
  private WebElement viewMapButton;

  private int waitTime = 5000;

  public InteractiveMapsMercuryComponentObject(WebDriver driver) {
    super(driver);
  }

  public void clickViewMapButton() {
    waitForElementVisibleByElement(viewMapButton);
    viewMapButton.click();
  }

  public void clickCloseButton() {
    waitForElementVisibleByElement(closeMapLightbox);
    closeMapLightbox.click();
  }

  public void clickFilterBox() {
    waitForElementVisibleByElement(filterBoxHeader);
    tapOnElement(filterBoxHeader);
  }

  public void clickZoomIn() {
    waitForElementVisibleByElement(zoomInButton);
    tapOnElement(zoomInButton);
  }

  public void clickZoomOut() {
    waitForElementVisibleByElement(zoomOutButton);
    tapOnElement(zoomOutButton);
  }

  public void clickPin() {
    waitForElementByElement(lightbox);
    driver.switchTo().frame(mapFrame);
    waitForElementVisibleByElement(poiPin);
    tapOnElement(poiPin);
  }

  public boolean isFilterBoxWasExpanded() {
    waitForElementVisibleByElement(filterBoxPoints);
    if (checkIfElementOnPage(filterBoxPoints)) {
      return true;
    }
    return false;
  }

  public boolean isMapModalVisible() {
    try {
      waitForElementByElement(lightbox);
      if (checkIfElementOnPage(lightbox)) {
        return true;
      }
      return false;
    } catch (TimeoutException e) {}
    return false;
  }

  public boolean isTextInMapTitleHeader() {
    waitForElementVisibleByElement(mapTitle);
    if (mapTitle.getText().isEmpty()) {
      return false;
    }
    return true;
  }

  public boolean isMapIdInUrl() {
    if (driver.getCurrentUrl().toString().contains("?map=")) {
      return true;
    }
    return false;
  }

  public boolean isPinPopUp() {
    if (checkIfElementOnPage(poiPopUp)) {
      return true;
    }
    return false;
  }

  public boolean isZoomButtonWorking(String zoomWay) {
    Shooter shooter = new Shooter();
    ImageComparison ic = new ImageComparison();
    File beforeZooming = shooter.capturePage(driver);
    if (zoomWay.equals("in")) {
      clickZoomIn();
    } else {
      clickZoomOut();
    }
    waitMilliseconds(waitTime, "waitMilliseconds");
    File afterZooming = shooter.capturePage(driver);
    if (ic.areFilesTheSame(beforeZooming, afterZooming)) {
      return false;
    }
    return true;
  }

  public boolean isZoomInButtonEnabled() {
    if (zoomInButton.getAttribute("class").contains("disabled")) {
      return false;
    }
    return true;
  }

  public boolean isFilterListScrollable(PerformTouchAction touchAction) {
    String methodName = "isFilterListScrollable";
    Shooter shooter = new Shooter();
    ImageComparison ic = new ImageComparison();
    File beforeScrolling = shooter.capturePage(driver);
    clickFilterBox();
    waitMilliseconds(waitTime, methodName);
    touchAction.swipeFromPointToPoint(40, 80, 40, 40, 500, waitTime);
    File afterScrolling = shooter.capturePage(driver);
    if (ic.areFilesTheSame(beforeScrolling, afterScrolling)) {
      return false;
    }
    return true;
  }

  public void switchToMapFrame() {
    waitForElementByElement(lightbox);
    driver.switchTo().frame(mapFrame);
  }
}
