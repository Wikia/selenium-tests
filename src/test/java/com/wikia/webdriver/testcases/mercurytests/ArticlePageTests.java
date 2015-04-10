package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.testng.annotations.AfterMethod;
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
  public void MercuryArticleTest_001_LogoSearchTopContributorsFooterElementsAreVisible() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    if (articlePage.isWikiaLogoVisible()) {
      PageObjectLogging.log("Wikia logo", "is visible", true);
    } else {
      PageObjectLogging.log("Wikia logo", "is not visible", false);
      failTest = true;
    }
    if (articlePage.isSearchButtonVisible()) {
      PageObjectLogging.log("Search button", "is visible", true);
    } else {
      PageObjectLogging.log("Search button", "is not visible", false);
      failTest = true;
    }
    if (articlePage.isTopContributorsSectionVisible()) {
      PageObjectLogging.log("Top contributors section", "is visible", true);
    } else {
      PageObjectLogging.log("Top contributors section", "is not visible", false);
      failTest = true;
    }
    if (articlePage.isTopContributorsThumbVisible(0)) {
      PageObjectLogging.log("Top contributors thumb", "is visible", true);
    } else {
      PageObjectLogging.log("Top contributors thumb", "is not visible", false);
      failTest = true;
    }
    if (articlePage.isFooterLogoVisible()) {
      PageObjectLogging.log("Footer Wikia logo", "is visible", true);
    } else {
      PageObjectLogging.log("Footer Wikia logo", "is not visible", false);
      failTest = true;
    }
    for (int i = 0; i < FOOTER_ELEMENTS.length; ++i) {
      if (articlePage.isElementInFooterVisible(FOOTER_ELEMENTS[i], i)) {
        PageObjectLogging.log("Footer link " + FOOTER_ELEMENTS[i], "is visible", true);
      } else {
        PageObjectLogging.log("Footer link " + FOOTER_ELEMENTS[i], "is not visible", false);
        failTest = true;
      }
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
    if (articlePage.isUrlContainingUserPage()) {
      PageObjectLogging.log("Url", "match pattern /wiki/User:", true);
    } else {
      PageObjectLogging.log("Url", "does not match pattern /wiki/User:", false);
      failTest = true;
    }
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
    if (driver.getCurrentUrl().equals(oldUrl)) {
      PageObjectLogging.log("Redirection", "does not work", false);
      failTest = true;
    } else {
      PageObjectLogging.log("Redirection", "works", true);
    }
    base.failTest(failTest);
  }

  // APT04
  @Test(groups = {"MercuryArticleTest_004", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTest_004_ChevronRotation() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    Assertion.assertTrue(articlePage.isChevronCollapsed(), "Chevron isn't collapsed");
    PageObjectLogging.log("Category list", "is collapsed", true);
    articlePage.clickCategoryButton();
    if (articlePage.isChevronCollapsed()) {
      PageObjectLogging.log("Category list", "is collapsed", false);
      failTest = true;
    } else {
      PageObjectLogging.log("Category list", "is expanded", true);
    }
    base.failTest(failTest);
  }
}
