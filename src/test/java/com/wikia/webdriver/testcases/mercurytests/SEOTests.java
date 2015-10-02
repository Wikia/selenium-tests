package com.wikia.webdriver.testcases.mercurytests;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.SEOUtils;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedContentPageObject;

/**
 * @ownership Content X-Wing
 */
@Test(groups = {"MercurySEOTests", "Mercury"})
public class SEOTests extends NewTestTemplate {

  private static final List<String> ROBOTS_TAG_ATTRIBUTES_NOINDEX_FOLLOW = Arrays.asList("noindex",
      "follow");
  private static final String MUPPET_MAIN_PAGE = "Muppet_Wiki";
  private static final String ROBOTS_META_TAG_NOT_PRESENT_MESSAGE =
      "Robot Meta Tags are set when not supposed to";
  private static final String ROBOTS_META_TAG_PRESENT_MESSAGE =
      "Robot Meta Tags are not set when supposed to";
  private static final String ROBOTS_META_TAG_DIFFERENT_MESSAGE =
      "Robot Meta Tags are different than expected";

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  // SEOT01
  @RelatedIssue(issueID = "HG-671")
  @Test(groups = "MercurySEOTest_001")
  public void MercurySEOTest_001_MetaTags_CanonicalLink() {
    wikiURL = urlBuilder.getUrlForWiki("muppet");
    NavigationSideComponentObject leftNav = new NavigationSideComponentObject(driver);
    SEOUtils seo = new SEOUtils(driver);
    leftNav.openMercuryArticleByName(wikiURL, MUPPET_MAIN_PAGE);

    // Uncomment after issue is fixed - related to HG-668
    /*
     * PageObjectLogging.log( "link[rel='canonical']", "contains current url", "contains wrong url",
     * seo.isLinkRelCanonical() );
     */

    boolean result = seo.isOgFbApp();
    LOG.result("meta[property='fb:app_id']", "is filled", "is empty", result);

    result = seo.isOgImage();
    LOG.result("meta[property='og:image']", "is filled", "is empty", result);

    result = seo.isOgUrlTag();
    LOG.result("meta[property='og:url']", "contains current url", "contains wrong url", result);

    result = seo.isOgDescription();
    LOG.result("meta[property='og:description']", "is filled", "is empty", result);

    result = !seo.isOgSiteName();
    LOG.result("meta[property='og:site_name']", "is empty", "is filled", result);

    result = seo.isOgTitleWithWiki();
    LOG.result("meta[property='og:title']", "contains Wiki", "is wrong", result);

    result = seo.isOgTypeWebsite();
    LOG.result("meta[property='og:type']", "contains website", "is wrong", result);

    // Uncomment after issue is fixed - related to HG-605
    /*
     * String lastDesc = seo.getDescription(); leftNav.clickSearchButton();
     * leftNav.clickNavListElement(0); seo.waitForLoadingSpinnerToFinish();
     * 
     * PageObjectLogging.warning( "Site status", "Page was reloaded asynchronously" );
     * 
     * PageObjectLogging.log( "meta[property='og:description']", "is filled", "is empty",
     * seo.isOgDescription() );
     * 
     * PageObjectLogging.log( "meta[property='og:description']", "is different", "does not changed",
     * !lastDesc.equals(seo.getDescription()) );
     * 
     * PageObjectLogging.log( "meta[property='og:site_name']", "is filled", "is empty",
     * seo.isOgSiteName() );
     * 
     * PageObjectLogging.log( "meta[property='og:title']", "contains Wiki", "is wrong",
     * seo.isOgTitleWithWiki() );
     * 
     * PageObjectLogging.log( "meta[property='og:type']", "contains article", "is wrong",
     * seo.isOgTypeArticle() );
     */
  }

  @Test(groups = "MercurySEOTest_002", enabled = false)
  public void MercurySEOTest_002_MetaTags_Robots_SectionToMainPage() {
    SEOUtils seoUtils = new SEOUtils(driver);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_CC);
    CuratedContentPageObject section = new CuratedContentPageObject(driver);
    section.openCuratedContentPage(wikiURL, MercurySubpages.CC_SECTION_CATEGORIES);

