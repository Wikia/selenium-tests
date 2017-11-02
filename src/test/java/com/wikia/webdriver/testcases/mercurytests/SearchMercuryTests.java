package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.skin.Skin;
import com.wikia.webdriver.common.skin.SkinHelper;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.elements.mercury.pages.SearchResultsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.GuidelinesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.testcases.mobilewikitests.SearchTests;

import org.testng.annotations.Test;

@InBrowser(browser = Browser.CHROME)
public class SearchMercuryTests extends SearchTests {

  private static final String SEARCH_PHRASE = "Infobox";

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_navigateUsingSearchSuggestionsOnMobile",
                  "Mercury_Search_001"})
  @RelatedIssue(issueID = "IRIS-5132", comment = "Infobox suggestions not showing both on "
                                                 + "desktop and "
                                                 + "mobile")
  public void mercury_search_navigateUsingSearchSuggestionsOnMobile() {
    String clickedSuggestion = new GuidelinesPage()
        .open()
        .getTopBar()
        .openSearch()
        .typeInSearch(SEARCH_PHRASE)
        .clickSearchSuggestion(0, Skin.MERCURY);

    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.MOBILE_WIKI));
    Assertion.assertEquals(
        clickedSuggestion.toLowerCase(),
        new ArticlePage().getHeader().getPageTitle().toLowerCase()
    );
  }

  @Execute(onWikia = "dauto")
  @Test(groups = {"mercury_search_navigateUsingSearchSuggestionsOnDesktop",
                  "Mercury_Search_001"})
  @InBrowser(browser = Browser.FIREFOX, browserSize = "1920x1080")
  @RelatedIssue(issueID = "IRIS-5132", comment = "Infobox suggestions not showing both on "
                                                  + "desktop and "
                                                 + "mobile")
  public void mercury_search_navigateUsingSearchSuggestionsOnDesktop() {
    String clickedSuggestion = new GuidelinesPage()
        .open()
        .getTopBar()
        .typeInDesktopSearchAndSelectSuggestion(SEARCH_PHRASE, 0);

    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.OASIS));
    Assertion.assertEquals(
        clickedSuggestion.toLowerCase(),
        new ArticlePageObject().getArticleName().toLowerCase()
    );
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_cancelSearchPhrase", "Mercury_Search_001"})
  public void mercury_search_clearSearchPhrase() {
    super.mercury_search_clearSearchPhrase(
        new GuidelinesPage().open()
    );
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_verifySearchLayout", "Mercury_Search_001"})
  public void mercury_search_verifySearchLayout() {
    super.mercury_search_verifySearchLayout(
        new GuidelinesPage().open()
    );
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_userIsRedirectedToSearchResultsPage", "Mercury_Search_001"})
  public void mercury_search_userIsRedirectedToSearchResultsPage() {
    SearchResultsPage searchResults =
        new GuidelinesPage()
            .open()
            .getTopBar()
            .openSearch()
            .typeInSearch(SEARCH_PHRASE)
            .clickEnterAndNavigateToSearchResults(Skin.MERCURY);

    Assertion.assertTrue(searchResults.isSearchResultsPageOpen());
  }
}
