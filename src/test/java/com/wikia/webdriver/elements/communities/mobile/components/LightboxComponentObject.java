package com.wikia.webdriver.elements.communities.mobile.components;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class LightboxComponentObject extends WikiBasePageObject {

  @FindBy(css = ".lightbox-content")
  private WebElement lightboxContent;
  @FindBy(css = ".lightbox-image")
  private WebElement currentImage;
  @FindBy(css = ".lightbox-footer")
  private WebElement lightboxFooter;
  @FindBy(css = ".lightbox-header")
  private WebElement lightboxHeader;
  @FindBy(css = ".lightbox-close-wrapper")
  private WebElement closeLightboxButton;
  @FindBy(css = "article img")
  private List<WebElement> imageList;

  public boolean isLightboxOpened() {
    try {
      wait.forElementVisible(lightboxContent, 5, 1000);
    } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
      Log.log("Lightbox not opened", e, true);

      return false;
    }

    return true;
  }

  public boolean isCurrentImageVisible() {
    wait.forElementVisible(currentImage);

    return true;
  }

  public boolean isLightboxFooterDisplayed() {
    try {
      wait.forElementVisible(lightboxFooter);

      return true;
    } catch (TimeoutException e) {

      return false;
    }
  }

  public void clickCloseButton() {
    wait.forElementClickable(closeLightboxButton);
    closeLightboxButton.click();
  }

  public void clickOnImage() {
    wait.forElementClickable(currentImage);
    currentImage.click();
  }

  public void openLightboxImage(int index) {
    WebElement targetImage = imageList.get(index);
    scrollAndClick(targetImage);
  }
}
