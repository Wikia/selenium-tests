package com.wikia.webdriver.TestCases.VisualEditor;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VEContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorEditTemplateDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorInsertTemplateDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 * @ownership Contribution
 *
 * VE-1413 Verify search suggestion on templates
 * VE-1413 Verify suggested templates appear by default
 * VE-1412 Verify adding template with params and template with no param
 * VE-1412 Verify adding template to a middle of paragraph or to a block node would insert template as block node
 */

public class VETemplateTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String articleName, mapID;
	InteractiveMapPageObject createdMap;

	@BeforeMethod(alwaysRun = true)
	public void setup_VEPreferred() {
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
	}

	@Test(
		groups = {"VETemplate", "VETemplateTests_001", "VETemplateSearch"}
	)
	public void VETemplateTests_001_SearchTemplate() {
		articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorInsertTemplateDialog templateDialog =
			(VisualEditorInsertTemplateDialog) ve.openDialogFromMenu(InsertDialog.TEMPLATE);
		//1 character search 'a', not matching article name, no result
		templateDialog.typeInSearchInput(VEContent.templateSearchStr1);
		templateDialog.verifyNoResultTemplate();
		//2 characters search 'ab', not matching article name, no result
		templateDialog.clearSearchInput();
		templateDialog.typeInSearchInput(VEContent.templateSearchStr2);
		templateDialog.verifyNoResultTemplate();
		//3 characters search 'per', not matching article name, 2 results on template name
		templateDialog.clearSearchInput();
		templateDialog.typeInSearchInput(VEContent.templateSearchStr3);
		templateDialog.verifyIsResultTemplate();
		//2 characters search 'ar', matching article name, 3 results on the article
		templateDialog.clearSearchInput();
		templateDialog.typeInSearchInput(VEContent.templateSearchStr4);
		templateDialog.verifyIsResultTemplate();
	}

	@Test(
		groups = {"VETemplate", "VETemplateTests_002", "VETemplateSuggestion"}
	)
	public void VETemplateTests_002_SuggestedTemplate() {
		articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorInsertTemplateDialog templateDialog =
			(VisualEditorInsertTemplateDialog) ve.openDialogFromMenu(InsertDialog.TEMPLATE);
		templateDialog.verifyNoResultTemplate();
		templateDialog.verifyIsSuggestedTemplate();
	}

	@Test(
		groups = {"VETemplate", "VETemplateTests_003", "VEAddTemplate"}
	)
	public void VETemplateTests_003_AddTemplates() {
		articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorInsertTemplateDialog templateDialog =
			(VisualEditorInsertTemplateDialog) ve.openDialogFromMenu(InsertDialog.TEMPLATE);
		VisualEditorEditTemplateDialog editTemplateDialog = templateDialog.selectResultTemplate("per", 0);
		ve = editTemplateDialog.clickDone();
		templateDialog =
			(VisualEditorInsertTemplateDialog) ve.openDialogFromMenu(InsertDialog.TEMPLATE);
		editTemplateDialog = templateDialog.selectResultTemplate("per", 1);
		ve = editTemplateDialog.closeDialog();
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		ArticlePageObject article = saveDialog.savePage();
		article.verifyVEPublishComplete();
		article.logOut(wikiURL);
	}

	@Test(
		groups = {"VETemplate", "VETemplateTests_004", "VEAddTemplate"}
	)
	public void VETemplateTests_004_CheckBlockedTransclusion() {
		articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		String selectText = PageContent.articleText.substring(12, 13);
		int numBlockTransclusion = ve.getNumberOfBlockTransclusion();
		int numInlineTransclusion = ve.getNumberOfInlineTransclusion();
		ve.typeTextArea(PageContent.articleText);
		ve.selectText(selectText);
		VisualEditorInsertTemplateDialog templateDialog =
			(VisualEditorInsertTemplateDialog) ve.openDialogFromMenu(InsertDialog.TEMPLATE);
		VisualEditorEditTemplateDialog editTemplateDialog = templateDialog.selectResultTemplate("book", 0);
		ve = editTemplateDialog.clickDone();
		ve.verifyNumberOfBlockTransclusion(++numBlockTransclusion);
		ve.verifyNumberOfInlineTransclusion(numInlineTransclusion);
		templateDialog =
			(VisualEditorInsertTemplateDialog) ve.openDialogFromMenu(InsertDialog.TEMPLATE);
		editTemplateDialog = templateDialog.selectResultTemplate("book", 0);
		ve = editTemplateDialog.clickDone();
		ve.verifyNumberOfBlockTransclusion(++numBlockTransclusion);
		ve.verifyNumberOfInlineTransclusion(numInlineTransclusion);
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		ArticlePageObject article = saveDialog.savePage();
		article.verifyVEPublishComplete();
		article.logOut(wikiURL);
	}
}
