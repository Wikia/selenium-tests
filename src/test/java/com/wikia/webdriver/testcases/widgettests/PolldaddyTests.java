package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PolldaddyWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WidgetPageObject;
import org.testng.annotations.Test;

@Test(groups = "PolldaddyWidget")
@InBrowser(browser = Browser.CHROME)
public class PolldaddyTests extends NewTestTemplate {

  private static final String POLLDADDY_ONE_WIDGET_ARTICLE_NAME = "PollDaddyOasis_OneWidget";
  private static final String POLLDADDY_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME =
      "PollDaddyOasis_MultipleWidgets";
  private static final String POLLDADDY_FORM_INCORRECT_WIDGET_ARTICLE_NAME =
      "PollDaddyOasis_IncorrectWidget";

  @Test(groups = "PolldaddyWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PolldaddyWidgetTest_001_isLoaded() {
    WidgetPageObject widget =
            new PolldaddyWidgetPageObject().create(POLLDADDY_ONE_WIDGET_ARTICLE_NAME);
    new ArticlePageObject().open(POLLDADDY_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = {"PolldaddyWidgetTest_002"})
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_002_areLoaded() {
    WidgetPageObject widget =
            new PolldaddyWidgetPageObject().createMultiple(POLLDADDY_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);
    new ArticlePageObject().open(POLLDADDY_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "PolldaddyWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PolldaddyKWidgetTest_003_isErrorPresent() {
    WidgetPageObject widget =
            new PolldaddyWidgetPageObject().createIncorrect(POLLDADDY_FORM_INCORRECT_WIDGET_ARTICLE_NAME);
    new ArticlePageObject().open(POLLDADDY_FORM_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
