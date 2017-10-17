package com.wikia.webdriver.pageobjectsfactory.componentobject.photo;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PhotoOptionsComponentObject extends BasePageObject {

  @FindBy(css = "#ImageUploadCaption")
  private WebElement captionField;
  @FindBy(css = "#ImageUploadDetails input[type='submit']")
  private WebElement addPhotoButton;
  @FindBy(css = "#ImageLayoutRow")
  private WebElement alignmentRow;
  @FindBy(css = "#ImageUploadLayoutLeft")
  private WebElement alignmentLeft;
  @FindBy(css = "#ImageUploadLayoutRight")
  private WebElement alignmentRight;
  @FindBy(css = ".yui-back")
  private WebElement selectAnotherPhotoButton;

  public PhotoOptionsComponentObject(WebDriver driver) {
    super();
    // TODO Auto-generated constructor stub
  }

  public void setCaption(String caption) {
    wait.forElementVisible(captionField);
    captionField.clear();
    captionField.sendKeys(caption);
    PageObjectLogging.log("setCaption", caption + " set", true);
  }

  public void clickAddPhoto() {
    wait.forElementVisible(addPhotoButton);
    scrollAndClick(addPhotoButton);
    waitForElementNotVisibleByElement(addPhotoButton);
    PageObjectLogging.log("clickAddPhoto", "addTextWith photo button clicked", true);
  }

  public void adjustAlignment(int i) {
    wait.forElementVisible(alignmentRow);
    switch (i) {
      case 1:
        alignmentLeft.click();
        PageObjectLogging.log("adjustAlignment", "left alignment selected", true);
        break;
      case 2:
        alignmentRight.click();
        PageObjectLogging.log("adjustAlignment", "right alignment selected", true);
        break;
      default:
        PageObjectLogging.log("adjustAlignment", "invalid alignment selected", false);

    }
  }

  public void adjustLayout() {
    //TODO
  }

  public void replaceCaption() {
    //TODO
  }

  public PhotoAddComponentObject clickSelectAnotherPhoto() {
    selectAnotherPhotoButton.click();
    PageObjectLogging.log("selectAnotherPhoto", "select Another Photo button clicked", true);
    return new PhotoAddComponentObject(driver);
  }
}
