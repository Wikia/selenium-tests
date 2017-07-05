package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.skin.Skin;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import org.testng.annotations.Test;

@Test(groups = "Mercury_Navigation")
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
public class NavigationMobileWikiTests extends NavigationTests {

  @Test(groups = "mercury_navigation_openAndCloseNavigationAndItsSubMenu")
  public void mercury_navigation_openAndCloseNavigationAndItsSubMenu() {
    super.mercury_navigation_openAndCloseNavigationAndItsSubMenu(
        new ArticlePage().open(MercurySubpages.MAIN_PAGE)
    );
  }

  @Test(groups = "mercury_navigation_resetNavigationState")
  public void mercury_navigation_resetNavigationState() {
    super.mercury_navigation_resetNavigationState(
        new ArticlePage().open(MercurySubpages.MAIN_PAGE)
    );
  }

  @Test(groups = "mercury_navigation_backButton")
  public void mercury_navigation_backButton() {
    super.mercury_navigation_backButton(
        new ArticlePage().open(MercurySubpages.MAIN_PAGE)
    );
  }

  @Test(groups = "mercury_navigation_navigationOnEnglishWiki")
  public void mercury_navigation_navigationOnEnglishWiki() {
    super.mercury_navigation_navigationOnEnglishWiki(
        new ArticlePage().open(MercurySubpages.MAIN_PAGE)
    );
  }

  @Execute(onWikia = MercuryWikis.DE_WIKI)
  @Test(groups = "mercury_navigation_navigationOnNonEnglishWiki")
  public void mercury_navigation_navigationOnNonEnglishWiki() {
    super.mercury_navigation_navigationOnNonEnglishWiki(
        new ArticlePage().open(MercurySubpages.MAIN_PAGE)
    );
  }

  @Test(groups = "mercury_navigation_navigationElementsUserLoggedIn")
  @Execute(asUser = User.USER_3)
  public void mercury_navigation_navigationElementsUserLoggedIn() {
    super.mercury_navigation_navigationElementsUserLoggedIn(
        new ArticlePage().open(MercurySubpages.MAIN_PAGE)
    );
  }

  @Test(groups = "mercury_navigation_navigationElementsAnonymousUser")
  @Execute(asUser = User.ANONYMOUS)
  public void mercury_navigation_navigationElementsAnonymousUser() {
    super.mercury_navigation_navigationElementsAnonymousUser(
        new ArticlePage().open(MercurySubpages.MAIN_PAGE)
    );
  }

  @Test(groups = "mercury_navigation_exploreWikiNavigatesToWikiMainPage")
  public void mercury_navigation_exploreWikiNavigatesToWikiMainPage() {
    new ArticlePage()
        .open(MercurySubpages.INFOBOX_1)
        .getTopBar()
        .openNavigation()
        .clickExploreWikiHeader(Skin.MOBILE_WIKI);

    Assertion.assertTrue(driver.getCurrentUrl().contains(MercurySubpages.MAIN_PAGE));
  }
}
