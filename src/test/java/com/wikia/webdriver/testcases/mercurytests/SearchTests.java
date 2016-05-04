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

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class SearchTests extends NewTestTemplate {

  private TopBar topBar;
  private Search search;

  private void init() {
    this.topBar = new TopBar(driver);
    this.search = new Search(driver);

    new Navigate(driver).toPage(MercurySubpages.MAIN_PAGE);
  }

  @Test(groups = "mercury_search_navigateToPageUsingSearch")
  public void mercury_search_navigateToPageUsingSearch() {
    String searchingPhrase = "Infobox";
    init();

    topBar.openSearch().typeInSearch(searchingPhrase).selectSearchSuggestion(0);

    Assertion.assertTrue(driver.getCurrentUrl().contains(searchingPhrase));
  }

  @Test(groups = "mercury_search_cancelSearchPhrase")
  public void mercury_search_clearSearchPhrase() {
    String searchingPhrase = "Infobox";
    init();

    Search search = topBar.openSearch();
    search.typeInSearch(searchingPhrase);
    search.clickClearSearchButton();

    Assertion.assertEquals(search.getSearchPhrase(), "");
  }
}
