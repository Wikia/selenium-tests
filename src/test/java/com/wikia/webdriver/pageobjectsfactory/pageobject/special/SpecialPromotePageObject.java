package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.core.imageutilities.ImageComparison;
import com.wikia.webdriver.common.core.imageutilities.ImageHelper;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class SpecialPromotePageObject extends BasePageObject {

  @FindBy(css = "#curMainImageName")
  protected WebElement thumbnailImage;
  @FindBy(css = ".description-wrapper textarea")
  protected WebElement wikiaDescription;
  @FindBy(css = ".headline-wrapper input")
  protected WebElement wikiaHeadline;
  @FindBy(css = ".wikia-button.upload-button")
  protected WebElement addPhotoButton;
  @FindBy(css = ".modify-remove.show")
  protected WebElement modifyThumbnailButton;
  @FindBy(css = ".button.normal.primary")
  protected WebElement submitButton;
  @FindBy(css = ".button.big")
  protected WebElement publishButton;
  @FindBy(css = "input[name='wpUploadFile']")
  protected WebElement uploadFileInput;


  public SpecialPromotePageObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void clickPublishButton() {
    wait.forElementVisible(publishButton);
    scrollAndClick(publishButton);
    LOG.success("clickPublishButton", "publish button click");
  }

  public String getUniqueThumbnailTextSpecialPromotePage() {
    return ImageHelper.getImageId(thumbnailImage);
  }

  public void modifyThumnailImage(String file) {
    wait.forElementVisible(thumbnailImage);
    scrollAndClick(thumbnailImage);
    wait.forElementVisible(modifyThumbnailButton);
    modifyThumbnailButton.click();
    wait.forElementVisible(uploadFileInput);
    uploadFileInput.sendKeys(CommonUtils
        .getAbsolutePathForFile(PageContent.IMAGE_UPLOAD_RESOURCES_PATH + file));
    LOG.result("modifyThumnailImage", "file " + file + " added to upload", true);
  }

  public void typeIntoHeadline(String text) {
    wait.forElementVisible(wikiaHeadline);
    wikiaHeadline.clear();
    wikiaHeadline.sendKeys(text);
    LOG.result("typeIntoHeadline", "text " + text + " typed into headline", true);
  }

  public void typeIntoDescription(String text) {
    wait.forElementVisible(wikiaDescription);
    wikiaDescription.clear();
    wikiaDescription.sendKeys(text);
    LOG.result("typeIntoDescription", "text " + text + " typed into description", true);
  }

  public void uploadThumbnailImage(String file) {
    wait.forElementVisible(addPhotoButton);
    scrollAndClick(addPhotoButton);
    wait.forElementVisible(uploadFileInput);
    uploadFileInput.sendKeys(CommonUtils
        .getAbsolutePathForFile(PageContent.IMAGE_UPLOAD_RESOURCES_PATH + file));
    LOG.result("uploadThumbnailImage", "file " + file + " added to upload", true);
    wait.forElementVisible(submitButton);
    submitButton.click();
  }

  public void verifyCrossWikiSearchDescription(String firstDescription) {
    wait.forElementVisible(wikiaDescription);
    Assertion.assertStringContains(wikiaDescription.getText(),
        firstDescription.substring(0, firstDescription.length() - 3));
  }

  public void verifyCrossWikiSearchImage(String firstImage) {
    wait.forElementVisible(thumbnailImage);
    String secondImage = getUniqueThumbnailTextSpecialPromotePage();
    Assertion.assertEquals(secondImage, firstImage);
  }

  public void verifyUploadedImage(String fileName) {
    File expectedImageFile = new File(PageContent.IMAGE_UPLOAD_RESOURCES_PATH + fileName);
    File actualImageFile = getUploadedImage();
    ImageComparison comparer = new ImageComparison();
    Boolean ifEqual = comparer.areFilesTheSame(expectedImageFile, actualImageFile);
    actualImageFile.delete();
    Assertion.assertTrue(ifEqual);
  }

  /**
   * This method creates a file in the process. recomended: after call, delete the physical file
   * using file.delete()
   *
   * @return file uploaded as main thumbnail on special:Promote
   */
  public File getUploadedImage() {
    wait.forElementVisible(thumbnailImage);
    File uploadedImageFile =
        new File(PageContent.IMAGE_UPLOAD_RESOURCES_PATH + "shouldBeDeleted.png");
    try {
      URL url = new URL(thumbnailImage.getAttribute("src"));
      BufferedImage bufImgOne = ImageIO.read(url);
      ImageIO.write(bufImgOne, "png", uploadedImageFile);
    } catch (IOException e) {
      LOG.error("IO Exception", e);
      throw new WebDriverException();
    }
    return uploadedImageFile;
  }

}
