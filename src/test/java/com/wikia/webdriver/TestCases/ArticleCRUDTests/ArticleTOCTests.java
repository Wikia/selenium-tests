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
		sourceEditMode.verifySourceModeEnabled();
		sourceEditMode.addTOC();
		ArticlePageObject article = sourceEditMode.submitArticle();
		article.verifyTOCpresent();
	}


	/**
	 * 1. as anon open an article with TOC
	 * 3. verify TOC is collapsed
	 * 2. verify that show/hide buttons work
	 */
	@Test(
			groups = { "ArticleTOCTests", "ArticleTOCTests_002" },
			dependsOnMethods = "ArticleTOCTests_001_CreateArticleWithTOCasAnon"
		 )
	public void ArticleTOCTests_002_verifyTOChideShowButtonsWorkForAnon() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		ArticlePageObject article = base.openArticleByName(wikiURL,
				articleTitle);
		article.verifyTOCpresent();
		article.verifyTOCcollapsed();
		article.clickTOCshowHideButton();
		article.verifyTOCexpanded();
		article.clickTOCshowHideButton();
		article.verifyTOCcollapsed();
	}

	/**
	 * 1. as anon open an article with TOC
	 * 2. user edits the article
	 * 3. user is able to see collapsed TOC on the preview
	 */
	@Test(
			groups = { "ArticleTOCTests", "ArticleTOCTests_003" },
			dependsOnMethods = "ArticleTOCTests_001_CreateArticleWithTOCasAnon"
		 )
	public void ArticleTOCTests_003_verifyTOCisCollapsedOnPreviewForAnon() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		ArticlePageObject article = base.openArticleByName(wikiURL,
				articleTitle);
		article.verifyTOCpresent();
		VisualEditModePageObject visualEditMode = article.editArticleUsingDropdown();
		visualEditMode.verifyContentLoaded();
		PreviewEditModePageObject preview = visualEditMode.previewArticle();
		preview.verifyTOCpresentOnPreview();
		preview.verifyTOCcollapsedOnPreview();
	}

	/**
	 * 1. as anon open an article with TOC
	 * 2. user expands the TOC
	 * 3. user clicks on the first link in TOC
	 * 3. user view is sent to the chosen section
	 */
	@Test(
			enabled = false, //QAART-262
			groups = { "ArticleTOCTests", "ArticleTOCTests_004" },
			dependsOnMethods = "ArticleTOCTests_001_CreateArticleWithTOCasAnon"
		 )
	public void ArticleTOCTests_004_verifyTOCtakesAnonToSectionClicked() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		ArticlePageObject article = base.openArticleByName(wikiURL,
				articleTitle);
		article.verifyTOCpresent();
		article.verifyTOCcollapsed();
		article.clickTOCshowHideButton();
		article.verifyTOCsectionLinkWorks(1);
	}

	/**
	 * 1. as logged in user open an article with TOC
	 * 2. verify TOC is expanded
	 * 3. verify that show/hide buttons work
	 */
	@Test(
			groups = { "ArticleTOCTests", "ArticleTOCTests_005" },
			dependsOnMethods = "ArticleTOCTests_001_CreateArticleWithTOCasAnon"
		 )
	public void ArticleTOCTests_005_verifyTOChideShowButtonsWorkForLoggedInUser() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName10, credentials.password10, wikiURL);
		ArticlePageObject article = base.openArticleByName(wikiURL,
				articleTitle);
		article.verifyTOCpresent();
		article.verifyTOCexpanded();
		article.clickTOCshowHideButton();
		article.verifyTOCcollapsed();
		article.clickTOCshowHideButton();
		article.verifyTOCexpanded();
	}

	/**
	 * 1. as logged in user open an article with TOC
	 * 2. user edits the article
	 * 3. user is able to see expanded TOC on the preview
	 */
	@Test(
			groups = { "ArticleTOCTests", "ArticleTOCTests_006" },
			dependsOnMethods = "ArticleTOCTests_001_CreateArticleWithTOCasAnon"
		 )
	public void ArticleTOCTests_006_verifyTOCisExpandedOnPreviewForLoggedInUser() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName10, credentials.password10, wikiURL);
		ArticlePageObject article = base.openArticleByName(wikiURL,
				articleTitle);
		article.verifyTOCpresent();
		VisualEditModePageObject visualEditMode = article.editArticleUsingDropdown();
		visualEditMode.verifyContentLoaded();
		PreviewEditModePageObject preview = visualEditMode.previewArticle();
		preview.verifyTOCpresentOnPreview();
		preview.verifyTOCexpandedOnPreview();
	}

	/**
	 * 1. as logged in user open an article with TOC
	 * 2. user clicks on the first link in TOC
	 * 3. user view is sent to the chosen section
	 */
	@Test(
			enabled = false, //QAART-262
			groups = { "ArticleTOCTests", "ArticleTOCTests_007" },
			dependsOnMethods = "ArticleTOCTests_001_CreateArticleWithTOCasAnon"
		 )
	public void ArticleTOCTests_007_verifyTOCtakesLoggedInUserToSectionClicked() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName10, credentials.password10, wikiURL);
		ArticlePageObject article = base.openArticleByName(wikiURL,
				articleTitle);
		article.verifyTOCpresent();
		article.verifyTOCexpanded();
		article.verifyTOCsectionLinkWorks(1);
	}

	/**
	 * DAR-2616 bug prevention test case
	 * details jira:	https://wikia-inc.atlassian.net/browse/DAR-2616
	 * 1. as logged in user open an article with TOC
	 * 2. add toc class modifier, which should add a class to each toc li elem
	 * 3. make sure classes appear on TOC li elements
	 */
	@Test(
			groups = { "ArticleTOCTests", "ArticleTOCTests_009" },
			dependsOnMethods = "ArticleTOCTests_001_CreateArticleWithTOCasAnon"
		 )
	public void ArticleTOCTests_009_verifyToclimitClassesAppear() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName10, credentials.password10, wikiURL);
		ArticlePageObject article = base.openArticleByName(wikiURL,
				articleTitle);
		article.verifyTOCpresent();
		article.verifyTOCexpanded();
		VisualEditModePageObject visualEditMode = article.editArticleUsingDropdown();
		SourceEditModePageObject sourceEditMode = visualEditMode.clickSourceButton();
		sourceEditMode.appendNewLine(PageContent.tocClassModifier);
		article = sourceEditMode.submitArticle();
		article.verifyTOCpresent();
		article.verifyToclimitPresent(2, 4);
	}
}
