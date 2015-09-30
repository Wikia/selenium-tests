package com.wikia.webdriver.pageobjectsfactory.componentobject.photo;

import com.wikia.webdriver.common.logging.LOG;
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
    super(driver);
    // TODO Auto-generated constructor stub
  }

  public void setCaption(String caption) {
    wait.forElementVisible(captionField);
    captionField.clear();
    captionField.sendKeys(caption);
    LOG.log("setCaption", caption + " set", LOG.Type.SUCCESS);
  }

  public void clickAddPhoto() {
    wait.forElementVisible(addPhotoButton);
    scrollAndClick(addPhotoButton);
    waitForElementNotVisibleByElement(addPhotoButton);
    LOG.log("clickAddPhoto", "add photo button clicked", LOG.Type.SUCCESS);
  }

  public void adjustAlignment(int i) {
    wait.forElementVisible(alignmentRow);
    switch (i) {
      case 1:
        alignmentLeft.click();
        LOG.log("adjustAlignment", "left alignment selected", LOG.Type.SUCCESS);
        break;
      case 2:
        alignmentRight.click();
        LOG.log("adjustAlignment", "right alignment selected", LOG.Type.SUCCESS);
        break;
      default:
        LOG.log("adjustAlignment", "invalid alignment selected", LOG.Type.ERROR);

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
    LOG.log("selectAnotherPhoto", "select Another Photo button clicked", LOG.Type.SUCCESS);
    return new PhotoAddComponentObject(driver);
  }
}
