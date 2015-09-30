package com.wikia.webdriver.testcases.mercurytests.widgettests;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.LoginPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SpotifyWidgetPageObject;

/**
 * @ownership: Content X-Wing
 */
@Test(groups = {"MercurySpotifyWidgetTests", "MercuryWidgetTests", "Mercury"})
public class SpotifyTests extends NewTestTemplate {

  private static final String SPOTIFY_ARTICLE_NAME = "SpotifyWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

    // @TODO XW-314 - Login is necessary to bypass cache
    new LoginPageObject(driver).get().logUserIn(Configuration.getCredentials().userNameStaff2,
        Configuration.getCredentials().passwordStaff2);
  }

  @Test(groups = "MercurySpotifyWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercurySpotifyWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget.create().navigate(wikiURL);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercurySpotifyWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget.create().openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);
    new NavigationSideComponentObject(driver).navigateToArticle(SPOTIFY_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercurySpotifyWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget.create().navigate(wikiURL);

    new NavigationSideComponentObject(driver).navigateToArticle(MAPS_ARTICLE_NAME)
        .navigateToArticle(SPOTIFY_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_004", enabled = false)
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercurySpotifyWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget.createMultiple().navigate(wikiURL);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_005", enabled = false)
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercurySpotifyWidgetTest_005_isErrorPresent() {
    SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget.createIncorrect().navigate(wikiURL);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
