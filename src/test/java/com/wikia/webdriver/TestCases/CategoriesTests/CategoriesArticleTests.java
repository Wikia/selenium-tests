/**
 *
 */
package com.wikia.webdriver.TestCases.CategoriesTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.EditCategory.EditCategoryComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class CategoriesArticleTests extends NewTestTemplate{

	/*
	 * Add category to article as anon
	 * Add category to article from suggestion list as anon
	 * Add category to article as user
	 * Add category to article from suggestion list as user
	 * Add category to article as anon edit, delete
	 */

	Credentials credentials = config.getCredentials();

	@Test(groups = {"CategoriesTestsArticle001", "CategoriesTestsArticle"})
	public void CategoriesTestsArticle001_anon() {
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openRandomArticle(wikiURL);
		String categoryName = PageContent.categoryNamePrefix + article.getTimeStamp();
		article.addCategory(categoryName);
		article.submitCategory();
		article.verifyCategoryPresent(categoryName);
	}

	@Test(groups = {"CategoriesTestsArticle002", "CategoriesTestsArticle"})
	public void CategoriesTestsArticle002_anonSuggestions() {
		VisualEditModePageObject visualEditMode = new VisualEditModePageObject(driver);
		visualEditMode.goToArticleEditPage(
				wikiURL,
				PageContent.articleNamePrefix + visualEditMode.getTimeStamp()
		);
		visualEditMode.addContent(PageContent.articleText);
		ArticlePageObject article = visualEditMode.submitArticle();
		String desiredCategory = article.addCategorySuggestions(PageContent.categoryNamePrefix, 2);
		article.submitCategory();
		article.verifyCategoryPresent(desiredCategory);
	}

	@Test(groups = {"CategoriesTestsArticle003", "CategoriesTestsArticle"})
	public void CategoriesTestsArticle003_user() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		String categoryName = PageContent.categoryNamePrefix + article.getTimeStamp();
		article.addCategory(categoryName);
		article.submitCategory();
		article.verifyCategoryPresent(categoryName);
	}

	@Test(groups = {"CategoriesTestsArticle004", "CategoriesTestsArticle"})
	public void CategoriesTestsArticle004_userSuggestions() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password);
		VisualEditModePageObject visualEditMode = base.goToArticleEditPage(
				wikiURL,
				PageContent.articleNamePrefix + base.getTimeStamp()
				);
		visualEditMode.addContent(PageContent.articleText);
		ArticlePageObject article = visualEditMode.submitArticle();
		String desiredCategory = article.addCategorySuggestions(PageContent.categoryNamePrefix, 2);
		article.submitCategory();
		article.verifyCategoryPresent(desiredCategory);
	}

	@Test(groups = {"CategoriesTestsArticle005", "CategoriesTestsArticle"})
	public void CategoriesTestsArticle005_anonEdit() {
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openRandomArticle(wikiURL);
		String categoryName = PageContent.categoryNamePrefix + article.getTimeStamp();
		article.addCategory(categoryName);
		EditCategoryComponentObject editCategory = article.editCategory(categoryName);
		categoryName = PageContent.categoryNamePrefix + article.getTimeStamp();
		editCategory.editCategoryName(categoryName);
		article.submitCategory();
		article.verifyCategoryPresent(categoryName);
	}

	@Test(groups = {"CategoriesTestsArticle005", "CategoriesTestsArticle"})
	public void CategoriesTestsArticle006_anonDelete() {
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openRandomArticle(wikiURL);
		String categoryName = PageContent.categoryNamePrefix + article.getTimeStamp();
		article.addCategory(categoryName);
		article.verifySubmitCategoryEnabled();
		article.removeCategory(categoryName);
		article.verifySubmitCategoryDisabled();
	}
}
