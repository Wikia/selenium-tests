/**
 *
 */
package com.wikia.webdriver.testcases.mediatests.addvideo;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.video.YoutubeVideo;
import com.wikia.webdriver.common.core.video.YoutubeVideoProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class VetSpecialVideosTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @RelatedIssue(issueID = "MAIN-4294")
  @Test(groups = {"VetTests001", "VetTests", "SpecialVideo", "Media"}, enabled = false)
  public void SpecialVideos_001_Provider() {
    YoutubeVideo video = YoutubeVideoProvider.getLatestVideoForQuery("review");

    SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver);
    specialVideos.logInCookie(credentials.userName, credentials.password, wikiURL);
    specialVideos.openSpecialVideoPage(wikiURL);
    VetAddVideoComponentObject vetAddingVideo = specialVideos.clickAddAVideo();
    vetAddingVideo.addVideoByUrl(video.getUrl());
    specialVideos.verifyVideoAdded(video.getTitle());

    FilePagePageObject filePage = specialVideos.openFilePage(wikiURL, video.getWikiFileName());

    filePage.getVenusGlobalNav().openAccountNAvigation().clickLogOut();
    filePage.getVenusGlobalNav().openAccountNAvigation()
        .logIn(credentials.userNameStaff, credentials.passwordStaff);
    DeletePageObject deletePage = filePage.deletePage();
    deletePage.submitDeletion();

    filePage = specialVideos.openFilePage(wikiURL, video.getWikiFileName());
    filePage.verifyEmptyFilePage();
  }

  @Test(enabled = false, groups = {"VetTests002", "VetTests", "SpecialVideo", "Media"})
  public void SpecialVideos_002_Library() {
    SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver);
    specialVideos.logInCookie(credentials.userName, credentials.password, wikiURL);
    specialVideos.openSpecialVideoPage(wikiURL);
    VetAddVideoComponentObject vetAddingVideo = specialVideos.clickAddAVideo();
    vetAddingVideo.addVideoByQuery(VideoContent.WIKIA_VIDEO_QUERY, 0);
    specialVideos.verifyVideoAdded(vetAddingVideo.getVideoName());
  }

}
