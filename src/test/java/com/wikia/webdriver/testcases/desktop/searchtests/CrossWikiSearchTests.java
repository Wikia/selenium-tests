package com.wikia.webdriver.testcases.desktop.searchtests;

import com.wikia.webdriver.common.contentpatterns.SearchContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.dataprovider.CrossWikiSearchProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.search.crosswikisearch.CrossWikiSearchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.WikiArticleHomePage;

import org.testng.annotations.Test;

/**
 * Author: Artur Dwornik & Rodrigo Molinero Gomez Date: 29.03.13 Time: 11:22 STAPI01: Verify that
 * adding only underscores, only dashes, no spaces, or wiki domain exact match for a wiki will
 * display as first result STAPI02: Verify that pagination is working correctly for queries which
 * give more than 10 results STAPI03: Verify that user is redirect to a wiki home page when clicking
 * results from cross wiki search STAPI04: Verify that no pagination or description is displayed
 * when there are no results STAPI05: Verify that no pagination is displayed when there are results
 * but they are less than 10 STAPI06: Verify that push to top exact match is working correctly for
 * different wikis STAPI09: Verify that searching for a query with either roman or decimal numbers
 * will display expected result in first page
 */
@Execute(onWikia = "community-search")
@Test(groups = "CrossWikiSearch")
public class CrossWikiSearchTests extends NewTestTemplate {

  private static final String LACK_OF_IMAGE = "Thumbnail does not contain image";

  @Test(dataProviderClass = CrossWikiSearchProvider.class, dataProvider = "getExactMatchQueries", groups = {
      "CrossWikiSearch_001", "Search", "CrossWikiSearch_1"})
  public void crossWikiSearch_001_exactMatch(String query, String wikiName, String vertical) {
    CrossWikiSearchPageObject search = new CrossWikiSearchPageObject();
    search.searchForWiki(wikiURL, query);
    search.verifyFirstResultTitle(wikiName);
    search.verifyFirstResultVertical(vertical);
    search.verifyFirstResultDescription();
    search.verifyFirstResultPageCount();
    search.verifyFirstResultPageImages();
    search.verifyFirstResultPageVideos();
  }

  @Test(groups = {"CrossWikiSearch_002", "Search", "CrossWikiSearch_2"})
  public void crossWikiSearch_002_pagination() {
    CrossWikiSearchPageObject search = new CrossWikiSearchPageObject();
    search.searchForWiki(wikiURL, SearchContent.SEARCH_PHRASE);
    // verify results pos parameter for first page
    search.verifyResultsPosForPage(0, SearchContent.RESULTS_PER_PAGE);
    search.verifyResultsCount(SearchContent.RESULTS_PER_PAGE);
    search.verifyThumbnailsAmount(SearchContent.RESULTS_PER_PAGE);
    Assertion.assertTrue(search.areThumbnailsContainImages(), LACK_OF_IMAGE);
    search.verifyDescription(SearchContent.RESULTS_PER_PAGE);
    search.verifyStatistics(SearchContent.RESULTS_PER_PAGE);
    search.nextPage();
    // verify results pos parameter for second page
    search.verifyResultsPosForPage(1, SearchContent.RESULTS_PER_PAGE);
    search.verifyResultsCount(SearchContent.RESULTS_PER_PAGE);
    search.verifyThumbnailsAmount(SearchContent.RESULTS_PER_PAGE);
    Assertion.assertTrue(search.areThumbnailsContainImages(), LACK_OF_IMAGE);
    search.verifyDescription(SearchContent.RESULTS_PER_PAGE);
    search.verifyStatistics(SearchContent.RESULTS_PER_PAGE);
    search.prevPage();
    // verify results pos parameter for first page
    search.verifyResultsPosForPage(0, SearchContent.RESULTS_PER_PAGE);
    search.verifyResultsCount(SearchContent.RESULTS_PER_PAGE);
    search.verifyThumbnailsAmount(SearchContent.RESULTS_PER_PAGE);
    Assertion.assertTrue(search.areThumbnailsContainImages(), LACK_OF_IMAGE);
    search.verifyDescription(SearchContent.RESULTS_PER_PAGE);
    search.verifyStatistics(SearchContent.RESULTS_PER_PAGE);
  }

  @Test(groups = {"CrossWikiSearch_003", "Search", "CrossWikiSearch_1"})
  public void crossWikiSearch_003_resultClick() {
    CrossWikiSearchPageObject search = new CrossWikiSearchPageObject();
    search.searchForWiki(wikiURL, SearchContent.SEARCH_PHRASE);
    WikiArticleHomePage wikiArticleHomePage = search.openResult(0);
    wikiArticleHomePage.verifyThisIsWikiHomePage();
    driver.navigate().back();
    search.openResult(2);
    wikiArticleHomePage.verifyThisIsWikiHomePage();
  }

  @Test(groups = {"CrossWikiSearch_004", "Search", "CrossWikiSearch_2"})
  public void crossWikiSearch_004_noResults() {
    CrossWikiSearchPageObject search = new CrossWikiSearchPageObject();
    search.searchForWiki(wikiURL, SearchContent.SEARCH_PHRASE_NO_RESULTS);
    search.verifyNoPagination();
    search.verifyNoResultsCaption();
    search.verifyNoCommunitiesLink();
  }

  @Test(groups = {"CrossWikiSearch_005", "Search", "CrossWikiSearch_1"})
  public void crossWikiSearch_005_onePageResult() {
    CrossWikiSearchPageObject search = new CrossWikiSearchPageObject();
    search.searchForWiki(wikiURL, SearchContent.SEARCH_PHRASE_ONE_PAGE_RESULTS);
    search.verifyNoPagination();
    search.verifyNoCommunitiesLink();
  }

  @Test(dataProviderClass = CrossWikiSearchProvider.class, dataProvider = "getPushToTopQueries", groups = {
      "CrossWikiSearch_006", "Search", "CrossWikiSearch_2"})
  public void crossWikiSearch_006_pushToTop(String query, String wikiName) {
    CrossWikiSearchPageObject search = new CrossWikiSearchPageObject();
    search.searchForWiki(wikiURL, query);
    search.verifyFirstResultTitle(wikiName);
  }

  /**
   * Navigate to http://www.wikia.com/index.php?title=Special:Search type: GTA V verify that GTA V
   * wikia was found type: GTA 5 verify that GTA V wikia was found
   */
  @Test( // MAIN-4498
      groups = {"CrossWikiSearch_007", "Search", "CrossWikiSearch_2"})
  public void crossWikiSearch_007_romanNumbersMatch() {
    CrossWikiSearchPageObject search = new CrossWikiSearchPageObject();
    search.searchForWiki(wikiURL, SearchContent.SEARCH_PHRASE_ROMAN_NUMBER);
    search.verifyQuery(SearchContent.WIKI_NAME);
    search.searchForWiki(wikiURL, SearchContent.SEARCH_PHRASE_DECIMAL_NUMBER);
    search.verifyQuery(SearchContent.WIKI_NAME);
  }
}
