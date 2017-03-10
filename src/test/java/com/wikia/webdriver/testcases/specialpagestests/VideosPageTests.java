package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.video.YoutubeVideo;
import com.wikia.webdriver.common.core.video.YoutubeVideoProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;

import org.testng.annotations.Test;

public class VideosPageTests extends NewTestTemplate {

  static final String VIDEO_QUERY = "truth";
  public static final String SUFFIX_FOR_LONG_TITLE = " ...";

  /**
   * Verify UI elements on the Special:Videos page Logged-Out
   */
  @Test(groups = {"VideosPage", "VideosPageTest_001", "Media"})
  public void VideosPageTest_001_UIComponentsPresence() {
    SpecialVideosPageObject specialVideos =
        new WikiBasePageObject().openSpecialVideoPageMostRecent(wikiURL);

    Assertion.assertTrue(specialVideos.isHeaderVisible(), "Header is not visible");
    Assertion.assertTrue(specialVideos.isAddVideoButtonClickable(),
                         "Add video button is not clickable");
    Assertion.assertTrue(specialVideos.isNewestVideoVisible(), "Newest video is not visible");
  }

  /**
   * Checks if a video can successfully be deleted from the Special:Videos page. Specifically, this
   * test checks if, after the video has been deleted, its title shows up in the delete confirmation
   * presented by Global Notifications. (Note: This test also adds a video beforehand to make sure
   * running this test is sustainable).
   */
  @Execute(asUser = User.STAFF)
  @Test(groups = {"VideosPage", "VideosPageTest_002", "Media"})
  @RelatedIssue(issueID = "SUS-755")
  public void VideosPageTest_002() {
    SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver);
    YoutubeVideo video = YoutubeVideoProvider.getLatestVideoForQuery(VIDEO_QUERY);

    specialVideos.addVideoViaAjax(video.getUrl());
    specialVideos.isNewVideoAdded();

    String addedVideoTitle = specialVideos.getNewestVideoTitle();
    String addedVideoTitlePattern =
        addedVideoTitle.endsWith(SUFFIX_FOR_LONG_TITLE)
        ? addedVideoTitle.replace(SUFFIX_FOR_LONG_TITLE, "")
        : addedVideoTitle;
    specialVideos.deleteNewestVideo();

    Assertion.assertTrue(specialVideos.getBannerNotifications().isNotificationMessageVisible(),
                         "Banner notification is not visible");
    Assertion
        .assertStringContains(specialVideos.getBannerNotificationText(), addedVideoTitlePattern);
  }

  /**
   * Checks if a video can successfully be deleted from the Special:Videos page. Specifically, this
   * test checks if, after the video has been deleted, it is no longer present in the list of most
   * recent videos on Special:Videos. (Note: in order to accomplish this the test also adds a video
   * before hand to ensure that 1.) the test is sustainable, and 2.) it knows what the most recent
   * video is).
   */
  @Execute(asUser = User.STAFF)
  @Test(groups = {"VideosPage", "VideosPageTest_003", "Media"})
  @RelatedIssue(issueID = "SUS-863")
  public void VideosPageTest_003() {
    SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver);
    YoutubeVideo video = YoutubeVideoProvider.getLatestVideoForQuery(VIDEO_QUERY);

    specialVideos.addVideoViaAjax(video.getUrl());

    specialVideos.isNewVideoAdded();

    String addedVideoTitle = specialVideos.getNewestVideoTitle();

    specialVideos.deleteNewestVideo();

    Assertion.assertTrue(specialVideos.getBannerNotifications().isNotificationMessageVisible(),
                         "Banner notification is not visible");
    Assertion.assertNotEquals(specialVideos.getNewestVideoTitle(), addedVideoTitle,
                              "Video is still visible as newest video");
  }
}
