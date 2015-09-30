package com.wikia.webdriver.pageobjectsfactory.componentobject.videosmodule;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author James Sutterfield
 */
public class VideosModuleComponentObject extends WikiBasePageObject {

  @FindBy(css = "#videosModule")
  private WebElement videosModuleContainer;
  @FindBy(css = "#videosModule img")
  private List<WebElement> videos;
  private static final int VIDEO_COUNT_MIN = 3;
  private static final int VIDEO_COUNT_MAX = 5;

  public VideosModuleComponentObject(WebDriver driver) {
    super(driver);
  }

  public void verifyVideosModuleShowing() {
    Assertion.assertTrue(isElementOnPage(videosModuleContainer));
    LOG.success("verifyVideosModuleShowing", "Videos Module showing");
  }

  public void verifyVideosModuleNotShowing() {
    Assertion.assertTrue(!isElementOnPage(videosModuleContainer));
    LOG.success("verifyVideosModuleNotShowing",
            "Videos Module not showing (test passed)");
  }

  public void verifyDisplayCount() {
    Assertion.assertTrue(videos.size() >= VIDEO_COUNT_MIN && videos.size() <= VIDEO_COUNT_MAX);
    LOG.success("verifyDisplayCount",
            "Videos Module showing correct number of videos");
  }

  public void verifyNoDuplicates() {
    String videoTitle1, videoTitle2;
    for (int i = 0; i < videos.size() - 1; i++) {
      for (int j = i + 1; j < videos.size(); j++) {
        videoTitle1 = videos.get(i).getAttribute("data-video-key");
        videoTitle2 = videos.get(j).getAttribute("data-video-key");
        Assertion.assertNotEquals(videoTitle2, videoTitle1);
      }
    }
    LOG.success("verifyNoDuplicates", "Videos Module not showing duplicates");
  }
}
