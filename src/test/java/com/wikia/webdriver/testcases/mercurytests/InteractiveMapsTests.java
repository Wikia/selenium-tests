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
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  private boolean failTest = false;

  // IMAPT01
  @Test(groups = {"MercuryInteractiveMapsTest_001", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMapsTest_001_MapModal_Url_Title_PinPopUp_Close() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAPS);
    InteractiveMapsComponentObject maps = new InteractiveMapsComponentObject(driver);
    maps.clickViewMapButton();
    Assertion.assertTrue(maps.isMapModalVisible(), "Map modal is hidden");
    PageObjectLogging.log("Map modal", "is visible", true);
    PageObjectLogging.log("Url", "match pattern ?map=", "does not match pattern ?map=", maps.isMapIdInUrl());
    if (! maps.isMapIdInUrl()) failTest = true;
    PageObjectLogging.log("Map title in header", "is displayed", "is not displayed",
                          maps.isTextInMapTitleHeader());
    if (! maps.isTextInMapTitleHeader()) failTest = true;
    maps.switchToMapFrame();
    maps.clickPin();
    PageObjectLogging.log("Pin popup", "appears", "does not appear", maps.isPinPopUp());
    if (! maps.isPinPopUp()) failTest = true;
    maps.switchToDefaultFrame();
    maps.clickCloseButton();
    PageObjectLogging.log("Map modal", "is closed", "is opened", ! maps.isMapModalVisible());
    if (maps.isMapModalVisible()) failTest = true;
    base.failTest(failTest);
  }

  // IMAPT02
  @Test(groups = {"MercuryInteractiveMapsTest_002", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMapsTest_002_ZoomByGesture_ZoomByButtons() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAPS);
    PerformTouchAction touchAction = new PerformTouchAction(driver);
    InteractiveMapsComponentObject maps = new InteractiveMapsComponentObject(driver);
    maps.clickViewMapButton();
    maps.switchToMapFrame();
    Assertion.assertFalse(maps.isZoomInButtonEnabled(), "Zoom in button is enabled");
    PageObjectLogging.log("Zoom in button", "is disabled", true);
    File beforeZooming = new Shooter().capturePage(driver);
    maps.clickZoomOut();
    base.waitMilliseconds(5000, "Wait after zoom out");
    File afterZooming = new Shooter().capturePage(driver);
    Assertion.assertFalse(new ImageComparison().areFilesTheSame(beforeZooming, afterZooming),
                          "Zoom out doesn't work");
    PageObjectLogging.log("Zoom out by click", "works", true);
    Assertion.assertTrue(maps.isZoomInButtonEnabled(), "Zoom in button is disabled");
    PageObjectLogging.log("Zoom in button", "is enabled", true);
    beforeZooming = new Shooter().capturePage(driver);
    maps.clickZoomIn();
    base.waitMilliseconds(5000, "Wait after zoom in");
    afterZooming = new Shooter().capturePage(driver);
    Assertion.assertFalse(new ImageComparison().areFilesTheSame(beforeZooming, afterZooming),
                          "Zoom in doesn't work");
    PageObjectLogging.log("Zoom in by click", "works", true);
    Assertion.assertFalse(maps.isZoomInButtonEnabled(), "Zoom in button is enabled");
    PageObjectLogging.log("Zoom in button", "is disabled", true);
    beforeZooming = new Shooter().capturePage(driver);
    touchAction.zoomInOutPointXY(50, 50, 50, 100, PerformTouchAction.ZOOM_WAY_OUT, 5000);
    afterZooming = new Shooter().capturePage(driver);
    Assertion.assertFalse(new ImageComparison().areFilesTheSame(beforeZooming, afterZooming),
                          "Zoom out doesn't work");
    PageObjectLogging.log("Zoom out by gesture", "works", true);
    Assertion.assertTrue(maps.isZoomInButtonEnabled(), "Zoom in button is disabled");
    PageObjectLogging.log("Zoom in button", "is enabled", true);
    beforeZooming = new Shooter().capturePage(driver);
    touchAction.zoomInOutPointXY(50, 50, 50, 100, PerformTouchAction.ZOOM_WAY_IN, 5000);
    afterZooming = new Shooter().capturePage(driver);
    Assertion.assertFalse(new ImageComparison().areFilesTheSame(beforeZooming, afterZooming),
                          "Zoom in doesn't work");
    PageObjectLogging.log("Zoom in by gesture", "works", true);
    Assertion.assertFalse(maps.isZoomInButtonEnabled(), "Zoom in button is enabled");
    PageObjectLogging.log("Zoom in button", "is disabled", true);
    base.failTest(failTest);
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
    Assertion.assertFalse(maps.isFilterBoxWasExpanded(), "Filter box is expanded");
    PageObjectLogging.log("Filter box", "is collapsed", true);
    maps.clickFilterBox();
    Assertion.assertTrue(maps.isFilterBoxWasExpanded(), "Filter box is collapsed");
    PageObjectLogging.log("Filter box", "is expanded", true);
    base.waitMilliseconds(5000, "Wait fo filterbox to be scrollable");
    touchAction.swipeFromPointToPoint(40, 80, 40, 40, 500, 5000);
    File afterScrolling = new Shooter().capturePage(driver);
    PageObjectLogging.log("Scrolling in filter box", "works", "does not work", ! new ImageComparison().areFilesTheSame(beforeScrolling, afterScrolling));
    if (new ImageComparison().areFilesTheSame(beforeScrolling, afterScrolling)) failTest = true;
    base.failTest(failTest);
  }
}
