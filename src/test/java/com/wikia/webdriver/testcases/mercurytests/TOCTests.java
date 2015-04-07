package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.TableOfContentPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 * @ownership: Content - Mercury mobile
 */
public class TOCTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void optInMercury() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    BasePageObject.turnOnMercurySkin(driver, wikiURL);
  }

  private final static int H2_PADDING_TOP = 40;

  // TOCT01
  @Test(groups = {"MercuryTOCTest_001", "MercuryTOCTests", "Mercury"})
  public void MercuryTOCTest_001_TOCPresenceListRedirection() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_TOC_TEST_ARTICLE);
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    Assertion.assertTrue(toc.isH2AndTOC(), "TOC isn't displayed");
    Assertion.assertTrue(toc.isTOCUnderArticleName(), "TOC isn't under article name");
    Assertion.assertFalse(toc.isTOCMenuVisible(), "TOC menu is visible");
    toc.clickOnTOC();
    Assertion.assertTrue(toc.isTOCMenuVisible(), "TOC menu isn't visible");
    toc.clickOnTOC();
    Assertion.assertFalse(toc.isTOCMenuVisible(), "TOC menu is visible");
    toc.clickOnTOC();
    Assertion.assertTrue(toc.isUserMovedToRightSection(1), "User wasn't moved to right section");
    Assertion.assertTrue(toc.isH2PaddingTopMoreThan(1, H2_PADDING_TOP),
                         "Header padding top is improper");
  }

  // TOCT02
  @Test(groups = {"MercuryTOCTest_002", "MercuryTOCTests", "Mercury"})
  public void MercuryTOCTest_002_NoH2NoTOC() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_ARTICLE_WITHOUT_TOC);
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    Assertion.assertFalse(toc.isH2AndTOC(), "TOC is displayed");
  }
}
