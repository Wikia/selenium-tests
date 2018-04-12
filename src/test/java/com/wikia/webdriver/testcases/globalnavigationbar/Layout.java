package com.wikia.webdriver.testcases.globalnavigationbar;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigation;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWikiActivityPageObject;
import org.testng.annotations.Test;

@Test(groups = {"globalnavigationbar", "globalnavigationbarLayout"})
public class Layout extends NewTestTemplate {

  @Test(groups = {"globalNavigationBarIsFixedOnScrollForAnon"})
  public void globalNavigationBarIsFixedOnScrollForAnon() {
    SpecialWikiActivityPageObject wikiActivity = new SpecialWikiActivityPageObject().open();
    wikiActivity.verifyGlobalNavigation();
    wikiActivity.scrollToFooter();
    wikiActivity.verifyGlobalNavigation();
  }

  @Test(groups = {"globalNavigationBarLayoutForEnglishAnon"})
  public void testLayoutForEnglishAnon() {
    GlobalNavigation globalNavigation = new HomePage().getGlobalNavigation();

    Assertion.assertTrue(globalNavigation.isFandomLogoVisible());
    Assertion.assertTrue(globalNavigation.isGamesHubVisible());
    Assertion.assertTrue(globalNavigation.isMoviesHubVisible());
    Assertion.assertTrue(globalNavigation.isTVHubVisible());
    Assertion.assertTrue(globalNavigation.isWikisMenuVisible());
    Assertion.assertTrue(globalNavigation.isSearchInputVisible());
    Assertion.assertTrue(globalNavigation.isAccountMenuVisible());
    Assertion.assertTrue(globalNavigation.isStartWikiButtonVisible());

    Assertion.assertFalse(globalNavigation.isCommunityCentralLinkVisible());
    Assertion.assertFalse(globalNavigation.isUserAvatarVisible());
    Assertion.assertFalse(globalNavigation.isNotificationsIconVisible());
  }

  @Test(groups = {"globalNavigationBarLayoutForEnglishLoggedIn"})
  @Execute(asUser = User.USER)
  public void testLayoutForEnglishLoggedIn() {
    GlobalNavigation globalNavigation = new HomePage().getGlobalNavigation();

    Assertion.assertTrue(globalNavigation.isFandomLogoVisible());
    Assertion.assertTrue(globalNavigation.isGamesHubVisible());
    Assertion.assertTrue(globalNavigation.isMoviesHubVisible());
    Assertion.assertTrue(globalNavigation.isTVHubVisible());
    Assertion.assertTrue(globalNavigation.isWikisMenuVisible());
    Assertion.assertTrue(globalNavigation.isSearchInputVisible());
    Assertion.assertTrue(globalNavigation.isUserAvatarVisible());
    Assertion.assertTrue(globalNavigation.isNotificationsIconVisible());
    Assertion.assertTrue(globalNavigation.isStartWikiButtonVisible());

    Assertion.assertFalse(globalNavigation.isCommunityCentralLinkVisible());
    Assertion.assertFalse(globalNavigation.isAccountMenuVisible());
  }


  @Test(groups = {"globalNavigationBarLayoutForDeAnon1284x900"})
  @Execute(onWikia = "dauto", language = "szl")
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void testLayoutForDeAnon1284x900() {
    GlobalNavigation globalNavigation = new HomePage().getGlobalNavigation();

    Assertion.assertTrue(globalNavigation.isFandomLogoVisible());
    Assertion.assertTrue(globalNavigation.isCommunityCentralLinkVisible());
    Assertion.assertTrue(globalNavigation.isSearchInputVisible());
    Assertion.assertTrue(globalNavigation.isAccountMenuVisible());
    Assertion.assertTrue(globalNavigation.isStartWikiButtonVisible());
    Assertion.assertTrue(globalNavigation.isPartnerSlotLinkVisible());

    Assertion.assertFalse(globalNavigation.isGamesHubVisible());
    Assertion.assertFalse(globalNavigation.isMoviesHubVisible());
    Assertion.assertFalse(globalNavigation.isTVHubVisible());
    Assertion.assertFalse(globalNavigation.isWikisMenuVisible());
    Assertion.assertFalse(globalNavigation.isUserAvatarVisible());
    Assertion.assertFalse(globalNavigation.isNotificationsIconVisible());
  }

