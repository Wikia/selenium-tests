package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.imageutilities.ImageComparison;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.InteractiveMapsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @ownership Content X-Wing Wikia
 */
public class InteractiveMapsTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_AUTOMATION_TESTING);
  }

  // IMAPT01
  @Test(groups = {"MercuryInteractiveMapsTest_001", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMapsTest_001_MapModal_Url_Title_PinPopUp_Close() {
    InteractiveMapsComponentObject maps = new InteractiveMapsComponentObject(driver);
    maps.openMercuryArticleByName(wikiURL, MercurySubpages.MAP);

    maps.clickMapThumbnail();

    Assertion.assertTrue(
        maps.isMapModalVisible(),
        "Map modal is hidden"
    );

    PageObjectLogging.log(
        "Map modal",
        "is visible",
        true
    );

    boolean result = maps.isMapIdInUrl();
    PageObjectLogging.log(
        "Url",
        "match pattern ?map=",
        "does not match pattern ?map=",
        result
    );

    result = maps.isTextInMapTitleHeader();
    PageObjectLogging.log(
        "Map title in header",
        "is displayed",
        "is not displayed",
        result
    );

    maps.switchToMapFrame();
    maps.clickPin();

    result = maps.isPinPopUp();
    PageObjectLogging.log(
        "Pin popup",
        "appears",
        "does not appear",
        result
    );

    maps.switchToDefaultFrame();
    maps.clickCloseButton();

    result = !maps.isMapModalVisible();
    PageObjectLogging.log(
        "Map modal",
        "is closed",
        "is opened",
        result
    );
  }

  // IMAPT02
  @Test(groups = {"MercuryInteractiveMapsTest_002", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMapsTest_002_ZoomByGesture_ZoomByButtons() {
    InteractiveMapsComponentObject maps = new InteractiveMapsComponentObject(driver);
    maps.openMercuryArticleByName(wikiURL, MercurySubpages.MAP);
    PerformTouchAction touchAction = new PerformTouchAction(driver);

    maps.clickMapThumbnail();
    maps.switchToMapFrame();

    Assertion.assertFalse(
        maps.isZoomInButtonEnabled(),
        "Zoom in button is enabled"
    );

    PageObjectLogging.log(
        "Zoom in button",
        "is disabled",
        true
    );

    File beforeZooming = new Shooter().capturePage(driver);
    maps.clickZoomOut();
    maps.waitMilliseconds(5000, "Wait after zoom out");
    File afterZooming = new Shooter().capturePage(driver);

    Assertion.assertFalse(
        new ImageComparison().areFilesTheSame(beforeZooming, afterZooming),
        "Zoom out doesn't work"
    );

    PageObjectLogging.log(
        "Zoom out by click",
        "works",
        true
    );

    Assertion.assertTrue(
        maps.isZoomInButtonEnabled(),
        "Zoom in button is disabled"
    );

    PageObjectLogging.log(
        "Zoom in button",
        "is enabled",
        true
    );

    beforeZooming = new Shooter().capturePage(driver);
    maps.clickZoomIn();
    maps.waitMilliseconds(5000, "Wait after zoom in");
    afterZooming = new Shooter().capturePage(driver);

    Assertion.assertFalse(
        new ImageComparison().areFilesTheSame(beforeZooming, afterZooming),
        "Zoom in doesn't work"
    );

    PageObjectLogging.log(
        "Zoom in by click",
        "works",
        true
    );

    Assertion.assertFalse(
        maps.isZoomInButtonEnabled(),
        "Zoom in button is enabled"
    );

    PageObjectLogging.log(
        "Zoom in button",
        "is disabled",
        true
    );

    beforeZooming = new Shooter().capturePage(driver);
    touchAction.zoomInOutPointXY(50, 50, 50, 100, PerformTouchAction.ZOOM_WAY_OUT, 5000);
    afterZooming = new Shooter().capturePage(driver);

    Assertion.assertFalse(
        new ImageComparison().areFilesTheSame(beforeZooming, afterZooming),
        "Zoom out doesn't work"
    );

    PageObjectLogging.log(
        "Zoom out by gesture",
        "works",
        true
    );

    Assertion.assertTrue(
        maps.isZoomInButtonEnabled(),
        "Zoom in button is disabled"
    );

    PageObjectLogging.log(
        "Zoom in button",
        "is enabled",
        true
    );

    beforeZooming = new Shooter().capturePage(driver);
    touchAction.zoomInOutPointXY(50, 50, 50, 100, PerformTouchAction.ZOOM_WAY_IN, 5000);
    afterZooming = new Shooter().capturePage(driver);

    Assertion.assertFalse(
        new ImageComparison().areFilesTheSame(beforeZooming, afterZooming),
        "Zoom in doesn't work"
    );

    PageObjectLogging.log(
        "Zoom in by gesture",
        "works",
        true
    );

    Assertion.assertFalse(
        maps.isZoomInButtonEnabled(),
        "Zoom in button is enabled"
    );

    PageObjectLogging.log(
        "Zoom in button",
        "is disabled",
        true
    );
  }

  // IMAPT03
  @Test(groups = {"MercuryInteractiveMapsTest_003", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMapsTest_003_FilterBoxListScroll() {
    InteractiveMapsComponentObject maps = new InteractiveMapsComponentObject(driver);
    maps.openMercuryArticleByName(wikiURL, MercurySubpages.MAP);
    PerformTouchAction touchAction = new PerformTouchAction(driver);

    maps.clickMapThumbnail();
    maps.switchToMapFrame();
    File beforeScrolling = new Shooter().capturePage(driver);

    Assertion.assertFalse(
        maps.isFilterBoxWasExpanded(),
        "Filter box is expanded"
    );

    PageObjectLogging.log(
        "Filter box",
        "is collapsed",
        true
    );

    maps.clickFilterBox();

    Assertion.assertTrue(
        maps.isFilterBoxWasExpanded(),
        "Filter box is collapsed"
    );

    PageObjectLogging.log(
        "Filter box",
        "is expanded",
        true
    );

    maps.waitMilliseconds(5000, "Wait for filterbox to be scrollable");
    touchAction.swipeFromPointToPoint(40, 80, 40, 40, 500, 5000);
    File afterScrolling = new Shooter().capturePage(driver);

    boolean result = !new ImageComparison().areFilesTheSame(beforeScrolling, afterScrolling);
    PageObjectLogging.log(
        "Scrolling in filter box",
        "works",
        "does not work",
        result
    );
  }
}
