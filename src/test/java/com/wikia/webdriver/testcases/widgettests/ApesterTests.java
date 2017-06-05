package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.ApesterWidgetPageObject;

import org.testng.annotations.Test;

@Test(groups = "ApesterWidget")
@InBrowser(browser = Browser.CHROME)
public class ApesterTests extends NewTestTemplate {

  private static final String APESTER_ONE_WIDGET_ARTICLE_NAME = "/wiki/ApesterOasis/OneWidget";
  private static final String APESTER_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME =
      "/wiki/ApesterOasis/MultipleWidgets";
  private static final String APESTER_FORM_INCORRECT_WIDGET_ARTICLE_NAME =
      "/wiki/ApesterOasis/IncorrectWidget";

  private ApesterWidgetPageObject widget;
  private Navigate navigate;

  private void init() {
    this.widget = new ApesterWidgetPageObject();
    this.navigate = new Navigate();
  }

  @Test(groups = "ApesterWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void ApesterWidgetTest_001_isLoaded() {
    init();

    widget.create(APESTER_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage(APESTER_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = {"ApesterWidgetTest_002"})
  @Execute(onWikia = "mercuryautomationtesting")
  public void ApesterWidgetTest_002_areLoaded() {
    init();

    widget.createMultiple(APESTER_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);
    navigate.toPage(APESTER_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "ApesterWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void ApesterKWidgetTest_003_isErrorPresent() {
    init();

    widget.createIncorrect(APESTER_FORM_INCORRECT_WIDGET_ARTICLE_NAME);
    navigate.toPage(APESTER_FORM_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
