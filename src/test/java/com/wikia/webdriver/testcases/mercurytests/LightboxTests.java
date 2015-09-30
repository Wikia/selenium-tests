package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.imageutilities.ImageComparison;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.common.driverprovider.NewDriverProvider;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.LightboxComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DriverCommand;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @ownership Content X-Wing
 */
public class LightboxTests extends NewTestTemplate {

  private static final String DIRECTION_LEFT = "left";
  private static final String DIRECTION_RIGHT = "right";
  private static final String DIRECTION_UP = "up";
  private static final String DIRECTION_DOWN = "down";
  private static final double ACCURACY = 0.83;

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_AUTOMATION_TESTING);
  }

  // MT01
  @Test(groups = {"MercuryLightboxTest_001", "MercuryLightboxTests", "Mercury"})
  public void MercuryLightboxTest_001_Open_Close() {
    LightboxComponentObject lightbox = new LightboxComponentObject(driver);
    lightbox.openMercuryArticleByName(wikiURL, MercurySubpages.GALLERY);

    lightbox.clickGalleryImage(0);

    Assertion.assertTrue(
        lightbox.isLightboxOpened(),
        "Lightbox is closed"
    );

    LOG.logResult(
        "Lightbox",
        "is opened",
        true
    );

    boolean result = lightbox.isCurrentImageVisible();
    LOG.log(
        "Current image",
        "is visible",
        "is not visible",
        result
    );

    lightbox.clickCloseButton();

    result = !lightbox.isLightboxOpened();
    LOG.log(
        "Lightbox",
        "is closed",
        "is opened",
        result
    );
  }

  // MT02
  @Test(groups = {"MercuryLightboxTest_002", "MercuryLightboxTests", "Mercury"})
  public void MercuryLightboxTest_002_TapOnEdgesChangeImages_SwipeChangeImages() {
    LightboxComponentObject lightbox = new LightboxComponentObject(driver);
    lightbox.openMercuryArticleByName(wikiURL, MercurySubpages.GALLERY);
    PerformTouchAction touchAction = new PerformTouchAction(driver);

    lightbox.clickGalleryImage(0);

    Assertion.assertTrue(
        lightbox.isCurrentImageVisible(),
        "Image is not visible"
    );

    LOG.logResult(
        "Current image",
        "is visible",
        true
    );

    String currentImageSrc = lightbox.getCurrentImagePath();
    touchAction.tapOnPointXY(25, 50, 500, 5000);
    String nextImageSrc = lightbox.getCurrentImagePath();

    boolean result = !currentImageSrc.equals(nextImageSrc);
    LOG.log(
        "Change image by tap left edge",
        "works",
        "doesn't work",
        result
    );

    currentImageSrc = lightbox.getCurrentImagePath();
    touchAction.tapOnPointXY(75, 50, 500, 5000);
    nextImageSrc = lightbox.getCurrentImagePath();

    result = !currentImageSrc.equals(nextImageSrc);
    LOG.log(
        "Change image by tap right edge",
        "works",
        "doesn't work",
        result
    );

    lightbox.clickCloseButton();
    lightbox.clickGalleryImage(0);

    Assertion.assertTrue(
        lightbox.isCurrentImageVisible(),
        "Image is not visible"
    );

    LOG.logResult(
        "Current image",
        "is visible",
        true
    );

    currentImageSrc = lightbox.getCurrentImagePath();
    boolean imageChanged = false;

    for (int i = 0; i < 10; ++i) {
      touchAction.swipeFromPointToPoint(70, 50, 20, 50, 300, 5000);
      nextImageSrc = lightbox.getCurrentImagePath();
      if (!nextImageSrc.contains(currentImageSrc)) {
        imageChanged = true;
        break;
      }
    }

    result = imageChanged;
    LOG.log(
        "Change image by swipe left",
        "works",
        "does not work",
        result
    );

    currentImageSrc = lightbox.getCurrentImagePath();
    imageChanged = false;

    for (int i = 0; i < 10; ++i) {
      touchAction.swipeFromPointToPoint(20, 50, 70, 50, 300, 5000);
      nextImageSrc = lightbox.getCurrentImagePath();
      if (!nextImageSrc.contains(currentImageSrc)) {
        imageChanged = true;
        break;
      }
    }

    result = imageChanged;
    LOG.log(
        "Change image by swipe right",
        "works",
        "does not work",
        result
    );
  }

  // MT03
  @Test(groups = {"MercuryLightboxTest_003", "MercuryLightboxTests", "Mercury"})
  public void MercuryLightboxTest_003_ZoomByGesture_ZoomByDoubleTap() {
    LightboxComponentObject lightbox = new LightboxComponentObject(driver);
    lightbox.openMercuryArticleByName(wikiURL, MercurySubpages.GALLERY);
    PerformTouchAction touchAction = new PerformTouchAction(driver);

    lightbox.clickGalleryImage(0);

    Assertion.assertTrue(
        lightbox.isCurrentImageVisible(),
        "Image is not visible"
    );

    LOG.logResult(
        "Current image",
        "is visible",
        true
    );

    File beforeZooming = new Shooter().capturePage(driver);
    touchAction.zoomInOutPointXY(50, 50, 50, 100, PerformTouchAction.ZOOM_WAY_IN, 3000);
    File afterZooming = new Shooter().capturePage(driver);

    boolean result = !new ImageComparison().areFilesTheSame(beforeZooming, afterZooming, ACCURACY);
    LOG.log(
        "Zooming in by gesture",
        "works",
        "does not work",
        result
    );

    touchAction.zoomInOutPointXY(50, 50, 50, 140, PerformTouchAction.ZOOM_WAY_OUT, 3000);
    afterZooming = new Shooter().capturePage(driver);

    result = new ImageComparison().areFilesTheSame(beforeZooming, afterZooming, ACCURACY);
    LOG.log(
        "Zooming out by gesture",
        "works",
        "does not work",
        result
    );

    lightbox.clickCloseButton();
    lightbox.clickGalleryImage(0);

    Assertion.assertTrue(
        lightbox.isCurrentImageVisible(),
        "Image is not visible"
    );

    LOG.logResult(
        "Current image",
        "is visible",
        true
    );

    beforeZooming = new Shooter().capturePage(driver);
    touchAction.tapOnPointXY(50, 50, 140, 0);
    touchAction.tapOnPointXY(50, 50, 140, 3000);
    afterZooming = new Shooter().capturePage(driver);

    result = !new ImageComparison().areFilesTheSame(beforeZooming, afterZooming, ACCURACY);
    LOG.log(
        "Zooming in by double tap",
        "works",
        "does not work",
        result
    );

    touchAction.tapOnPointXY(50, 50, 140, 0);
    touchAction.tapOnPointXY(50, 50, 140, 3000);
    afterZooming = new Shooter().capturePage(driver);

    result = new ImageComparison().areFilesTheSame(beforeZooming, afterZooming, ACCURACY);
    LOG.log(
        "Zooming out by double tap",
        "works",
        "does not work",
        result
    );
  }

  // MT04
  @Test(groups = {"MercuryLightboxTest_004", "MercuryLightboxTests", "Mercury"})
  public void MercuryLightboxTest_004_UIShow_UIHide() {
    LightboxComponentObject lightbox = new LightboxComponentObject(driver);
    lightbox.openMercuryArticleByName(wikiURL, MercurySubpages.GALLERY);
    PerformTouchAction touchAction = new PerformTouchAction(driver);

    lightbox.clickGalleryImage(0);

    Assertion.assertTrue(
        lightbox.isLightboxHeaderDisplayed(),
        "Lightbox header isn't displayed"
    );

    Assertion.assertTrue(
        lightbox.isLightboxFooterDisplayed(),
        "Lightbox footer isn't displayed"
    );

    touchAction.tapOnPointXY(50, 50, 500, 5000);

    Assertion.assertFalse(
        lightbox.isLightboxHeaderDisplayed(),
        "Lightbox header is displayed"
    );

    Assertion.assertFalse(
        lightbox.isLightboxFooterDisplayed(),
        "Lightbox footer is displayed"
    );

    touchAction.tapOnPointXY(50, 50, 500, 5000);

    Assertion.assertTrue(
        lightbox.isLightboxHeaderDisplayed(),
        "Lightbox header isn't displayed"
    );

    Assertion.assertTrue(
        lightbox.isLightboxFooterDisplayed(),
        "Lightbox footer isn't displayed"
    );
  }

  // MT05
  @RelatedIssue(issueID = "HG-730")
  @Test(groups = {"MercuryLightboxTest_005", "MercuryLightboxTests", "Mercury"}, enabled = false)
  public void MercuryLightboxTest_005_BackButtonCloseLightbox() {
    AndroidDriver mobileDriver = NewDriverProvider.getMobileDriver();
    LightboxComponentObject lightbox = new LightboxComponentObject(driver);
    lightbox.openMercuryArticleByName(wikiURL, MercurySubpages.GALLERY);

    String oldUrl = driver.getCurrentUrl();
    lightbox.clickGalleryImage(0);

    Assertion.assertTrue(
        lightbox.isLightboxOpened(),
        "Lightbox is closed"
    );

    mobileDriver.execute(DriverCommand.GO_BACK, null);

    boolean result = !lightbox.isLightboxOpened();
    LOG.log(
        "Lightbox",
        "is closed",
        "is opened",
        result
    );

    result = oldUrl.equals(driver.getCurrentUrl());
    LOG.log(
        "URL",
        "is the same",
        "is different",
        result
    );
  }

  // MT06
  @Test(groups = {"MercuryLightboxTest_006", "MercuryLightboxTests", "Mercury"})
  public void MercuryLightboxTest_006_MovingOnZoomedImage() {
    LightboxComponentObject lightbox = new LightboxComponentObject(driver);
    lightbox.openMercuryArticleByName(wikiURL, MercurySubpages.GALLERY);
    PerformTouchAction touchAction = new PerformTouchAction(driver);

    lightbox.clickGalleryImage(0);
    String direction = DIRECTION_LEFT;

    Assertion.assertTrue(
        lightbox.isCurrentImageVisible(),
        "Image is not visible"
    );

    LOG.logResult(
        "Current image",
        "is visible",
        true
    );

    touchAction.tapOnPointXY(50, 50, 500, 2000);
    File beforeZooming = new Shooter().capturePage(driver);
    touchAction.tapOnPointXY(50, 50, 140, 0);
    touchAction.tapOnPointXY(50, 50, 140, 2000);
    File afterZooming = new Shooter().capturePage(driver);

    boolean result = !new ImageComparison().areFilesTheSame(beforeZooming, afterZooming);
    LOG.log(
        "Zooming in",
        "works",
        "does not work",
        result
    );

    touchAction.swipeFromCenterToDirection(direction, 200, 200, 2000);
    File afterMoving = new Shooter().capturePage(driver);

    result = !new ImageComparison().areFilesTheSame(afterZooming, afterMoving);
    LOG.log(
        "Moving " + direction,
        "works",
        "does not work",
        result
    );

    lightbox.clickCloseButton();
    lightbox.clickGalleryImage(0);
    direction = DIRECTION_RIGHT;

    Assertion.assertTrue(
        lightbox.isCurrentImageVisible(),
        "Image is not visible"
    );

    LOG.logResult(
        "Current image",
        "is visible",
        true
    );

    touchAction.tapOnPointXY(50, 50, 500, 2000);
    beforeZooming = new Shooter().capturePage(driver);
    touchAction.tapOnPointXY(50, 50, 140, 0);
    touchAction.tapOnPointXY(50, 50, 140, 2000);
    afterZooming = new Shooter().capturePage(driver);

    result = !new ImageComparison().areFilesTheSame(beforeZooming, afterZooming);
    LOG.log(
        "Zooming in",
        "works",
        "does not work",
        result
    );

    touchAction.swipeFromCenterToDirection(direction, 200, 200, 2000);
    afterMoving = new Shooter().capturePage(driver);

    result = !new ImageComparison().areFilesTheSame(afterZooming, afterMoving);
    LOG.log(
        "Moving " + direction,
        "works",
        "does not work",
        result
    );

    lightbox.clickCloseButton();
    lightbox.clickGalleryImage(0);
    direction = DIRECTION_UP;

    Assertion.assertTrue(
        lightbox.isCurrentImageVisible(),
        "Image is not visible"
    );

    LOG.logResult(
        "Current image",
        "is visible",
        true
    );

    touchAction.tapOnPointXY(50, 50, 500, 2000);
    beforeZooming = new Shooter().capturePage(driver);
    touchAction.tapOnPointXY(50, 50, 140, 0);
    touchAction.tapOnPointXY(50, 50, 140, 2000);
    afterZooming = new Shooter().capturePage(driver);

    result = !new ImageComparison().areFilesTheSame(beforeZooming, afterZooming);
    LOG.log(
        "Zooming in",
        "works",
        "does not work",
        result
    );

    touchAction.swipeFromCenterToDirection(direction, 200, 200, 2000);
    afterMoving = new Shooter().capturePage(driver);

    result = !new ImageComparison().areFilesTheSame(afterZooming, afterMoving);
    LOG.log(
        "Moving " + direction,
        "works",
        "does not work",
        result
    );

    lightbox.clickCloseButton();
    lightbox.clickGalleryImage(0);
    direction = DIRECTION_DOWN;

    Assertion.assertTrue(
        lightbox.isCurrentImageVisible(),
        "Image is not visible"
    );

    LOG.logResult(
        "Current image",
        "is visible",
        true
    );

    touchAction.tapOnPointXY(50, 50, 500, 2000);
    beforeZooming = new Shooter().capturePage(driver);
    touchAction.tapOnPointXY(50, 50, 140, 0);
    touchAction.tapOnPointXY(50, 50, 140, 2000);
    afterZooming = new Shooter().capturePage(driver);

    result = !new ImageComparison().areFilesTheSame(beforeZooming, afterZooming);
    LOG.log(
        "Zooming in",
        "works",
        "does not work",
        result
    );

    touchAction.swipeFromCenterToDirection(direction, 200, 200, 2000);
    afterMoving = new Shooter().capturePage(driver);

    result = !new ImageComparison().areFilesTheSame(afterZooming, afterMoving);
    LOG.log(
        "Moving " + direction,
        "works",
        "does not work",
        result
    );
  }
}
