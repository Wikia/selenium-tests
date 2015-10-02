package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryPaths;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlChecker;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedContentPageObject;

/**
 * @ownership: Content X-Wing
 */
@Test(groups = {"MercuryCuratedNavigationTests", "MercuryCuratedContentTests", "MercuryCuratedMainPageTests", "Mercury"})
public class NavigationTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_CC);
  }

  // CCT06
  @Test(groups = "MercuryCuratedNavigationTest_001")
  public void MercuryCuratedNavigationTest_001_navigateThroughCategory() {
    CuratedContentPageObject category = new CuratedContentPageObject(driver);
    category.openCuratedMainPage(wikiURL, MercurySubpages.CC_MAIN_PAGE);

    category.clickOnCuratedContentElementByIndex(1);
    category.waitForLoadingSpinnerToFinish();

    category.isTitleVisible().isLinkToMainPageVisible().isSectionVisible()
        .isCuratedContentItemVisibleByIndex(1);

    String sectionTitle = category.getTitle();
    String expectedUrlPath = MercuryPaths.ROOT_PATH_CATEGORY + sectionTitle;
    UrlChecker.isPathContainedInCurrentUrl(driver, expectedUrlPath);

    String previousUrl = driver.getCurrentUrl();
    category.navigateToMainPage();
    String nextUrl = driver.getCurrentUrl();
    UrlChecker.isPathContainedInCurrentUrl(driver, MercuryPaths.ROOT_PATH);

    driver.navigate().back();
    Assertion.assertUrlEqualToCurrentUrl(driver, previousUrl);

    driver.navigate().forward();
    Assertion.assertUrlEqualToCurrentUrl(driver, nextUrl);
  }

  // CCT07
  @Test(groups = "MercuryCuratedNavigationTest_002")
  public void MercuryCuratedNavigationTest_002_navigateThroughSection() {
    CuratedContentPageObject section = new CuratedContentPageObject(driver);
    section.openCuratedMainPage(wikiURL, MercurySubpages.CC_MAIN_PAGE);

    section.clickOnCuratedContentElementByIndex(0);
    section.waitForLoadingSpinnerToFinish();

    section.isTitleVisible().isLinkToMainPageVisible().isSectionVisible()
        .isCuratedContentItemVisibleByIndex(1);

    UrlChecker.isPathContainedInCurrentUrl(driver,
        MercuryPaths.ROOT_PATH_SECTION + section.getTitle());
  }

  // CCT11
  @Test(groups = "MercuryCuratedNavigationTest_003")
  public void MercuryCuratedNavigationTest_003_navigateThroughNamespaces() {
    CuratedContentPageObject category = new CuratedContentPageObject(driver);
    category.openCuratedContentPage(wikiURL, MercurySubpages.CC_CATEGORY_ARTICLES)
        .isArticleIconVisible().clickOnCuratedContentElementByIndex(0)
        .waitForLoadingSpinnerToFinish();
    UrlChecker.isPathContainedInCurrentUrl(driver, MercuryPaths.ROOT_ARTICLE_PATH);

    category.openCuratedContentPage(wikiURL, MercurySubpages.CC_CATEGORY_BLOGS).isBlogIconVisible()
        .clickOnCuratedContentElementByIndex(0).waitForLoadingSpinnerToFinish();
    UrlChecker.isPathContainedInCurrentUrl(driver, MercuryPaths.ROOT_ARTICLE_PATH);

    category.openCuratedContentPage(wikiURL, MercurySubpages.CC_CATEGORY_BLOGS)
        .isImageIconVisible().clickOnCuratedContentElementByIndex(0)
        .waitForLoadingSpinnerToFinish();
    UrlChecker.isPathContainedInCurrentUrl(driver, MercuryPaths.ROOT_ARTICLE_PATH);

    category.openCuratedContentPage(wikiURL, MercurySubpages.CC_CATEGORY_BLOGS)
        .isVideoIconVisible().clickOnCuratedContentElementByIndex(0)
        .waitForLoadingSpinnerToFinish();
    UrlChecker.isPathContainedInCurrentUrl(driver, MercuryPaths.ROOT_ARTICLE_PATH);
  }

  // CCT09
  @Test(groups = "MercuryCuratedNavigationTest_004")
  @Execute(onWikia = MercuryWikis.MERCURY_CC)
  public void MercuryCuratedNavigationTest_004_navigateThroughDifferentUrl() {
    CuratedContentPageObject section = new CuratedContentPageObject(driver);

    String expectedUrl =
        urlBuilder.getUrlForPathWithoutWiki(Configuration.getWikiName(),
            MercurySubpages.CC_CATEGORY_TEMPLATES);
    String testUrl = expectedUrl;
    section.openWikiPage(testUrl);
    section.waitForLoadingSpinnerToFinish();
    Assertion.assertUrlEqualToCurrentUrl(driver, expectedUrl);

    expectedUrl =
        urlBuilder.getUrlForPathWithoutWiki(Configuration.getWikiName(),
            MercurySubpages.CC_SECTION_CATEGORIES);
    testUrl = expectedUrl;
    section.openWikiPage(testUrl);
    section.waitForLoadingSpinnerToFinish();
    Assertion.assertUrlEqualToCurrentUrl(driver, expectedUrl);

    expectedUrl =
        urlBuilder.getUrlForPath(Configuration.getWikiName(), MercurySubpages.CC_MAIN_PAGE);
    testUrl =
        urlBuilder.getUrlForPathWithoutWiki(Configuration.getWikiName(),
            MercurySubpages.CC_EMPTY_CATEGORY);
    section.openWikiPage(testUrl);
    section.waitForLoadingSpinnerToFinish();
    Assertion.assertUrlEqualToCurrentUrl(driver, expectedUrl);

    expectedUrl =
        urlBuilder.getUrlForPath(Configuration.getWikiName(), MercurySubpages.CC_MAIN_PAGE);
    testUrl =
        urlBuilder.getUrlForPathWithoutWiki(Configuration.getWikiName(),
            MercurySubpages.CC_NOT_EXISTING_SECTION);
    section.openWikiPage(testUrl);
    section.waitForLoadingSpinnerToFinish();
    section.isAlertNotificationVisible();
    Assertion.assertUrlEqualToCurrentUrl(driver, expectedUrl);
  }
}
