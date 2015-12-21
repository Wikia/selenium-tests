package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercuryPaths;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.contentpatterns.WikiTextContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Device;
import com.wikia.webdriver.common.core.url.UrlChecker;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.MercuryAlertComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedContentPageObject;

import org.testng.annotations.Test;

@Execute(
    onWikia = MercuryWikis.MERCURY_CC,
    onDevice = Device.GOOGLE_NEXUS_5,
    browser = Browser.CHROME
)
public class NavigationTests extends NewTestTemplate {

  @Test(groups = "MercuryCuratedNavigationTest_001")
  @RelatedIssue(issueID = "XW-687", comment = "java.lang.IndexOutOfBoundsException: Index: 1, Size: 0")
  public void MercuryCuratedNavigationTest_001_navigateThroughCategory() {
    CuratedContentPageObject category = new CuratedContentPageObject(driver);
    category.openCuratedMainPage(wikiURL, MercurySubpages.CC_MAIN_PAGE);

    category.clickOnCuratedContentElementByIndex(1);
    category.waitForLoadingOverlayToDisappear();

    category
        .isTitleVisible()
        .isLinkToMainPageVisible()
        .isSectionVisible()
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

  @Test(groups = "MercuryCuratedNavigationTest_002")
  public void MercuryCuratedNavigationTest_002_navigateThroughSection() {
    CuratedContentPageObject section = new CuratedContentPageObject(driver);
    section.openCuratedMainPage(wikiURL, MercurySubpages.CC_MAIN_PAGE);

    section.clickOnCuratedContentElementByIndex(0);
    section.waitForLoadingOverlayToDisappear();

    section
        .isTitleVisible()
        .isLinkToMainPageVisible()
        .isSectionVisible()
        .isCuratedContentItemVisibleByIndex(1);

    UrlChecker
        .isPathContainedInCurrentUrl(driver, MercuryPaths.ROOT_PATH_SECTION + section.getTitle());
  }

  @Test(groups = "MercuryCuratedNavigationTest_003")
  @RelatedIssue(issueID = "XW-640")
  public void MercuryCuratedNavigationTest_003_navigateThroughNamespaces() {
    CuratedContentPageObject category = new CuratedContentPageObject(driver);
    category
        .openCuratedContentPage(wikiURL, MercurySubpages.CC_CATEGORY_ARTICLES)
        .isArticleIconVisible()
        .clickOnCuratedContentElementByIndex(0)
        .waitForLoadingOverlayToDisappear();
    UrlChecker.isPathContainedInCurrentUrl(driver, MercuryPaths.ROOT_ARTICLE_PATH);

    category
        .openCuratedContentPage(wikiURL, MercurySubpages.CC_CATEGORY_BLOGS)
        .isBlogIconVisible()
        .clickOnCuratedContentElementByIndex(0)
        .waitForLoadingOverlayToDisappear();
    UrlChecker.isPathContainedInCurrentUrl(driver, MercuryPaths.ROOT_ARTICLE_PATH);

    category
        .openCuratedContentPage(wikiURL, MercurySubpages.CC_CATEGORY_BLOGS)
        .isImageIconVisible()
        .clickOnCuratedContentElementByIndex(0)
        .waitForLoadingOverlayToDisappear();
    UrlChecker.isPathContainedInCurrentUrl(driver, MercuryPaths.ROOT_ARTICLE_PATH);

    category
        .openCuratedContentPage(wikiURL, MercurySubpages.CC_CATEGORY_BLOGS)
        .isVideoIconVisible()
        .clickOnCuratedContentElementByIndex(0)
        .waitForLoadingOverlayToDisappear();
    UrlChecker.isPathContainedInCurrentUrl(driver, MercuryPaths.ROOT_ARTICLE_PATH);
  }

  @Test(groups = "MercuryCuratedNavigationTest_004")
  public void MercuryCuratedNavigationTest_004_navigateThroughDifferentUrl() {
    CuratedContentPageObject section = new CuratedContentPageObject(driver);

    String expectedUrl = urlBuilder.getUrlForPathWithoutWiki(
        Configuration.getWikiName(),
        MercurySubpages.CC_CATEGORY_TEMPLATES
    );
    String testUrl = expectedUrl;
    section.openWikiPage(testUrl);
    section.waitForLoadingOverlayToDisappear();
    Assertion.assertUrlEqualToCurrentUrl(driver, expectedUrl);

    expectedUrl = urlBuilder.getUrlForPathWithoutWiki(
        Configuration.getWikiName(),
        MercurySubpages.CC_SECTION_CATEGORIES
    );

    testUrl = expectedUrl;
    section.openWikiPage(testUrl);
    section.waitForLoadingOverlayToDisappear();
    Assertion.assertUrlEqualToCurrentUrl(driver, expectedUrl);

    expectedUrl = urlBuilder.getUrlForPath(
        Configuration.getWikiName(),
        MercurySubpages.CC_MAIN_PAGE
    );
    testUrl = urlBuilder.getUrlForPathWithoutWiki(
        Configuration.getWikiName(),
        MercurySubpages.CC_EMPTY_CATEGORY
    );

    section.openWikiPage(testUrl);
    section.waitForLoadingOverlayToDisappear();
    Assertion.assertUrlEqualToCurrentUrl(driver, expectedUrl);

    expectedUrl = urlBuilder.getUrlForPath(
        Configuration.getWikiName(),
        MercurySubpages.CC_MAIN_PAGE
    );
    testUrl = urlBuilder.getUrlForPathWithoutWiki(
        Configuration.getWikiName(),
        MercurySubpages.CC_NOT_EXISTING_SECTION
    );

    section.openWikiPage(testUrl);
    section.waitForLoadingOverlayToDisappear();
    MercuryAlertComponentObject mercuryError = new MercuryAlertComponentObject(
        driver,
        MercuryAlertComponentObject.AlertMessage.NOT_EXISTING_SECTION
    );

    Assertion.assertTrue(mercuryError.isAlertMessageVisible());
    Assertion.assertUrlEqualToCurrentUrl(driver, expectedUrl);
  }

  @Test(groups = "MercuryCuratedNavigationTest_005")
  public void MercuryCuratedNavigationTest_005_redirectToExistingArticle() {
    ArticlePageObject article = new ArticlePageObject(driver);
    String redirect = WikiTextContent.REDIRECT +
                      WikiTextContent.INTERNAL_LINK_OPENING +
                      MercurySubpages.CC_REDIRECT_DESTINATION +
                      WikiTextContent.INTERNAL_LINK_CLOSING;

    new ArticleContent().push(redirect, MercurySubpages.CC_REDIRECT_SOURCE_1);

    article.openCuratedMainPage(wikiURL, MercurySubpages.CC_REDIRECT_SOURCE_1);
    Assertion.assertEqualsIgnoreCase(article.getArticleTitle(),
                                     MercurySubpages.CC_REDIRECT_DESTINATION);
  }
}
