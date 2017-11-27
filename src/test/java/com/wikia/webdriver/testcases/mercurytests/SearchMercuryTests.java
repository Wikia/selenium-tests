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

@Test(groups = "SearchMercuryTests")
@InBrowser(browser = Browser.CHROME)
public class SearchMercuryTests extends SearchTests {

  private static final String SEARCH_PHRASE = "Infobox";

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @RelatedIssue(issueID = "IRIS-5167")
  @Test(groups =
      {"mercury_search_navigateUsingSearchSuggestionsOnMobileFromDiscussionsGuidelinesPage",
                  "Discussions_Search_001"})
  public void mercury_search_navigateUsingSearchSuggestionsOnMobileFromDiscussionsGuidelinesPage() {

    String clickedSuggestion = new GuidelinesPage()
        .open()
        .getTopBar()
        .openSearch()
        .typeInSearch(SEARCH_PHRASE)
        .clickSearchSuggestion(0, Skin.MERCURY);

    ArticlePage page = new ArticlePage();
    page.getHeader().waitForLoaded();
    Assertion.assertEquals(
        clickedSuggestion.toLowerCase(), page.getHeader().getPageTitle().toLowerCase()
    );
  }

  @Execute(onWikia = "dauto")
  @Test(groups = {"mercury_search_navigateUsingSearchSuggestionsOnMobileFromDiscussionsGuidelinesPage",
                  "Discussions_Search_001"})
  @InBrowser(browser = Browser.FIREFOX, browserSize = "1920x1080")

  public void mercury_search_navigateUsingSearchSuggestionsOnDesktopFromDiscussionsGuidelinesPage() {

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
  @Test(groups = {"mercury_search_cancelSearchPhraseFromDiscussionsGuidelinesPage", "Discussions_Search_001"})
  public void mercury_search_clearSearchPhraseFromDiscussionsGuidelinesPage() {
    super.mercury_search_clearSearchPhrase(
        new GuidelinesPage().open()
    );
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_verifySearchLayoutFromDiscussionsGuidelinesPage", "Discussions_Search_001"})
  public void mercury_search_verifySearchLayoutFromDiscussionsGuidelinesPage() {
    super.mercury_search_verifySearchLayout(
        new GuidelinesPage().open()
    );
  }

  @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"mercury_search_userIsRedirectedToSearchResultsPageFromDiscussionsGuidelinesPage", "Discussions_Search_001"})
  public void mercury_search_userIsRedirectedToSearchResultsPageFromDiscussionsGuidelinesPage() {
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
