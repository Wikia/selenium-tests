package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 * @ownership: Content - Mercury mobile
 */
public class InteractiveMapsComponentObject extends BasePageObject {

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

  public InteractiveMapsComponentObject(WebDriver driver) {
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
    waitForElementVisibleByElement(poiPin);
    tapOnElement(poiPin);
  }

  public boolean isFilterBoxWasExpanded() {
    waitForElementVisibleByElement(filterBoxPoints);
    return checkIfElementOnPage(filterBoxPoints);
  }

  public boolean isMapModalVisible() {
    try {
      waitForElementVisibleByElementCustomTimeOut(lightbox, 5, 1000);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isTextInMapTitleHeader() {
    waitForElementVisibleByElement(mapTitle);
    return !mapTitle.getText().isEmpty();
  }

  public boolean isMapIdInUrl() {
    return driver.getCurrentUrl().contains("?map=");
  }

  public boolean isPinPopUp() {
    return checkIfElementOnPage(poiPopUp);
  }

  public boolean isZoomInButtonEnabled() throws WebDriverException {
    if (zoomInButton.getAttribute("class") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return !zoomInButton.getAttribute("class").contains("disabled");
  }

  public void switchToMapFrame() {
    waitForElementByElement(lightbox);
    driver.switchTo().frame(mapFrame);
  }

  public void switchToDefaultFrame() {
    driver.switchTo().defaultContent();
  }
}
