package com.wikia.webdriver.testcases.desktop.navigation.global;

import static com.wikia.webdriver.common.contentpatterns.URLsContent.COMMUNITY_WIKI;
import static com.wikia.webdriver.common.contentpatterns.URLsContent.COMMUNITY_WIKI_SZL;
import static com.wikia.webdriver.common.contentpatterns.URLsContent.HUBS_SZL;
import static com.wikia.webdriver.common.core.configuration.Configuration.DEFAULT_LANGUAGE;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.RunOnly;
import com.wikia.webdriver.common.core.configuration.EnvType;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;

import org.testng.annotations.Test;

@Test(groups = {"globalnavigationbar", "globalnavigationbarNavigating"})
public class GlobalNavigationNavigating extends NewTestTemplate {

  @DontRun(language = "szl")
  @Test(groups = {"fandomLogoClickOnEnCommunityOpensFandomWikia"})
  public void logoClickOnEnglishCommunityOpensFandom() {
    new HomePage().getGlobalNavigation().clickFandomLogo();

    Assertion.assertEquals(driver.getCurrentUrl(), fandomUrlBuilder.getFandomUrl(EnvType.PROD));

  }

  @DontRun(language = "szl")
  @Test(groups = {"gamesHubLinkClickOnEnCommunityOpensGamesHub"})
  public void testGamesHubLink() {
    new HomePage().getGlobalNavigation().clickGamesHubLink();

    Assertion.assertEquals(driver.getCurrentUrl(),
                           fandomUrlBuilder.getFandomUrl(EnvType.PROD) + "topics/games"
    );
  }

  @RunOnly(language = "szl")
  @Test(groups = {"gamesHubLinkClickOnEnCommunityOpensGamesHub"})
  public void testGamesHubLinkSzl() {
    new HomePage().getGlobalNavigation().clickGamesHubLink();

    Assertion.assertEquals(driver.getCurrentUrl(),
                           fandomUrlBuilder.getFandomUrl(EnvType.PROD) + HUBS_SZL + "#Gry"
    );
  }

  @DontRun(language = "szl")
  @Test(groups = {"moviesHubLinkClickOnEnCommunityOpensMoviesHub"})
  public void testMoviesHubLink() {
    new HomePage().getGlobalNavigation().clickMoviesHubLink();

    Assertion.assertEquals(driver.getCurrentUrl(),
                           fandomUrlBuilder.getFandomUrl(EnvType.PROD) + "topics/movies"
    );
  }

  @RunOnly(language = "szl")
  @Test(groups = {"moviesHubLinkClickOnEnCommunityOpensMoviesHub"})
  public void testMoviesHubLinkSzl() {
    new HomePage().getGlobalNavigation().clickMoviesHubLink();

    Assertion.assertEquals(driver.getCurrentUrl(),
                           fandomUrlBuilder.getFandomUrl(EnvType.PROD) + HUBS_SZL + "#Filmy"
    );
  }

  @DontRun(language = "szl")
  @Test(groups = {"tvHubLinkClickOnEnCommunityOpensTvHub"})
  public void testTVHubLink() {
    new HomePage().getGlobalNavigation().clickTVHubLink();

    Assertion.assertEquals(driver.getCurrentUrl(),
                           fandomUrlBuilder.getFandomUrl(EnvType.PROD) + "topics/tv"
    );
  }

  @RunOnly(language = "szl")
  @Test(groups = {"tvHubLinkClickOnEnCommunityOpensTvHub"})
  public void testTVHubLinkSzl() {
    new HomePage().getGlobalNavigation().clickTVHubLink();

    Assertion.assertEquals(driver.getCurrentUrl(),
                           fandomUrlBuilder.getFandomUrl(EnvType.PROD) + HUBS_SZL + "#TV"
    );
  }

  @DontRun(language = "szl")
  @Test(groups = {"exploreWikisLinkClickOnEnCommunityOpensExplorePage"})
  public void testExploreWikisLink() {
    new HomePage().getGlobalNavigation().openWikisMenu().clickExploreWikisLink();

    Assertion.assertEquals(driver.getCurrentUrl(),
                           fandomUrlBuilder.getFandomUrl(EnvType.PROD) + "explore"
    );
  }

  @RunOnly(language = "szl")
  @Test(groups = {"exploreWikisLinkClickOnEnCommunityOpensExplorePage"})
  public void testExploreWikisLinkSzl() {
    new HomePage().getGlobalNavigation().openWikisMenu().clickExploreWikisLink();

    Assertion.assertEquals(driver.getCurrentUrl(),
                           fandomUrlBuilder.getFandomUrl(EnvType.PROD) + HUBS_SZL
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
