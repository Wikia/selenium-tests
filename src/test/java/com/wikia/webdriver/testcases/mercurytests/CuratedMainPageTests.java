package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.CuratedPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ownership Content X-Wing
 */
public class CuratedMainPageTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  private static final String ROOT_PATH = "/";
  private static final String SECTION_PATH = "/main/section/";
  private static final String CATEGORY_PATH = "/main/category/";

  private static final String[]
      ELEMENTS_ORDER =
      {".mobile-top-leaderboard", ".article-title", ".wikia-stats-container", ".featured-content",
       ".curated-content", ".mobile-in-content", ".trending-articles", ".trending-videos",
       ".mobile-prefooter"};


  // CCT01
  @Test(groups = {"MercuryCuratedMainPageTests_001", "MercuryCuratedMainPageTests", "Mercury"})
  public void MercuryCuratedMainPageTests_001_Description() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.CC_MAIN_PAGE);
    CuratedPageObject cc = new CuratedPageObject(driver);
    PageObjectLogging
        .log("Current URL", "is set on " + ROOT_PATH, "is not set on " + ROOT_PATH,
             cc.isUrlPathEqualTo(ROOT_PATH));
    PageObjectLogging
        .log("AD Mobile Top Leaderboard", "is visible", "is not visible",
             cc.isMobileTopLeaderboardVisible());
    PageObjectLogging
        .log("Article Title", "is visible", "is not visible", cc.isArticleTitleVisible());
    PageObjectLogging
        .log("Wikia Stats Container", "is visible", "is not visible",
             cc.isWikiaStatsContainerVisible());
    PageObjectLogging
        .log("Featured Content", "is visible", "is not visible", cc.isFeaturedContentVisible());
    PageObjectLogging
        .log("Curated Content", "is visible", "is not visible", cc.isCuratedContentVisible());
    PageObjectLogging
        .log("AD Mobile In Content", "is visible", "is not visible", cc.isMobileInContentVisible());
    PageObjectLogging
        .log("Trending Articles", "is visible", "is not visible", cc.isTrendingArticlesVisible());
    PageObjectLogging
        .log("Trending Videos", "is visible", "is not visible", cc.isTrendingVideosVisible());
    PageObjectLogging
        .log("AD Mobile Prefooter", "is visible", "is not visible", cc.isMobilePrefooterVisible());

    int lastPosition = 0;
    String lastElement = "top";

    for (String element : ELEMENTS_ORDER) {
      int newPosition = cc.getElementHeight(element);
      PageObjectLogging
          .log("Element " + element, "is after " + lastElement, "is not after " + lastElement,
               lastPosition <= newPosition);
      lastPosition = newPosition;
      lastElement = element;
    }
  }
}
