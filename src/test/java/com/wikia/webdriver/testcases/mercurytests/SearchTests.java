package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.Search;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.elements.mercury.pages.SearchResultsPage;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
@Test(groups = "MercurySearch")
public class SearchTests extends NewTestTemplate {

  private static final String SEARCH_PHRASE = "Infobox";
  private static final String SEARCH_PHRASE_NO_RESULTS = "AComplexQueryWithNoResults";

  @Test(groups = "mercury_search_navigateToPageUsingSearchSuggestions")
  public void mercury_search_navigateToPageUsingSearchSuggestions() {
    String suggestionLink =
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopBar()
            .openSearch()
            .typeInSearch(this.SEARCH_PHRASE)
            .clickSearchSuggestion(0);

    Assertion.assertTrue(driver.getCurrentUrl().equals(suggestionLink));
  }

  @Test(groups = "mercury_search_navigateToPageUsingSearchResults")
  public void mercury_search_navigateToPageUsingSearchResults() {
    String resultLink =
        new SearchResultsPage()
            .openForQuery(this.SEARCH_PHRASE)
            .clickSearchResult(0);

    Assertion.assertTrue(driver.getCurrentUrl().equals(resultLink));
  }

  @Test(groups = "mercury_search_cancelSearchPhrase")
  public void mercury_search_clearSearchPhrase() {
    String currentPhrase = new ArticlePage()
        .open(MercurySubpages.MAIN_PAGE)
        .getTopBar()
        .openSearch()
        .typeInSearch(this.SEARCH_PHRASE)
        .clickClearSearchButton()
        .getSearchPhrase();

    Assertion.assertTrue(currentPhrase.isEmpty());
  }

  @Test(groups = "mercury_search_verifySearchLayout")
  public void mercury_search_verifySearchLayout() {
    Search search =
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopBar()
            .openSearch();

    Assertion.assertTrue(search.isSearchInputFieldVisible());
    Assertion.assertTrue(search.isClearSearchButtonVisible());
    Assertion.assertTrue(search.isInputFieldSearchIconVisible());
  }

  @Test(groups = "mercury_search_userIsRedirectedToSearchResultsPage")
  public void mercury_search_userIsRedirectedToSearchResultsPage() {
    SearchResultsPage searchResults =
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopBar()
            .openSearch()
            .typeInSearch(this.SEARCH_PHRASE)
            .clickEnterAndNavigateToSearchResults();

    Assertion.assertTrue(searchResults.isSearchResultsPageOpen());
  }

  @Test(groups = "mercury_search_searchResultsPageHasNoSearchIconInTopBar")
  public void mercury_search_searchResultsPageHasNoSearchIconInTopBar() {
    SearchResultsPage resultsPage =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE);

    Assertion.assertFalse(resultsPage.getTopBar().isSearchIconClickable());
  }

  @Test(groups = "mercury_search_searchInputDoesNotCoverNavigation")
  public void mercury_search_searchInputDoesNotCoverNavigation() {
    SearchResultsPage resultsPage =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE);

    resultsPage
        .getTopBar()
        .openNavigation();

    Assertion.assertFalse(resultsPage.getSearch().isSearchInputFieldEditable());
  }

  @Test(groups = "mercury_search_searchNoResultsPageDisplayed")
  public void mercury_search_searchNoResultsPageDisplayed() {
    SearchResultsPage searchResults =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE_NO_RESULTS);

    Assertion.assertTrue(searchResults.isNoResultsPagePresent());
  }

  @Test(groups = "mercury_search_noSuggestionsOnSearchResultsPage")
  public void mercury_search_noSuggestionsOnSearchResultsPage() {
    SearchResultsPage searchResults =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE_NO_RESULTS);

    Assertion.assertFalse(searchResults.getSearch().areSearchSuggestionsDisplayed());
  }

  @Test(groups = "mercury_search_focusOnTryAnotherSearchWhenNoResults")
  public void mercury_search_focusOnTryAnotherSearchWhenNoResults() {
    SearchResultsPage searchResults =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE_NO_RESULTS)
            .clickTryAnotherSearch();

    Assertion.assertTrue(searchResults.getSearch().isInputFieldFocused());
    Assertion.assertTrue(searchResults.getSearch().getSearchPhrase().isEmpty());
  }

  @Test(groups = "mercury_search_redirectToNewResultsPageFromNoResults")
  public void mercury_search_redirectToNewResultsPageFromNoResults() {
    SearchResultsPage searchResults =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE_NO_RESULTS)
            .clickTryAnotherSearch()
            .getSearch()
            .typeInSearch(SEARCH_PHRASE)
            .clickEnterAndNavigateToSearchResults();

    Assertion.assertTrue(searchResults.isSearchResultsPageOpen());
    Assertion.assertFalse(searchResults.isNoResultsPagePresent());
    Assertion.assertTrue(searchResults.areResultsPresent());
  }
}
