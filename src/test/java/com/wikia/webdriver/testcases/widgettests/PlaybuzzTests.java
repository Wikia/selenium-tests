package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PlaybuzzWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WidgetPageObject;
import org.testng.annotations.Test;

@Test(groups = "PlaybuzzWidget")
@InBrowser(browser = Browser.CHROME)
public class PlaybuzzTests extends NewTestTemplate {

  private static final String PLAYBUZZ_ONE_WIDGET_ARTICLE_NAME = "PlaybuzzOasis_OneWidget";
  private static final String PLAYBUZZ_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME =
      "PlaybuzzOasis_MultipleWidgets";
  private static final String PLAYBUZZ_FORM_INCORRECT_WIDGET_ARTICLE_NAME =
      "PlaybuzzOasis_IncorrectWidget";

  @Test(groups = "PlaybuzzWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PlaybuzzWidgetTest_001_isLoaded() {
    WidgetPageObject widget =
            new PlaybuzzWidgetPageObject().create(PLAYBUZZ_ONE_WIDGET_ARTICLE_NAME);
    new ArticlePageObject().open(PLAYBUZZ_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = {"PlaybuzzWidgetTest_002"})
  @Execute(onWikia = "mercuryautomationtesting")
  public void PlaybuzzWidgetTest_002_areLoaded() {
    WidgetPageObject widget =
            new PlaybuzzWidgetPageObject().createMultiple(PLAYBUZZ_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);
    new ArticlePageObject().open(PLAYBUZZ_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "PlaybuzzWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PlaybuzzKWidgetTest_003_isErrorPresent() {
    WidgetPageObject widget =
            new PlaybuzzWidgetPageObject().createIncorrect(PLAYBUZZ_FORM_INCORRECT_WIDGET_ARTICLE_NAME);
    new ArticlePageObject().open(PLAYBUZZ_FORM_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
