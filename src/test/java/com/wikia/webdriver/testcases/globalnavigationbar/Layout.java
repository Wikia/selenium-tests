package com.wikia.webdriver.testcases.globalnavigationbar;

import junit.framework.Assert;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigation;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWikiActivityPageObject;

@Test(groups = {"globalnavigationbar", "globalnavigationbarLayout"})
public class Layout extends NewTestTemplate {

  @Test(groups = {"globalNavigationBarIsFixedOnScrollForAnon"})
  public void globalNavigationBarIsFixedOnScrollForAnon() {
    SpecialWikiActivityPageObject wikiActivity = new SpecialWikiActivityPageObject(driver).open();
    wikiActivity.verifyGlobalNavigation();
    wikiActivity.scrollToFooter();
    wikiActivity.verifyGlobalNavigation();
  }

  @Test(groups = {"globalNavigationBarLayoutForEnglishAnon"})
  public void testLayoutForEnglishAnon() {
    GlobalNavigation globalNavigation = new HomePage().getGlobalNavigation();

    Assert.assertTrue(globalNavigation.isFandomLogoVisible());
    Assert.assertTrue(globalNavigation.isGamesHubVisible());
    Assert.assertTrue(globalNavigation.isMoviesHubVisible());
    Assert.assertTrue(globalNavigation.isTVHubVisible());
    Assert.assertTrue(globalNavigation.isWikisMenuVisible());
    Assert.assertTrue(globalNavigation.isSearchInputVisible());
    Assert.assertTrue(globalNavigation.isAccountMenuVisible());
    Assert.assertTrue(globalNavigation.isStartWikiButtonVisible());

    Assert.assertFalse(globalNavigation.isCommunityCentralLinkVisible());
    Assert.assertFalse(globalNavigation.isUserAvatarVisible());
    Assert.assertFalse(globalNavigation.isNotificationsIconVisible());
  }

  @Test(groups = {"globalNavigationBarLayoutForEnglishLoggedIn"})
  @Execute(asUser = User.USER)
  public void testLayoutForEnglishLoggedIn() {
    GlobalNavigation globalNavigation = new HomePage().getGlobalNavigation();

    Assert.assertTrue(globalNavigation.isFandomLogoVisible());
    Assert.assertTrue(globalNavigation.isGamesHubVisible());
    Assert.assertTrue(globalNavigation.isMoviesHubVisible());
    Assert.assertTrue(globalNavigation.isTVHubVisible());
    Assert.assertTrue(globalNavigation.isWikisMenuVisible());
    Assert.assertTrue(globalNavigation.isSearchInputVisible());
    Assert.assertTrue(globalNavigation.isUserAvatarVisible());
    Assert.assertTrue(globalNavigation.isNotificationsIconVisible());
    Assert.assertTrue(globalNavigation.isStartWikiButtonVisible());

    Assert.assertFalse(globalNavigation.isCommunityCentralLinkVisible());
    Assert.assertFalse(globalNavigation.isAccountMenuVisible());
  }


  @Test(groups = {"globalNavigationBarLayoutForDeAnon1284x900"})
  @Execute(onWikia = "de.gta")
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void testLayoutForDeAnon1284x900() {
    GlobalNavigation globalNavigation = new HomePage().getGlobalNavigation();

    Assert.assertTrue(globalNavigation.isFandomLogoVisible());
    Assert.assertTrue(globalNavigation.isCommunityCentralLinkVisible());
    Assert.assertTrue(globalNavigation.isSearchInputVisible());
    Assert.assertTrue(globalNavigation.isAccountMenuVisible());
    Assert.assertTrue(globalNavigation.isStartWikiButtonVisible());
    Assert.assertTrue(globalNavigation.isPartnerSlotLinkVisible());

    Assert.assertFalse(globalNavigation.isGamesHubVisible());
    Assert.assertFalse(globalNavigation.isMoviesHubVisible());
    Assert.assertFalse(globalNavigation.isTVHubVisible());
    Assert.assertFalse(globalNavigation.isWikisMenuVisible());
    Assert.assertFalse(globalNavigation.isUserAvatarVisible());
    Assert.assertFalse(globalNavigation.isNotificationsIconVisible());
  }

