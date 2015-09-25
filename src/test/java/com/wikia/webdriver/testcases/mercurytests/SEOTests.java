package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.SEOUtils;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedContentPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ownership Content X-Wing
 */
public class SEOTests extends NewTestTemplate {

  private static final List<String> ROBOTS_TAG_ATTRIBUTES_INDEX_FOLLOW =
      Arrays.asList("index", "follow");
  private static final List<String> ROBOTS_TAG_ATTRIBUTES_NOINDEX_FOLLOW =
      Arrays.asList("noindex", "follow");
  private static final String MUPPET_MAIN_PAGE = "Muppet_Wiki";

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  // SEOT01
  @RelatedIssue(issueID = "HG-671")
  @Test(groups = {"MercurySEOTest_001", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_001_MetaTags_CanonicalLink() {
    wikiURL = urlBuilder.getUrlForWiki("muppet");
    NavigationSideComponentObject leftNav = new NavigationSideComponentObject(driver);
    SEOUtils seo = new SEOUtils(driver);
    leftNav.openMercuryArticleByName(wikiURL, MUPPET_MAIN_PAGE);

    // Uncomment after issue is fixed - related to HG-668
    /*PageObjectLogging.log(
        "link[rel='canonical']",
        "contains current url",
        "contains wrong url",
        seo.isLinkRelCanonical()
    );*/

    boolean result = seo.isOgFbApp();
    PageObjectLogging.log(
        "meta[property='fb:app_id']",
        "is filled",
        "is empty",
        result
    );

    result = seo.isOgImage();
    PageObjectLogging.log(
        "meta[property='og:image']",
        "is filled",
        "is empty",
        result
    );

    result = seo.isOgUrlTag();
    PageObjectLogging.log(
        "meta[property='og:url']",
        "contains current url",
        "contains wrong url",
        result
    );

    result = seo.isOgDescription();
    PageObjectLogging.log(
        "meta[property='og:description']",
        "is filled",
        "is empty",
        result
    );

    result = !seo.isOgSiteName();
    PageObjectLogging.log(
        "meta[property='og:site_name']",
        "is empty",
        "is filled",
        result
    );

    result = seo.isOgTitleWithWiki();
    PageObjectLogging.log(
        "meta[property='og:title']",
        "contains Wiki",
        "is wrong",
        result
    );

    result = seo.isOgTypeWebsite();
    PageObjectLogging.log(
        "meta[property='og:type']",
        "contains website",
        "is wrong",
        result
    );

    // Uncomment after issue is fixed - related to HG-605
    /*String lastDesc = seo.getDescription();
    leftNav.clickSearchButton();
    leftNav.clickNavListElement(0);
    seo.waitForLoadingSpinnerToFinish();

    PageObjectLogging.logWarning(
        "Site status",
        "Page was reloaded asynchronously"
    );

    PageObjectLogging.log(
        "meta[property='og:description']",
        "is filled",
        "is empty",
        seo.isOgDescription()
    );

    PageObjectLogging.log(
        "meta[property='og:description']",
        "is different",
        "does not changed",
        !lastDesc.equals(seo.getDescription())
    );

    PageObjectLogging.log(
        "meta[property='og:site_name']",
        "is filled",
        "is empty",
        seo.isOgSiteName()
    );

    PageObjectLogging.log(
        "meta[property='og:title']",
        "contains Wiki",
        "is wrong",
        seo.isOgTitleWithWiki()
    );

    PageObjectLogging.log(
        "meta[property='og:type']",
        "contains article",
        "is wrong",
        seo.isOgTypeArticle()
    );*/
  }

  @Test(groups = {"MercurySEOTest_002", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_002_MetaTags_Robots_SectionToMainPage() {
    SEOUtils seoUtils = new SEOUtils(driver);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_CC);
    CuratedContentPageObject section = new CuratedContentPageObject(driver);
    section.openCuratedContentPage(wikiURL, MercurySubpages.CC_SECTION_CATEGORIES);

    Assertion.assertTrue(seoUtils.isAttributesListPresentInRobotsMetaTag(
        ROBOTS_TAG_ATTRIBUTES_NOINDEX_FOLLOW));
    section.clickOnMainPageLink();
    Assertion.assertTrue(seoUtils.isAttributesListPresentInRobotsMetaTag(
        ROBOTS_TAG_ATTRIBUTES_INDEX_FOLLOW));
  }

  @Test(groups = {"MercurySEOTest_003", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_003_MetaTags_Robots_CategoryToMainPage() {
    SEOUtils seoUtils = new SEOUtils(driver);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_CC);
    CuratedContentPageObject category = new CuratedContentPageObject(driver);
    category.openCuratedContentPage(wikiURL, MercurySubpages.CC_CATEGORY_10_ITEMS);

    Assertion.assertTrue(seoUtils.isAttributesListPresentInRobotsMetaTag(
        ROBOTS_TAG_ATTRIBUTES_NOINDEX_FOLLOW));
    category.clickOnMainPageLink();
    Assertion.assertFalse(seoUtils.isRobotsMetaTagSet());
  }

  @Test(groups = {"MercurySEOTest_004", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_004_MetaTags_Robots_MainPageToSection() {
    SEOUtils seoUtils = new SEOUtils(driver);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_CC);
    CuratedContentPageObject mainPage = new CuratedContentPageObject(driver);
    mainPage.openCuratedMainPage(wikiURL, MercurySubpages.CC_MAIN_PAGE);

    Assertion.assertTrue(seoUtils.isAttributesListPresentInRobotsMetaTag(
        ROBOTS_TAG_ATTRIBUTES_INDEX_FOLLOW));
    mainPage.clickOnCuratedContentElementByIndex(0);
    Assertion.assertTrue(seoUtils.isAttributesListPresentInRobotsMetaTag(
        ROBOTS_TAG_ATTRIBUTES_NOINDEX_FOLLOW));
  }

  @Test(groups = {"MercurySEOTest_005", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_005_MetaTags_Robots_MainPageToCategory() {
    SEOUtils seoUtils = new SEOUtils(driver);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_CC);
    CuratedContentPageObject mainPage = new CuratedContentPageObject(driver);
    mainPage.openCuratedMainPage(wikiURL, MercurySubpages.CC_MAIN_PAGE);

    Assertion.assertTrue(seoUtils.isAttributesListPresentInRobotsMetaTag(
        ROBOTS_TAG_ATTRIBUTES_INDEX_FOLLOW));
    mainPage.clickOnCuratedContentElementByIndex(0);
    mainPage.waitForLoadingSpinnerToFinish();
    mainPage.clickOnCuratedContentElementByIndex(0);
    Assertion.assertTrue(seoUtils.isAttributesListPresentInRobotsMetaTag(
        ROBOTS_TAG_ATTRIBUTES_NOINDEX_FOLLOW));
  }
}
