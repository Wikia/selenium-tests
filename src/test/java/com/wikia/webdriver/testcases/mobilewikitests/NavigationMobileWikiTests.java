package com.wikia.webdriver.testcases.mobilewikitests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.elements.mercury.components.GlobalNavigationMobile;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import org.testng.annotations.Test;

@Test(groups = "MobileWiki_Navigation")
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
public class NavigationMobileWikiTests {

  //test disabled until we find solution how to click 'Wikis' link in navigation
  @Test(groups = "mobileWiki_navigation_openAndCloseNavigationAndItsSubMenu", enabled = false)
  public void mobileWiki_navigation_openAndCloseNavigationAndItsSubMenu() {
    GlobalNavigationMobile nav = new ArticlePage().open(MercurySubpages.MAIN_PAGE).getGlobalNavigationMobile().openNavigation();
    Assertion.assertTrue(nav.isFirstLevelMenuVisible());

    nav.clickCloseButton();
    Assertion.assertFalse(nav.isFirstLevelMenuVisible());
  }

  @Test(groups = "mobileWiki_navigation_navigationOnEnglishWiki")
  public void mobileWiki_navigation_navigationOnEnglishWiki() {
    GlobalNavigationMobile nav = new ArticlePage().open(MercurySubpages.MAIN_PAGE).getGlobalNavigationMobile().openNavigation();
    Assertion.assertTrue(nav.isFirstLevelMenuVisible());

    nav.clickCloseButton();
    Assertion.assertFalse(nav.isFirstLevelMenuVisible());
  }

  @Execute(onWikia = MercuryWikis.DE_WIKI, language = "de")
  @Test(groups = "mobileWiki_navigation_navigationOnNonEnglishWiki")
  public void mobileWiki_navigation_navigationOnNonEnglishWiki() {
    GlobalNavigationMobile nav = new ArticlePage().open(MercurySubpages.MAIN_PAGE).getGlobalNavigationMobile().openNavigation();
    Assertion.assertTrue(nav.isFirstLevelMenuVisible());

    nav.clickCloseButton();
    Assertion.assertFalse(nav.isFirstLevelMenuVisible());
  }

}
