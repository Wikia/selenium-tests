package com.wikia.webdriver.testcases.mobile;

import com.wikia.webdriver.common.contentpatterns.MobileSubpages;
import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.communities.mobile.components.PortableInfoboxObject;
import com.wikia.webdriver.elements.communities.mobile.pages.TableOfContentPageObject;

import org.testng.annotations.Test;

/*
 * Portable Infobox is a special case table with information and medias about some character or entity.
 * More info: https://community.wikia.com/wiki/Help:Infoboxes
 */

@Test(groups = "Mercury_Infobox")
@Execute(onWikia = MobileWikis.MERCURY_AUTOMATION_TESTING)
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
    this.navigate = new Navigate();
  }

  @Test(groups = "infoboxElementsBeforeAndAfterExpanding")
  public void infoboxElementsBeforeAndAfterExpanding() {
    init();
    navigate.toPage(MobileSubpages.INFOBOX_1);

    // Before infobox expanding
    infobox
        .isTitleNotVisible()
        .isImageInTitleNotVisible()
        .isImageInTabberVisible();

    // After infobox expanding
    infobox
        .clickExpandButton()
        .isVideoVisible()
        .areDataLabelsVisible()
        .areDataValuesVisible()
        .areLinksVisible()
        .areReferencesVisible()
        .areHeadersVisible()
        .areUnorderedListsVisible()
        .areOrderedListsVisible()
        .verifyDataValueMargin()
        .verifyListMargin();
  }

  @Test(groups = "expandAndCollapseByButtonClickAndTap")
  public void expandAndCollapseByButtonClickAndTap() {
    init();
    navigate.toPage(MobileSubpages.INFOBOX_1);

    // expand by clicking button
    infobox
        .isInfoboxCollapsed()
        .clickExpandButton()
        .isInfoboxExpanded()
        .clickExpandButton()
        .isInfoboxCollapsed();

    // tapping on infobox content - doesn't expand infobox
    infobox
        .tapInfoboxContent()
        .isInfoboxCollapsed();
  }

  @Test(groups = "externalLinkRedirectsToCorrespondingUrl")
  public void externalLinkRedirectsToCorrespondingUrl() {
    init();
    navigate.toPage(MobileSubpages.INFOBOX_1);

    String externalLinkName = infobox
        .clickExpandButton()
        .areLinksVisible()
        .getExternalLinkName(0);

    String externalURL = infobox
        .clickExternalLink(0)
        .getUrlFromExternalLinkAfterPageIsLoaded();

    infobox.isExternalLinkLabelInURL(externalLinkName, externalURL);
  }

  @Test(groups = "imagesAndVideosOpenInLightbox")
  public void imagesAndVideosOpenInLightbox() {
    init();
    navigate.toPage(MobileSubpages.INFOBOX_1);

    // Check image
    infobox
        .clickOnImageInInfobox()
        .isLightboxOpened()
        .closeLightbox();

    // Check video
    infobox
        .clickExpandButton()
        .clickVideo()
        .isLightboxOpened();
  }

  @Test(groups = "infoboxSizeIsNotAffectedByClickOnImages")
  public void infoboxSizeIsNotAffectedByClickOnImages() {
    init();
    navigate.toPage(MobileSubpages.INFOBOX_3);

    infobox
        .clickGalleryImage(0)
        .isLightboxOpened()
        .closeLightbox()
        .isInfoboxCollapsed()
        .clickExpandButton();
  }

  @Test(groups = "imageCollectionIsVisibleAndChangingImagesWorks")
  public void imageCollectionIsVisibleAndChangingImagesWorks() {
    init();
    navigate.toPage(MobileSubpages.INFOBOX_5);

    infobox.isImageInCollectionVisible();
  }

  @Test(groups = "headerIsNotVisibleInArticleTOC", enabled = false)
  public void headerIsNotVisibleInArticleTOC() {
    init();
    navigate.toPage(MobileSubpages.INFOBOX_2);

    toc.clickOnTOC();
    toc.TOCItemNotContainsText(0, infobox.getHeaderName(0));
  }
}
