package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.ArticleDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreatePagePageObject;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class ArticleCRUDAnonTests extends NewTestTemplate {

	@Test(
		groups={"ArticleCRUDAnon", "ArticleCRUDAnon_001"}
	)
	public void ArticleCRUDUser_001_addBySpecialPage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleContent = PageContent.articleText;
		String articleTitle = PageContent.articleNamePrefix + base.getTimeStamp();
		SpecialCreatePagePageObject specialCreatePage = base.openSpecialCreatePage(wikiURL);
		VisualEditModePageObject visualEditMode = specialCreatePage.populateTitleField(articleTitle);
		visualEditMode.addContent(articleContent);
		ArticlePageObject article  = visualEditMode.submitArticle();
		article.verifyContent(articleContent);
		article.verifyArticleTitle(articleTitle);
	}

	@Test(
		groups={"ArticleCRUDAnon", "ArticleCRUDAnon_002"}
	)
	public void ArticleCRUDUser_002_addByURL() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleContent = PageContent.articleText;
		String articleTitle = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.goToArticleEditPage(wikiURL, articleTitle);
		visualEditMode.addContent(articleContent);
		ArticlePageObject article  = visualEditMode.submitArticle();
		article.verifyContent(articleContent);
		article.verifyArticleTitle(articleTitle);
	}

	@Test(
		groups = {"ArticleCRUDAnon", "ArticleCRUDAnon_003"}
	)
	public void ArticleCRUDUser_003_addDropdown() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleContent = PageContent.articleText;
		String articleTitle = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.createArticleUsingDropdown(articleTitle);
		visualEditMode.addContent(articleContent);
		visualEditMode.submitArticle();
		article.verifyContent(articleContent);
		article.verifyArticleTitle(articleTitle);
	}

	@Test(
		dataProviderClass = ArticleDataProvider.class,
		dataProvider = "articleTitles",
		groups = {"ArticleCRUDAnon", "ArticleCRUDAnon_004"}
	)
	public void ArticleCRUDUser_004_differentTitles(String articleTitle) {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleContent = PageContent.articleText;
		articleTitle = articleTitle + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.goToArticleEditPage(wikiURL, articleTitle);
		visualEditMode.addContent(articleContent);
		ArticlePageObject article  = visualEditMode.submitArticle();
		article.verifyContent(articleContent);
		article.verifyArticleTitle(articleTitle);
	}

	@Test(
		groups = {"ArticleCRUDAnon", "ArticleCRUDAnon_005"}
	)
	public void ArticleCRUDUser_005_editByURL() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleContent = PageContent.articleText;
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.addContent(articleContent);
		visualEditMode.submitArticle();
		article.verifyContent(articleContent);
	}

	@Test(
		groups = {"ArticleCRUDAnon", "ArticleCRUDAnon_006"}
	)
	public void ArticleCRUDUser_006_editDropdown() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleContent = PageContent.articleText;
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.editArticleInRTEUsingDropdown();
		visualEditMode.addContent(articleContent);
		visualEditMode.submitArticle();
		article.verifyContent(articleContent);
	}
}
