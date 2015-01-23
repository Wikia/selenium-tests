package com.wikia.webdriver.testcases.searchtests;

import com.wikia.webdriver.common.contentpatterns.SearchContent;
import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.IntraWikiSearchProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.SearchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.search.intrawikiSearch.IntraWikiSearchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.search.intrawikiSearch.IntraWikiSearchPageObject.sortOptions;
import org.testng.annotations.Test;

import java.util.List;

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

	private static final int RESULTS_PER_PAGE = 25;
	private static final String SEARCH_PHRASE_RESULTS = "a";
	private static final String SEARCH_PAGINATION_RESULTS = "what";
	private static final String SEARCH_RESULT_WITH_EXTENSION = "betweenlions";
	private static final String SEARCH_PHRASE_NO_RESULTS = "qazwsxedcrfvtgb";
	private static final String SEARCH_PHRASE_SUGGESTIONS = "Gon";
	private static final String SEARCH_WIKI = "Marvel";

	@Test(groups = {"IntraWikiSearch_002", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_002_pagination() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(SEARCH_PAGINATION_RESULTS);
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

	@Test(groups = {"IntraWikiSearch_003", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_003_resultsCount() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(SEARCH_PHRASE_RESULTS);
		search.verifyResultsCount(RESULTS_PER_PAGE);
		search.clickNextPaginator();
		search.verifyResultsCount(RESULTS_PER_PAGE);
	}

	@Test(groups = {"IntraWikiSearch_004", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_004_noResults() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(SEARCH_PHRASE_NO_RESULTS);
		search.verifyNoResults();
	}

	@Test(groups = {"IntraWikiSearch_005", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_005_filtering() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(SEARCH_PHRASE_RESULTS);
		search.selectPhotosVideos();
		search.verifyTitlesNotEmpty();
		search.selectPhotosOnly();
		search.verifyTitlesNotEmpty();
		search.verifyAllResultsImages(RESULTS_PER_PAGE);
		search.selectVideosOnly();
		search.verifyTitlesNotEmpty();
		search.verifyAllResultsVideos(RESULTS_PER_PAGE);
	}

	@Test(groups = {"IntraWikiSearch_006", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_006_sortingVideos() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(SEARCH_PHRASE_RESULTS);
		search.selectPhotosVideos();
		search.selectVideosOnly();
		search.verifyTitlesNotEmpty();
		search.sortBy(sortOptions.DURATION);
		List<String> titles1 = search.getTitles();
		search.sortBy(sortOptions.RELEVANCY);
		List<String> titles2 = search.getTitles();
		search.sortBy(sortOptions.PUBLISH_DATE);
		List<String> titles3 = search.getTitles();
		search.compareTitleListsNotEquals(titles1, titles2);
		search.compareTitleListsNotEquals(titles1, titles3);
		search.compareTitleListsNotEquals(titles2, titles3);
	}

	@Test(groups = {"IntraWikiSearch_007", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_007_sortingImages() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(SEARCH_PHRASE_RESULTS);
		search.selectPhotosVideos();
		search.selectPhotosOnly();
		search.verifyTitlesNotEmpty();
		search.sortBy(sortOptions.RELEVANCY);
		List<String> titles1 = search.getTitles();
		search.sortBy(sortOptions.PUBLISH_DATE);
		List<String> titles2 = search.getTitles();
		search.compareTitleListsNotEquals(titles1, titles2);
	}

	@Test(groups = {"IntraWikiSearch_008", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_008_dropDownSuggestions() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.verifySuggestions(SEARCH_PHRASE_SUGGESTIONS);
	}

	@Test(groups = {"IntraWikiSearch_009", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_009_languageTranslation() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(SEARCH_PHRASE_RESULTS);
		search.addQqxUselang();
		search.verifyLanguageTranslation();
	}

	@Test(groups = {"IntraWikiSearch_010", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_010_imagesAndVideosOnly() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(SEARCH_PHRASE_RESULTS);
		search.selectPhotosVideos();
		search.selectPhotosOnly();
		search.verifyPhotosOnly();
		search.selectVideosOnly();
		search.verifyVideosOnly();
	}

	@Test(groups = {"IntraWikiSearch_011", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_011_defaultNamespaces() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(SEARCH_PHRASE_RESULTS);
		search.clickAdvancedButton();
		search.verifyDefaultNamespaces();
	}

	@Test(groups = {"IntraWikiSearch_012", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_012_extensionNotNeeded() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(SEARCH_RESULT_WITH_EXTENSION);
		search.selectPhotosVideos();
		search.verifyFirstResultExtension(SEARCH_RESULT_WITH_EXTENSION);
	}

	@Test(dataProviderClass = IntraWikiSearchProvider.class,
		dataProvider = "getNamespaces",
		groups = {"IntraWikiSearch_013", "IntraWikiSearch", "Search"}
	)
	public void IntraWikiSearch_013_namespaces(String searchPhrase, String namespace) {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(searchPhrase);
		search.selectAllAdvancedOptions();
		SearchPageObject searchPage = new SearchPageObject(driver);
		searchPage.clickSearchButton();
		searchPage.setSearchTab(SearchPageObject.SearchTab.EVERYTHING);
		search.verifyNamespace(namespace);
	}

	@Test(groups = {"IntraWikiSearch_014", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_014_searchPageOpened() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor("");
		search.verifySearchPageOpened();
	}

	@Test(groups = {"IntraWikiSearch_015", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_015_topModule() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(testedWiki);
		search.searchFor(SEARCH_PHRASE_RESULTS);
		search.verifyTopModule();
	}

	@Test(groups = {"IntraWikiSearch_016", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_016_communityPushToTopWikiResult() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(communityWiki);
		search.searchForInGlobalNavIfPresent(SEARCH_WIKI);
		search.verifyPushToTopWikiTitle(SEARCH_WIKI);
		search.verifyPushToTopWikiThumbnail();
	}

	@Test(enabled = false, groups = {"IntraWikiSearch_017", "IntraWikiSearch", "Search"})
	public void IntraWikiSearch_017_searchSuggestionsVisibility() {
		IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
		search.openWikiPage(searchSuggestionsWiki);
		search.verifyNewSuggestionsTextAndImages(SearchContent.SEARCH_PHRASE_NEW_SUGGESTIONS);
	}
}
