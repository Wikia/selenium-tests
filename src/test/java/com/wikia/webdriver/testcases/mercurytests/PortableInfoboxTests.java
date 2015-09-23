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

  @Test(groups = {"MercuryPortableInfoboxTest_001", "MercuryPortableInfoboxTests"})
  public void MercuryPortableInfoboxTest_001_VerifyElementsVisible() {
    new ArticlePageObject(driver)
        .openMercuryArticleByName(wikiURL, MercurySubpages.INFOBOX_1);

    new PortableInfoboxObject(driver)
        .isMainImageVisible()
        .isTitleOverImageVisible()
        .isImageInTitleNotVisible()
        .clickExpandButton()
        .verifyDataItemsVisibility()
        .verifyLinksVisibility()
        .verifyReferencesVisibility()
        .areHeadersVisible();
  }

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

  @Test(groups = {"MercuryPortableInfoboxTest_004", "MercuryPortableInfoboxTests"})
  public void MercuryPortableInfoboxTest_004_VerifyTabberVisibility() {
    new ArticlePageObject(driver)
        .openMercuryArticleByName(wikiURL, MercurySubpages.INFOBOX_1);

    new PortableInfoboxObject(driver)
        .isImageInTabberVisible()
        .isImageCaptionInTabberVisible();
  }

  @Test(groups = {"MercuryPortableInfoboxTest_005", "MercuryPortableInfoboxTests"})
  public void MercuryPortableInfoboxTest_005_VerifyLightboxPresence() {
    new ArticlePageObject(driver)
        .openMercuryArticleByName(wikiURL, MercurySubpages.INFOBOX_1);

    new PortableInfoboxObject(driver)
        .clickMainImage()
        .isLightboxOpened()
        .closeLightbox()
        .isTitleOverImageVisible();
  }

  @Test(groups = {"MercuryPortableInfoboxTest_006", "MercuryPortableInfoboxTests"})
  public void MercuryPortableInfoboxTest_006_VerifyListsMargin() {
    new ArticlePageObject(driver)
        .openMercuryArticleByName(wikiURL, MercurySubpages.INFOBOX_1);

    new PortableInfoboxObject(driver)
        .clickExpandButton()
        .areUnorderedListsVisible()
        .areOrderedListsVisible()
        .compareListsAndDataValuesMargin();
  }

  @Test(groups = {"MercuryPortableInfoboxTest_007", "MercuryPortableInfoboxTests"})
  public void MercuryPortableInfoboxTest_007_VideoInDataFields() {
    new ArticlePageObject(driver)
        .openMercuryArticleByName(wikiURL, MercurySubpages.INFOBOX_1);

    new PortableInfoboxObject(driver)
        .clickExpandButton()
        .isVideoVisible()
        .isVideoCaptionVisible()
        .clickVideo()
        .isLightboxOpened();
  }

  @Test(groups = {"MercuryPortableInfoboxTest_008", "MercuryPortableInfoboxTests"})
  public void MercuryPortableInfoboxTest_008_SmallImages() {
    new ArticlePageObject(driver)
        .openMercuryArticleByName(wikiURL, MercurySubpages.INFOBOX_2);

    new PortableInfoboxObject(driver)
        .isTitleAboveImageVisible()
        .isHeroImageCentered();
  }

}