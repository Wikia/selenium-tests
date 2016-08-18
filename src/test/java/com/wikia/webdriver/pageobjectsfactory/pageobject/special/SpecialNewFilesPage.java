package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.lightbox.LightboxComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;

public class SpecialNewFilesPage extends SpecialPageObject {

  private static final String NEW_FILES_SPECIAL_PAGE_TITLE = "Images";

  @FindBy(css = "a.upphotos[title*='Add a photo']")
  private WebElement addPhotoButton;
  @FindBy(css = "input[name='wpUploadFile']")
  private WebElement browseForFileInput;
  @FindBy(css = "div.step-1 input[value*='Upload']")
  private WebElement uploadFileInput;
  @FindBy(css = "div.advanced a")
  private WebElement moreOrFewerOptions;
  @FindBy(css = "div.toggles input[name='wpIgnoreWarning']")
  private WebElement ignoreAnyWarnings;
  @FindBy(css = "div.wikia-gallery div.wikia-gallery-item:first-child img")
  private WebElement latestWikiaPreviewImg;
  @FindBys(@FindBy(css = "#mw-content-text img"))
  private List<WebElement> imagesNewFiles;
  @FindBy(css = ".wikia-gallery div.wikia-gallery-item a.image")
  private List<WebElement> galleryImageBox;
  @FindBy(css = "input[name='wpDestFile']")
  private WebElement fileNameInput;

  public SpecialNewFilesPage addPhoto() {
    scrollAndClick(addPhotoButton);
    PageObjectLogging.log("ClickAddPhotoButton", "Add photo button clicked", true);

    return this;
  }

  public SpecialNewFilesPage clickUploadButton() {
    scrollAndClick(uploadFileInput);
    PageObjectLogging.log("ClickOnUploadaPhoto", "Click on upload a photo button", true);

    return this;
  }

  public SpecialNewFilesPage setFileName(String fileName) {
    fileNameInput.clear();
    fileNameInput.sendKeys(fileName);

    return this;
  }

  public SpecialNewFilesPage clickOnMoreOptions() {
    moreOrFewerOptions.click();
    waitForValueToBePresentInElementsCssByCss("div.options", "display", "block");
    PageObjectLogging.log("ClickOnMoreOptions", "Click on More options", true);

    return this;
  }

  public SpecialNewFilesPage clickOnFewerOptions() {
    moreOrFewerOptions.click();
    waitForValueToBePresentInElementsCssByCss("div.options", "display", "none");
    PageObjectLogging.log("ClickOnFewerOptions", "Click on Fewer options", true);

    return this;
  }

  public SpecialNewFilesPage checkIgnoreAnyWarnings() {
    wait.forElementVisible(ignoreAnyWarnings);
    ignoreAnyWarnings.click();
    PageObjectLogging.log("CheckIgnoreAnyWarnings", "Check 'Ignore Any Warnings' option", true);

    return this;
  }

  public SpecialNewFilesPage selectFileToUpload(String file) {
    browseForFileInput.sendKeys(
        CommonUtils.getAbsolutePathForFile(PageContent.IMAGE_UPLOAD_RESOURCES_PATH + file));

    waitForValueToBePresentInElementsCssByCss("div.status", "display", "block");

    PageObjectLogging.log("typeInFileToUploadPath", "type file " + file + " to upload it", true);

    return this;
  }

  public void verifyFileUploaded(String fileName) {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      PageObjectLogging.log("SLEEP INTERRUPTED", e, false);
    }
    driver.navigate().refresh();
    waitForValueToBePresentInElementsAttributeByElement(latestWikiaPreviewImg, "src", fileName);
    PageObjectLogging.log("waitForFile",
        "Verify if " + fileName + " has been successfully uploaded", true);
  }

  /**
   * @return name of random image on Special:NewFiles page
   */
  public String getRandomImageName() {
    List<String> hrefs = new ArrayList<>();
    for (WebElement elem : imagesNewFiles) {
      hrefs.add(elem.getAttribute("data-image-name"));
    }
    Random r = new Random();
    return hrefs.get((r.nextInt(hrefs.size() - 1)) + 1);
  }

  public String getImageUrl(String imageName) {
    for (WebElement elem : imagesNewFiles) {
      String href = elem.findElement(parentBy).getAttribute("href");
      if (href.contains(imageName)) {
        return href;
      }
    }
    throw new NoSuchElementException("there is no " + imageName + " on Special:NewFiles page");
  }

  /**
   * @param imageName eg. test.png. This file should be visible on Special:NewFiles
   * @param noRedirect if true, ?redirect=no is added to current url
   * @return new file page object of file specified in imageName parameter
   */
  public FilePagePageObject openImage(String imageName, boolean noRedirect) {
    String url = getImageUrl(imageName);
    if (noRedirect) {
      String parameter = "redirect=no";
      url = urlBuilder.appendQueryStringToURL(url, parameter);
    }
    driver.get(url);
    return new FilePagePageObject();
  }

  public WatchPageObject unfollowImage(String wikiURL, String imageName) {
    String url = urlBuilder.appendQueryStringToURL(
        wikiURL + URLsContent.WIKI_DIR + URLsContent.FILE_NAMESPACE + imageName,
        URLsContent.ACTION_UNFOLLOW);
    getUrl(url);
    return new WatchPageObject(driver);
  }

  public LightboxComponentObject openLightbox(int itemNumber) {
    scrollAndClick(galleryImageBox.get(itemNumber));
    return new LightboxComponentObject(driver);
  }

  /**
   * Hide warnings, cause they may interrupt with test stability - test clicks warning instead of
   * "More Options" link
   */
  public SpecialNewFilesPage hideWarnings() {
    jsActions.execute("$(\"div.status\").attr(\"style\", \"display=none\")");

    return this;
  }

  public String getTitle(){
    return NEW_FILES_SPECIAL_PAGE_TITLE;
  }
}
