package com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 * @author Bogna 'bognix' Knychala
 */
public class AddMediaModalComponentObject extends WikiBasePageObject {

  @FindBy(css = "#VideoEmbedBackWrapper")
  protected WebElement addVideoModal;
  @FindBy(css = "#UploadPhotosWrapper")
  private WebElement addPhotoModal;
  @FindBy(css = "#UploadPhotosWrapper .close")
  private WebElement modalAddPhotoClose;
  @FindBy(css = "#VideoEmbedBackWrapper button.close")
  private WebElement modalAddVideoClose;

  public AddMediaModalComponentObject(WebDriver driver) {
    super(driver);
  }

  public void closeAddPhotoModal() {
    wait.forElementVisible(addPhotoModal);
    LOG.success("UploadPhotoModalIsPresent", "Upload photo modal is present",true);
    scrollAndClick(modalAddPhotoClose);
    waitForElementNotVisibleByElement(addPhotoModal);
    LOG.success("UploadPhotoModalClosed", "Upload photo modal is closed",true);
  }

  public void closeAddVideoModal() {
    wait.forElementVisible(addVideoModal);
    LOG.success("UploadVideoModalIsPresent", "Upload video modal is present",true);
    scrollAndClick(modalAddVideoClose);
    waitForElementNotVisibleByElement(addVideoModal);
    LOG.success("UploadVideoModalClosed", "Upload video modal is closed",true);
  }
}
