package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class NavigationTest extends NewTestTemplate {

  @Test(groups = "mercury_navigation_openAndCloseNavigationAndItsSubMenu")
  public void mercury_navigation_openAndCloseNavigationAndItsSubMenu() {
    TopBar topBar =
        new ArticlePage()
          .open(MercurySubpages.MAIN_PAGE)
          .getTopBar();

    topBar
        .openNavigation()
        .openSubMenu(1)
        .closeSubMenu();

    topBar.clickCloseButton();

    Assertion.assertTrue(topBar.isHamburgerIconVisible());
  }

  @Test(groups = "mercury_navigation_resetNavigationState")
  public void mercury_navigation_resetNavigationState() {
    TopBar topBar =
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopBar();

    Navigation navigation = topBar.openNavigation();

    Assertion.assertTrue(navigation.isMainHeaderVisible());
    navigation.openSubMenu(1);

    Assertion.assertTrue(navigation.isBackButtonVisible());
    topBar.clickCloseButton();

    topBar.openNavigation();
    Assertion.assertTrue(navigation.isMainHeaderVisible());
  }

  @Test(groups = "mercury_navigation_backButton")
  public void mercury_navigation_backButton() {
    Navigation navigation =
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopBar()
            .openNavigation();

    Assertion.assertTrue(navigation.isMainHeaderVisible());
    navigation.openSubMenu(1);

    Assertion.assertTrue(navigation.isBackButtonVisible());
    navigation.clickBackButton();

    Assertion.assertTrue(navigation.isMainHeaderVisible());
  }

  @Test(groups = "mercury_navigation_navigationOnEnglishWiki")
  public void mercury_navigation_navigationOnEnglishWiki() {
    Navigation navigation =
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopBar()
            .openNavigation();

    Assertion.assertTrue(navigation.areHubLinksVisible());
  }

  @Execute(onWikia = MercuryWikis.DE_WIKI)
  @Test(groups = "mercury_navigation_navigationOnNonEnglishWiki")
  public void mercury_navigation_navigationOnNonEnglishWiki() {
    Navigation navigation =
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopBar()
            .openNavigation();

    Assertion.assertFalse(navigation.areHubLinksVisible());
  }

  @Execute(asUser = User.USER)
  @Test(groups = "mercury_navigation_navigationElementsUserLoggedIn")
  public void mercury_navigation_navigationElementsUserLoggedIn() {
    Navigation navigation =
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopBar()
            .openNavigation();

    Assertion.assertTrue(navigation.isUserAvatarVisible());
    Assertion.assertTrue(navigation.isUserProfileLinkVisible());
    Assertion.assertTrue(navigation.isLogoutLinkVisible());
  }

  @Execute(asUser = User.ANONYMOUS)
  @Test(groups = "mercury_navigation_navigationElementsAnonymousUser")
  public void mercury_navigation_navigationElementsAnonymousUser() {
    Navigation navigation =
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopBar()
            .openNavigation();

    Assertion.assertTrue(navigation.isUserAvatarVisible());
    Assertion.assertFalse(navigation.isUserProfileLinkVisible());
    Assertion.assertFalse(navigation.isLogoutLinkVisible());
    Assertion.assertEquals(navigation.getNavigationHeaderText(), "Sign In | Register");
  }

  @Test(groups = "mercury_navigation_exploreWikiNavigatesToWikiMainPage")
  public void mercury_navigation_exploreWikiNavigatesToWikiMainPage() {
    new ArticlePage()
        .open(MercurySubpages.INFOBOX_1)
        .getTopBar()
        .openNavigation()
        .clickExploreWikiHeader();

    Assertion.assertTrue(driver.getCurrentUrl().contains(MercurySubpages.MAIN_PAGE));
  }
}
