package com.wikia.webdriver.testcases.visualeditor;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorAddMediaDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

public class VEAddVideoTests extends NewTestTemplate {

  // AM01
  @Test(groups = {"VEAddVideo", "VEAddExternalVideoTests_001", "VEAddExternalVideo",
      "VEAddExternalVideoTests_004"})
  @Execute(asUser = User.USER)
  public void VEAddExternalVideoTests_001_AddNonPremiumVid() {
    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, PageContent.ARTICLE_NAME_PREFIX
            + DateTime.now().getMillis());
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog =
        (VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
    VisualEditorPageObject veNew = mediaDialog.addMediaByURL(VideoContent.NON_PREMIUM_VIDEO_URL);
    veNew.verifyVideos(1);
    veNew.verifyVEToolBarPresent();
    VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
    ArticlePageObject article = save.savePage();
    article.verifyVEPublishComplete();
  }

  //AM02
  @Test(groups = {"VEAddVideo", "VEAddExternalVideoTests_002", "VEAddExternalVideo"})
  @Execute(asUser = User.USER)
  public void VEAddExternalVideoTests_002_AddPremiumVid() {
    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, PageContent.ARTICLE_NAME_PREFIX
            + DateTime.now().getMillis());
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog =
        (VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
    VisualEditorPageObject veNew = mediaDialog.addMediaByURL(VideoContent.PREMIUM_VIDEO_URL);
    veNew.verifyVideos(1);
    veNew.verifyVEToolBarPresent();
    VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
    ArticlePageObject article = save.savePage();
    article.verifyVEPublishComplete();
  }

  // AM03
  @Test(groups = {"VEAddVideo", "VEAddExternalVideoTests_003", "VEAddExistingVideo"})
  @Execute(asUser = User.USER)
  public void VEAddExternalVideoTests_003_AddExistingVid() {
    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, PageContent.ARTICLE_NAME_PREFIX
            + DateTime.now().getMillis());
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog =
        (VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
    mediaDialog = mediaDialog.searchMedia("y");
    VisualEditorPageObject veNew = mediaDialog.addExistingMedia(2);
    veNew.verifyVideos(2);
    veNew.verifyVEToolBarPresent();
    VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
    ArticlePageObject article = save.savePage();
    article.verifyVEPublishComplete();
  }


  @Test(groups = {"VEAddVideo", "VEAddExternalVideoTests_004", "VEAddExistingVideo"})
  @Execute(asUser = User.USER)
  public void VEAddExternalVideoTests_004_RemoveVideoFromArticle() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog =
        (VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
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
}
