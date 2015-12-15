package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PollsnackWidgetPageObject;

import org.testng.annotations.Test;

@Test(groups = {"PollsnackWidgetTests", "WidgetTests"})
public class PollsnackTests extends NewTestTemplate {

  private static String POLLSNACK_ONE_WIDGET_ARTICLE_NAME = "PollsnackOasis/OneWidget";
  private static String POLLSNACK_MULTIPLE_WIDGETS_ARTICLE_NAME = "PollsnackOasis/MultipleWidgets";
  private static String POLLSNACK_INCORRECT_WIDGET_ARTICLE_NAME = "PollsnackOasis/IncorrectWidget";

  @Test(groups = "PollsnackWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_001_isLoaded() {
    PollsnackWidgetPageObject widget = new PollsnackWidgetPageObject(driver);

    widget
      .create(POLLSNACK_ONE_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL,POLLSNACK_ONE_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "PollsnackWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_002_areLoaded() {
    PollsnackWidgetPageObject widget = new PollsnackWidgetPageObject(driver);

    widget
      .createMultiple(POLLSNACK_MULTIPLE_WIDGETS_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL,POLLSNACK_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "PollsnackWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_003_isErrorPresent() {
    PollsnackWidgetPageObject widget = new PollsnackWidgetPageObject(driver);

    widget
      .createIncorrect(POLLSNACK_INCORRECT_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, POLLSNACK_INCORRECT_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
