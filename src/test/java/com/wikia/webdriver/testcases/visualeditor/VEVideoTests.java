package com.wikia.webdriver.testcases.visualeditor;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorAddMediaDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorMediaSettingsDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.joda.time.DateTime;
import org.openqa.selenium.Dimension;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Setting;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.ImageSize;
import org.testng.annotations.Test;

public class VEVideoTests extends NewTestTemplate {
  // AM01
  @Test(groups = {"VEVideo", "VEAddExternalVideoTests_001", "VEAddExternalVideo",
      "VEAddExternalVideoTests_004"})
  @Execute(asUser = User.USER)
  public void VEAddExternalVideoTests_001_AddNonPremiumVid() {
    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, PageContent.ARTICLE_NAME_PREFIX
            + DateTime.now().getMillis());
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog = ve.clickVideoButton();
    VisualEditorPageObject veNew = mediaDialog.addMediaByURL(VideoContent.NON_PREMIUM_VIDEO_URL);
    veNew.verifyVideos(1);
    veNew.verifyVEToolBarPresent();
    VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
    ArticlePageObject article = save.savePage();
    article.verifyVEPublishComplete();
  }

  //AM02
  @Test(groups = {"VEVideo", "VEAddExternalVideoTests_002", "VEAddExternalVideo"})
  @Execute(asUser = User.USER)
  public void VEAddExternalVideoTests_002_AddPremiumVid() {
    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, PageContent.ARTICLE_NAME_PREFIX
            + DateTime.now().getMillis());
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog = ve.clickVideoButton();
    VisualEditorPageObject veNew = mediaDialog.addMediaByURL(VideoContent.PREMIUM_VIDEO_URL);
    veNew.verifyVideos(1);
    veNew.verifyVEToolBarPresent();
    VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
    ArticlePageObject article = save.savePage();
    article.verifyVEPublishComplete();
  }

  // AM03
  @Test(groups = {"VEVideo", "VEAddExternalVideoTests_003", "VEAddExistingVideo"})
  @Execute(asUser = User.USER)
  public void VEAddExternalVideoTests_003_AddExistingVid() {
    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, PageContent.ARTICLE_NAME_PREFIX
                                                                    + DateTime.now().getMillis());
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog = ve.clickVideoButton();
    mediaDialog = mediaDialog.searchMedia("y");
    VisualEditorPageObject veNew = mediaDialog.addExistingMedia(2);
    veNew.verifyVideos(2);
    veNew.verifyVEToolBarPresent();
    VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
    ArticlePageObject article = save.savePage();
    article.verifyVEPublishComplete();
  }


  @Test(groups = {"VEVideo", "VEAddExternalVideoTests_004", "VEAddExistingVideo"})
  @Execute(asUser = User.USER)
  public void VEAddExternalVideoTests_004_RemoveVideoFromArticle() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog = ve.clickVideoButton();
    VisualEditorPageObject veNew = mediaDialog.addMediaByURL(VideoContent.NON_PREMIUM_VIDEO_URL);
    veNew.verifyVideos(1);
    veNew.verifyVEToolBarPresent();
    VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
    ArticlePageObject article = save.savePage();
    article.verifyVEPublishComplete();

    ve.openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    ve.selectMediaAndDelete();
    ve.verifyNoVideo();
    save = ve.clickPublishButton();
    article = save.savePage();
    article.verifyVEPublishComplete();
  }

  @Test(groups = {"VEVideo", "VEVideo_001", "VEVideoPreview"})
  @Execute(asUser = User.USER_9)
  public void VEVideoTests_001_previewVideo() {
    String mediaTitle =
        "Short film directed by Guy Ritchie starring David Beckham - H&M Spring 2013";
    String providerName = "youtube";

    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog = ve.clickVideoButton();
    mediaDialog = mediaDialog.searchMedia(mediaTitle);
    ve = mediaDialog.previewExistingMediaByTitle(mediaTitle);
    ve.verifyPreviewVideoPlay(providerName);
  }

  @Test(groups = {"VEVideo", "VEVideo_002", "VEMediaResize"})
  @Execute(asUser = User.USER_9)
  public void VEVideoTests_002_resizeVideoWithHandle() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog = ve.clickVideoButton();
    mediaDialog = mediaDialog.searchMedia("h");
    ve = mediaDialog.addExistingMedia(1);
    ve.verifyVideos(1);
    Dimension source = ve.getVideoDimension();
    ve.randomResizeOnMedia();
    ve.verifyVideoResized(source);
    VisualEditorSaveChangesDialog save = ve.clickPublishButton();
    ArticlePageObject article = save.savePage();
    article.verifyVEPublishComplete();
  }

  // MS02
  @Test(groups = {"VEVideo", "VEVideo_003", "VEMediaResize"})
  @Execute(asUser = User.USER_9)
  public void VEVideoTests_003_resizeVideoWithSetting() {
    int resizeNumber = 250;
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();

    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog = ve.clickVideoButton();
    mediaDialog = mediaDialog.searchMedia("h");
    ve = mediaDialog.addExistingMedia(1);
    ve.verifyVideos(1);
    Dimension source = ve.getVideoDimension();
    ve.selectMedia();
    VisualEditorMediaSettingsDialog mediaSettingsDialog = ve.openMediaSettings();
    mediaSettingsDialog.selectSettings(Setting.ADVANCED);
    // change width of video to 250
    mediaSettingsDialog.setCustomSize(resizeNumber, ImageSize.WIDTH);
    mediaSettingsDialog.clickApplyChangesButton();
    ve.verifyVideoResized(source);
    source = ve.getVideoDimension();
    mediaSettingsDialog = ve.openMediaSettings();
    mediaSettingsDialog.selectSettings(Setting.ADVANCED);
    // change height of video to 250
    mediaSettingsDialog.setCustomSize(resizeNumber, ImageSize.HEIGHT);
    mediaSettingsDialog.clickApplyChangesButton();
    ve.verifyVideoResized(source);
    VisualEditorSaveChangesDialog save = ve.clickPublishButton();
    ArticlePageObject article = save.savePage();
    article.verifyVEPublishComplete();
  }
}
