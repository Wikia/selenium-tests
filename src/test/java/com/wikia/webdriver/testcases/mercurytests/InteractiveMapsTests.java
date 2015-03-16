package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.InteractiveMapsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
  private static final String ZOOM_WAY_IN = "in";
  private static final String ZOOM_WAY_OUT = "out";

  // IMAPT01
  @Test(groups = {"MercuryInteractiveMaps_001", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_001_ViewButtonWillOpenMapModal() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAPS);
    InteractiveMapsComponentObject maps = new InteractiveMapsComponentObject(driver);
    maps.clickViewMapButton();
    Assertion.assertTrue(maps.isMapModalVisible(), "Map modal is hidden");
  }

  // IMAPT02
  @Test(groups = {"MercuryInteractiveMaps_002", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_002_PoiIsClickable() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAPS);
    InteractiveMapsComponentObject maps = new InteractiveMapsComponentObject(driver);
    maps.clickViewMapButton();
    maps.clickPin();
    Assertion.assertTrue(maps.isPinPopUp(), "Pin popup doesn't appear");
  }

  // IMAPT03
  @Test(groups = {"MercuryInteractiveMaps_003", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_003_ZoomByGesture() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAPS);
    PerformTouchAction touchAction = new PerformTouchAction(driver);
    InteractiveMapsComponentObject maps = new InteractiveMapsComponentObject(driver);
    maps.clickViewMapButton();
    maps.switchToMapFrame();
    Assertion.assertFalse(maps.isZoomInButtonEnabled(), "Zoom in button is enabled");
    touchAction.zoomInOutPointXY(50, 50, 50, 100, PerformTouchAction.ZOOM_WAY_OUT, WAIT_TIME);
    Assertion.assertTrue(maps.isZoomInButtonEnabled(), "Zoom in button is disabled");
    touchAction.zoomInOutPointXY(50, 50, 50, 100, PerformTouchAction.ZOOM_WAY_IN, WAIT_TIME);
    Assertion.assertFalse(maps.isZoomInButtonEnabled(), "Zoom in button is enabled");
  }

  // IMAPT04
  @Test(groups = {"MercuryInteractiveMaps_004", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_004_ZoomButtons() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAPS);
    InteractiveMapsComponentObject maps = new InteractiveMapsComponentObject(driver);
    maps.clickViewMapButton();
    maps.switchToMapFrame();
    Assertion.assertTrue(maps.isZoomButtonWorking(ZOOM_WAY_OUT), "Zoom out doesn't work");
    Assertion.assertTrue(maps.isZoomButtonWorking(ZOOM_WAY_IN), "Zoom in doesn't work");
  }

  // IMAPT05
  @Test(groups = {"MercuryInteractiveMaps_005", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_005_FilterBoxCanBeExpanded() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAPS);
    InteractiveMapsComponentObject maps = new InteractiveMapsComponentObject(driver);
    maps.clickViewMapButton();
    maps.switchToMapFrame();
    maps.clickFilterBox();
    Assertion.assertTrue(maps.isFilterBoxWasExpanded(), "Filter box is collapsed");
  }

  // IMAPT06
  @Test(groups = {"MercuryInteractiveMaps_006", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_006_MapTitleInHeader() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAPS);
    InteractiveMapsComponentObject maps = new InteractiveMapsComponentObject(driver);
    maps.clickViewMapButton();
    Assertion.assertTrue(maps.isTextInMapTitleHeader(), "Map title header is empty");
  }

  // IMAPT07
  @Test(groups = {"MercuryInteractiveMaps_007", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_007_ScrollableFilterList() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAPS);
    PerformTouchAction touchAction = new PerformTouchAction(driver);
    InteractiveMapsComponentObject maps = new InteractiveMapsComponentObject(driver);
    maps.clickViewMapButton();
    maps.switchToMapFrame();
    Assertion.assertTrue(maps.isFilterListScrollable(touchAction),
                         "Scrolling in filter box doesn't work");
  }

  // IMAPT08
  @Test(groups = {"MercuryInteractiveMaps_008", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_008_CloseButtonWillCloseModal() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAPS);
    InteractiveMapsComponentObject maps = new InteractiveMapsComponentObject(driver);
    maps.clickViewMapButton();
    maps.clickCloseButton();
    Assertion.assertFalse(maps.isMapModalVisible(), "Map modal is visible");
  }

  // IMAPT09
  @Test(groups = {"MercuryInteractiveMaps_009", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_009_MapIdInLink() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAPS);
    InteractiveMapsComponentObject maps = new InteractiveMapsComponentObject(driver);
    maps.clickViewMapButton();
    Assertion.assertTrue(maps.isMapIdInUrl(), "Url doesn't contain map");
  }
}
