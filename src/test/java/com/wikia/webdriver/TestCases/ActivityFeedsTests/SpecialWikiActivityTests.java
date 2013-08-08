package com.wikia.webdriver.TestCases.ActivityFeedsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreatePagePageObject;

/**
 * @author Michał 'justnpT' Nowierski
 *
 */
public class SpecialWikiActivityTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-1617
	 */
	@Test(groups = { "SpecialWikiActivity", "SpecialWikiActivity_001", "darwin" })
	public void SpecialWikiActivityTests_001_newEditionIsRecordedOnAvtivityModule() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		String articleContent = PageContent.articleText;
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.addContent(articleContent);
		visualEditMode.submitArticle();
		article.verifyContent(articleContent);
	}

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-1617
	 */
	@Test(groups = { "SpecialWikiActivity", "SpecialWikiActivity_002", "darwin" })
	public void SpecialWikiActivityTests_002_newPageCretionIsRecordedOnAvtivityModule() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialCreatePagePageObject specialCreatePage = base.openSpecialCreatePage(wikiURL);
		String articleContent = PageContent.articleText;
		String articleTitle = PageContent.articleNamePrefix + specialCreatePage.getTimeStamp();
		VisualEditModePageObject visualEditMode = specialCreatePage.populateTitleField(articleTitle);
		visualEditMode.addContent(articleContent);
		ArticlePageObject article  = visualEditMode.submitArticle();
		article.verifyContent(articleContent);
		article.verifyArticleTitle(articleTitle);
	}

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-1617
	 */
	@Test(groups = { "SpecialWikiActivity", "SpecialWikiActivity_003", "darwin" })
	public void SpecialWikiActivityTests_003_newBlogCreationIsRecordedOnAvtivityModule() {

	}

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-1617
	 */
	@Test(groups = { "SpecialWikiActivity", "SpecialWikiActivity_004", "darwin" })
	public void SpecialWikiActivityTests_004_newCategorizationIsRecordedOnAvtivityModule() {

	}
}
