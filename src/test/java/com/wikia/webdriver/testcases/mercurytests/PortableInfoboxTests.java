package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PortableInfoboxObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.TableOfContentPageObject;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class PortableInfoboxTests extends NewTestTemplate {

  @Test(groups = "MercuryPortableInfoboxTest_001")
  public void MercuryPortableInfoboxTest_001_ElementsVisibility() {
    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
    infobox.openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_1);

    // Before infobox expanding
    infobox
        .isMainImageVisible()
        .isTitleOverImageVisible()
        .isImageInTitleNotVisible()
        .isImageInTabberVisible()
        .isImageCaptionInTabberVisible();

    // After infobox expanding
    infobox
        .clickExpandButton()
        .isVideoVisible()
        .isVideoCaptionVisible()
        .areDataLabelsVisible()
        .areDataValuesVisible()
        .areLinksVisible()
        .areReferencesVisible()
        .areHeadersVisible()
        .areUnorderedListsVisible()
        .areOrderedListsVisible()
        .areUnorderedListAndDataValuesMarginEqual()
        .areOrderedListAndDataValuesMarginEqual();
  }

  @Test(groups = "MercuryPortableInfoboxTest_002")
  public void MercuryPortableInfoboxTest_002_CollapsingMethods() {
    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
    infobox.openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_1);

    // expand by clicking button
    infobox
        .isInfoboxCollapsed()
        .clickExpandButton()
        .isInfoboxExpanded()
        .clickExpandButton()
        .isInfoboxCollapsed();

    // expand by tapping Infobox's content
    infobox
        .tapInfoboxContent()
        .isInfoboxExpanded()
        .tapInfoboxContent()
        .isInfoboxCollapsed();
  }

  @Test(groups = "MercuryPortableInfoboxTest_003")
  public void MercuryPortableInfoboxTest_003_ExternalLinkRedirection() {
    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
    infobox.openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_1);

    String externalLinkName = infobox
        .clickExpandButton()
        .areLinksVisible()
        .getExternalLinkName(0);

    String externalURL = infobox
        .clickExternalLink(0)
        .getUrlFromExternalLinkAfterPageIsLoaded();

    infobox.isExternalLinkLabelInURL(externalLinkName, externalURL);
  }

  @Test(groups = "MercuryPortableInfoboxTest_004")
  public void MercuryPortableInfoboxTest_004_ImageAndVideoOpensInLightbox() {
    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
    infobox.openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_1);

    // Check image
    infobox
        .clickMainImage()
        .isLightboxOpened()
        .closeLightbox()
        .isTitleOverImageVisible();

    // Check video
    infobox
        .clickExpandButton()
        .clickVideo()
        .isLightboxOpened();
  }

  @Test(groups = "MercuryPortableInfoboxTest_005")
  public void MercuryPortableInfoboxTest_005_SmallImages() {
    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
    infobox.openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_2);

    infobox
        .isTitleAboveImageVisible()
        .isHeroImageCentered();
  }

  @Test(groups = "MercuryPortableInfoboxTest_006")
  public void MercuryPortableInfoboxTest_006_DifferentClickTargets() {
    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
    infobox.openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_3);

    // Check click on main image
    infobox
        .clickMainImage()
        .isLightboxOpened()
        .closeLightbox()
        .isInfoboxCollapsed();

    // Check click on "View more" button in gallery in infobox
    infobox
        .clickExpandButton()
        .clickGalleryButton(0)
        .isInfoboxExpanded();
  }

  @Test(groups = "MercuryPortableInfoboxTest_007")
  public void MercuryPortableInfoboxTest_007_HeroImageCropping() {
    PortableInfoboxObject infoboxTallImage = new PortableInfoboxObject(driver);
    infoboxTallImage.openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_1);
    infoboxTallImage.isHeroImageSquare();

    PortableInfoboxObject infoboxWideImage = new PortableInfoboxObject(driver);
    infoboxWideImage.openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_4);
    infoboxWideImage.isNotHeroImageSquare();
  }

  @Test(groups = "MercuryPortableInfoboxTest_008")
  public void MercuryPortableInfoboxTest_008_ImageCollection() {
    PortableInfoboxObject info = new PortableInfoboxObject(driver);
    info.openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_5);

    info.isImageInCollectionVisible()
        .clickNextImageArrow()
        .isImageInCollectionVisible()
        .clickNextImageArrow()
        .isImageInCollectionVisible();
  }

  @Test(groups = "MercuryPortableInfoboxTest_009")
  public void MercuryPortableInfoboxTest_009_HeadersInTOC() {
    PortableInfoboxObject info = new PortableInfoboxObject(driver);
    info.openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_2);

    String headerName = info.getHeaderName(0);

    TableOfContentPageObject toc = new TableOfContentPageObject(driver);
    toc.clickOnTOC();
    toc.hasTOCitemText(0, headerName);
  }

}
