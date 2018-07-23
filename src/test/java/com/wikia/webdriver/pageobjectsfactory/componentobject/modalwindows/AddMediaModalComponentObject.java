package com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddMediaModalComponentObject extends WikiBasePageObject {

  @FindBy(css = "#VideoEmbedBackWrapper")
  protected WebElement addVideoModal;
  @FindBy(css = "#UploadPhotosWrapper")
  private WebElement addPhotoModal;
  @FindBy(css = "#UploadPhotosWrapper .close")
  private WebElement modalAddPhotoClose;
  @FindBy(css = "#VideoEmbedBackWrapper button.close")
  private WebElement modalAddVideoClose;

  public void closeAddPhotoModal() {
    wait.forElementVisible(addPhotoModal);
    Log.log("UploadPhotoModalIsPresent", "Upload photo modal is present", true, driver);
    scrollAndClick(modalAddPhotoClose);
    waitForElementNotVisibleByElement(addPhotoModal);
    Log.log("UploadPhotoModalClosed", "Upload photo modal is closed", true, driver);
  }

  public void closeAddVideoModal() {
    wait.forElementVisible(addVideoModal);
    Log.log("UploadVideoModalIsPresent", "Upload video modal is present", true, driver);
    scrollAndClick(modalAddVideoClose);
    waitForElementNotVisibleByElement(addVideoModal);
    Log.log("UploadVideoModalClosed", "Upload video modal is closed", true, driver);
  }

  public boolean isVideoModalVisible() {
    wait.forElementVisible(addVideoModal);

    return true;
  }
}
