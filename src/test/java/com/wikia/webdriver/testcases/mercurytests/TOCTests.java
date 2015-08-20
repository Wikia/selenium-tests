package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.TableOfContentPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ownership Content X-Wing
 */
public class TOCTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_AUTOMATION_TESTING);
  }

  private final static int H2_PADDING_TOP = 40;

  // TOCT01
  @Test(groups = {"MercuryTOCTest_001", "MercuryTOCTests", "Mercury"})
  public void MercuryTOCTest_001_TOCPresence_ListRedirection() {
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    toc.openMercuryArticleByName(wikiURL, MercurySubpages.TOC);

    Assertion.assertTrue(
        toc.isTOCDisplayed(),
        "TOC isn't displayed"
    );

    PageObjectLogging.log(
        "TOC",
        "is displayed",
        true
    );

    boolean result = toc.isTOCUnderArticleName();
    PageObjectLogging.log(
        "TOC position",
        "is under article name",
        "is not under article name",
        result
    );

    Assertion.assertFalse(
        toc.isTOCMenuVisible(),
        "TOC menu is expanded"
    );

    PageObjectLogging.log(
        "TOC menu",
        "is collapsed",
        true
    );

    toc.clickOnTOC();

    Assertion.assertTrue(
        toc.isTOCMenuVisible(),
        "TOC menu is collapsed"
    );

    PageObjectLogging.log(
        "TOC menu",
        "is expanded",
        true
    );

    toc.clickOnTOCListElement(1);

    result = toc.isUserMovedToRightSection(1);
    PageObjectLogging.log(
        "TOC redirection",
        "works",
        "does not work",
        result
    );

    result = toc.isH2PaddingTopMoreThan(1, H2_PADDING_TOP);
    PageObjectLogging.log(
        "Header padding",
        "is correct",
        "is wrong",
        result
    );
  }

  // TOCT02
  @Test(groups = {"MercuryTOCTest_002", "MercuryTOCTests", "Mercury"})
  public void MercuryTOCTest_002_NoH2NoTOC() {
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    toc.openMercuryArticleByName(wikiURL, MercurySubpages.TOC_WITHOUT_H2);

    boolean result = !toc.isTOCDisplayed();
    PageObjectLogging.log(
        "TOC",
        "is hidden",
        "is displayed",
        result
    );
  }

  // TOCT03
  @Test(groups = {"MercuryTOCTest_003", "MercuryTOCTests", "Mercury"})
  public void MercuryTOCTest_003_ListRedirectionDirectlyFromLink() {
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    toc.openMercuryArticleByName(wikiURL, MercurySubpages.TOC, "Second_header");

    boolean result = toc.isUserMovedToRightSection(1);
    PageObjectLogging.log(
        "TOC redirection",
        "works",
        "does not work",
        result
    );
  }
}
