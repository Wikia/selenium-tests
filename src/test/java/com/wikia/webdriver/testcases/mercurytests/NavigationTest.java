package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.Navigation;
import com.wikia.webdriver.elements.mercury.TopBar;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class NavigationTest extends NewTestTemplate {

  private TopBar topBar;
  private Navigation navigation;

  private final static String SEARCH_PASS = "Gallery";
  private final static String SEARCH_FAIL = "tee";

  private void init() {
    this.topBar = new TopBar(driver);
    this.navigation = new Navigation(driver);

    new Navigate(driver).toPage(MercurySubpages.MAIN_PAGE);
  }

  @Test(groups = "mercury-navigation-001")
  public void navigation_001_openNavigateClose() {
    init();

    topBar.openNavigation();
    navigation.openSubMenu(1);
    navigation.closeSubMenu();
    topBar.closeNavigation();
  }

  @Test(groups = "mercury-navigation-002")
  public void navigation_002_searchValidSuggestionAndOpenIt() {
    init();

    topBar.openNavigation();
    navigation.openSubMenu(1);
    navigation.typeInSearch(SEARCH_PASS);
    navigation.selectSearchSuggestion(1);
  }

  @Test(groups = "mercury-navigation-003")
  public void navigation_003_searchInvalidSuggestionAndCloseSearchView() {
    init();

    topBar.openNavigation();
    navigation.openSubMenu(1);
    navigation.typeInSearch(SEARCH_FAIL);
    navigation.seeNoSearchResults();
    navigation.cancelSearch();
  }

  @Test(groups = "mercury-navigation-004")
  public void navigation_004_navigateToPageUsingLocalNav() {
    init();

    topBar.openNavigation();
    navigation.openSubMenu(1);
    navigation.openSubMenu(0);
    navigation.openPageLink(0);
  }
}
