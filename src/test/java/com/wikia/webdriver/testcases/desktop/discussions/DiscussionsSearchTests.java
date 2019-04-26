package com.wikia.webdriver.testcases.desktop.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.skin.Skin;
import com.wikia.webdriver.common.skin.SkinHelper;
import com.wikia.webdriver.elements.communities.mobile.pages.ArticlePage;
import com.wikia.webdriver.elements.communities.mobile.pages.SearchResultsPage;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.GuidelinesPage;
import com.wikia.webdriver.elements.communities.desktop.components.navigation.global.GlobalNavigation;
import com.wikia.webdriver.testcases.mobile.search.SearchTests;

import org.testng.annotations.Test;

@Test(groups = "discussions-search")
@InBrowser(browser = Browser.CHROME)
@Execute(onWikia = "qadiscussions", language = "de")
public class DiscussionsSearchTests extends SearchTests {

  private static final String SEARCH_PHRASE = "Infobox";

  @Test
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  public void navigateUsingSearchSuggestionsOnMobileFromDiscussionsGuidelinesPage() {

    String clickedSuggestion = new GuidelinesPage().open()
        .getGlobalNavigationMobile()
        .openSearch()
        .typeInSearch(SEARCH_PHRASE)
        .clickSearchSuggestion(0);

    ArticlePage page = new ArticlePage();
    page.getHeader().waitForLoaded();
    Assertion.assertEquals(clickedSuggestion.toLowerCase(),
                           page.getHeader().getPageTitle().toLowerCase()
    );
  }

  @Test
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void navigateUsingSearchSuggestionsOnDesktopFromDiscussionsGuidelinesPage() {
    Integer resultNo = 0;
    GlobalNavigation nav = new GuidelinesPage().open().getGlobalNavigation();

    nav.typeInSearch(SEARCH_PHRASE);
    String clickedSuggestion = nav.getNthSearchResultText(resultNo);
    nav.clickNthSearchResult(resultNo);

    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.OASIS));
    Assertion.assertEquals(clickedSuggestion.toLowerCase(),
                           new ArticlePage().getArticleName().toLowerCase()
    );
  }

  @Test
  public void clearSearchPhraseFromDiscussionsGuidelinesPageOnDesktop() {
    GlobalNavigation nav = new GuidelinesPage().open().getGlobalNavigation();

    nav.typeInSearch(SEARCH_PHRASE);
    nav.clearSearchPhrase();

    Assertion.assertTrue(nav.getCurrentSearchPhrase().isEmpty());
  }

  @Test
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  public void verifySearchLayoutFromDiscussionsGuidelinesPage() {
    super.verifySearchLayout(new GuidelinesPage().open());
  }

  @Test
  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5)
  @RelatedIssue(issueID = "IW-1949")
  public void userIsRedirectedToSearchResultsPageFromDiscussionsGuidelinesPage() {
    SearchResultsPage searchResults = new GuidelinesPage().open()
        .getGlobalNavigationMobile()
        .openSearch()
        .typeInSearch(SEARCH_PHRASE)
        .clickEnterAndNavigateToSearchResults(Skin.DISCUSSIONS);

    Assertion.assertTrue(searchResults.isSearchResultsPageOpen());
  }
}
