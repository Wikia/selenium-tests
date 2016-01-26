package com.wikia.webdriver.elements.mercury.old.curatedcontent;

import com.wikia.webdriver.elements.mercury.Loading;
import com.wikia.webdriver.elements.mercury.old.BasePageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.imageupload.UploadImageModalComponentObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    new Loading(driver).handleAsyncPageReload();
    waitAndClick(doneButton);
  }

  public UploadImageModalComponentObject clickOnImage() {
    waitAndClick(imageField);
    return new UploadImageModalComponentObject(driver);
  }

  public void typeDisplayName(String displayName) {
    wait.forElementVisible(displayNameField);
    displayNameField.sendKeys(displayName);
  }

  public void typePageName(String pageName) {
    wait.forElementVisible(displayNameField);
    displayNameField.sendKeys(pageName);
  }

  public void waitForDeleteButtonToBeVisible() {
    wait.forElementVisible(deleteItemButton);
  }
}
