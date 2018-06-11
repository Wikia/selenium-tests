package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SpecialMultipleUploadPageObject extends WikiBasePageObject {

  @FindBy(css = "input[name='wpIgnoreWarning']")
  private WebElement ignoreAnyWarnings;
  @FindBy(css = "input[name='wpUpload']")
  private WebElement uploadFileButton;
  @FindBy(css = "form[id='mw-upload-form']")
  private WebElement multipleUploadForm;
  @FindBy(css = "#wpUploadDescription")
  private WebElement multipleUploadSummaryField;
  @FindBy(css = "tr.mw-htmlform-field-UploadSourceField td.mw-input input")
  private List<WebElement> fileInputs;
  @FindBy(css = "div[id='mw-content-text'] ul li a")
  private List<WebElement> uploadedFileslist;
  @FindBy(css = "div[id='mw-content-text']")
  private WebElement uploadedFilesListContener;

  public SpecialMultipleUploadPageObject(WebDriver driver) {
    super();
  }

  /**
   * Selects given files in upload browser.
   * @param FilesNamesList List of files to be uploaded. Look at folder PageContent.resourcesPath
   */
  public void selectFilesToUpload(String[] filesNamesList) {
    wait.forElementVisible(multipleUploadForm);
    for (int i = 0; i < filesNamesList.length; i++) {
      jsActions.scrollToElement(fileInputs.get(i));
      fileInputs.get(i)
          .sendKeys(
              CommonUtils.getAbsolutePathForFile(
                  PageContent.IMAGE_UPLOAD_RESOURCES_PATH + filesNamesList[i]));
    }
    Log.log(
        "typeInFilesToUpload",
        filesNamesList.length + " files added to upload list",
        true
    );
  }

  public void typeInMultiUploadSummary(String summary) {
    multipleUploadSummaryField.sendKeys(summary);
    Log
        .log("typeInMultiUploadSummary", "summary: " + summary + " added to multiupload", true);
  }

  public void checkIgnoreAnyWarnings() {
    scrollAndClick(ignoreAnyWarnings);
    Log.log("CheckIgnoreAnyWarnings", "Check 'Ignore Any Warnings' option", true);

  }

  public void clickUploadButton() {
    scrollAndClick(uploadFileButton);
    Log.log("ClickOnUploadFile", "Click on Upload File button", true);

  }

  /**
   * Checks if the upload have been succesful. <p> The method checks if the uploaded files
   * correspond to those in FilesNamesList. FFilesNamesList is a parameter of the method
   *
   * @param filesNamesList list of expected names of files
   */
  public void verifySuccessfulUpload(String[] filesNamesList) {
    wait.forElementVisible(uploadedFilesListContener);
    for (int i = 0; i < filesNamesList.length; i++) {
      Assertion.assertStringContains(uploadedFileslist.get(i).getText(), filesNamesList[i]);
    }
  }
}
