package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload.UploadImageModalComponentObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
 */
public abstract class CuratedEditorFormPageObject extends BasePageObject {

  @FindBy(css = "input#label")
  protected WebElement displayNameField;
  @FindBy(css = "input#title")
  protected WebElement pageNameField;

  @FindBy(css = ".sub-head--cancel")
  protected WebElement backButton;
  @FindBy(css = ".sub-head--done")
  protected WebElement doneButton;

  @FindBy(css = ".curated-content-editor-remove")
  protected WebElement deleteItemButton;
  @FindBy(css = ".curated-content-editor-photo")
  protected WebElement imageField;

  public CuratedEditorFormPageObject(WebDriver driver) {
    super(driver);
  }

  public void clickDoneButton() {
    waitAndClick(doneButton);
  }

  public UploadImageModalComponentObject clickOnImage() {
    waitAndClick(imageField);
    return new UploadImageModalComponentObject(driver);
  }

  public void typeDisplayName(String displayName) {
    waitAndSendKeys(displayNameField, displayName);
  }

  public void typePageName(String pageName) {
    waitAndSendKeys(pageNameField, pageName);
  }

  public void waitForDeleteButtonToBeVisible() {
    wait.forElementVisible(deleteItemButton);
  }
}
