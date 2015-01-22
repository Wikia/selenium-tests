package com.wikia.webdriver.testcases.visualeditor;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorAddMediaDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Robert 'Rochan' Chan
 * @ownership Contribution
 * <p/>
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
		groups = {
			"VEAddVideo", "VEAddExternalVideoTests_001", "VEAddExternalVideo", "VEAddExternalVideoTests_004"
		}
	)
	public void VEAddExternalVideoTests_001_AddNonPremiumVid() {
		articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		VisualEditorAddMediaDialog mediaDialog =
			(VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
		VisualEditorPageObject veNew = mediaDialog.addMediaByURL(VideoContent.NON_PREMIUM_VIDEO_URL);
		veNew.verifyVideo();
		veNew.verifyVEToolBarPresent();
		VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
		ArticlePageObject article = save.savePage();
		article.verifyVEPublishComplete();
	}

	@Test(
		groups = {"VEAddVideo", "VEAddExternalVideoTests_002", "VEAddExternalVideo"}
	)
	public void VEAddExternalVideoTests_002_AddPremiumVid() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		VisualEditorAddMediaDialog mediaDialog =
			(VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
		VisualEditorPageObject veNew = mediaDialog.addMediaByURL(VideoContent.PREMIUM_VIDEO_URL);
		veNew.verifyVideo();
		veNew.verifyVEToolBarPresent();
		VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
		ArticlePageObject article = save.savePage();
		article.verifyVEPublishComplete();
	}

	@Test(
		groups = {"VEAddVideo", "VEAddExternalVideoTests_003", "VEAddExistingVideo"}
	)
	public void VEAddExternalVideoTests_003_AddExistingVid() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		VisualEditorAddMediaDialog mediaDialog =
			(VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
		mediaDialog = mediaDialog.searchMedia("y");
		VisualEditorPageObject veNew = mediaDialog.addExistingMedia(2);
		veNew.verifyVideos(2);
		veNew.verifyVEToolBarPresent();
		VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
		ArticlePageObject article = save.savePage();
		article.verifyVEPublishComplete();
	}


	@Test(
		groups = {"VEAddVideo", "VEAddExternalVideoTests_004", "VEAddExistingVideo"},
		dependsOnGroups = "VEAddExternalVideoTests_001"
	)
	public void VEAddExternalVideoTests_004_RemoveVideoFromArticle() {
		VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
		;
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.selectMediaAndDelete();
		ve.verifyNoVideo();
		VisualEditorSaveChangesDialog save = ve.clickPublishButton();
		ArticlePageObject article = save.savePage();
		article.verifyVEPublishComplete();
	}
}
