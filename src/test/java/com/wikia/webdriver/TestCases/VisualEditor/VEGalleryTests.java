package com.wikia.webdriver.testcases.visualeditor;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorInsertGalleryDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Robert 'Rochan' Chan
 * @ownership Contribution
 *
 */

public class VEGalleryTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	ArticlePageObject article;
	String articleName;

	@BeforeMethod(alwaysRun = true)
	public void setup_VEPreferred() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.VE_ENABLED_WIKI);
		article = new ArticlePageObject(driver);
		article.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
	}

	@Test(
		groups = {"VEGallery", "VEGalleryTests_001", "VEGalleryAdd"}
	)
	public void VEGalleryTests_001_AddGallery() {
		final int NUM_OF_MEDIAS = 9;
		final int NUM_OF_GALLERIES = 1;

		articleName = PageContent.ARTICLE_NAME_PREFIX + article.getTimeStamp();
		VisualEditorPageObject ve = article.launchVisualEditorWithMainEdit(articleName, wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		VisualEditorInsertGalleryDialog galleryDialog =
			(VisualEditorInsertGalleryDialog) ve.openDialogFromMenu(VisualEditorDataProvider.InsertDialog.GALLERY);
		galleryDialog = galleryDialog.searchMedia("he");
		ve = galleryDialog.addExistingMedia(NUM_OF_MEDIAS);
		ve.verifyGalleries(NUM_OF_GALLERIES);
		ve.verifyMediasInGallery(NUM_OF_MEDIAS);
		VisualEditorSaveChangesDialog saveChangesDialog = ve.clickPublishButton();
		article = saveChangesDialog.savePage();
		article.verifyVEPublishComplete();
	}

	@Test(
			groups = {"VEGallery", "VEGalleryTests_002", "VEGalleryCart"}
	)
	public void VEGalleryTests_002_GalleryCart() {
		final int NUM_OF_MEDIA_TO_REMOVE_FIRST = 1;
		final int NUM_OF_MEDIA_TO_REMOVE_SECOND = 2;
		final int NUM_OF_MEDIA_TO_ADD = 2;
		final int INITIAL_NUM_OF_MEDIA = 7;
		int expectedNumOfMedia = INITIAL_NUM_OF_MEDIA;

		articleName = PageContent.ARTICLE_NAME_PREFIX + article.getTimeStamp();
		VisualEditorPageObject ve = article.launchVisualEditorWithMainEdit(articleName, wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		VisualEditorInsertGalleryDialog galleryDialog =
				(VisualEditorInsertGalleryDialog) ve.openDialogFromMenu(VisualEditorDataProvider.InsertDialog.GALLERY);
		galleryDialog = galleryDialog.searchMedia("he");
		//verify # of cart items  = 9
		galleryDialog.addMediaToCart(INITIAL_NUM_OF_MEDIA);
		galleryDialog.verifyNumOfCartItems(expectedNumOfMedia);
		//verify # of cart items  = 8
		galleryDialog.removeMediaFromCart(NUM_OF_MEDIA_TO_REMOVE_FIRST);
		expectedNumOfMedia = expectedNumOfMedia - NUM_OF_MEDIA_TO_REMOVE_FIRST;
		galleryDialog.verifyNumOfCartItems(expectedNumOfMedia);
		//verify # of cart item = 11
		galleryDialog = galleryDialog.searchMedia("a");
		galleryDialog.addMediaToCart(NUM_OF_MEDIA_TO_ADD);
		expectedNumOfMedia = expectedNumOfMedia + NUM_OF_MEDIA_TO_ADD;
		galleryDialog.verifyNumOfCartItems(expectedNumOfMedia);
		//verify # of cart item = 9
		galleryDialog = galleryDialog.searchMedia("he");
		galleryDialog.removeMediaFromCart(NUM_OF_MEDIA_TO_REMOVE_SECOND);
		expectedNumOfMedia = expectedNumOfMedia - NUM_OF_MEDIA_TO_REMOVE_SECOND;
		galleryDialog.verifyNumOfCartItems(expectedNumOfMedia);
	}

	@Test(
			groups = {"VEGallery", "VEGalleryTests_003", "VEGalleryPreview"}
	)
	public void VEGalleryTests_003_PreviewOnTitle() {

		articleName = PageContent.ARTICLE_NAME_PREFIX + article.getTimeStamp();
		VisualEditorPageObject ve = article.launchVisualEditorWithMainEdit(articleName, wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		VisualEditorInsertGalleryDialog galleryDialog =
				(VisualEditorInsertGalleryDialog) ve.openDialogFromMenu(VisualEditorDataProvider.InsertDialog.GALLERY);
		galleryDialog = galleryDialog.searchMedia("he");
		ve = galleryDialog.clickTitleToPreview(7);
		ve.verifyPreviewImage();
	}

	@Test(
			groups = {"VEGallery", "VEGalleryTests_004", "VEGalleryPreview"}
	)
	public void VEGalleryTests_004_PreviewOnMetadata() {

		articleName = PageContent.ARTICLE_NAME_PREFIX + article.getTimeStamp();
		VisualEditorPageObject ve = article.launchVisualEditorWithMainEdit(articleName, wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		VisualEditorInsertGalleryDialog galleryDialog =
				(VisualEditorInsertGalleryDialog) ve.openDialogFromMenu(VisualEditorDataProvider.InsertDialog.GALLERY);
		galleryDialog = galleryDialog.searchMedia("he");
		ve = galleryDialog.clickMetaDataToPreview(3);
		ve.verifyPreviewImage();
	}
}
