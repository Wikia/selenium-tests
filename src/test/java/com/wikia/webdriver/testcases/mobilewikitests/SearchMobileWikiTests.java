package com.wikia.webdriver.testcases.mobilewikitests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.skin.Skin;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.elements.mercury.pages.SearchResultsPage;
import org.testng.annotations.Test;

@Test(groups = "SearchMobileWikiTests")
@InBrowser(browser = Browser.CHROME)
public class SearchMobileWikiTests extends SearchTests {

  private static final String SEARCH_PHRASE = "Infobox";
  private static final String SEARCH_PHRASE_NO_RESULTS = "AComplexQueryWithNoResults";
  private static final String MULTIPLE_RESULTS_SEARCH_PHRASE = "Test";
  private static final String SINGLE_RESULT_SEARCH_PHRASE = "SRPWithOnlyOneSearchResult";
  private static final String EMPTY_SEARCH_PHRASE = "";
  // Mobile Wiki tries to get 25 results, but a bug in the Search API returns 24 for the first batch
  // See SUS-1151 for details
  private static final int SEARCH_RESULTS_NUMBER_FIRST_BATCH = 24;
  private static final int SEARCH_RESULTS_NUMBER_NEXT_BATCH = 25;

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @RelatedIssue(issueID = "IRIS-5167")
  @Test(groups = {"mercury_search_navigateUsingSearchSuggestionsOnMobile",
                  "MobileWiki_Search_001"})
  public void mercury_search_navigateUsingSearchSuggestionsOnMobile() {
    ArticlePage article = new ArticlePage().open(MercurySubpages.MAIN_PAGE);
    String clickedSuggestion = article.getTopBar()
        .openSearch()
        .typeInSearch(SEARCH_PHRASE)
        .clickSearchSuggestion(0);

    Assertion.assertEquals(
        clickedSuggestion.toLowerCase(),
        article.getHeader().getPageTitle().toLowerCase()
    );
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_navigateToPageUsingSearchResults", "MobileWiki_Search_001"})
  public void mercury_search_navigateToPageUsingSearchResults() {
    String resultLink =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE)
            .clickSearchResult(0);

    Assertion.assertEquals(driver.getCurrentUrl(), resultLink);
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_cancelSearchPhrase", "MobileWiki_Search_001"})
  public void mercury_search_clearSearchPhrase() {
    super.mercury_search_clearSearchPhrase(
        new ArticlePage().open(MercurySubpages.MAIN_PAGE)
    );
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_verifySearchLayout", "MobileWiki_Search_001"})
  public void mercury_search_verifySearchLayout() {
    super.mercury_search_verifySearchLayout(
        new ArticlePage().open(MercurySubpages.MAIN_PAGE)
    );
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_userIsRedirectedToSearchResultsPage", "MobileWiki_Search_001"})
  public void mercury_search_userIsRedirectedToSearchResultsPage() {
    SearchResultsPage searchResults =
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopBar()
            .openSearch()
            .typeInSearch(SEARCH_PHRASE)
            .clickEnterAndNavigateToSearchResults(Skin.MOBILE_WIKI);

    Assertion.assertTrue(searchResults.isSearchResultsPageOpen());
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_searchResultsPageHasNoSearchIconInTopBar", "MobileWiki_Search_001"})
  public void mercury_search_searchResultsPageHasNoSearchIconInTopBar() {
    SearchResultsPage resultsPage =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE);

    Assertion.assertFalse(resultsPage.getTopBar().isSearchIconClickable());
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_searchInputDoesNotCoverNavigation", "MobileWiki_Search_001"})
  public void mercury_search_searchInputDoesNotCoverNavigation() {
    SearchResultsPage resultsPage =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE);

    resultsPage
        .getTopBar()
        .openNavigation();

    Assertion.assertFalse(resultsPage.getSearch().isSearchInputFieldEditable());
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_searchNoResultsPageDisplayed", "MobileWiki_Search_001"})
  public void mercury_search_searchNoResultsPageDisplayed() {
    SearchResultsPage searchResults =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE_NO_RESULTS);

    Assertion.assertTrue(searchResults.isNoResultsPagePresent());
    Assertion.assertFalse(searchResults.isLoadMoreButtonVisible());
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_noSuggestionsOnSearchResultsPage", "MobileWiki_Search_001"})
  public void mercury_search_noSuggestionsOnSearchResultsPage() {
    SearchResultsPage searchResults =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE_NO_RESULTS);

    Assertion.assertFalse(searchResults.getSearch().areSearchSuggestionsDisplayed());
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_focusOnTryAnotherSearchWhenNoResults", "MobileWiki_Search_002"})
  public void mercury_search_focusOnTryAnotherSearchWhenNoResults() {
    SearchResultsPage searchResults =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE_NO_RESULTS)
            .clickTryAnotherSearch();

    Assertion.assertTrue(searchResults.getSearch().isInputFieldFocused());
    Assertion.assertTrue(searchResults.getSearch().getSearchPhrase().isEmpty());
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_redirectToNewResultsPageFromNoResults",
                  "MobileWiki_Search_002"})
  public void mercury_search_redirectToNewResultsPageFromNoResults() {
    SearchResultsPage searchResults =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE_NO_RESULTS)
            .clickTryAnotherSearch()
            .getSearch()
            .typeInSearch(SEARCH_PHRASE)
            .clickEnterAndNavigateToSearchResults(Skin.MOBILE_WIKI);

    Assertion.assertTrue(searchResults.isSearchResultsPageOpen());
    Assertion.assertFalse(searchResults.isNoResultsPagePresent());
    Assertion.assertTrue(searchResults.areResultsPresent());
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_defaultResultsNumberOnSearchResultsPage"})
  public void mercury_search_defaultResultsNumberOnSearchResultsPage() {
    SearchResultsPage resultsPage =
        new SearchResultsPage()
            .openForQuery(MULTIPLE_RESULTS_SEARCH_PHRASE);

    Assertion.assertEquals(resultsPage.getResultCardsNumber(), SEARCH_RESULTS_NUMBER_FIRST_BATCH);
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_loadingMoreResultsOnSearchResultsPage"})
  public void mercury_search_loadingMoreResultsOnSearchResultsPage() {
    SearchResultsPage resultsPage =
        new SearchResultsPage()
            .openForQuery(MULTIPLE_RESULTS_SEARCH_PHRASE);

    int defaultCardNumber = resultsPage.getResultCardsNumber();

    Assertion.assertTrue(resultsPage.isLoadMoreButtonVisible());
    Assertion.assertEquals(defaultCardNumber, SEARCH_RESULTS_NUMBER_FIRST_BATCH);

    resultsPage.clickLoadMoreButton();

    int moreResultsLoaded = resultsPage.getResultCardsNumber() - defaultCardNumber;

    Assertion.assertEquals(moreResultsLoaded, SEARCH_RESULTS_NUMBER_NEXT_BATCH);
    Assertion.assertTrue(resultsPage.isLoadMoreButtonVisible());
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_loadMoreResultsOnSearchResultsPageNotVisible",
                  "MobileWiki_Search_002"})
  public void mercury_search_loadMoreResultsOnSearchResultsPageNotVisible() {
    SearchResultsPage resultsPage =
        new SearchResultsPage()
            .openForQuery(SINGLE_RESULT_SEARCH_PHRASE);

    Assertion.assertTrue(resultsPage.getResultCardsNumber() < SEARCH_RESULTS_NUMBER_FIRST_BATCH);
    Assertion.assertFalse(resultsPage.isLoadMoreButtonVisible());
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_loadMoreResultsOnSearchResultsPageNotVisible",
                  "MobileWiki_Search_002"})
  public void mercury_search_emptySearchPhrase() {
    SearchResultsPage resultsPage =
        new SearchResultsPage()
            .openForQuery(EMPTY_SEARCH_PHRASE);

    Assertion.assertEquals(resultsPage.getResultCardsNumber(), 0);
    Assertion.assertFalse(resultsPage.isLoadMoreButtonVisible());
  }
}
