package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
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

  // APT01
  @Test(groups = {"MercuryArticleTests_001", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTests_001_LogoAndSearchButtonAreVisible() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject articlePage =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_CATEGORY_TEST_ARTICLE);
    articlePage.verifyWikiaLogoIsVisible();
    articlePage.verifySearchButtonIsVisible();
  }

  // APT03
  @Test(groups = {"MercuryArticleTests_003", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTests_003_TopContributorsWikiSection() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject articlePage =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_CATEGORY_TEST_ARTICLE);
    articlePage.verifyTopContributorsSectionIsVisible();
    articlePage.verifyTopContributorsThumb();
  }

  // APT06
  @Test(groups = {"MercuryArticleTests_006", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTests_006_SingleLinkedImageRedirect() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject articlePage =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_SINGLE_LINKED_IMAGE);
    articlePage.verifySingleLinkedImageRedirect(0);
  }

  // APT07
  @Test(groups = {"MercuryArticleTests_007", "MercuryArticleTests", "Mercury"})
  public void MercuryArticleTests_007_CanonicalTag() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject articlePage =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_TEST);
    articlePage.verifyCanonicalUrl();
  }
}
