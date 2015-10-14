package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PolldaddyWidgetPageObject;

import org.testng.annotations.Test;

/**
 * @ownership Content X-Wing Wikia
 */
@Test(groups = {"PolldaddyWidgetTests", "WidgetTests"})
public class PolldaddyTests extends NewTestTemplate {

  private static String POLLDADDY_ONE_WIDGET_ARTICLE_NAME = "PollDaddyOasis/OneWidget";
  private static String POLLDADDY_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME = "PollDaddyOasis/MultipleWidgets";
  private static String POLLDADDY_FORM_INCORRECT_WIDGET_ARTICLE_NAME = "PollDaddyOasis/IncorrectWidget";

  @Test(groups = "PolldaddyWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PolldaddyWidgetTest_001_isLoaded() {
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget
      .create(POLLDADDY_ONE_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, POLLDADDY_ONE_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = {"PolldaddyWidgetTest_002"})
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_002_areLoaded() {
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget
      .createMultiple(POLLDADDY_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, POLLDADDY_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "PolldaddyWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PolldaddyKWidgetTest_003_isErrorPresent() {
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget
      .createIncorrect(POLLDADDY_FORM_INCORRECT_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, POLLDADDY_FORM_INCORRECT_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
