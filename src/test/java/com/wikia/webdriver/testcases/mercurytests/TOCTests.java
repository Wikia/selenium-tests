package com.wikia.webdriver.testcases.mercurytests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.TableOfContentPageObject;

/**
 * @author Tomasz Napieralski
 */
public class TOCTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @BeforeMethod(alwaysRun = true)
  public void optInMercury() {
    MercuryContent.turnOnMercurySkin(driver, wikiURL);
  }

  // TOCT01
  @Test(groups = {"MercuryTOCTests_001", "MercuryTOCTests", "Mercury"})
  public void MercuryTOCTests_001_NoH2NoTOC() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_ARTICLE_WITHOUT_TOC);
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    Assertion.assertFalse(toc.isH2AndTOC(), "TOC is displayed");
  }

  // TOCT02
  @Test(groups = {"MercuryTOCTests_002", "MercuryTOCTests", "Mercury"})
  public void MercuryTOCTests_002_IfH2ThenTOC() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_TOC_TEST_ARTICLE);
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    Assertion.assertTrue(toc.isH2AndTOC(), "TOC isn't displayed");
  }

  // TOCT03
  @Test(groups = {"MercuryTOCTests_003", "MercuryTOCTests", "Mercury"})
  public void MercuryTOCTests_003_TOCUnderArticleName() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_TOC_TEST_ARTICLE);
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    Assertion.assertTrue(toc.isTOCUnderArticleName(), "TOC isn't under article name");
  }

  // TOCT04
  @Test(groups = {"MercuryTOCTests_004", "MercuryTOCTests", "Mercury"})
  public void MercuryTOCTests_004_TapOnElementScrollToSection() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_TOC_TEST_ARTICLE);
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    toc.clickOnTOC();
    Assertion.assertTrue(toc.isUserMovedToRightSection(1), "User wasn't moved to right section");
    Assertion.assertTrue(toc.isH2PaddingTopMoreThan(1, 40), "Header padding top is improper");
  }

  // TOCT05
  @Test(groups = {"MercuryTOCTests_005", "MercuryTOCTests", "Mercury"})
  public void MercuryTOCTests_005_TapOnTOCCollapseOrExpandMenu() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_TOC_TEST_ARTICLE);
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    Assertion.assertFalse(toc.isTOCMenuVisible(), "TOC menu is visible");
    toc.clickOnTOC();
    Assertion.assertTrue(toc.isTOCMenuVisible(), "TOC menu isn't visible");
    toc.clickOnTOC();
    Assertion.assertFalse(toc.isTOCMenuVisible(), "TOC menu is visible");
  }
}
