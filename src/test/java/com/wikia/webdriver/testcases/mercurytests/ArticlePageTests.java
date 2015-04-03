package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.core.Assertion;
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
  public void optInMercury() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    BasePageObject.turnOnMercurySkin(driver, wikiURL);
  }

  private static final String[]
      FOOTER_ELEMENTS =
      {"Games", "Movies", "TV", "Comics", "Music", "Books", "Lifestyle", "Full site", "Licensing",
       "Privacy Policy", "Feedback"};

  // APT0-4,3,1
  @Test(groups = {"MercuryArticleTests_001", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTests_001_LogoSearchFooterAreVisible() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    Assertion.assertTrue(articlePage.isWikiaLogoVisible(), "Wikia logo isn't visible");
    Assertion.assertTrue(articlePage.isSearchButtonVisible(), "Search button isn't visible");
    Assertion.assertTrue(articlePage.isTopContributorsSectionVisible(),
                         "Top contributors section isn't visible");
    Assertion.assertTrue(articlePage.isTopContributorsThumbVisible(0),
                         "Top contributors thumb isn't visible");
    Assertion.assertTrue(articlePage.isFooterLogoVisible(), "Wikia footer logo isn't displayed");
    for (int i = 0; i < FOOTER_ELEMENTS.length; ++i) {
      Assertion.assertTrue(articlePage.isElementInFooterVisible(FOOTER_ELEMENTS[i], i),
                           FOOTER_ELEMENTS[i] + " isn't displayed");
    }
  }

  // APT01
  @Test(groups = {"MercuryArticleTests_001", "MercuryArticleTests", "Mercury"}, enabled = false)
  public void MercuryArticleTests_001_LogoAndSearchButtonAreVisible() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    Assertion.assertTrue(articlePage.isWikiaLogoVisible(), "Wikia logo isn't visible");
    Assertion.assertTrue(articlePage.isSearchButtonVisible(), "Search button isn't visible");
  }

  // APT02
  @Test(groups = {"MercuryArticleTests_002", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTests_002_TapContributorRedirectToUserPage() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    articlePage.clickTopContributor(0);
    Assertion.assertTrue(articlePage.isUrlContainingUserPage(), "Url doesn't contain user page");
  }

  // APT03
  @Test(groups = {"MercuryArticleTests_003", "MercuryArticleTests", "Mercury"}, enabled = false)
  public void MercuryArticleTests_003_TopContributorsWikiSection() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    Assertion.assertTrue(articlePage.isTopContributorsSectionVisible(),
                         "Top contributors section isn't visible");
    Assertion.assertTrue(articlePage.isTopContributorsThumbVisible(0),
                         "Top contributors thumb isn't visible");
  }

  // APT04
  @Test(groups = {"MercuryArticleTests_004", "MercuryArticleTests", "Mercury"}, enabled = false)
  public void MercuryArticleTests_004_FooterElements() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    Assertion.assertTrue(articlePage.isFooterLogoVisible(), "Wikia footer logo isn't displayed");
    for (int i = 0; i < FOOTER_ELEMENTS.length; ++i) {
      Assertion.assertTrue(articlePage.isElementInFooterVisible(FOOTER_ELEMENTS[i], i),
                           FOOTER_ELEMENTS[i] + " isn't displayed");
    }
  }

  // APT05
  @Test(groups = {"MercuryArticleTests_005", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTests_005_SingleLinkedImageRedirect() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_SINGLE_LINKED_IMAGE);
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    Assertion.assertTrue(articlePage.isSingleLinkedImageRedirectionWorking(0),
                         "Redirection doesn't work");
  }

  // APT06
  @Test(groups = {"MercuryArticleTests_006", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTests_006_ChevronRotation() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    Assertion.assertTrue(articlePage.isChevronCollapsed(), "Chevron isn't collapsed");
    articlePage.clickCategoryButton();
    Assertion.assertFalse(articlePage.isChevronCollapsed(), "Chevron is collapsed");
  }
}
