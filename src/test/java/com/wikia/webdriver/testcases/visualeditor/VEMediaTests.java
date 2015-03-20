package com.wikia.webdriver.testcases.visualeditor;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Alignment;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.ImageSize;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Setting;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplateBeforeClass;
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

import org.openqa.selenium.Dimension;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Robert 'Rochan' Chan
 * @ownership Contribution <p/> VE-1335 Previewing Youtube video from VE's media dialog VE-1335
 * Previewing image from VE's media dialog VE-1336 1519 Uploading an image with a new file name
 * VE-1334 Adding caption to a media VE-1333 Resizing a media with the highlight handle VE-1333
 * Resizing a media with the advance setting from the media dialog VE-1419 Adjusting media's
 * horizontal alignment
 */

public class VEMediaTests extends NewTestTemplateBeforeClass {

  Credentials credentials = config.getCredentials();
  WikiBasePageObject base;
  String articleName, testFullFileName;
  int numOfVideo = 1;
  ImageLicense testImageLicense;

  @BeforeMethod(alwaysRun = true)
  public void setup_VEPreferred() {
    base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
    articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
  }

  //AM04
  @Test(
      groups = {"VEMediaTests", "VEMediaTests_001", "VEPreviewVideo"}
  )
  public void VEMediaTests_001_previewVideo() {
    String
        mediaTitle =
        "Short film directed by Guy Ritchie starring David Beckham - H&M Spring 2013";
    String providerName = "youtube";

    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog =
        (VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
    mediaDialog = mediaDialog.searchMedia("h");
    ve = mediaDialog.previewExistingMediaByTitle(mediaTitle);
    ve.verifyPreviewVideoPlay(providerName);
    ve.logOut(wikiURL);
  }

  //AM05
  @Test(
      groups = {"VEMediaTests", "VEMediaTests_002", "VEPreviewImage"}
  )
  public void VEMediaTests_002_previewImage() {
    String mediaTitle = "Thomas Wright 1792 - 1849";

    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog =
        (VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
    mediaDialog = mediaDialog.searchMedia("h");
    ve = mediaDialog.previewExistingMediaByTitle(mediaTitle);
    ve.verifyPreviewImage();
    ve.logOut(wikiURL);
  }

  //AM06
  @Test(
      groups = {"VEMediaTests", "VEMediaTests_003", "VEUploadImage"}
  )
  public void VEMediaTests_003_uploadImageWithCustomFileName() {
    String testFileUploadName = "TestFileVEMediaTests003";
    testFullFileName = testFileUploadName + ".png";
    testImageLicense = ImageLicense.CCBYSA;

    VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog =
        (VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
    ve = mediaDialog.uploadImage(PageContent.FILE2PNG, testFileUploadName, testImageLicense);
    VisualEditorSaveChangesDialog save = ve.clickPublishButton();
    ArticlePageObject article = save.savePage();
    article.verifyVEPublishComplete();
    article.logOut(wikiURL);
  }

  //MS01
  @Test(
      groups = {"VEMediaTests", "VEMediaTests_004", "VEPreviewVideo"}
  )
  public void VEMediaTests_004_editCaption() {
    String captionText = "test123";

    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
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
    article.logOut(wikiURL);
  }

  @Test(
      groups = {"VEMediaTests", "VEMediaTests_005", "VEResizeVideo"}
  )
  public void VEMediaTests_005_resizeVideoWithHandle() {
    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
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
    article.logOut(wikiURL);
  }

  //MS02
  @Test(
      groups = {"VEMediaTests", "VEMediaTests_006", "VEResizeVideo"}
  )
  public void VEMediaTests_006_resizeVideoWithSetting() {
    int resizeNumber = 250;

    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
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
    //change width of video to 250
    mediaSettingsDialog.setCustomSize(resizeNumber, ImageSize.WIDTH);
    mediaSettingsDialog.clickApplyChangesButton();
    ve.verifyVideoResized(source);
    source = ve.getVideoDimension();
    mediaSettingsDialog = ve.openMediaSettings();
    mediaSettingsDialog.selectSettings(Setting.ADVANCED);
    //change height of video to 250
    mediaSettingsDialog.setCustomSize(resizeNumber, ImageSize.HEIGHT);
    mediaSettingsDialog.clickApplyChangesButton();
    ve.verifyVideoResized(source);
    VisualEditorSaveChangesDialog save = ve.clickPublishButton();
    ArticlePageObject article = save.savePage();
    article.verifyVEPublishComplete();
    article.logOut(wikiURL);
  }

  //MS03
  @Test(
      groups = {"VEMediaTests", "VEMediaTests_007", "VEAlignMedia"}
  )
  public void VEMediaTests_007_changeAlignment() {
    int numOfMedia = 3;
    List<String> wikiTexts = new ArrayList<String>();
    wikiTexts.add("|centre");
    wikiTexts.add("|left");

    String randomArticleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, randomArticleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog =
        (VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
    mediaDialog = mediaDialog.searchMedia("h");
    ve = mediaDialog.addExistingMedia(numOfMedia);
    ve.verifyMedias(numOfMedia);
    ve.verifyVEToolBarPresent();
    ve.selectMediaByIndex(2);
    VisualEditorMediaSettingsDialog mediaSettingsDialog = ve.openMediaSettings();
    mediaSettingsDialog.selectSettings(Setting.ADVANCED);
    mediaSettingsDialog.clickAlignment(Alignment.LEFT);
    ve = mediaSettingsDialog.clickApplyChangesButton();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    ve.selectMediaByIndex(0);
    mediaSettingsDialog = ve.openMediaSettings();
    mediaSettingsDialog.selectSettings(Setting.ADVANCED);
    mediaSettingsDialog.clickAlignment(Alignment.CENTER);
    ve = mediaSettingsDialog.clickApplyChangesButton();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
    VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
    reviewDialog.verifyAddedDiffs(wikiTexts);
    saveDialog = reviewDialog.clickReturnToSaveFormButton();
    ArticlePageObject article = saveDialog.savePage();
    article.verifyVEPublishComplete();
    article.logOut(wikiURL);
  }

  @AfterGroups(groups = "VEMediaTests_003")
  public void delete_Image() {
    base.logOut(wikiURL);
    base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    FilePagePageObject filePage = base.openFilePage(wikiURL, testFullFileName);
    filePage.verifyImageLicense(testImageLicense);
    filePage.selectHistoryTab();
    filePage.verifyArticleName(URLsContent.FILE_NAMESPACE + testFullFileName);
    DeletePageObject deletePage = filePage.deleteVersion(1);
    deletePage.submitDeletion();
    deletePage.logOut(wikiURL);
  }
}
