package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.PreviewEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.SourceEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreatePagePageObject;

/**
 * @author: Michal 'justnpT' Nowierski
 */
public class ArticleTOCTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();
	String articleTitle;

	/**
	 * 1. as anon create an article with TOC
	 * 2. verify TOC is present on the article
	 *
	 */
	@Test(groups = { "ArticleTOCTests", "ArticleTOCTests_001" })
	public void ArticleTOCTests_001_CreateArticleWithTOCasAnon() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		articleTitle = PageContent.articleNamePrefix + base.getTimeStamp();
		SpecialCreatePagePageObject specialCreatePage = base
				.openSpecialCreatePage(wikiURL);
		VisualEditModePageObject visualEditMode = specialCreatePage
				.populateTitleField(articleTitle);
		SourceEditModePageObject sourceEditMode = visualEditMode
				.clickSourceButton();
		sourceEditMode.addTOC();
		ArticlePageObject article = sourceEditMode.submitArticle();
		article.verifyTOCpresent();
	}

	/**
	 * 1. as anon open an article with TOC
	 * 2. verify TOC is collapsed
	 */
	@Test(groups = { "ArticleTOCTests", "ArticleTOCTests_002" }, dependsOnMethods = "ArticleTOCTests_001_CreateArticleWithTOCasAnon")
	public void ArticleTOCTests_002_verifyTOCisCollapsedForAnon() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		ArticlePageObject article = base.openArticleByName(wikiURL,
				articleTitle);
		article.verifyTOCpresent();
		article.verifyTOCcollapsed();
	}

	/**
	 * 1. as anon open an article with TOC
	 * 2. verify that show/hide buttons work
	 */
	@Test(groups = { "ArticleTOCTests", "ArticleTOCTests_003" }, dependsOnMethods = "ArticleTOCTests_001_CreateArticleWithTOCasAnon")
	public void ArticleTOCTests_003_verifyTOChideShowButtonsWorkForAnon() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		ArticlePageObject article = base.openArticleByName(wikiURL,
				articleTitle);
		article.verifyTOCpresent();
		article.verifyTOCcollapsed();
		article.clickTOCshowButton();
		article.verifyTOCexpanded();
		article.clickTOChideButton();
		article.verifyTOCcollapsed();
	}

	/**
	 * 1. as anon open an article with TOC
	 * 2. user edits the article
	 * 3. user is able to see collapsed TOC on the preview
	 */
	@Test(groups = { "ArticleTOCTests", "ArticleTOCTests_004" }, dependsOnMethods = "ArticleTOCTests_001_CreateArticleWithTOCasAnon")
	public void ArticleTOCTests_004_verifyTOCisCollapsedOnPreviewForAnon() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		ArticlePageObject article = base.openArticleByName(wikiURL,
				articleTitle);
		article.verifyTOCpresent();
		VisualEditModePageObject visualEditMode = article.editArticleUsingDropdown();
		PreviewEditModePageObject preview = visualEditMode.previewArticle();
		preview.verifyTOCpresentOnPreview();
		preview.verifyTOCcollapsedOnPreview();
	}

	/**
	 * 1. as anon open an article with TOC
	 * 2. user expands the TOC
	 * 3. user click on the first link in TOC
	 * 3. user is sent to the choosen section
	 */
	@Test(groups = { "ArticleTOCTests", "ArticleTOCTests_004" }, dependsOnMethods = "ArticleTOCTests_001_CreateArticleWithTOCasAnon")
	public void ArticleTOCTests_005_verifyTOCtakesAnonToSectionClicked() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		ArticlePageObject article = base.openArticleByName(wikiURL,
				articleTitle);
		article.verifyTOCpresent();
		article.verifyTOCcollapsed();
		article.clickTOCshowButton();
		article.verifyTOCsectionLnkWorks(1);

	}
}
