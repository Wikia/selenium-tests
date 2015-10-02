package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WeiboWidgetPageObject;

import org.testng.annotations.Test;

/**
 * @ownership: Content X-Wing
 */
@Test(groups = {"WeiboWidgetTests", "WidgetTests"})
public class WeiboTests extends NewTestTemplate {

  private static String WEIBO_ONE_WIDGET_ARTICLE_NAME = "WeiboOasis/OneWidget";
  private static String WEIBO_MULTIPLE_WIDGETS_ARTICLE_NAME = "WeiboOasis/MultipleWidgets";
  private static String WEIBO_INCORRECT_WIDGET_ARTICLE_NAME = "WeiboOasis/IncorrectWidget";

  @Test(groups = "WeiboWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void WeiboWidgetTest_001_isLoaded() {
    WeiboWidgetPageObject widget = new WeiboWidgetPageObject(driver);

    widget
      .create(WEIBO_ONE_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, WEIBO_ONE_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "WeiboWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void WeiboWidgetTest_002_areLoaded() {
    WeiboWidgetPageObject widget = new WeiboWidgetPageObject(driver);

    widget
      .createMultiple(WEIBO_MULTIPLE_WIDGETS_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, WEIBO_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG
    );
  }

  @Test(groups = "WeiboWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void WeiboKWidgetTest_003_isErrorPresent() {
    WeiboWidgetPageObject widget = new WeiboWidgetPageObject(driver);

    widget
      .createIncorrect(WEIBO_INCORRECT_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, WEIBO_INCORRECT_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
