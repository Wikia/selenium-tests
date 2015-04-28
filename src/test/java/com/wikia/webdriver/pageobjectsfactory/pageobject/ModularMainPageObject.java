package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by RodriGomez on 02/04/15.
 */
public class ModularMainPageObject extends WikiBasePageObject{

  @FindBy(css = ".hero-image")
  private WebElement heroImageModule;
  @FindBy(css = ".hero-description-text")
  private WebElement heroPublishedDescription;
  @FindBy(css = ".title-edit-btn")
  private WebElement editDescriptionButton;
  @FindBy(css = ".upload-group input")
  private WebElement updateCoverImageInput;
  @FindBy(css = ".image-save-bar .new-btn.save-btn")
  private WebElement imagePublishButton;
  @FindBy(css = ".image-save-bar .new-btn.discard-btn")
  private WebElement imageDiscardButton;
  @FindBy(css = ".hero-description .new-btn.save-btn")
  private WebElement descriptionPublishButton;
  @FindBy(css = ".hero-description .new-btn.discard-btn")
  private WebElement descriptionDiscardButton;
  @FindBy(css = ".edited-text")
  private WebElement descriptionEditField;
  @FindBy(css = ".edit-box")
  private WebElement editBox;
  @FindBy (css = ".save-text")
  private WebElement coverImageInformativeText;
  @FindBy (css = ".position-text")
  private WebElement dragToRepositionText;
  @FindBy (css = ".update-btn")
  private WebElement updateCoverImageLink;

  public ModularMainPageObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void verifyMoMImage() {
    waitForElementByElement(heroImageModule);
    PageObjectLogging.log("verifyMoMImage", "Hero module image is visible", true);
  }

  public void clickUpdateCoverImageLink() {
    waitForElementByElement(updateCoverImageInput);
    updateCoverImageInput.click();
  }

  public void selectFileToUpload(String file) {
      updateCoverImageInput.sendKeys(getAbsolutePathForFile(
          ClassLoader.getSystemResource("ImagesForUploadTests/" + file).getPath()).toString());
    PageObjectLogging.log("typeInFileToUploadPath", "type file " + file + " to upload it", true);
  }

  public void verifyDragToRepositionText() {
    waitForElementByElement(dragToRepositionText);
    PageObjectLogging.log("verifyDragToRepositionText", "Drag to reposition text message is visible", true);
  }

  public void clickPublishButton() {
    waitForElementByElement(imagePublishButton);
    imagePublishButton.click();
  }

  public void clickEditDescriptionLink() {
    waitForElementByElement(editDescriptionButton);
    editDescriptionButton.click();
  }

  public void typeMoMDescription(String momDescription) {
    waitForElementByElement(descriptionEditField);
    descriptionEditField.clear();
    descriptionEditField.sendKeys(momDescription);
    PageObjectLogging.log("typeMoMDescription",
                          momDescription + "MoM description was typed in", true);
  }

  public void clickDescriptionPublishButton() {
    waitForElementByElement(descriptionPublishButton);
    descriptionPublishButton.click();
  }

  public void verifyEditedAndPublishedDescriptions(String editedDescription) {
    waitForElementByElement(heroPublishedDescription);
    Assertion.assertEquals(editedDescription, heroPublishedDescription.getText());
  }

  public void verifyAdminStaffButtons() {
    waitForElementByElement(updateCoverImageLink);
    waitForElementByElement(editDescriptionButton);
  }

  public void verifyNoAdminStaffButtons() {
    waitForElementNotVisibleByElement(updateCoverImageLink);
    waitForElementNotVisibleByElement(editDescriptionButton);
  }

  public String getMoMSrc() {
    waitForElementByElement(heroImageModule);
    return heroImageModule.getAttribute("src");
  }

  public void verifySrcTxtAreDifferent(String imgSrc, String newImgSrc) {
    waitForElementByElement(heroImageModule);
    Assertion.assertNotEquals(imgSrc, newImgSrc);
  }
}
