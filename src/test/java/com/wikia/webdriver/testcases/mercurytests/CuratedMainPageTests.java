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

  /**
   * Page elements in correct order
   */
  private enum PageElements {
    TOP_LEADERBOARD("Top Leaderboard AD", ".mobile-top-leaderboard"),
    ARTICLE_TITLE("Article Title", ".article-title"),
    WIKIA_STATS("Wikia Stats Container", ".wikia-stats-container"),
    FEATURED_CONTENT("Featured Content", ".featured-content"),
    CURATED_CONTENT("Curated Content", ".curated-content"),
    IN_CONTENT("In Content AD", ".mobile-in-content"),
    TRENDING_ARTICLES("Trending Articles", ".trending-articles"),
    TRENDING_VIDEOS("Trending Videos", ".trending-videos"),
    PREFOOTER("Prefooter AD", ".mobile-prefooter");

    private String name;
    private String className;

    private PageElements(String name, String className) {
      this.name = name;
      this.className = className;
    }
  }

  // CCT01
  @Test(groups = {"MercuryCuratedMainPageTests_001", "MercuryCuratedMainPageTests", "Mercury"})
  public void MercuryCuratedMainPageTests_001_CheckElementsVisibilityElementsOrderAndRootPath() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.CC_MAIN_PAGE);
    CuratedPageObject cc = new CuratedPageObject(driver);
    PageObjectLogging.logWarning("Check", "Page URL");
    PageObjectLogging
        .log("Current URL", "is set on " + ROOT_PATH, "is not set on " + ROOT_PATH,
             cc.isUrlPathEqualTo(ROOT_PATH));
    PageObjectLogging.logWarning("Check", "Elements visibility");
    PageObjectLogging
        .log(PageElements.TOP_LEADERBOARD.name, MercuryMessages.VISIBLE_MSG,
             MercuryMessages.INVISIBLE_MSG,
             cc.isMobileTopLeaderboardVisible());
    PageObjectLogging
        .log(PageElements.ARTICLE_TITLE.name, MercuryMessages.VISIBLE_MSG,
             MercuryMessages.INVISIBLE_MSG, cc.isArticleTitleVisible());
    PageObjectLogging
        .log(PageElements.WIKIA_STATS.name, MercuryMessages.VISIBLE_MSG,
             MercuryMessages.INVISIBLE_MSG,
             cc.isWikiaStatsContainerVisible());
    PageObjectLogging
        .log(PageElements.FEATURED_CONTENT.name, MercuryMessages.VISIBLE_MSG,
             MercuryMessages.INVISIBLE_MSG, cc.isFeaturedContentVisible());
    PageObjectLogging
        .log(PageElements.CURATED_CONTENT.name, MercuryMessages.VISIBLE_MSG,
             MercuryMessages.INVISIBLE_MSG, cc.isCuratedContentVisible());
    PageObjectLogging
        .log(PageElements.IN_CONTENT.name, MercuryMessages.VISIBLE_MSG,
             MercuryMessages.INVISIBLE_MSG, cc.isMobileInContentVisible());
    PageObjectLogging
        .log(PageElements.TRENDING_ARTICLES.name, MercuryMessages.VISIBLE_MSG,
             MercuryMessages.INVISIBLE_MSG, cc.isTrendingArticlesVisible());
    PageObjectLogging
        .log(PageElements.TRENDING_VIDEOS.name, MercuryMessages.VISIBLE_MSG,
             MercuryMessages.INVISIBLE_MSG, cc.isTrendingVideosVisible());
    PageObjectLogging
        .log(PageElements.PREFOOTER.name, MercuryMessages.VISIBLE_MSG,
             MercuryMessages.INVISIBLE_MSG, cc.isMobilePrefooterVisible());

    int lastPosition = 0;
    String lastElement = "top";

    PageObjectLogging.logWarning("Check", "Elements order");

    for (PageElements element : PageElements.values()) {
      int newPosition = cc.getElementHeight(element.className);
      PageObjectLogging
          .log(element.name, "is after " + lastElement, "is not after " + lastElement,
               lastPosition <= newPosition);
      lastPosition = newPosition;
      lastElement = element.name;
    }
  }

  // CCT02
  @Test(groups = {"MercuryCuratedMainPageTests_002", "MercuryCuratedMainPageTests", "Mercury"})
  public void MercuryCuratedMainPageTests_002_CheckElementsInvisibility() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.ECC_MAIN_PAGE);
    CuratedPageObject cc = new CuratedPageObject(driver);

    PageObjectLogging.logWarning("Check", "Element visibility");
    PageObjectLogging
        .log(PageElements.ARTICLE_TITLE.name, MercuryMessages.VISIBLE_MSG,
             MercuryMessages.INVISIBLE_MSG, cc.isArticleTitleVisible());

    PageObjectLogging.logWarning("Check", "Elements invisibility");
    PageObjectLogging
        .log(PageElements.WIKIA_STATS.name, MercuryMessages.INVISIBLE_MSG,
             MercuryMessages.VISIBLE_MSG,
             !cc.isWikiaStatsContainerVisible());
    PageObjectLogging
        .log(PageElements.FEATURED_CONTENT.name, MercuryMessages.INVISIBLE_MSG,
             MercuryMessages.VISIBLE_MSG, !cc.isFeaturedContentVisible());
    PageObjectLogging
        .log(PageElements.CURATED_CONTENT.name, MercuryMessages.INVISIBLE_MSG,
             MercuryMessages.VISIBLE_MSG, !cc.isCuratedContentVisible());
    PageObjectLogging
        .log(PageElements.IN_CONTENT.name, MercuryMessages.INVISIBLE_MSG,
             MercuryMessages.VISIBLE_MSG, !cc.isMobileInContentVisible());
    PageObjectLogging
        .log(PageElements.TRENDING_ARTICLES.name, MercuryMessages.INVISIBLE_MSG,
             MercuryMessages.VISIBLE_MSG, !cc.isTrendingArticlesVisible());
    PageObjectLogging
        .log(PageElements.TRENDING_VIDEOS.name, MercuryMessages.INVISIBLE_MSG,
             MercuryMessages.VISIBLE_MSG, !cc.isTrendingVideosVisible());
  }

  // CCT03
  @Test(groups = {"MercuryCuratedMainPageTests_003", "MercuryCuratedMainPageTests", "Mercury"})
  public void MercuryCuratedMainPageTests_003_CheckElementsForNoTrendingArticles() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.NTACC_MAIN_PAGE);
    CuratedPageObject cc = new CuratedPageObject(driver);

    PageObjectLogging.logWarning("Check", "Element invisibility");
    PageObjectLogging
        .log(PageElements.TRENDING_ARTICLES.name, MercuryMessages.INVISIBLE_MSG,
             MercuryMessages.VISIBLE_MSG, !cc.isTrendingArticlesVisible());

    PageObjectLogging.logWarning("Check", "Elements visibility");
    PageObjectLogging
        .log(PageElements.TOP_LEADERBOARD.name, MercuryMessages.VISIBLE_MSG,
             MercuryMessages.INVISIBLE_MSG,
             cc.isMobileTopLeaderboardVisible());
    PageObjectLogging
        .log(PageElements.ARTICLE_TITLE.name, MercuryMessages.VISIBLE_MSG,
             MercuryMessages.INVISIBLE_MSG, cc.isArticleTitleVisible());
    PageObjectLogging
        .log(PageElements.WIKIA_STATS.name, MercuryMessages.VISIBLE_MSG,
             MercuryMessages.INVISIBLE_MSG,
             cc.isWikiaStatsContainerVisible());
    PageObjectLogging
        .log(PageElements.FEATURED_CONTENT.name, MercuryMessages.VISIBLE_MSG,
             MercuryMessages.INVISIBLE_MSG, cc.isFeaturedContentVisible());
    PageObjectLogging
        .log(PageElements.CURATED_CONTENT.name, MercuryMessages.VISIBLE_MSG,
             MercuryMessages.INVISIBLE_MSG, cc.isCuratedContentVisible());
    PageObjectLogging
        .log(PageElements.IN_CONTENT.name, MercuryMessages.VISIBLE_MSG,
             MercuryMessages.INVISIBLE_MSG, cc.isMobileInContentVisible());
    PageObjectLogging
        .log(PageElements.TRENDING_VIDEOS.name, MercuryMessages.VISIBLE_MSG,
             MercuryMessages.INVISIBLE_MSG, cc.isTrendingVideosVisible());
    PageObjectLogging
        .log(PageElements.PREFOOTER.name, MercuryMessages.VISIBLE_MSG,
             MercuryMessages.INVISIBLE_MSG, cc.isMobilePrefooterVisible());
  }
}
