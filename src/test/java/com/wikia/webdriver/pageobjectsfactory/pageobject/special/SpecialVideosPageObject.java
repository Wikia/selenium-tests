package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.video.YoutubeVideo;
import com.wikia.webdriver.common.core.video.YoutubeVideoProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.lightbox.LightboxComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

  private static final String NEWEST_VIDEO_CSS = ".special-videos-grid li:nth-child(1) .title a";

  public SpecialVideosPageObject(WebDriver driver) {
    super();
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
    getUrl(wikiURL + URLsContent.WIKI_DIR + URLsContent.FILE_NAMESPACE + videoName
        + "?action=unwatch");
    return new WatchPageObject(driver);
  }

  private void verifyH1() {
    wait.forElementVisible(h1Header);
  }

  private void verifyNewestVideo() {
    wait.forElementVisible(newestVideo);
  }

  private void verifyAddVideoButton() {
    wait.forElementClickable(addVideo);
  }

  private void verifySortDropdown() {
    wait.forElementVisible(sortDropdown);
  }

  public VetAddVideoComponentObject clickAddAVideo() {
    verifyAddVideoButton();
    scrollAndClick(addVideo);
    return new VetAddVideoComponentObject(driver);
  }

  public void verifyVideoAdded(String videoTitle) {
    waitForValueToBePresentInElementsAttributeByCss(NEWEST_VIDEO_CSS, "title", escapeHtml(videoTitle));
    PageObjectLogging.log("verifyVideoAdded",
        "verify that video with following description was added: " + videoTitle, true);
  }

  public LightboxComponentObject openLightboxForGridVideo(int itemNumber) {
    scrollAndClick(videoItem, itemNumber);
    return new LightboxComponentObject(driver);
  }

  public String getNewestVideoTitle() {
    wait.forElementVisible(newestVideo);
    return newestVideoTitle.getText();
  }

  public void deleteVideo() {
    openSpecialVideoPageMostRecent(getWikiUrl());
    jsActions.execute("$('.special-videos-grid .remove').first().show()");
    wait.forElementVisible(newestVideo);
    newestVideoDeleteIcon.click();
    wait.forElementVisible(deleteConfirmButton);
    deleteConfirmButton.click();
  }

  public void verifyDeleteViaGlobalNotifications() {
    YoutubeVideo video = YoutubeVideoProvider.getLatestVideoForQuery("truth");

    addVideoViaAjax(video.getUrl());
    deleteVideo();
    String deletedVideo = "\"File:" + video.getTitle() + "\" has been deleted. (undelete)";
    Assertion.assertEquals(getBannerNotificationText(), deletedVideo);
    PageObjectLogging.log("verifyDeleteVideoGlobalNotifications", "verify video " + deletedVideo
        + " was deleted", true);
  }

  public void verifyDeleteViaVideoNotPresent() {
    YoutubeVideo video = YoutubeVideoProvider.getLatestVideoForQuery("truth");

    addVideoViaAjax(video.getUrl());
    deleteVideo();
    getBannerNotifications().verifyNotificationMessage();
    Assertion.assertNotEquals(getNewestVideoTitle(), video.getTitle());
    PageObjectLogging.log("verifyDeleteVideoNotPresent", "verify video " + video.getTitle()
        + " was deleted", true);
  }

  public void verifyElementsOnPage() {
    verifyH1();
    PageObjectLogging.log("verifyElementsOnPage", "verify that H1 is present", true);
    verifyAddVideoButton();
    PageObjectLogging.log("verifyElementsOnPage", "verify that Add Video button is present", true);
    verifyNewestVideo();
    PageObjectLogging.log("verifyElementsOnPage",
        "verify that there is at least one video present", true);
  }
}
