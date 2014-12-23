package com.wikia.webdriver.TestCases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak
 * @ownership: Mobile Web
 */

public class CommentsTests extends MobileTestTemplate {

	Credentials credentials = config.getCredentials();

	@BeforeMethod(alwaysRun = true)
	public void optInMercury() {
		MercuryContent.turnOnMercurySkin(driver, wikiURL);
	}

	@Test(groups = {"MercuryCommentsTests_001", "MercuryCommentsTests", "Mercury"})
	public void MercuryCommentsTests_001_ClickingCommentsWillUncollapseComments() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
		article.clickCommentsHeader();
		article.verifyCommentsAreUncollapsed();
	}

	@Test(groups = {"MercuryCommentsTest_002", "MercuryCommentsTests", "Mercury"})
	public void MercuryCommentsTests_002_CheckCommentElements() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
		article.clickCommentsHeader();
		article.verifyCommentsElements();
	}

	@Test(groups = {"MercuryCommentsTest_003", "MercuryCommentsTests", "Mercury"})
	public void MercuryCommentsTests_003_ClickViewReplyWillExpandReplies() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
		article.clickCommentsHeader();
		article.clickViewReplies();
		article.verifyRepliesAreExpanded();
	}

	@Test(groups = {"MercuryCommentsTest_004", "MercuryCommentsTests", "Mercury"})
	public void MercuryCommentsTests_004_VerifyCommentsCounter() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
		article.clickCommentsHeader();
	}
}
