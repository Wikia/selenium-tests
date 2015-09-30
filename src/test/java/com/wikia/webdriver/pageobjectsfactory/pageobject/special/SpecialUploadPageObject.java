package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;

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
    super(driver);
  }

  /**
   * Selects given file in upload browser.
   *
   * @author Michal Nowierski ** @param file file to Be uploaded <p> Look at folder
   * acceptancesrc/src/test/resources/ImagesForUploadTests - this is where those files are stored
   */

  public void selectFileToUpload(String file) {
    browseForFileInput.sendKeys(
        CommonUtils.getAbsolutePathForFile(PageContent.IMAGE_UPLOAD_RESOURCES_PATH + file)
    );
    LOG.logResult(
        "typeInFileToUploadPath",
        "file " + file + " added to upload",
        true
    );

  }

  public void checkIgnoreAnyWarnings() {
    wait.forElementClickable(ignoreAnyWarnings);
    scrollAndClick(ignoreAnyWarnings);
    LOG.log(
        "checkIgnoreAnyWarnings",
        "ignore warnings checkbox selected",
        true,
        driver
    );

  }

  public FilePagePageObject clickUploadButton() {
    scrollAndClick(uploadFileInput);
    LOG.log("clickOnUploadFile", "upload file button clicked.", LOG.Type.SUCCESS);
    return new FilePagePageObject(driver);
  }

  public void typeFileName(String fileName) {
    uploadFileName.clear();
    uploadFileName.sendKeys(fileName);
    LOG.log("typeFileName", fileName + " typed into file name field", LOG.Type.SUCCESS);
  }
}
