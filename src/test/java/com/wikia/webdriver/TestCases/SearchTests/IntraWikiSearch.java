package com.wikia.webdriver.TestCases.SearchTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.DataProvider.IntraWikiSearchProvider;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Search.IntraWikiSearch.IntraWikiSearchPageObject;

public class IntraWikiSearch extends TestTemplate{

	/*
	 *
  	STAPI103AT11: Verify that text messages in intra-wiki search are translatable
    STAPI103AT12: Verify that wiki description, URL and article title are displayed in SRP
    STAPI103AT13: Verify that for exact matches within namespace search there exists namespace information in search results titles
    STAPI103AT14: Verify that performing an "All files" search will display always thumbnails if there are results
    STAPI103AT15: Verify that performing an "All files" search for a string containing a colon will display always thumbnails if there are results
    STAPI103AT16: Verify searching for the EXACT name of a video which contains apostrophe displays correct result at the top
    STAPI103AT17: Verify searching for the EXACT name of a video which contains colon displays correct result at the top
    STAPI103AT18: Verify searching for the EXACT name of a video which contains points displays correct result at the top
    STAPI103AT19: Verify searching for the EXACT name of a video which contains parentheses displays correct result at the top
    STAPI103AT20: Verify in article page that auto-dropdown works correctly after populating input field with 3 characters
    STAPI103AT21: Verify image extension must not be included when trying to find a certain image
    STAPI103AT22: Verify clicking “Photos only” option will not display videos
    STAPI103AT23: Verify clicking “Videos only” option will not display photos

	 */
	private static final int resultsPerPage = 25;
	private static final String searchPhrase = "a";
	private static final String searchPhraseNoResults = "qazwsxedcrfvtgb";

	@Test(dataProviderClass=IntraWikiSearchProvider.class,
			dataProvider="getArticleName",
			groups={"intraSearch001", "Search"})
	public void intraWikiSearch_001_exactMatch(String query){
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openIntraWikiSearch();
		search.searchFor(query);
		search.verifyFirstResult(query);
	}

	@Test(groups={"intraSearch002", "Search"})
	public void intraWikiSearch_002_pagination(){
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openIntraWikiSearch();
		search.searchFor(searchPhrase);
		search.verifyPagination();
		search.clickNextPaginator();
		search.verifyPagination();
		search.clickPrevPaginator();
		search.verifyPagination();
	}

	@Test(groups={"intraSearch003", "Search"})
	public void intraWikiSearch_003_resultsCount() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openIntraWikiSearch();
		search.searchFor(searchPhrase);
		search.verifyResultsCount(resultsPerPage);
		search.clickNextPaginator();
		search.verifyResultsCount(resultsPerPage);
	}

	@Test(groups={"intraSearch004", "Search"})
	public void intraWikiSearch_004_noResults() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openIntraWikiSearch();
		search.searchFor(searchPhraseNoResults);
		search.verifyNoResults();
	}

	@Test(groups={"intraSearch005", "Search"})
	public void intraWikiSearch_005_filtering() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openIntraWikiSearch();
		search.searchFor(searchPhrase);
		search.selectPhotosVideos();
		search.verifyNamespacesInTitles(URLsContent.fileNS);
		search.selectPhotosOnly();
		search.verifyNamespacesInTitles(URLsContent.fileNS);
		search.verifyAllResultsImages(resultsPerPage);
		search.verifyNamespacesInTitles(URLsContent.fileNS);
		search.selectVideosOnly();
		search.verifyAllResultsVideos(resultsPerPage);
	}
}
