package com.wikia.webdriver.elements.mercury.old.curatedcontent.imageupload;

import com.wikia.webdriver.common.core.elemnt.Wait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UploadImageModalComponentObject {

  @FindBy(css = ".modal-dialog li:nth-of-type(3)")
  private WebElement searchForImageButton;
  @FindBy(css = ".modal-dialog-wrapper.menu .modal-dialog")
  private WebElement modal;
  @FindBy(css = "#fileUpload")
  private WebElement uploadInput;
  @FindBy(css = ".modal-dialog .crop-image")
  private WebElement cropImageButton;

  private Wait wait;
  private WebDriver driver;

  public UploadImageModalComponentObject(WebDriver driver) {
    this.driver = driver;
    this.wait = new Wait(driver);

    PageFactory.initElements(driver, this);
  }

  public SearchForImagePageObject clickSearchForImageButton() {
    wait.forElementVisible(searchForImageButton);
    searchForImageButton.click();

    return new SearchForImagePageObject(driver);
  }

  public CroppingToolPageObject uploadImage(String filePath) {
    wait.forElementVisible(modal);
    uploadInput.sendKeys(filePath);
    uploadInput.submit();
    return new CroppingToolPageObject(driver);
  }

  public boolean isCropOptionEnabled() {
    return !cropImageButton.getAttribute("class").contains("disabled");
  }

  public CroppingToolPageObject selectCrop() {
    wait.forElementVisible(cropImageButton);
    cropImageButton.click();

    return new CroppingToolPageObject(driver);
  }
}
