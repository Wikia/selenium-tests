package com.wikia.webdriver.testcases.mercurytests;

import io.appium.java_client.android.AndroidDriver;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.driverprovider.NewDriverProvider;

import org.openqa.selenium.remote.DriverCommand;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.LightBoxMercuryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

/**
 * @ownership: Mobile Web
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 */
public class LightboxTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @BeforeMethod(alwaysRun = true)
  public void optInMercury() {
    MercuryContent.turnOnMercurySkin(driver, wikiURL);
  }

  // MT01
  @Test(groups = {"MercuryLightboxTests_001", "MercuryLightboxTests", "Mercury"})
  public void MercuryLightboxTests_001_TappingImageOpenLightbox() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_TWO);
    LightBoxMercuryComponentObject lightbox = new LightBoxMercuryComponentObject(driver);
    lightbox.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
    Assertion.assertTrue(lightbox.isCurrentImageVisible(), "Current image isn't visible");
  }

  // MT02
  @Test(groups = {"MercuryLightboxTests_002", "MercuryLightboxTests", "Mercury"})
  public void MercuryLightboxTests_002_TappingCloseButtonCloseLightbox() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_TWO);
    LightBoxMercuryComponentObject lightbox = new LightBoxMercuryComponentObject(driver);
    lightbox.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
    lightbox.clickCloseButton();
    Assertion.assertFalse(lightbox.isLightboxOpened(), "Lightbox is opened");
  }

  // MT03
  @Test(groups = {"MercuryLightboxTests_003", "MercuryLightboxTests", "Mercury"})
  public void MercuryLightboxTests_003_SwipeChangeImages() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_TWO);
    PerformTouchAction touchAction = new PerformTouchAction(driver);
    LightBoxMercuryComponentObject lightbox = new LightBoxMercuryComponentObject(driver);
    lightbox.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
    Assertion.assertTrue(lightbox.isDifferentImageAfterSwiping(touchAction,
                                                               PerformTouchAction.DIRECTION_LEFT,
                                                               10), "Swiping to left doesn't work");
    Assertion.assertTrue(
        lightbox.isDifferentImageAfterSwiping(touchAction, PerformTouchAction.DIRECTION_RIGHT, 10),
        "Swiping to right doesn't work");
  }

  // MT04
  @Test(groups = {"MercuryLightboxTests_004", "MercuryLightboxTests", "Mercury"})
  public void MercuryLightboxTests_004_ZoomThroughPanninAndDoubleTapping() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_TWO);
    PerformTouchAction touchAction = new PerformTouchAction(driver);
    LightBoxMercuryComponentObject lightbox = new LightBoxMercuryComponentObject(driver);
    lightbox.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
    Assertion.assertTrue(lightbox.isZoomingByGestureWorking(touchAction,
                                                            LightBoxMercuryComponentObject.ZOOM_METHOD_GESTURE),
                         "Zoom by gesture doesn't work");
    lightbox.clickCloseButton();
    lightbox.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
    Assertion.assertTrue(lightbox.isZoomingByGestureWorking(touchAction,
                                                            LightBoxMercuryComponentObject.ZOOM_METHOD_TAP),
                         "Zoom by tap doesn't work");
  }

  // MT05
  @Test(groups = {"MercuryLightboxTests_005", "MercuryLightboxTests", "Mercury"})
  public void MercuryLightboxTests_005_TapOnCenterShowHideUI() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_TWO);
    PerformTouchAction touchAction = new PerformTouchAction(driver);
    LightBoxMercuryComponentObject lightbox = new LightBoxMercuryComponentObject(driver);
    lightbox.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
    Assertion.assertTrue(lightbox.isLightboxHeaderDisplayed(), "Lightbox header isn't displayed");
    Assertion.assertTrue(lightbox.isLightboxFooterDisplayed(), "Lightbox footer isn't displayed");
    int duration = 500;
    int waitAfter = 5000;
    touchAction.tapOnPointXY(50, 50, duration, waitAfter);
    Assertion.assertFalse(lightbox.isLightboxHeaderDisplayed(), "Lightbox header is displayed");
    Assertion.assertFalse(lightbox.isLightboxFooterDisplayed(), "Lightbox footer is displayed");
    touchAction.tapOnPointXY(50, 50, duration, waitAfter);
    Assertion.assertTrue(lightbox.isLightboxHeaderDisplayed(), "Lightbox header isn't displayed");
    Assertion.assertTrue(lightbox.isLightboxFooterDisplayed(), "Lightbox footer isn't displayed");
  }

  // MT06
  @Test(groups = {"MercuryLightboxTests_006", "MercuryLightboxTests", "Mercury"})
  public void MercuryLightboxTests_006_TapOnLeftRightSpaceNearEdgeChangeImage() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_TWO);
    PerformTouchAction touchAction = new PerformTouchAction(driver);
    LightBoxMercuryComponentObject lightbox = new LightBoxMercuryComponentObject(driver);
    lightbox.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
    Assertion.assertTrue(lightbox.isTappingOnImageEdgeChangeImage(touchAction,
                                                                  LightBoxMercuryComponentObject.EDGE_LEFT),
                         "Tapping left edge doesn't change image");
    Assertion.assertTrue(lightbox.isTappingOnImageEdgeChangeImage(touchAction,
                                                                  LightBoxMercuryComponentObject.EDGE_RIGHT),
                         "Tapping right edge doesn't change image");
  }

  // MT07
  @Test(groups = {"MercuryLightboxTests_007", "MercuryLightboxTests", "Mercury"})
  public void MercuryLightboxTests_007_BackButtonCloseLightbox() {
    AndroidDriver mobileDriver = NewDriverProvider.getMobileDriver();
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_TWO);
    LightBoxMercuryComponentObject lightbox = new LightBoxMercuryComponentObject(driver);
    lightbox.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
    Assertion.assertTrue(lightbox.isLightboxOpened(), "Lightbox is closed");
    mobileDriver.execute(DriverCommand.GO_BACK, null);
    Assertion.assertFalse(lightbox.isLightboxOpened(), "Lightbox is opened");
  }

  // MT08
  @Test(groups = {"MercuryLightboxTests_008", "MercuryLightboxTests", "Mercury"})
  public void MercuryLightboxTests_008_MovingImageAfterZooming() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_TWO);
    PerformTouchAction touchAction = new PerformTouchAction(driver);
    LightBoxMercuryComponentObject lightbox = new LightBoxMercuryComponentObject(driver);
    lightbox.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
    Assertion.assertTrue(lightbox.isImageMovedToDirectionAfterZoomIn(touchAction,
                                                                     LightBoxMercuryComponentObject.DIRECTION_LEFT),
                         "Moving left doesn't work");
    lightbox.clickCloseButton();
    lightbox.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
    Assertion.assertTrue(lightbox.isImageMovedToDirectionAfterZoomIn(touchAction,
                                                                     LightBoxMercuryComponentObject.DIRECTION_RIGHT),
                         "Moving right doesn't work");
    lightbox.clickCloseButton();
    lightbox.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
    Assertion.assertTrue(lightbox.isImageMovedToDirectionAfterZoomIn(touchAction,
                                                                     LightBoxMercuryComponentObject.DIRECTION_UP),
                         "Moving up doesn't work");
    lightbox.clickCloseButton();
    lightbox.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
    Assertion.assertTrue(lightbox.isImageMovedToDirectionAfterZoomIn(touchAction,
                                                                     LightBoxMercuryComponentObject.DIRECTION_DOWN), "Moving down doesn't work");
  }
}
