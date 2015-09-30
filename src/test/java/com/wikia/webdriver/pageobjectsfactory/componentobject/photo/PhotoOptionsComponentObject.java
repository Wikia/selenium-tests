package com.wikia.webdriver.pageobjectsfactory.componentobject.photo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

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
    super(driver);
    // TODO Auto-generated constructor stub
  }

  public void setCaption(String caption) {
    wait.forElementVisible(captionField);
    captionField.clear();
    captionField.sendKeys(caption);
    LOG.success("setCaption", caption + " set");
  }

  public void clickAddPhoto() {
    wait.forElementVisible(addPhotoButton);
    scrollAndClick(addPhotoButton);
    waitForElementNotVisibleByElement(addPhotoButton);
    LOG.success("clickAddPhoto", "add photo button clicked");
  }

  public void adjustAlignment(int i) {
    wait.forElementVisible(alignmentRow);
    switch (i) {
      case 1:
        alignmentLeft.click();
        LOG.success("adjustAlignment", "left alignment selected");
        break;
      case 2:
        alignmentRight.click();
        LOG.success("adjustAlignment", "right alignment selected");
        break;
      default:
        LOG.error("adjustAlignment", "invalid alignment selected");

    }
  }

  public void adjustLayout() {
    // TODO
  }

  public void replaceCaption() {
    // TODO
  }

  public PhotoAddComponentObject clickSelectAnotherPhoto() {
    selectAnotherPhotoButton.click();
    LOG.success("selectAnotherPhoto", "select Another Photo button clicked");
    return new PhotoAddComponentObject(driver);
  }
}
