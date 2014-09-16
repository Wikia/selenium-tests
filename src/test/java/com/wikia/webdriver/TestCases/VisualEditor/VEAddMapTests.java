package com.wikia.webdriver.TestCases.VisualEditor;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorAddMapDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 * @ownership Contribution
 *
 * VE-1337 - Adding existing map onto article
 * VE-1337 - Checking empty state dialog on wiki with no maps
 *
 */

public class VEAddMapTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String articleName;

	@BeforeMethod(alwaysRun = true)
	public void setup_VEPreferred() {
		base = new WikiBasePageObject(driver);
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
	}

	@Test(
		groups = {"VEAddMap", "VEAddMapTests_001", "VEAddExistingMap"}
	)
	public void VEAddMapTests_001_AddExistingMap() {
		articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorAddMapDialog mapDialog =
			(VisualEditorAddMapDialog) ve.openDialogFromMenu(InsertDialog.MAP);
		VisualEditorPageObject veNew = mapDialog.addExistingMap(0);
		veNew.verifyMapPresent();
		VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
		ArticlePageObject article = save.savePage();
		article.verifyVEPublishComplete();
		article.logOut(wikiURL);
	}

	@Test(
		groups = {"VEAddMap", "VEAddMapTests_002", "VEEmptyMap"}
	)
	public void VEAddMapTests_002_CheckEmptyMapWiki() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.veDisabledTestMainPage);
		articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorAddMapDialog mapDialog =
			(VisualEditorAddMapDialog) ve.openDialogFromMenu(InsertDialog.MAP);
		mapDialog.checkIsEmptyState();
	}
}
