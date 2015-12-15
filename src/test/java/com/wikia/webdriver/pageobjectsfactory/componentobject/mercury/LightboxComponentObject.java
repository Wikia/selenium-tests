package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LightboxComponentObject extends BasePageObject {

  @FindBy(css = ".lightbox-content")
  private WebElement lightboxContent;
  @FindBy(css = ".current")
  private WebElement currentImage;
  @FindBy(css = ".lightbox-footer")
  private WebElement lightboxFooter;
  @FindBy(css = ".lightbox-header")
  private WebElement lightboxHeader;
  @FindBy(css = ".lightbox-close-wrapper")
  private WebElement closeLightboxButton;


  public LightboxComponentObject(WebDriver driver) {
    super(driver);
  }

  public boolean isLightboxOpened() {
    try {
      wait.forElementVisible(lightboxContent, 5, 1000);
    } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
      PageObjectLogging.log("Lightbox not opened", e, true);
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

  public String getCurrentImagePath() throws WebDriverException {
    wait.forElementVisible(currentImage);
    if (currentImage.getAttribute("src") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return currentImage.getAttribute("src");
  }

  public void clickCloseButton() {
    wait.forElementVisible(closeLightboxButton);
    closeLightboxButton.click();
  }

  public void clickOnImage() {
    waitForLoadingOverlayToDisappear();
    waitAndClick(currentImage);
  }
}
