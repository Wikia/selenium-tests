package com.wikia.webdriver.elements.mercury.old.curatedcontent;

import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.elements.mercury.components.Loading;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.imageupload.UploadImageModalComponentObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class CuratedEditorFormPageObject {

  @FindBy(css = "input#label")
  protected WebElement displayNameField;
  @FindBy(css = "input#title")
  protected WebElement pageNameField;

  @FindBy(css = ".sub-head--done")
  protected WebElement doneButton;

  @FindBy(css = ".curated-content-editor-remove")
  protected WebElement deleteItemButton;
  @FindBy(css = ".curated-content-editor-photo")
  protected WebElement imageField;

  protected Wait wait;
  protected WebDriver driver;
  private Loading loading;

  public CuratedEditorFormPageObject(WebDriver driver) {
    this.driver = driver;
    this.wait = new Wait(driver);
    this.loading = new Loading(driver);

    PageFactory.initElements(driver, this);
  }

  public void clickDoneButton() {
    loading.handleAsyncPageReload();
    wait.forElementVisible(doneButton);
    doneButton.click();
  }

  public UploadImageModalComponentObject clickOnImage() {
    wait.forElementVisible(imageField);
    imageField.click();

    return new UploadImageModalComponentObject(driver);
  }

  public void typeDisplayName(String displayName) {
    wait.forElementVisible(displayNameField);
    displayNameField.sendKeys(displayName);
  }

  public void typePageName(String pageName) {
    wait.forElementVisible(pageNameField);
    pageNameField.sendKeys(pageName);
  }

  public void waitForDeleteButtonToBeVisible() {
    wait.forElementVisible(deleteItemButton);
  }
}
