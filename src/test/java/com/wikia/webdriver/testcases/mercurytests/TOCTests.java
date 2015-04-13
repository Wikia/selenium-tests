package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
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
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  private boolean failTest = false;

  private final static int H2_PADDING_TOP = 40;

  // TOCT01
  @Test(groups = {"MercuryTOCTest_001", "MercuryTOCTests", "Mercury"})
  public void MercuryTOCTest_001_TOCPresence_ListRedirection() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_TOC_TEST_ARTICLE);
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    Assertion.assertTrue(toc.isTOCDisplayed(), "TOC isn't displayed");
    PageObjectLogging.log("TOC", "is displayed", true);
    if (toc.isTOCUnderArticleName()) {
      PageObjectLogging.log("TOC position", "is under article name", true);
    } else {
      PageObjectLogging.log("TOC position", "is not under article name", false);
      failTest = true;
    }
    Assertion.assertFalse(toc.isTOCMenuVisible(), "TOC menu is expanded");
    PageObjectLogging.log("TOC menu", "is collapsed", true);
    toc.clickOnTOC();
    Assertion.assertTrue(toc.isTOCMenuVisible(), "TOC menu is collapsed");
    PageObjectLogging.log("TOC menu", "is expanded", true);
    toc.clickOnTOCListElement(1);
    if (toc.isUserMovedToRightSection(1)) {
      PageObjectLogging.log("TOC redirection", "works", true);
    } else {
      PageObjectLogging.log("TOC redirection", "does not work", false);
      failTest = true;
    }
    if (toc.isH2PaddingTopMoreThan(1, H2_PADDING_TOP)) {
      PageObjectLogging.log("Header padding", "is correct", true);
    } else {
      PageObjectLogging.log("TOC redirection", "is wrong", false);
      failTest = true;
    }
    base.failTest(failTest);
  }

  // TOCT02
  @Test(groups = {"MercuryTOCTest_002", "MercuryTOCTests", "Mercury"})
  public void MercuryTOCTest_002_NoH2NoTOC() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_ARTICLE_WITHOUT_TOC);
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    if (toc.isTOCDisplayed()) {
      PageObjectLogging.log("TOC", "is displayed", false);
      failTest = true;
    } else {
      PageObjectLogging.log("TOC", "is hidden", true);
    }
    base.failTest(failTest);
  }
}
