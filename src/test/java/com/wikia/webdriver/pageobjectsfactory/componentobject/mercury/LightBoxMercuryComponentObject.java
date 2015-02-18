package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.common.core.imageutilities.ImageComparison;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.List;

/**
 * @author: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 */

public class LightBoxMercuryComponentObject extends MercuryBasePageObject {

  @FindBy(css = ".lightbox-close-wrapper")
  private WebElement closeLightboxButton;
  @FindBy(css = ".current")
  private WebElement currentImage;
  @FindBy(css = ".lightbox-content")
  private WebElement lightboxContent;
  @FindBy(css = ".lightbox-header")
  private WebElement lightboxHeader;
  @FindBy(css = ".lightbox-footer")
  private WebElement lightboxFooter;
  @FindBy(css = ".article-gallery img")
  private List<WebElement> galleryImagesArray;

  public static final String EDGE_LEFT = "left";
  public static final String EDGE_RIGHT = "right";

  public static final String ZOOM_METHOD_GESTURE = "gesture";
  public static final String ZOOM_METHOD_TAP = "tap";

  public static final String DIRECTION_LEFT = "left";
  public static final String DIRECTION_RIGHT = "right";
  public static final String DIRECTION_UP = "up";
  public static final String DIRECTION_DOWN = "down";

  public LightBoxMercuryComponentObject(WebDriver driver) {
    super(driver);
  }

  public void clickCloseButton() {
    waitForElementVisibleByElement(closeLightboxButton);
    closeLightboxButton.click();
  }

  public void clickGalleryImage(int index) {
    waitForElementByElement(galleryImagesArray.get(index));
    scrollToElement(galleryImagesArray.get(index));
    galleryImagesArray.get(index).click();
    PageObjectLogging.log("clickGalleryImage", "Image was clicked by test", true);
  }

  public String getCurrentImagePath() {
    return currentImage.getAttribute("src");
  }

  public boolean isLightboxOpened() {
    try {
      waitForElementByElement(lightboxContent);
      if (checkIfElementOnPage(lightboxContent)) {
        return true;
      }
      return false;
    } catch (NoSuchElementException e) {}
    return false;
  }

  public boolean isCurrentImageVisible() {
    waitForElementVisibleByElement(currentImage);
    if (checkIfElementOnPage(currentImage)) {
      return true;
    }
    return false;
  }

  public boolean isDifferentImageAfterSwiping(PerformTouchAction touchAction, String direction, int attempts) {
    String currentImageSrc = getCurrentImagePath();
    String nextImageSrc;
    boolean imageChanged = false;
    int startX = 70;
    int endX = 20;
    int duration = 300;
    int waitAfter = 5000;
    int centerY = 50;
    if ("right".equals(direction)) {
      int temp = startX;
      startX = endX;
      endX = temp;
    } else {
      direction = "left";
    }
    for (int i = 0; i < attempts; ++i) {
      touchAction.swipeFromPointToPoint(startX, centerY, endX, centerY, duration, waitAfter);
      nextImageSrc = getCurrentImagePath();
      if (!nextImageSrc.contains(currentImageSrc)) {
        imageChanged = true;
        break;
      }
    }
    return imageChanged;
  }

  public boolean isLightboxHeaderDisplayed() {
    if (lightboxHeader.getCssValue("display").contains("none")) {
      return false;
    }
    return true;
  }

  public boolean isLightboxFooterDisplayed() {
    if (lightboxFooter.getCssValue("display").contains("none")) {
      return false;
    }
    return true;
  }

  public boolean isTappingOnImageEdgeChangeImage(PerformTouchAction touchAction, String edge) {
    waitForElementByElement(currentImage);
    String currentImageSrc = getCurrentImagePath();
    String nextImageSrc;
    int pointX = 25;
    int duration = 500;
    int waitAfter = 5000;
    if ("right".equals(edge)) {
      pointX = 75;
    } else {
      edge = "left";
    }
    touchAction.tapOnPointXY(pointX, 50, duration, waitAfter);
    nextImageSrc = getCurrentImagePath();
    if (nextImageSrc.contains(currentImageSrc)) {
      return false;
    }
    return true;
  }

  public boolean isZoomingByGestureWorking(PerformTouchAction touchAction, String zoomMethod) {
    waitForElementByElement(currentImage);
    Shooter shooter = new Shooter();
    ImageComparison ic = new ImageComparison();
    File beforeZooming = shooter.capturePage(driver);
    switch (zoomMethod) {
      case "gesture":
        touchAction.zoomInOutPointXY(50, 50, 50, 100, PerformTouchAction.ZOOM_WAY_IN, 3000);
        break;
      case "tap":
        touchAction.tapOnPointXY(50, 50, 140, 0);
        touchAction.tapOnPointXY(50, 50, 140, 3000);
        break;
      default:
        break;
    }
    File afterZoomIn = shooter.capturePage(driver);
    if (ic.areFilesTheSame(beforeZooming, afterZoomIn)) {
      return false;
    }
    switch (zoomMethod) {
      case "gesture":
        touchAction.zoomInOutPointXY(50, 50, 50, 140, PerformTouchAction.ZOOM_WAY_OUT, 3000);
        break;
      case "tap":
        touchAction.tapOnPointXY(50, 50, 140, 0);
        touchAction.tapOnPointXY(50, 50, 140, 3000);
        break;
      default:
        break;
    }
    File afterZoomOut = shooter.capturePage(driver);
    if (ic.areFilesTheSame(beforeZooming, afterZoomOut)) {
      return true;
    }
    return false;
  }

  public boolean isImageMovedToDirectionAfterZoomIn(PerformTouchAction touchAction,
                                                 String direction) {
    waitForElementByElement(currentImage);
    Shooter shooter = new Shooter();
    ImageComparison ic = new ImageComparison();
    File beforeZooming = shooter.capturePage(driver);
    touchAction.tapOnPointXY(50, 50, 140, 0);
    touchAction.tapOnPointXY(50, 50, 140, 2000);
    File afterZooming = shooter.capturePage(driver);
    if (ic.areFilesTheSame(beforeZooming, afterZooming)) {
      return false;
    }
    touchAction.swipeFromCenterToDirection(direction, 200, 200, 2000);
    File afterMoving = shooter.capturePage(driver);
    if (ic.areFilesTheSame(afterZooming, afterMoving)) {
      return false;
    }
    return true;
  }
}
