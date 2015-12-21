package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.imageutilities.ImageComparison;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.common.driverprovider.NewDriverProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.GalleryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.LightboxComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DriverCommand;
import org.testng.annotations.Test;

import java.io.File;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class LightboxTests extends NewTestTemplate {

  private static final String DIRECTION_LEFT = "left";
  private static final String DIRECTION_RIGHT = "right";
  private static final String DIRECTION_UP = "up";
  private static final String DIRECTION_DOWN = "down";
  private static final double ACCURACY = 0.83;

  @Test(groups = "MercuryLightboxTest_001")
  public void MercuryLightboxTest_001_Open_Close() {
    GalleryComponentObject gallery = new GalleryComponentObject(driver);
    LightboxComponentObject lightbox;
    gallery.openMercuryArticleByName(wikiURL, MercurySubpages.GALLERY);

    lightbox = gallery.clickGalleryImage(0);

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

  @Test(groups = "MercuryLightboxTest_002")
  @InBrowser(browser = Browser.CHROME_ANDROID)
  public void MercuryLightboxTest_002_TapOnEdgesChangeImages_SwipeChangeImages() {
    GalleryComponentObject gallery = new GalleryComponentObject(driver);
    LightboxComponentObject lightbox;
    gallery.openMercuryArticleByName(wikiURL, MercurySubpages.GALLERY);
    PerformTouchAction touchAction = new PerformTouchAction(driver);

    lightbox = gallery.clickGalleryImage(0);

    Assertion.assertTrue(
        lightbox.isCurrentImageVisible(),
        MercuryMessages.IMAGE_INVISIBLE_MSG
    );

    PageObjectLogging.log(
        "Current image",
        "is visible",
        true
    );

    String currentImageSrc = lightbox.getCurrentImagePath();
    touchAction.tapOnPointXY(25, 50, 500, 5000);
    String nextImageSrc = lightbox.getCurrentImagePath();

    boolean result = !currentImageSrc.equals(nextImageSrc);
    PageObjectLogging.log(
        "Change image by tap left edge",
        "works",
        "doesn't work",
        result
    );

    currentImageSrc = lightbox.getCurrentImagePath();
    touchAction.tapOnPointXY(75, 50, 500, 5000);
    nextImageSrc = lightbox.getCurrentImagePath();

    result = !currentImageSrc.equals(nextImageSrc);
    PageObjectLogging.log(
        "Change image by tap right edge",
        "works",
        "doesn't work",
        result
    );

    lightbox.clickCloseButton();
    gallery.clickGalleryImage(0);

    Assertion.assertTrue(
        lightbox.isCurrentImageVisible(),
        MercuryMessages.IMAGE_INVISIBLE_MSG
    );

    PageObjectLogging.log(
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
    PageObjectLogging.log(
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
    PageObjectLogging.log(
        "Change image by swipe right",
        "works",
        "does not work",
        result
    );
  }

  @Test(groups = "MercuryLightboxTest_003")
  @InBrowser(browser = Browser.CHROME_ANDROID)
  public void MercuryLightboxTest_003_ZoomByGesture_ZoomByDoubleTap() {
    GalleryComponentObject gallery = new GalleryComponentObject(driver);
    LightboxComponentObject lightbox;
    gallery.openMercuryArticleByName(wikiURL, MercurySubpages.GALLERY);
    PerformTouchAction touchAction = new PerformTouchAction(driver);

    lightbox = gallery.clickGalleryImage(0);

    Assertion.assertTrue(
        lightbox.isCurrentImageVisible(),
        MercuryMessages.IMAGE_INVISIBLE_MSG
    );

    PageObjectLogging.log(
        "Current image",
        "is visible",
        true
    );

    File beforeZooming = new Shooter().capturePage(driver);
    touchAction.zoomInOutPointXY(50, 50, 50, 100, PerformTouchAction.ZOOM_WAY_IN, 3000);
    File afterZooming = new Shooter().capturePage(driver);

    boolean result = !new ImageComparison().areFilesTheSame(beforeZooming, afterZooming, ACCURACY);
    PageObjectLogging.log(
        "Zooming in by gesture",
        "works",
        "does not work",
        result
    );

    touchAction.zoomInOutPointXY(50, 50, 50, 140, PerformTouchAction.ZOOM_WAY_OUT, 3000);
    afterZooming = new Shooter().capturePage(driver);

    result = new ImageComparison().areFilesTheSame(beforeZooming, afterZooming, ACCURACY);
    PageObjectLogging.log(
        "Zooming out by gesture",
        "works",
        "does not work",
        result
    );

    lightbox.clickCloseButton();
    gallery.clickGalleryImage(0);

    Assertion.assertTrue(
        lightbox.isCurrentImageVisible(),
        MercuryMessages.IMAGE_INVISIBLE_MSG
    );

    PageObjectLogging.log(
        "Current image",
        "is visible",
        true
    );

    beforeZooming = new Shooter().capturePage(driver);
    touchAction.tapOnPointXY(50, 50, 140, 0);
    touchAction.tapOnPointXY(50, 50, 140, 3000);
    afterZooming = new Shooter().capturePage(driver);

    result = !new ImageComparison().areFilesTheSame(beforeZooming, afterZooming, ACCURACY);
    PageObjectLogging.log(
        "Zooming in by double tap",
        "works",
        "does not work",
        result
    );

    touchAction.tapOnPointXY(50, 50, 140, 0);
    touchAction.tapOnPointXY(50, 50, 140, 3000);
    afterZooming = new Shooter().capturePage(driver);

    result = new ImageComparison().areFilesTheSame(beforeZooming, afterZooming, ACCURACY);
    PageObjectLogging.log(
        "Zooming out by double tap",
        "works",
        "does not work",
        result
    );
  }

  @Test(groups = "MercuryLightboxTest_004")
  public void MercuryLightboxTest_004_UIShow_UIHide() {
    GalleryComponentObject gallery = new GalleryComponentObject(driver);
    LightboxComponentObject lightbox;
    gallery.openMercuryArticleByName(wikiURL, MercurySubpages.GALLERY);
    lightbox = gallery.clickGalleryImage(0);

    Assertion.assertTrue(lightbox.isLightboxHeaderDisplayed(), "Lightbox header isn't displayed");
    Assertion.assertTrue(lightbox.isLightboxFooterDisplayed(), "Lightbox footer isn't displayed");

    lightbox.clickOnImage();

    Assertion.assertFalse(lightbox.isLightboxHeaderDisplayed(), "Lightbox header is displayed");
    Assertion.assertFalse(lightbox.isLightboxFooterDisplayed(), "Lightbox footer is displayed");

    lightbox.clickOnImage();

    Assertion.assertTrue(lightbox.isLightboxHeaderDisplayed(), "Lightbox header isn't displayed");
    Assertion.assertTrue(lightbox.isLightboxFooterDisplayed(), "Lightbox footer isn't displayed");
  }

  @RelatedIssue(issueID = "HG-730")
  @Test(groups = "MercuryLightboxTest_005", enabled = false)
  public void MercuryLightboxTest_005_BackButtonCloseLightbox() {
    AndroidDriver mobileDriver = NewDriverProvider.getMobileDriver();
    LightboxComponentObject lightbox;
    GalleryComponentObject gallery = new GalleryComponentObject(driver);
    gallery.openMercuryArticleByName(wikiURL, MercurySubpages.GALLERY);

    String oldUrl = driver.getCurrentUrl();
    lightbox = gallery.clickGalleryImage(0);

    Assertion.assertTrue(
        lightbox.isLightboxOpened(),
        "Lightbox is closed"
    );

    mobileDriver.execute(DriverCommand.GO_BACK, null);

    boolean result = !lightbox.isLightboxOpened();
    PageObjectLogging.log(
        "Lightbox",
        "is closed",
        "is opened",
        result
    );

    result = oldUrl.equals(driver.getCurrentUrl());
    PageObjectLogging.log(
        "URL",
        "is the same",
        "is different",
        result
    );
  }

  @Test(groups = "MercuryLightboxTest_006")
  @InBrowser(browser = Browser.CHROME_ANDROID)
  public void MercuryLightboxTest_006_MovingOnZoomedImage() {
    GalleryComponentObject gallery = new GalleryComponentObject(driver);
    LightboxComponentObject lightbox;
    gallery.openMercuryArticleByName(wikiURL, MercurySubpages.GALLERY);
    PerformTouchAction touchAction = new PerformTouchAction(driver);

    lightbox = gallery.clickGalleryImage(0);
    String direction = DIRECTION_LEFT;

    Assertion.assertTrue(
        lightbox.isCurrentImageVisible(),
        MercuryMessages.IMAGE_INVISIBLE_MSG
    );

    PageObjectLogging.log(
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
    PageObjectLogging.log(
        "Zooming in",
        "works",
        "does not work",
        result
    );

    touchAction.swipeFromCenterToDirection(direction, 200, 200, 2000);
    File afterMoving = new Shooter().capturePage(driver);

    result = !new ImageComparison().areFilesTheSame(afterZooming, afterMoving);
    PageObjectLogging.log(
        "Moving " + direction,
        "works",
        "does not work",
        result
    );

    lightbox.clickCloseButton();
    gallery.clickGalleryImage(0);
    direction = DIRECTION_RIGHT;

    Assertion.assertTrue(
        lightbox.isCurrentImageVisible(),
        MercuryMessages.IMAGE_INVISIBLE_MSG
    );

    PageObjectLogging.log(
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
    PageObjectLogging.log(
        "Zooming in",
        "works",
        "does not work",
        result
    );

    touchAction.swipeFromCenterToDirection(direction, 200, 200, 2000);
    afterMoving = new Shooter().capturePage(driver);

    result = !new ImageComparison().areFilesTheSame(afterZooming, afterMoving);
    PageObjectLogging.log(
        "Moving " + direction,
        "works",
        "does not work",
        result
    );

    lightbox.clickCloseButton();
    gallery.clickGalleryImage(0);
    direction = DIRECTION_UP;

    Assertion.assertTrue(
        lightbox.isCurrentImageVisible(),
        MercuryMessages.IMAGE_INVISIBLE_MSG
    );

    PageObjectLogging.log(
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
    PageObjectLogging.log(
        "Zooming in",
        "works",
        "does not work",
        result
    );

    touchAction.swipeFromCenterToDirection(direction, 200, 200, 2000);
    afterMoving = new Shooter().capturePage(driver);

    result = !new ImageComparison().areFilesTheSame(afterZooming, afterMoving);
    PageObjectLogging.log(
        "Moving " + direction,
        "works",
        "does not work",
        result
    );

    lightbox.clickCloseButton();
    gallery.clickGalleryImage(0);
    direction = DIRECTION_DOWN;

    Assertion.assertTrue(
        lightbox.isCurrentImageVisible(),
        MercuryMessages.IMAGE_INVISIBLE_MSG
    );

    PageObjectLogging.log(
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
    PageObjectLogging.log(
        "Zooming in",
        "works",
        "does not work",
        result
    );

    touchAction.swipeFromCenterToDirection(direction, 200, 200, 2000);
    afterMoving = new Shooter().capturePage(driver);

    result = !new ImageComparison().areFilesTheSame(afterZooming, afterMoving);
    PageObjectLogging.log(
        "Moving " + direction,
        "works",
        "does not work",
        result
    );
  }
}
