package com.wikia.webdriver.testcases.mercurytests.old;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
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

  @Test(groups = "mercury_infobox_verifyElementsBeforeAndAfterExpanding")
  public void mercury_infobox_verifyElementsBeforeAndAfterExpanding() {
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

  @Test(groups = "mercury_infobox_expandAndCollapseByButtonClickAndTap")
  public void mercury_infobox_expandAndCollapseByButtonClickAndTap() {
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

  @Test(groups = "mercury_infobox_externalLinkRedirectsToCorrespondingUrl")
  public void mercury_infobox_externalLinkRedirectsToCorrespondingUrl() {
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

  @Test(groups = "mercury_infobox_imagesAndVideosOpenInLightbox")
  public void mercury_infobox_imagesAndVideosOpenInLightbox() {
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

  @Test(groups = "mercury_infobox_heroImageIsCentredAndHasTitleAbove")
  public void mercury_infobox_heroImageIsCentredAndHasTitleAbove() {
    init();
    navigate.toPage(MercurySubpages.INFOBOX_2);

    infobox
        .isTitleAboveImageVisible()
        .isHeroImageCentered();
  }

  @Test(groups = "mercury_infobox_infoboxSizeIsNotAffectedByClickOnImages")
  public void mercury_infobox_infoboxSizeIsNotAffectedByClickOnImages() {
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

  @Test(groups = "mercury_infobox_heroImageIsSquare")
  public void mercury_infobox_heroImageIsSquare() {
    init();
    navigate.toPage(MercurySubpages.INFOBOX_1);

    infobox.isHeroImageSquare();
  }

  @Test(groups = "mercury_infobox_heroImageIsRectangle")
  public void mercury_infobox_heroImageIsRectangle() {
    init();
    navigate.toPage(MercurySubpages.INFOBOX_4);

    infobox.isNotHeroImageSquare();
  }

  @Test(groups = "mercury_infobox_imageCollectionIsVisibleAndChangingImagesWorks")
  public void mercury_infobox_imageCollectionIsVisibleAndChangingImagesWorks() {
    init();
    navigate.toPage(MercurySubpages.INFOBOX_5);

    infobox.isImageInCollectionVisible()
        .clickNextImageArrow()
        .isImageInCollectionVisible()
        .clickNextImageArrow()
        .isImageInCollectionVisible();
  }

  @Test(groups = "mercury_infobox_headerIsNotVisibleInArticleTOC")
  public void mercury_infobox_headerIsNotVisibleInArticleTOC() {
    init();
    navigate.toPage(MercurySubpages.INFOBOX_2);

    toc.clickOnTOC();
    toc.TOCItemNotContainsText(0, infobox.getHeaderName(0));
  }
}
