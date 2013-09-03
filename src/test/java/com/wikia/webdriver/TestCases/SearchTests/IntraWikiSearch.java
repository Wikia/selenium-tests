package com.wikia.webdriver.TestCases.SearchTests;

import java.util.List;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.IntraWikiSearchProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Search.IntraWikiSearch.IntraWikiSearchPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Search.IntraWikiSearch.IntraWikiSearchPageObject.sortOptions;

public class IntraWikiSearch extends NewTestTemplate {

	private String testedWiki;

	public IntraWikiSearch() {
		UrlBuilder urlBuilder = new UrlBuilder(config.getEnv());
		testedWiki = urlBuilder.getUrlForWiki("muppet");
	}

	/*
	 *
		STAPI103AT01: Verify that all text messages in intra-wiki search are translatable
			STAPI103AT02: Verify that article description, URL and article title are displayed in SRP
			STAPI103AT03: Verify that for exact matches within namespace search, search result is pushed to top
			STAPI103AT04: Verify that performing an "All files" search will display always thumbnails if there are results
			STAPI103AT06: Verify searching for the EXACT name of a video which contains apostrophe displays correct result at the top (i.e.-”File:Cats & Dogs - The dogs' headquarters”)
			STAPI103AT07: Verify searching for the EXACT name of a video which contains colon displays correct result at the top (i.e - “File:Funny Cats”)
			STAPI103AT08: Verify searching for the EXACT name of a video which contains points displays correct result at the top
			STAPI103AT09: Verify searching for the EXACT name of a video which contains parentheses displays correct result at the top
		STAPI103AT10: Verify image extension must not be included when trying to find a certain image (lion2 and lion2.gif)
		STAPI103AT11: Verify clicking “Photos only” option will not display videos
		STAPI103AT12: Verify clicking “Videos only” option will not display photos
		STAPI103AT13: Verify all search image filtering options are appearing in en and non-en wikis
			STAPI103AT13: Verify all search video filtering options are appearing in en and non-en wikis
		STAPI103AT14: Verify that searching for “Characters” in marvel wiki will display namespace info: “Category:Characters”
		STAPI103AT15: Verify performing a people search will only display results from user namespace
		STAPI103AT16: Verify performing a user blog search will only display results from user blog namespace
		STAPI103AT17: Verify performing a Photos and Videos search will only display results from file namespace
		STAPI103AT18: Verify when performing intra-wiki search the default namespaces which are checked in their checkboxes must be main and category
	 */

	private static final int resultsPerPage = 25;
	private static final String searchPhraseResults = "a";
	private static final String searchPaginationResults = "what";
	private static final String searchPhraseNoResults = "qazwsxedcrfvtgb";
	private static final String searchPhraseSuggestions = "Gon";

	@Test(dataProviderClass=IntraWikiSearchProvider.class,
			dataProvider="getArticleName",
			groups={"intraSearch001", "intraSearch", "Search"})
	public void intraWikiSearch_001_exactMatch(String query) {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver, testedWiki);
		search.searchFor(query);
		search.verifyFirstResult(query);
	}

	@Test(groups={"intraSearch002", "intraSearch", "Search"})
	public void intraWikiSearch_002_pagination() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver, testedWiki);
		search.searchFor(searchPaginationResults);
		String firstResult = search.getInnerText();
		search.verifyPagination();
		search.clickNextPaginator();
		search.verifyArticlesNotTheSame(firstResult);
		search.verifyPagination();
		search.clickPrevPaginator();
		search.verifyArticlesTheSame(firstResult);
		search.verifyPagination();
		search.verifyLastResultPage();
	}

	@Test(groups={"intraSearch003", "intraSearch", "Search"})
	public void intraWikiSearch_003_resultsCount() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver, testedWiki);
		search.searchFor(searchPhraseResults);
		search.verifyResultsCount(resultsPerPage);
		search.clickNextPaginator();
		search.verifyResultsCount(resultsPerPage);
	}

	@Test(groups={"intraSearch004", "intraSearch", "Search"})
	public void intraWikiSearch_004_noResults() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver, testedWiki);
		search.searchFor(searchPhraseNoResults);
		search.verifyNoResults();
	}

	@Test(groups={"intraSearch005", "intraSearch", "Search"})
	public void intraWikiSearch_005_filtering() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver, testedWiki);
		search.searchFor(searchPhraseResults);
		search.selectPhotosVideos();
		search.verifyNamespacesInTitles(URLsContent.fileNameSpace);
		search.selectPhotosOnly();
		search.verifyNamespacesInTitles(URLsContent.fileNameSpace);
		search.verifyAllResultsImages(resultsPerPage);
		search.selectVideosOnly();
		search.verifyNamespacesInTitles(URLsContent.fileNameSpace);
		search.verifyAllResultsVideos(resultsPerPage);
	}

	@Test(groups={"intraSearch006", "intraSearch", "Search"})
	public void intraWikiSearch_006_sortingVideos() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver, testedWiki);
		search.searchFor(searchPhraseResults);
		search.selectPhotosVideos();
		search.selectVideosOnly();
		search.verifyNamespacesInTitles(URLsContent.fileNameSpace);
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

	@Test(groups={"intraSearch007", "intraSearch", "Search"})
	public void intraWikiSearch_007_sortingImages() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver, testedWiki);
		search.searchFor(searchPhraseResults);
		search.selectPhotosVideos();
		search.selectPhotosOnly();
		search.verifyNamespacesInTitles(URLsContent.fileNameSpace);
		search.sortBy(sortOptions.relevancy);
		List<String> titles1 = search.getTitles();
		search.sortBy(sortOptions.publishDate);
		List<String> titles2 = search.getTitles();
		search.compareTitleListsNotEquals(titles1, titles2);
	}

	@Test(groups={"intraSearch008", "intraSearch", "Search"})
	public void intraWikiSearch_008_dropDownSuggestions() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver, testedWiki);
		search.typeSearchQuery(searchPhraseSuggestions);
		search.verifySuggestions(searchPhraseSuggestions);
	}
}
