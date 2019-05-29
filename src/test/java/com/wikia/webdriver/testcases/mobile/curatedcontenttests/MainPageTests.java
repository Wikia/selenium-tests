package com.wikia.webdriver.testcases.mobile.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MobileSubpages;
import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.communities.mobile.pages.ArticlePageObject;
import com.wikia.webdriver.elements.communities.mobile.pages.curatedcontent.CuratedContentPageObject;
import com.wikia.webdriver.elements.communities.mobile.pages.curatedcontent.CuratedMainPagePageObject;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

@Test(groups = "Mercury_CuratedMainPage")
@InBrowser(emulator = Emulator.GOOGLE_NEXUS_5, browser = Browser.CHROME)
public class MainPageTests extends NewTestTemplate {

  private static String RIVERSED_ARTICLE_TITLE_SELECTOR = ".wiki-page-header__title";
  private static String AD_TOP_LEADERBOARD_SELECTOR = ".top-leaderboard";
  private static String ARTICLE_TITLE_SELECTOR = ".wiki-page-title";
  private static String WIKIA_STATS_SELECTOR = ".wikia-stats-container";
  private static String FEATURED_CONTENT_SELECTOR = ".featured-content";
  private static String CURATED_CONTENT_SELECTOR = ".curated-content";
  private static String AD_MOBILE_TOP_BOXAD_SELECTOR = "#top_boxad";
  private static String TRENDING_ARTICLES_SELECTOR = ".trending-articles";
  private static String TRENDING_VIDEOS_SELECTOR = ".trending-videos";

  private Navigate navigate;
  private CuratedMainPagePageObject curatedMainPage;
  private CuratedContentPageObject curatedContent;

  private void init() {
    this.navigate = new Navigate();
    this.curatedMainPage = new CuratedMainPagePageObject();
    this.curatedContent = new CuratedContentPageObject();
  }

  @Test(groups = "MercuryCuratedMainPageTest_001")
  @Execute(onWikia = MobileWikis.MERCURY_CC)
  public void MercuryCuratedMainPageTest_001_CheckElementsVisibilityElementsOrderAndRootPath() {
    init();

    navigate.toPageByPath(MobileSubpages.CC_MAIN_PAGE);
    new ArticlePageObject(driver).isFooterVisible();

    List<String> elementsWhichShouldBeDisplayed = Arrays.asList(AD_TOP_LEADERBOARD_SELECTOR,
                                                                ARTICLE_TITLE_SELECTOR,
                                                                WIKIA_STATS_SELECTOR,
                                                                FEATURED_CONTENT_SELECTOR,
                                                                CURATED_CONTENT_SELECTOR,
                                                               AD_MOBILE_TOP_BOXAD_SELECTOR,
                                                                TRENDING_ARTICLES_SELECTOR,
                                                                TRENDING_VIDEOS_SELECTOR
    );

    verifyElementsVisible(elementsWhichShouldBeDisplayed);
    verifyElementsPositionOnPage(elementsWhichShouldBeDisplayed);
  }

  @Test(groups = "MercuryCuratedMainPageTest_002")
  @Execute(onWikia = MobileWikis.MERCURY_EMPTY_CC)
  public void MercuryCuratedMainPageTest_002_CheckElementsInvisibility() {
    init();

    navigate.toPageByPath(MobileSubpages.ECC_MAIN_PAGE);

    List<String> elementsWhichShouldBeDisplayed = Arrays.asList(RIVERSED_ARTICLE_TITLE_SELECTOR);

    List<String> elementsWhichShouldNotBeDisplayed = Arrays.asList(WIKIA_STATS_SELECTOR,
                                                                   FEATURED_CONTENT_SELECTOR,
                                                                   CURATED_CONTENT_SELECTOR,
                                                                   TRENDING_ARTICLES_SELECTOR,
                                                                   TRENDING_VIDEOS_SELECTOR
    );

    verifyElementsVisible(elementsWhichShouldBeDisplayed);
    verifyElementsNotVisible(elementsWhichShouldNotBeDisplayed);
  }

