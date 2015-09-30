package com.wikia.webdriver.testcases.mercurytests;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriverException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.ArticlePageObject;

/**
 * @ownership Content X-Wing
 */
public class ArticlePageTests extends NewTestTemplate {

  private static final String[] FOOTER_ELEMENTS = {"Games", "Movies", "TV", "Comics", "Music",
      "Books", "Lifestyle", "Full site", "Licensing", "Privacy Policy", "Feedback"};

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_AUTOMATION_TESTING);
  }

  // APT01
  @Test(groups = {"MercuryArticleTest_001", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTest_001_Logo_Search_TopContributors_FooterElements() {
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    articlePage.openMercuryArticleByName(wikiURL, MercurySubpages.MAIN_PAGE);

    boolean result = articlePage.isWikiaLogoVisible();
    LOG.log("Wikia logo", "is visible", "is not visible", result);

    result = articlePage.isSearchButtonVisible();
    LOG.log("Search button", "is visible", "is not visible", result);

    result = articlePage.isTopContributorsSectionVisible();
    LOG.log("Top contributors section", "is visible", "is not visible", result);

    result = articlePage.isTopContributorsThumbVisible(0);
    LOG.log("Top contributors thumb", "is visible", "is not visible", result);

    result = articlePage.isFooterLogoVisible();
    LOG.log("Footer Wikia logo", "is visible", "is not visible", result);

    for (int i = 0; i < FOOTER_ELEMENTS.length; ++i) {
      result = articlePage.isElementInFooterVisible(FOOTER_ELEMENTS[i], i);
      LOG.log("Footer link " + FOOTER_ELEMENTS[i], "is visible", "is not visible", result);
    }
  }

  // APT02
  @Test(groups = {"MercuryArticleTest_002", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTest_002_TapContributorRedirectToUserPage() {
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    articlePage.openMercuryArticleByName(wikiURL, MercurySubpages.MAIN_PAGE);

    articlePage.clickTopContributor(0);

    boolean result = articlePage.isUrlContainingUserPage();
    LOG.log("Url", "match pattern /wiki/User:", "does not match pattern /wiki/User:", result);
  }

  // APT03
  @Test(groups = {"MercuryArticleTest_003", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTest_003_SingleLinkedImageRedirect() {
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    articlePage.openMercuryArticleByName(wikiURL, MercurySubpages.LINKED_IMAGES);

    String oldUrl = driver.getCurrentUrl();
    articlePage.clickOnImage(0);
    articlePage.waitForLoadingSpinnerToFinish();

    boolean result = !driver.getCurrentUrl().equals(oldUrl);
    LOG.log("Redirection", "works", "does not work", result);
  }

  // APT04
  @Test(groups = {"MercuryArticleTest_004", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTest_004_CategoryListCollapsed_CategoryListExpanded() {
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    articlePage.openMercuryArticleByName(wikiURL, MercurySubpages.MAIN_PAGE);

    Assertion.assertTrue(articlePage.isChevronCollapsed(), "Chevron isn't collapsed");

    LOG.result("Category list", "is collapsed", true);

    articlePage.clickCategoryButton();

    boolean result = !articlePage.isChevronCollapsed();
    LOG.log("Category list", "is expanded", "is collapsed", result);

    articlePage.clickOnCategoryListElement(0);

    result = articlePage.isUrlContainingCategoryPage();
    LOG.log("Url", "match pattern /wiki/Category:", "does not match pattern /wiki/Category:",
        result);
  }

  // APT05
  @Test(groups = {"MercuryArticleTest_005", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTest_005_NavigateToArticleWithColonAndQuestionMark() {
    ArticlePageObject article = new ArticlePageObject(driver);
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);

    String encodedQuestionMarkUrl, encodedColonUrl;

    try {
      encodedColonUrl = URLEncoder.encode(MercurySubpages.COLON, "UTF-8");
      encodedQuestionMarkUrl = URLEncoder.encode(MercurySubpages.QUESTION_MARK, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new WebDriverException("Wrong URL encoding");
    }

    LOG.warning("Info", "Accessing article directly through URL");

    article.openMercuryArticleByName(wikiURL, encodedColonUrl);

    boolean result = driver.getCurrentUrl().contains(encodedColonUrl);
    LOG.log("URL for colon", "is encoded", "is not encoded", result);

    result = article.getArticleTitle().toLowerCase().equals(MercurySubpages.COLON.toLowerCase());
    LOG.log("Article title for colon", "is correct", "is not correct", result);

    article.openMercuryArticleByName(wikiURL, encodedQuestionMarkUrl);

    result = driver.getCurrentUrl().contains(encodedQuestionMarkUrl);
    LOG.log("URL for question mark", "is encoded", "is not encoded", result);

    result =
        article.getArticleTitle().toLowerCase().equals(MercurySubpages.QUESTION_MARK.toLowerCase());
    LOG.log("Article title for question mark", "is correct", "is not correct", result);

    LOG.warning("Info", "Accessing article through link in content");

    article.clickOnAnchorInContent(0);
    article.waitForLoadingSpinnerToFinish();

    result = !driver.getCurrentUrl().contains(encodedColonUrl);
    LOG.log("URL for colon", "is not encoded", "is encoded", result);

    result = article.getArticleTitle().toLowerCase().equals(MercurySubpages.COLON.toLowerCase());
    LOG.log("Article title for colon", "is correct", "is not correct", result);

    article.clickOnAnchorInContent(0);
    article.waitForLoadingSpinnerToFinish();

    result = driver.getCurrentUrl().contains(encodedQuestionMarkUrl);
    LOG.log("URL for question mark", "is encoded", "is not encoded", result);

    result =
        article.getArticleTitle().toLowerCase().equals(MercurySubpages.QUESTION_MARK.toLowerCase());
    LOG.log("Article title for question mark", "is correct", "is not correct", result);

    LOG.warning("Info", "Accessing article through link in navigation side");

    nav.clickSearchButton();
    nav.clickNavListElement(4);
    nav.clickNavListElement(7);
    article.waitForLoadingSpinnerToFinish();

    result = !driver.getCurrentUrl().contains(encodedColonUrl);
    LOG.log("URL for colon", "is not encoded", "is encoded", result);

    result = article.getArticleTitle().toLowerCase().equals(MercurySubpages.COLON.toLowerCase());
    LOG.log("Article title for colon", "is correct", "is not correct", result);

    nav.clickSearchButton();
    nav.clickNavListElement(4);
    nav.clickNavListElement(6);
    article.waitForLoadingSpinnerToFinish();

    result = driver.getCurrentUrl().contains(encodedQuestionMarkUrl);
    LOG.log("URL for question mark", "is encoded", "is not encoded", result);

    result =
        article.getArticleTitle().toLowerCase().equals(MercurySubpages.QUESTION_MARK.toLowerCase());
    LOG.log("Article title for question mark", "is correct", "is not correct", result);

    LOG.warning("Info", "Accessing article through link in search result");

    nav.clickSearchButton();
    nav.clickSearchField();
    nav.typeInSearchField(MercurySubpages.COLON.substring(0, 4));
    nav.clickSuggestion(0);
    article.waitForLoadingSpinnerToFinish();

    result = driver.getCurrentUrl().contains(encodedColonUrl);
    LOG.log("URL for colon", "is encoded", "is not encoded", result);

    result = article.getArticleTitle().toLowerCase().equals(MercurySubpages.COLON.toLowerCase());
    LOG.log("Article title for colon", "is correct", "is not correct", result);

    nav.clickSearchButton();
    nav.clickSearchField();
    nav.typeInSearchField(MercurySubpages.QUESTION_MARK.substring(0, 4));
    nav.clickSuggestion(0);
    article.waitForLoadingSpinnerToFinish();

    result = driver.getCurrentUrl().contains(encodedQuestionMarkUrl);
    LOG.log("URL for question mark", "is encoded", "is not encoded", result);

    result =
        article.getArticleTitle().toLowerCase().equals(MercurySubpages.QUESTION_MARK.toLowerCase());
    LOG.log("Article title for question mark", "is correct", "is not correct", result);
  }
}
