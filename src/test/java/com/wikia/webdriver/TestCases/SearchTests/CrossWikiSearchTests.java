package com.wikia.webdriver.TestCases.SearchTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.SearchContent;
import com.wikia.webdriver.Common.DataProvider.CrossWikiSearchProvider;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Search.CrossWikiSearch.CrossWikiSearchPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialPromotePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticleHomePage;

/**
 * Author: Artur Dwornik & Rodrigo Molinero Gomez
 * Date: 29.03.13
 * Time: 11:22
	STAPI01: Verify that adding only underscores, only dashes, no spaces, or wiki domain exact match for a wiki will display as first result
	STAPI02: Verify that pagination is working correctly for queries which give more than 10 results
	STAPI03: Verify that user is redirect to a wiki home page when clicking results from cross wiki search
	STAPI04: Verify that no pagination or description is displayed when there are no results
	STAPI05: Verify that no pagination is displayed when there are results but they are less than 10
	STAPI06: Verify that push to top exact match is working correctly for different wikis
	STAPI07: Verify that Special:PRomote page data is displayed in cross-wiki search results
	STAPI08: Verify that performing a certain search will always display results in same order
	STAPI09: Verify that searching for a query with either roman or decimal numbers will display expected result in first page
 */
public class CrossWikiSearchTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(dataProviderClass = CrossWikiSearchProvider.class,
			dataProvider = "getExactMatchQueries",
			groups = {"CrossWikiSearchTests_001", "Search", "CrossWikiSearch"})
	public void crossWikiSearch_001_exactMatch(String query, String wikiName, String vertical) {
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
		CrossWikiSearchPageObject searchPage = home.searchFor(query);
		searchPage.verifyFirstResultTitle(wikiName);
		searchPage.verifyFirstResultVertical(vertical);
		searchPage.verifyFirstResultDescription();
		searchPage.verifyFirstResultPageCount();
		searchPage.verifyFirstResultPageImages();
		searchPage.verifyFirstResultPageVideos();
	}

	@Test(groups= {"CrossWikiSearchTests_002", "Search", "CrossWikiSearch"})
	public void crossWikiSearch_002_pagination() {
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
		CrossWikiSearchPageObject searchPage = home.searchFor(SearchContent.searchPhrase);
		// verify results pos parameter for first page
		searchPage.verifyResultsPosForPage(0, SearchContent.resultsPerPage);
		searchPage.verifyResultsCount(SearchContent.resultsPerPage);
		searchPage.verifyThumbnails(SearchContent.resultsPerPage);
		searchPage.verifyDescription(SearchContent.resultsPerPage);
		searchPage.verifyStatistics(SearchContent.resultsPerPage);
		searchPage.nextPage();
		// verify results pos parameter for second page
		searchPage.verifyResultsPosForPage(1, SearchContent.resultsPerPage);
		searchPage.verifyResultsCount(SearchContent.resultsPerPage);
		searchPage.verifyThumbnails(SearchContent.resultsPerPage);
		searchPage.verifyDescription(SearchContent.resultsPerPage);
		searchPage.verifyStatistics(SearchContent.resultsPerPage);
		searchPage.prevPage();
		// verify results pos parameter for first page
		searchPage.verifyResultsPosForPage(0, SearchContent.resultsPerPage);
		searchPage.verifyResultsCount(SearchContent.resultsPerPage);
		searchPage.verifyThumbnails(SearchContent.resultsPerPage);
		searchPage.verifyDescription(SearchContent.resultsPerPage);
		searchPage.verifyStatistics(SearchContent.resultsPerPage);
	}

	@Test(groups= {"CrossWikiSearchTests_003" , "Search", "CrossWikiSearch"} )
	public void crossWikiSearch_003_resultClick() {
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
		CrossWikiSearchPageObject searchPage = home.searchFor(SearchContent.searchPhrase);
		WikiArticleHomePage wikiArticleHomePage = searchPage.openResult(0);
		wikiArticleHomePage.verifyThisIsWikiHomePage();
		searchPage.navigateBack();
		searchPage.openResult(1);
		wikiArticleHomePage.verifyThisIsWikiHomePage();
	}

	@Test(groups = {"CrossWikiSearchTests_004", "Search", "CrossWikiSearch"})
	public void crossWikiSearch_004_noResults() {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(PageContent.wikiaGlobalUrl);
		search.searchFor(SearchContent.searchPhraseNoResults);
		search.verifyNoPagination();
		search.verifyNoResultsCaption();
	}

	@Test(groups = {"CrossWikiSearchTests_005", "Search", "CrossWikiSearch"})
	public void crossWikiSearch_005_onePageResult() {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(PageContent.wikiaGlobalUrl);
		search.searchFor(SearchContent.searchPhraseOnePageResults);
		search.verifyNoPagination();
	}
	@Test(dataProviderClass = CrossWikiSearchProvider.class,
			dataProvider = "getPushToTopQueries",
			groups = {"CrossWikiSearchTests_006", "Search", "CrossWikiSearch"})
	public void crossWikiSearch_006_pushToTop(String query, String wikiName) {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(PageContent.wikiaGlobalUrl);
		CrossWikiSearchPageObject searchPage = search.searchFor(query);
		searchPage.verifyFirstResultTitle(wikiName);
	}

	@Test(groups = {"CrossWikiSearchTests_007", "Search", "CrossWikiSearch"})
	public void crossWikiSearch_007_specialPromoteData() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(PageContent.wikiaGlobalUrl);
		search.searchFor(SearchContent.searchPhrase);
		String searchDescription = search.getFirstDescription();
		String searchImage = search.getFirstImageText();
		search.openResult(0);
		base.openSpecialPromoteOnCurrentWiki();
		SpecialPromotePageObject promote = new SpecialPromotePageObject(driver);
		promote.verifyCrossWikiSearchDescription(searchDescription);
		promote.verifyCrossWikiSearchImage(searchImage);
	}

	//TODO when Jakub K. agrees final functionality
//	@Test(dataProviderClass=SearchDataProvider.class,
//			dataProvider="getExpectedWikiResults",
//			groups = {"CrossWikiSearchTests_008", "Search, "CrossWikiSearch""})
	public void crossWikiSearch_008_wikimatch( String expectedWikiTitle ) {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(PageContent.wikiaGlobalUrl);
		search.searchFor(SearchContent.searchPhraseResultsSameOrder);
		search.verifyQuery(expectedWikiTitle);
	}

	@Test(groups = {"CrossWikiSearchTests_009", "Search", "CrossWikiSearch"})
	public void crossWikiSearch_009_romanNumbersMatch() {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(PageContent.wikiaGlobalUrl);
		search.searchFor(SearchContent.searchPhraseRomanNumber);
		search.verifyQuery(SearchContent.wikiName);
		search.searchFor(SearchContent.searchPhraseDecimalNumber);
		search.verifyQuery(SearchContent.wikiName);
	}
}
