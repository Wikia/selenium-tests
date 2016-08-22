package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpecialUploadPageObject extends WikiBasePageObject {

  @FindBy(css = "#wpUploadFile")
  private WebElement browseForFileInput;
  @FindBy(css = "#wpIgnoreWarning")
  private WebElement ignoreAnyWarnings;
  @FindBy(css = "input.mw-htmlform-submit")
  private WebElement uploadFileInput;
  @FindBy(css = "#wpDestFile")
  private WebElement uploadFileName;

  public SpecialUploadPageObject(WebDriver driver) {
    super();
  }

  /**
   * Selects given file in upload browser.
   *
   *  @param file file to Be uploaded
   *              Look at folder acceptancesrc/src/test/resources/ImagesForUploadTests
   *              this is where those files are stored
   */
  public void selectFileToUpload(String file) {
    browseForFileInput.sendKeys(
        CommonUtils.getAbsolutePathForFile(PageContent.IMAGE_UPLOAD_RESOURCES_PATH + file)
    );
    PageObjectLogging.log(
        "typeInFileToUploadPath",
        "file " + file + " added to upload",
        true
    );

  }

  public void checkIgnoreAnyWarnings() {
    wait.forElementClickable(ignoreAnyWarnings);
    scrollAndClick(ignoreAnyWarnings);
    PageObjectLogging.log(
        "checkIgnoreAnyWarnings",
        "ignore warnings checkbox selected",
        true,
        driver
    );

  }

  public FilePage clickUploadButton() {
    scrollAndClick(uploadFileInput);
    PageObjectLogging.log("clickOnUploadFile", "upload file button clicked.", true);
    return new FilePage();
  }

  public void typeFileName(String fileName) {
    uploadFileName.clear();
    uploadFileName.sendKeys(fileName);
    PageObjectLogging.log("typeFileName", fileName + " typed into file name field", true);
  }
}
