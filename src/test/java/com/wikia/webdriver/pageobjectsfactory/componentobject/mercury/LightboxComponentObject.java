package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownership: Content X-Wing
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
    wait.forElementVisible(closeLightboxButton);
    closeLightboxButton.click();
  }

  public void clickGalleryImage(int index) {
    wait.forElementVisible(galleryImagesArray.get(index));
    scrollToElement(galleryImagesArray.get(index));
    galleryImagesArray.get(index).click();
  }

  public String getCurrentImagePath() throws WebDriverException {
    wait.forElementVisible(currentImage);
    if (currentImage.getAttribute("src") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return currentImage.getAttribute("src");
  }

  public boolean isLightboxOpened() {
    try {
      wait.forElementVisible(lightboxContent, 5, 1000);
    } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
      return false;
    }
    return true;
  }

  public boolean isCurrentImageVisible() {
    wait.forElementVisible(currentImage);
    return isElementOnPage(currentImage);
  }

  public boolean isLightboxHeaderDisplayed() {
    return !lightboxHeader.getCssValue("display").contains("none");
  }

  public boolean isLightboxFooterDisplayed() {
    return !lightboxFooter.getCssValue("display").contains("none");
  }
}
