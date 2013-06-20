package com.wikia.webdriver.TestCases.SearchTests;

import java.util.List;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.DataProvider.IntraWikiSearchProvider;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Search.IntraWikiSearch.IntraWikiSearchPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Search.IntraWikiSearch.IntraWikiSearchPageObject.sortOptions;

public class IntraWikiSearch extends TestTemplate{

	/*
	 *
  	STAPI103AT11: Verify that text messages in intra-wiki search are translatable
    STAPI103AT12: Verify that wiki description, URL and article title are displayed in SRP
    STAPI103AT13: Verify that for exact matches within namespace search there exists namespace information in search results titles
    STAPI103AT14: Verify that performing an "All files" search will display always thumbnails if there are results
    STAPI103AT15: Verify that performing an "All files" search for a string containing a colon will display always thumbnails if there are results
    STAPI103AT16: Verify searching for the EXACT name of a video which contains apostrophe displays correct result at the top
    http://mediawiki119.wikia.com/wiki/File:Frank_Zappa-Apostrophe%27
    STAPI103AT17: Verify searching for the EXACT name of a video which contains colon displays correct result at the top
    STAPI103AT18: Verify searching for the EXACT name of a video which contains points displays correct result at the top
    http://mediawiki119.wikia.com/wiki/File:The_full_stop_punctuation_blues._by.Darryl_K..avi
    STAPI103AT19: Verify searching for the EXACT name of a video which contains parentheses displays correct result at the top
    http://mediawiki119.wikia.com/wiki/File:The_Lonely_Island_Performs_%22Semicolon%22_with_Alanis_Morissette
    STAPI103AT20: Verify in article page that auto-dropdown works correctly after populating input field with 3 characters +
    STAPI103AT21: Verify image extension must not be included when trying to find a certain image
    STAPI103AT22: Verify clicking “Photos only” option will not display videos +
    STAPI103AT23: Verify clicking “Videos only” option will not display photos +


	 */
	private static final int resultsPerPage = 25;
	private static final String searchPhraseResults = "a";
	private static final String searchPhraseNoResults = "qazwsxedcrfvtgb";
	private static final String searchPhraseSuggestions = "pmg";

	@Test(dataProviderClass=IntraWikiSearchProvider.class,
			dataProvider="getArticleName",
			groups={"intraSearch001", "Search"})
	public void intraWikiSearch_001_exactMatch(String query) {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openIntraWikiSearch();
		search.searchFor(query);
		search.verifyFirstResult(query);
	}

	@Test(groups={"intraSearch002", "Search"})
	public void intraWikiSearch_002_pagination() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openIntraWikiSearch();
		search.searchFor(searchPhraseResults);
		search.verifyPagination();
		search.clickNextPaginator();
		search.verifyPagination();
		search.clickPrevPaginator();
		search.verifyPagination();
		//TODO go to last and verify
	}

	@Test(groups={"intraSearch003", "Search"})
	public void intraWikiSearch_003_resultsCount() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openIntraWikiSearch();
		search.searchFor(searchPhraseResults);
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
		search.searchFor(searchPhraseResults);
		search.selectPhotosVideos();
		search.verifyNamespacesInTitles(URLsContent.fileNS);
		search.selectPhotosOnly();
		search.verifyNamespacesInTitles(URLsContent.fileNS);
		search.verifyAllResultsImages(resultsPerPage);
		search.verifyNamespacesInTitles(URLsContent.fileNS);
		search.selectVideosOnly();
		search.verifyAllResultsVideos(resultsPerPage);
	}

	@Test(groups={"intraSearch006", "Search"})
	public void intraWikiSearch_006_sorting() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openIntraWikiSearch();
		search.searchFor(searchPhraseResults);
		search.selectPhotosVideos();
		search.verifyNamespacesInTitles(URLsContent.fileNS);
		search.sortBy(sortOptions.duration);
		List<String> titles1 = search.getTitles();
		search.sortBy(sortOptions.relevancy);
		List<String> titles2 = search.getTitles();
		search.sortBy(sortOptions.publishDate);
		List<String> titles3 = search.getTitles();
		search.compareTitleListsNotEquals(titles1, titles2);
		search.compareTitleListsNotEquals(titles1, titles3);
		search.compareTitleListsNotEquals(titles2, titles3);

	}

	@Test(groups={"intraSearch007", "Search"})
	public void intraWikiSearch_007_dropDownSuggestions() {
		WikiBasePageObject page = new WikiBasePageObject(driver);
		page.openWikiPage();
		page.typeSearchQuery(searchPhraseSuggestions);
		page.verifySuggestionDropdown(searchPhraseSuggestions);
	}
}
