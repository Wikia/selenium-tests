package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
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
    CuratedPageObject cc = new CuratedPageObject(driver);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_CC);
    cc.openMercuryArticleByName(wikiURL, MercuryArticles.CC_MAIN_PAGE);

    boolean result = cc.isUrlPathEqualTo(ROOT_PATH);
    PageObjectLogging.log(
        "Current URL",
        "is set on " + ROOT_PATH,
        "is not set on " + ROOT_PATH,
        result
    );

    result = cc.isMobileTopLeaderboardVisible();
    PageObjectLogging.log(
        PageElements.TOP_LEADERBOARD.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isArticleTitleVisible();
    PageObjectLogging.log(
        PageElements.ARTICLE_TITLE.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isWikiaStatsContainerVisible();
    PageObjectLogging.log(
        PageElements.WIKIA_STATS.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isFeaturedContentVisible();
    PageObjectLogging.log(
        PageElements.FEATURED_CONTENT.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isCuratedContentVisible();
    PageObjectLogging.log(
        PageElements.CURATED_CONTENT.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isMobileInContentVisible();
    PageObjectLogging.log(
        PageElements.IN_CONTENT.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isTrendingArticlesVisible();
    PageObjectLogging.log(
        PageElements.TRENDING_ARTICLES.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isTrendingVideosVisible();
    PageObjectLogging.log(
        PageElements.TRENDING_VIDEOS.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isMobilePrefooterVisible();
    PageObjectLogging.log(
        PageElements.PREFOOTER.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    int lastPosition = 0;
    String lastElement = "top";

    for (PageElements element : PageElements.values()) {
      int newPosition = cc.getElementHeight(element.className);

      result = lastPosition <= newPosition;
      PageObjectLogging.log(
          element.name,
          "is after " + lastElement,
          "is not after " + lastElement,
          result
      );

      lastPosition = newPosition;
      lastElement = element.name;
    }
  }

  // CCT02
  @RelatedIssue(issueID = "CONCF-762")
  @Test(groups = {"MercuryCuratedMainPageTests_002", "MercuryCuratedMainPageTests", "Mercury"}, enabled = false)
  public void MercuryCuratedMainPageTests_002_CheckElementsInvisibility() {
    CuratedPageObject cc = new CuratedPageObject(driver);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_EMPTY_CC);
    cc.openMercuryArticleByName(wikiURL, MercuryArticles.ECC_MAIN_PAGE);

    boolean result = cc.isMobileTopLeaderboardVisible();
    PageObjectLogging.log(
        PageElements.TOP_LEADERBOARD.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isArticleTitleVisible();
    PageObjectLogging.log(
        PageElements.ARTICLE_TITLE.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = !cc.isWikiaStatsContainerVisible();
    PageObjectLogging.log(
        PageElements.WIKIA_STATS.name,
        MercuryMessages.INVISIBLE_MSG,
        MercuryMessages.VISIBLE_MSG,
        result
    );

    result = !cc.isFeaturedContentVisible();
    PageObjectLogging.log(
        PageElements.FEATURED_CONTENT.name,
        MercuryMessages.INVISIBLE_MSG,
        MercuryMessages.VISIBLE_MSG,
        result
    );

    result = !cc.isCuratedContentVisible();
    PageObjectLogging.log(
        PageElements.CURATED_CONTENT.name,
        MercuryMessages.INVISIBLE_MSG,
        MercuryMessages.VISIBLE_MSG,
        result
    );

    result = !cc.isMobileInContentVisible();
    PageObjectLogging.log(
        PageElements.IN_CONTENT.name,
        MercuryMessages.INVISIBLE_MSG,
        MercuryMessages.VISIBLE_MSG,
        result
    );

    result = !cc.isTrendingArticlesVisible();
    PageObjectLogging.log(
        PageElements.TRENDING_ARTICLES.name,
        MercuryMessages.INVISIBLE_MSG,
        MercuryMessages.VISIBLE_MSG,
        result
    );

    result = !cc.isTrendingVideosVisible();
    PageObjectLogging.log(
        PageElements.TRENDING_VIDEOS.name,
        MercuryMessages.INVISIBLE_MSG,
        MercuryMessages.VISIBLE_MSG,
        result
    );

    result = !cc.isMobilePrefooterVisible();
    PageObjectLogging.log(
        PageElements.PREFOOTER.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );
  }

  // CCT03
  @Test(groups = {"MercuryCuratedMainPageTests_003", "MercuryCuratedMainPageTests", "Mercury"})
  public void MercuryCuratedMainPageTests_003_CheckElementsForNoTrendingArticles() {
    CuratedPageObject cc = new CuratedPageObject(driver);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_NTA_CC);
    cc.openMercuryArticleByName(wikiURL, MercuryArticles.NTACC_MAIN_PAGE);

    boolean result = !cc.isTrendingArticlesVisible();
    PageObjectLogging.log(
        PageElements.TRENDING_ARTICLES.name,
        MercuryMessages.INVISIBLE_MSG,
        MercuryMessages.VISIBLE_MSG,
        result
    );

    result = cc.isMobileTopLeaderboardVisible();
    PageObjectLogging.log(
        PageElements.TOP_LEADERBOARD.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isArticleTitleVisible();
    PageObjectLogging.log(
        PageElements.ARTICLE_TITLE.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isWikiaStatsContainerVisible();
    PageObjectLogging.log(
        PageElements.WIKIA_STATS.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isFeaturedContentVisible();
    PageObjectLogging.log(
        PageElements.FEATURED_CONTENT.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isCuratedContentVisible();
    PageObjectLogging.log(
        PageElements.CURATED_CONTENT.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isMobileInContentVisible();
    PageObjectLogging.log(
        PageElements.IN_CONTENT.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isTrendingVideosVisible();
    PageObjectLogging.log(
        PageElements.TRENDING_VIDEOS.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isMobilePrefooterVisible();
    PageObjectLogging.log(
        PageElements.PREFOOTER.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );
  }

  // CCT04
  @Test(groups = {"MercuryCuratedMainPageTests_004", "MercuryCuratedMainPageTests", "Mercury"})
  public void MercuryCuratedMainPageTests_004_CheckElementsVisibilityExceptTrendingVideos() {
    CuratedPageObject cc = new CuratedPageObject(driver);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_NTV_CC);
    cc.openMercuryArticleByName(wikiURL, MercuryArticles.NTVCC_MAIN_PAGE);

    boolean result = cc.isMobileTopLeaderboardVisible();
    PageObjectLogging.log(
        PageElements.TOP_LEADERBOARD.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isArticleTitleVisible();
    PageObjectLogging.log(
        PageElements.ARTICLE_TITLE.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isWikiaStatsContainerVisible();
    PageObjectLogging.log(
        PageElements.WIKIA_STATS.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isFeaturedContentVisible();
    PageObjectLogging.log(
        PageElements.FEATURED_CONTENT.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isCuratedContentVisible();
    PageObjectLogging.log(
        PageElements.CURATED_CONTENT.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isMobileInContentVisible();
    PageObjectLogging.log(
        PageElements.IN_CONTENT.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isTrendingArticlesVisible();
    PageObjectLogging.log(
        PageElements.TRENDING_ARTICLES.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isMobilePrefooterVisible();
    PageObjectLogging.log(
        PageElements.PREFOOTER.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = !cc.isTrendingVideosVisible();
    PageObjectLogging.log(
        PageElements.TRENDING_VIDEOS.name,
        MercuryMessages.INVISIBLE_MSG,
        MercuryMessages.VISIBLE_MSG,
        result
    );
  }

  // CCT05
  @Test(groups = {"MercuryCuratedMainPageTests_005", "MercuryCuratedMainPageTests", "Mercury"})
  public void MercuryCuratedMainPageTests_005_CheckElementsVisibilityExceptTrendingVideosAndArticles() {
    CuratedPageObject cc = new CuratedPageObject(driver);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_NTAV_CC);
    cc.openMercuryArticleByName(wikiURL, MercuryArticles.NTAVCC_MAIN_PAGE);

    boolean result = cc.isMobileTopLeaderboardVisible();
    PageObjectLogging.log(
        PageElements.TOP_LEADERBOARD.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isArticleTitleVisible();
    PageObjectLogging.log(
        PageElements.ARTICLE_TITLE.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isWikiaStatsContainerVisible();
    PageObjectLogging.log(
        PageElements.WIKIA_STATS.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isFeaturedContentVisible();
    PageObjectLogging.log(
        PageElements.FEATURED_CONTENT.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isCuratedContentVisible();
    PageObjectLogging.log(
        PageElements.CURATED_CONTENT.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = cc.isMobileInContentVisible();
    PageObjectLogging.log(
        PageElements.IN_CONTENT.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    result = !cc.isTrendingArticlesVisible();
    PageObjectLogging.log(
        PageElements.TRENDING_ARTICLES.name,
        MercuryMessages.INVISIBLE_MSG,
        MercuryMessages.VISIBLE_MSG,
        result
    );

    result = !cc.isTrendingVideosVisible();
    PageObjectLogging.log(
        PageElements.TRENDING_VIDEOS.name,
        MercuryMessages.INVISIBLE_MSG,
        MercuryMessages.VISIBLE_MSG,
        result
    );

    result = !cc.isMobilePrefooterVisible();
    PageObjectLogging.log(
        PageElements.PREFOOTER.name,
        MercuryMessages.INVISIBLE_MSG,
        MercuryMessages.VISIBLE_MSG,
        result
    );
  }
}
