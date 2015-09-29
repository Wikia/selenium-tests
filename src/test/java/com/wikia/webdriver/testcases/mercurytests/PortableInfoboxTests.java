package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
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
  public void MercuryPortableInfoboxTest_001_ElementsVisibility() {
    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
    infobox.openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_1);

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
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPortableInfoboxTest_002_CollapsingMethods() {
    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
    infobox.openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_1);

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
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPortableInfoboxTest_003_ExternalLinkRedirection() {
    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
    infobox.openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_1);

    String externalLinkName = infobox
        .clickExpandButton()
        .areLinksVisible()
        .getExternalLinkName(0);

    String externalURL = infobox
        .clickExternalLink(0)
        .getUrlFromExternalLinkaAfterPageIsLoaded();

    infobox.isExternalLinkLabelInURL(externalLinkName, externalURL);
  }

  @Test(groups = "MercuryPortableInfoboxTest_004")
  @Execute(onWikia = "mercuryautomationtesting")
  @RelatedIssue(issueID = "DAT-3085")
  public void MercuryPortableInfoboxTest_004_ImageAndVideoOpensInLightbox() {
    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
    infobox.openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_1);

    // Check image
    infobox
        .clickMainImage()
        .isLightboxOpened()
        .closeLightbox()
        .isTitleOverImageVisible();

    // Check video
    infobox
        // TODO: uncomment after related issue is done
        //.clickExpandButton()
        .clickVideo()
        .isLightboxOpened();
  }

  @Test(groups = "MercuryPortableInfoboxTest_005")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPortableInfoboxTest_005_SmallImages() {
    PortableInfoboxObject infobox = new PortableInfoboxObject(driver);
    infobox.openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.INFOBOX_2);

    infobox
        .isTitleAboveImageVisible()
        .isHeroImageCentered();
  }
}
