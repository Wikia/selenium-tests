package com.wikia.webdriver.testcases.mediatests.videohomepage;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.videohomepage.VideoHomePageObject;

import org.testng.annotations.Test;

/**
 * Created by Liz Lee on 6/4/14.
 * @ownership Content X-Wing
 */
public class VideoHomePageTests extends NewTestTemplate {

  @Test(groups = {"VideoHomePage_001", "Media", "VideoHomePageTests"})
  @Execute(onWikia = URLsContent.VIDEO_TEST_WIKI)
  public void VideoHomePage_001_FeaturedVideoSlider() {
    VideoHomePageObject videoHomePageObject = new VideoHomePageObject(driver).open();
    videoHomePageObject.verifyFeaturedSliderInitialized();
    videoHomePageObject.verifyFeaturedSliderSlides(5);
  }

  @Test(groups = {"VideoHomePage_002", "Media", "VideoHomePageTests"})
  @Execute(onWikia = URLsContent.VIDEO_TEST_WIKI)
  public void VideoHomePage_002_LatestVideos() {
    VideoHomePageObject videoHomePageObject = new VideoHomePageObject(driver).open();
    videoHomePageObject.verifyLatestVideosRows(3);
  }
}
