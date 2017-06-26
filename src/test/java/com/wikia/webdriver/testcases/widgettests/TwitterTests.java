package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.TwitterWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WidgetPageObject;
import org.testng.annotations.Test;

@Test(groups = "TwitterWidget")
@InBrowser(browser = Browser.CHROME)
public class TwitterTests extends NewTestTemplate {

  private static final String TWITTER_ONE_WIDGET_ARTICLE_NAME = "TwitterOasis_OneWidget";
  private static final String TWITTER_MULTIPLE_WIDGETS_ARTICLE_NAME =
      "TwitterOasis_MultipleWidgets";
  private static final String TWITTER_INCORRECT_WIDGET_ARTICLE_NAME =
      "TwitterOasis_IncorrectWidget";

  @Test(groups = "TwitterWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void TwitterWidgetTest_001_isLoaded() {
    WidgetPageObject widget =
            new TwitterWidgetPageObject().create(TWITTER_ONE_WIDGET_ARTICLE_NAME);
    new ArticlePageObject().open(TWITTER_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "TwitterWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void TwitterWidgetTest_002_areLoaded() {
    WidgetPageObject widget =
            new TwitterWidgetPageObject().createMultiple(TWITTER_MULTIPLE_WIDGETS_ARTICLE_NAME);
    new ArticlePageObject().open(TWITTER_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areAllValidSwappedForIFrames(),
        MercuryMessages.SOME_VALID_WIDGETS_WERE_NOT_SWAPPED_MSG);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "TwitterWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void TwitterWidgetTest_003_isErrorPresent() {
    WidgetPageObject widget =
            new TwitterWidgetPageObject().createIncorrect(TWITTER_INCORRECT_WIDGET_ARTICLE_NAME);
    new ArticlePageObject().open(TWITTER_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
