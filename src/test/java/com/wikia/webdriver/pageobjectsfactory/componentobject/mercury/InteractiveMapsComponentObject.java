package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
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
  @FindBy(css = ".filter-menu.shown-box")
  private WebElement filterBox;
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
    wait.forElementVisible(viewMapButton);
    viewMapButton.click();
  }

  public void clickCloseButton() {
    wait.forElementVisible(closeMapLightbox);
    closeMapLightbox.click();
  }

  public void clickFilterBox() {
    wait.forElementVisible(filterBoxHeader);
    tapOnElement(filterBoxHeader);
  }

  public void clickZoomIn() {
    wait.forElementVisible(zoomInButton);
    tapOnElement(zoomInButton);
  }

  public void clickZoomOut() {
    wait.forElementVisible(zoomOutButton);
    tapOnElement(zoomOutButton);
  }

  public void clickPin() {
    wait.forElementVisible(poiPin);
    tapOnElement(poiPin);
  }

  public boolean isFilterBoxWasExpanded() {
    try {
      wait.forElementVisible(filterBox, 5, 1000);
    } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
      LOG.success("Filter box not expanded", e);
      return false;
    }
    return true;
  }

  public boolean isMapModalVisible() {
    try {
      wait.forElementVisible(lightbox, 5, 1000);
    } catch (TimeoutException e) {
      LOG.success("Map modal not visible", e);
      return false;
    }
    return true;
  }

  public boolean isTextInMapTitleHeader() {
    wait.forElementVisible(mapTitle);
    return !mapTitle.getText().isEmpty();
  }

  public boolean isMapIdInUrl() {
    return driver.getCurrentUrl().contains("?map=");
  }

  public boolean isPinPopUp() {
    return isElementOnPage(poiPopUp);
  }

  public boolean isZoomInButtonEnabled() throws WebDriverException {
    if (zoomInButton.getAttribute("class") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return !zoomInButton.getAttribute("class").contains("disabled");
  }

  public void switchToMapFrame() {
    wait.forElementVisible(lightbox);
    driver.switchTo().frame(mapFrame);
  }

  public void switchToDefaultFrame() {
    driver.switchTo().defaultContent();
  }
}
