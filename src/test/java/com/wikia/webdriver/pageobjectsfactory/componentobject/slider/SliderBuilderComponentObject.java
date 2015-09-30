package com.wikia.webdriver.pageobjectsfactory.componentobject.slider;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.componentobject.addphoto.AddPhotoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SliderBuilderComponentObject extends BasePageObject {

  @FindBy(css = "#WikiaPhotoGallerySliderType_bottom")
  private WebElement hPosition;
  @FindBy(css = "#WikiaPhotoGallerySliderType_right")
  private WebElement vPosition;
  @FindBy(css = "#WikiaPhotoGallerySliderAddImage")
  private WebElement addPhotoButton;
  @FindBy(css = "#WikiaPhotoGalleryEditorSave")
  private WebElement finishButton;

  public SliderBuilderComponentObject(WebDriver driver) {
    super(driver);
    // TODO Auto-generated constructor stub
  }

  public enum MenuPositions {
    HORIZONTAL, VERTICAL
  }

  public void selectMenuPosition(MenuPositions pos) {
    wait.forElementVisible(hPosition);
    wait.forElementVisible(vPosition);
    switch (pos) {
      case HORIZONTAL:
        hPosition.click();
        break;
      case VERTICAL:
        vPosition.click();
        break;
      default:
        throw new NoSuchElementException("Non-existing position selected");
    }
    LOG
        .log("selectMenuPosition", pos.toString() + " position selected", true, driver);
  }

  public AddPhotoComponentObject clickAddPhoto() {
    wait.forElementVisible(addPhotoButton);
    addPhotoButton.click();
    LOG.log("addPhoto", "add photo button clicked", LOG.Type.SUCCESS);
    return new AddPhotoComponentObject(driver);
  }

  public void clickFinish() {
    wait.forElementVisible(finishButton);
    finishButton.click();
    LOG.log("clickFinish", "finish button clicked", LOG.Type.SUCCESS);
  }

}
