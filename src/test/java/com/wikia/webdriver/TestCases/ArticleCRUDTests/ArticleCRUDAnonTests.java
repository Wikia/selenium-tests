package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.ArticleDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreatePagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class ArticleCRUDAnonTests extends NewTestTemplate {

	@Test(
		groups={"ArticleCRUDAnon", "ArticleCRUDAnon_001"}
	)
	public void ArticleCRUDAnon_001_addBySpecialPage() {
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
	public void ArticleCRUDAnon_002_addByURL() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleContent = PageContent.articleText;
		String articleTitle = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.navigateToArticleEditPageCK(wikiURL, articleTitle);
		visualEditMode.addContent(articleContent);
		ArticlePageObject article  = visualEditMode.submitArticle();
		article.verifyContent(articleContent);
		article.verifyArticleTitle(articleTitle);
	}

	@Test(
		groups = {"ArticleCRUDAnon", "ArticleCRUDAnon_003"}
	)
	public void ArticleCRUDAnon_003_addDropdown() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleContent = PageContent.articleText;
		String articleTitle = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditorPageObject ve = article.createArticleInVEUsingDropdown(articleTitle);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		article = ve.clickVEEditAndPublish(articleContent);
		article.verifyContent(articleContent);
		article.verifyArticleTitle(articleTitle);
	}

	@Test(
		dataProviderClass = ArticleDataProvider.class,
		dataProvider = "articleTitles",
		groups = {"ArticleCRUDAnon", "ArticleCRUDAnon_004"}
	)
	public void ArticleCRUDAnon_004_differentTitles(String articleTitle) {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleContent = PageContent.articleText;
		articleTitle = articleTitle + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.navigateToArticleEditPageCK(wikiURL, articleTitle);
		visualEditMode.addContent(articleContent);
		ArticlePageObject article  = visualEditMode.submitArticle();
		article.verifyContent(articleContent);
		article.verifyArticleTitle(articleTitle);
	}

	@Test(
		groups = {"ArticleCRUDAnon", "ArticleCRUDAnon_005"}
	)
	public void ArticleCRUDAnon_005_editByURL() {
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
	public void ArticleCRUDAnon_006_editDropdown() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleContent = PageContent.articleText;
		ArticlePageObject article =
			base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		article = ve.clickVEEditAndPublish(articleContent);
		article.verifyContent(articleContent);
	}
}