  @Test(groups = "MercuryCuratedMainPageTest_003")
  @Execute(onWikia = MobileWikis.MERCURY_NTA_CC)
  public void MercuryCuratedMainPageTest_003_CheckElementsForNoTrendingArticles() {
    init();

    navigate.toPageByPath(MobileSubpages.NTACC_MAIN_PAGE);

    List<String> elementsWhichShouldBeDisplayed = Arrays.asList(AD_TOP_LEADERBOARD_SELECTOR,
                                                                ARTICLE_TITLE_SELECTOR,
                                                                WIKIA_STATS_SELECTOR,
                                                                FEATURED_CONTENT_SELECTOR,
                                                                CURATED_CONTENT_SELECTOR,
                                                                AD_MOBILE_TOP_BOXAD_SELECTOR,
                                                                TRENDING_VIDEOS_SELECTOR
    );

    List<String> elementsWhichShouldNotBeDisplayed = Arrays.asList(TRENDING_ARTICLES_SELECTOR);

    verifyElementsVisible(elementsWhichShouldBeDisplayed);
    verifyElementsNotVisible(elementsWhichShouldNotBeDisplayed);
  }

  @Test(groups = "MercuryCuratedMainPageTest_004")
  @Execute(onWikia = MobileWikis.MERCURY_NTV_CC)
  public void MercuryCuratedMainPageTest_004_CheckElementsVisibilityExceptTrendingVideos() {
    init();

    navigate.toPageByPath(MobileSubpages.NTVCC_MAIN_PAGE);

    List<String> elementsWhichShouldBeDisplayed = Arrays.asList(AD_TOP_LEADERBOARD_SELECTOR,
                                                                ARTICLE_TITLE_SELECTOR,
                                                                WIKIA_STATS_SELECTOR,
                                                                FEATURED_CONTENT_SELECTOR,
                                                                CURATED_CONTENT_SELECTOR,
                                                                AD_MOBILE_TOP_BOXAD_SELECTOR
    );

    List<String> elementsWhichShouldNotBeDisplayed = Arrays.asList(TRENDING_VIDEOS_SELECTOR);

    verifyElementsVisible(elementsWhichShouldBeDisplayed);
    verifyElementsNotVisible(elementsWhichShouldNotBeDisplayed);
  }

  @Test(groups = "MercuryCuratedMainPageTest_005")
  @Execute(onWikia = MobileWikis.MERCURY_NTAV_CC)
  public void MercuryCuratedMainPageTest_005_CheckElementsVisibilityExceptTrendingVideosAndArticles() {
    init();
    navigate.toPageByPath(MobileSubpages.NTAVCC_MAIN_PAGE);

    List<String> elementsWhichShouldBeDisplayed = Arrays.asList(AD_TOP_LEADERBOARD_SELECTOR,
                                                                ARTICLE_TITLE_SELECTOR,
                                                                WIKIA_STATS_SELECTOR,
                                                                FEATURED_CONTENT_SELECTOR,
                                                                CURATED_CONTENT_SELECTOR,
                                                                AD_MOBILE_TOP_BOXAD_SELECTOR
    );

    List<String> elementsWhichShouldNotBeDisplayed = Arrays.asList(TRENDING_ARTICLES_SELECTOR,
                                                                   TRENDING_VIDEOS_SELECTOR
    );

    verifyElementsVisible(elementsWhichShouldBeDisplayed);
    verifyElementsNotVisible(elementsWhichShouldNotBeDisplayed);
  }

  private void verifyElementsVisible(List<String> elementsList) {
    elementsList.forEach(element -> Assertion.assertTrue(curatedMainPage.isCuratedElementVisible(
        element), element + " is not visible"));
  }

  private void verifyElementsNotVisible(List<String> elementsList) {
    elementsList.forEach(element -> Assertion.assertFalse(curatedMainPage.isCuratedElementVisible(
        element), element + " is visible"));
  }

  private void verifyElementsPositionOnPage(List<String> elementsList) {
    float lastPosition = 0;

    for (int i = 0; i < elementsList.size(); i++) {
      float newPosition = curatedMainPage.getElementOffsetTop(elementsList.get(i));
      Assertion.assertTrue(lastPosition <= newPosition,
                           elementsList.get(i) + "is in wrong position"
      );
      lastPosition = newPosition;
    }
  }
}
