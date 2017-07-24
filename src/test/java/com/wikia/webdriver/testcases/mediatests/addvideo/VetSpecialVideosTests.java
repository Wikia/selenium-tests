package com.wikia.webdriver.testcases.mediatests.addvideo;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.video.YoutubeVideo;
import com.wikia.webdriver.common.core.video.YoutubeVideoProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePage;

@Test(groups = {"VetTests", "SpecialVideo", "Media"})
@Execute(onWikia = "sustainingtest")
public class VetSpecialVideosTests extends NewTestTemplate {

  @Test(groups = {"VetTests001"})
  @Execute(asUser = User.SUS_REGULAR_USER2)
  public void StaffCanDeleteFilePage() {
    YoutubeVideo video = YoutubeVideoProvider.getLatestVideoForQuery("media");
    SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver);
    specialVideos.openSpecialVideoPage(wikiURL);
    VetAddVideoComponentObject vetAddingVideo = specialVideos.clickAddAVideo();
    vetAddingVideo.addVideoWithoutDetailsByUrl(video.getUrl());
    specialVideos.verifyVideoAdded(video.getTitle());

    FilePage filePage = new FilePage().open(video.getFileName());

    filePage.loginAs(User.SUS_CHAT_STAFF2);
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
