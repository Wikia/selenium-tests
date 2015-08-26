package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by RodriGomez on 02/04/15.
 */
public class ModularMainPageObject extends WikiBasePageObject {

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
  @FindBy(css = ".hero-description .new-btn.save-btn")
  private WebElement descriptionPublishButton;
  @FindBy(css = ".hero-description .new-btn.discard-btn")
  private WebElement descriptionDiscardButton;
  @FindBy(css = ".edited-text")
  private WebElement descriptionEditField;
  @FindBy(css = ".edit-box")
  private WebElement editBox;
  @FindBy(css = ".position-text")
  private WebElement dragToRepositionText;
  @FindBy(css = ".update-btn")
  private WebElement updateCoverImageLink;
  @FindBy(css = "button.sg-sub[disabled=disabled]")
  private WebElement publishButtonDisabled;
  @FindBy(css = ".image-window")
  private WebElement imageWindowDragging;


  public ModularMainPageObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void verifyMoMImage() {
    wait.forElementVisible(heroImageModule);
    PageObjectLogging.log("verifyMoMImage", "Hero module image is visible", true);
  }

  public void clickUpdateCoverImageLink() {
    wait.forElementVisible(updateCoverImageInput);
    updateCoverImageInput.click();
  }

  public void selectFileToUpload(String file) {
    updateCoverImageInput.sendKeys(getAbsolutePathForFile(ClassLoader.getSystemResource(
        "ImagesForUploadTests/" + file).getPath()));
    PageObjectLogging.log("typeInFileToUploadPath", "type file " + file + " to upload it", true);
  }

  public void verifyDragToRepositionText() {
    wait.forElementVisible(dragToRepositionText);
    PageObjectLogging.log("verifyDragToRepositionText",
        "Drag to reposition text message is visible", true);
  }

  public void clickPublishButton() {
    wait.forElementVisible(imagePublishButton);
    imagePublishButton.click();
  }

  public void clickEditDescriptionLink() {
    wait.forElementVisible(editDescriptionButton);
    editDescriptionButton.click();
  }

  public void typeMoMDescription(String momDescription) {
    wait.forElementVisible(descriptionEditField);
    descriptionEditField.clear();
    descriptionEditField.sendKeys(momDescription);
    PageObjectLogging.log("typeMoMDescription", momDescription + "MoM description was typed in",
        true);
  }

  public void clickDescriptionPublishButton() {
    wait.forElementVisible(descriptionPublishButton);
    descriptionPublishButton.click();
  }

  public void verifyEditedAndPublishedDescriptions(String editedDescription) {
    wait.forElementVisible(heroPublishedDescription);
    Assertion.assertEquals(heroPublishedDescription.getText(), editedDescription);
  }

  public void verifyAdminStaffButtons() {
    wait.forElementVisible(updateCoverImageLink);
    wait.forElementVisible(editDescriptionButton);
  }

  public void verifyNoAdminStaffButtons() {
    waitForElementNotVisibleByElement(updateCoverImageLink);
    waitForElementNotVisibleByElement(editDescriptionButton);
  }

  public String getMoMSrc() {
    wait.forElementVisible(heroImageModule);
    return heroImageModule.getAttribute("src");
  }

  public void verifySrcTxtAreDifferent(String imgSrc, String newImgSrc) {
    wait.forElementVisible(heroImageModule);
    Assertion.assertNotEquals(newImgSrc, imgSrc);
  }

  public void deleteDescriptionEditorContent() {
    descriptionEditField.clear();
    descriptionEditField.sendKeys("a");
    descriptionEditField.sendKeys(Keys.BACK_SPACE);
  }

  public void verifyWikiaPromotionalMessage() {
    String promoteMessage = descriptionEditField.getAttribute("placeholder");
    Assertion.assertEquals(promoteMessage, PageContent.WIKIA_HERO_PROMOTE_MESSAGE);
  }

  public void verifyPublishButtonDisability() {
    wait.forElementVisible(editBox);
    wait.forElementVisible(publishButtonDisabled);
  }

  public String getDescriptionText() {
    wait.forElementVisible(heroPublishedDescription);
    return heroPublishedDescription.getText();
  }

  public void addRandomTextToDescriptionField(String randomText) {
    wait.forElementVisible(editBox);
    descriptionEditField.click();
    descriptionEditField.sendKeys(randomText);
  }

  public void verifyPublishedTextAndEditor(String publishedText) {
    wait.forElementVisible(heroPublishedDescription);
    Assertion.assertEquals(heroPublishedDescription.getText(), publishedText);
  }

  public void clickDiscardButton() {
    wait.forElementVisible(descriptionDiscardButton);
    waitForElementClickableByElement(descriptionDiscardButton);
    descriptionDiscardButton.click();
  }

  public void moveCoverImage() {
    Actions actions = new Actions(driver);
    actions.clickAndHold(imageWindowDragging).clickAndHold().moveByOffset(-200, -200).release()
        .perform();
  }

  public String getTopAttribute() {
    if (StringUtils.isNotBlank(heroImageModule.getAttribute("style"))) {
      return heroImageModule.getAttribute("style");
    } else {
      return "";
    }
  }

  public void compareTopValues(String firstTopValue, String secondTopValue) {
    Assertion.assertNotEquals(secondTopValue, firstTopValue);
  }
}
