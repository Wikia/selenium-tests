package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;
import org.testng.annotations.Test;

public class VideosPageTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  /**
   * Verify UI elements on the Special:Videos page Logged-Out
   */
  @Test(groups = {"VideosPage", "VideosPageTest_001", "Media"})
  public void VideosPageTest_001_UIComponentsPresence() {
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialVideosPageObject specialVideos = base.openSpecialVideoPageMostRecent(wikiURL);

    Assertion.assertTrue(specialVideos.isHeaderVisible());
    Assertion.assertTrue(specialVideos.isAddVideoButtonClickable());
    Assertion.assertTrue(specialVideos.isNewestVideoVisible());
  }

  /**
   * Checks if a video can successfully be deleted from the Special:Videos page. Specifically, this
   * test checks if, after the video has been deleted, its title shows up in the delete confirmation
   * presented by Global Notifications. (Note: This test also adds a video beforehand to make sure
   * running this test is sustainable).
   */
  @Test(groups = {"VideosPage", "VideosPageTest_002", "Media"})
  @RelatedIssue(issueID = "SUS-755")
  public void VideosPageTest_002() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver);
    specialVideos.verifyDeleteViaGlobalNotifications();
  }

  /**
   * Checks if a video can successfully be deleted from the Special:Videos page. Specifically, this
   * test checks if, after the video has been deleted, it is no longer present in the list of most
   * recent videos on Special:Videos. (Note: in order to accomplish this the test also adds a video
   * before hand to ensure that 1.) the test is sustainable, and 2.) it knows what the most recent
   * video is).
   */
  @Test(groups = {"VideosPage", "VideosPageTest_003", "Media"})
  @RelatedIssue(issueID = "SUS-863")
  public void VideosPageTest_003() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver);
    specialVideos.verifyDeleteViaVideoNotPresent();
  }
}
