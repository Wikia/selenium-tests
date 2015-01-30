package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.lightbox.LightboxComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Garth Webb
 * @author Saipetch Kongkatong
 */
public class SpecialVideosPageObject extends SpecialPageObject {

  @FindBy(css = ".WikiaPageHeader h1")
  private WebElement h1Header;
  @FindBy(css = "a.button.addVideo")
  private WebElement addVideo;
  @FindBy(css = ".special-videos-grid li:nth-child(1)")
  private WebElement newestVideo;
  @FindBy(css = ".special-videos-grid li:nth-child(1) .title")
  private WebElement newestVideoTitle;
  @FindBy(css = ".special-videos-grid li:nth-child(1) .remove")
  private WebElement newestVideoDeleteIcon;
  @FindBys(@FindBy(css = ".image.video > img"))
  private List<WebElement> videos;
  @FindBy(css = ".special-videos-grid a.video")
  private List<WebElement> videoItem;
  @FindBy(css = "#WikiaConfirmOk")
  private WebElement deleteConfirmButton;
  @FindBy(css = "#sorting-dropdown")
  private WebElement sortDropdown;

  private By newestVideoBy = By.cssSelector(".special-videos-grid li:nth-child(1) .title");

  public SpecialVideosPageObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public String getRandomVideo() {
    List<String> names = new ArrayList();
    for (WebElement elem : videos) {
      names.add(elem.getAttribute("data-video-key"));
    }
    Random r = new Random();
    int rnd = r.nextInt(names.size() - 1);
    return names.get((rnd) + 1);
  }

  public WatchPageObject unfollowVideo(String wikiURL, String videoName) {
    getUrl(
        wikiURL +
        URLsContent.WIKI_DIR +
        URLsContent.FILE_NAMESPACE +
        videoName +
        "?action=unwatch"
    );
    return new WatchPageObject(driver);
  }

  protected void verifyH1() {
    waitForElementByElement(h1Header);
  }

  protected void verifyNewestVideo() {
    waitForElementByElement(newestVideo);
  }

  protected void verifyAddVideoButton() {
    waitForElementByElement(addVideo);
    waitForElementClickableByElement(addVideo);
  }

  protected void verifySortDropdown() {
    waitForElementByElement(sortDropdown);
  }

  public VetAddVideoComponentObject clickAddAVideo() {
    verifyAddVideoButton();
    scrollAndClick(addVideo);
    PageObjectLogging.log("clickAddAVideo", "click on 'add a video' button", true, driver);
    return new VetAddVideoComponentObject(driver);
  }

  public void verifyVideoAdded(String videoTitle) {
    waitForTextToBePresentInElementLocatedBy(newestVideoBy, videoTitle);
    PageObjectLogging.log("verifyVideoAdded",
                          "verify that video with following description was added: " + videoTitle,
                          true);
  }

  public LightboxComponentObject openLightboxForGridVideo(int itemNumber) {
    scrollAndClick(videoItem.get(itemNumber));
    return new LightboxComponentObject(driver);
  }

  public String getFileUrl(String wikiURL, int itemNumber) {
    String
        fileUrl =
        wikiURL + URLsContent.WIKI_DIR + URLsContent.FILE_NAMESPACE + getVideoKey(itemNumber);
    PageObjectLogging.log("getFileUrl", "File url: " + fileUrl, true);
    return fileUrl;
  }

  public String getVideoKey(int itemNumber) {
    String videoKey = videos.get(itemNumber).getAttribute("data-video-key");
    PageObjectLogging.log("getVideoKey", "Video key: " + videoKey, true);
    return videoKey;
  }

  public String getNewestVideoTitle() {
    waitForElementByElement(newestVideo);
    return newestVideoTitle.getText();
  }

  public void deleteVideo() {
    openSpecialVideoPageMostRecent(getWikiUrl());
    executeScript("$('.special-videos-grid .remove').first().show()");
    waitForElementByElement(newestVideo);
    newestVideoDeleteIcon.click();
    waitForElementByElement(deleteConfirmButton);
    deleteConfirmButton.click();
  }

  public void verifyDeleteViaGlobalNotifications() {
    addVideoViaAjax(VideoContent.YOUTUBE_VIDEO_URL2);
    deleteVideo();
    String
        deletedVideo =
        "\"File:" + VideoContent.YOUTUBE_VIDEO_URL2_NAME + "\" has been deleted. (undelete)";
    Assertion.assertEquals(deletedVideo, getFlashMessageText());
    PageObjectLogging.log("verifyDeleteVideoGlobalNotifications",
                          "verify video " + deletedVideo + " was deleted", true);
  }

  public void verifyDeleteViaVideoNotPresent() {
    addVideoViaAjax(VideoContent.YOUTUBE_VIDEO_URL2);
    deleteVideo();
    verifyNotificationMessage();
    Assertion.assertNotEquals(VideoContent.YOUTUBE_VIDEO_URL2_NAME, getNewestVideoTitle());
    PageObjectLogging.log("verifyDeleteVideoNotPresent",
                          "verify video " + VideoContent.YOUTUBE_VIDEO_URL2_NAME + " was deleted",
                          true);
  }

  public void verifyElementsOnPage() {
    verifyH1();
    PageObjectLogging.log("verifyElementsOnPage", "verify that H1 is present", true);
    verifyAddVideoButton();
    PageObjectLogging.log("verifyElementsOnPage", "verify that Add Video button is present", true);
    verifySortDropdown();
    PageObjectLogging.log("verifyElementsOnPage", "verify that sort dropdown is present", true);
    verifyNewestVideo();
    PageObjectLogging.log("verifyElementsOnPage",
                          "verify that there is at least one video present", true);
  }
}
