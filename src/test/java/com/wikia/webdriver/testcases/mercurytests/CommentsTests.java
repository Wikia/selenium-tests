package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak
 * @ownership: Mobile Web
 */

public class CommentsTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@BeforeMethod(alwaysRun = true)
	public void optInMercury() {
		MercuryContent.turnOnMercurySkin(driver, wikiURL);
	}

	//CT01
	@Test(groups = {"MercuryCommentsTests_001", "MercuryCommentsTests", "Mercury"})
	public void MercuryCommentsTests_001_ClickingCommentsWillUncollapseComments() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
		article.clickCommentsHeader();
		article.verifyCommentsAreUncollapsed();
	}

	//CT04
	@Test(groups = {"MercuryCommentsTest_004", "MercuryCommentsTests", "Mercury"})
	public void MercuryCommentsTests_004_ClickViewReplyWillExpandReplies() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
		article.clickCommentsHeader();
		article.clickViewReplies();
		article.verifyRepliesAreExpanded();
	}
	
	//CT05
	@Test(groups = {"MercuryCommentsTest_005", "MercuryCommentsTests", "Mercury"})
	public void MercuryCommentsTests_005_CheckCommentElements() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
		article.clickCommentsHeader();
		article.verifyCommentsElements();
	}

}
