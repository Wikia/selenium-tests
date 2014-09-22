package com.wikia.webdriver.TestCases.VisualEditor;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorAddMediaDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 * @ownership Contribution
 *
 * VE-1134 Adding non-premium (Youtube) video
 * VE-1134 Adding Premium Video with full URL
 * VE-1264 Adding Existing videos to an article
 * VE-1265 Deleting a video from the article
 */

public class VEAddVideoTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String articleName;

	@BeforeMethod(alwaysRun = true)
	public void setup_VEPreferred() {
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
	}

	@Test(
		groups = {"VEAddVideo", "VEAddExternalVideoTests_001", "VEAddExternalVideo"}
	)
	public void VEAddExternalVideoTests_001_AddNonPremiumVid() {
		articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		VisualEditorAddMediaDialog mediaDialog =
			(VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
		VisualEditorPageObject veNew = mediaDialog.addMediaByURL(VideoContent.nonPremiumVideoURL);
		veNew.verifyVideo();
		veNew.verifyVEToolBarPresent();
		VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
		article = save.savePage();
		article.verifyVEPublishComplete();
		article.logOut(wikiURL);
	}

	@Test(
		groups = {"VEAddVideo", "VEAddExternalVideoTests_002", "VEAddExternalVideo"}
	)
	public void VEAddExternalVideoTests_002_AddPremiumVid() {
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		VisualEditorAddMediaDialog mediaDialog =
			(VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
		VisualEditorPageObject veNew = mediaDialog.addMediaByURL(VideoContent.premiumVideoURL);
		veNew.verifyVideo();
		veNew.verifyVEToolBarPresent();
		VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
		article = save.savePage();
		article.verifyVEPublishComplete();
		article.logOut(wikiURL);
	}

	@Test(
		groups = {"VEAddVideo", "VEAddExternalVideoTests_003", "VEAddExistingVideo"}
	)
	public void VEAddExternalVideoTests_003_AddExistingVid() {
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		VisualEditorAddMediaDialog mediaDialog =
			(VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
		mediaDialog = mediaDialog.searchMedia("y");
		VisualEditorPageObject veNew = mediaDialog.addExistingMedia(2);
		veNew.verifyVideos(2);
		veNew.verifyVEToolBarPresent();
		VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
		article = save.savePage();
		article.verifyVEPublishComplete();
		article.logOut(wikiURL);
	}


	@Test(
		groups = {"VEAddVideo", "VEAddExternalVideoTests_004", "VEAddExistingVideo"},
		dependsOnGroups = "VEAddExternalVideoTests_001"
	)
	public void VEAddExternalVideoTests_004_RemoveVideoFromArticle() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.selectMediaAndDelete();
		ve.verifyNoVideo();
		VisualEditorSaveChangesDialog save = ve.clickPublishButton();
		article = save.savePage();
		article.verifyVEPublishComplete();
		article.logOut(wikiURL);
	}
}
