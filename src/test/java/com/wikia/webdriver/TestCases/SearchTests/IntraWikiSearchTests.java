package com.wikia.webdriver.TestCases.SearchTests;

import java.util.List;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.SearchContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.IntraWikiSearchProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SearchPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Search.IntraWikiSearch.IntraWikiSearchPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Search.IntraWikiSearch.IntraWikiSearchPageObject.sortOptions;

/*
 *  1. Search for different phrases and verify if they give correct first result
 *  2. Check search page pagination
 *  3. Verify number of results on page
 *  4. Search for not existing phrase and verify there is no results
 *  5. Search for some phrase and verify filtering options work correctly and give correct results
 *  6. Search for some phrase and verify sorting options for video give correct results
 *  7. Search for some phrase and verify sorting options for images give correct results
 *  8. Type at least 3 chars and verify suggestions are displaying and contain given phrase
 *  9. Verify search page hubs and titles are translatable
 *  10. Select photos only option and verify there are only photos,
 *		then select videos only option and verify there are only videos
 *  11. Verify if there are correct advanced option set as a default
 *  12. Search for some image without typing extension (.jpg) and verify photo is found
 *  13. Search for different phrases and verify there are correct namespaces in result titles
 *  14. Search for empty field and verify if search page is opened
 *  15. Verify top module
 *  16. Verify push to top is working in community.wikia.com
 */

public class IntraWikiSearchTests extends NewTestTemplate {

	private String testedWiki;
	private String communityWiki;
	private String searchSuggestionsWiki;

	public IntraWikiSearchTests() {
		UrlBuilder urlBuilder = new UrlBuilder(config.getEnv());
		testedWiki = urlBuilder.getUrlForWiki("muppet");
		communityWiki = urlBuilder.getUrlForWiki("community");
		searchSuggestionsWiki = urlBuilder.getUrlForWiki("communitycouncil");
	}

	private static final int resultsPerPage = 25;
	private static final String searchPhraseResults = "a";
	private static final String searchPaginationResults = "what";
	private static final String searchResultWithExtension = "betweenlions";
	private static final String searchPhraseNoResults = "qazwsxedcrfvtgb";
	private static final String searchPhraseSuggestions = "Gon";
	private static final String searchWiki = "Marvel";

	@Test(dataProviderClass=IntraWikiSearchProvider.class,
			dataProvider="getArticleName",
			groups={"IntraSearch001", "IntraWikiSearchExactMatch", "Search"}
	)
	public void intraWikiSearch_001_exactMatch(String query) {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(query);
		search.verifyFirstResult(query);
	}

	@Test(groups={"IntraSearch002", "IntraWikiSearch", "Search"})
	public void intraWikiSearch_002_pagination() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchPaginationResults);
		String firstResult = search.getTitleInnerText();
		search.verifyPagination();
		search.clickNextPaginator();
		search.verifyFirstArticleNameNotTheSame(firstResult);
		search.verifyPagination();
		search.clickPrevPaginator();
		search.verifyFirstArticleNameTheSame(firstResult);
		search.verifyPagination();
		search.verifyLastResultPage();
	}

	@Test(groups={"IntraSearch003", "IntraWikiSearch", "Search"})
	public void intraWikiSearch_003_resultsCount() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchPhraseResults);
		search.verifyResultsCount(resultsPerPage);
		search.clickNextPaginator();
		search.verifyResultsCount(resultsPerPage);
	}

	@Test(groups={"IntraSearch004", "IntraWikiSearch", "Search"})
	public void intraWikiSearch_004_noResults() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchPhraseNoResults);
		search.verifyNoResults();
	}

	@Test(groups={"IntraSearch005", "IntraWikiSearch", "Search"})
	public void intraWikiSearch_005_filtering() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
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

	@Test(groups={"IntraSearch006", "IntraWikiSearch", "Search"})
	public void intraWikiSearch_006_sortingVideos() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
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

	@Test(groups={"IntraSearch007", "IntraWikiSearch", "Search"})
	public void intraWikiSearch_007_sortingImages() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
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

	@Test(groups={"IntraSearch008", "IntraWikiSearch", "Search"})
	public void intraWikiSearch_008_dropDownSuggestions() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.verifySuggestions(searchPhraseSuggestions);
	}

	@Test(groups={"IntraSearch009", "IntraWikiSearch", "Search"})
	public void intraWikiSearch_009_languageTranslation() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchPhraseResults);
		search.addQqxUselang();
		search.verifyLanguageTranslation();
	}

	@Test(groups={"IntraSearch010", "IntraWikiSearch", "Search"})
	public void intraWikiSearch_010_imagesAndVideosOnly() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchPhraseResults);
		search.selectPhotosVideos();
		search.selectPhotosOnly();
		search.verifyPhotosOnly();
		search.selectVideosOnly();
		search.verifyVideosOnly();
	}

	@Test(groups={"IntraSearch011", "IntraWikiSearch", "Search"})
	public void intraWikiSearch_011_defaultNamespaces() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchPhraseResults);
		search.clickAdvancedButton();
		search.verifyDefaultNamespaces();
	}

	@Test(groups={"IntraSearch012", "IntraWikiSearch", "Search"})
	public void intraWikiSearch_012_extensionNotNeeded() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchResultWithExtension);
		search.selectPhotosVideos();
		search.verifyFirstResultExtension(searchResultWithExtension);
	}

	@Test(dataProviderClass=IntraWikiSearchProvider.class,
			dataProvider="getNamespaces",
			groups={"IntraSearch013", "IntraWikiSearch", "Search"}
	)
	public void intraWikiSearch_013_namespaces(String searchPhrase, String namespace) {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchPhrase);
		search.selectAllAdvancedOptions();
		SearchPageObject searchPage = new SearchPageObject(driver);
		searchPage.clickSearchButton();
		search.verifyNamespace(namespace);
	}

	@Test(groups={"IntraSearch014", "IntraWikiSearch", "Search"})
	public void intraWikiSearch_014_searchPageOpened() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor("");
		search.verifySearchPageOpened();
	}

	@Test(groups={"IntraSearch015", "IntraWikiSearch", "Search"})
	public void intraWikiSearch_015_topModule() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchPhraseResults);
		search.verifyTopModule();
	}

	@Test(groups={"IntraSearch016", "IntraWikiSearch", "Search"})
	public void intraWikiSearch_016_communityPushToTopWikiResult() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(communityWiki);
		search.searchFor(searchWiki);
		search.verifyPushToTopWikiTitle(searchWiki);
		search.verifyPushToTopWikiThumbnail();
	}

	@Test(enabled = false, groups={"IntraSearch017", "IntraWikiSearch", "Search"})
	public void intraWikiSearch_017_searchSuggestionsVisibility() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(searchSuggestionsWiki);
		search.verifyNewSuggestionsTextAndImages(SearchContent.searchPhraseNewSuggestions);
	}
}
