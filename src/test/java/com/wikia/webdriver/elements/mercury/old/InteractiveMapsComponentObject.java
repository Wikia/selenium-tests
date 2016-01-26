package com.wikia.webdriver.elements.mercury.old;

import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
  @FindBy(css = ".wikia-map > img")
  private WebElement mapThumbnail;

  private JavascriptExecutor jsexec;

  public InteractiveMapsComponentObject(WebDriver driver) {
    super(driver);
    this.jsexec = (JavascriptExecutor) driver;
  }

  public void clickMapThumbnail() {
    wait.forElementVisible(mapThumbnail);
    mapThumbnail.click();
  }

  public void clickCloseButton() {
    wait.forElementVisible(closeMapLightbox);
    closeMapLightbox.click();
  }

  public void clickFilterBox() {
    wait.forElementVisible(filterBoxHeader);
    jsexec.executeScript("arguments[0].click();", filterBoxHeader);
  }

  public void clickZoomIn() {
    wait.forElementVisible(zoomInButton);
    jsexec.executeScript("arguments[0].click();", zoomInButton);
  }

  public void clickZoomOut() {
    wait.forElementVisible(zoomOutButton);
    jsexec.executeScript("arguments[0].click();", zoomOutButton);
  }

  public void clickPin() {
    wait.forElementVisible(poiPin);
    jsexec.executeScript("arguments[0].click();", poiPin);
  }

  public boolean isFilterBoxWasExpanded() {
    try {
      wait.forElementVisible(filterBox, 5, 1000);
    } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
      PageObjectLogging.log("Filter box not expanded", e, true);
      return false;
    }
    return true;
  }

  public boolean isMapModalVisible() {
    try {
      wait.forElementVisible(lightbox, 5, 1000);
    } catch (TimeoutException e) {
      PageObjectLogging.log("Map modal not visible", e, true);
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
