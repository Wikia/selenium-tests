package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Search;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.elements.mercury.pages.SearchResultsPage;

import org.openqa.selenium.WebDriverException;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
@Test(groups = "MercurySearch")
public class SearchTests extends NewTestTemplate {

  private TopBar topBar;
  private Search search;

  private void init() {
    this.topBar = new TopBar(driver);
    this.search = new Search();

    new Navigate().toPage(MercurySubpages.MAIN_PAGE);
  }

  @Test(groups = "mercury_search_navigateToPageUsingSearchSuggestions")
  public void mercury_search_navigateToPageUsingSearchSuggestions() {
    String searchPhrase = "Infobox";
    init();

    topBar
        .openSearch()
        .typeInSearch(searchPhrase)
        .selectSearchSuggestion(0);

    Assertion.assertTrue(driver.getCurrentUrl().contains(searchPhrase));
  }

  @Test(groups = "mercury_search_navigateToPageUsingSearchResults")
  public void mercury_search_navigateToPageUsingSearchResults() {
    String searchPhrase = "Infobox";
    init();

    SearchResultsPage searchResults = new SearchResultsPage().openForQuery(searchPhrase);
    searchResults.selectSearchResult(0);

    Assertion.assertTrue(driver.getCurrentUrl().contains(searchPhrase));
  }

  @Test(groups = "mercury_search_cancelSearchPhrase")
  public void mercury_search_clearSearchPhrase() {
    String searchPhrase = "Infobox";
    init();

    topBar
        .openSearch()
        .typeInSearch(searchPhrase)
        .clickClearSearchButton();

    Assertion.assertEquals(search.getSearchPhrase(), "");
  }

  @Test(groups = "mercury_search_verifySearchLayout")
  public void mercury_search_verifySearchLayout() {
    init();

    Search search = topBar.openSearch();
    Assertion.assertTrue(search.isSearchInputFieldVisible());
    Assertion.assertTrue(search.isClearSearchButtonVisible());
    Assertion.assertTrue(search.isInputFieldSearchIconVisible());
  }

  @Test(groups = "mercury_search_userIsRedirectedToSearchResultsPage")
  public void mercury_search_userIsRedirectedToSearchResultsPage() {
    String searchPhrase = "Info";
    init();

    SearchResultsPage searchResults =
        topBar.openSearch().typeInSearch(searchPhrase).clickEnterAndNavigateToSearchResults();
    Assertion.assertTrue(searchResults.isSearchResultsPageOpen());
  }

  @Test(groups = "mercury_search_searchResultsPageHasNoSearchIconInTopBar")
  public void mercury_search_searchResultsPageHasNoSearchIconInTopBar() {
    String searchPhrase = "Info";
    init();

    new SearchResultsPage().openForQuery(searchPhrase);
    Assertion.assertFalse(this.topBar.isSearchIconClickable());
  }

  @Test(groups = "mercury_search_searchInputDoesNotCoverNavigation",
      expectedExceptions = WebDriverException.class)
  public void mercury_search_searchInputDoesNotCoverNavigation() {
    String searchPhrase = "Info";
    init();

    SearchResultsPage searchResults = new SearchResultsPage().openForQuery(searchPhrase);
    this.topBar.openNavigation();
    searchResults.typeInSearch(searchPhrase);
  }

  @Test(groups = "mercury_search_searchNoResultsPageDisplayed")
  public void mercury_search_searchNoResultsPageDisplayed() {
    String searchPhrase = "DubiousQueryWithNoResults";
    init();

    SearchResultsPage searchResults = new SearchResultsPage().openForQuery(searchPhrase);
    Assertion.assertTrue(searchResults.isNoResultsPagePresent());
  }
}
