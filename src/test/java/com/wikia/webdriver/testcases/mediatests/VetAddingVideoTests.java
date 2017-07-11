package com.wikia.webdriver.testcases.mediatests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWall;
import org.testng.annotations.Test;

/**
 * Documentation: https://docs.google.com/a/wikia-inc.com/spreadsheet/ccc?key=0
 * AtG89yMxyGSadEtPY28ydDB4czkydXNmMkJVQ2NGR0E#gid=7
 */

@Test(groups = {"VetTests", "VetAddVideo", "Media"})
@Execute(onWikia = "sustainingtestchat")
public class VetAddingVideoTests extends NewTestTemplate {

  @Test(groups = {"VetAddVideo_001"}, invocationCount = 20)
  @Execute(asUser = User.SUS_REGULAR_USER2)
  public void VetAddVideo_001_MessageWallProvider() {
    MessageWall wall = new MessageWall(driver).open(User.SUS_REGULAR_USER2.getUserName());
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    wall.clickBoldButton();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    VetAddVideoComponentObject vetAddingVideo = mini.clickAddVideo();
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL3);
    vetOptions.setCaption(PageContent.CAPTION);
    vetOptions.submit();
    mini.verifyVideoMiniEditor();
    wall.submit();
    wall.verifyPostedMessageVideo(title);
  }

  @Test(groups = {"VetAddVideo_002"}, invocationCount = 20)
  @Execute(asUser = User.SUS_REGULAR_USER2)
  public void VetAddVideo_002_MessageWallLibrary() {
    MessageWall wall = new MessageWall(driver).open(User.SUS_REGULAR_USER2.getUserName());
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    wall.clickBoldButton();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    VetAddVideoComponentObject vetAddingVideo = mini.clickAddVideo();
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByQuery(VideoContent.WIKIA_VIDEO_QUERY, 0);
    vetOptions.setCaption(PageContent.CAPTION);
    vetOptions.submit();
    mini.verifyVideoMiniEditor();
    wall.submit();
    wall.verifyPostedMessageVideo(title);
  }
}
