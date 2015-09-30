package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedEditorFormPageObject;

/**
 * @ownership: Content X-Wing
 */
public class CroppingToolPageObject extends CuratedEditorFormPageObject {

  @FindBy(css = ".cropper-container")
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
