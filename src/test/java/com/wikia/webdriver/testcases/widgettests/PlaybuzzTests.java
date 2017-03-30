package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PlaybuzzWidgetPageObject;

import org.testng.annotations.Test;

@Test(groups = "PlaybuzzWidget")
@InBrowser(browser = Browser.CHROME)
public class PlaybuzzTests extends NewTestTemplate {
  private static final String PLAYBUZZ_ONE_WIDGET_ARTICLE_NAME = "/wiki/PlaybuzzOasis/OneWidget";
  private static final String PLAYBUZZ_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME =
      "/wiki/PlaybuzzOasis/MultipleWidgets";
  private static final String PLAYBUZZ_FORM_INCORRECT_WIDGET_ARTICLE_NAME =
      "/wiki/PlaybuzzOasis/IncorrectWidget";

  private PlaybuzzWidgetPageObject widget;
  private Navigate navigate;

  private void init() {
    this.widget = new PlaybuzzWidgetPageObject(driver);
    this.navigate = new Navigate();
  }

  @Test(groups = "PlaybuzzWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PlaybuzzWidgetTest_001_isLoaded() {
    init();

    widget.create(PLAYBUZZ_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage(PLAYBUZZ_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = {"PlaybuzzWidgetTest_002"})
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_002_areLoaded() {
    init();

    widget.createMultiple(PLAYBUZZ_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);
    navigate.toPage(PLAYBUZZ_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "PlaybuzzWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PlaybuzzKWidgetTest_003_isErrorPresent() {
    init();

    widget.createIncorrect(PLAYBUZZ_FORM_INCORRECT_WIDGET_ARTICLE_NAME);
    navigate.toPage(PLAYBUZZ_FORM_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
