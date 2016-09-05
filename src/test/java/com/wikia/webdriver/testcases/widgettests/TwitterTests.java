package com.wikia.webdriver.testcases.widgettests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.TwitterWidgetPageObject;

@Test(groups = "TwitterWidget")
@InBrowser(browser = Browser.CHROME)
public class TwitterTests extends NewTestTemplate {

  private static final String TWITTER_ONE_WIDGET_ARTICLE_NAME = "/wiki/TwitterOasis/OneWidget";
  private static final String TWITTER_MULTIPLE_WIDGETS_ARTICLE_NAME =
      "/wiki/TwitterOasis/MultipleWidgets";
  private static final String TWITTER_INCORRECT_WIDGET_ARTICLE_NAME =
      "/wiki/TwitterOasis/IncorrectWidget";

  private TwitterWidgetPageObject widget;
  private Navigate navigate;

  private void init() {
    this.widget = new TwitterWidgetPageObject(driver);
    this.navigate = new Navigate();
  }

  @Test(groups = "TwitterWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void TwitterWidgetTest_001_isLoaded() {
    init();

    widget.create(TWITTER_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage(TWITTER_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "TwitterWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void TwitterWidgetTest_002_areLoaded() {
    init();

    widget.createMultiple(TWITTER_MULTIPLE_WIDGETS_ARTICLE_NAME);
    navigate.toPage(TWITTER_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areAllValidSwappedForIFrames(),
        MercuryMessages.SOME_VALID_WIDGETS_WERE_NOT_SWAPPED_MSG);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "TwitterWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void TwitterWidgetTest_003_isErrorPresent() {
    init();

    widget.createIncorrect(TWITTER_INCORRECT_WIDGET_ARTICLE_NAME);
    navigate.toPage(TWITTER_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
