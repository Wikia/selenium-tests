package com.wikia.webdriver.TestCases.VisualEditor;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.*;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Robert 'Rochan' Chan
 * @ownership Contribution
 *
 * VE-1413 Verify search suggestion on templates
 * VE-1413 Verify suggested templates appear by default
 * VE-1412 Verify adding template with params and template with no param
 * VE-1412 Verify adding template to a middle of paragraph or to a block node would insert template as block node
 * VE-1414 Verify deleting template from an article
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
			(VisualEditorInsertGalleryDialog) ve.openDialogFromMenu(InsertDialog.GALLERY);
		galleryDialog = galleryDialog.searchMedia("he");
		ve = galleryDialog.addExistingMedia(NUM_OF_MEDIAS);
		ve.verifyGalleries(NUM_OF_GALLERIES);
		ve.verifyMediasInGallery(NUM_OF_MEDIAS);
		VisualEditorSaveChangesDialog saveChangesDialog = ve.clickPublishButton();
		article = saveChangesDialog.savePage();
		article.verifyVEPublishComplete();
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
				(VisualEditorInsertGalleryDialog) ve.openDialogFromMenu(InsertDialog.GALLERY);
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
				(VisualEditorInsertGalleryDialog) ve.openDialogFromMenu(InsertDialog.GALLERY);
		galleryDialog = galleryDialog.searchMedia("he");
		ve = galleryDialog.clickMetaDataToPreview(3);
		ve.verifyPreviewImage();
	}
}
