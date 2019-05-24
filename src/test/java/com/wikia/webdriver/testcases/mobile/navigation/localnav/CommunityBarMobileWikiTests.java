package com.wikia.webdriver.testcases.mobile.navigation.localnav;

import com.wikia.webdriver.common.contentpatterns.MobileSubpages;
import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.mobile.pages.ArticlePage;
import com.wikia.webdriver.elements.communities.mobile.components.navigation.local.CommunityBarMobile;

import org.testng.annotations.Test;

//Class tests all available functionality on Community Bar (commonly named also as LocalNav) on mobile wiki

@Test(groups = "MobileWiki_LocalNavigation")
@Execute(onWikia = MobileWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class CommunityBarMobileWikiTests extends NewTestTemplate {

  @Test
  public void communityBarIsVisibleForAnon() {
     CommunityBarMobile localNavMobile =
          new ArticlePage()
              .open(MobileSubpages.MAIN_PAGE)
              .getCommunityBarMobile();

   Assertion.assertTrue(localNavMobile.isCommunityBarVisible());
   Assertion.assertTrue(localNavMobile.isCommunityNameVisible());
   Assertion.assertTrue(localNavMobile.isDiscussionsEntrypointVisible());
   Assertion.assertTrue(localNavMobile.isNavigationEntryPointVisible());

  }

  @Test
  @Execute(asUser = User.USER)
  public void communityBarIsVisibleForLoggedInUser() {
    CommunityBarMobile localNavMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getCommunityBarMobile();

    Assertion.assertTrue(localNavMobile.isCommunityBarVisible());
    Assertion.assertTrue(localNavMobile.isCommunityNameVisible());
    Assertion.assertTrue(localNavMobile.isDiscussionsEntrypointVisible());
    Assertion.assertTrue(localNavMobile.isNavigationEntryPointVisible());

  }

  @Test
  public void clickOnCommunityNameTakesUserToWikiMainpage() {
    CommunityBarMobile localNavMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getCommunityBarMobile();

    localNavMobile.clickCommunityName();

    ArticlePage article = new ArticlePage();

    Assertion.assertTrue(article.getArticleTitle().contains("Mercury automation testing Wiki"),"Title should contain: 'Mercury automation testing Wikia' but it doesn't!");

  }

  @Test
  @Execute(asUser = User.USER)
  public void clickOnDiscussionsIconTakesUserToFeeds() {
    CommunityBarMobile localNavMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getCommunityBarMobile();

    localNavMobile.clickDiscussionsEntrypoint();

    Assertion.assertStringContains(driver.getCurrentUrl(), String.format("%s/f", Configuration.getEnvType().getDomain()));
  }

  @Test
  public void clickOnLocalNavEntryPointOpensAndClosesLocalMenu() {
    CommunityBarMobile localNavMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getCommunityBarMobile();

    localNavMobile.clickNavigationEntrypoint();

    Assertion.assertTrue(localNavMobile.isSubMenuOpened());

    localNavMobile.clickNavigationEntrypoint();

    Assertion.assertTrue(localNavMobile.isSubMenuClosed());
  }

  @Test
  public void navigationThroughMenuLevelsWorksCorrectly() {
    CommunityBarMobile localNavMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getCommunityBarMobile();
    localNavMobile
        .clickNavigationEntrypoint()
        .open1stlevelMenuElement(0);

    Assertion.assertTrue(localNavMobile.isBackButtonVisible());
    Assertion.assertTrue(localNavMobile.is2ndLevelMenuHeaderVisible());

    localNavMobile.clickBackButton();

    Assertion.assertTrue(localNavMobile.isSubMenuOpened());
  }


}
