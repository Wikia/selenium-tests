package com.wikia.webdriver.testcases.mercurytests.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.TwitterWidgetPageObject;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class TwitterTests extends NewTestTemplate {

  private static String TWITTER_ONE_WIDGET_ARTICLE_NAME = "TwitterMercury/OneWidget";
  private static String TWITTER_MULTIPLE_WIDGETS_ARTICLE_NAME = "TwitterMercury/MultipleWidgets";
  private static String TWITTER_INCORRECT_WIDGET_ARTICLE_NAME = "TwitterMercury/IncorrectWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";

  @Test(groups = "MercuryTwitterWidgetTest_001")
  public void MercuryTwitterWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    TwitterWidgetPageObject widget = new TwitterWidgetPageObject(driver);

    widget
      .create(TWITTER_ONE_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, TWITTER_ONE_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryTwitterWidgetTest_002")
  public void MercuryTwitterWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    TwitterWidgetPageObject widget = new TwitterWidgetPageObject(driver);

    widget
      .create(TWITTER_ONE_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);

    new NavigationSideComponentObject(driver).navigateToArticle(TWITTER_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryTwitterWidgetTest_003")
  public void MercuryTwitterWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    TwitterWidgetPageObject widget = new TwitterWidgetPageObject(driver);

    widget
      .create(TWITTER_ONE_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, TWITTER_ONE_WIDGET_ARTICLE_NAME);

    new NavigationSideComponentObject(driver)
      .navigateToArticle(MAPS_ARTICLE_NAME)
      .navigateToArticle(TWITTER_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryTwitterWidgetTest_004")
  public void MercuryTwitterWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
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

  @Test(groups = "MercuryTwitterWidgetTest_005", enabled = false)
  public void MercuryTwitterWidgetTest_005_isErrorPresent() {
    TwitterWidgetPageObject widget = new TwitterWidgetPageObject(driver);

    widget
      .createIncorrect(TWITTER_INCORRECT_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, TWITTER_INCORRECT_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
