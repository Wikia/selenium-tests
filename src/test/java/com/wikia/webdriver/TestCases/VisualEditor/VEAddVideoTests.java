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
 *
 * VE-1134 Adding Youtube Video
 * VE-1134 Adding Premium Video with full URL
 *
 */

public class VEAddVideoTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;

	@BeforeMethod(alwaysRun = true)
	public void setup_VEPreferred() {
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
	}

	@Test(
		groups = {"VEAddVideo", "VEAddExternalVideoTests_001", "VEAddExternalVideo"}
	)
	public void VEAddExternalVideoTests_001_AddNonPremiumVid() {
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		VisualEditorAddMediaDialog mediaDialog =
			(VisualEditorAddMediaDialog) ve.selectInsertToOpenDialog(InsertDialog.MEDIA);
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
			(VisualEditorAddMediaDialog) ve.selectInsertToOpenDialog(InsertDialog.MEDIA);
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
			(VisualEditorAddMediaDialog) ve.selectInsertToOpenDialog(InsertDialog.MEDIA);
		mediaDialog = mediaDialog.searchMedia("y");
		VisualEditorPageObject veNew = mediaDialog.addExistingMedia(2);
		veNew.verifyVideos(2);
		veNew.verifyVEToolBarPresent();
		VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
		article = save.savePage();
		article.verifyVEPublishComplete();
		article.logOut(wikiURL);
	}
}
