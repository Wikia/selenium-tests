package com.wikia.webdriver.Common.Logging;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

/**
 * @author Michal 'justnpT' Nowierski
 */
public class PageObjectLoggingTests extends NewTestTemplate{


	/**
	 *  the test will trigger following logging methods:
	 *  1. afterNavigateTo, 2. beforeClickOn, 3. AfterClickOn,
	 *  4. afterChangeValueOf, 5. onTestSuccess, 6. onStart,
	 *  7. onTestsStart, onFinish
	 */
	@Test( groups= {"PageObjectLoggingTests_001", "PageObjectLoggingTests"})
	public void produceLogFileWithAllEvents_succesTest() {
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openRandomArticle(wikiURL);
		WikiArticleEditMode articleEdit = article.clickEditButton();
		articleEdit.typeInContent(PageContent.articleText);
	}

	/**
	 *  the test will trigger following logging methods:
	 *  1. logWithScreenshot, 2. logJSError, 3. logLowLevelAction,
	 *  4. onTestFailure 5. onStart, 6. onTestsStart,
	 *  7. onFinish
	 */
	@Test( groups= {"PageObjectLoggingTests_001", "PageObjectLoggingTests"})
	public void produceLogFileWithAllEvents_failureTest() {
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openRandomArticle(wikiURL);
		WikiArticleEditMode articleEdit = article.clickEditButton();
		articleEdit.writeSourceMode(PageContent.articleText);
	}
}
