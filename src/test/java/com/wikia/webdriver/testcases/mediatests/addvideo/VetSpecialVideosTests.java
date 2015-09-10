/**
 *
 */
package com.wikia.webdriver.testcases.mediatests.addvideo;

import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.video.YoutubeVideo;
import com.wikia.webdriver.common.core.video.YoutubeVideoProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;

import org.testng.annotations.Test;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @ownership Content X-Wing
 */
public class VetSpecialVideosTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"VetTests001", "VetTests", "SpecialVideo", "Media"})
  public void SpecialVideos_001_Provider() {
    String wikiURL = urlBuilder.getUrlForWiki("mobileregressiontesting");
    YoutubeVideo video = YoutubeVideoProvider.getLatestVideoForQuery("cats");

    SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver);
    specialVideos.loginAs(credentials.userName, credentials.password, wikiURL);
    specialVideos.openSpecialVideoPage(wikiURL);
    VetAddVideoComponentObject vetAddingVideo = specialVideos.clickAddAVideo();
    vetAddingVideo.addVideoByUrl(video.getUrl());
    specialVideos.verifyVideoAdded(video.getTitle());

    FilePagePageObject filePage = specialVideos.openFilePage(wikiURL, video.getWikiFileName());

    filePage.getVenusGlobalNav().openAccountNAvigation().clickLogOut();
    filePage.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    DeletePageObject deletePage = filePage.deletePage();
    deletePage.submitDeletion();

    filePage = specialVideos.openFilePage(wikiURL, video.getWikiFileName());
    filePage.verifyEmptyFilePage();
  }

  @Test(enabled = false, groups = {"VetTests002", "VetTests", "SpecialVideo", "Media"})
  public void SpecialVideos_002_Library() {
    SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver);
    specialVideos.loginAs(credentials.userName, credentials.password, wikiURL);
    specialVideos.openSpecialVideoPage(wikiURL);
    VetAddVideoComponentObject vetAddingVideo = specialVideos.clickAddAVideo();
    vetAddingVideo.addVideoByQuery(VideoContent.WIKIA_VIDEO_QUERY, 0);
    specialVideos.verifyVideoAdded(vetAddingVideo.getVideoName());
  }

}
