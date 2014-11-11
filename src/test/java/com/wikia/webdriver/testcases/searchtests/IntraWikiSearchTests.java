package com.wikia.webdriver.testcases.searchtests;

import java.util.List;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.SearchContent;
import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.IntraWikiSearchProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.SearchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.search.intrawikisearch.IntraWikiSearchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.search.intrawikisearch.IntraWikiSearchPageObject.sortOptions;

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
 *		then select videos only option and verify:
 * 			1. the number of videos = 25
 *			2. the number of videos equals the number of play buttons
 *			3. video titles start with "file" prefix
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
			groups={"IntraWikiSearch_001", "IntraWikiSearchExactMatch", "Search"}
	)
	public void IntraWikiSearch_001_exactMatch(String query) {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(query);
		search.verifyFirstResult(query);
	}

	@Test(groups={"IntraWikiSearch_002", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_002_pagination() {
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

	@Test(groups={"IntraWikiSearch_003", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_003_resultsCount() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchPhraseResults);
		search.verifyResultsCount(resultsPerPage);
		search.clickNextPaginator();
		search.verifyResultsCount(resultsPerPage);
	}

	@Test(groups={"IntraWikiSearch_004", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_004_noResults() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchPhraseNoResults);
		search.verifyNoResults();
	}

	@Test(groups={"IntraWikiSearch_005", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_005_filtering() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchPhraseResults);
		search.selectPhotosVideos();
		search.verifyTitlesNotEmpty();
		search.selectPhotosOnly();
		search.verifyTitlesNotEmpty();
		search.verifyAllResultsImages(resultsPerPage);
		search.selectVideosOnly();
		search.verifyTitlesNotEmpty();
		search.verifyAllResultsVideos(resultsPerPage);
	}

	@Test(groups={"IntraWikiSearch_006", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_006_sortingVideos() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchPhraseResults);
		search.selectPhotosVideos();
		search.selectVideosOnly();
		search.verifyTitlesNotEmpty();
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

	@Test(groups={"IntraWikiSearch_007", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_007_sortingImages() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchPhraseResults);
		search.selectPhotosVideos();
		search.selectPhotosOnly();
		search.verifyTitlesNotEmpty();
		search.sortBy(sortOptions.relevancy);
		List<String> titles1 = search.getTitles();
		search.sortBy(sortOptions.publishDate);
		List<String> titles2 = search.getTitles();
		search.compareTitleListsNotEquals(titles1, titles2);
	}

	@Test(groups={"IntraWikiSearch_008", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_008_dropDownSuggestions() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.verifySuggestions(searchPhraseSuggestions);
	}

	@Test(groups={"IntraWikiSearch_009", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_009_languageTranslation() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchPhraseResults);
		search.addQqxUselang();
		search.verifyLanguageTranslation();
	}

	@Test(groups={"IntraWikiSearch_010", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_010_imagesAndVideosOnly() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchPhraseResults);
		search.selectPhotosVideos();
		search.selectPhotosOnly();
		search.verifyPhotosOnly();
		search.selectVideosOnly();
		search.verifyVideosOnly();
	}

	@Test(groups={"IntraWikiSearch_011", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_011_defaultNamespaces() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchPhraseResults);
		search.clickAdvancedButton();
		search.verifyDefaultNamespaces();
	}

	@Test(groups={"IntraWikiSearch_012", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_012_extensionNotNeeded() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchResultWithExtension);
		search.selectPhotosVideos();
		search.verifyFirstResultExtension(searchResultWithExtension);
	}

	@Test(dataProviderClass=IntraWikiSearchProvider.class,
			dataProvider="getNamespaces",
			groups={"IntraWikiSearch_013", "IntraWikiSearch", "Search"}
	)
	public void IntraWikiSearch_013_namespaces(String searchPhrase, String namespace) {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchPhrase);
		search.selectAllAdvancedOptions();
		SearchPageObject searchPage = new SearchPageObject(driver);
		searchPage.clickSearchButton();
		search.verifyNamespace(namespace);
	}

	@Test(groups={"IntraWikiSearch_014", "intrawikisearch", "search"})
	public void IntraWikiSearch_014_searchPageOpened() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor("");
		search.verifySearchPageOpened();
	}

	@Test(groups={"IntraWikiSearch_015", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_015_topModule_PLA_1514() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchPhraseResults);
		search.verifyTopModule();
	}

	@Test(groups={"IntraWikiSearch_016", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_016_communityPushToTopWikiResult() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(communityWiki);
		search.searchForInGlobalNavIfPresent(searchWiki);
		search.verifyPushToTopWikiTitle(searchWiki);
		search.verifyPushToTopWikiThumbnail();
	}

	@Test(enabled = false, groups={"IntraWikiSearch_017", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_017_searchSuggestionsVisibility() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(searchSuggestionsWiki);
		search.verifyNewSuggestionsTextAndImages(SearchContent.searchPhraseNewSuggestions);
	}
}
