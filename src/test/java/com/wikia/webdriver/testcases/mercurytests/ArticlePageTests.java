package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 * @ownership: Content - Mercury mobile
 */
public class ArticlePageTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  private boolean failTest = false;

  private static final String[]
      FOOTER_ELEMENTS =
      {"Games", "Movies", "TV", "Comics", "Music", "Books", "Lifestyle", "Full site", "Licensing",
       "Privacy Policy", "Feedback"};

  // APT01
  @Test(groups = {"MercuryArticleTest_001", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTest_001_Logo_Search_TopContributors_FooterElements() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    PageObjectLogging.log("Wikia logo", "is visible", "is not visible", articlePage.isWikiaLogoVisible());
    if(! articlePage.isWikiaLogoVisible()) failTest = true;
    PageObjectLogging.log("Search button", "is visible", "is not visible", articlePage.isSearchButtonVisible());
    if (! articlePage.isSearchButtonVisible()) failTest = true;
    PageObjectLogging.log("Top contributors section", "is visible", "is not visible", articlePage.isTopContributorsSectionVisible());
    if (! articlePage.isTopContributorsSectionVisible()) failTest = true;
    PageObjectLogging.log("Top contributors thumb", "is visible", "is not visible", articlePage.isTopContributorsThumbVisible(0));
    if (! articlePage.isTopContributorsThumbVisible(0)) failTest = true;
    PageObjectLogging.log("Footer Wikia logo", "is visible", "is not visible", articlePage.isFooterLogoVisible());
    if (! articlePage.isFooterLogoVisible()) failTest = true;
    for (int i = 0; i < FOOTER_ELEMENTS.length; ++i) {
      PageObjectLogging.log("Footer link " + FOOTER_ELEMENTS[i], "is visible", "is not visible", articlePage.isElementInFooterVisible(FOOTER_ELEMENTS[i], i));
      if (! articlePage.isElementInFooterVisible(FOOTER_ELEMENTS[i], i)) failTest = true;
    }
    base.failTest(failTest);
  }

  // APT02
  @Test(groups = {"MercuryArticleTest_002", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTest_002_TapContributorRedirectToUserPage() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    articlePage.clickTopContributor(0);
    PageObjectLogging.log("Url", "match pattern /wiki/User:", "does not match pattern /wiki/User:", articlePage.isUrlContainingUserPage());
    if (! articlePage.isUrlContainingUserPage()) failTest = true;
    base.failTest(failTest);
  }

  // APT03
  @Test(groups = {"MercuryArticleTest_003", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTest_003_SingleLinkedImageRedirect() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_SINGLE_LINKED_IMAGE);
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    String oldUrl = driver.getCurrentUrl();
    articlePage.clickOnImage(0);
    base.waitForLoadingSpinnerToFinishReloadingPage();
    PageObjectLogging.log("Redirection", "works", "does not work", ! driver.getCurrentUrl().equals(oldUrl));
    if (driver.getCurrentUrl().equals(oldUrl)) failTest = true;
    base.failTest(failTest);
  }

  // APT04
  @Test(groups = {"MercuryArticleTest_004", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTest_004_CategoryListCollapsed_CategoryListExpanded() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    Assertion.assertTrue(articlePage.isChevronCollapsed(), "Chevron isn't collapsed");
    PageObjectLogging.log("Category list", "is collapsed", true);
    articlePage.clickCategoryButton();
    PageObjectLogging.log("Category list", "is expanded", "is collapsed", ! articlePage.isChevronCollapsed());
    if (articlePage.isChevronCollapsed()) failTest = true;
    base.failTest(failTest);
  }
}
