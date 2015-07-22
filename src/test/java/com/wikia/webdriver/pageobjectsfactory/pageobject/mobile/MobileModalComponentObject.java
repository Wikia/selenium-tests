package com.wikia.webdriver.pageobjectsfactory.pageobject.mobile;

import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MobileModalComponentObject extends MobileBasePageObject {

  public MobileModalComponentObject(WebDriver driver) {
    super(driver);
  }

  @FindBy(css = ".zoomer.open.imgMdl")
  private WebElement openedModal;
  @FindBy(css = "#wkMdlClo")
  private WebElement closeModalButton;
  @FindBy(css = "#prvImg")
  private WebElement previousImageButton;
  @FindBy(css = "#nxtImg")
  private WebElement nextImageButton;
  @FindBy(css = "section.current>img")
  private WebElement currentImage;
  @FindBy(css = ".imgMdl.zoomer:not(.hdn)")
  private WebElement topBarVisible;
  @FindBy(css = ".imgMdl.zoomer.hdn")
  private WebElement topBarHidden;
  @FindBy(css = ".swiperPage.current")
  private WebElement currentImageModal;

  String modalTransition = "section[style*='transition']";

  public void closeModal() {
    wait.forElementVisible(openedModal);
    waitForElementClickableByElement(closeModalButton);
    scrollAndClick(closeModalButton);
  }

  public void closeModalWithBackButton() {
    driver.navigate().back();
  }

  public void goToPreviousImage() {
    wait.forElementVisible(previousImageButton);
    previousImageButton.click();
    waitForElementNotPresent(modalTransition);
  }

  public void goToNextImage() {
    wait.forElementVisible(nextImageButton);
    nextImageButton.click();
    waitForElementNotPresent(modalTransition);
  }

  public String getCurrentImageUrl() {
    return currentImage.getAttribute("src");
  }

  public void verifyTopBarVisible() {
    wait.forElementVisible(topBarVisible);
  }

  public void verifyTopBarHidden() {
    wait.forElementVisible(topBarHidden);
  }

  public void verifyModalClosed() {
    waitForElementNotVisibleByElement(currentImageModal);
    waitForElementNotVisibleByElement(openedModal);
    PageObjectLogging.log("verifyModalClosed", "modal was closed", true);
  }

  public void hideTopBar() {
    wait.forElementVisible(topBarVisible);
    topBarVisible.click();
  }

  public void showTopBar() {
    wait.forElementVisible(topBarHidden);
    topBarHidden.click();
  }

}
