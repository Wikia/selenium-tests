package com.wikia.webdriver.testcases.widgettests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PollsnackWidgetPageObject;

@InBrowser(browser = Browser.CHROME)
public class PollsnackTests extends NewTestTemplate {

  private static final String POLLSNACK_ONE_WIDGET_ARTICLE_NAME = "/wiki/PollsnackOasis/OneWidget";
  private static final String POLLSNACK_MULTIPLE_WIDGETS_ARTICLE_NAME =
      "/wiki/PollsnackOasis/MultipleWidgets";
  private static final String POLLSNACK_INCORRECT_WIDGET_ARTICLE_NAME =
      "/wiki/PollsnackOasis/IncorrectWidget";

  private PollsnackWidgetPageObject widget;
  private Navigate navigate;

  private void init() {
    this.widget = new PollsnackWidgetPageObject(driver);
    this.navigate = new Navigate();
  }

  @Test(groups = "PollsnackWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_001_isLoaded() {
    init();

    widget.create(POLLSNACK_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage(POLLSNACK_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "PollsnackWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_002_areLoaded() {
    init();

    widget.createMultiple(POLLSNACK_MULTIPLE_WIDGETS_ARTICLE_NAME);
    navigate.toPage(POLLSNACK_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "PollsnackWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_003_isErrorPresent() {
    init();

    widget.createIncorrect(POLLSNACK_INCORRECT_WIDGET_ARTICLE_NAME);
    navigate.toPage(POLLSNACK_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
