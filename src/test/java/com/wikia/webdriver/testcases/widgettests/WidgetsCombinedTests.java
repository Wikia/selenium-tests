package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PollsnackWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SoundCloudWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SpotifyWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.TwitterWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.VKWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WeiboWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WidgetPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @ownership: Content X-Wing
 */
public class WidgetsCombinedTests extends NewTestTemplate {

  private static final String ARTICLE_NAME = "WidgetsCombined";
  private static final String MAPS_ARTICLE_NAME = "Map";
  private static ArrayList<WidgetPageObject> widgets = new ArrayList<WidgetPageObject>();

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    widgets.add(new PollsnackWidgetPageObject(driver));
    widgets.add(new SoundCloudWidgetPageObject(driver));
    widgets.add(new SpotifyWidgetPageObject(driver));
    widgets.add(new TwitterWidgetPageObject(driver));
    widgets.add(new VKWidgetPageObject(driver));
    widgets.add(new WeiboWidgetPageObject(driver));
  }

  @Test(groups = "MercuryWidgetsCombinedTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryWidgetsCombinedTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    String content = "";
    for (WidgetPageObject widget : widgets) {
      content += widget.getTag();
    }

    ArticleContent articleContent = new ArticleContent();
    articleContent.clear(ARTICLE_NAME);
    articleContent.push(content, ARTICLE_NAME);

    ArticlePageObject article = new ArticlePageObject(driver);
    article.open(ARTICLE_NAME);

    for (WidgetPageObject widget : widgets) {
      Assertion.assertTrue(widget.isLoadedOnOasis(), MercuryMessages.INVISIBLE_MSG);
    }
  }
}
