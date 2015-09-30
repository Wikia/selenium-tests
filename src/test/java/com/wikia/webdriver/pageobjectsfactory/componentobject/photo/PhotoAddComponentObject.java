package com.wikia.webdriver.pageobjectsfactory.componentobject.photo;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.core.interactions.Elements;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PhotoAddComponentObject extends BasePageObject {

  @FindBy(css = "#ImageQuery")
  private WebElement searchField;
  @FindBy(css = "#ImageUploadFind [value='Find']")
  private WebElement findButton;
  @FindBy(css = "#WMU_source_1")
  private WebElement flickButton;
  @FindBy(css = "#WMU_source_0")
  private WebElement thisWikiButton;
  @FindBy(css = "#ImageUploadFile")
  private WebElement chooseFileInput;
  @FindBy(css = "#ImageUploadForm input:nth-child(2)")
  private WebElement uploadButton;
  @FindBy(css = "tr.ImageUploadFindImages td a")
  private List<WebElement> addThisPhotoList;

  private String photoName;

  public PhotoAddComponentObject(WebDriver driver) {
    super(driver);
  }

  public void verifyAddPhotoModal() {
    wait.forElementVisible(searchField);
    wait.forElementVisible(findButton);
  }

  public void typeSearchQuery(String photoName) {
    wait.forElementVisible(searchField);
    searchField.sendKeys(photoName);
    LOG.success("typeSearchQuery", photoName + " searching");
  }

  public void clickFind() {
    wait.forElementVisible(findButton);
    scrollAndClick(findButton);
    wait.forElementNotVisible(By.cssSelector("#ImageUploadProgress2"));
    LOG.success("clickSearch", "search button clicked");
  }

  public PhotoOptionsComponentObject clickAddThisPhoto(int photoNumber) {
    WebElement photo = wait.forElementVisible(addThisPhotoList.get(photoNumber));
    photoName =
        addThisPhotoList.get(photoNumber).findElement(By.cssSelector("img"))
            .getAttribute("data-image-name");
    scrollAndClick(photo);
    LOG.success("clickAddPhoto", "add photo button clicked");
    return new PhotoOptionsComponentObject(driver);
  }

  public PhotoOptionsComponentObject clickAddThisPhoto(String fileName) {
    WebElement photo = Elements.getElementByValue(addThisPhotoList, "title", fileName);
    photoName = photo.findElement(By.cssSelector("img")).getAttribute("data-image-name");
    scrollAndClick(photo);
    LOG.success("clickAddPhoto", "add photo button clicked");
    return new PhotoOptionsComponentObject(driver);
  }

  public String getPhotoName() {
    return photoName.replace(' ', '_');
  }

  /**
   * Adding photo with given @photoName and @photoNumber
   */
  public PhotoOptionsComponentObject addPhotoFromWiki(String photoName, int photoNumber) {
    typeSearchQuery(photoName);
    clickFind();
    clickAddThisPhoto(photoNumber);
    return new PhotoOptionsComponentObject(driver);
  }

  public PhotoOptionsComponentObject addPhotoFromWiki(String photoName) {
    typeSearchQuery(photoName);
    clickFind();
    clickAddThisPhoto(photoName);
    return new PhotoOptionsComponentObject(driver);
  }

  public void clickThisWiki() {
    thisWikiButton.click();
    LOG.success("clickThisWiki", "this wiki button clicked");
  }

  public void clickFlickr() {
    flickButton.click();
    LOG.success("clickFlickr", "flickr button clicked");
  }

  public void chooseFileToUpload(String file) {
    chooseFileInput
        .sendKeys(
            CommonUtils.getAbsolutePathForFile(PageContent.IMAGE_UPLOAD_RESOURCES_PATH + file));
    LOG.success("selectFileToUpload", "select file " + file + " to upload it");
  }

  public void clickUpload() {
    uploadButton.click();
    LOG.success("clickUpload", "click on upload button");
  }
}
