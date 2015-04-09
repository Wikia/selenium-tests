package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.common.core.imageutilities.ImageComparison;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.List;

/**
 * @author: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 * @ownership: Content - Mercury mobile
 */
public class LightboxComponentObject extends BasePageObject {

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

  public LightboxComponentObject(WebDriver driver) {
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
  }

  public String getCurrentImagePath() throws WebDriverException {
    waitForElementByElement(currentImage);
    if (currentImage.getAttribute("src") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return currentImage.getAttribute("src");
  }

  public boolean isLightboxOpened() {
    try {
      waitForElementVisibleByElementCustomTimeOut(lightboxContent, 5, 1000);
    } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
      return false;
    }
    return true;
  }

  public boolean isCurrentImageVisible() {
    waitForElementVisibleByElement(currentImage);
    return checkIfElementOnPage(currentImage);
  }

  public boolean isLightboxHeaderDisplayed() {
    return !lightboxHeader.getCssValue("display").contains("none");
  }

  public boolean isLightboxFooterDisplayed() {
    return !lightboxFooter.getCssValue("display").contains("none");
  }
}
