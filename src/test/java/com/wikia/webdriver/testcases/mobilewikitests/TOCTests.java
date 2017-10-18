package com.wikia.webdriver.testcases.mobilewikitests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.old.ArticlePageObject;
import com.wikia.webdriver.elements.mercury.old.TableOfContentPageObject;
import org.testng.annotations.Test;

@Test(groups = "Mercury_TOC")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class TOCTests extends NewTestTemplate {

  private final static int H2_PADDING_TOP = 40;

  private TableOfContentPageObject toc;
  private Navigate navigate;

  private void init() {
    this.toc = new TableOfContentPageObject(driver);
    this.navigate = new Navigate();
  }

  @Test(groups = "MercuryTOCTest_001")
  public void MercuryTOCTest_001_TOCPresence_ListRedirection() {
    init();
    navigate.toPageByPath(MercurySubpages.TOC);

    Assertion.assertTrue(
        toc.isTOCDisplayed(),
        "TOC isn't displayed"
    );

    PageObjectLogging.log(
        "TOC",
        "is displayed",
        true
    );

    boolean result = toc.isTOCAtTheTopOfTheArticle();
    PageObjectLogging.log(
        "TOC position",
        "is under article name at the top of the article content",
        "is not under article name at the top of the article content",
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

    result = toc.isUserMovedToSectionByIndex("2");
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

  @Test(groups = "MercuryTOCTest_002")
  public void MercuryTOCTest_002_NoH2NoTOC() {
    init();
    navigate.toPageByPath(MercurySubpages.TOC_WITHOUT_H2);

    boolean result = !toc.isTOCDisplayed();
    PageObjectLogging.log(
        "TOC",
        "is hidden",
        "is displayed",
        result
    );
  }

  @Test(groups = "MercuryTOCTest_003")
  public void MercuryTOCTest_003_RedirectionToHeaderDirectlyFromLink() {
    init();
    navigate.toPageByPath(MercurySubpages.TOC, "Second_header");

    boolean result = toc.isUserMovedToSectionByIndex("2");
    PageObjectLogging.log(
        "Redirection to header directly from link",
        "works",
        "does not work",
        result
    );
  }

  @Test(groups = "MercuryTOCTest_004")
  public void MercuryTOCTest_004_RedirectionToHeaderFromCurrentPage() {
    init();
    navigate.toPageByPath(MercurySubpages.TOC);

    new ArticlePageObject(driver).clickOnAnchorInContent(0);

    boolean result = toc.isUserMovedToSectionByIndex("2");
    PageObjectLogging.log(
        "Redirection to header from current page",
        "works",
        "does not work",
        result
    );
  }

  @Test(groups = "MercuryTOCTest_005")
  public void MercuryTOCTest_005_RedirectionToHeaderFromOtherPage() {
    init();
    navigate.toPageByPath(MercurySubpages.TOC_WITHOUT_H2);

    ArticlePageObject article = new ArticlePageObject(driver);
    article.clickOnAnchorInContent(0);
    article.waitForPageReload();

    boolean result = toc.isUserMovedToSectionByIndex("2");
    PageObjectLogging.log(
        "Redirection to header from other page",
        "works",
        "does not work",
        result
    );
  }

  @Test(groups = "MercuryTOCTest_006")
  public void MercuryTOCTest_006_TOCPresence_PlacedUnderInfobox() {
    init();
    navigate.toPageByPath(MercurySubpages.TOC_WITH_PORTABLE_INFOBOX);

    boolean result = toc.isTOCBelowFirstAdSlot();
    PageObjectLogging.log(
        "TOC",
        "is right below the first portable infobox",
        "is in a wrong place, as it's not right below the first portable infobox",
        result
    );
  }
}
