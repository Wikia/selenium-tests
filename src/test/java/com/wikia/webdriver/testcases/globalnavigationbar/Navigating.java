package com.wikia.webdriver.testcases.globalnavigationbar;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigation;
import org.testng.annotations.Test;

@Test(groups = {"globalnavigationbar", "globalnavigationbarNavigating"})
public class Navigating extends NewTestTemplate {

  @Test(groups = {"fandomLogoClickOnEnCommunityOpensFandomWikia"})
  public void logoClickOnEnglishCommunityOpensFandom() {
    HomePage homePage = new HomePage();
    GlobalNavigation globalNav = homePage.getGlobalNavigation();


    homePage = globalNav.clickFandomLogo();

    Assertion.assertEquals(homePage.getCurrentUrl(), urlBuilder.getUrlForPage("fandom", "/"));
  }

  @Test(groups = {"gamesHubLinkClickOnEnCommunityOpensGamesHub"})
  public void testGamesHubLink() {
    HomePage homePage = new HomePage();
    GlobalNavigation globalNav = homePage.getGlobalNavigation();

    homePage = globalNav.clickGamesHubLink();

    Assertion.assertEquals(homePage.getCurrentUrl(), urlBuilder.getUrlForPage("fandom", "/games"));
  }

  @Test(groups = {"moviesHubLinkClickOnEnCommunityOpensMoviesHub"})
  public void testMoviesHubLink() {
    HomePage homePage = new HomePage();
    GlobalNavigation globalNav = homePage.getGlobalNavigation();

    homePage = globalNav.clickMoviesHubLink();

    Assertion.assertEquals(homePage.getCurrentUrl(), urlBuilder.getUrlForPage("fandom", "/movies"));
  }

  @Test(groups = {"tvHubLinkClickOnEnCommunityOpensTvHub"})
  public void testTVHubLink() {
    HomePage homePage = new HomePage();
    GlobalNavigation globalNav = homePage.getGlobalNavigation();

    homePage = globalNav.clickTVHubLink();

    Assertion.assertEquals(homePage.getCurrentUrl(), urlBuilder.getUrlForPage("fandom", "/tv"));
  }

  @Test(groups = {"communityCentralLinkClickOnDeCommunityOpensDeCommunityCentral"})
  @Execute(onWikia = "de.gta")
  public void testCommunityCentralLinkOnDeCommunity() {
    HomePage homePage = new HomePage();
    GlobalNavigation globalNav = homePage.getGlobalNavigation();

    homePage = globalNav.clickCommunityCentralLink();

    Assertion.assertEquals(homePage.getCurrentUrl(), urlBuilder.getUrlForPage("de.community", "/wiki/Community_Deutschland"));
  }

  @Test(groups = {"exploreWikisLinkClickOnEnCommunityOpensExplorePage"})
  public void testExploreWikisLink() {
    HomePage homePage = new HomePage();
    GlobalNavigation globalNav = homePage.getGlobalNavigation();

    globalNav = globalNav.openWikisMenu();
    homePage = globalNav.clickExploreWikisLink();

    Assertion.assertEquals(homePage.getCurrentUrl(), urlBuilder.getUrlForPage("fandom", "/explore"));
  }

  @Test(groups = {"fandomUniversityLinkClickOnEnCommunityOpensFandomUniversity"})
  public void testFandomUniversityLink() {
    HomePage homePage = new HomePage();
    GlobalNavigation globalNav = homePage.getGlobalNavigation();

    globalNav = globalNav.openWikisMenu();
    homePage = globalNav.clickFandomUniversityLink();

    Assertion.assertEquals(homePage.getCurrentUrl(), urlBuilder.getUrlForPage("community", "/wiki/Fandom_University"));
  }

  @Test(groups = {"communityCentralLinkClickOnEnCommunityOpensEnCommunityCentral"})
  public void testCommunityCentralLink() {
    HomePage homePage = new HomePage();
    GlobalNavigation globalNav = homePage.getGlobalNavigation();

    globalNav = globalNav.openWikisMenu();
    homePage = globalNav.clickCommunityCentralLink();

    Assertion.assertEquals(homePage.getCurrentUrl(), urlBuilder.getUrlForPage("community", "/wiki/Community_Central"));
  }
}
