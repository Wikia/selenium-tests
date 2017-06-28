package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.ApesterWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WidgetPageObject;
import org.testng.annotations.Test;

@Test(groups = "ApesterWidget")
@InBrowser(browser = Browser.CHROME)
public class ApesterTests extends NewTestTemplate {

  private static final String APESTER_ONE_WIDGET_ARTICLE_NAME = "ApesterOasis_OneWidget";
  private static final String APESTER_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME =
      "ApesterOasis_MultipleWidgets";
  private static final String APESTER_FORM_INCORRECT_WIDGET_ARTICLE_NAME =
      "ApesterOasis_IncorrectWidget";

  @Test(groups = "ApesterWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void ApesterWidgetTest_001_isLoaded() {
    WidgetPageObject widget =
            new ApesterWidgetPageObject().create(APESTER_ONE_WIDGET_ARTICLE_NAME);
    new ArticlePageObject().open(APESTER_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = {"ApesterWidgetTest_002"})
  @Execute(onWikia = "mercuryautomationtesting")
  public void ApesterWidgetTest_002_areLoaded() {
    WidgetPageObject widget =
            new ApesterWidgetPageObject().createMultiple(APESTER_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);
    new ArticlePageObject().open(APESTER_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "ApesterWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void ApesterKWidgetTest_003_isErrorPresent() {
    WidgetPageObject widget =
            new ApesterWidgetPageObject().createIncorrect(APESTER_FORM_INCORRECT_WIDGET_ARTICLE_NAME);
    new ArticlePageObject().open(APESTER_FORM_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
