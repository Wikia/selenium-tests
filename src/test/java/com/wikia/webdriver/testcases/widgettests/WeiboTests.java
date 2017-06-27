package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WeiboWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WidgetPageObject;
import org.testng.annotations.Test;

@Test(groups = "WeiboWidget")
@InBrowser(browser = Browser.CHROME)
public class WeiboTests extends NewTestTemplate {

  private static final String WEIBO_ONE_WIDGET_ARTICLE_NAME = "WeiboOasis_OneWidget";
  private static final String WEIBO_MULTIPLE_WIDGETS_ARTICLE_NAME =
      "WeiboOasis_MultipleWidgets";
  private static final String WEIBO_INCORRECT_WIDGET_ARTICLE_NAME =
      "WeiboOasis_IncorrectWidget";

  @Test(groups = "WeiboWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void WeiboWidgetTest_001_isLoaded() {
    WidgetPageObject widget =
            new WeiboWidgetPageObject().create(WEIBO_ONE_WIDGET_ARTICLE_NAME);
    new ArticlePageObject().open(WEIBO_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "WeiboWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void WeiboWidgetTest_002_areLoaded() {
    WidgetPageObject widget =
            new WeiboWidgetPageObject().createMultiple(WEIBO_MULTIPLE_WIDGETS_ARTICLE_NAME);
    new ArticlePageObject().open(WEIBO_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "WeiboWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void WeiboWidgetTest_003_isErrorPresent() {
    WidgetPageObject widget =
            new WeiboWidgetPageObject().createIncorrect(WEIBO_INCORRECT_WIDGET_ARTICLE_NAME);
    new ArticlePageObject().open(WEIBO_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
