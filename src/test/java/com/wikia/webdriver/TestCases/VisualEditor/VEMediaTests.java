package com.wikia.webdriver.TestCases.VisualEditor;

import org.openqa.selenium.Point;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorAddMediaDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorMediaSettingsDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 *
 * VE-1335 Previewing Youtube video from VE's media dialog
 * VE-1335 Previewing image from VE's media dialog
 * VE-1336 Uploading an image
 * VE-1334 Adding caption to a media
 */

public class VEMediaTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String articleName;

	@BeforeMethod(alwaysRun = true)
	public void setup_VEPreferred() {
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
		articleName = PageContent.articleNamePrefix + base.getTimeStamp();
	}

	@Test(
		groups = {"VEMediaTests", "VEMediaTests_001", "VEPreviewVideo"}
	)
	public void VEMediaTests_001_previewVideo() {
		String mediaTitle = "Short film directed by Guy Ritchie starring David Beckham - H&M Spring 2013";
		String providerName = "youtube";

		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorAddMediaDialog mediaDialog =
			(VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
		mediaDialog = mediaDialog.searchMedia("h");
		ve = mediaDialog.previewExistingMediaByTitle(mediaTitle);
		ve.verifyPreviewVideoPlay(providerName);
	}

	@Test(
		groups = {"VEMediaTests", "VEMediaTests_002", "VEPreviewImage"}
	)
	public void VEMediaTests_002_previewImage() {
		String mediaTitle = "Thomas Wright 1792 - 1849";

		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorAddMediaDialog mediaDialog =
			(VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
		mediaDialog = mediaDialog.searchMedia("h");
		ve = mediaDialog.previewExistingMediaByTitle(mediaTitle);
		ve.verifyPreviewImage();
	}

	@Test(
		groups = {"VEMediaTests", "VEMediaTests_003", "VEUploadImage"}
	)
	public void VEMediaTests_003_AddImageFromUpload() {
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorAddMediaDialog mediaDialog =
			(VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
		ve = mediaDialog.uploadImage(PageContent.file);
		VisualEditorSaveChangesDialog save = ve.clickPublishButton();
		ArticlePageObject article = save.savePage();
		article.verifyVEPublishComplete();
		article.logOut(wikiURL);
	}

	@Test(
		groups = {"VEMediaTests", "VEMediaTests_004", "VEPreviewVideo"}
	)
	public void VEMediaTests_004_editCaption() {
		String captionText = "test123";

		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorAddMediaDialog mediaDialog =
			(VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
		mediaDialog = mediaDialog.searchMedia("h");
		ve = mediaDialog.addExistingMedia(1);
		ve.verifyVideo();
		VisualEditorMediaSettingsDialog mediaSettingsDialog = ve.openMediaSettings();
		mediaSettingsDialog.typeCaption(captionText);
		ve = mediaSettingsDialog.clickApplyChangesButton();
		ve.verifyVideoCaption(captionText);
	}

	@Test(
		groups = {"VEMediaTests", "VEMediaTests_005", "VEResizeVideo"}
	)
	public void VEMediaTests_005_resizeVideo() {
		int numOfVideo = 1;

		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorAddMediaDialog mediaDialog =
			(VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
		mediaDialog = mediaDialog.searchMedia("h");
		ve = mediaDialog.addExistingMedia(numOfVideo);
		ve.verifyVideos(numOfVideo);
		Point source = ve.getVideoSWHandle();
		ve.randomResizeOnMedia();
		ve.verifyVideoSWHandleMoved(source);
	}
}
