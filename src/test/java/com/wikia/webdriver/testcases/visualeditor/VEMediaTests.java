package com.wikia.webdriver.testcases.visualeditor;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Alignment;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.ImageSize;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Setting;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorAddMediaDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorAddMediaDialog.ImageLicense;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorMediaSettingsDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorReviewChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.joda.time.DateTime;
import org.openqa.selenium.Dimension;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class VEMediaTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();
  String testFullFileName;
  int numOfVideo = 1;
  ImageLicense testImageLicense;

  // AM04
  @Test(groups = {"VEMediaTests", "VEMediaTests_001", "VEMediaPreview"})
  @Execute(asUser = User.USER_9)
  public void VEMediaTests_001_previewVideo() {
    String mediaTitle =
        "Short film directed by Guy Ritchie starring David Beckham - H&M Spring 2013";
    String providerName = "youtube";

    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, PageContent.ARTICLE_NAME_PREFIX
                                                                    + DateTime.now().getMillis());
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog =
        (VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
    mediaDialog = mediaDialog.searchMedia("h");
    ve = mediaDialog.previewExistingMediaByTitle(mediaTitle);
    ve.verifyPreviewVideoPlay(providerName);
  }

  // AM05
  @Test(groups = {"VEMediaTests", "VEMediaTests_002", "VEMediaPreview"})
  @Execute(asUser = User.USER_9)
  public void VEMediaTests_002_previewImage() {
    String mediaTitle = "Thomas Wright 1792 - 1849";

    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, PageContent.ARTICLE_NAME_PREFIX
                                                                    + DateTime.now().getMillis());
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog =
        (VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
    mediaDialog = mediaDialog.searchMedia("h");
    ve = mediaDialog.previewExistingMediaByTitle(mediaTitle);
    ve.verifyPreviewImage();
  }

  // AM06
  // This test would fail on FF: https://wikia-inc.atlassian.net/browse/VE-1370
  @Test(groups = {"VEMediaTests", "VEMediaTests_003", "VEUploadImage"})
  @Execute(asUser = User.USER_9)
  public void VEMediaTests_003_uploadImageWithCustomFileName() {
    String testFileUploadName = "TestFileVEMediaTests003";
    testFullFileName = testFileUploadName + ".png";
    testImageLicense = ImageLicense.CCBYSA;

    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openNewArticleEditModeVisual(wikiURL);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    // Excluding FF on running this VE-1370
    if (!"ff".equalsIgnoreCase(Configuration.getBrowser())) {
      VisualEditorAddMediaDialog mediaDialog =
          (VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
      ve = mediaDialog.uploadImage(PageContent.FILE2PNG, testFileUploadName, testImageLicense);
      VisualEditorSaveChangesDialog save = ve.clickPublishButton();
      ArticlePageObject article = save.savePage();
      article.verifyVEPublishComplete();
    }
  }

  // MS01
  @Test(groups = {"VEMediaTests", "VEMediaTests_004", "VEMediaSetting"})
  @Execute(asUser = User.USER_9)
  public void VEMediaTests_004_editCaption() {
    String captionText = "test123";

    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, PageContent.ARTICLE_NAME_PREFIX
                                                                    + DateTime.now().getMillis());
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog =
        (VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
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

  @Test(groups = {"VEMediaTests", "VEMediaTests_005", "VEMediaResize"})
  @Execute(asUser = User.USER_9)
  public void VEMediaTests_005_resizeVideoWithHandle() {
    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, PageContent.ARTICLE_NAME_PREFIX
                                                                    + DateTime.now().getMillis());
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog =
        (VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
    mediaDialog = mediaDialog.searchMedia("h");
    ve = mediaDialog.addExistingMedia(numOfVideo);
    ve.verifyVideos(numOfVideo);
    Dimension source = ve.getVideoDimension();
    ve.randomResizeOnMedia();
    ve.verifyVideoResized(source);
    VisualEditorSaveChangesDialog save = ve.clickPublishButton();
    ArticlePageObject article = save.savePage();
    article.verifyVEPublishComplete();
  }

  // MS02
  @Test(groups = {"VEMediaTests", "VEMediaTests_006", "VEMediaResize"})
  @Execute(asUser = User.USER_9)
  public void VEMediaTests_006_resizeVideoWithSetting() {
    int resizeNumber = 250;

    VisualEditorPageObject ve =
        new VisualEditorPageObject(driver).openVEOnArticle(wikiURL, PageContent.ARTICLE_NAME_PREFIX
                                                                    + DateTime.now().getMillis());
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog =
        (VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
    mediaDialog = mediaDialog.searchMedia("h");
    ve = mediaDialog.addExistingMedia(numOfVideo);
    ve.verifyVideos(numOfVideo);
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

  // MS03
  @Test(groups = {"VEMediaTests", "VEMediaTests_007", "VEMediaSetting"})
  @Execute(asUser = User.USER_9)
  public void VEMediaTests_007_changeAlignment() {
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
    VisualEditorAddMediaDialog mediaDialog =
        (VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
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

  @AfterGroups(groups = {"VEMediaTests", "VEMediaTests_003", "VEUploadImage"})
  public void delete_Image() {
    // Excluding FF on running this VE-1370
    if (!"ff".equalsIgnoreCase(Configuration.getBrowser())) {
      WikiBasePageObject base = new WikiBasePageObject(driver);
      base.logOut(wikiURL);
      base.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
      FilePagePageObject filePage = new FilePagePageObject(driver).open(testFullFileName);
      filePage.verifyImageLicense(testImageLicense);
      filePage.selectHistoryTab();
      filePage.verifyArticleNameInWgPageName(URLsContent.FILE_NAMESPACE + testFullFileName);
      DeletePageObject deletePage = filePage.deleteVersion(1);
      deletePage.submitDeletion();
    }
  }
}
