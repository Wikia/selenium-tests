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

public class ArticlePageTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@BeforeMethod(alwaysRun = true)
	public void optInMercury() {
		MercuryContent.turnOnMercurySkin(driver, wikiURL);
	}

	//AP01
	@Test(groups = {"MercuryArticleTests_001", "MercuryArticleTests", "Mercury"})
	public void MercuryArticleTests_001_VerifyLogoAndSearchButtonAreVisible() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject articlePage = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_CATEGORY_TEST_ARTICLE);
		articlePage.verifyWikiaLogoIsVisible();
		articlePage.verifySearchButtonIsVisible();
	}

	//AP03
	@Test(groups = {"MercuryArticleTests_002", "MercuryArticleTests", "Mercury"})
	public void MercuryArticleTests_002_VerifyTopContributorsWikiSection() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject articlePage = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_CATEGORY_TEST_ARTICLE);
		articlePage.verifyTopContributorsSectionIsVisible();
		articlePage.verifyTopContributorsThumb();
	}

}

