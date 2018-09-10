package com.wikia.webdriver.testcases.desktop.navigation.global;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RunOnly;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.wikia.webdriver.elements.communities.desktop.components.navigation.global.GlobalNavigation;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWikiActivityPageObject;
import org.testng.annotations.Test;

@Test(groups = {"globalnavigationbar", "globalnavigationbarLayout"})
public class GlobalNavigationLayout extends NewTestTemplate {

  @Test(groups = {"globalNavigationBarIsFixedOnScrollForAnon"})
  public void globalNavigationBarIsFixedOnScrollForAnon() {
    SpecialWikiActivityPageObject wikiActivity = new SpecialWikiActivityPageObject().open();
    wikiActivity.verifyGlobalNavigation();
    wikiActivity.scrollToFooter();
    wikiActivity.verifyGlobalNavigation();
  }

  @DontRun(language = "szl")
  @Test(groups = {"globalNavigationBarLayoutForEnglishAnon"})
  public void testLayoutForEnglishAnon() {
    GlobalNavigation globalNavigation = new HomePage().getGlobalNavigation();

    Assertion.assertTrue(globalNavigation.isFandomLogoVisible());
    Assertion.assertTrue(globalNavigation.isGamesHubVisible());
    Assertion.assertTrue(globalNavigation.isMoviesHubVisible());
    Assertion.assertTrue(globalNavigation.isTVHubVisible());
    Assertion.assertTrue(globalNavigation.isVideoHubVisible());
    Assertion.assertTrue(globalNavigation.isWikisMenuVisible());
    Assertion.assertTrue(globalNavigation.isSearchInputVisible());
    Assertion.assertTrue(globalNavigation.isStartWikiButtonVisible());

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
    Assertion.assertTrue(globalNavigation.isVideoHubVisible());
    Assertion.assertTrue(globalNavigation.isWikisMenuVisible());
    Assertion.assertTrue(globalNavigation.isSearchInputVisible());
    Assertion.assertTrue(globalNavigation.isUserAvatarVisible());
    Assertion.assertTrue(globalNavigation.isNotificationsIconVisible());
    Assertion.assertTrue(globalNavigation.isStartWikiButtonVisible());
  }

  @DontRun(language = "szl")
  @Test(groups = {"globalNavigationBarLayoutForDeAnon"})
  @Execute(onWikia = "gta", language = "de")
  public void testLayoutForDeAnon() {
    GlobalNavigation globalNavigation = new HomePage().open().getGlobalNavigation();

    Assertion.assertTrue(globalNavigation.isFandomLogoVisible());
    Assertion.assertTrue(globalNavigation.isSearchInputVisible());
    Assertion.assertTrue(globalNavigation.isStartWikiButtonVisible());
    Assertion.assertTrue(globalNavigation.isPartnerSlotLinkVisible());
    Assertion.assertTrue(globalNavigation.isVideospieleHubVisible());
    Assertion.assertTrue(globalNavigation.isFilmeHubVisible());
    Assertion.assertTrue(globalNavigation.isTVDEHubVisible());
    Assertion.assertTrue(globalNavigation.isWikisMenuVisible());

    Assertion.assertFalse(globalNavigation.isVideoHubVisible());
    Assertion.assertFalse(globalNavigation.isUserAvatarVisible());
    Assertion.assertFalse(globalNavigation.isNotificationsIconVisible());
  }

  @RunOnly(language = "szl")
  @Test(groups = {"globalNavigationBarLayoutForDeAnon"})
  public void testLayoutForAnonSzl() {
    GlobalNavigation globalNavigation = new HomePage().open().getGlobalNavigation();

    Assertion.assertTrue(globalNavigation.isFandomLogoVisible());
    Assertion.assertTrue(globalNavigation.isSearchInputVisible());
    Assertion.assertTrue(globalNavigation.isStartWikiButtonVisible());
    Assertion.assertTrue(globalNavigation.isGryHubVisible());
    Assertion.assertTrue(globalNavigation.isFilmyHubVisible());
    Assertion.assertTrue(globalNavigation.isTVDEHubVisible());
    Assertion.assertTrue(globalNavigation.isWikisMenuVisible());

    Assertion.assertFalse(globalNavigation.isVideoHubVisible());
    Assertion.assertFalse(globalNavigation.isUserAvatarVisible());
    Assertion.assertFalse(globalNavigation.isNotificationsIconVisible());
  }

  @DontRun(language = "szl")
  @Test(groups = {"globalNavigationBarLayoutForDeLoggedIn"})
  @Execute(onWikia = "gta", language = "de", asUser = User.USER_GERMAN)
  public void testLayoutForDeLoggedIn() {
    GlobalNavigation globalNavigation = new HomePage().getGlobalNavigation();

    Assertion.assertTrue(globalNavigation.isFandomLogoVisible());
    Assertion.assertTrue(globalNavigation.isSearchInputVisible());
    Assertion.assertTrue(globalNavigation.isStartWikiButtonVisible());
    Assertion.assertTrue(globalNavigation.isPartnerSlotLinkVisible());
    Assertion.assertTrue(globalNavigation.isVideospieleHubVisible());
    Assertion.assertTrue(globalNavigation.isFilmeHubVisible());
    Assertion.assertTrue(globalNavigation.isTVDEHubVisible());
    Assertion.assertTrue(globalNavigation.isWikisMenuVisible());
    Assertion.assertTrue(globalNavigation.isUserAvatarVisible());
    Assertion.assertTrue(globalNavigation.isNotificationsIconVisible());

    Assertion.assertFalse(globalNavigation.isVideoHubVisible());
  }

  @RunOnly(language = "szl")
  @Test(groups = {"globalNavigationBarLayoutForDeLoggedIn"})
  @Execute(language = "szl", asUser = User.USER_GERMAN)
  public void testLayoutForLoggedInSzl() {
    GlobalNavigation globalNavigation = new HomePage().getGlobalNavigation();

    Assertion.assertTrue(globalNavigation.isFandomLogoVisible());
    Assertion.assertTrue(globalNavigation.isSearchInputVisible());
    Assertion.assertTrue(globalNavigation.isStartWikiButtonVisible());
    Assertion.assertTrue(globalNavigation.isPartnerSlotLinkVisible());
    Assertion.assertTrue(globalNavigation.isVideospieleHubVisible());
    Assertion.assertTrue(globalNavigation.isFilmeHubVisible());
    Assertion.assertTrue(globalNavigation.isTVDEHubVisible());
    Assertion.assertTrue(globalNavigation.isWikisMenuVisible());
    Assertion.assertTrue(globalNavigation.isUserAvatarVisible());
    Assertion.assertTrue(globalNavigation.isNotificationsIconVisible());

    Assertion.assertFalse(globalNavigation.isVideoHubVisible());
  }
}
