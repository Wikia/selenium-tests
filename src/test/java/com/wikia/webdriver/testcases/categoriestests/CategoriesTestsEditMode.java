/**
 *
 */
package com.wikia.webdriver.testcases.categoriestests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.editcategory.EditCategoryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class CategoriesTestsEditMode extends NewTestTemplate {

	/*
	 * Add category to article edit mode as anon
	 * Add category to article edit mode from suggestion list as anon
	 * Add category to article edit mode as user
	 * Add category to article edit mode from suggestion list as user
	 * Add category to article edit mode as anon edit, delete
	 */

	Credentials credentials = config.getCredentials();

	@Test(groups = {"CategoriesTestsArticleEdit_001", "CategoriesTestsArticleEditMode"})
	public void CategoriesTestsArticleEdit_001_anonEdit() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		VisualEditModePageObject visual = base.navigateToArticleEditPageCK(wikiURL, articleName);
		String categoryName = PageContent.CATEGORY_NAME_PREFIX + visual.getTimeStamp();
		visual.typeCategoryName(categoryName);
		visual.submitCategory();
		visual.verifyCategoryPresent(categoryName);
		EditCategoryComponentObject editCategory = visual.editCategory(categoryName);
		categoryName = PageContent.CATEGORY_NAME_PREFIX + visual.getTimeStamp();
		editCategory.editCategoryName(categoryName);
		visual.verifyCategoryPresent(categoryName);
	}

	@Test(groups = {"CategoriesTestsArticleEdit_002", "CategoriesTestsArticleEditMode"})
	public void CategoriesTestsArticleEdit_002_anonDelete() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		VisualEditModePageObject visual = base.navigateToArticleEditPageCK(wikiURL, articleName);
		String categoryName = PageContent.CATEGORY_NAME_PREFIX + visual.getTimeStamp();
		visual.typeCategoryName(categoryName);
		visual.submitCategory();
		visual.verifyCategoryPresent(categoryName);
		visual.removeCategory(categoryName);
		visual.verifyCategoryNotPresent(categoryName);
	}

	@Test(groups = {"CategoriesTestsArticleEdit_003", "CategoriesTestsArticleEditMode"})
	public void CategoriesTestsArticleEdit_003_anonSuggestions() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		VisualEditModePageObject visual = base.navigateToArticleEditPageCK(wikiURL, articleName);
		visual.typeCategoryName(PageContent.CATEGORY_NAME_PREFIX);
		visual.triggerCategorySuggestions();
		String categoryName = visual.selectCategorySuggestions(1);
		visual.verifyCategoryPresent(categoryName);
	}

	@Test(groups = {"CategoriesTestsArticleEdit_004", "CategoriesTestsArticleEditMode"})
	public void CategoriesTestsArticleEdit_004_user() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		VisualEditModePageObject visual = base.navigateToArticleEditPageCK(wikiURL, articleName);
		String categoryName = PageContent.CATEGORY_NAME_PREFIX + visual.getTimeStamp();
		visual.typeCategoryName(categoryName);
		visual.submitCategory();
		visual.verifyCategoryPresent(categoryName);
	}

	@Test(groups = {"CategoriesTestsArticleEdit_005", "CategoriesTestsArticleEditMode"})
	public void CategoriesTestsArticleEdit_005_userSuggestions() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		VisualEditModePageObject visual = base.navigateToArticleEditPageCK(wikiURL, articleName);
		visual.typeCategoryName(PageContent.CATEGORY_NAME_PREFIX);
		visual.triggerCategorySuggestions();
		String categoryName = visual.selectCategorySuggestions(1);
		visual.verifyCategoryPresent(categoryName);
	}
}
