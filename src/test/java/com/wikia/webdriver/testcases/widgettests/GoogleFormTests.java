package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.GoogleFormWidgetPageObject;

import org.testng.annotations.Test;
@Test(groups = "GoogleFormWidget")
@InBrowser(browser = Browser.CHROME)
public class GoogleFormTests extends NewTestTemplate {

  private static final String GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME = "/wiki/GoogleFormOasis/OneWidget";
  private static final String GOOGLE_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME = "/wiki/GoogleFormOasis/MultipleWidgets";
  private static final String GOOGLE_FORM_INCORRECT_WIDGET_ARTICLE_NAME = "/wiki/GoogleFormOasis/IncorrectWidget";

  private GoogleFormWidgetPageObject widget;
  private Navigate navigate;

  private void init() {
    this.widget = new GoogleFormWidgetPageObject(driver);
    this.navigate = new Navigate();
  }

  @Test(groups = "GoogleFormWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void GoogleFormWidgetTest_001_isLoaded() {
    init();

    widget.create(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "GoogleFormWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_002_areLoaded() {
    init();

    widget.createMultiple(GOOGLE_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);
    navigate.toPage(GOOGLE_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "GoogleFormWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void GoogleFormWidgetTest_003_isErrorPresent() {
    init();

    widget.createIncorrect(GOOGLE_FORM_INCORRECT_WIDGET_ARTICLE_NAME);
    navigate.toPage(GOOGLE_FORM_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
