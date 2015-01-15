package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.PreviewEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePagePageObject;
import org.testng.annotations.Test;

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
	@Test(groups = {"ArticleTOCTests", "ArticleTOCTests_001"})
	public void ArticleTOCTests_001_CreateArticleWithTOCasAnon() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		articleTitle = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
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
		groups = {"ArticleTOCTests", "ArticleTOCTests_002"},
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
		groups = {"ArticleTOCTests", "ArticleTOCTests_003"},
		dependsOnMethods = "ArticleTOCTests_001_CreateArticleWithTOCasAnon"
	)
	public void ArticleTOCTests_003_verifyTOCisCollapsedOnPreviewForAnon() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		ArticlePageObject article = base.openArticleByName(wikiURL,
			articleTitle);
		article.verifyTOCpresent();
		VisualEditModePageObject visualEditMode = article.editArticleInRTEUsingDropdown();
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
		groups = {"ArticleTOCTests", "ArticleTOCTests_004"},
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
		groups = {"ArticleTOCTests", "ArticleTOCTests_005"},
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
		groups = {"ArticleTOCTests", "ArticleTOCTests_006"},
		dependsOnMethods = "ArticleTOCTests_001_CreateArticleWithTOCasAnon"
	)
	public void ArticleTOCTests_006_verifyTOCisExpandedOnPreviewForLoggedInUser() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName10, credentials.password10, wikiURL);
		ArticlePageObject article = base.openArticleByName(wikiURL,
			articleTitle);
		article.verifyTOCpresent();
		VisualEditModePageObject visualEditMode = article.editArticleInRTEUsingDropdown();
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
		groups = {"ArticleTOCTests", "ArticleTOCTests_007"},
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
}
