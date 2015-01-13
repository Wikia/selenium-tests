package com.wikia.webdriver.testcases.visualeditor;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VEContent;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorEditTemplateDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorInsertTemplateDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorReviewChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

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
		articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
		VisualEditorInsertTemplateDialog templateDialog =
			(VisualEditorInsertTemplateDialog) ve.openDialogFromMenu(InsertDialog.TEMPLATE);
		//1 character search 'a', not matching article name, no result
		templateDialog.typeInSearchInput(VEContent.TEMPLATE_SEARCH_1CHAR_NOMATCH);
		templateDialog.verifyNoResultTemplate();
		//2 characters search 'ab', not matching article name, no result
		templateDialog.clearSearchInput();
		templateDialog.typeInSearchInput(VEContent.TEMPLATE_SEARCH_2CHARS_NOMATCH);
		templateDialog.verifyNoResultTemplate();
		//3 characters search 'per', not matching article name, 2 results on template name
		templateDialog.clearSearchInput();
		templateDialog.typeInSearchInput(VEContent.TEMPLATE_SEARCH_3CHARS_PARTIALMATCH);
		templateDialog.verifyIsResultTemplate();
		//2 characters search 'ar', matching article name, 3 results on the article
		templateDialog.clearSearchInput();
		templateDialog.typeInSearchInput(VEContent.TEMPLATE_SEARCH_MATCH_ARTICLE);
		templateDialog.verifyIsResultTemplate();
	}

	@Test(
		groups = {"VETemplate", "VETemplateTests_002", "VETemplateSuggestion"}
	)
	public void VETemplateTests_002_SuggestedTemplate() {
		articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
		VisualEditorInsertTemplateDialog templateDialog =
			(VisualEditorInsertTemplateDialog) ve.openDialogFromMenu(InsertDialog.TEMPLATE);
		templateDialog.verifyNoResultTemplate();
		templateDialog.verifyIsSuggestedTemplate();
	}

	@Test(
		groups = {"VETemplate", "VETemplateTests_003", "VEAddTemplate"}
	)
	public void VETemplateTests_003_AddTemplates() {
		articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
		int numBlockTransclusion = ve.getNumberOfBlockTransclusion();
		int numInlineTransclusion = ve.getNumberOfInlineTransclusion();
		VisualEditorInsertTemplateDialog templateDialog =
			(VisualEditorInsertTemplateDialog) ve.openDialogFromMenu(InsertDialog.TEMPLATE);
		VisualEditorEditTemplateDialog editTemplateDialog = templateDialog.selectSuggestedTemplate(0);
		ve = editTemplateDialog.clickDone();
		ve.verifyNumberOfBlockTransclusion(numBlockTransclusion);
		ve.verifyNumberOfInlineTransclusion(++numInlineTransclusion);
		templateDialog =
			(VisualEditorInsertTemplateDialog) ve.openDialogFromMenu(InsertDialog.TEMPLATE);
		editTemplateDialog = templateDialog.selectResultTemplate(VEContent.TEMPLATE_SEARCH_3CHARS_PARTIALMATCH, 1);
		ve = editTemplateDialog.closeDialog();
		ve.verifyNumberOfBlockTransclusion(++numBlockTransclusion);
		ve.verifyNumberOfInlineTransclusion(numInlineTransclusion);
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		ArticlePageObject article = saveDialog.savePage();
		article.verifyVEPublishComplete();
		article.logOut(wikiURL);
	}

	@Test(
		groups = {"VETemplate", "VETemplateTests_004", "VEAddTemplate", "VETemplateTests_005", "VETemplateTests_006"}
	)
	public void VETemplateTests_004_CheckBlockedTransclusion() {
		articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
		String selectText = PageContent.ARTICLE_TEXT.substring(12, 13);
		int numBlockTransclusion = ve.getNumberOfBlockTransclusion();
		int numInlineTransclusion = ve.getNumberOfInlineTransclusion();
		ve.typeTextArea(PageContent.ARTICLE_TEXT);
		ve.selectText(selectText);
		VisualEditorInsertTemplateDialog templateDialog =
			(VisualEditorInsertTemplateDialog) ve.openDialogFromMenu(InsertDialog.TEMPLATE);
		VisualEditorEditTemplateDialog editTemplateDialog =
			templateDialog.selectResultTemplate(VEContent.TEMPLATE_SEARCH_EXACTMATCH, 0);
		ve = editTemplateDialog.clickDone();
		ve.verifyNumberOfBlockTransclusion(++numBlockTransclusion);
		ve.verifyNumberOfInlineTransclusion(numInlineTransclusion);
		templateDialog =
			(VisualEditorInsertTemplateDialog) ve.openDialogFromMenu(InsertDialog.TEMPLATE);
		editTemplateDialog = templateDialog.selectResultTemplate(VEContent.TEMPLATE_SEARCH_EXACTMATCH, 0);
		ve = editTemplateDialog.clickDone();
		ve.verifyNumberOfBlockTransclusion(++numBlockTransclusion);
		ve.verifyNumberOfInlineTransclusion(numInlineTransclusion);
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		ArticlePageObject article = saveDialog.savePage();
		article.verifyVEPublishComplete();
		article.logOut(wikiURL);
	}

	@Test(
		groups = {"VETemplate", "VETemplateTests_005", "VEDeleteTemplate"},
		dependsOnGroups = "VETemplateTests_004"
	)
	public void VETemplateTests_005_DeleteTemplates() {
		VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
		int numBlockTransclusion = ve.getNumberOfBlockTransclusion();
		int numInlineTransclusion = ve.getNumberOfInlineTransclusion();
		ve.deleteBlockTransclusion(1);
		ve.verifyNumberOfBlockTransclusion(--numBlockTransclusion);
		ve.verifyNumberOfInlineTransclusion(numInlineTransclusion);
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		ArticlePageObject article = saveDialog.savePage();
		article.verifyVEPublishComplete();
		article.logOut(wikiURL);
	}

	@Test(
		groups = {"VETemplate", "VETemplateTests_006", "VEAddTemplate"},
		dependsOnGroups = "VETemplateTests_004"
	)
	public void VETemplateTests_006_CheckBlockedTransclusion() {
		List<String> templateWikiTexts = new ArrayList<>();
		templateWikiTexts.add(VEContent.TEMPLATE_WIKITEXT);
		VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
		ve.clickBlockTransclusion(0);
		VisualEditorEditTemplateDialog editTemplateDialog = ve.openEditTemplateDialog();
		editTemplateDialog.typeInParam(VEContent.TEMPLATE_PARAM_LABEL1, VEContent.TEMPLATE_PARAM_VALUE1);
		editTemplateDialog.typeInParam(VEContent.TEMPLATE_PARAM_LABEL2, VEContent.TEMPLATE_PARAM_VALUE2);
		ve = editTemplateDialog.clickDone();
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
		reviewDialog.verifyAddedDiffs(templateWikiTexts);
		saveDialog = reviewDialog.clickReturnToSaveFormButton();
		ArticlePageObject article = saveDialog.savePage();
		article.verifyVEPublishComplete();
		article.logOut(wikiURL);
	}
}
