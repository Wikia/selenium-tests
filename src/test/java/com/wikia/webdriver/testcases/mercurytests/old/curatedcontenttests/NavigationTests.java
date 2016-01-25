package com.wikia.webdriver.testcases.mercurytests.old.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercuryPaths;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.contentpatterns.WikiTextContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.UrlChecker;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.Loading;
import com.wikia.webdriver.elements.mercury.old.MercuryAlertComponentObject;
import com.wikia.webdriver.elements.mercury.old.ArticlePageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.CuratedContentPageObject;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_CC)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class NavigationTests extends NewTestTemplate {

  private CuratedContentPageObject curatedContent;
  private Navigate navigate;
  private Loading loading;
  private ArticlePageObject article;
  private MercuryAlertComponentObject mercuryError;

  private void init() {
    this.curatedContent = new CuratedContentPageObject(driver);
    this.navigate = new Navigate(driver);
    this.loading = new Loading(driver);
    this.article = new ArticlePageObject(driver);
    this.mercuryError = new MercuryAlertComponentObject(
        driver,
        MercuryAlertComponentObject.AlertMessage.NOT_EXISTING_SECTION
    );
  }

  @Test(groups = "MercuryCuratedNavigationTest_001")
  @RelatedIssue(issueID = "XW-687", comment = "java.lang.IndexOutOfBoundsException: Index: 1, Size: 0")
  public void MercuryCuratedNavigationTest_001_navigateThroughCategory() {
    init();

    navigate.toPage("/wiki/" + MercurySubpages.CC_MAIN_PAGE);

    curatedContent.clickOnCuratedContentElementByIndex(1);
    loading.handleAsyncPageReload();

    curatedContent
        .isTitleVisible()
        .isLinkToMainPageVisible()
        .isSectionVisible()
        .isCuratedContentItemVisibleByIndex(1);

    String sectionTitle = curatedContent.getTitle();
    String expectedUrlPath = MercuryPaths.ROOT_PATH_CATEGORY + sectionTitle;
    UrlChecker.isPathContainedInCurrentUrl(driver, expectedUrlPath);

    String previousUrl = driver.getCurrentUrl();
    curatedContent.navigateToMainPage();
    String nextUrl = driver.getCurrentUrl();
    UrlChecker.isPathContainedInCurrentUrl(driver, MercuryPaths.ROOT_PATH);

    driver.navigate().back();
    Assertion.assertUrlEqualToCurrentUrl(driver, previousUrl);

    driver.navigate().forward();
    Assertion.assertUrlEqualToCurrentUrl(driver, nextUrl);
  }

  @Test(groups = "MercuryCuratedNavigationTest_002")
  public void MercuryCuratedNavigationTest_002_navigateThroughSection() {
    init();

    navigate.toPage("/wiki/" + MercurySubpages.CC_MAIN_PAGE);

    curatedContent.clickOnCuratedContentElementByIndex(0);
    loading.handleAsyncPageReload();

    curatedContent
        .isTitleVisible()
        .isLinkToMainPageVisible()
        .isSectionVisible()
        .isCuratedContentItemVisibleByIndex(1);

    UrlChecker.isPathContainedInCurrentUrl(
        driver, MercuryPaths.ROOT_PATH_SECTION + curatedContent.getTitle());
  }

  @Test(groups = "MercuryCuratedNavigationTest_003")
  @RelatedIssue(issueID = "XW-640")
  public void MercuryCuratedNavigationTest_003_navigateThroughNamespaces() {
    init();

    navigate.toPage("/" + MercurySubpages.CC_CATEGORY_ARTICLES);
    curatedContent
        .isArticleIconVisible()
        .clickOnCuratedContentElementByIndex(0);
    loading.handleAsyncPageReload();
    UrlChecker.isPathContainedInCurrentUrl(driver, MercuryPaths.ROOT_ARTICLE_PATH);

    navigate.toPage("/" + MercurySubpages.CC_CATEGORY_BLOGS);
    curatedContent
        .isBlogIconVisible()
        .clickOnCuratedContentElementByIndex(0);
    loading.handleAsyncPageReload();
    UrlChecker.isPathContainedInCurrentUrl(driver, MercuryPaths.ROOT_ARTICLE_PATH);

    navigate.toPage("/" + MercurySubpages.CC_CATEGORY_BLOGS);
    curatedContent
        .isImageIconVisible()
        .clickOnCuratedContentElementByIndex(0);
    loading.handleAsyncPageReload();
    UrlChecker.isPathContainedInCurrentUrl(driver, MercuryPaths.ROOT_ARTICLE_PATH);

    navigate.toPage("/" + MercurySubpages.CC_CATEGORY_BLOGS);
    curatedContent
        .isVideoIconVisible()
        .clickOnCuratedContentElementByIndex(0);
    loading.handleAsyncPageReload();
    UrlChecker.isPathContainedInCurrentUrl(driver, MercuryPaths.ROOT_ARTICLE_PATH);
  }

  @Test(groups = "MercuryCuratedNavigationTest_004")
  public void MercuryCuratedNavigationTest_004_navigateThroughDifferentUrl() {
    init();

    String expectedUrl = urlBuilder.getUrlForPathWithoutWiki(
        Configuration.getWikiName(),
        MercurySubpages.CC_CATEGORY_TEMPLATES
    );
    navigate.toPage("/" + MercurySubpages.CC_CATEGORY_TEMPLATES);
    loading.handleAsyncPageReload();
    Assertion.assertTrue(driver.getCurrentUrl().contains(expectedUrl));

    expectedUrl = urlBuilder.getUrlForPathWithoutWiki(
        Configuration.getWikiName(),
        MercurySubpages.CC_SECTION_CATEGORIES
    );
    navigate.toPage("/" + MercurySubpages.CC_SECTION_CATEGORIES);
    loading.handleAsyncPageReload();
    Assertion.assertTrue(driver.getCurrentUrl().contains(expectedUrl));

    expectedUrl = urlBuilder.getUrlForPath(
        Configuration.getWikiName(),
        MercurySubpages.CC_MAIN_PAGE
    );
    navigate.toPage("/" + MercurySubpages.CC_EMPTY_CATEGORY);
    curatedContent.waitForLoadingOverlayToDisappear();
    Assertion.assertTrue(driver.getCurrentUrl().contains(expectedUrl));

    expectedUrl = urlBuilder.getUrlForPath(
        Configuration.getWikiName(),
        MercurySubpages.CC_MAIN_PAGE
    );
    navigate.toPage("/" + MercurySubpages.CC_NOT_EXISTING_SECTION);
    curatedContent.waitForLoadingOverlayToDisappear();

    Assertion.assertTrue(mercuryError.isAlertMessageVisible());
    Assertion.assertTrue(driver.getCurrentUrl().contains(expectedUrl));
  }

  @Test(groups = "MercuryCuratedNavigationTest_005")
  public void MercuryCuratedNavigationTest_005_redirectToExistingArticle() {
    init();

    String redirect = WikiTextContent.REDIRECT +
                      WikiTextContent.INTERNAL_LINK_OPENING +
                      MercurySubpages.CC_REDIRECT_DESTINATION +
                      WikiTextContent.INTERNAL_LINK_CLOSING;

    new ArticleContent().push(redirect, MercurySubpages.CC_REDIRECT_SOURCE_1);

    navigate.toPage("/wiki/" + MercurySubpages.CC_REDIRECT_SOURCE_1);

    Assertion.assertEqualsIgnoreCase(article.getArticleTitle(),
                                     MercurySubpages.CC_REDIRECT_DESTINATION);
  }
}
