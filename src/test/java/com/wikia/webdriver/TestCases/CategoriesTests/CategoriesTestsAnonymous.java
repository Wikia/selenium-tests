package com.wikia.webdriver.TestCases.CategoriesTests;


import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiCategoryPageObject;
import org.testng.annotations.Test;

public class CategoriesTestsAnonymous extends TestTemplate {

	String categoryName;

	/*
	 * TestCase001 Open random wiki page as anonymous user add category Verify
	 * category is added
	 */
	@Test(groups = {"CategoriesTestsAnonymous_001", "CategoriesTestsAnonymous"})
	public void CategoriesTestsAnonymous_001_addCategoryNotEditMode() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		categoryName = PageContent.categoryNamePrefix + article.getTimeStamp();
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
		WikiBasePageObject wiki =  new WikiBasePageObject(driver);
		wiki.openWikiPage();
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		categoryName = PageContent.categoryNamePrefix + article.getTimeStamp();
		WikiArticleEditMode articleEdit = article.clickEditButton();
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
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		categoryName = PageContent.categoryNamePrefix + article.getTimeStamp();
		WikiArticleEditMode articleEdit = article.edit();
		articleEdit.categories_addCategoryEditMode(categoryName);
		// adding the below category will save 1 minute of time execution time.
		// Reason: it assures that list of categories will not be empty, what
		// eliminates searching for empty lists during verification
		articleEdit.categories_addCategoryEditMode(categoryName + 2);
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName);
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName + 2);
		articleEdit.clickOnPublishButton();
		article.categories_verifyCategoryPresent(categoryName);
		articleEdit = article.clickEditButton();
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
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		categoryName = PageContent.categoryNamePrefix + article.getTimeStamp();
		String articleName = article.getPageName();
		article.categories_clickAddCategory();
		article.categories_typeCategoryName(categoryName);
		article.categories_clickOnSave();
		article.categories_verifyCategoryPresent(categoryName);
		article.refreshPage();
		WikiCategoryPageObject categoryPage = article.openCategoryPage(categoryName);
		categoryPage.verifyCategoryContainsPage(articleName);
	}

	/*
	 * TestCase006 Open random wiki page as anonymous user edit it and add
	 * category from source mode Verify category is properly added in visual
	 */
	@Test(groups = { "CategoriesTestsAnonymous_006", "CategoriesTestsAnonymous" })
	public void CategoriesTestsAnonymous_006_addCategorySourceModeTransitionToVisual() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		categoryName = PageContent.categoryNamePrefix + article.getTimeStamp();
		WikiArticleEditMode articleEdit = article.edit();
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
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		categoryName = PageContent.categoryNamePrefix + article.getTimeStamp();
		WikiArticleEditMode articleEdit = article.navigateToEditPage();
		articleEdit.categories_addCategoryEditMode(categoryName);
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName);
		articleEdit.clickOnSourceButton();
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName);
		article = articleEdit.clickOnPublishButton();
		article.categories_verifyCategoryPresent(categoryName);
	}
}
