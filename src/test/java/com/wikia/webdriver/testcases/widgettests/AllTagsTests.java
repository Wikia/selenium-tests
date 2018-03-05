package com.wikia.webdriver.testcases.widgettests;

import java.util.ArrayList;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.ApesterWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.GoogleFormWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PlaybuzzWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PolldaddyWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SoundCloudWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SpotifyWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.TwitterWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.VKWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WeiboWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WidgetPageObject;

@InBrowser(browser = Browser.CHROME)
public class AllTagsTests extends NewTestTemplate {

  private static final String ARTICLE_NAME = "AllTagsWidget";
  private static ArrayList<WidgetPageObject> widgets;

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    widgets = new ArrayList<>();
    widgets.add(new SoundCloudWidgetPageObject());
    widgets.add(new SpotifyWidgetPageObject());
    widgets.add(new TwitterWidgetPageObject());
    widgets.add(new VKWidgetPageObject());
    widgets.add(new WeiboWidgetPageObject());
    widgets.add(new GoogleFormWidgetPageObject());
    widgets.add(new PolldaddyWidgetPageObject());
    widgets.add(new PlaybuzzWidgetPageObject());
    widgets.add(new ApesterWidgetPageObject());

    String content = "";
    for (WidgetPageObject widget : widgets) {
      content += widget.getSingleTag();
    }

    new ArticleContent().push(content, ARTICLE_NAME);
  }

  @Test(groups = "AllTagsWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void AllTagsWidgetTest_001_isLoaded() {
    new ArticlePageObject().open(ARTICLE_NAME);

    for (WidgetPageObject widget : widgets) {
      Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
    }
  }
}
