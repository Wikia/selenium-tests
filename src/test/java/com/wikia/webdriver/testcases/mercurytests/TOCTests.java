package com.wikia.webdriver.testcases.mercurytests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
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
    toc.verifyNoH2NoTOC();
  }
  
  // TOCT02
  @Test(groups = {"MercuryTOCTests_002", "MercuryTOCTests", "Mercury"})
  public void MercuryTOCTests_002_IfH2ThenTOC() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_TOC_TEST_ARTICLE);
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    toc.verifyIfH2ThenTOC();
  }
  
  // TOCT03
  @Test(groups = {"MercuryTOCTests_003", "MercuryTOCTests", "Mercury"})
  public void MercuryTOCTests_003_TOCUnderArticleName() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_TOC_TEST_ARTICLE);
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    toc.verifyTOCUnderArticleName();
  }
  
  // TOCT04
  @Test(groups = {"MercuryTOCTests_004", "MercuryTOCTests", "Mercury"})
  public void MercuryTOCTests_004_TapOnElementScrollToSection() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_TOC_TEST_ARTICLE);
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    toc.verifyTapOnElementScrollToSection(1);
  }
  
  // TOCT05
  @Test(groups = {"MercuryTOCTests_005", "MercuryTOCTests", "Mercury"})
  public void MercuryTOCTests_005_TapOnTOCCollapseOrExpandMenu() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_TOC_TEST_ARTICLE);
    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    toc.verifyTapOnTOCCollapseOrExpandMenu();
  }
}
