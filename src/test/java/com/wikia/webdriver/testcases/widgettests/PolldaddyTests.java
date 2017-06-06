package com.wikia.webdriver.testcases.widgettests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PolldaddyWidgetPageObject;

@Test(groups = "PolldaddyWidget")
@InBrowser(browser = Browser.CHROME)
public class PolldaddyTests extends NewTestTemplate {

  private static final String POLLDADDY_ONE_WIDGET_ARTICLE_NAME = "/wiki/PollDaddyOasis/OneWidget";
  private static final String POLLDADDY_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME =
      "/wiki/PollDaddyOasis/MultipleWidgets";
  private static final String POLLDADDY_FORM_INCORRECT_WIDGET_ARTICLE_NAME =
      "/wiki/PollDaddyOasis/IncorrectWidget";

  private PolldaddyWidgetPageObject widget;
  private Navigate navigate;

  private void init() {
    this.widget = new PolldaddyWidgetPageObject();
    this.navigate = new Navigate();
  }

  @Test(groups = "PolldaddyWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PolldaddyWidgetTest_001_isLoaded() {
    init();

    widget.create(POLLDADDY_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage(POLLDADDY_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = {"PolldaddyWidgetTest_002"})
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_002_areLoaded() {
    init();

    widget.createMultiple(POLLDADDY_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);
    navigate.toPage(POLLDADDY_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "PolldaddyWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PolldaddyKWidgetTest_003_isErrorPresent() {
    init();

    widget.createIncorrect(POLLDADDY_FORM_INCORRECT_WIDGET_ARTICLE_NAME);
    navigate.toPage(POLLDADDY_FORM_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
