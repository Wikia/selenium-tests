package com.wikia.webdriver.elements.mercury.old;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class InteractiveMapsComponentObject extends WikiBasePageObject {

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
  @FindBy(css = ".article-media-map-thumbnail img")
  private WebElement mapThumbnail;

  public void clickMapThumbnail() {
    wait.forElementVisible(mapThumbnail);
    mapThumbnail.click();
  }

  public void clickCloseButton() {
    wait.forElementVisible(closeMapLightbox);
    closeMapLightbox.click();
  }

  public void clickPin() {
    wait.forElementVisible(poiPin);
    driver.executeScript("arguments[0].click();", poiPin);
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
    wait.forElementVisible(poiPopUp);

    return true;
  }

  public void switchToMapFrame() {
    wait.forElementVisible(lightbox);
    driver.switchTo().frame(mapFrame);
  }

  public void switchToDefaultFrame() {
    driver.switchTo().defaultContent();
  }
}
