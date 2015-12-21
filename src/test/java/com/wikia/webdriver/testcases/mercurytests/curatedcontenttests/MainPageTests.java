package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Device;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.MercuryAlertComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedContentPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedMainPagePageObject;

import org.testng.annotations.Test;

@Execute(
    onDevice = Device.GOOGLE_NEXUS_5,
    browser = Browser.CHROME
)
public class MainPageTests extends NewTestTemplate {

  private static final String ROOT_PATH = "/wiki/Mercury_CC_Wikia";

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

  @Test(groups = "MercuryCuratedMainPageTest_001")
  @Execute(onWikia = MercuryWikis.MERCURY_CC)
  public void MercuryCuratedMainPageTest_001_CheckElementsVisibilityElementsOrderAndRootPath() {
    CuratedMainPagePageObject cc = new CuratedMainPagePageObject(driver);
    cc.openMercuryArticleByName(wikiURL, MercurySubpages.CC_MAIN_PAGE);
    new ArticlePageObject(driver).waitForFooterToBeVisible();

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

    Assertion.assertTrue(cc.isMainPagePadSlotInDOM());

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

    int lastPosition = 0;
    String lastElement = "top";

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

  @Test(groups = "MercuryCuratedMainPageTest_002")
  @Execute(onWikia = MercuryWikis.MERCURY_EMPTY_CC)
  public void MercuryCuratedMainPageTest_002_CheckElementsInvisibility() {
    CuratedMainPagePageObject cc = new CuratedMainPagePageObject(driver);
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

  @Test(groups = "MercuryCuratedMainPageTest_003")
  @Execute(onWikia = MercuryWikis.MERCURY_NTA_CC)
  public void MercuryCuratedMainPageTest_003_CheckElementsForNoTrendingArticles() {
    CuratedMainPagePageObject cc = new CuratedMainPagePageObject(driver);
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

  @Test(groups = "MercuryCuratedMainPageTest_004")
  @Execute(onWikia = MercuryWikis.MERCURY_NTV_CC)
  public void MercuryCuratedMainPageTest_004_CheckElementsVisibilityExceptTrendingVideos() {
    CuratedMainPagePageObject cc = new CuratedMainPagePageObject(driver);
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

    result = !cc.isTrendingVideosVisible();
    PageObjectLogging.log(
        PageElements.TRENDING_VIDEOS.name,
        MercuryMessages.INVISIBLE_MSG,
        MercuryMessages.VISIBLE_MSG,
        result
    );
  }

  @Test(groups = "MercuryCuratedMainPageTest_005")
  @Execute(onWikia = MercuryWikis.MERCURY_NTAV_CC)
  public void MercuryCuratedMainPageTest_005_CheckElementsVisibilityExceptTrendingVideosAndArticles() {
    CuratedMainPagePageObject cc = new CuratedMainPagePageObject(driver);
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

  @Test(groups = "MercuryCuratedMainPageTest_006")
  @Execute(onWikia = MercuryWikis.MERCURY_CC)
  public void MercuryCuratedMainPageTest_006_CheckWrongCategoryAlert() {
    CuratedContentPageObject ccp = new CuratedContentPageObject(driver);
    ccp.openMercuryArticleByNameWithNoCacheBuster(wikiURL, MercurySubpages.CC_MAIN_PAGE);

    MercuryAlertComponentObject mercuryAlert = new MercuryAlertComponentObject(
        driver, MercuryAlertComponentObject.AlertMessage.NOT_EXISTING_CATEGORY);

    String oldUrl = driver.getCurrentUrl();
    ccp.clickOnCuratedContentElementByIndex(2);
    Assertion.assertTrue(mercuryAlert.isAlertMessageVisible());

    ccp.waitForLoadingOverlayToDisappear();
    Assertion.assertUrlEqualToCurrentUrl(driver, oldUrl);
  }
}
