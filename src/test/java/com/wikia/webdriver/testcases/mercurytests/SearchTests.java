package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.skin.Skin;
import com.wikia.webdriver.common.skin.SkinHelper;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.Search;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.elements.mercury.pages.SearchResultsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.DiscussionsPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

@InBrowser(browser = Browser.CHROME)
public class SearchTests extends NewTestTemplate {

  private static final String SEARCH_PHRASE = "Infobox";
  private static final String SEARCH_PHRASE_NO_RESULTS = "AComplexQueryWithNoResults";
  private static final String MULTIPLE_RESULTS_SEARCH_PHRASE = "Test";
  private static final String SINGLE_RESULT_SEARCH_PHRASE = "SRPWithOnlyOneSearchResult";
  private static final String EMPTY_SEARCH_PHRASE = "";
  private static final int SEARCH_RESULTS_DEFAULT_NUMBER = 25;

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_navigateUsingSearchSuggestionsOnMobile",
                  "Mercury_Search_001"})
  public void mercury_search_navigateUsingSearchSuggestionsOnMobile() {
    ArticlePage article = new ArticlePage().open(MercurySubpages.MAIN_PAGE);
    String clickedSuggestion = article.getTopBar()
        .openSearch()
        .typeInSearch(SEARCH_PHRASE)
        .clickSearchSuggestion(0);

    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.MERCURY));
    Assertion.assertEquals(clickedSuggestion.toLowerCase(),
                           article.getHeader().getPageTitle().toLowerCase());
  }

  @Execute(onWikia = "dauto")
  @Test(groups = {"mercury_search_navigateUsingSearchSuggestionsOnDesktop",
                  "Mercury_Search_001"})
  @InBrowser(browser = Browser.FIREFOX, browserSize = "1920x1080")
  public void mercury_search_navigateUsingSearchSuggestionsOnDesktop() {
    String clickedSuggestion =
        new DiscussionsPage()
            .getTopBar()
            .typeInDesktopSearchAndSelectSuggestion(SEARCH_PHRASE, 0);

    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.OASIS));
    Assertion.assertEquals(clickedSuggestion.toLowerCase(),
                           new ArticlePageObject().getArticleName().toLowerCase());
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_navigateToPageUsingSearchResults", "Mercury_Search_001"})
  public void mercury_search_navigateToPageUsingSearchResults() {
    String resultLink =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE)
            .clickSearchResult(0);

    Assertion.assertEquals(driver.getCurrentUrl(), resultLink);
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_cancelSearchPhrase", "Mercury_Search_001"})
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

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_verifySearchLayout", "Mercury_Search_001"})
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

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_userIsRedirectedToSearchResultsPage", "Mercury_Search_001"})
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

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_searchResultsPageHasNoSearchIconInTopBar", "Mercury_Search_001"})
  public void mercury_search_searchResultsPageHasNoSearchIconInTopBar() {
    SearchResultsPage resultsPage =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE);

    Assertion.assertFalse(resultsPage.getTopBar().isSearchIconClickable());
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_searchInputDoesNotCoverNavigation", "Mercury_Search_001"})
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
  @Test(groups = {"mercury_search_searchNoResultsPageDisplayed", "Mercury_Search_002"})
  public void mercury_search_searchNoResultsPageDisplayed() {
    SearchResultsPage searchResults =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE_NO_RESULTS);

    Assertion.assertTrue(searchResults.isNoResultsPagePresent());
    Assertion.assertFalse(searchResults.isLoadMoreButtonVisible());
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_noSuggestionsOnSearchResultsPage", "Mercury_Search_002"})
  public void mercury_search_noSuggestionsOnSearchResultsPage() {
    SearchResultsPage searchResults =
        new SearchResultsPage()
            .openForQuery(SEARCH_PHRASE_NO_RESULTS);

    Assertion.assertFalse(searchResults.getSearch().areSearchSuggestionsDisplayed());
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_focusOnTryAnotherSearchWhenNoResults", "Mercury_Search_002"})
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
                  "Mercury_Search_002"})
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

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_defaultResultsNumberOnSearchResultsPage",
                  "Mercury_Search_002"})
  @RelatedIssue(issueID = "SUS-1151")
  public void mercury_search_defaultResultsNumberOnSearchResultsPage() {
    SearchResultsPage resultsPage =
        new SearchResultsPage()
            .openForQuery(MULTIPLE_RESULTS_SEARCH_PHRASE);

    Assertion.assertEquals(resultsPage.getResultCardsNumber(), SEARCH_RESULTS_DEFAULT_NUMBER);
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_loadingMoreResultsOnSearchResultsPage",
                  "Mercury_Search_002"})
  @RelatedIssue(issueID = "SUS-1151")
  public void mercury_search_loadingMoreResultsOnSearchResultsPage() {
    SearchResultsPage resultsPage =
        new SearchResultsPage()
            .openForQuery(MULTIPLE_RESULTS_SEARCH_PHRASE);

    int defaultCardNumber = resultsPage.getResultCardsNumber();

    Assertion.assertTrue(resultsPage.isLoadMoreButtonVisible());
    Assertion.assertEquals(defaultCardNumber, SEARCH_RESULTS_DEFAULT_NUMBER);

    resultsPage.clickLoadMoreButton();
    int moreResultsLoaded = resultsPage.getResultCardsNumber() - defaultCardNumber;

    Assertion.assertEquals(moreResultsLoaded, SEARCH_RESULTS_DEFAULT_NUMBER);
    Assertion.assertTrue(resultsPage.isLoadMoreButtonVisible());
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_loadMoreResultsOnSearchResultsPageNotVisible",
                  "Mercury_Search_002"})
  public void mercury_search_loadMoreResultsOnSearchResultsPageNotVisible() {
    SearchResultsPage resultsPage =
        new SearchResultsPage()
            .openForQuery(SINGLE_RESULT_SEARCH_PHRASE);

    Assertion.assertTrue(resultsPage.getResultCardsNumber() < SEARCH_RESULTS_DEFAULT_NUMBER);
    Assertion.assertFalse(resultsPage.isLoadMoreButtonVisible());
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_loadMoreResultsOnSearchResultsPageNotVisible",
                  "Mercury_Search_002"})
  public void mercury_search_emptySearchPhrase() {
    SearchResultsPage resultsPage =
        new SearchResultsPage()
            .openForQuery(EMPTY_SEARCH_PHRASE);

    Assertion.assertEquals(resultsPage.getResultCardsNumber(), 0);
    Assertion.assertFalse(resultsPage.isLoadMoreButtonVisible());
  }
}
