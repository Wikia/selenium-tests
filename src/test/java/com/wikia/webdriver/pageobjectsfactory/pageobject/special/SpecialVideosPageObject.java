package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.lightbox.LightboxComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;

import org.openqa.selenium.TimeoutException;
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

  public VetAddVideoComponentObject clickAddAVideo() {
    wait.forElementClickable(addVideo);
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
    return newestVideoTitle.getText();
  }

  public void deleteNewestVideo() {
    newestVideoDeleteIcon.click();
    wait.forElementVisible(deleteConfirmButton);
    deleteConfirmButton.click();
  }

  public boolean isNewVideoAdded() {
    try {
      openSpecialVideoPageMostRecent(getWikiUrl());
      wait.forElementVisible(newestVideoTitle);
      jsActions.execute("$('.special-videos-grid .remove').first().show()");
      wait.forElementVisible(newestVideo);
      return true;
    } catch (TimeoutException e) {
      PageObjectLogging.logInfo("Title is not visible", e);
      return false;
    }
  }

  public boolean isHeaderVisible() {
    try {
      wait.forElementVisible(h1Header);
      return true;
    } catch (TimeoutException e) {
      PageObjectLogging.logInfo("Header is not visible", e);
      return false;
    }
  }

  public boolean isAddVideoButtonClickable() {
    try {
      wait.forElementClickable(addVideo);
      return true;
    } catch (TimeoutException e) {
      PageObjectLogging.logInfo("Add video button is not clickable", e);
      return false;
    }
  }

  public boolean isNewestVideoVisible() {
    try {
      wait.forElementVisible(newestVideo);
      return true;
    } catch (TimeoutException e) {
      PageObjectLogging.logInfo("Newest video is not visible", e);
      return false;
    }
  }

}
