package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PortableInfoboxObject;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

/**
 * @ownshership: Content West-Wing
 */

@Test(groups = {"MercuryPortableInfoboxTests"})
public class PortableInfoboxTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_AUTOMATION_TESTING);
  }

  //TC01
  @Test(groups = {"MercuryPortableInfoboxTest_001", "MercuryPortableInfoboxTests"})
  public void MercuryPortableInfoboxTest_001_VerifyElementsVisible() {
    new ArticlePageObject(driver)
        .openMercuryArticleByName(wikiURL, MercurySubpages.INFOBOX_1);

    new PortableInfoboxObject(driver)
        .isMainImageVisible()
        .isTitleVisible()
        .clickExpandButton()
        .verifyDataItemsVisibility()
        .verifyLinksVisibility()
        .verifyReferencesVisibility();
  }

  //TC02
  @Test(groups = {"MercuryPortableInfoboxTest_002", "MercuryPortableInfoboxTests"})
  public void MercuryPortableInfoboxTest_002_VerifyCollapsing() {
    new ArticlePageObject(driver)
        .openMercuryArticleByName(wikiURL, MercurySubpages.INFOBOX_1);

    new PortableInfoboxObject(driver)
        //expand by clicking button
        .isInfoboxCollapsed()
        .clickExpandButton()
        .isInfoboxExpanded()
        .clickExpandButton()
        .isInfoboxCollapsed()
        //expand by tapping Infobox's content
        .tapInfoboxContent()
        .isInfoboxExpanded()
        .tapInfoboxContent()
        .isInfoboxCollapsed();
  }

  //TC03
  @Test(groups = {"MercuryPortableInfoboxTest_003", "MercuryPortableInfoboxTests"})
  public void MercuryPortableInfoboxTest_003_VerifyExternalRedirecting() {
    new ArticlePageObject(driver)
        .openMercuryArticleByName(wikiURL, MercurySubpages.INFOBOX_1);

    PortableInfoboxObject info = new PortableInfoboxObject(driver);

    String externalLinkName = info
        .clickExpandButton()
        .verifyLinksVisibility()
        .getExternalLinkName(0);

    String externalURL = info
        .clickExternalLink(0)
        .getUrlFromExternalLinkaAfterPageIsLoaded();

    info.verifyExternalLinkNameAndURL(externalLinkName, externalURL);
  }

  //TC04
  @Test(groups = {"MercuryPortableInfoboxTest_004", "MercuryPortableInfoboxTests"})
  public void MercuryPortableInfoboxTest_004_VerifyTabberVisibility() {
    new ArticlePageObject(driver)
        .openMercuryArticleByName(wikiURL, MercurySubpages.INFOBOX_1);

    new PortableInfoboxObject(driver)
        .isImageInTabberVisible()
        .isImageCaptionInTabberVisible();
  }

  //TC05
  @Test(groups = {"MercuryPortableInfoboxTest_005", "MercuryPortableInfoboxTests"})
  public void MercuryPortableInfoboxTest_005_VerifyLightboxPresence() {
    new ArticlePageObject(driver)
        .openMercuryArticleByName(wikiURL, MercurySubpages.INFOBOX_1);

    new PortableInfoboxObject(driver)
        
  }

}