package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.imageutilities.ImageComparison;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.InteractiveMapsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 * @ownership: Content - Mercury mobile
 */
public class InteractiveMapsTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void optInMercury() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    BasePageObject.turnOnMercurySkin(driver, wikiURL);
  }

  private static final int WAIT_TIME = 5000;

  // IMAPT01
  @Test(groups = {"MercuryInteractiveMapsTest_001", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMapsTest_001_MapModalUrlTitlePinPopUpClose() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAPS);
    InteractiveMapsComponentObject maps = new InteractiveMapsComponentObject(driver);
    maps.clickViewMapButton();
    Assertion.assertTrue(maps.isMapModalVisible(), "Map modal is hidden");
    PageObjectLogging.log("Map modal", "is visible", true);
    if (maps.isMapIdInUrl()) {
      PageObjectLogging.log("Url", "match pattern ?map=", true);
    } else {
      PageObjectLogging.log("Url", "does not match pattern ?map=", false);
    }
    if (maps.isTextInMapTitleHeader()) {
      PageObjectLogging.log("Map title in header", "is displayed", true);
    } else {
      PageObjectLogging.log("Map title in header", "is not displayed", false);
    }
    maps.switchToMapFrame();
    maps.clickPin();
    if (maps.isPinPopUp()) {
      PageObjectLogging.log("Pin popup", "appears", true);
    } else {
      PageObjectLogging.log("Pin popup", "does not appear", false);
    }
    maps.switchToDefaultFrame();
    maps.clickCloseButton();
    if (maps.isMapModalVisible()) {
      PageObjectLogging.log("Map modal", "is visible", false);
    } else {
      PageObjectLogging.log("Map modal", "is hidden", true);
    }
  }

  // IMAPT02
  @Test(groups = {"MercuryInteractiveMapsTest_002", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMapsTest_002_ZoomByGestureAndButtons() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAPS);
    PerformTouchAction touchAction = new PerformTouchAction(driver);
    InteractiveMapsComponentObject maps = new InteractiveMapsComponentObject(driver);
    maps.clickViewMapButton();
    maps.switchToMapFrame();
    Assertion.assertFalse(maps.isZoomInButtonEnabled(), "Zoom in button is enabled");
    File beforeZooming = new Shooter().capturePage(driver);
    maps.clickZoomOut();
    base.waitMilliseconds(WAIT_TIME, "Wait after zoom out");
    File afterZooming = new Shooter().capturePage(driver);
    Assertion.assertFalse(new ImageComparison().areFilesTheSame(beforeZooming, afterZooming),
                          "Zoom out doesn't work");
    Assertion.assertTrue(maps.isZoomInButtonEnabled(), "Zoom in button is disabled");
    PageObjectLogging.log("Zoom out by click", "works", true);
    beforeZooming = new Shooter().capturePage(driver);
    maps.clickZoomIn();
    base.waitMilliseconds(WAIT_TIME, "Wait after zoom in");
    afterZooming = new Shooter().capturePage(driver);
    Assertion.assertFalse(new ImageComparison().areFilesTheSame(beforeZooming, afterZooming),
                          "Zoom in doesn't work");
    Assertion.assertFalse(maps.isZoomInButtonEnabled(), "Zoom in button is enabled");
    PageObjectLogging.log("Zoom in by click", "works", true);
    beforeZooming = new Shooter().capturePage(driver);
    touchAction.zoomInOutPointXY(50, 50, 50, 100, PerformTouchAction.ZOOM_WAY_OUT, WAIT_TIME);
    afterZooming = new Shooter().capturePage(driver);
    Assertion.assertFalse(new ImageComparison().areFilesTheSame(beforeZooming, afterZooming),
                          "Zoom out doesn't work");
    Assertion.assertTrue(maps.isZoomInButtonEnabled(), "Zoom in button is disabled");
    PageObjectLogging.log("Zoom out by gesture", "works", true);
    beforeZooming = new Shooter().capturePage(driver);
    touchAction.zoomInOutPointXY(50, 50, 50, 100, PerformTouchAction.ZOOM_WAY_IN, WAIT_TIME);
    afterZooming = new Shooter().capturePage(driver);
    Assertion.assertFalse(new ImageComparison().areFilesTheSame(beforeZooming, afterZooming),
                          "Zoom in doesn't work");
    Assertion.assertFalse(maps.isZoomInButtonEnabled(), "Zoom in button is enabled");
    PageObjectLogging.log("Zoom in by gesture", "works", true);
  }

  // IMAPT03
  @Test(groups = {"MercuryInteractiveMapsTest_003", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMapsTest_003_FilterBoxListScroll() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAPS);
    PerformTouchAction touchAction = new PerformTouchAction(driver);
    InteractiveMapsComponentObject maps = new InteractiveMapsComponentObject(driver);
    maps.clickViewMapButton();
    maps.switchToMapFrame();
    File beforeScrolling = new Shooter().capturePage(driver);
    maps.clickFilterBox();
    Assertion.assertTrue(maps.isFilterBoxWasExpanded(), "Filter box is collapsed");
    PageObjectLogging.log("Filter box", "is expanded", true);
    base.waitMilliseconds(WAIT_TIME, "Wait fo filterbox to be scrollable");
    touchAction.swipeFromPointToPoint(40, 80, 40, 40, 500, WAIT_TIME);
    File afterScrolling = new Shooter().capturePage(driver);
    if (new ImageComparison().areFilesTheSame(beforeScrolling, afterScrolling)) {
      PageObjectLogging.log("Scrolling in filter box", "does not work", false);
    } else {
      PageObjectLogging.log("Scrolling in filter box", "works", true);
    }
  }
}
