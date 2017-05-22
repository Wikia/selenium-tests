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
    new HomePage()
        .getGlobalNavigation()
        .clickFandomLogo();

    Assertion.assertEquals(driver.getCurrentUrl(), urlBuilder.getUrlForPage("fandom", "/"));
  }

  @Test(groups = {"gamesHubLinkClickOnEnCommunityOpensGamesHub"})
  public void testGamesHubLink() {
    new HomePage()
        .getGlobalNavigation()
        .clickGamesHubLink();

    Assertion.assertEquals(driver.getCurrentUrl(), urlBuilder.getUrlForPage("fandom", "/games"));
  }

  @Test(groups = {"moviesHubLinkClickOnEnCommunityOpensMoviesHub"})
  public void testMoviesHubLink() {
   new HomePage()
        .getGlobalNavigation()
        .clickMoviesHubLink();

    Assertion.assertEquals(driver.getCurrentUrl(), urlBuilder.getUrlForPage("fandom", "/movies"));
  }

  @Test(groups = {"tvHubLinkClickOnEnCommunityOpensTvHub"})
  public void testTVHubLink() {
    new HomePage()
        .getGlobalNavigation()
        .clickTVHubLink();

    Assertion.assertEquals(driver.getCurrentUrl(), urlBuilder.getUrlForPage("fandom", "/tv"));
  }

  @Test(groups = {"communityCentralLinkClickOnDeCommunityOpensDeCommunityCentral"})
  @Execute(onWikia = "de.gta")
  public void testCommunityCentralLinkOnDeCommunity() {
    new HomePage()
        .getGlobalNavigation()
        .clickCommunityCentralLink();

    Assertion.assertEquals(driver.getCurrentUrl(), urlBuilder.getUrlForPage("de.community", "/wiki/Community_Deutschland"));
  }

  @Test(groups = {"exploreWikisLinkClickOnEnCommunityOpensExplorePage"})
  public void testExploreWikisLink() {
    new HomePage()
        .getGlobalNavigation()
        .openWikisMenu()
        .clickExploreWikisLink();

    Assertion.assertEquals(driver.getCurrentUrl(), urlBuilder.getUrlForPage("fandom", "/explore"));
  }

  @Test(groups = {"fandomUniversityLinkClickOnEnCommunityOpensFandomUniversity"})
  public void testFandomUniversityLink() {
   new HomePage()
        .getGlobalNavigation()
        .openWikisMenu()
        .clickFandomUniversityLink();

    Assertion.assertEquals(driver.getCurrentUrl(), urlBuilder.getUrlForPage("community", "/wiki/Fandom_University"));
  }

  @Test(groups = {"communityCentralLinkClickOnEnCommunityOpensEnCommunityCentral"})
  public void testCommunityCentralLink() {
    new HomePage()
        .getGlobalNavigation()
        .openWikisMenu()
        .clickCommunityCentralLink();

    Assertion.assertEquals(driver.getCurrentUrl(), urlBuilder.getUrlForPage("community", "/wiki/Community_Central"));
  }
}
