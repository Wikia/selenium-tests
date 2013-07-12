package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.ArticleDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreatePagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.testng.annotations.Test;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class ArticleCRUDAnonTests extends NewTestTemplate {

	@Test(
		groups={"ArticleCRUDAnon", "ArticleCRUDAnon_001", "Smoke"}
	)
	public void ArticleCRUDUser_specialPage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleContent = PageContent.articleText;
		String articleTitle = PageContent.articleNamePrefix + base.getTimeStamp();
		SpecialCreatePagePageObject specialCreatePage = base.openSpecialCreatePage(wikiURL);
		VisualEditModePageObject visualEditMode = specialCreatePage.fillTitle(articleTitle);
		visualEditMode.addContent(articleContent);
		ArticlePageObject article  = visualEditMode.submit();
		article.compareContent(articleContent);
		article.compareTitle(articleTitle);
	}

	@Test(
		groups={"ArticleCRUDAnon", "ArticleCRUDAnon_002", "Smoke"}
	)
	public void ArticleCRUDUser_addByURL() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleContent = PageContent.articleText;
		String articleTitle = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.goToArticleEditPage(wikiURL, articleTitle);
		visualEditMode.addContent(articleContent);
		ArticlePageObject article  = visualEditMode.submit();
		article.compareContent(articleContent);
		article.compareTitle(articleTitle);
	}

	@Test(
		groups = {"ArticleCRUDAnon", "ArticleCRUDAnon_003", "Smoke"}
	)
	public void ArticleCRUDUser_addDropdown() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleContent = PageContent.articleText;
		String articleTitle = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject article = base.openRandomArticleOnWiki(wikiURL);
		VisualEditModePageObject visualEditMode = article.createArticleUsingDropdown(articleTitle);
		visualEditMode.addContent(articleContent);
		visualEditMode.submit();
		article.compareContent(articleContent);
		article.compareTitle(articleTitle);
	}

	@Test(
		dataProviderClass = ArticleDataProvider.class,
		dataProvider = "articleTitles",
		groups = {"ArticleCRUDAnon", "ArticleCRUDAnon_004"}
	)
	public void ArticleCRUDUser_differentTitles(String articleTitle) {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleContent = PageContent.articleText;
		articleTitle = articleTitle + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.goToArticleEditPage(wikiURL, articleTitle);
		visualEditMode.addContent(articleContent);
		ArticlePageObject article  = visualEditMode.submit();
		article.compareContent(articleContent);
		article.compareTitle(articleTitle);
	}

	@Test(
		groups = {"ArticleCRUDAnon", "ArticleCRUDAnon_005"}
	)
	public void ArticleCRUDUser_editByURL() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleContent = PageContent.articleText;
		ArticlePageObject article = base.openRandomArticleOnWiki(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.addContent(articleContent);
		visualEditMode.submit();
		article.compareContent(articleContent);
	}

	@Test(
		groups = {"ArticleCRUDAnon", "ArticleCRUDAnon_006"}
	)
	public void ArticleCRUDUser_editDropdown() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleContent = PageContent.articleText;
		ArticlePageObject article = base.openRandomArticleOnWiki(wikiURL);
		VisualEditModePageObject visualEditMode = article.editArticleUsingDropdown();
		visualEditMode.addContent(articleContent);
		visualEditMode.submit();
		article.compareContent(articleContent);
	}
}
