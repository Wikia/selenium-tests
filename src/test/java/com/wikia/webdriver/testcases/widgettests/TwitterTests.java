package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.TwitterWidgetPageObject;

import org.testng.annotations.Test;

/**
 * @ownership Content X-Wing Wikia
 */
@Test(groups = {"TwitterWidgetTests", "WidgetTests"})
public class TwitterTests extends NewTestTemplate {

  private static String TWITTER_ONE_WIDGET_ARTICLE_NAME = "TwitterOasis/OneWidget";
  private static String TWITTER_MULTIPLE_WIDGETS_ARTICLE_NAME = "TwitterOasis/MultipleWidgets";
  private static String TWITTER_INCORRECT_WIDGET_ARTICLE_NAME = "TwitterOasis/IncorrectWidget";

  @Test(groups = "TwitterWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void TwitterWidgetTest_001_isLoaded() {
    TwitterWidgetPageObject widget = new TwitterWidgetPageObject(driver);

    widget
      .create(TWITTER_ONE_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, TWITTER_ONE_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "TwitterWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void TwitterWidgetTest_002_areLoaded() {
    TwitterWidgetPageObject widget = new TwitterWidgetPageObject(driver);

    widget
      .createMultiple(TWITTER_MULTIPLE_WIDGETS_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, TWITTER_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(
        widget.areAllValidSwappedForIFrames(),
        MercuryMessages.SOME_VALID_WIDGETS_WERE_NOT_SWAPPED_MSG
    );

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "TwitterWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void TwitterWidgetTest_003_isErrorPresent() {
    TwitterWidgetPageObject widget = new TwitterWidgetPageObject(driver);

    widget
      .createIncorrect(TWITTER_INCORRECT_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, TWITTER_INCORRECT_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
