package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.url.UrlChecker;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedContentPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedMainPagePageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ownership Content X-Wing
 */
@Test(groups = {"MercuryCuratedMainPageTests", "MercuryCuratedContentTests", "Mercury"})
public class MainPageTests extends NewTestTemplate {

  private static final String ROOT_PATH = "/wiki/Mercury_CC_Wikia";

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    //This is fix for problem which was occurring long time ago
    //It was like accessing selector before page finished loading
    //crashed driver
    //TODO: Investigate is that problem still valid
    //Ticket: https://wikia-inc.atlassian.net/browse/CONCF-894
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

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

    PageElements(String name, String className) {
      this.name = name;
      this.className = className;
    }
  }

  //TODO: Move logging methods to page object
  //Ticket: https://wikia-inc.atlassian.net/browse/CONCF-894
  // CCT01
  @Test(groups = "MercuryCuratedMainPageTest_001")
  @RelatedIssue(issueID = "XW-209")
  public void MercuryCuratedMainPageTest_001_CheckElementsVisibilityElementsOrderAndRootPath() {
    CuratedMainPagePageObject cc = new CuratedMainPagePageObject(driver);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_CC);
    cc.openMercuryArticleByName(wikiURL, MercurySubpages.CC_MAIN_PAGE);

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

    // @TODO: find way to show trending articles on mercuryntvcc.wikia.com
    // TICKET: https://wikia-inc.atlassian.net/browse/CONCF-894

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

    //TODO: make function from this
    //Ticket: https://wikia-inc.atlassian.net/browse/CONCF-894
    for (PageElements element : PageElements.values()) {
      if (PageElements.TRENDING_ARTICLES.name.equals(element.name)) {
        continue;
      }

      int newPosition = cc.getElementOffsetTop(element.className);

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
  @Test(groups = "MercuryCuratedMainPageTest_002")
  public void MercuryCuratedMainPageTest_002_CheckElementsInvisibility() {
    CuratedMainPagePageObject cc = new CuratedMainPagePageObject(driver);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_EMPTY_CC);
    cc.openMercuryArticleByName(wikiURL, MercurySubpages.ECC_MAIN_PAGE);

    boolean result = cc.isArticleTitleVisible();
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
  }

  // CCT03
  @Test(groups = "MercuryCuratedMainPageTest_003")
  public void MercuryCuratedMainPageTest_003_CheckElementsForNoTrendingArticles() {
    CuratedMainPagePageObject cc = new CuratedMainPagePageObject(driver);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_NTA_CC);
    cc.openMercuryArticleByName(wikiURL, MercurySubpages.NTACC_MAIN_PAGE);

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
  @Test(groups = "MercuryCuratedMainPageTest_004")
  public void MercuryCuratedMainPageTest_004_CheckElementsVisibilityExceptTrendingVideos() {
    CuratedMainPagePageObject cc = new CuratedMainPagePageObject(driver);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_NTV_CC);
    cc.openMercuryArticleByName(wikiURL, MercurySubpages.NTVCC_MAIN_PAGE);

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

    // @TODO: find way to show trending articles on mercuryntvcc.wikia.com
    // TICKET: https://wikia-inc.atlassian.net/browse/CONCF-894

    result = !cc.isTrendingVideosVisible();
    PageObjectLogging.log(
        PageElements.TRENDING_VIDEOS.name,
        MercuryMessages.INVISIBLE_MSG,
        MercuryMessages.VISIBLE_MSG,
        result
    );
  }

  // CCT05
  @Test(groups = "MercuryCuratedMainPageTest_005")
  public void MercuryCuratedMainPageTest_005_CheckElementsVisibilityExceptTrendingVideosAndArticles() {
    CuratedMainPagePageObject cc = new CuratedMainPagePageObject(driver);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_NTAV_CC);
    cc.openMercuryArticleByName(wikiURL, MercurySubpages.NTAVCC_MAIN_PAGE);

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

  // TODO: add description to test
  // TICKET: https://wikia-inc.atlassian.net/browse/CONCF-894
  // CCT12
  @Test(groups = "MercuryCuratedMainPageTest_006", enabled = false)
  @RelatedIssue(issueID = "XW-209")
  public void MercuryCuratedMainPageTest_006_CheckWrongCategoryAlert() {
    CuratedContentPageObject ccp = new CuratedContentPageObject(driver);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_CC);
    ccp.openMercuryArticleByNameWithNoCacheBuster(wikiURL, MercurySubpages.CC_MAIN_PAGE);

    String oldUrl = driver.getCurrentUrl();
    ccp
        .clickOnCuratedContentElementByIndex(2)
        .isAlertNotificationVisible()
        .waitForLoadingSpinnerToFinish();
    UrlChecker.isUrlEqualToCurrentUrl(driver, oldUrl);
  }
}