  @Test(groups = {"globalNavigationBarLayoutForDeLoggedIn1284x900"})
  @Execute(onWikia = "gta", language = "de", asUser = User.USER_GERMAN)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void testLayoutForDeLoggedIn1284x900() {
    GlobalNavigation globalNavigation = new HomePage().getGlobalNavigation();

    Assertion.assertTrue(globalNavigation.isFandomLogoVisible());
    Assertion.assertTrue(globalNavigation.isCommunityCentralLinkVisible());
    Assertion.assertTrue(globalNavigation.isSearchInputVisible());
    Assertion.assertTrue(globalNavigation.isUserAvatarVisible());
    Assertion.assertTrue(globalNavigation.isNotificationsIconVisible());
    Assertion.assertTrue(globalNavigation.isStartWikiButtonVisible());
    Assertion.assertTrue(globalNavigation.isPartnerSlotLinkVisible());

    Assertion.assertFalse(globalNavigation.isGamesHubVisible());
    Assertion.assertFalse(globalNavigation.isMoviesHubVisible());
    Assertion.assertFalse(globalNavigation.isTVHubVisible());
    Assertion.assertFalse(globalNavigation.isWikisMenuVisible());
    Assertion.assertFalse(globalNavigation.isAccountMenuVisible());
  }

  @Test(groups = {"globalNavigationBarLayoutForDeAnon"})
  @Execute(onWikia = "gta", language = "de")
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_SMALL)
  public void testLayoutForDeAnon() {
    GlobalNavigation globalNavigation = new HomePage().open().getGlobalNavigation();

    Assertion.assertTrue(globalNavigation.isFandomLogoVisible());
    Assertion.assertTrue(globalNavigation.isCommunityCentralLinkVisible());
    Assertion.assertTrue(globalNavigation.isSearchInputVisible());
    Assertion.assertTrue(globalNavigation.isAccountMenuVisible());
    Assertion.assertTrue(globalNavigation.isStartWikiButtonVisible());

    Assertion.assertFalse(globalNavigation.isGamesHubVisible());
    Assertion.assertFalse(globalNavigation.isMoviesHubVisible());
    Assertion.assertFalse(globalNavigation.isTVHubVisible());
    Assertion.assertFalse(globalNavigation.isWikisMenuVisible());
    Assertion.assertFalse(globalNavigation.isUserAvatarVisible());
    Assertion.assertFalse(globalNavigation.isNotificationsIconVisible());
    Assertion.assertFalse(globalNavigation.isPartnerSlotLinkVisible());
  }

  @Test(groups = {"globalNavigationBarLayoutForDeLoggedIn"})
  @Execute(onWikia = "gta", language = "de", asUser = User.USER_GERMAN)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_SMALL)
  public void testLayoutForDeLoggedIn() {
    GlobalNavigation globalNavigation = new HomePage().getGlobalNavigation();

    Assertion.assertTrue(globalNavigation.isFandomLogoVisible());
    Assertion.assertTrue(globalNavigation.isCommunityCentralLinkVisible());
    Assertion.assertTrue(globalNavigation.isSearchInputVisible());
    Assertion.assertTrue(globalNavigation.isUserAvatarVisible());
    Assertion.assertTrue(globalNavigation.isNotificationsIconVisible());
    Assertion.assertTrue(globalNavigation.isStartWikiButtonVisible());

    Assertion.assertFalse(globalNavigation.isGamesHubVisible());
    Assertion.assertFalse(globalNavigation.isMoviesHubVisible());
    Assertion.assertFalse(globalNavigation.isTVHubVisible());
    Assertion.assertFalse(globalNavigation.isWikisMenuVisible());
    Assertion.assertFalse(globalNavigation.isAccountMenuVisible());
    Assertion.assertFalse(globalNavigation.isPartnerSlotLinkVisible());
  }
}
