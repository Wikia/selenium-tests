package com.wikia.webdriver.testcases.visualeditor;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Alignment;
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

/**
 * These tests are failing in FF, but was tested manually
 * - changeImageAlignment
 * - editImageCaption
 */
@InBrowser(browser = Browser.CHROME)
public class VEImageTests extends NewTestTemplate {
  @Test(groups = {"VEImageTests", "VEMediaPreview"})
  @Execute(asUser = User.USER_9)
  public void previewImage() {
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

  @Test(groups = {"VEImageTests", "VEMediaSetting"})
  @Execute(asUser = User.USER_9)
  public void editImageCaption() {
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
    ve.publish();
  }

  @Test(groups = {"VEImageTests", "VEMediaSetting"})
  @Execute(asUser = User.USER_9)
  public void changeImageAlignment() {
    int numOfMedia = 3;
    List<String> wikiTexts = new ArrayList<String>();
    wikiTexts.add("|centre");
    wikiTexts.add("|left");

    String randomArticleName =
        PageContent.ARTICLE_NAME_PREFIX + new VisualEditorPageObject(driver).getTimeStamp();
    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, randomArticleName);
    VisualEditorAddMediaDialog mediaDialog =  ve.searchImage("h");
    ve = mediaDialog.addExistingMedia(numOfMedia);
    ve.verifyMedias(numOfMedia);
    ve.alignMedia(2, Alignment.LEFT);
    ve.alignMedia(0, Alignment.CENTER);
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
