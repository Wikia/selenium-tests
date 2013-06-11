package com.wikia.webdriver.TestCases.SearchTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.CrossWikiSearchProvider;
import com.wikia.webdriver.Common.DataProvider.SearchDataProvider;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CrossWikiSearch.CrossWikiSearchPage;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticleHomePage;

/**
 * Author: Artur Dwornik
 * Date: 29.03.13
 * Time: 11:22
 */
public class CrossWikiSearchTests extends TestTemplate {
	private static final int resultsPerPage = 7;
	private static final String searchPhrase = "muppets";

	@Test(dataProviderClass = CrossWikiSearchProvider.class,
			dataProvider = "getExactMatchQueries",
			groups = {"CrossWikiSearchTests_001", "Search"})
	public void crossWikiSearch_001_exactMatch(String query, String wikiName, String vertical) {
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
		CrossWikiSearchPage searchPage = home.searchFor(query);
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
		CrossWikiSearchPage searchPage = home.searchFor(searchPhrase);
		// verify results pos parameter for first page
		searchPage.verifyResultsPosForPage(0, resultsPerPage);
		searchPage.verifyResultsCount(resultsPerPage);
		searchPage.nextPage();
		// verify results pos parameter for second page
		searchPage.verifyResultsPosForPage(1, resultsPerPage);
		searchPage.verifyResultsCount(resultsPerPage);
		searchPage.prevPage();
		// verify results pos parameter for first page
		searchPage.verifyResultsPosForPage(0, resultsPerPage);
		searchPage.verifyResultsCount(resultsPerPage);
	}

	@Test(groups= {"CrossWikiSearchTests_003" , "Search"} )
	public void crossWikiSearch_003_resultClick() {
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
		CrossWikiSearchPage searchPage = home.searchFor(searchPhrase);
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
		CrossWikiSearchPage search = new CrossWikiSearchPage(driver);
		search.goToSearchPage(PageContent.wikiaGlobalUrl);
		CrossWikiSearchPage searched = search.searchFor(searchTerm);
		searched.verifyMatchResultUrl(expectedUrl);
	}
}
