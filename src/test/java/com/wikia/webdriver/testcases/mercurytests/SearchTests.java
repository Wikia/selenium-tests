package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.skin.Skin;
import com.wikia.webdriver.common.skin.SkinHelper;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.Search;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.elements.mercury.pages.SearchResultsPage;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME)
@Test(groups = "MercurySearch")
public class SearchTests extends NewTestTemplate {

  private static final String SEARCH_PHRASE = "Infobox";
  private static final String SEARCH_PHRASE_NO_RESULTS = "AComplexQueryWithNoResults";

  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = "mercury_search_navigateToPageUsingSearchSuggestions")
  public void mercury_search_navigateToPageUsingSearchSuggestions() {
    String suggestionLink =
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopBar()
            .openSearch()
            .typeInSearch(SEARCH_PHRASE)
            .selectSearchSuggestion(0);

    Assertion.assertTrue(driver.getCurrentUrl().equals(suggestionLink));
  }

  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = "mercury_search_navigateUsingSearchSuggestionsOnMobile")
  public void mercury_search_navigateUsingSearchSuggestionsOnMobile() {
    String suggestionLink =
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopBar()
            .openSearch()
            .typeInSearch(SEARCH_PHRASE)
            .selectSearchSuggestion(0);

    Assertion.assertTrue(driver.getCurrentUrl().equals(suggestionLink));
    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.MERCURY));
  }

  @Execute(onWikia = MercuryWikis.MEDIAWIKI_119)
  @Test(groups = "mercury_search_navigateUsingSearchSuggestionsOnDesktop")
  @InBrowser(browser = Browser.FIREFOX, browserSize = "1920x1080")
  public void mercury_search_navigateUsingSearchSuggestionsOnDesktop() {
    String suggestionLink =
        new ArticlePage()
            .open("/d/")
            .getTopBar()
            .openSearch()
            .typeInSearch(SEARCH_PHRASE)
            .selectSearchSuggestion(0);

    Assertion.assertTrue(driver.getCurrentUrl().equals(suggestionLink));
    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.OASIS));
  }

  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = "mercury_search_navigateToPageUsingSearchResults")
  public void mercury_search_navigateToPageUsingSearchResults() {
    String resultLink =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE)
            .selectSearchResult(0);

    Assertion.assertTrue(driver.getCurrentUrl().equals(resultLink));
  }

  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = "mercury_search_cancelSearchPhrase")
  public void mercury_search_clearSearchPhrase() {
    String currentPhrase = new ArticlePage()
        .open(MercurySubpages.MAIN_PAGE)
        .getTopBar()
        .openSearch()
        .typeInSearch(SEARCH_PHRASE)
        .clickClearSearchButton()
        .getSearchPhrase();

    Assertion.assertTrue(currentPhrase.isEmpty());
  }

  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
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

  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = "mercury_search_userIsRedirectedToSearchResultsPage")
  public void mercury_search_userIsRedirectedToSearchResultsPage() {
    SearchResultsPage searchResults =
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopBar()
            .openSearch()
            .typeInSearch(SEARCH_PHRASE)
            .clickEnterAndNavigateToSearchResults();

    Assertion.assertTrue(searchResults.isSearchResultsPageOpen());
  }

  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = "mercury_search_searchResultsPageHasNoSearchIconInTopBar")
  public void mercury_search_searchResultsPageHasNoSearchIconInTopBar() {
    SearchResultsPage resultsPage =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE);

    Assertion.assertFalse(resultsPage.getTopBar().isSearchIconClickable());
  }

  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
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

  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = "mercury_search_searchNoResultsPageDisplayed")
  public void mercury_search_searchNoResultsPageDisplayed() {
    SearchResultsPage searchResults =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE_NO_RESULTS);

    Assertion.assertTrue(searchResults.isNoResultsPagePresent());
  }

  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = "mercury_search_noSuggestionsOnSearchResultsPage")
  public void mercury_search_noSuggestionsOnSearchResultsPage() {
    SearchResultsPage searchResults =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE_NO_RESULTS);

    Assertion.assertFalse(searchResults.getSearch().areSearchSuggestionsDisplayed());
  }

  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = "mercury_search_focusOnTryAnotherSearchWhenNoResults")
  public void mercury_search_focusOnTryAnotherSearchWhenNoResults() {
    SearchResultsPage searchResults =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE_NO_RESULTS)
            .clickTryAnotherSearch();

    Assertion.assertTrue(searchResults.getSearch().isInputFieldFocused());
    Assertion.assertTrue(searchResults.getSearch().getSearchPhrase().isEmpty());
  }

  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
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
