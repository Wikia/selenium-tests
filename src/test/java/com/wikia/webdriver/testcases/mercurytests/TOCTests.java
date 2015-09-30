package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.TableOfContentPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ownership Content X-Wing
 */
@Test(groups = {"MercuryTOCTests", "Mercury"})
public class TOCTests extends NewTestTemplate {

  private final static int H2_PADDING_TOP = 40;

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_AUTOMATION_TESTING);
  }

  // TOCT01
  @Test(groups = "MercuryTOCTest_001")
  public void MercuryTOCTest_001_TOCPresence_ListRedirection() {
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    toc.openMercuryArticleByName(wikiURL, MercurySubpages.TOC);

    Assertion.assertTrue(
        toc.isTOCDisplayed(),
        "TOC isn't displayed"
    );

    LOG.result(
        "TOC",
        "is displayed",
        true
    );

    boolean result = toc.isTOCUnderArticleName();
    LOG.log(
        "TOC position",
        "is under article name",
        "is not under article name",
        result
    );

    Assertion.assertFalse(
        toc.isTOCMenuVisible(),
        "TOC menu is expanded"
    );

    LOG.result(
        "TOC menu",
        "is collapsed",
        true
    );

    toc.clickOnTOC();

    Assertion.assertTrue(
        toc.isTOCMenuVisible(),
        "TOC menu is collapsed"
    );

    LOG.result(
        "TOC menu",
        "is expanded",
        true
    );

    toc.clickOnTOCListElement(1);

    result = toc.isUserMovedToRightSection(1);
    LOG.log(
        "TOC redirection",
        "works",
        "does not work",
        result
    );

    result = toc.isH2PaddingTopMoreThan(1, H2_PADDING_TOP);
    LOG.log(
        "Header padding",
        "is correct",
        "is wrong",
        result
    );
  }

  // TOCT02
  @Test(groups = "MercuryTOCTest_002")
  public void MercuryTOCTest_002_NoH2NoTOC() {
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    toc.openMercuryArticleByName(wikiURL, MercurySubpages.TOC_WITHOUT_H2);

    boolean result = !toc.isTOCDisplayed();
    LOG.log(
        "TOC",
        "is hidden",
        "is displayed",
        result
    );
  }

  // TOCT03
  @Test(groups = "MercuryTOCTest_003")
  public void MercuryTOCTest_003_RedirectionToHeaderDirectlyFromLink() {
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    toc.openMercuryArticleByName(wikiURL, MercurySubpages.TOC, "Second_header");

    boolean result = toc.isUserMovedToRightSection(1);
    LOG.log(
        "Redirection to header directly from link",
        "works",
        "does not work",
        result
    );
  }

  // TOCT04
  @Test(groups = "MercuryTOCTest_004")
  public void MercuryTOCTest_004_RedirectionToHeaderFromCurrentPage() {
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    toc.openMercuryArticleByName(wikiURL, MercurySubpages.TOC);
    new ArticlePageObject(driver).clickOnAnchorInContent(0);

    boolean result = toc.isUserMovedToRightSection(1);
    LOG.log(
        "Redirection to header from current page",
        "works",
        "does not work",
        result
    );
  }

  // TOCT05
  @Test(groups = "MercuryTOCTest_005")
  public void MercuryTOCTest_005_RedirectionToHeaderFromOtherPage() {
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    toc.openMercuryArticleByName(wikiURL, MercurySubpages.TOC_WITHOUT_H2);
    new ArticlePageObject(driver).clickOnAnchorInContent(0);
    toc.waitForLoadingSpinnerToFinish();

    boolean result = toc.isUserMovedToRightSection(1);
    LOG.log(
        "Redirection to header from other page",
        "works",
        "does not work",
        result
    );
  }
}
