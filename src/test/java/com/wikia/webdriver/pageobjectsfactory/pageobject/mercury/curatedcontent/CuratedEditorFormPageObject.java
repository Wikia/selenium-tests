package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload.UploadImageModalComponentObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
 */
public class CuratedEditorFormPageObject extends BasePageObject {

  @FindBy(css = "input#label")
  private WebElement displayNameField;
  @FindBy(css = ".curated-content-editor-photo")
  private WebElement imageField;

  @FindBy(css = ".sub-head--cancel")
  private WebElement backButton;
  @FindBy(css = ".curated-content-editor-remove")
  private WebElement deleteItemButton;

  public CuratedEditorFormPageObject(WebDriver driver) {
    super(driver);
  }

  public void typeDisplayName(String displayName) {
    waitAndSendKeys(displayNameField, displayName);
  }

  public UploadImageModalComponentObject clickOnImage() {
    waitAndClick(imageField);
    return new UploadImageModalComponentObject(driver);
  }

  public void waitForDeleteButtonToBeVisible() {
    wait.forElementVisible(deleteItemButton);
  }
}
