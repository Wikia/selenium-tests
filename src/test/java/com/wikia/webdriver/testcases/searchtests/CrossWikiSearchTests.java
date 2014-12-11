package com.wikia.webdriver.testcases.searchtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.SearchContent;
import com.wikia.webdriver.common.dataprovider.CrossWikiSearchProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.search.crosswikisearch.CrossWikiSearchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPromotePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.WikiArticleHomePage;

/**
 * Author: Artur Dwornik & Rodrigo Molinero Gomez
 * Date: 29.03.13
 * Time: 11:22
 * STAPI01: Verify that adding only underscores, only dashes, no spaces, or wiki domain exact match for a wiki will display as first result
 * STAPI02: Verify that pagination is working correctly for queries which give more than 10 results
 * STAPI03: Verify that user is redirect to a wiki home page when clicking results from cross wiki search
 * STAPI04: Verify that no pagination or description is displayed when there are no results
 * STAPI05: Verify that no pagination is displayed when there are results but they are less than 10
 * STAPI06: Verify that push to top exact match is working correctly for different wikis
 * STAPI07: Verify that Special:PRomote page data is displayed in cross-wiki search results
 * STAPI08: Verify that performing a certain search will always display results in same order
 * STAPI09: Verify that searching for a query with either roman or decimal numbers will display expected result in first page
 */
public class CrossWikiSearchTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(dataProviderClass = CrossWikiSearchProvider.class,
		dataProvider = "getExactMatchQueries",
		groups = {"CrossWikiSearchTests_001", "Search", "CrossWikiSearch"})
	public void crossWikiSearch_001_exactMatch(String query, String wikiName, String vertical) {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(wikiCorporateURL);
		search.searchFor(query);
		search.verifyFirstResultTitle(wikiName);
		search.verifyFirstResultVertical(vertical);
		search.verifyFirstResultDescription();
		search.verifyFirstResultPageCount();
		search.verifyFirstResultPageImages();
		search.verifyFirstResultPageVideos();
	}

	@Test(groups = {"CrossWikiSearchTests_002", "Search", "CrossWikiSearch"})
	public void crossWikiSearch_002_pagination() {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(wikiCorporateURL);
		search.searchFor(SearchContent.SEARCH_PHRASE);
		// verify results pos parameter for first page
		search.verifyResultsPosForPage(0, SearchContent.RESULTS_PER_PAGE);
		search.verifyResultsCount(SearchContent.RESULTS_PER_PAGE);
		search.verifyThumbnails(SearchContent.RESULTS_PER_PAGE);
		search.verifyDescription(SearchContent.RESULTS_PER_PAGE);
		search.verifyStatistics(SearchContent.RESULTS_PER_PAGE);
		search.nextPage();
		// verify results pos parameter for second page
		search.verifyResultsPosForPage(1, SearchContent.RESULTS_PER_PAGE);
		search.verifyResultsCount(SearchContent.RESULTS_PER_PAGE);
		search.verifyThumbnails(SearchContent.RESULTS_PER_PAGE);
		search.verifyDescription(SearchContent.RESULTS_PER_PAGE);
		search.verifyStatistics(SearchContent.RESULTS_PER_PAGE);
		search.prevPage();
		// verify results pos parameter for first page
		search.verifyResultsPosForPage(0, SearchContent.RESULTS_PER_PAGE);
		search.verifyResultsCount(SearchContent.RESULTS_PER_PAGE);
		search.verifyThumbnails(SearchContent.RESULTS_PER_PAGE);
		search.verifyDescription(SearchContent.RESULTS_PER_PAGE);
		search.verifyStatistics(SearchContent.RESULTS_PER_PAGE);
	}

	@Test(groups = {"CrossWikiSearchTests_003", "Search", "CrossWikiSearch"})
	public void crossWikiSearch_003_resultClick() {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(wikiCorporateURL);
		search.searchFor(SearchContent.SEARCH_PHRASE);
		WikiArticleHomePage wikiArticleHomePage = search.openResult(0);
		wikiArticleHomePage.verifyThisIsWikiHomePage();
		search.navigateBack();
		search.openResult(2);
		wikiArticleHomePage.verifyThisIsWikiHomePage();
	}

	@Test(groups = {"CrossWikiSearch_004", "Search", "CrossWikiSearch"})
	public void crossWikiSearch_004_noResults() {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(wikiCorporateURL);
		search.searchFor(SearchContent.SEARCH_PHRASE_NO_RESULTS);
		search.verifyNoPagination();
		search.verifyNoResultsCaption();
	}

	@Test(groups = {"CrossWikiSearch_005", "Search", "CrossWikiSearch"})
	public void crossWikiSearch_005_onePageResult() {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(wikiCorporateURL);
		search.searchFor(SearchContent.SEARCH_PHRASE_ONE_PAGE_RESULTS);
		search.verifyNoPagination();
	}

	@Test(dataProviderClass = CrossWikiSearchProvider.class,
		dataProvider = "getPushToTopQueries",
		groups = {"CrossWikiSearch_006", "Search", "CrossWikiSearch"})
	public void crossWikiSearch_006_pushToTop(String query, String wikiName) {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(wikiCorporateURL);
		search.searchFor(query);
		search.verifyFirstResultTitle(wikiName);
	}

	@Test(groups = {"CrossWikiSearchTests_007", "Search", "CrossWikiSearch"})
	public void crossWikiSearch_007_specialPromoteData() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(wikiCorporateURL);
		search.searchFor(SearchContent.SEARCH_PHRASE);
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
	public void crossWikiSearch_008_wikimatch(String expectedWikiTitle) {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(wikiCorporateURL);
		search.searchFor(SearchContent.SEARCH_PHRASE_RESULTS_SAME_ORDER);
		search.verifyQuery(expectedWikiTitle);
	}

	/**
	 * Navigate to http://www.wikia.com/index.php?title=Special:Search
	 * type: GTA V
	 * verify that GTA V wikia was found
	 * type: GTA 5
	 * verify that GTA V wikia was found
	 */
	@Test(enabled = false, groups = {"CrossWikiSearchTests_009", "Search", "CrossWikiSearch"})
	public void crossWikiSearch_009_romanNumbersMatch_PLA_1245() {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(wikiCorporateURL);
		search.searchFor(SearchContent.SEARCH_PHRASE_ROMAN_NUMBER);
		search.verifyQuery(SearchContent.WIKI_NAME);
		search.searchFor(SearchContent.SEARCH_PHRASE_DECIMAL_NUMBER);
		search.verifyQuery(SearchContent.WIKI_NAME);
	}

}
