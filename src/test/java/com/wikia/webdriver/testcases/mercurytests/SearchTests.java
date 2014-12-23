package com.wikia.webdriver.TestCases.mercurytests;

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

	@Test(groups = {"MercurySearchTests_001", "MercurySearchTests", "Mercury"})
	public void MercurySearchTests_001_VerifySearchAutosuggestionsAppear() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
		SearchNavSideMenuComponentObject searchObject = article.clickSearchButton();
		searchObject.clickSearchField();
		searchObject.typeInSearchField(MercuryContent.MERCURY_SEARCH_PASS, 3);
		searchObject.verifySearchSuggestionsWereVisible();
	}

	@Test(groups = {"MercurySearchTests_002", "MercurySearchTests", "Mercury"})
	public void MercurySearchTests_002_VerifyClickOnSearchResultWillRedirectUser() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
		SearchNavSideMenuComponentObject searchObject = article.clickSearchButton();
		searchObject.clickSearchField();
		searchObject.verifySearchView();
		searchObject.typeInSearchField(MercuryContent.MERCURY_SEARCH_PASS, 3);
		searchObject.verifySearchSuggestionsWereVisible();
		String redirection = searchObject.getSearchResultHref(MercuryContent.MERCURY_SEARCH_CLICK_INDEX);
		base = searchObject.clickSearchSuggestion(MercuryContent.MERCURY_SEARCH_CLICK_INDEX);
		String link = base.getCurrentUrl();
		base.waitForStringInURL(redirection);
	}

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

	@Test(groups = {"MercurySearchTests_004", "MercurySearchTests", "Mercury"})
	public void MercurySearchTests_004_SearchSuggestionsShouldNotBeCalled() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
		SearchNavSideMenuComponentObject searchObject = article.clickSearchButton();
		searchObject.clickSearchField();
		searchObject.verifySearchView();
		searchObject.typeInSearchField(MercuryContent.MERCURY_SEARCH_PASS, 2);
		searchObject.verifySearchNotMatch();
	}
}
