package com.wikia.webdriver.TestCases.VisualEditor;

import java.util.ArrayList;

import org.openqa.selenium.Dimension;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Alignment;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Setting;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorAddMediaDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorMediaSettingsDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorReviewChangesDialog;
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
 * VE-1333 Resizing a media with the highlight handle
 * VE-1333 Resizing a media with the advance setting from the media dialog
 * VE-1419 Adjusting media's horizontal alignment
 */

public class VEMediaTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String articleName;
	int numOfVideo = 1;

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
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
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

	@Test(
		groups = {"VEMediaTests", "VEMediaTests_006", "VEResizeVideo"}
	)
	public void VEMediaTests_006_resizeVideoWithSetting() {
		int resizeNumber = 250;

		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorAddMediaDialog mediaDialog =
			(VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
		mediaDialog = mediaDialog.searchMedia("h");
		ve = mediaDialog.addExistingMedia(numOfVideo);
		ve.verifyVideos(numOfVideo);
		Dimension source = ve.getVideoDimension();
		VisualEditorMediaSettingsDialog mediaSettingsDialog = ve.openMediaSettings();
		mediaSettingsDialog.selectSettings(Setting.ADVANCED);
		mediaSettingsDialog.setCustomSize(resizeNumber);
		mediaSettingsDialog.clickApplyChangesButton();
		ve.verifyVideoResized(source);
		VisualEditorSaveChangesDialog save = ve.clickPublishButton();
		ArticlePageObject article = save.savePage();
		article.verifyVEPublishComplete();
		article.logOut(wikiURL);
	}

	@Test(
		groups = {"VEMediaTests", "VEMediaTests_007", "VEAlignMedia"}
	)
	public void VEMediaTests_007_changeAlignment() {
		int numOfMedia = 3;
		ArrayList<String> wikiTexts = new ArrayList<String>();
		wikiTexts.add("|centre");
		wikiTexts.add("|left");

		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
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
		ve.selectMediaByIndex(0);
		mediaSettingsDialog = ve.openMediaSettings();
		mediaSettingsDialog.selectSettings(Setting.ADVANCED);
		mediaSettingsDialog.clickAlignment(Alignment.CENTER);
		ve = mediaSettingsDialog.clickApplyChangesButton();
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
		reviewDialog.verifyAddedDiffs(wikiTexts);
		saveDialog = reviewDialog.clickReturnToSaveFormButton();
		ArticlePageObject article = saveDialog.savePage();
		article.verifyVEPublishComplete();
		article.logOut(wikiURL);
	}
}
