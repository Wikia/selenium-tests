package com.wikia.webdriver.TestCases.VisualEditor;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.ContentPatterns.VEContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.*;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

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
			groups = {"VEGallery", "VEGalleryTests_002", "VEGalleryCart"}
	)
	public void VEGalleryTests_002_GalleryCart() {
		int numOfMedias = 7;
		int numToRemoveFirst = 1;
		int numToRemoveSecond = 2;
		int numToAdd = 2;

		articleName = PageContent.ARTICLE_NAME_PREFIX + article.getTimeStamp();
		VisualEditorPageObject ve = article.launchVisualEditorWithMainEdit(articleName, wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		VisualEditorInsertGalleryDialog galleryDialog =
				(VisualEditorInsertGalleryDialog) ve.openDialogFromMenu(InsertDialog.GALLERY);
		galleryDialog = galleryDialog.searchMedia("he");
		//verify # of cart items  = 9
		galleryDialog.addMediaToCart(numOfMedias);
		galleryDialog.verifyNumOfCartItems(numOfMedias);
		//verify # of cart items  = 8
		galleryDialog.removeMediaFromCart(numToRemoveFirst);
		numOfMedias = numOfMedias - numToRemoveFirst;
		galleryDialog.verifyNumOfCartItems(numOfMedias);
		//verify # of cart item = 11
		galleryDialog = galleryDialog.searchMedia("a");
		galleryDialog.addMediaToCart(numToAdd);
		numOfMedias = numOfMedias + numToAdd;
		galleryDialog.verifyNumOfCartItems(numOfMedias);
		//verify # of cart item = 9
		galleryDialog = galleryDialog.searchMedia("he");
		galleryDialog.removeMediaFromCart(numToRemoveSecond);
		numOfMedias = numOfMedias - numToRemoveSecond;
		galleryDialog.verifyNumOfCartItems(numOfMedias);

	}
}
