package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 * @ownership: Mobile Web
 */

public class ArticlePageTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @BeforeMethod(alwaysRun = true)
  public void optInMercury() {
    MercuryContent.turnOnMercurySkin(driver, wikiURL);
  }

  private final String[] FOOTER_ELEMENTS = {"Games", "Movies", "TV", "Comics", "Music", "Books", "Lifestyle", "Full site", "Licensing", "Privacy Policy", "Feedback"};

  // APT01
  @Test(groups = {"MercuryArticleTests_001", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTests_001_LogoAndSearchButtonAreVisible() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject articlePage =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_CATEGORY_TEST_ARTICLE);
    Assertion.assertTrue(articlePage.isWikiaLogoVisible(), "Wikia logo isn't visible");
    Assertion.assertTrue(articlePage.isSearchButtonVisible(), "Search button isn't visible");
  }

  // APT03
  @Test(groups = {"MercuryArticleTests_003", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTests_003_TopContributorsWikiSection() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject articlePage =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_CATEGORY_TEST_ARTICLE);
    Assertion.assertTrue(articlePage.isTopContributorsSectionVisible(), "Top contributors section isn't visible");
    Assertion.assertTrue(articlePage.isTopContributorsThumbVisible(0), "Top contributors thumb isn't visible");
  }

  // APT04
  @Test(groups = {"MercuryArticleTests_004", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTests_004_FooterElements() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject articlePage =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_TEST);
    Assertion.assertTrue(articlePage.isFooterLogoVisible(), "Wikia footer logo isn't displayed");
    for (int i = 0; i < FOOTER_ELEMENTS.length; ++i) {
      Assertion.assertTrue(articlePage.isElementInFooterVisible(FOOTER_ELEMENTS[i], i), FOOTER_ELEMENTS[i] + " isn't displayed");
    }
  }
  
  // APT05
  @Test(groups = {"MercuryArticleTests_005", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTests_005_SingleLinkedImageRedirect() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject articlePage =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_SINGLE_LINKED_IMAGE);
    Assertion.assertTrue(articlePage.isSingleLinkedImageRedirectionWorking(0), "Redirection doesn't work");
  }

  // APT06
  @Test(groups = {"MercuryArticleTests_006", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTests_006_CanonicalTag() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject articlePage =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_TEST);
    Assertion.assertTrue(articlePage.isUrlCanonical(), "Url isn't canonical");
  }
  
  // APT07
  @Test(groups = {"MercuryArticleTests_007", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTests_007_ChevronRotation() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject articlePage =
        base.openMercuryArticleByName(wikiURL, "");
    Assertion.assertTrue(articlePage.isChevronCollapsed(), "Chevron isn't collapsed");
    articlePage.clickCategoryButton();
    Assertion.assertFalse(articlePage.isChevronCollapsed(), "Chevron is collapsed");
  }
  
  // APT08
  @Test(groups = {"MercuryArticleTests_008", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTests_008_TapContributorRedirectToUserPage() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject articlePage =
        base.openMercuryArticleByName(wikiURL, "");
    articlePage.clickTopContributor(0);
    Assertion.assertTrue(articlePage.isUrlContainingUserPage(), "Url doesn't contain user page");
  }
}
