package com.wikia.webdriver.testcases.desktop.navigation.global;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.annotations.RunOnly;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.core.url.UrlChecker;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;
import org.testng.annotations.Test;

import static com.wikia.webdriver.common.contentpatterns.URLsContent.*;
import static com.wikia.webdriver.common.core.configuration.Configuration.DEFAULT_LANGUAGE;

@Test(groups = {"globalnavigationbar", "globalnavigationbarNavigating"})
public class GlobalNavigationNavigating extends NewTestTemplate {

  UrlChecker urlChecker = new UrlChecker();

  @DontRun(language = "szl")
  @Test(groups = {"fandomLogoClickOnEnCommunityOpensFandomWikia"})
  public void logoClickOnEnglishCommunityOpensFandom() {
    new HomePage().getGlobalNavigation().clickFandomLogo();

    Assertion.assertEquals(
            urlChecker.getProtocolRelativeURL(driver.getCurrentUrl()),
            urlChecker.getProtocolRelativeURL(fandomUrlBuilder.getFandomUrl(new Configuration().getEnvType()))
    );

  }

  @DontRun(language = "szl")
  @Test(groups = {"gamesHubLinkClickOnEnCommunityOpensGamesHub"})
  public void testGamesHubLink() {
    new HomePage().getGlobalNavigation().clickGamesHubLink();

    Assertion.assertEquals(
            urlChecker.getProtocolRelativeURL(driver.getCurrentUrl()),
            urlChecker.getProtocolRelativeURL(
                    fandomUrlBuilder.getFandomUrl(new Configuration().getEnvType()) + "topics/games")
    );
  }

  @RunOnly(language = "szl")
  @Test(groups = {"gamesHubLinkClickOnEnCommunityOpensGamesHub"})
  public void testGamesHubLinkSzl() {
    new HomePage().getGlobalNavigation().clickGamesHubLink();

    Assertion.assertEquals(
            urlChecker.getProtocolRelativeURL(driver.getCurrentUrl()),
            urlChecker.getProtocolRelativeURL(
                    fandomUrlBuilder.getFandomUrl(new Configuration().getEnvType()) + HUBS_SZL + "#Gry")
    );
  }

  @DontRun(language = "szl")
  @Test(groups = {"moviesHubLinkClickOnEnCommunityOpensMoviesHub"})
  public void testMoviesHubLink() {
    new HomePage().getGlobalNavigation().clickMoviesHubLink();

    Assertion.assertEquals(
            urlChecker.getProtocolRelativeURL(driver.getCurrentUrl()),
            urlChecker.getProtocolRelativeURL(
                    fandomUrlBuilder.getFandomUrl(new Configuration().getEnvType()) + "topics/movies")
    );
  }

  @RunOnly(language = "szl")
  @Test(groups = {"moviesHubLinkClickOnEnCommunityOpensMoviesHub"})
  public void testMoviesHubLinkSzl() {
    new HomePage().getGlobalNavigation().clickMoviesHubLink();

    Assertion.assertEquals(
            urlChecker.getProtocolRelativeURL(driver.getCurrentUrl()),
            urlChecker.getProtocolRelativeURL(
                    fandomUrlBuilder.getFandomUrl(new Configuration().getEnvType()) + HUBS_SZL + "#Filmy")
    );
  }

  @DontRun(language = "szl")
  @Test(groups = {"tvHubLinkClickOnEnCommunityOpensTvHub"})
  public void testTVHubLink() {
    new HomePage().getGlobalNavigation().clickTVHubLink();

    Assertion.assertEquals(
            urlChecker.getProtocolRelativeURL(driver.getCurrentUrl()),
            urlChecker.getProtocolRelativeURL(
                    fandomUrlBuilder.getFandomUrl(new Configuration().getEnvType()) + "topics/tv")
    );
  }

  @RunOnly(language = "szl")
  @Test(groups = {"tvHubLinkClickOnEnCommunityOpensTvHub"})
  public void testTVHubLinkSzl() {
    new HomePage().getGlobalNavigation().clickTVHubLink();

    Assertion.assertEquals(
            urlChecker.getProtocolRelativeURL(driver.getCurrentUrl()),
            urlChecker.getProtocolRelativeURL(
                    fandomUrlBuilder.getFandomUrl(new Configuration().getEnvType()) + HUBS_SZL + "#TV")
    );
  }

  @RelatedIssue(issueID = "IW-1607")
  @DontRun(language = "szl")
  @Test(groups = {"exploreWikisLinkClickOnEnCommunityOpensExplorePage"}, enabled = false)
  public void testExploreWikisLink() {
    new HomePage().getGlobalNavigation().openWikisMenu().clickExploreWikisLink();

    Assertion.assertEquals(
            urlChecker.getProtocolRelativeURL(driver.getCurrentUrl()),
            urlChecker.getProtocolRelativeURL(
                    fandomUrlBuilder.getFandomUrl(new Configuration().getEnvType()) + "explore")
    );
  }

  @RunOnly(language = "szl")
  @Test(groups = {"exploreWikisLinkClickOnEnCommunityOpensExplorePage"})
  public void testExploreWikisLinkSzl() {
    new HomePage().getGlobalNavigation().openWikisMenu().clickExploreWikisLink();

    Assertion.assertEquals(
            urlChecker.getProtocolRelativeURL(driver.getCurrentUrl()),
            urlChecker.getProtocolRelativeURL(
                    fandomUrlBuilder.getFandomUrl(new Configuration().getEnvType()) + HUBS_SZL)
    );
  }

  @DontRun(language = "szl")
  @Test(groups = {"communityCentralLinkClickOnEnCommunityOpensEnCommunityCentral"})
  public void testCommunityCentralLink() {
    new HomePage().getGlobalNavigation().openWikisMenu().clickCommunityCentralLink();

    Assertion.assertEquals(driver.getCurrentUrl(),
                           UrlBuilder.createUrlBuilderForWikiAndLang(COMMUNITY_WIKI,
                                                                     DEFAULT_LANGUAGE
                           ).getUrlForWikiPage(URLsContent.COMMUNITY_CENTRAL)
    );
  }

  @RunOnly(language = "szl")
  @Test(groups = {"communityCentralLinkClickOnEnCommunityOpensEnCommunityCentral"})
  public void testCommunityCentralLinkSzl() {
    new HomePage().getGlobalNavigation().openWikisMenu().clickCommunityCentralLink();

    Assertion.assertEquals(driver.getCurrentUrl(),
                           UrlBuilder.createUrlBuilderForWikiAndLang(COMMUNITY_WIKI_SZL,
                                                                     DEFAULT_LANGUAGE
                           ).getUrlForWikiPage(URLsContent.COMMUNITY_CENTRAL_SZL)
    );
  }
}
