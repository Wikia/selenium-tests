package com.wikia.webdriver.testcases.mercurytests.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ownership: Content X-Wing
 */
public class AllTagsTests extends NewTestTemplate {

  private static final String ARTICLE_NAME = "AllTagsWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";
  private static List<WidgetPageObject> widgets;

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

    widgets = new ArrayList<>();
    widgets.add(new PollsnackWidgetPageObject(driver));
    widgets.add(new SoundCloudWidgetPageObject(driver));
    widgets.add(new SpotifyWidgetPageObject(driver));
    widgets.add(new TwitterWidgetPageObject(driver));
    widgets.add(new VKWidgetPageObject(driver));
    widgets.add(new WeiboWidgetPageObject(driver));
    widgets.add(new GoogleFormWidgetPageObject(driver));

    String content = "";
    for (WidgetPageObject widget : widgets) {
      content += widget.getTag();
    }

    ArticleContent articleContent = new ArticleContent();
    articleContent.clear(ARTICLE_NAME);
    articleContent.push(content, ARTICLE_NAME);
  }

  @Test(groups = "MercuryAllTagsWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryAllTagsWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    new ArticlePageObject(driver).openMercuryArticleByNameWithCbAndNoAds(wikiURL, ARTICLE_NAME);

    for (WidgetPageObject widget : widgets) {
      Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
    }
  }

  @Test(groups = "MercuryAllTagsWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryAllTagsWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    new ArticlePageObject(driver)
        .openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);
    new NavigationSideComponentObject(driver).navigateToArticle(ARTICLE_NAME);

    for (WidgetPageObject widget : widgets) {
      Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
    }
  }

  @Test(groups = "MercuryAllTagsWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryAllTagsWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    new ArticlePageObject(driver).openMercuryArticleByNameWithCbAndNoAds(wikiURL, ARTICLE_NAME);

    new NavigationSideComponentObject(driver)
        .navigateToArticle(MAPS_ARTICLE_NAME)
        .navigateToArticle(ARTICLE_NAME);

    for (WidgetPageObject widget : widgets) {
      Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
    }
  }
}
