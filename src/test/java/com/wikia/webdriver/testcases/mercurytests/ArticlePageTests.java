package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.openqa.selenium.WebDriverException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

  private static final String[]
      FOOTER_ELEMENTS =
      {"Games", "Movies", "TV", "Comics", "Music", "Books", "Lifestyle", "Full site", "Licensing",
       "Privacy Policy", "Feedback"};

  // APT01
  @Test(groups = {"MercuryArticleTest_001", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTest_001_Logo_Search_TopContributors_FooterElements() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MAIN_PAGE);
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    PageObjectLogging
        .log("Wikia logo", "is visible", "is not visible", articlePage.isWikiaLogoVisible());
    PageObjectLogging
        .log("Search button", "is visible", "is not visible", articlePage.isSearchButtonVisible());
    PageObjectLogging.log("Top contributors section", "is visible", "is not visible",
                          articlePage.isTopContributorsSectionVisible());
    PageObjectLogging.log("Top contributors thumb", "is visible", "is not visible",
                          articlePage.isTopContributorsThumbVisible(0));
    PageObjectLogging.log("Footer Wikia logo", "is visible", "is not visible",
                          articlePage.isFooterLogoVisible());
    for (int i = 0; i < FOOTER_ELEMENTS.length; ++i) {
      PageObjectLogging.log("Footer link " + FOOTER_ELEMENTS[i], "is visible", "is not visible",
                            articlePage.isElementInFooterVisible(FOOTER_ELEMENTS[i], i));
    }
  }

  // APT02
  @Test(groups = {"MercuryArticleTest_002", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTest_002_TapContributorRedirectToUserPage() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MAIN_PAGE);
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    articlePage.clickTopContributor(0);
    PageObjectLogging.log("Url", "match pattern /wiki/User:", "does not match pattern /wiki/User:",
                          articlePage.isUrlContainingUserPage());
  }

  // APT03
  @Test(groups = {"MercuryArticleTest_003", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTest_003_SingleLinkedImageRedirect() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.LINKED_IMAGES);
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    String oldUrl = driver.getCurrentUrl();
    articlePage.clickOnImage(0);
    base.waitForLoadingSpinnerToFinishReloadingPage();
    PageObjectLogging.log("Redirection", "works", "does not work",
                          !driver.getCurrentUrl().equals(oldUrl));
  }

  // APT04
  @Test(groups = {"MercuryArticleTest_004", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTest_004_CategoryListCollapsed_CategoryListExpanded() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MAIN_PAGE);
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    Assertion.assertTrue(articlePage.isChevronCollapsed(), "Chevron isn't collapsed");
    PageObjectLogging.log("Category list", "is collapsed", true);
    articlePage.clickCategoryButton();
    PageObjectLogging.log("Category list", "is expanded", "is collapsed",
                          !articlePage.isChevronCollapsed());
    articlePage.clickOnCategoryListElement(0);
    PageObjectLogging
        .log("Url", "match pattern /wiki/Category:", "does not match pattern /wiki/Category:",
             articlePage.isUrlContainingCategoryPage());
  }

  // APT05
  @Test(groups = {"MercuryArticleTest_005", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTest_005_NavigateToArticleWithColonAndQuestionMark() {
    BasePageObject base = new BasePageObject(driver);
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    ArticlePageObject article = new ArticlePageObject(driver);
    String encodedQuestionMarkUrl, encodedColonUrl;
    try {
      encodedColonUrl = URLEncoder.encode(MercuryArticles.COLON, "UTF-8");
      encodedQuestionMarkUrl = URLEncoder.encode(MercuryArticles.QUESTION_MARK, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new WebDriverException("Wrong URL encoding");
    }
    PageObjectLogging.logWarning("Info", "Accessing article directly through URL");
    base.openMercuryArticleByName(wikiURL, encodedColonUrl);
    PageObjectLogging.log("URL for colon", "is encoded", "is not encoded",
                          driver.getCurrentUrl().contains(encodedColonUrl));
    PageObjectLogging.log("Article title for colon", "is correct", "is not correct",
                          article.getArticleTitle().toLowerCase().equals(
                              MercuryArticles.COLON.toLowerCase()));
    base.openMercuryArticleByName(wikiURL, encodedQuestionMarkUrl);
    PageObjectLogging.log("URL for question mark", "is encoded", "is not encoded",
                          driver.getCurrentUrl().contains(encodedQuestionMarkUrl));
    PageObjectLogging.log("Article title for question mark", "is correct", "is not correct",
                          article.getArticleTitle().toLowerCase().equals(
                              MercuryArticles.QUESTION_MARK.toLowerCase()));
    PageObjectLogging.logWarning("Info", "Accessing article through link in content");
    article.clickOnAnchorInContent(0);
    base.waitForLoadingSpinnerToFinishReloadingPage();
    PageObjectLogging.log("URL for colon", "is not encoded", "is encoded",
                          !driver.getCurrentUrl().contains(encodedColonUrl));
    PageObjectLogging.log("Article title for colon", "is correct", "is not correct",
                          article.getArticleTitle().toLowerCase().equals(
                              MercuryArticles.COLON.toLowerCase()));
    article.clickOnAnchorInContent(0);
    base.waitForLoadingSpinnerToFinishReloadingPage();
    PageObjectLogging.log("URL for question mark", "is encoded", "is not encoded",
                          driver.getCurrentUrl().contains(encodedQuestionMarkUrl));
    PageObjectLogging.log("Article title for question mark", "is correct", "is not correct",
                          article.getArticleTitle().toLowerCase().equals(
                              MercuryArticles.QUESTION_MARK.toLowerCase()));
    PageObjectLogging.logWarning("Info", "Accessing article through link in navigation side");
    nav.clickSearchButton();
    nav.clickNavListElement(4);
    nav.clickNavListElement(7);
    base.waitForLoadingSpinnerToFinishReloadingPage();
    PageObjectLogging.log("URL for colon", "is not encoded", "is encoded",
                          !driver.getCurrentUrl().contains(encodedColonUrl));
    PageObjectLogging.log("Article title for colon", "is correct", "is not correct",
                          article.getArticleTitle().toLowerCase().equals(
                              MercuryArticles.COLON.toLowerCase()));
    nav.clickSearchButton();
    nav.clickNavListElement(4);
    nav.clickNavListElement(6);
    base.waitForLoadingSpinnerToFinishReloadingPage();
    PageObjectLogging.log("URL for question mark", "is encoded", "is not encoded",
                          driver.getCurrentUrl().contains(encodedQuestionMarkUrl));
    PageObjectLogging.log("Article title for question mark", "is correct", "is not correct",
                          article.getArticleTitle().toLowerCase().equals(
                              MercuryArticles.QUESTION_MARK.toLowerCase()));
    PageObjectLogging.logWarning("Info", "Accessing article through link in search result");
    nav.clickSearchButton();
    nav.clickSearchField();
    nav.typeInSearchField(MercuryArticles.COLON.substring(0, 4));
    nav.clickSuggestion(0);
    base.waitForLoadingSpinnerToFinishReloadingPage();
    PageObjectLogging.log("URL for colon", "is encoded", "is not encoded",
                          driver.getCurrentUrl().contains(encodedColonUrl));
    PageObjectLogging.log("Article title for colon", "is correct", "is not correct",
                          article.getArticleTitle().toLowerCase().equals(
                              MercuryArticles.COLON.toLowerCase()));
    nav.clickSearchButton();
    nav.clickSearchField();
    nav.typeInSearchField(MercuryArticles.QUESTION_MARK.substring(0, 4));
    nav.clickSuggestion(0);
    base.waitForLoadingSpinnerToFinishReloadingPage();
    PageObjectLogging.log("URL for question mark", "is encoded", "is not encoded",
                          driver.getCurrentUrl().contains(encodedQuestionMarkUrl));
    PageObjectLogging.log("Article title for question mark", "is correct", "is not correct",
                          article.getArticleTitle().toLowerCase().equals(
                              MercuryArticles.QUESTION_MARK.toLowerCase()));
  }
}
