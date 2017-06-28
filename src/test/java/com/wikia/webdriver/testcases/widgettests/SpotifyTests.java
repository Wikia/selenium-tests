package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SpotifyWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WidgetPageObject;
import org.testng.annotations.Test;

@Test(groups = "SpotifyWidget")
@InBrowser(browser = Browser.CHROME)
public class SpotifyTests extends NewTestTemplate {

  private static final String SPOTIFY_ONE_WIDGET_ARTICLE_NAME = "SpotifyOasis_OneWidget";
  private static final String SPOTIFY_MULTIPLE_WIDGETS_ARTICLE_NAME =
      "SpotifyOasis_MultipleWidgets";
  private static final String SPOTIFY_INCORRECT_WIDGET_ARTICLE_NAME =
      "SpotifyOasis_IncorrectWidget";

  @Test(groups = "SpotifyWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void SpotifyWidgetTest_001_isLoaded() {
    WidgetPageObject widget =
            new SpotifyWidgetPageObject().create(SPOTIFY_ONE_WIDGET_ARTICLE_NAME);
    new ArticlePageObject().open(SPOTIFY_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "SpotifyWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void SpotifyWidgetTest_002_areLoaded() {
    WidgetPageObject widget =
            new SpotifyWidgetPageObject().createMultiple(SPOTIFY_MULTIPLE_WIDGETS_ARTICLE_NAME);
    new ArticlePageObject().open(SPOTIFY_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "SpotifyWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void SpotifyWidgetTest_003_isErrorPresent() {
    WidgetPageObject widget =
            new SpotifyWidgetPageObject().createIncorrect(SPOTIFY_INCORRECT_WIDGET_ARTICLE_NAME);
    new ArticlePageObject().open(SPOTIFY_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
