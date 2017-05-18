package com.wikia.webdriver.testcases.mediatests.addvideo;

import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.video.YoutubeVideo;
import com.wikia.webdriver.common.core.video.YoutubeVideoProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePage;
import org.testng.annotations.Test;

@Test(groups = {"VetTests", "SpecialVideo", "Media"})
public class VetSpecialVideosTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"VetTests001"}, invocationCount = 15)
  @Execute(asUser = User.USER)
  public void SpecialVideos_001_Provider() {
    YoutubeVideo video = YoutubeVideoProvider.getLatestVideoForQuery("flower");
    SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver);
    specialVideos.openSpecialVideoPage(wikiURL);
    VetAddVideoComponentObject vetAddingVideo = specialVideos.clickAddAVideo();
    vetAddingVideo.addVideoByUrl(video.getUrl());
    specialVideos.verifyVideoAdded(video.getTitle());

    FilePage filePage = new FilePage().open(video.getFileName());

    // filePage.getGlobalNavigation().openAccountNavigation().clickLogOut();
    filePage.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    DeletePageObject deletePage = filePage.deletePage();
    deletePage.submitDeletion();

    filePage = filePage.open(video.getFileName());
    filePage.verifyEmptyFilePage();
  }

  @Test(enabled = false, groups = {"VetTests002"})
  @Execute(asUser = User.USER)
  public void SpecialVideos_002_Library() {
    SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver);
    specialVideos.openSpecialVideoPage(wikiURL);
    VetAddVideoComponentObject vetAddingVideo = specialVideos.clickAddAVideo();
    vetAddingVideo.addVideoByQuery(VideoContent.WIKIA_VIDEO_QUERY, 0);
    specialVideos.verifyVideoAdded(vetAddingVideo.getVideoName());
  }

}
