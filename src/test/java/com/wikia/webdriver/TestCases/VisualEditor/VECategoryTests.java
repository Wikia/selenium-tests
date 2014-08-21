package com.wikia.webdriver.TestCases.VisualEditor;

import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
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
 *
 */

public class VECategoryTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String articleName;
	String testCategory;
	ArrayList<String> categoryWikiTexts;

	@BeforeClass(alwaysRun = true)
	public void setup() {
		testCategory = "ca";
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
}
