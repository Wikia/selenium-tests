package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.video.YoutubeVideo;
import com.wikia.webdriver.common.core.video.YoutubeVideoProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.diffpage.DiffPagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.historypage.HistoryPagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePage;
import org.testng.annotations.Test;

public class FilePageTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  /**
   * Verify functionality of tabs on file pages in Oasis. When a tab is clicked, the corresponding
   * content should be displayed.
   */
  @Test(groups = {"FilePage", "filePage001_tabs", "Media"})
  public void filePage001_tabs() {
    FilePage filePage = new FilePage();
    filePage.open(URLsContent.FILENAME_001);

    filePage.verifySelectedTab("about");

    filePage.clickTab(FilePage.HISTORY_TAB);
    filePage.verifySelectedTab("history");

    filePage.clickTab(FilePage.ABOUT_TAB);
    filePage.verifySelectedTab("about");

    filePage.clickTab(FilePage.METADATA_TAB);
    filePage.verifySelectedTab("metadata");
  }

  /**
   * Verify that file page tabs will save their state for logged in users when they navigate away
   * from the page and back to it.
   */
  @Test(groups = {"FilePage", "filePage002_tabsLoggedIn", "Media"})
  @Execute(asUser = User.USER)
  public void filePage002_tabsLoggedIn() {
    FilePage filePage = new FilePage().open(URLsContent.FILENAME_001);

    filePage.refreshAndVerifyTabs(0);
    filePage.refreshAndVerifyTabs(1);
    filePage.refreshAndVerifyTabs(2);
  }

  /**
   * Verify if a diff table is present on a diff page. Note that not all diff pages have diff tables
   * but the one specified does.
   */
  @Test(groups = {"FilePage", "filePage003_diffPage", "Media"})
  public void filePage003_diffPage() {

    WikiBasePageObject base = new WikiBasePageObject();
    HistoryPagePageObject historyPage = base.openFileHistoryPage(URLsContent.FILENAME_001, wikiURL);

    DiffPagePageObject diffPage = historyPage.goToDiffPageFromHistoryPage();
    diffPage.verifyDiffTablePresent();
  }

  /**
   * Verify that a video can be deleted from the File page
   */
  @RelatedIssue(issueID = "MAIN-4294")
  @Test(groups = {"FilePage", "filePage004_delete", "Media"})
  public void filePage004_delete() {
    // Go to Special:Videos to add a video
    YoutubeVideo video = YoutubeVideoProvider.getLatestVideoForQuery("data");

    SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver);
    specialVideos.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    specialVideos.openSpecialVideoPage(wikiURL);

    // Add a Youtube video we'll delete
    VetAddVideoComponentObject vetAddingVideo = specialVideos.clickAddAVideo();
    vetAddingVideo.addVideoByUrl(video.getUrl());

    // Verify the video is actually there
    specialVideos.verifyVideoAdded(video.getTitle());

    // Now delete the video
    FilePage filePage = new FilePage().open(video.getFileName());
    DeletePageObject deletePage = filePage.deletePage();
    deletePage.submitDeletion();

    // Go back to the file page and make sure its gone
    filePage = filePage.open(video.getFileName());
    filePage.verifyEmptyFilePage();
  }

  /**
   * Verify that a video can be deleted from the File page
   */
  @Test(groups = {"FilePage", "filePage005_deleteFromHistory", "Media"})
  @RelatedIssue(issueID = "SUS-317", comment = "Product code defect. Test manually that the video can be deleted")
  public void filePage005_deleteFromHistory() {

    YoutubeVideo video = YoutubeVideoProvider.getLatestVideoForQuery("pokemon");

    // Go to Special:Videos to add a video
    SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver);
    specialVideos.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    specialVideos.openSpecialVideoPage(wikiURL);

    // Add a Youtube video we'll delete
    VetAddVideoComponentObject vetAddingVideo = specialVideos.clickAddAVideo();
    vetAddingVideo.addVideoByUrl(video.getUrl());

    // Verify the video is actually there
    specialVideos.verifyVideoAdded(video.getTitle());

    // Go to the history tab and add a second video to test deleting a version
    FilePage filePage = new FilePage().open(video.getFileName());
    filePage.selectHistoryTab();

    filePage.replaceVideo(VideoContent.YOUTUBE_VIDEO_URL5);

    // Load the file page again, should have the same name
    filePage.open(video.getFileName()).verifyEmbeddedVideoIsPresent();

    //Removed following lines until SUS-317 is fixed
    //// Go to the history tab and verify there are at least two videos
   //filePage.selectHistoryTab();
    //filePage.verifyVersionCountAtLeast(2);

    //// Delete the second version
    //DeletePageObject deletePage = filePage.deleteVersion(2);
    //deletePage.submitDeletion();
    //Removed above lines until SUS-317is fixed

    // Load the file page again, should have the same name
    filePage.open(video.getFileName()).verifyEmbeddedVideoIsPresent();

    // Delete the first version and thus the whole page
    DeletePageObject deletePage = filePage.deleteVersion(1);
    deletePage.submitDeletion();

    // Go back to the file page and make sure its gone
    filePage.open(video.getFileName()).verifyEmptyFilePage();
  }
}