  @Test(groups = {"globalNavigationBarLayoutForDeLoggedIn1284x900"})
  @Execute(onWikia = "de.gta", asUser = User.USER_GERMAN)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void testLayoutForDeLoggedIn1284x900() {
    GlobalNavigation globalNavigation = new HomePage().getGlobalNavigation();

    Assert.assertTrue(globalNavigation.isFandomLogoVisible());
    Assert.assertTrue(globalNavigation.isCommunityCentralLinkVisible());
    Assert.assertTrue(globalNavigation.isSearchInputVisible());
    Assert.assertTrue(globalNavigation.isUserAvatarVisible());
    Assert.assertTrue(globalNavigation.isNotificationsIconVisible());
    Assert.assertTrue(globalNavigation.isStartWikiButtonVisible());
    Assert.assertTrue(globalNavigation.isPartnerSlotLinkVisible());

    Assert.assertFalse(globalNavigation.isGamesHubVisible());
    Assert.assertFalse(globalNavigation.isMoviesHubVisible());
    Assert.assertFalse(globalNavigation.isTVHubVisible());
    Assert.assertFalse(globalNavigation.isWikisMenuVisible());
    Assert.assertFalse(globalNavigation.isAccountMenuVisible());
  }

  @Test(groups = {"globalNavigationBarLayoutForDeAnon"})
  @Execute(onWikia = "de.gta")
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_SMALL)
  public void testLayoutForDeAnon() {
    GlobalNavigation globalNavigation = new HomePage().open().getGlobalNavigation();

    Assert.assertTrue(globalNavigation.isFandomLogoVisible());
    Assert.assertTrue(globalNavigation.isCommunityCentralLinkVisible());
    Assert.assertTrue(globalNavigation.isSearchInputVisible());
    Assert.assertTrue(globalNavigation.isAccountMenuVisible());
    Assert.assertTrue(globalNavigation.isStartWikiButtonVisible());

    Assert.assertFalse(globalNavigation.isGamesHubVisible());
    Assert.assertFalse(globalNavigation.isMoviesHubVisible());
    Assert.assertFalse(globalNavigation.isTVHubVisible());
    Assert.assertFalse(globalNavigation.isWikisMenuVisible());
    Assert.assertFalse(globalNavigation.isUserAvatarVisible());
    Assert.assertFalse(globalNavigation.isNotificationsIconVisible());
    Assert.assertFalse(globalNavigation.isPartnerSlotLinkVisible());
  }

  @Test(groups = {"globalNavigationBarLayoutForDeLoggedIn"})
  @Execute(onWikia = "de.gta", asUser = User.USER_GERMAN)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_SMALL)
  public void testLayoutForDeLoggedIn() {
    GlobalNavigation globalNavigation = new HomePage().getGlobalNavigation();

    Assert.assertTrue(globalNavigation.isFandomLogoVisible());
    Assert.assertTrue(globalNavigation.isCommunityCentralLinkVisible());
    Assert.assertTrue(globalNavigation.isSearchInputVisible());
    Assert.assertTrue(globalNavigation.isUserAvatarVisible());
    Assert.assertTrue(globalNavigation.isNotificationsIconVisible());
    Assert.assertTrue(globalNavigation.isStartWikiButtonVisible());

    Assert.assertFalse(globalNavigation.isGamesHubVisible());
    Assert.assertFalse(globalNavigation.isMoviesHubVisible());
    Assert.assertFalse(globalNavigation.isTVHubVisible());
    Assert.assertFalse(globalNavigation.isWikisMenuVisible());
    Assert.assertFalse(globalNavigation.isAccountMenuVisible());
    Assert.assertFalse(globalNavigation.isPartnerSlotLinkVisible());
  }
}
