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
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SpotifyWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WidgetPageObject;
import org.testng.annotations.Test;
@Test(groups = "Mercury_SpotifyWidget")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.NEXUS_5X
)
public class SpotifyTests extends NewTestTemplate {

  private static final String SPOTIFY_ONE_WIDGET_ARTICLE_NAME = "SpotifyMercury/OneWidget";
  private static final String SPOTIFY_MULTIPLE_WIDGETS_ARTICLE_NAME = "SpotifyMercury/MultipleWidgets";
  private static final String SPOTIFY_INCORRECT_WIDGET_ARTICLE_NAME = "SpotifyMercury/IncorrectWidget";
  private static final String QUERY_1 = MercurySubpages.MAP.substring(6);
  private static final String QUERY_2 = SPOTIFY_ONE_WIDGET_ARTICLE_NAME;

  @Test(groups = "MercurySpotifyWidgetTest_001")
  @Execute(asUser = User.USER)
  public void MercurySpotifyWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    WidgetPageObject widget =
            new SpotifyWidgetPageObject().create(SPOTIFY_ONE_WIDGET_ARTICLE_NAME);

    new Navigate().toPage(SPOTIFY_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_002")
  @Execute(asUser = User.USER)
  public void MercurySpotifyWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    WidgetPageObject widget =
            new SpotifyWidgetPageObject().create(SPOTIFY_ONE_WIDGET_ARTICLE_NAME);

    new Navigate().toPageByPath(MercurySubpages.MAIN_PAGE);
    new TopBar().openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_003")
  @Execute(asUser = User.USER)
  public void MercurySpotifyWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    WidgetPageObject widget =
            new SpotifyWidgetPageObject().create(SPOTIFY_ONE_WIDGET_ARTICLE_NAME);
    new ArticleContent().push("Spotify test 003", "Map");

    new Navigate().toPage(SPOTIFY_ONE_WIDGET_ARTICLE_NAME);
    new TopBar().openSearch().navigateToPage(QUERY_1);
    new TopBar().openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_004")
  @Execute(asUser = User.USER)
  public void MercurySpotifyWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    WidgetPageObject widget =
            new SpotifyWidgetPageObject().createMultiple(SPOTIFY_MULTIPLE_WIDGETS_ARTICLE_NAME);

    new Navigate().toPage(SPOTIFY_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySpotifyWidgetTest_005")
  @Execute(asUser = User.USER)
  public void MercurySpotifyWidgetTest_005_isErrorPresent() {
    WidgetPageObject widget =
            new SpotifyWidgetPageObject().createIncorrect(SPOTIFY_INCORRECT_WIDGET_ARTICLE_NAME);

    new Navigate().toPage(SPOTIFY_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
