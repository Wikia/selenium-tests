package com.wikia.webdriver.pageobjectsfactory.componentobject.vet;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.AddMediaModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode.WikiArticleEditMode;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VetOptionsComponentObject extends AddMediaModalComponentObject {

  @FindBy(css = "#VideoEmbedLayoutRow")
  private WebElement videoEmbedLayotRow;
  @FindBy(css = "#VideoEmbedCaption")
  private WebElement captionField;
  @FindBy(css = "#VideoEmbedManualWidth")
  private WebElement widthInputField;
  @FindBy(css = "#VET_LayoutLeftBox label")
  private WebElement positionLayoutLeft;
  @FindBy(css = "#VET_LayoutCenterBox label")
  private WebElement positionLayoutCenter;
  @FindBy(css = "#VET_LayoutRightBox label")
  private WebElement positionLayoutRight;
  @FindBy(css = ".input-group.button-group input")
  private WebElement addAvideo;
  @FindBy(css = "#VideoEmbedCloseButton")
  private WebElement returnToEditing;
  @FindBy(css = "input.wikia-button.v-float-right")
  private WebElement updateVideoButton;
  @FindBy(css = "input#VideoEmbedName")
  private WebElement uneditableVideoNameField;
  @FindBy(css = "#VideoEmbedThumb .video-embed")
  private WebElement videoThumbnail;
  @FindBy(css = "div#VideoEmbedNameRow p")
  private WebElement videoNameCaption;

  private static final int VIDEO_THUMBNAIL_WIDTH = 350;

  public VetOptionsComponentObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public String getVideoName() {
    return videoNameCaption.getText();
  }

  public VetOptionsComponentObject setCaption(String caption) {
    wait.forElementVisible(captionField);
    captionField.clear();
    captionField.sendKeys(caption);
    PageObjectLogging.log("setCaption", "caption was set to: " + caption, true);

    return this;
  }

  public void adjustWith(int width) {
    String widthString = Integer.toString(width);
    wait.forElementVisible(widthInputField);
    widthInputField.clear();
    widthInputField.sendKeys(widthString);
    PageObjectLogging.log("adjustWith", "width set to: " + width, true, driver);
  }

  private void clickAddaVideo() {
    wait.forElementClickable(addAvideo);
    scrollAndClick(addAvideo);
    PageObjectLogging.log("clickAddaVideo", "add video button clicked", true, driver);
  }

  private void clickRetunToEditing() {
    wait.forElementVisible(returnToEditing);
    scrollAndClick(returnToEditing);
    PageObjectLogging.log("clickReturnToEditing", "return to editing button clicked", true, driver);
  }

  private void verifyVideoThumbnail() {
    wait.forElementVisible(videoThumbnail);
    Dimension dim = videoThumbnail.getSize();
    int w = dim.getWidth();
    Assertion.assertEquals(w, VIDEO_THUMBNAIL_WIDTH);
    PageObjectLogging.log("verifyVideoThumbnail", "video thumbnail is visible", true);
  }

  private void verifyVideoModalNotVisible() {
    waitForElementNotVisibleByElement(addVideoModal);
  }

  public WikiArticleEditMode submit() {
    verifyVideoThumbnail();
    clickAddaVideo();
    clickRetunToEditing();
    verifyVideoModalNotVisible();

    return new WikiArticleEditMode();
  }

  public void update() {
    clickAddaVideo();
    verifyVideoModalNotVisible();
  }

  public void adjustPosition(PositionsVideo position) {
    wait.forElementVisible(videoEmbedLayotRow);
    switch (position) {
      case LEFT:
        positionLayoutLeft.click();
        break;
      case CENTER:
        positionLayoutCenter.click();
        break;
      case RIGHT:
        positionLayoutRight.click();
        break;
      default:
        throw new NoSuchElementException("Non-existing position selected");
    }
    PageObjectLogging.log("adjustPosition", "position " + position.toString() + " selected", true);
  }

  public void verifyVideoAlignmentSelected(PositionsVideo positions) {
    wait.forElementVisible(videoEmbedLayotRow);
    String selectedPositionId = videoEmbedLayotRow
        .findElement(By.cssSelector(".selected"))
        .getAttribute("id");
    String desiredPositionId;
    switch (positions) {
      case LEFT:
        desiredPositionId = "VET_LayoutLeftBox";
        break;
      case CENTER:
        desiredPositionId = "VET_LayoutCenterBox";
        break;
      case RIGHT:
        desiredPositionId = "VET_LayoutRightBox";
        break;
      default:
        desiredPositionId = "desired position not provided";
    }
    Assertion.assertEquals(selectedPositionId, desiredPositionId);
  }

  public int getVideoWidth() {
    wait.forElementVisible(widthInputField);

    return Integer.parseInt(widthInputField.getAttribute("value"));
  }

  public void verifyCaption(String captionDesired) {
    String caption = captionField.getAttribute("value");
    Assertion.assertEquals(caption, captionDesired);
  }

  public void verifyNameNotEditable() {
    Assertion.assertTrue(!uneditableVideoNameField.isDisplayed());
    PageObjectLogging.log("verifyNameNotEditable", "video name field not editable", true);
  }
}
