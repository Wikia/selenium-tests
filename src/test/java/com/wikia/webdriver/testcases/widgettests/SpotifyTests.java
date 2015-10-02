package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SpotifyWidgetPageObject;

import org.testng.annotations.Test;

/**
 * @ownership: Content X-Wing
 */
@Test(groups = {"SpotifyWidgetTests", "WidgetTests"})
public class SpotifyTests extends NewTestTemplate {

  private static String SPOTIFY_ONE_WIDGET_ARTICLE_NAME = "SpotifyOasis/OneWidget";
  private static String SPOTIFY_MULTIPLE_WIDGETS_ARTICLE_NAME = "SpotifyOasis/MultipleWidgets";
  private static String SPOTIFY_INCORRECT_WIDGET_ARTICLE_NAME = "SpotifyOasis/IncorrectWidget";

  @Test(groups = "SpotifyWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void SpotifyWidgetTest_001_isLoaded() {
    SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget
      .create(SPOTIFY_ONE_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, SPOTIFY_ONE_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "SpotifyWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void SpotifyWidgetTest_002_areLoaded() {
    SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget
      .createMultiple(SPOTIFY_MULTIPLE_WIDGETS_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, SPOTIFY_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "SpotifyWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void SpotifyWidgetTest_003_isErrorPresent() {
  SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget
      .createIncorrect(SPOTIFY_INCORRECT_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, SPOTIFY_INCORRECT_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
