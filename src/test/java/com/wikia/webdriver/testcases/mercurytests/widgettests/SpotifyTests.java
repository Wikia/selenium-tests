package com.wikia.webdriver.testcases.mercurytests.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SpotifyWidgetPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ownership Content X-Wing Wikia
 */
@Test(groups = {"MercurySpotifyWidgetTests", "MercuryWidgetTests", "Mercury"})
public class SpotifyTests extends NewTestTemplate {

  private static String SPOTIFY_ONE_WIDGET_ARTICLE_NAME = "SpotifyMercury/OneWidget";
  private static String SPOTIFY_MULTIPLE_WIDGETS_ARTICLE_NAME = "SpotifyMercury/MultipleWidgets";
  private static String SPOTIFY_INCORRECT_WIDGET_ARTICLE_NAME = "SpotifyMercury/IncorrectWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  //@TODO XW-314 - Login is necessary to bypass cache.

  @Test(groups = "MercurySpotifyWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting", asUser = User.USER)
  public void MercurySpotifyWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget
        .create(SPOTIFY_ONE_WIDGET_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, SPOTIFY_ONE_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting", asUser = User.USER)
  public void MercurySpotifyWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget
        .create(SPOTIFY_ONE_WIDGET_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);

    new NavigationSideComponentObject(driver).navigateToArticle(SPOTIFY_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting", asUser = User.USER)
  public void MercurySpotifyWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget
        .create(SPOTIFY_ONE_WIDGET_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, SPOTIFY_ONE_WIDGET_ARTICLE_NAME);

    new NavigationSideComponentObject(driver)
        .navigateToArticle(MAPS_ARTICLE_NAME)
        .navigateToArticle(SPOTIFY_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_004")
  @Execute(onWikia = "mercuryautomationtesting", asUser = User.USER)
  public void MercurySpotifyWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget
        .createMultiple(SPOTIFY_MULTIPLE_WIDGETS_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, SPOTIFY_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_005")
  @Execute(onWikia = "mercuryautomationtesting", asUser = User.USER)
  public void MercurySpotifyWidgetTest_005_isErrorPresent() {
    SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget
        .createIncorrect(SPOTIFY_INCORRECT_WIDGET_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, SPOTIFY_INCORRECT_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
