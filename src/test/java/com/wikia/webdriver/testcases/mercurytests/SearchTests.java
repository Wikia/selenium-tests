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

import lombok.Getter;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
@Test(groups = "MercurySearch")
public class SearchTests extends NewTestTemplate {

  private TopBar topBar;

  @Getter(lazy = true)
  private final Search search = new Search();

  private static final String SEARCH_PHRASE = "Infobox";
  private static final String SEARCH_PHRASE_NO_RESULTS = "DubiousQueryWithNoResults";

  private void init() {
    this.topBar = new TopBar(driver);
    new Navigate().toPage(MercurySubpages.MAIN_PAGE);
  }

  @Test(groups = "mercury_search_navigateToPageUsingSearchSuggestions")
  public void mercury_search_navigateToPageUsingSearchSuggestions() {
    init();

    try {
      this.topBar.openSearch().typeInSearch(SEARCH_PHRASE).selectSearchSuggestion(0);
    } catch (Exception e) {
      System.out.println(e);
    }

    Assertion.assertTrue(driver.getCurrentUrl().contains(SEARCH_PHRASE));
  }

  @Test(groups = "mercury_search_navigateToPageUsingSearchResults")
  public void mercury_search_navigateToPageUsingSearchResults() {
    init();

    SearchResultsPage searchResults = new SearchResultsPage().openForQuery(SEARCH_PHRASE);
    searchResults.selectSearchResult(0);

    Assertion.assertTrue(driver.getCurrentUrl().contains(SEARCH_PHRASE));
  }

  @Test(groups = "mercury_search_cancelSearchPhrase")
  public void mercury_search_clearSearchPhrase() {
    init();

    this.topBar.openSearch().typeInSearch(SEARCH_PHRASE).clickClearSearchButton();

    Assertion.assertEquals(this.getSearch().getSearchPhrase(), "");
  }

  @Test(groups = "mercury_search_verifySearchLayout")
  public void mercury_search_verifySearchLayout() {
    init();

    Search searchInstance = this.topBar.openSearch();

    Assertion.assertTrue(searchInstance.isSearchInputFieldVisible());
    Assertion.assertTrue(searchInstance.isClearSearchButtonVisible());
    Assertion.assertTrue(searchInstance.isInputFieldSearchIconVisible());
  }

  @Test(groups = "mercury_search_userIsRedirectedToSearchResultsPage")
  public void mercury_search_userIsRedirectedToSearchResultsPage() {
    init();

    SearchResultsPage searchResults =
        this.topBar.openSearch().typeInSearch(SEARCH_PHRASE)
            .clickEnterAndNavigateToSearchResults();
    Assertion.assertTrue(searchResults.isSearchResultsPageOpen());
  }

  @Test(groups = "mercury_search_searchResultsPageHasNoSearchIconInTopBar")
  public void mercury_search_searchResultsPageHasNoSearchIconInTopBar() {
    init();

    new SearchResultsPage().openForQuery(SEARCH_PHRASE);
    Assertion.assertFalse(this.topBar.isSearchIconClickable());
  }

  @Test(groups = "mercury_search_searchInputDoesNotCoverNavigation",
      expectedExceptions = WebDriverException.class)
  public void mercury_search_searchInputDoesNotCoverNavigation() {
    init();

    SearchResultsPage searchResults = new SearchResultsPage().openForQuery(SEARCH_PHRASE);
    this.topBar.openNavigation();
    searchResults.typeInSearch(SEARCH_PHRASE);
  }

  @Test(groups = "mercury_search_searchNoResultsPageDisplayed")
  public void mercury_search_searchNoResultsPageDisplayed() {
    init();

    SearchResultsPage
        searchResults =
        new SearchResultsPage().openForQuery(SEARCH_PHRASE_NO_RESULTS);
    Assertion.assertTrue(searchResults.isNoResultsPagePresent());
  }
}
