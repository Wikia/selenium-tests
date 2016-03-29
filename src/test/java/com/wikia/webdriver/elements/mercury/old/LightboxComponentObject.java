package com.wikia.webdriver.elements.mercury.old;

import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.components.Loading;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LightboxComponentObject {

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

  private Wait wait;
  private Loading loading;

  public LightboxComponentObject(WebDriver driver) {
    this.wait = new Wait(driver);
    this.loading = new Loading(driver);

    PageFactory.initElements(driver, this);
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

    return true;
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
    loading.handleAsyncPageReload();
    wait.forElementVisible(currentImage);
    currentImage.click();
  }
}
