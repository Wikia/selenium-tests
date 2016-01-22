package com.wikia.webdriver.testcases.mercurytests.old.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.Navigation;
import com.wikia.webdriver.elements.mercury.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SpotifyWidgetPageObject;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class SpotifyTests extends NewTestTemplate {

  private TopBar topBar;
  private Navigation navigation;

  private static final String SPOTIFY_ONE_WIDGET_ARTICLE_NAME = "SpotifyMercury/OneWidget";
  private static final String SPOTIFY_MULTIPLE_WIDGETS_ARTICLE_NAME = "SpotifyMercury/MultipleWidgets";
  private static final String SPOTIFY_INCORRECT_WIDGET_ARTICLE_NAME = "SpotifyMercury/IncorrectWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";

  public void init() {
    this.topBar = new TopBar(driver);
    this.navigation = new Navigation(driver);
  }

  @Test(groups = "MercurySpotifyWidgetTest_001")
  @Execute(asUser = User.USER)
  public void MercurySpotifyWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget
        .create(SPOTIFY_ONE_WIDGET_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, SPOTIFY_ONE_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_002")
  @Execute(asUser = User.USER)
  public void MercurySpotifyWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    init();
    SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget
        .create(SPOTIFY_ONE_WIDGET_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);

    topBar.openNavigation();
    navigation.navigateToPage(SPOTIFY_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_003")
  @Execute(asUser = User.USER)
  public void MercurySpotifyWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    init();
    SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget
        .create(SPOTIFY_ONE_WIDGET_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, SPOTIFY_ONE_WIDGET_ARTICLE_NAME);

    topBar.openNavigation();
    navigation.navigateToPage(MAPS_ARTICLE_NAME);
    topBar.openNavigation();
    navigation.navigateToPage(SPOTIFY_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_004")
  @Execute(asUser = User.USER)
  public void MercurySpotifyWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget
        .createMultiple(SPOTIFY_MULTIPLE_WIDGETS_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, SPOTIFY_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_005")
  @Execute(asUser = User.USER)
  public void MercurySpotifyWidgetTest_005_isErrorPresent() {
    SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget
        .createIncorrect(SPOTIFY_INCORRECT_WIDGET_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, SPOTIFY_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
