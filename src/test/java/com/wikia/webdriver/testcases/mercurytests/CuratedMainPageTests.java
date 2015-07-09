package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
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

  private static final String VISIBLE = "AD Mobile Top Leaderboard";
  private static final String INVISIBLE = "Article Title";

  private static final String LEADERBOARD = "AD Mobile Top Leaderboard";
  private static final String TITLE = "Article Title";
  private static final String STATS = "Wikia Stats Container";
  private static final String FEATURED_CONTENT = "Featured Content";
  private static final String CURATED_CONTENT = "Curated Content";
  private static final String MIDDLE_AD = "AD Mobile In Content";
  private static final String TRENDING_ARTICLES = "Trending Articles";
  private static final String TRENDING_VIDEOS = "Trending Videos";
  private static final String PREFOOTER_AD = "AD Mobile Prefooter";

  private static final String[]
      ELEMENTS_ORDER =
      {".mobile-top-leaderboard", ".article-title", ".wikia-stats-container", ".featured-content",
       ".curated-content", ".mobile-in-content", ".trending-articles", ".trending-videos",
       ".mobile-prefooter"};


  // CCT01
  @Test(groups = {"MercuryCuratedMainPageTests_001", "MercuryCuratedMainPageTests", "Mercury"})
  public void MercuryCuratedMainPageTests_001_CheckElementsVisibilityElementsOrderAndRootPath() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.CC_MAIN_PAGE);
    CuratedPageObject cc = new CuratedPageObject(driver);
    PageObjectLogging
        .log("Current URL", "is set on " + ROOT_PATH, "is not set on " + ROOT_PATH,
             cc.isUrlPathEqualTo(ROOT_PATH));
    PageObjectLogging
        .log(LEADERBOARD, MercuryMessages.VISIBLE_MSG, MercuryMessages.INVISIBLE_MSG,
             cc.isMobileTopLeaderboardVisible());
    PageObjectLogging
        .log(TITLE, MercuryMessages.INVISIBLE_MSG, cc.isArticleTitleVisible());
    PageObjectLogging
        .log(STATS, MercuryMessages.VISIBLE_MSG, MercuryMessages.INVISIBLE_MSG,
             cc.isWikiaStatsContainerVisible());
    PageObjectLogging
        .log(FEATURED_CONTENT, MercuryMessages.VISIBLE_MSG, MercuryMessages.INVISIBLE_MSG, cc.isFeaturedContentVisible());
    PageObjectLogging
        .log(CURATED_CONTENT, MercuryMessages.VISIBLE_MSG, MercuryMessages.INVISIBLE_MSG, cc.isCuratedContentVisible());
    PageObjectLogging
        .log(MIDDLE_AD, MercuryMessages.VISIBLE_MSG, MercuryMessages.INVISIBLE_MSG, cc.isMobileInContentVisible());
    PageObjectLogging
        .log(TRENDING_ARTICLES, MercuryMessages.VISIBLE_MSG, MercuryMessages.INVISIBLE_MSG, cc.isTrendingArticlesVisible());
    PageObjectLogging
        .log(TRENDING_VIDEOS, MercuryMessages.VISIBLE_MSG, MercuryMessages.INVISIBLE_MSG, cc.isTrendingVideosVisible());
    PageObjectLogging
        .log(PREFOOTER_AD, MercuryMessages.VISIBLE_MSG, MercuryMessages.INVISIBLE_MSG, cc.isMobilePrefooterVisible());

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
