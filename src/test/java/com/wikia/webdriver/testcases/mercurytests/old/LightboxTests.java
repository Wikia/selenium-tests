package com.wikia.webdriver.testcases.mercurytests.old;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.old.GalleryComponentObject;
import com.wikia.webdriver.elements.mercury.old.LightboxComponentObject;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;

import org.testng.annotations.Test;


@Test(groups = "Mercury_Lightbox")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class LightboxTests extends NewTestTemplate {

  private GalleryComponentObject gallery;
  private LightboxComponentObject lightbox;

  private void init() {
    this.gallery = new GalleryComponentObject(driver);
    this.lightbox = new LightboxComponentObject();

    new Navigate().toPage(MercurySubpages.GALLERY);
  }

  @Test(groups = "mercury_lightbox_openAndClose")
  public void mercury_lightbox_openAndClose() {
    init();
    gallery.clickGalleryImage(0);

    Assertion.assertTrue(
        lightbox.isLightboxOpened(),
        "Lightbox is closed"
    );

    PageObjectLogging.log(
        "Lightbox",
        "is opened",
        true
    );

    boolean result = lightbox.isCurrentImageVisible();
    PageObjectLogging.log(
        "Current image",
        "is visible",
        "is not visible",
        result
    );

    lightbox.clickCloseButton();

    result = !lightbox.isLightboxOpened();
    PageObjectLogging.log(
        "Lightbox",
        "is closed",
        "is opened",
        result
    );
  }

  @Test(groups = "mercury_lightbox_UIShowsAndHidesByTapOnCenter")
  public void mercury_lightbox_UIShowsAndHidesByTapOnCenter() {
    init();
    gallery.clickGalleryImage(0);

    Assertion.assertTrue(lightbox.isLightboxHeaderDisplayed(), "Lightbox header isn't displayed");
    Assertion.assertTrue(lightbox.isLightboxFooterDisplayed(), "Lightbox footer isn't displayed");

    lightbox.clickOnImage();

    Assertion.assertFalse(lightbox.isLightboxHeaderDisplayed(), "Lightbox header is displayed");
    Assertion.assertFalse(lightbox.isLightboxFooterDisplayed(), "Lightbox footer is displayed");

    lightbox.clickOnImage();

    Assertion.assertTrue(lightbox.isLightboxHeaderDisplayed(), "Lightbox header isn't displayed");
    Assertion.assertTrue(lightbox.isLightboxFooterDisplayed(), "Lightbox footer isn't displayed");
  }

  @Test
  public void mercury_lightbox_PreserveScrollPositionAfterLightboxOpen() {
    ArticlePage articlePage = new ArticlePage().open("/LightboxTest");
    articlePage.getLightbox().openLightboxImage(0);
    Long startingPosition = new JavascriptActions().getCurrentPosition();
    articlePage.getLightbox().clickCloseButton();
    Long closingPosition = new JavascriptActions().getCurrentPosition();

    Assertion.assertEquals(startingPosition, closingPosition, "Starting scroll position should be "
                                                              + "the same as closing position");
    Assertion.assertTrue(startingPosition > 0 && closingPosition > 0, "Page shoudln't be scrolled"
                                                                      + "to top");
  }
}
