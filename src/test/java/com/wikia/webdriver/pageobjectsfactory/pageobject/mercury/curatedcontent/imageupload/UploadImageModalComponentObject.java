package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
 */
public class UploadImageModalComponentObject extends BasePageObject {

  @FindBy(css = ".modal-dialog li:nth-of-type(2)")
  private WebElement searchForImageButton;
  @FindBy(css = ".modal-dialog-wrapper.menu .modal-dialog")
  private WebElement modal;
  @FindBy(css = ".file-upload-label")
  private WebElement uploadButton;
  @FindBy(css = "#fileUpload")
  private WebElement uploadInput;

  public UploadImageModalComponentObject(WebDriver driver) {
    super(driver);
  }

  public SearchForImagePageObject clickSearchForImageButton() {
    waitAndClick(searchForImageButton);
    return new SearchForImagePageObject(driver);
  }

  public CroppingToolPageObject uploadImage(String filePath) {
    wait.forElementVisible(modal);
    uploadInput.sendKeys(filePath);
    return new CroppingToolPageObject(driver);
  }

  public void clickUpload() {
    waitAndClick(uploadButton);
  }
}
