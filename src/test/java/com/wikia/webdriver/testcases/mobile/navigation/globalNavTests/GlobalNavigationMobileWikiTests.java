package com.wikia.webdriver.testcases.mobile.navigation.globalNavTests;

import com.wikia.webdriver.common.contentpatterns.MobileSubpages;
import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mobile.components.ContentRecommendationsMobile;
import com.wikia.webdriver.pageobjectsfactory.componentobject.navigation.mobileNav.global.GlobalNavigationMobile;
import com.wikia.webdriver.elements.mobile.pages.ArticlePage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.navigation.mobileNav.local.CommunityBarMobile;

import org.testng.annotations.Test;

@Test(groups = "MobileWiki_GlobalNav")
@Execute(onWikia = MobileWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class GlobalNavigationMobileWikiTests extends NewTestTemplate {

  @Test
  public void globalNavIsAlwaysVisible() {
    GlobalNavigationMobile globalNavigationMobile =
        new ArticlePage()
            .openDefault()
            .getGlobalNavigationMobile();

    Assertion.assertTrue(globalNavigationMobile.isNavigationBarVisible());
    Assertion.assertTrue(globalNavigationMobile.isLogoVisible());
    Assertion.assertTrue(globalNavigationMobile.isSearchIconVisible());
    Assertion.assertTrue(globalNavigationMobile.isAnonUserAvatarVisible());

    driver.executeScript("window.scrollTo(100, document.body.scrollHeight)");

    Assertion.assertTrue(globalNavigationMobile.isNavigationBarVisible());
    Assertion.assertTrue(globalNavigationMobile.isLogoHeartVisible());
    Assertion.assertTrue(globalNavigationMobile.isSearchIconVisible());

    CommunityBarMobile communityName = new CommunityBarMobile();

    Assertion.assertTrue(communityName.isCommunityNameVisible());
  }

  @Test
  public void closeButtonAppears() {
    GlobalNavigationMobile globalNavigationMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getGlobalNavigationMobile();

    globalNavigationMobile.openSearch();
    Assertion.assertTrue(globalNavigationMobile.isCloseIconVisible());

    globalNavigationMobile.clickCloseButton();
    Assertion.assertTrue(globalNavigationMobile.isSearchIconVisible());
  }

  @Test
  public void fandomLogoRedirectsToFandomPage() {
    GlobalNavigationMobile globalNavigationMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getGlobalNavigationMobile();

    globalNavigationMobile.clickFandomLogo();

    Assertion.assertTrue(globalNavigationMobile.getCurrentUrl().contains("fandom.wikia.com"));
  }

  @Test
  public void searchIsPresentInGlobalNav() {
    GlobalNavigationMobile globalNavigationMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getGlobalNavigationMobile();

    Assertion.assertTrue(globalNavigationMobile.isSearchComponentPresent());
  }

  @Test
  public void hubsLinksOnEnWiki() {
    GlobalNavigationMobile globalNavigationMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getGlobalNavigationMobile();

    globalNavigationMobile.openSearch();

    Assertion.assertTrue(globalNavigationMobile.areInterntionalHubLinksVisible());
    Assertion.assertTrue(globalNavigationMobile.isVideoHubLinkVisible());
  }

  @Test
  @Execute(onWikia = "de.dman")
  public void hubsLinksOnNonEnWiki() {
    GlobalNavigationMobile globalNavigationMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getGlobalNavigationMobile();

    globalNavigationMobile.openSearch();

    Assertion.assertTrue(globalNavigationMobile.areInterntionalHubLinksVisible());
    Assertion.assertFalse(globalNavigationMobile.isVideoHubLinkVisible());
  }

  //wikis link under search is not clickable for some reason
  @Test(enabled = false)
  public void userCanNotCreateNewWikiOnMobile() {
    GlobalNavigationMobile globalNavigationMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getGlobalNavigationMobile();

    globalNavigationMobile.openSearch();
    globalNavigationMobile.clickWikisMenuLink();

    Assertion.assertFalse(globalNavigationMobile.isStartAwikiButtonNotVisibleinWikisDropdown());

  }

  @Test
  public void trendingArticlesModuleOpensUnderMobileSearchOnEnWikis() {
    GlobalNavigationMobile globalNavigationMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getGlobalNavigationMobile();

    globalNavigationMobile.openSearch();

    ContentRecommendationsMobile trendingArticles = new ContentRecommendationsMobile();

    Assertion.assertTrue(trendingArticles.isTrendingArticlesModuleVisible());
  }

  @Test
  @Execute(onWikia = MobileWikis.DE_WIKI)
  public void trendingArticlesModuleDoesNotOpenUnderMobileSearchOnNonEnWikis() {
    GlobalNavigationMobile globalNavigationMobile =
        new ArticlePage()
            .openDefault()
            .getGlobalNavigationMobile();

    globalNavigationMobile.openSearch();

    ContentRecommendationsMobile trendingArticles = new ContentRecommendationsMobile();

    Assertion.assertTrue(trendingArticles.isTrendingArticlesModuleNotVisible());
  }

}
