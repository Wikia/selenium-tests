package com.wikia.webdriver.testcases.mercurytests.old;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.old.PortableInfoboxObject;
import com.wikia.webdriver.elements.mercury.old.TableOfContentPageObject;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class PortableInfoboxTests extends NewTestTemplate {

  private PortableInfoboxObject infobox;
  private TableOfContentPageObject toc;
  private Navigate navigate;

  private void init() {
    this.infobox = new PortableInfoboxObject(driver);
    this.toc = new TableOfContentPageObject(driver);
    this.navigate = new Navigate(driver);
  }

  @Test(groups = "MercuryPortableInfoboxTest_001")
  public void MercuryPortableInfoboxTest_001_ElementsVisibility() {
    init();
    navigate.toPage(MercurySubpages.INFOBOX_1);

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
    init();
    navigate.toPage(MercurySubpages.INFOBOX_1);

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
    init();
    navigate.toPage(MercurySubpages.INFOBOX_1);

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
    init();
    navigate.toPage(MercurySubpages.INFOBOX_1);

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
    init();
    navigate.toPage(MercurySubpages.INFOBOX_2);

    infobox
        .isTitleAboveImageVisible()
        .isHeroImageCentered();
  }

  @Test(groups = "MercuryPortableInfoboxTest_006")
  public void MercuryPortableInfoboxTest_006_DifferentClickTargets() {
    init();
    navigate.toPage(MercurySubpages.INFOBOX_3);

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
  public void MercuryPortableInfoboxTest_007_HeroImageTall() {
    init();
    navigate.toPage(MercurySubpages.INFOBOX_1);

    infobox.isHeroImageSquare();
  }

  @Test(groups = "MercuryPortableInfoboxTest_008")
  public void MercuryPortableInfoboxTest_008_HeroImageWide() {
    init();
    navigate.toPage(MercurySubpages.INFOBOX_4);

    infobox.isNotHeroImageSquare();
  }

  @Test(groups = "MercuryPortableInfoboxTest_009")
  public void MercuryPortableInfoboxTest_009_ImageCollection() {
    init();
    navigate.toPage(MercurySubpages.INFOBOX_5);

    infobox.isImageInCollectionVisible()
        .clickNextImageArrow()
        .isImageInCollectionVisible()
        .clickNextImageArrow()
        .isImageInCollectionVisible();
  }

  @Test(groups = "MercuryPortableInfoboxTest_010")
  public void MercuryPortableInfoboxTest_010_HeadersInTOC() {
    init();
    navigate.toPage(MercurySubpages.INFOBOX_2);

    toc.clickOnTOC();
    toc.TOCItemNotContainsText(0, infobox.getHeaderName(0));
  }
}
