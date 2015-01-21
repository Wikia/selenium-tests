package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;

import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.SearchNavSideMenuComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
* @ownership: Mobile Web
* @authors: Rodrigo Gomez, Łukasz Nowak
* */
public class SearchTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@BeforeMethod(alwaysRun = true)
	public void optInMercury() {
		MercuryContent.turnOnMercurySkin(driver, wikiURL);
	}

	//ST02
	@Test(groups = {"MercurySearchTests_002", "MercurySearchTests", "Mercury"})
	public void MercurySearchTests_002_VerifySearchAutosuggestionsAppear() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
		SearchNavSideMenuComponentObject searchObject = article.clickSearchButton();
		searchObject.clickSearchField();
		searchObject.typeInSearchField(MercuryContent.MERCURY_SEARCH_PASS, 3);
		searchObject.verifySearchSuggestionsWereVisible();
	}

	//ST03
	@Test(groups = {"MercurySearchTests_003", "MercurySearchTests", "Mercury"})
	public void MercurySearchTests_003_VerifyTappingCancelWillDisplayNavBarMenu() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
		SearchNavSideMenuComponentObject searchObject = article.clickSearchButton();
		searchObject.clickSearchField();
		searchObject.verifySearchView();
		searchObject.clickCancelButton();
		searchObject.verifyMenuView();
	}
	
	//ST04
	@Test(groups = {"MercurySearchTests_004", "MercurySearchTests", "Mercury"})
	public void MercurySearchTests_004_VerifyClickOnSearchResultWillRedirectUser() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
		SearchNavSideMenuComponentObject searchObject = article.clickSearchButton();
		searchObject.clickSearchField();
		searchObject.verifySearchView();
		searchObject.typeInSearchField(MercuryContent.MERCURY_SEARCH_PASS, 3);
		searchObject.verifySearchSuggestionsWereVisible();
		String redirection = searchObject.getSearchResultHref(MercuryContent.MERCURY_SEARCH_CLICK_INDEX);
		base = searchObject.clickSearchSuggestion(MercuryContent.MERCURY_SEARCH_CLICK_INDEX);
		base.waitForStringInURL(redirection);
	}

	//ST05 - FAIL
	@Test(groups = {"MercurySearchTests_005", "MercurySearchTests", "Mercury"})
	public void MercurySearchTests_005_SearchSuggestionsShouldNotBeCalled() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
		SearchNavSideMenuComponentObject searchObject = article.clickSearchButton();
		searchObject.clickSearchField();
		searchObject.verifySearchView();
		searchObject.typeInSearchField(MercuryContent.MERCURY_SEARCH_PASS, 2);
		searchObject.verifySearchNotMatch();
	}
}
