package com.wikia.webdriver.TestCases.VisualEditor;

import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.CategoryResultType;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorOptionsDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorReviewChangesDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 * @ownership Contribution
 *
 * VE-1407 Adding category to an article
 * VE-1408 Deleting category from an article
 * VE-1411 New category suggestion when adding category to an article
 * VE-1411 Matching categories suggestion when adding category to an article
 */

public class VECategoryTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String articleName, testCategory, categorySearchStr;
	ArrayList<String> categoryWikiTexts;

	@BeforeClass(alwaysRun = true)
	public void setup() {
		testCategory = "ca";
		categorySearchStr = "abcd";
		categoryWikiTexts = new ArrayList<>();
		categoryWikiTexts.add("[[Category:Ca]]");
		base = new WikiBasePageObject(driver);
		articleName = PageContent.articleNamePrefix + base.getTimeStamp();
	}

	@Test(
		groups = {"VECategoryTests", "VECategoryTests_001", "VEAddCategory"}
	)
	public void VECategoryTests_001_AddNewCategory() {
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorOptionsDialog optionsDialog =
			(VisualEditorOptionsDialog) ve.openDialogFromMenu(InsertDialog.CATEGORIES);
		optionsDialog.addCategory(testCategory);
		ve = optionsDialog.clickApplyChangesButton();
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
		reviewDialog.verifyAddedDiffs(categoryWikiTexts);
		saveDialog = reviewDialog.clickReturnToSaveFormButton();
		ArticlePageObject article = saveDialog.savePage();
		article.verifyVEPublishComplete();
	}

	@Test(
		groups = {"VECategoryTests", "VECategoryTests_002", "VERemoveCategory"},
		dependsOnGroups = "VECategoryTests_001"
	)
	public void VECategoryTests_002_RemoveCategory() {
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorOptionsDialog optionsDialog =
			(VisualEditorOptionsDialog) ve.openDialogFromMenu(InsertDialog.CATEGORIES);
		optionsDialog.removeCategory(testCategory);
		ve = optionsDialog.clickApplyChangesButton();
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
		reviewDialog.verifyDeletedDiffs(categoryWikiTexts);
		saveDialog = reviewDialog.clickReturnToSaveFormButton();
		ArticlePageObject article = saveDialog.savePage();
		article.verifyVEPublishComplete();
	}

	@Test(
		groups = {"VECategoryTests", "VECategoryTests_003", "VEAddCategory"}
	)
	public void VECategoryTests_003_NewCategorySuggestions() {
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorOptionsDialog optionsDialog =
			(VisualEditorOptionsDialog) ve.openDialogFromMenu(InsertDialog.CATEGORIES);
		optionsDialog.verifyLinkSuggestions(categorySearchStr, CategoryResultType.NEW);
	}

	@Test(
		groups = {"VECategoryTests", "VECategoryTests_004", "VEAddCategory"}
	)
	public void VECategoryTests_004_MatchingCategorySuggestions() {
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorOptionsDialog optionsDialog =
			(VisualEditorOptionsDialog) ve.openDialogFromMenu(InsertDialog.CATEGORIES);
		optionsDialog.verifyLinkSuggestions(categorySearchStr, CategoryResultType.MATCHING);
	}
}
