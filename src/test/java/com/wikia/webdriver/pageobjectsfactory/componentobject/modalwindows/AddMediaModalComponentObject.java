package com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Bogna 'bognix' Knychala
 */
public class AddMediaModalComponentObject extends WikiBasePageObject {

  @FindBy(css = "#UploadPhotosWrapper")
  private WebElement addPhotoModal;
  @FindBy(css = "#UploadPhotosWrapper .close")
  private WebElement modalAddPhotoClose;
  @FindBy(css = "#VideoEmbedBackWrapper button.close")
  private WebElement modalAddVideoClose;
  @FindBy(css = "#VideoEmbedBackWrapper")
  protected WebElement addVideoModal;

  public AddMediaModalComponentObject(WebDriver driver) {
    super(driver);
  }

  public void closeAddPhotoModal() {
    wait.forElementVisible(addPhotoModal);
    LOG.logResult(
        "UploadPhotoModalIsPresent",
        "Upload photo modal is present",
        true, driver
    );
    scrollAndClick(modalAddPhotoClose);
    waitForElementNotVisibleByElement(addPhotoModal);
    LOG.logResult(
        "UploadPhotoModalClosed",
        "Upload photo modal is closed",
        true, driver
    );
  }

  public void closeAddVideoModal() {
    wait.forElementVisible(addVideoModal);
    LOG.logResult(
        "UploadVideoModalIsPresent",
        "Upload video modal is present",
        true, driver
    );
    scrollAndClick(modalAddVideoClose);
    waitForElementNotVisibleByElement(addVideoModal);
    LOG.logResult(
        "UploadVideoModalClosed",
        "Upload video modal is closed",
        true, driver
    );
  }
}
