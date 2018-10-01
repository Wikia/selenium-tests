package com.wikia.webdriver.testcases.desktop.visualeditor;

import static com.wikia.webdriver.common.contentpatterns.VideoContent.TEST_VIDEO_QUERY;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Alignment;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.*;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * These tests are failing in FF, but was tested manually - changeImageAlignment - editImageCaption
 */
@InBrowser(browser = Browser.CHROME)
public class VEImageTests extends NewTestTemplate {

  @Test(groups = {"VEImageTests", "VEMediaPreview"})
  @Execute(asUser = User.USER_9)
  public void previewImage() {
    String mediaTitle = "Default Image002";

    VisualEditorPageObject ve = new VisualEditorPageObject().openVEOnArticle(wikiURL,
                                                                             PageContent.ARTICLE_NAME_PREFIX
                                                                             + DateTime.now()
                                                                                 .getMillis()
    );
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog = ve.clickImageButton();
    mediaDialog = mediaDialog.searchMedia(mediaTitle);
    ve = mediaDialog.previewExistingPhotoByTitle(mediaTitle);
    ve.verifyPreviewImage();
  }

  @Test(groups = {"VEImageTests", "VEMediaSetting"})
  @Execute(asUser = User.USER_9)
  public void editImageCaption() {
    String captionText = "test123";

    VisualEditorPageObject ve = new VisualEditorPageObject().openVEOnArticle(wikiURL,
                                                                             PageContent.ARTICLE_NAME_PREFIX
                                                                             + DateTime.now()
                                                                                 .getMillis()
    );
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog = ve.clickImageButton();
    mediaDialog = mediaDialog.searchMedia(TEST_VIDEO_QUERY);
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

    String randomArticleName = PageContent.ARTICLE_NAME_PREFIX
                               + new VisualEditorPageObject().getTimeStamp();
    VisualEditorPageObject veCreatePage = new VisualEditorPageObject().openVEOnArticle(wikiURL,
                                                                                       randomArticleName
    );
    VisualEditorAddMediaDialog mediaDialog = veCreatePage.searchImage(TEST_VIDEO_QUERY);
    veCreatePage = mediaDialog.addExistingMedia(numOfMedia);
    veCreatePage.verifyMedias(numOfMedia);
    veCreatePage.clickPublishButton();
    new VisualEditorSaveChangesDialog(this.driver).savePage();
    veCreatePage.waitForPageLoad();

    ArticlePageObject article = new ArticlePageObject();
    article.verifyVEPublishComplete();
    article.openVEModeWithMainEditButton();
    VisualEditorPageObject ve = new VisualEditorPageObject();
    ve.alignMedia(2, Alignment.LEFT);
    ve.alignMedia(0, Alignment.CENTER);
    ve.verifyEditorSurfacePresent();
    ve.verifyVEToolBarPresent();
    VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
    VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
    reviewDialog.verifyAddedDiffs(wikiTexts);
    saveDialog = reviewDialog.clickReturnToSaveFormButton();
    saveDialog.savePage();
    article.verifyVEPublishComplete();
  }
}
