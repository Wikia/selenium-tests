package com.wikia.webdriver.TestCases.SearchTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.SearchContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.DataProvider.CrossWikiSearchProvider;
import com.wikia.webdriver.Common.DataProvider.SearchDataProvider;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Search.CrossWikiSearch.CrossWikiSearchPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialPromotePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticleHomePage;

/**
 * Author: Artur Dwornik
 * Date: 29.03.13
 * Time: 11:22
 */
public class CrossWikiSearchTests extends NewTestTemplate {
	
	Credentials credentials = config.getCredentials();
	
	@Test(dataProviderClass = CrossWikiSearchProvider.class,
			dataProvider = "getExactMatchQueries",
			groups = {"CrossWikiSearchTests_001", "Search"})
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

	@Test(groups= {"CrossWikiSearchTests_002", "Search"})
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

	@Test(groups= {"CrossWikiSearchTests_003" , "Search"} )
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

	public void crossWikiSearch_004_wikiUrlmatch() {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(PageContent.wikiaGlobalUrl);
		search.searchFor(SearchContent.wikiName);
		search.verifyMatchResultUrl(SearchContent.expectedUrl);
	}

	@Test(groups = {"CrossWikiSearchTests_005", "Search"})
	public void crossWikiSearch_005_noResults() {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(PageContent.wikiaGlobalUrl);
		search.searchFor(SearchContent.searchPhraseNoResults);
		search.verifyNoPagination();
		search.verifyNoResultsCaption();
	}

	@Test(groups = {"CrossWikiSearchTests_006", "Search"})
	public void crossWikiSearch_006_onePageResult() {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(PageContent.wikiaGlobalUrl);
		search.searchFor(SearchContent.searchPhraseOnePageResults);
		search.verifyNoPagination();
	}
	@Test(dataProviderClass = CrossWikiSearchProvider.class,
			dataProvider = "getPushToTopQueries",
			groups = {"CrossWikiSearchTests_007", "Search"})
	public void crossWikiSearch_007_pushToTop(String query, String wikiName) {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(PageContent.wikiaGlobalUrl);
		CrossWikiSearchPageObject searchPage = search.searchFor(query);
		searchPage.verifyFirstResultTitle(wikiName);	
	}
	
	@Test(groups = {"CrossWikiSearchTests_008", "Search"})
	public void crossWikiSearch_008_specialPromoteData() {
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
	
	//TODO
//	@Test(dataProviderClass=SearchDataProvider.class,
//			dataProvider="getExpectedWikiResults",
//			groups = {"CrossWikiSearchTests_009", "Search"})
	public void crossWikiSearch_009_wikimatch( String expectedWikiTitle ) {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(PageContent.wikiaGlobalUrl);
		search.searchFor(SearchContent.searchPhraseResultsSameOrder);
		search.verifyQuery(expectedWikiTitle);
	}
	
	@Test(groups = {"CrossWikiSearchTests_010", "Search"})
	public void crossWikiSearch_010_romanNumbersMatch() {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(PageContent.wikiaGlobalUrl);
		search.searchFor(SearchContent.searchPhraseRomanNumber);
		search.verifyQuery(SearchContent.wikiName);
		search.searchFor(SearchContent.searchPhraseDecimalNumber);
		search.verifyQuery(SearchContent.wikiName);
	}
}