    Assertion.assertTrue(seoUtils.isRobotsMetaTagSet(), ROBOTS_META_TAG_NOT_PRESENT_MESSAGE);
    Assertion.assertTrue(
        seoUtils.isAttributesListPresentInRobotsMetaTag(ROBOTS_TAG_ATTRIBUTES_NOINDEX_FOLLOW),
        ROBOTS_META_TAG_DIFFERENT_MESSAGE);
    section.clickOnMainPageLink();
    Assertion.assertFalse(seoUtils.isRobotsMetaTagSet(), ROBOTS_META_TAG_PRESENT_MESSAGE);
  }

  @Test(groups = "MercurySEOTest_003", enabled = false)
  public void MercurySEOTest_003_MetaTags_Robots_CategoryToMainPage() {
    SEOUtils seoUtils = new SEOUtils(driver);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_CC);
    CuratedContentPageObject category = new CuratedContentPageObject(driver);
    category.openCuratedContentPage(wikiURL, MercurySubpages.CC_CATEGORY_10_ITEMS);

    Assertion.assertTrue(seoUtils.isRobotsMetaTagSet(), ROBOTS_META_TAG_NOT_PRESENT_MESSAGE);
    Assertion.assertTrue(
        seoUtils.isAttributesListPresentInRobotsMetaTag(ROBOTS_TAG_ATTRIBUTES_NOINDEX_FOLLOW),
        ROBOTS_META_TAG_DIFFERENT_MESSAGE);
    category.clickOnMainPageLink();
    Assertion.assertFalse(seoUtils.isRobotsMetaTagSet(), ROBOTS_META_TAG_PRESENT_MESSAGE);
  }

  @Test(groups = "MercurySEOTest_004", enabled = false)
  public void MercurySEOTest_004_MetaTags_Robots_MainPageToSection() {
    SEOUtils seoUtils = new SEOUtils(driver);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_CC);
    CuratedContentPageObject mainPage = new CuratedContentPageObject(driver);
    mainPage.openCuratedMainPage(wikiURL, MercurySubpages.CC_MAIN_PAGE);

    Assertion.assertFalse(seoUtils.isRobotsMetaTagSet(), ROBOTS_META_TAG_PRESENT_MESSAGE);
    mainPage.clickOnCuratedContentElementByIndex(0);
    mainPage.waitForLoadingSpinnerToFinish();
    Assertion.assertTrue(seoUtils.isRobotsMetaTagSet(), ROBOTS_META_TAG_NOT_PRESENT_MESSAGE);
    Assertion.assertTrue(
        seoUtils.isAttributesListPresentInRobotsMetaTag(ROBOTS_TAG_ATTRIBUTES_NOINDEX_FOLLOW),
        ROBOTS_META_TAG_DIFFERENT_MESSAGE);
  }

  @Test(groups = "MercurySEOTest_005", enabled = false)
  public void MercurySEOTest_005_MetaTags_Robots_MainPageToCategory() {
    SEOUtils seoUtils = new SEOUtils(driver);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_CC);
    CuratedContentPageObject mainPage = new CuratedContentPageObject(driver);
    mainPage.openCuratedMainPage(wikiURL, MercurySubpages.CC_MAIN_PAGE);

    Assertion.assertFalse(seoUtils.isRobotsMetaTagSet(), ROBOTS_META_TAG_PRESENT_MESSAGE);
    mainPage.clickOnCuratedContentElementByIndex(0);
    mainPage.waitForLoadingSpinnerToFinish();
    Assertion.assertTrue(seoUtils.isRobotsMetaTagSet(), ROBOTS_META_TAG_NOT_PRESENT_MESSAGE);
    Assertion.assertTrue(
        seoUtils.isAttributesListPresentInRobotsMetaTag(ROBOTS_TAG_ATTRIBUTES_NOINDEX_FOLLOW),
        ROBOTS_META_TAG_DIFFERENT_MESSAGE);
    mainPage.clickOnCuratedContentElementByIndex(0);
    mainPage.waitForLoadingSpinnerToFinish();
    Assertion.assertTrue(seoUtils.isRobotsMetaTagSet(), ROBOTS_META_TAG_NOT_PRESENT_MESSAGE);
    Assertion.assertTrue(
        seoUtils.isAttributesListPresentInRobotsMetaTag(ROBOTS_TAG_ATTRIBUTES_NOINDEX_FOLLOW),
        ROBOTS_META_TAG_DIFFERENT_MESSAGE);
  }
}
