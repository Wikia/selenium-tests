package com.wikia.webdriver.testcases.mercurytests.old.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Loading;
import com.wikia.webdriver.elements.mercury.old.ArticlePageObject;
import com.wikia.webdriver.elements.mercury.old.MercuryAlertComponentObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.CuratedContentPageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.CuratedMainPagePageObject;

import org.testng.annotations.Test;

@InBrowser(
    emulator = Emulator.GOOGLE_NEXUS_5,
    browser = Browser.CHROME
)
public class MainPageTests extends NewTestTemplate {

  private static final String ROOT_PATH = "/wiki/Mercury_CC_Wikia";

  /**
   * Page elements in correct order
   */
  private enum PageElements {
    TOP_LEADERBOARD("Top Leaderboard AD", ".mobile-top-leaderboard"),
    ARTICLE_TITLE("Article Title", ".wiki-page-title"),
    WIKIA_STATS("Wikia Stats Container", ".wikia-stats-container"),
    FEATURED_CONTENT("Featured Content", ".featured-content"),
    CURATED_CONTENT("Curated Content", ".curated-content"),
    IN_CONTENT("In Content AD", ".mobile-in-content"),
    TRENDING_ARTICLES("Trending Articles", ".trending-articles"),
    PREFOOTER("Prefooter AD", ".mobile-prefooter"),
    TRENDING_VIDEOS("Trending Videos", ".trending-videos");

    private String name;
    private String className;

    PageElements(String name, String className) {
      this.name = name;
      this.className = className;
    }
  }

  private Navigate navigate;
  private CuratedMainPagePageObject cc;
  private CuratedContentPageObject curatedContent;
  private MercuryAlertComponentObject mercuryAlert;
  private Loading loading;

  private void init() {
    this.navigate = new Navigate();
    this.cc = new CuratedMainPagePageObject(driver);
    this.curatedContent = new CuratedContentPageObject(driver);
    this.loading = new Loading(driver);
    this.mercuryAlert = new MercuryAlertComponentObject(
        driver, MercuryAlertComponentObject.AlertMessage.NOT_EXISTING_CATEGORY);
  }

  @Test(groups = "MercuryCuratedMainPageTest_001")
  @Execute(onWikia = MercuryWikis.MERCURY_CC)
  public void MercuryCuratedMainPageTest_001_CheckElementsVisibilityElementsOrderAndRootPath() {
    init();

    navigate.toPage(MercurySubpages.CC_MAIN_PAGE);
    new ArticlePageObject(driver).waitForFooterToBeVisible();

    boolean result = driver.getCurrentUrl().contains(ROOT_PATH);
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

    result = cc.isTrendingVideosVisible();
    PageObjectLogging.log(
        PageElements.TRENDING_VIDEOS.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    int lastPosition = 0;
    String lastElement = "top";

    for (PageElements element : PageElements.values()) {
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
    init();

    navigate.toPage(MercurySubpages.ECC_MAIN_PAGE);

    boolean result = cc.isRevisedArticleTitleVisible();
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
    init();

    navigate.toPage(MercurySubpages.NTACC_MAIN_PAGE);

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

    result = !cc.isMobilePrefooterVisible();
    PageObjectLogging.log(
        PageElements.PREFOOTER.name,
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
  }

  @Test(groups = "MercuryCuratedMainPageTest_004")
  @Execute(onWikia = MercuryWikis.MERCURY_NTV_CC)
  public void MercuryCuratedMainPageTest_004_CheckElementsVisibilityExceptTrendingVideos() {
    init();

    navigate.toPage(MercurySubpages.NTVCC_MAIN_PAGE);

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
    init();

    navigate.toPage(MercurySubpages.NTAVCC_MAIN_PAGE);

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

    result = !cc.isMobilePrefooterVisible();
    PageObjectLogging.log(
        PageElements.PREFOOTER.name,
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

  @Test(groups = "MercuryCuratedMainPageTest_006")
  @Execute(onWikia = MercuryWikis.MERCURY_CC)
  public void MercuryCuratedMainPageTest_006_CheckWrongCategoryAlert() {
    init();

    navigate.toPage(MercurySubpages.CC_MAIN_PAGE);

    String oldUrl = driver.getCurrentUrl();
    curatedContent.clickOnCuratedContentElementByIndex(2);
    Assertion.assertTrue(mercuryAlert.isAlertMessageVisible());

    loading.handleAsyncPageReload();
    Assertion.assertTrue(oldUrl.contains(driver.getCurrentUrl()));
  }
}
