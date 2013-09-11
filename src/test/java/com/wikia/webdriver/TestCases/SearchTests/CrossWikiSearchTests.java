package com.wikia.webdriver.TestCases.SearchTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.DataProvider.CrossWikiSearchProvider;
import com.wikia.webdriver.Common.DataProvider.SearchDataProvider;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Search.CrossWikiSearch.CrossWikiSearchPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticleHomePage;

/**
 * Author: Artur Dwornik
 * Date: 29.03.13
 * Time: 11:22
 */
public class CrossWikiSearchTests extends NewTestTemplate {
	Credentials credentials = config.getCredentials();
	private static final int resultsPerPage = 10;
	private static final String searchPhrase = "muppets";
	private static final String searchPhraseNoResults = "qazwsxedcrfvtgb";
	private static final String searchPhraseOnePageResults = "muppet_wiki";
	private static final String searchPhraseResultsSameOrder = "marvel";
	private static final String searchPhraseDecimalNumber = "GTA 5";
	private static final String searchPhraseRomanNumber = "GTA V";
	


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
		CrossWikiSearchPageObject searchPage = home.searchFor(searchPhrase);
		// verify results pos parameter for first page
		searchPage.verifyResultsPosForPage(0, resultsPerPage);
		searchPage.verifyResultsCount(resultsPerPage);
		searchPage.verifyThumbnails(resultsPerPage);
		searchPage.verifyDescription(resultsPerPage);
		searchPage.verifyStatistics(resultsPerPage);
		searchPage.nextPage();
		// verify results pos parameter for second page
		searchPage.verifyResultsPosForPage(1, resultsPerPage);
		searchPage.verifyResultsCount(resultsPerPage);
		searchPage.verifyThumbnails(resultsPerPage);
		searchPage.verifyDescription(resultsPerPage);
		searchPage.verifyStatistics(resultsPerPage);
		searchPage.prevPage();
		// verify results pos parameter for first page
		searchPage.verifyResultsPosForPage(0, resultsPerPage);
		searchPage.verifyResultsCount(resultsPerPage);
		searchPage.verifyThumbnails(resultsPerPage);
		searchPage.verifyDescription(resultsPerPage);
		searchPage.verifyStatistics(resultsPerPage);
	}

	@Test(groups= {"CrossWikiSearchTests_003" , "Search"} )
	public void crossWikiSearch_003_resultClick() {
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
		CrossWikiSearchPageObject searchPage = home.searchFor(searchPhrase);
		WikiArticleHomePage wikiArticleHomePage = searchPage.openResult(0);
		wikiArticleHomePage.verifyThisIsWikiHomePage();
		searchPage.navigateBack();
		searchPage.openResult(1);
		wikiArticleHomePage.verifyThisIsWikiHomePage();
	}

	@Test(dataProviderClass=SearchDataProvider.class,
			dataProvider="getCrossWikiTermsAndUrls",
			groups = {"CrossWikiSearchTests_004", "Search"})
	public void crossWikiSearch_004_wikimatch( String searchTerm, String expectedUrl ) {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(PageContent.wikiaGlobalUrl);
		search.searchFor(searchTerm);
		search.verifyMatchResultUrl(expectedUrl);
	}

	@Test(groups = {"CrossWikiSearchTests_005", "Search"})
	public void crossWikiSearch_005_noResults() {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(PageContent.wikiaGlobalUrl);
		search.searchFor(searchPhraseNoResults);
		search.verifyNoPagination();
		search.verifyNoResultsCaption();
	}

	@Test(groups = {"CrossWikiSearchTests_006", "Search"})
	public void crossWikiSearch_006_onePageResult() {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(PageContent.wikiaGlobalUrl);
		search.searchFor(searchPhraseOnePageResults);
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
		CrossWikiSearchPageObject searchPage = search.searchFor(searchPhrase);
		String searchDescription = searchPage.getFirstDescription();
		String searchImage = searchPage.getFirstImageText();
		searchPage.openSpecialPromote();
		searchPage.verifyCrossWikiSearchDescription(searchDescription);
		searchPage.verifyCrossWikiSearchImage(searchImage);
	}
	
	@Test(dataProviderClass=SearchDataProvider.class,
			dataProvider="getExpectedWikiResults",
			groups = {"CrossWikiSearchTests_009", "Search"})
	public void crossWikiSearch_009_wikimatch( String expectedUrl ) {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(PageContent.wikiaGlobalUrl);
		search.searchFor(searchPhraseResultsSameOrder);
		search.verifyMatchResultUrl(expectedUrl);
	}
	
	@Test(groups = {"CrossWikiSearchTests_010", "Search"})
	public void crossWikiSearch_010_romanNumbersMatch() {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(PageContent.wikiaGlobalUrl);
		search.searchFor(searchPhraseRomanNumber);
		search.verifyFirstGTAResult();
		search.searchFor(searchPhraseDecimalNumber);
		search.verifyFourthGTAResult();
	}
}


