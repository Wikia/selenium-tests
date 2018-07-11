package com.wikia.webdriver.testcases.mobilewikitests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;

import org.testng.annotations.Test;

@Test(groups = "MobileWiki_Navigation")
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
public class NavigationMobileWikiTests extends NavigationTests {

  @Test(groups = "mobileWiki_navigation_openAndCloseNavigationAndItsSubMenu")
  public void mobileWiki_navigation_openAndCloseNavigationAndItsSubMenu() {
    super.mobileWiki_navigation_openAndCloseNavigationAndItsSubMenu(
        new ArticlePage().open(MercurySubpages.MAIN_PAGE)
    );
  }

  @Test(groups = "mobileWiki_navigation_openWikiWithManyItemsInLocalNav")
  @Execute(onWikia = "agas")
  public void mobileWiki_navigation_openWikiWithManyItemsInLocalNav() {
    super.mobileWiki_navigation_openAndCloseNavigationAndItsSubMenu(
        new ArticlePage().open(MercurySubpages.MAIN_PAGE)
    );
  }

  @Test(groups = "mobileWiki_navigation_navigationOnEnglishWiki")
  public void mobileWiki_navigation_navigationOnEnglishWiki() {
    super.mobileWiki_navigation_navigationOnEnglishWiki(
        new ArticlePage().open(MercurySubpages.MAIN_PAGE)
    );
  }

  @Execute(onWikia = MercuryWikis.DE_WIKI, language = "de")
  @Test(groups = "mobileWiki_navigation_navigationOnNonEnglishWiki")
  public void mobileWiki_navigation_navigationOnNonEnglishWiki() {
    super.mobileWiki_navigation_navigationOnNonEnglishWiki(
        new ArticlePage().open(MercurySubpages.MAIN_PAGE)
    );
  }

}
