package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.driverprovider.NewDriverProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.LightboxComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

import org.openqa.selenium.remote.DriverCommand;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 * @ownership: Content - Mercury mobile
 */
public class LightboxTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void optInMercury() {
    BasePageObject.turnOnMercurySkin(driver, wikiURL);
  }

  private static final String EDGE_LEFT = "left";
  private static final String EDGE_RIGHT = "right";

  private static final String ZOOM_METHOD_GESTURE = "gesture";
  private static final String ZOOM_METHOD_TAP = "tap";

  private static final String DIRECTION_LEFT = "left";
  private static final String DIRECTION_RIGHT = "right";
  private static final String DIRECTION_UP = "up";
  private static final String DIRECTION_DOWN = "down";

  // MT01
  @Test(groups = {"MercuryLightboxTests_001", "MercuryLightboxTests", "Mercury"})
  public void MercuryLightboxTests_001_TappingImageOpenLightbox() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_GALLERY_TEST_TWO);
    LightboxComponentObject lightbox = new LightboxComponentObject(driver);
    lightbox.clickGalleryImage(0);
    Assertion.assertTrue(lightbox.isCurrentImageVisible(), "Current image isn't visible");
  }

  // MT02
  @Test(groups = {"MercuryLightboxTests_002", "MercuryLightboxTests", "Mercury"})
  public void MercuryLightboxTests_002_TappingCloseButtonCloseLightbox() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_GALLERY_TEST_TWO);
    LightboxComponentObject lightbox = new LightboxComponentObject(driver);
    lightbox.clickGalleryImage(0);
    lightbox.clickCloseButton();
    Assertion.assertFalse(lightbox.isLightboxOpened(), "Lightbox is opened");
  }

  // MT03
  @Test(groups = {"MercuryLightboxTests_003", "MercuryLightboxTests", "Mercury"})
  public void MercuryLightboxTests_003_SwipeChangeImages() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_GALLERY_TEST_TWO);
    PerformTouchAction touchAction = new PerformTouchAction(driver);
    LightboxComponentObject lightbox = new LightboxComponentObject(driver);
    lightbox.clickGalleryImage(0);
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
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_GALLERY_TEST_TWO);
    PerformTouchAction touchAction = new PerformTouchAction(driver);
    LightboxComponentObject lightbox = new LightboxComponentObject(driver);
    lightbox.clickGalleryImage(0);
    Assertion.assertTrue(lightbox.isZoomingByGestureWorking(touchAction, ZOOM_METHOD_GESTURE),
                         "Zoom by gesture doesn't work");
    lightbox.clickCloseButton();
    lightbox.clickGalleryImage(0);
    Assertion.assertTrue(lightbox.isZoomingByGestureWorking(touchAction, ZOOM_METHOD_TAP),
                         "Zoom by tap doesn't work");
  }

  // MT05
  @Test(groups = {"MercuryLightboxTests_005", "MercuryLightboxTests", "Mercury"})
  public void MercuryLightboxTests_005_TapOnCenterShowHideUI() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_GALLERY_TEST_TWO);
    PerformTouchAction touchAction = new PerformTouchAction(driver);
    LightboxComponentObject lightbox = new LightboxComponentObject(driver);
    lightbox.clickGalleryImage(0);
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
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_GALLERY_TEST_TWO);
    PerformTouchAction touchAction = new PerformTouchAction(driver);
    LightboxComponentObject lightbox = new LightboxComponentObject(driver);
    lightbox.clickGalleryImage(0);
    Assertion.assertTrue(lightbox.isTappingOnImageEdgeChangeImage(touchAction, EDGE_LEFT),
                         "Tapping left edge doesn't change image");
    Assertion.assertTrue(lightbox.isTappingOnImageEdgeChangeImage(touchAction, EDGE_RIGHT),
                         "Tapping right edge doesn't change image");
  }

  // MT07
  @Test(groups = {"MercuryLightboxTests_007", "MercuryLightboxTests", "Mercury"})
  public void MercuryLightboxTests_007_BackButtonCloseLightbox() {
    AndroidDriver mobileDriver = NewDriverProvider.getMobileDriver();
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_GALLERY_TEST_TWO);
    LightboxComponentObject lightbox = new LightboxComponentObject(driver);
    lightbox.clickGalleryImage(0);
    Assertion.assertTrue(lightbox.isLightboxOpened(), "Lightbox is closed");
    mobileDriver.execute(DriverCommand.GO_BACK, null);
    Assertion.assertFalse(lightbox.isLightboxOpened(), "Lightbox is opened");
  }

  // MT08
  @Test(groups = {"MercuryLightboxTests_008", "MercuryLightboxTests", "Mercury"})
  public void MercuryLightboxTests_008_MovingImageAfterZooming() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_GALLERY_TEST_TWO);
    PerformTouchAction touchAction = new PerformTouchAction(driver);
    LightboxComponentObject lightbox = new LightboxComponentObject(driver);
    lightbox.clickGalleryImage(0);
    Assertion.assertTrue(lightbox.isImageMovedToDirectionAfterZoomIn(touchAction, DIRECTION_LEFT),
                         "Moving left doesn't work");
    lightbox.clickCloseButton();
    lightbox.clickGalleryImage(0);
    Assertion.assertTrue(lightbox.isImageMovedToDirectionAfterZoomIn(touchAction, DIRECTION_RIGHT),
                         "Moving right doesn't work");
    lightbox.clickCloseButton();
    lightbox.clickGalleryImage(0);
    Assertion.assertTrue(lightbox.isImageMovedToDirectionAfterZoomIn(touchAction, DIRECTION_UP),
                         "Moving up doesn't work");
    lightbox.clickCloseButton();
    lightbox.clickGalleryImage(0);
    Assertion.assertTrue(lightbox.isImageMovedToDirectionAfterZoomIn(touchAction, DIRECTION_DOWN),
                         "Moving down doesn't work");
  }
}
