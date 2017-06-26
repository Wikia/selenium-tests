package com.wikia.webdriver.testcases.widgettests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WeiboWidgetPageObject;

@Test(groups = "WeiboWidget")
@InBrowser(browser = Browser.CHROME)
public class WeiboTests extends NewTestTemplate {

  private static final String WEIBO_ONE_WIDGET_ARTICLE_NAME = "/wiki/WeiboOasis/OneWidget";
  private static final String WEIBO_MULTIPLE_WIDGETS_ARTICLE_NAME =
      "/wiki/WeiboOasis/MultipleWidgets";
  private static final String WEIBO_INCORRECT_WIDGET_ARTICLE_NAME =
      "/wiki/WeiboOasis/IncorrectWidget";

  private WeiboWidgetPageObject widget;
  private Navigate navigate;

  private void init() {
    this.widget = new WeiboWidgetPageObject();
    this.navigate = new Navigate();
  }

  @Test(groups = "WeiboWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void WeiboWidgetTest_001_isLoaded() {
    init();

    widget.create(WEIBO_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPageByPath(WEIBO_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "WeiboWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void WeiboWidgetTest_002_areLoaded() {
    init();

    widget.createMultiple(WEIBO_MULTIPLE_WIDGETS_ARTICLE_NAME);
    navigate.toPageByPath(WEIBO_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "WeiboWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void WeiboWidgetTest_003_isErrorPresent() {
    init();

    widget.createIncorrect(WEIBO_INCORRECT_WIDGET_ARTICLE_NAME);
    navigate.toPageByPath(WEIBO_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
