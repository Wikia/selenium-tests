package com.wikia.webdriver.testcases.mobilewikitests;

import com.wikia.webdriver.common.contentpatterns.MobileSubpages;
import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.elements.mercury.pages.discussions.DiscussionsPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.navigation.local.LocalNavigationMobile;

import org.testng.annotations.Test;

@Test(groups = "Mobile_LocalNavigation")
@Execute(onWikia = MobileWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class LocalNavigationMobileTests extends NewTestTemplate {

  @Test
  public void communityBarIsVisibleForAnon() {
     LocalNavigationMobile localNavMobile =
          new ArticlePage()
              .open(MobileSubpages.MAIN_PAGE)
              .getLocalNavigationMobile();

   Assertion.assertTrue(localNavMobile.isCommunityBarVisible());
   Assertion.assertTrue(localNavMobile.isCommunityNameVisible());
   Assertion.assertTrue(localNavMobile.isDiscussionsEntrypointVisible());
   Assertion.assertTrue(localNavMobile.isNavigationEntryPointVisible());

  }

  @Test
  @Execute(asUser = User.USER)
  public void communityBarIsVisibleForLoggedInUser() {
    LocalNavigationMobile localNavMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getLocalNavigationMobile();

    Assertion.assertTrue(localNavMobile.isCommunityBarVisible());
    Assertion.assertTrue(localNavMobile.isCommunityNameVisible());
    Assertion.assertTrue(localNavMobile.isDiscussionsEntrypointVisible());
    Assertion.assertTrue(localNavMobile.isNavigationEntryPointVisible());

  }

  @Test
  public void clickOnCommunityNameTakesUserToWikiMainpage() {
    LocalNavigationMobile localNavMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getLocalNavigationMobile();

    localNavMobile.clickCommunityName();

    ArticlePage article = new ArticlePage();

    Assertion.assertEquals(article.getArticleTitle(), "Mercury automation testing Wiki");

  }

  @Test
  @Execute(asUser = User.USER)
  public void clickOnDiscussionsIconTakesUserToDiscussions() {
    LocalNavigationMobile localNavMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getLocalNavigationMobile();

    localNavMobile.clickDiscussionsEntrypoint();

    DiscussionsPage discussions = new DiscussionsPage();

    Assertion.assertTrue(discussions.isDiscussions());
  }

  @Test
  public void clickOnLocalNavEntrypointOpensAndClosesLocalMenu() {
    LocalNavigationMobile localNavMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getLocalNavigationMobile();

    localNavMobile.clickNavigationEntrypoint();

    Assertion.assertTrue(localNavMobile.isSubMenuOpened());

    localNavMobile.clickNavigationEntrypoint();

    Assertion.assertTrue(localNavMobile.isSubMenuClosed());
  }

  @Test
  public void navigationThroughMenuLevelsWorksCorrectly() {
    LocalNavigationMobile localNavMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getLocalNavigationMobile();
    localNavMobile
        .clickNavigationEntrypoint()
        .open1stlevelMenuElement(0);

    Assertion.assertTrue(localNavMobile.isBackButtonVisible());
    Assertion.assertTrue(localNavMobile.is2ndLevelMenuHeaderVisible());

    localNavMobile.clickBackButton();

    Assertion.assertTrue(localNavMobile.isSubMenuOpened());
  }

}
