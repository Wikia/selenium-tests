package com.wikia.webdriver.TestCases.CategoriesTests;

import java.awt.PageAttributes;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.XSSContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiCategoryPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class CategoriesTestsAnonymous extends TestTemplate {

	String categoryName;
	
	/*
	 * TestCase001 Open random wiki page as anonymous user add category Verify
	 * category is added
	 */
	@Test(groups = { "CategoriesTestsAnonymous_001", "CategoriesTestsAnonymous" })
	public void CategoriesTestsAnonymous_001_addCategoryNotEditMode() {

		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		categoryName = PageContent.categoryNamePrefix + article.getTimeStamp();
		article.openRandomArticleByUrl();
		article.categories_clickAddCategory();
		article.categories_typeCategoryName(categoryName);
		article.categories_clickOnSave();
		article.categories_verifyCategoryPresent(categoryName);

	}

	/*
	 * TestCase002 Open random wiki page as anonymous user edit it and add
	 * category Verify category is added
	 */
	@Test(groups = { "CategoriesTestsAnonymous_002", "CategoriesTestsAnonymous" })
	public void CategoriesTestsAnonymous_002_addCategoryEditMode() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		categoryName = PageContent.categoryNamePrefix + article.getTimeStamp();
		article.openRandomArticleByUrl();
		WikiArticleEditMode articleEdit = article.clickEditButton("");
		articleEdit.categories_addCategoryEditMode(categoryName);
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName);
		article = articleEdit.clickOnPublishButton();
		article.categories_verifyCategoryPresent(categoryName);
	}

	/*
	 * TestCase003 Open random wiki page as anonymous user edit it and add
	 * category, then remove it Verify category is removed
	 */
	@Test(groups = { "CategoriesTestsAnonymous_003", "CategoriesTestsAnonymous" })
	public void CategoriesTestsAnonymous_003_removeCategoryEditMode() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		categoryName = PageContent.categoryNamePrefix + article.getTimeStamp();
		article.openWikiPage();
		WikiArticleEditMode articleEdit = article.createNewDefaultArticle();
		articleEdit.categories_addCategoryEditMode(categoryName);
		// adding the below category will save 1 minute of time execution time.
		// Reason: it assures that list of categories will not be empty, what
		// eliminates searching for empty lists during verification
		articleEdit.categories_addCategoryEditMode(categoryName + 2);
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName);
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName + 2);
		articleEdit.clickOnPublishButton();
		article.categories_verifyCategoryPresent(categoryName);
		articleEdit = article.clickEditButton("");
		articleEdit.categories_removeCategoryEditMode(categoryName);
		articleEdit.categories_verifyCategoryRemovedEditMode(categoryName);
		articleEdit.clickOnPublishButton();
		article.categories_verifyCategoryRemoved(categoryName);

	}

	/*
	 * TestCase004 Open random wiki page as anonymous user edit it and add
	 * category, then publish it Open category page and verify it is valid
	 */
	@Test(groups = { "CategoriesTestsAnonymous_004", "CategoriesTestsAnonymous" })
	public void CategoriesTestsAnonymous_004_verifyCategoryPage() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		categoryName = PageContent.categoryNamePrefix + article.getTimeStamp();
		article.openRandomArticleByUrl();
		String articleName = article.getArticleNameFromURL();
		article.categories_clickAddCategory();
		article.categories_typeCategoryName(categoryName);
		article.categories_clickOnSave();
		article.categories_verifyCategoryPresent(categoryName);
		article.refreshPage();
		WikiCategoryPageObject categoryPage = article.openCategoryPage(categoryName);
		categoryPage.verifyCategoryContainsPage(articleName);
	}

	/*
	 * TestCase005 Open random wiki page as anonymous user add category from
	 * suggestions Verify category is added
	 */
//	@Test(groups = { "CategoriesTestsAnonymous_005", "CategoriesTestsAnonymous" })
	public void CategoriesTestsAnonymous_005_addSuggestedCategoryEditMode() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openRandomArticleByUrl();
		WikiArticleEditMode articleEdit = article.navigateToEditPage();
		// remmemeber name of added category
		categoryName = articleEdit
				.categories_addSuggestedCategoryEditMode("t");
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName);
		article = articleEdit.clickOnPublishButton();
		article.categories_verifyCategoryPresent(categoryName);
	}

	/*
	 * TestCase006 Open random wiki page as anonymous user edit it and add
	 * category from source mode Verify category is properly added in visual
	 */
	@Test(groups = { "CategoriesTestsAnonymous_006", "CategoriesTestsAnonymous" })
	public void CategoriesTestsAnonymous_006_addCategorySourceModeTransitionToVisual() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		categoryName = PageContent.categoryNamePrefix + article.getTimeStamp();
		article.openRandomArticleByUrl();
		WikiArticleEditMode articleEdit = article.navigateToEditPage();
		articleEdit.clickOnSourceButton();
		articleEdit.categories_addCategoryEditMode(categoryName);
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName);
		articleEdit.clickOnVisualButton();
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName);
		article = articleEdit.clickOnPublishButton();
		article.categories_verifyCategoryPresent(categoryName);
	}

	/*
	 * TestCase007 Open random wiki page as anonymous user edit it and add
	 * category from visaul mode Verify category is properly added in source
	 */
	@Test(groups = { "CategoriesTestsAnonymous_007", "CategoriesTestsAnonymous" })
	public void CategoriesTestsAnonymous_007_addCategoryVisualModeTransitionToSource() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		categoryName = PageContent.categoryNamePrefix + article.getTimeStamp();
		article.openRandomArticleByUrl();
		WikiArticleEditMode articleEdit = article.navigateToEditPage();
		articleEdit.categories_addCategoryEditMode(categoryName);
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName);
		articleEdit.clickOnSourceButton();
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName);
		article = articleEdit.clickOnPublishButton();
		article.categories_verifyCategoryPresent(categoryName);
	}
}
