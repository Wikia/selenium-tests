package com.wikia.webdriver.testcases.mercurytests.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.LoginPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SpotifyWidgetPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ownership: Content X-Wing
 */
public class SpotifyTests extends NewTestTemplate {

  private static final String SPOTIFY_ARTICLE_NAME = "SpotifyWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

    //@TODO XW-314 - Login is necessary to bypass cache
    new LoginPageObject(driver).get().logUserIn(Configuration.getCredentials().userNameStaff2,
                                                Configuration.getCredentials().passwordStaff2);
  }

  @Test(groups = "MercurySpotifyWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercurySpotifyWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    SpotifyWidgetPageObject spotifyWidget = new SpotifyWidgetPageObject(driver);

    spotifyWidget.createAndNavigate(wikiURL);
    Assertion.assertTrue(spotifyWidget.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercurySpotifyWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    SpotifyWidgetPageObject spotifyWidget = new SpotifyWidgetPageObject(driver);

    spotifyWidget
        .create()
        .openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);
    new NavigationSideComponentObject(driver).navigateToArticle(SPOTIFY_ARTICLE_NAME);

    Assertion.assertTrue(spotifyWidget.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercurySpotifyWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    SpotifyWidgetPageObject spotifyWidget = new SpotifyWidgetPageObject(driver);

    spotifyWidget.createAndNavigate(wikiURL);

    new NavigationSideComponentObject(driver)
        .navigateToArticle(MAPS_ARTICLE_NAME)
        .navigateToArticle(SPOTIFY_ARTICLE_NAME);

    Assertion.assertTrue(spotifyWidget.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_004")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercurySpotifyWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    SpotifyWidgetPageObject spotifyWidget = new SpotifyWidgetPageObject(driver);

    spotifyWidget.create(2).navigate(wikiURL);

    Assertion.assertTrue(spotifyWidget.areLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_005")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercurySpotifyWidgetTest_005_isErrorPresent() {
    SpotifyWidgetPageObject spotifyWidget = new SpotifyWidgetPageObject(driver);

    spotifyWidget.createIncorrectAndNavigate(wikiURL);
    Assertion.assertTrue(spotifyWidget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
