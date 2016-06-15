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
  @Test(groups = {"VEVideo", "VEAddExternalVideo"})
  @Execute(asUser = User.USER)
  public void addNonPremiumVideo() {
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

  @Test(groups = {"VEVideo", "VEAddExternalVideo"})
  @Execute(asUser = User.USER)
  public void addPremiumVideo() {
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

  @Test(groups = {"VEVideo", "VEAddExistingVideo"})
  @Execute(asUser = User.USER)
  public void addExistingVideo() {
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


  @Test(groups = {"VEVideo", "VEAddExistingVideo"})
  @Execute(asUser = User.USER)
  public void removeVideoFromArticle() {
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

  @Test(groups = {"VEVideo", "VEVideoPreview"})
  @Execute(asUser = User.USER_9)
  public void previewVideo() {
    String mediaTitle =
        "Short film directed by Guy Ritchie starring David Beckham - H&M Spring 2013";

    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog = ve.clickVideoButton();
    mediaDialog = mediaDialog.searchMedia(mediaTitle);
    ve = mediaDialog.previewExistingMediaByTitle(mediaTitle);
    ve.verifyPreviewVideo();
  }

  @Test(groups = {"VEVideo", "VEMediaResize"})
  @Execute(asUser = User.USER_9)
  public void resizeVideoWithHandle() {
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

  @Test(groups = {"VEVideo", "VEMediaResize"})
  @Execute(asUser = User.USER_9)
  public void resizeVideoWithSetting() {
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
