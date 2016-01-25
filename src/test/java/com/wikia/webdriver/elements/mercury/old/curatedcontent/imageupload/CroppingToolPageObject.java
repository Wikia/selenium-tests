package com.wikia.webdriver.elements.mercury.old.curatedcontent.imageupload;

import com.wikia.webdriver.elements.mercury.old.curatedcontent.CuratedEditorFormPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CroppingToolPageObject extends CuratedEditorFormPageObject {

  @FindBy (css=".cropper-container")
  private WebElement cropperContainer;

  public CroppingToolPageObject(WebDriver driver) {
    super(driver);
  }

  public boolean isCropperLoaded() {
    return isElementOnPage(cropperContainer);
  }

  public void waitForCropperToBeLoaded() {
    wait.forElementVisible(cropperContainer);
  }
}
