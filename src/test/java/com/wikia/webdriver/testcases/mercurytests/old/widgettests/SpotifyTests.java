package com.wikia.webdriver.testcases.mercurytests.old.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SpotifyWidgetPageObject;

import org.testng.annotations.Test;
@Test(groups = "Mercury_SpotifyWidget")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class SpotifyTests extends NewTestTemplate {

  private TopBar topBar;
  private Navigation navigation;
  private Navigate navigate;
  private SpotifyWidgetPageObject widget;

  private static final String SPOTIFY_ONE_WIDGET_ARTICLE_NAME = "/SpotifyMercury/OneWidget";
  private static final String SPOTIFY_MULTIPLE_WIDGETS_ARTICLE_NAME = "/SpotifyMercury/MultipleWidgets";
  private static final String SPOTIFY_INCORRECT_WIDGET_ARTICLE_NAME = "/SpotifyMercury/IncorrectWidget";
  private static final String QUERY_1 = MercurySubpages.MAP.substring(6);
  private static final String QUERY_2 = SPOTIFY_ONE_WIDGET_ARTICLE_NAME;
  private static final String VALID_SPOTIFY_TAG = "<spotify uri=\"spotify:track:5JunxkcjfCYcY7xJ29tLai\" />\n";
  private static final String INVALID_SPOTIFY_TAG = "<spotify />";

  private void init() {
    this.topBar = new TopBar();
    this.navigation = new Navigation(driver);
    this.navigate = new Navigate();
    this.widget = new SpotifyWidgetPageObject();
  }

  @Test(groups = "MercurySpotifyWidgetTest_001")
  @Execute(asUser = User.USER)
  public void MercurySpotifyWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    new ArticleContent().push(VALID_SPOTIFY_TAG, SPOTIFY_ONE_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage(SPOTIFY_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_002")
  @Execute(asUser = User.USER)
  public void MercurySpotifyWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    new ArticleContent().push(VALID_SPOTIFY_TAG, SPOTIFY_ONE_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage(MercurySubpages.MAIN_PAGE);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_003")
  @Execute(asUser = User.USER)
  public void MercurySpotifyWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    new ArticleContent().push(VALID_SPOTIFY_TAG, SPOTIFY_ONE_WIDGET_ARTICLE_NAME);
    new ArticleContent().push("Spotify test 003", "Map");
    init();

    navigate.toPage(SPOTIFY_ONE_WIDGET_ARTICLE_NAME);
    topBar.openSearch().navigateToPage(QUERY_1);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_004")
  @Execute(asUser = User.USER)
  public void MercurySpotifyWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    new ArticleContent().push(VALID_SPOTIFY_TAG + " " + VALID_SPOTIFY_TAG,
            SPOTIFY_MULTIPLE_WIDGETS_ARTICLE_NAME);
    init();

    navigate.toPage(SPOTIFY_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_005")
  @Execute(asUser = User.USER)
  public void MercurySpotifyWidgetTest_005_isErrorPresent() {
    new ArticleContent().push(INVALID_SPOTIFY_TAG, SPOTIFY_INCORRECT_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage(SPOTIFY_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
