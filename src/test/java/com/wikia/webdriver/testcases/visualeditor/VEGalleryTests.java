package com.wikia.webdriver.testcases.visualeditor;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorInsertGalleryDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * These tests are failing in FF, but was tested manually
 * - VEGalleryTests_005_Remove
 */
@InBrowser(browser = Browser.CHROME)
public class VEGalleryTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();
  ArticlePageObject article;
  String articleName;

  @BeforeMethod(alwaysRun = true)
  public void setup_VEPreferred() {
    wikiURL = urlBuilder.getUrlForWiki(URLsContent.VE_ENABLED_WIKI);
    article = new ArticlePageObject();
    article.loginAs(credentials.userName10, credentials.password10, wikiURL);
  }

  //AG01
  @Test(
      groups = {"VEGallery", "VEGalleryTests_001", "VEGalleryTests_005", "VEGalleryAdd"}
  )
  public void VEGalleryTests_001_AddGallery() {
    int numOfMedias = 9;
    int numOfGalleries = 1;

    articleName = PageContent.ARTICLE_NAME_PREFIX + article.getTimeStamp();
    VisualEditorPageObject ve = article.openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    ve.typeTextArea(PageContent.ARTICLE_TEXT);
    VisualEditorInsertGalleryDialog galleryDialog = ve.clickGalleryButton();
    galleryDialog = galleryDialog.searchMedia("he");
    ve = galleryDialog.addExistingMedia(numOfMedias);
    ve.verifyGalleries(numOfGalleries);
    ve.verifyMediasInGallery(numOfMedias);
    ve.publish();
  }

  //AG02
  @Test(
      groups = {"VEGallery", "VEGalleryTests_002", "VEGalleryCart"}
  )
  public void VEGalleryTests_002_GalleryCart() {
    int numOfMediaToRemoveFirst = 1;
    int numOfMediaToRemoveSecond = 2;
    int numOfMediaToAdd = 2;
    int initialNumOfMedia = 7;
    int expectedNumOfMedia = initialNumOfMedia;

    String randomArticleName = PageContent.ARTICLE_NAME_PREFIX + article.getTimeStamp();
    VisualEditorPageObject ve = article.openVEOnArticle(wikiURL, randomArticleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorInsertGalleryDialog galleryDialog = ve.clickGalleryButton();
    galleryDialog = galleryDialog.searchMedia("he");
    //verify # of cart items  = 9
    galleryDialog.addMediaToCart(initialNumOfMedia);
    galleryDialog.verifyNumOfCartItems(expectedNumOfMedia);
    //verify # of cart items  = 8
    galleryDialog.removeMediaFromCart(numOfMediaToRemoveFirst);
    expectedNumOfMedia = expectedNumOfMedia - numOfMediaToRemoveFirst;
    galleryDialog.verifyNumOfCartItems(expectedNumOfMedia);
    //verify # of cart item = 11
    galleryDialog = galleryDialog.searchMedia("a");
    galleryDialog.addMediaToCart(numOfMediaToAdd);
    expectedNumOfMedia = expectedNumOfMedia + numOfMediaToAdd;
    galleryDialog.verifyNumOfCartItems(expectedNumOfMedia);
    //verify # of cart item = 9
    galleryDialog = galleryDialog.searchMedia("he");
    galleryDialog.removeMediaFromCart(numOfMediaToRemoveSecond);
    expectedNumOfMedia = expectedNumOfMedia - numOfMediaToRemoveSecond;
    galleryDialog.verifyNumOfCartItems(expectedNumOfMedia);
  }

  //AG03
  @Test(
      groups = {"VEGallery", "VEGalleryTests_003", "VEGalleryPreview"}
  )
  public void VEGalleryTests_003_PreviewOnTitle() {
    String randomArticleName = PageContent.ARTICLE_NAME_PREFIX + article.getTimeStamp();

    VisualEditorPageObject ve = article.openVEOnArticle(wikiURL, randomArticleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorInsertGalleryDialog galleryDialog = ve.clickGalleryButton();
    galleryDialog = galleryDialog.searchMedia("he");
    ve = galleryDialog.clickTitleToPreview(7);
    ve.verifyPreviewImage();
  }

  //AG04
  @Test(
      groups = {"VEGallery", "VEGalleryTests_004", "VEGalleryPreview"}
  )
  public void VEGalleryTests_004_PreviewOnMetadata() {
    String randomArticleName = PageContent.ARTICLE_NAME_PREFIX + article.getTimeStamp();

    VisualEditorPageObject ve = article.openVEOnArticle(wikiURL, randomArticleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorInsertGalleryDialog galleryDialog = ve.clickGalleryButton();
    galleryDialog = galleryDialog.searchMedia("he");
    ve = galleryDialog.clickMetaDataToPreview(3);
    ve.verifyPreviewImage();
  }

  @Test(
      groups = {"VEGallery", "VEGalleryTests_005", "VEGalleryRemove"},
      dependsOnGroups = "VEGalleryTests_001"
  )
  public void VEGalleryTests_005_Remove() {
    VisualEditorPageObject ve = article.openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    ve.deleteGallery(0);
    ve.verifyGalleries(0);
    ve.publish();
  }
}
