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
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class CategoriesArticleTests extends NewTestTemplate {

	/*
	 * Add category to article as anon
	 * Add category to article from suggestion list as anon
	 * Add category to article as user
	 * Add category to article from suggestion list as user
	 * Add category to article as anon edit, delete
	 */

	Credentials credentials = config.getCredentials();

	@Test(groups = {"CategoriesTestsArticle_001", "CategoriesTestsArticle", "Smoke2"})
	public void CategoriesTestsArticle_001_anon() {
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openRandomArticle(wikiURL);
		String categoryName = PageContent.CATEGORY_NAME_PREFIX + article.getTimeStamp();
		article.addCategory(categoryName);
		article.submitCategory();
		article.verifyCategoryPresent(categoryName);
	}

	@Test(groups = {"CategoriesTestsArticle_002", "CategoriesTestsArticle"})
	public void CategoriesTestsArticle_002_anonSuggestions() {
		VisualEditModePageObject visualEditMode = new VisualEditModePageObject(driver);
		visualEditMode.navigateToArticleEditPageCK(
				wikiURL,
				PageContent.ARTICLE_NAME_PREFIX + visualEditMode.getTimeStamp()
		);
		visualEditMode.addContent(PageContent.ARTICLE_TEXT);
		ArticlePageObject article = visualEditMode.submitArticle();
		String desiredCategory = article.addCategorySuggestions(PageContent.CATEGORY_NAME_PREFIX, 2);
		article.submitCategory();
		article.verifyCategoryPresent(desiredCategory);
	}

	@Test(groups = {"CategoriesTestsArticle_003", "CategoriesTestsArticle"})
	public void CategoriesTestsArticle_003_user() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		String categoryName = PageContent.CATEGORY_NAME_PREFIX + article.getTimeStamp();
		article.addCategory(categoryName);
		article.submitCategory();
		article.verifyCategoryPresent(categoryName);
	}

	@Test(groups = {"CategoriesTestsArticle_004", "CategoriesTestsArticle"})
	public void CategoriesTestsArticle_004_userSuggestions() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditModePageObject visualEditMode = base.navigateToArticleEditPageCK(
				wikiURL,
				PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp()
		);
		visualEditMode.addContent(PageContent.ARTICLE_TEXT);
		ArticlePageObject article = visualEditMode.submitArticle();
		String desiredCategory = article.addCategorySuggestions(PageContent.CATEGORY_NAME_PREFIX, 2);
		article.submitCategory();
		article.verifyCategoryPresent(desiredCategory);
	}

	@Test(groups = {"CategoriesTestsArticle_005", "CategoriesTestsArticle"})
	public void CategoriesTestsArticle_005_anonEdit() {
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openRandomArticle(wikiURL);
		String categoryName = PageContent.CATEGORY_NAME_PREFIX + article.getTimeStamp();
		article.addCategory(categoryName);
		EditCategoryComponentObject editCategory = article.editCategory(categoryName);
		categoryName = PageContent.CATEGORY_NAME_PREFIX + article.getTimeStamp();
		editCategory.editCategoryName(categoryName);
		article.submitCategory();
		article.verifyCategoryPresent(categoryName);
	}

	@Test(groups = {"CategoriesTestsArticle_006", "CategoriesTestsArticle"})
	public void CategoriesTestsArticle_006_anonDelete() {
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openRandomArticle(wikiURL);
		String categoryName = PageContent.CATEGORY_NAME_PREFIX + article.getTimeStamp();
		article.addCategory(categoryName);
		article.verifySubmitCategoryEnabled();
		article.removeCategory(categoryName);
		article.verifySubmitCategoryDisabled();
	}
}
