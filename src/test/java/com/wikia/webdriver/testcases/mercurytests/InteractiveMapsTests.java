package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.imageutilities.ImageComparison;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
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
    Assertion.assertTrue(maps.isMapIdInUrl(), "Url doesn't contain map");
    Assertion.assertTrue(maps.isTextInMapTitleHeader(), "Map title header is empty");
    maps.switchToMapFrame();
    maps.clickPin();
    Assertion.assertTrue(maps.isPinPopUp(), "Pin popup doesn't appear");
    maps.switchToDefaultFrame();
    maps.clickCloseButton();
    Assertion.assertFalse(maps.isMapModalVisible(), "Map modal is visible");
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
    beforeZooming = new Shooter().capturePage(driver);
    maps.clickZoomIn();
    base.waitMilliseconds(WAIT_TIME, "Wait after zoom in");
    afterZooming = new Shooter().capturePage(driver);
    Assertion.assertFalse(new ImageComparison().areFilesTheSame(beforeZooming, afterZooming),
                          "Zoom in doesn't work");
    Assertion.assertFalse(maps.isZoomInButtonEnabled(), "Zoom in button is enabled");
    beforeZooming = new Shooter().capturePage(driver);
    touchAction.zoomInOutPointXY(50, 50, 50, 100, PerformTouchAction.ZOOM_WAY_OUT, WAIT_TIME);
    afterZooming = new Shooter().capturePage(driver);
    Assertion.assertFalse(new ImageComparison().areFilesTheSame(beforeZooming, afterZooming),
                          "Zoom out doesn't work");
    Assertion.assertTrue(maps.isZoomInButtonEnabled(), "Zoom in button is disabled");
    beforeZooming = new Shooter().capturePage(driver);
    touchAction.zoomInOutPointXY(50, 50, 50, 100, PerformTouchAction.ZOOM_WAY_IN, WAIT_TIME);
    afterZooming = new Shooter().capturePage(driver);
    Assertion.assertFalse(new ImageComparison().areFilesTheSame(beforeZooming, afterZooming),
                          "Zoom in doesn't work");
    Assertion.assertFalse(maps.isZoomInButtonEnabled(), "Zoom in button is enabled");
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
    base.waitMilliseconds(WAIT_TIME, "Wait fo filterbox to be scrollable");
    touchAction.swipeFromPointToPoint(40, 80, 40, 40, 500, WAIT_TIME);
    File afterScrolling = new Shooter().capturePage(driver);
    Assertion.assertFalse(new ImageComparison().areFilesTheSame(beforeScrolling, afterScrolling),
                          "Scrolling in filter box doesn't work");
  }
}
