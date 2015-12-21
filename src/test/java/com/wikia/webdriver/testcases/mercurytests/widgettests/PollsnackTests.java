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
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PollsnackWidgetPageObject;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class PollsnackTests extends NewTestTemplate {

  private static String POLLSNACK_ONE_WIDGET_ARTICLE_NAME = "PollsnackMercury/OneWidget";
  private static String
      POLLSNACK_MULTIPLE_WIDGETS_ARTICLE_NAME =
      "PollsnackMercury/MultipleWidgets";
  private static String
      POLLSNACK_INCORRECT_WIDGET_ARTICLE_NAME =
      "PollsnackMercury/IncorrectWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";

  @Test(groups = "MercuryPollsnackWidgetTest_001")
  public void MercuryPollsnackWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    PollsnackWidgetPageObject widget = new PollsnackWidgetPageObject(driver);

    widget
        .create(POLLSNACK_ONE_WIDGET_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, POLLSNACK_ONE_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_002")
  public void MercuryPollsnackWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    PollsnackWidgetPageObject widget = new PollsnackWidgetPageObject(driver);

    widget
        .create(POLLSNACK_ONE_WIDGET_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);

    new NavigationSideComponentObject(driver).navigateToArticle(POLLSNACK_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_003")
  public void MercuryPollsnackWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    PollsnackWidgetPageObject widget = new PollsnackWidgetPageObject(driver);

    widget
        .create(POLLSNACK_ONE_WIDGET_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, POLLSNACK_ONE_WIDGET_ARTICLE_NAME);

    new NavigationSideComponentObject(driver)
        .navigateToArticle(MAPS_ARTICLE_NAME)
        .navigateToArticle(POLLSNACK_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_004")
  public void MercuryPollsnackWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    PollsnackWidgetPageObject widget = new PollsnackWidgetPageObject(driver);

    widget
        .createMultiple(POLLSNACK_MULTIPLE_WIDGETS_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, POLLSNACK_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_005")
  public void MercuryPollsnackWidgetTest_005_isErrorPresent() {
    PollsnackWidgetPageObject widget = new PollsnackWidgetPageObject(driver);

    widget
        .createIncorrect(POLLSNACK_INCORRECT_WIDGET_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, POLLSNACK_INCORRECT_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
