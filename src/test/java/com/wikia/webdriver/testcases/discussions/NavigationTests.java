package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.elements.mercury.components.GlobalNavigationMobile;
import com.wikia.webdriver.elements.mercury.pages.discussions.GuidelinesPage;
import org.testng.annotations.Test;

@Test(groups = "Discussions_Navigation")
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
@Execute(onWikia = MercuryWikis.DISCUSSIONS_MOBILE)
public class NavigationTests {

  @Test(groups = "discussionsNavigationOpenAndCloseNavigationAndItsSubMenu")
  public void mercuryNavigationOpenAndCloseNavigationAndItsSubMenu() {
    GlobalNavigationMobile nav = new GuidelinesPage().open().getGlobalNavigationMobile().openNavigation();
    Assertion.assertTrue(nav.isFirstLevelMenuVisible());

    nav.clickCloseButton();
    Assertion.assertFalse(nav.isFirstLevelMenuVisible());
  }

  @Execute(onWikia = MercuryWikis.DE_WIKI, language = "de")
  @Test(groups = "discussionsNavigationOnNonEnglishWiki")
  public void mercuryNavigationOnNonEnglishWiki() {
    GlobalNavigationMobile nav = new GuidelinesPage().open().getGlobalNavigationMobile().openNavigation();
    Assertion.assertTrue(nav.isFirstLevelMenuVisible());

    nav.clickCloseButton();
    Assertion.assertFalse(nav.isFirstLevelMenuVisible());
  }
}
