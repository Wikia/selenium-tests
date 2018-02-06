package com.wikia.webdriver.testcases.visualeditor;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.ImageSize;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorAddMediaDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;
import org.joda.time.DateTime;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

/**
 * These tests are failing in FF, but was tested manually
 * - removeVideoFromArticle
 * - resizeVideoWithHandle
 * - resizeVideoWithHandle
 */
@InBrowser(browser = Browser.CHROME)
public class VEVideoTests extends NewTestTemplate {
  @Test(groups = {"VEVideo", "VEAddExternalVideo"})
  @Execute(asUser = User.USER)
  public void addNonPremiumVideo() {
    VisualEditorPageObject ve =
        new VisualEditorPageObject().openVEOnArticle(wikiURL, PageContent.ARTICLE_NAME_PREFIX
                                                                    + DateTime.now().getMillis());
    VisualEditorPageObject veNew = ve.addVideoToContent(VideoContent.NON_PREMIUM_VIDEO_URL);
    veNew.verifyVideos(1);
    veNew.verifyVEToolBarPresent();
    veNew.publish();
  }
//  // I think this test should be removed, because Premium Video was something from video.wikia.com
//  @Test(groups = {"VEVideo", "VEAddExternalVideo"})
//  @Execute(asUser = User.USER)
//  public void addPremiumVideo() {
//    VisualEditorPageObject ve =
//        new VisualEditorPageObject().openVEOnArticle(wikiURL, PageContent.ARTICLE_NAME_PREFIX
//                                                                    + DateTime.now().getMillis());
//    VisualEditorPageObject veNew = ve.addVideoToContent(VideoContent.PREMIUM_VIDEO_URL);
//    veNew.verifyVideos(1);
//    veNew.verifyVEToolBarPresent();
//    veNew.publish();
//  }

  @Test(groups = {"VEVideo", "VEAddExistingVideo"})
  @Execute(asUser = User.USER)
  @RelatedIssue(comment = "This test will run smoothly when Video removing scripts are done.")
  public void addExistingVideo() {
    VisualEditorPageObject ve =
        new VisualEditorPageObject().openVEOnArticle(wikiURL, PageContent.ARTICLE_NAME_PREFIX
                                                                    + DateTime.now().getMillis());

    VisualEditorAddMediaDialog mediaDialog = ve.searchVideo("y");
    VisualEditorPageObject veNew = mediaDialog.addExistingMedia(2);
    veNew.verifyVideos(2);
    veNew.verifyVEToolBarPresent();
    veNew.publish();
  }


  @Test(enabled = false, groups = {"VEVideo", "VEAddExistingVideo"})
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "SUS-757")
  public void removeVideoFromArticle() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditorPageObject ve =
        new VisualEditorPageObject().openVEOnArticle(wikiURL, articleName);

    VisualEditorPageObject veNew = ve.addVideoToContent(VideoContent.NON_PREMIUM_VIDEO_URL);
    veNew.verifyVideos(1);
    veNew.publish();

    ve.openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    ve.selectMediaAndDelete();
    ve.verifyNoVideo();
    ve.publish();
  }

  @Test(groups = {"VEVideo", "VEVideoPreview"})
  @Execute(asUser = User.USER_9)
  public void previewVideo() {
    String mediaTitle =
        "Short film directed by Guy Ritchie starring David Beckham - H&M Spring 2013";

    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditorPageObject ve =
        new VisualEditorPageObject().openVEOnArticle(wikiURL, articleName);
    VisualEditorAddMediaDialog mediaDialog = ve.searchVideo(mediaTitle);
    ve = mediaDialog.previewExistingVideoByTitle(mediaTitle);
    ve.verifyPreviewVideo();
  }

  @Test(groups = {"VEVideo", "VEMediaResize"})
  @Execute(asUser = User.USER_9)
  public void resizeVideoWithHandle() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditorPageObject ve =
        new VisualEditorPageObject().openVEOnArticle(wikiURL, articleName);

    VisualEditorAddMediaDialog mediaDialog = ve.searchVideo("h");
    ve = mediaDialog.addExistingMedia(1);
    ve.verifyVideos(1);
    Dimension source = ve.getVideoDimension();
    ve.randomResizeOnMedia();
    ve.verifyVideoResized(source);
    ve.publish();
  }

  @Test(groups = {"VEVideo", "VEMediaResize"})
  @Execute(asUser = User.USER_9)
  public void resizeVideoWithSetting() {
    int resizeNumber = 250;
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();

    VisualEditorPageObject ve =
        new VisualEditorPageObject().openVEOnArticle(wikiURL, articleName);

    VisualEditorAddMediaDialog mediaDialog = ve.searchVideo("h");
    ve = mediaDialog.addExistingMedia(1);
    ve.verifyVideos(1);
    Dimension source = ve.getVideoDimension();
    ve.selectMedia();
    ve.resizeMedia(resizeNumber, ImageSize.WIDTH);
    ve.verifyVideoResized(source);
    source = ve.getVideoDimension();
    ve.resizeMedia(resizeNumber, ImageSize.HEIGHT);
    ve.verifyVideoResized(source);
    ve.publish();
  }
}
