package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.TwitterWidgetPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ownership: Content X-Wing
 */
// Uncoment after finish all widget tags
//@Test(groups = {"MercuryWidgetTests", "Mercury"})
public class WidgetTests extends NewTestTemplate {

  private static final String TWITTER_ARTICLE_NAME = "TwitterWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  @Test(groups = "MercuryWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryWidgetTest_001_Twitter_isLoadedOnFirstVisitDirectlyFromUrl() {
    TwitterWidgetPageObject twitterWidgetPageObject = new TwitterWidgetPageObject(driver);

    twitterWidgetPageObject.createAndNavigate(wikiURL);
    Assertion
        .assertTrue(twitterWidgetPageObject.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryWidgetTest_002_Twitter_isLoadedOnFirstVisitFromDifferentArticle() {
    TwitterWidgetPageObject twitterWidgetPageObject = new TwitterWidgetPageObject(driver);

    twitterWidgetPageObject
        .create()
        .openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);
    new NavigationSideComponentObject(driver).navigateToArticle(TWITTER_ARTICLE_NAME);

    Assertion
        .assertTrue(twitterWidgetPageObject.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryWidgetTest_003_Twitter_isLoadedOnSecondVisitFromDifferentArticle() {
    TwitterWidgetPageObject twitterWidgetPageObject = new TwitterWidgetPageObject(driver);

    twitterWidgetPageObject.createAndNavigate(wikiURL);

    new NavigationSideComponentObject(driver)
        .navigateToArticle(MAPS_ARTICLE_NAME)
        .navigateToArticle(TWITTER_ARTICLE_NAME);

    Assertion
        .assertTrue(twitterWidgetPageObject.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }
}
