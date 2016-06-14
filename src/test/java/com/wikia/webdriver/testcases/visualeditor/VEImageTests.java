package com.wikia.webdriver.testcases.visualeditor;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Alignment;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Setting;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorAddMediaDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorMediaSettingsDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorReviewChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class VEImageTests extends NewTestTemplate {
  // AM05
  @Test(groups = {"VEImageTests", "VEImageTests_001", "VEMediaPreview"})
  @Execute(asUser = User.USER_9)
  public void VEImageTests_001_previewImage() {
    String mediaTitle = "Thomas Wright 1792 - 1849";

    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, PageContent.ARTICLE_NAME_PREFIX
                                                                    + DateTime.now().getMillis());
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog = ve.clickImageButton();
    mediaDialog = mediaDialog.searchMedia(mediaTitle);
    ve = mediaDialog.previewExistingMediaByTitle(mediaTitle);
    ve.verifyPreviewImage();
  }

  // MS01
  @Test(groups = {"VEImageTests", "VEImageTests_002", "VEMediaSetting"})
  @Execute(asUser = User.USER_9)
  public void VEImageTests_002_editCaption() {
    String captionText = "test123";

    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, PageContent.ARTICLE_NAME_PREFIX
                                                                    + DateTime.now().getMillis());
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog = ve.clickImageButton();
    mediaDialog = mediaDialog.searchMedia("h");
    ve = mediaDialog.addExistingMedia(1);
    ve.verifyVideos(1);
    ve.selectMedia();
    VisualEditorMediaSettingsDialog mediaSettingsDialog = ve.openMediaSettings();
    mediaSettingsDialog.typeCaption(captionText);
    ve = mediaSettingsDialog.clickApplyChangesButton();
    ve.verifyVideoCaption(captionText);
    VisualEditorSaveChangesDialog save = ve.clickPublishButton();
    ArticlePageObject article = save.savePage();
    article.verifyVEPublishComplete();
  }
/*

*/
  // MS03
  @Test(groups = {"VEImageTests", "VEImageTests_00", "VEMediaSetting"})
  @Execute(asUser = User.USER_9)
  public void VEImageTests_003_changeAlignment() {
    int numOfMedia = 3;
    List<String> wikiTexts = new ArrayList<String>();
    wikiTexts.add("|centre");
    wikiTexts.add("|left");

    String randomArticleName =
        PageContent.ARTICLE_NAME_PREFIX + new VisualEditorPageObject(driver).getTimeStamp();
    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, randomArticleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog = ve.clickImageButton();
    mediaDialog = mediaDialog.searchMedia("h");
    ve = mediaDialog.addExistingMedia(numOfMedia);
    ve.verifyMedias(numOfMedia);
    ve.verifyEditorSurfacePresent();
    ve.verifyVEToolBarPresent();
    ve.selectMediaByIndex(2);
    VisualEditorMediaSettingsDialog mediaSettingsDialog = ve.openMediaSettings();
    mediaSettingsDialog.selectSettings(Setting.ADVANCED);
    mediaSettingsDialog.clickAlignment(Alignment.LEFT);
    ve = mediaSettingsDialog.clickApplyChangesButton();
    ve.verifyEditorSurfacePresent();
    ve.verifyVEToolBarPresent();
    ve.selectMediaByIndex(0);
    mediaSettingsDialog = ve.openMediaSettings();
    mediaSettingsDialog.selectSettings(Setting.ADVANCED);
    mediaSettingsDialog.clickAlignment(Alignment.CENTER);
    ve = mediaSettingsDialog.clickApplyChangesButton();
    ve.verifyEditorSurfacePresent();
    ve.verifyVEToolBarPresent();
    VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
    VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
    reviewDialog.verifyAddedDiffs(wikiTexts);
    saveDialog = reviewDialog.clickReturnToSaveFormButton();
    ArticlePageObject article = saveDialog.savePage();
    article.verifyVEPublishComplete();
  }
}
