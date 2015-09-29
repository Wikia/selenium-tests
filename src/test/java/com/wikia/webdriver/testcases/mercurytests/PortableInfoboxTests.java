package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PortableInfoboxObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ownshership: Content West-Wing
 */
@Test(groups = {"MercuryPortableInfoboxTests", "Mercury"})
public class PortableInfoboxTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  @Test(groups = "MercuryPortableInfoboxTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPortableInfoboxTest_001_VerifyElementsVisible() {
    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
    infobox.openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_1);

    infobox
        .isMainImageVisible()
        .isTitleOverImageVisible()
        .isImageInTitleNotVisible()
        .clickExpandButton()
        .verifyDataItemsVisibility()
        .verifyLinksVisibility()
        .verifyReferencesVisibility()
        .areHeadersVisible();
  }

  @Test(groups = "MercuryPortableInfoboxTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPortableInfoboxTest_002_VerifyCollapsing() {
    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
    infobox.openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_1);

    //expand by clicking button
    infobox
        .isInfoboxCollapsed()
        .clickExpandButton()
        .isInfoboxExpanded()
        .clickExpandButton()
        .isInfoboxCollapsed();

    //expand by tapping Infobox's content
    infobox
        .tapInfoboxContent()
        .isInfoboxExpanded()
        .tapInfoboxContent()
        .isInfoboxCollapsed();
  }

  @Test(groups = "MercuryPortableInfoboxTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPortableInfoboxTest_003_VerifyExternalRedirecting() {
    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
    infobox.openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_1);

    String externalLinkName = infobox
        .clickExpandButton()
        .verifyLinksVisibility()
        .getExternalLinkName(0);

    String externalURL = infobox
        .clickExternalLink(0)
        .getUrlFromExternalLinkaAfterPageIsLoaded();

    infobox.verifyExternalLinkNameAndURL(externalLinkName, externalURL);
  }

  @Test(groups = "MercuryPortableInfoboxTest_004")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPortableInfoboxTest_004_VerifyTabberVisibility() {
    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
    infobox.openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_1);

    infobox
        .isImageInTabberVisible()
        .isImageCaptionInTabberVisible();
  }

  @Test(groups = "MercuryPortableInfoboxTest_005")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPortableInfoboxTest_005_VerifyLightboxPresence() {
    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
    infobox.openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_1);

    infobox
        .clickMainImage()
        .isLightboxOpened()
        .closeLightbox()
        .isTitleOverImageVisible();
  }

  @Test(groups = "MercuryPortableInfoboxTest_006")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPortableInfoboxTest_006_VerifyListsMargin() {
    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
    infobox.openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_1);

    infobox
        .clickExpandButton()
        .areUnorderedListsVisible()
        .areOrderedListsVisible()
        .compareListsAndDataValuesMargin();
  }

  @Test(groups = "MercuryPortableInfoboxTest_007")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPortableInfoboxTest_007_VideoInDataFields() {
    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
    infobox.openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_1);

    infobox
        .clickExpandButton()
        .isVideoVisible()
        .isVideoCaptionVisible()
        .clickVideo()
        .isLightboxOpened();
  }

  @Test(groups = "MercuryPortableInfoboxTest_008")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPortableInfoboxTest_008_SmallImages() {
    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
    infobox.openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_2);

    infobox
        .isTitleAboveImageVisible()
        .isHeroImageCentered();
  }
}
