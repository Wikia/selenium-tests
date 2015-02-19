package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.InteractiveMapsMercuryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

/**
 * @ownership: Mobile Web
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 */
public class InteractiveMapsTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @BeforeMethod(alwaysRun = true)
  public void optInMercury() {
    MercuryContent.turnOnMercurySkin(driver, wikiURL);
  }

  // IMAPT01
  @Test(groups = {"MercuryInteractiveMaps_001", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_001_ViewButtonWillOpenMapModal() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
    InteractiveMapsMercuryComponentObject maps = new InteractiveMapsMercuryComponentObject(driver);
    maps.clickViewMapButton();
    Assertion.assertTrue(maps.isMapModalVisible(), "Map modal is hidden");
  }

  // IMAPT02
  @Test(groups = {"MercuryInteractiveMaps_002", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_002_PoiIsClickable() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
    InteractiveMapsMercuryComponentObject maps = new InteractiveMapsMercuryComponentObject(driver);
    maps.clickViewMapButton();
    maps.clickPin();
    Assertion.assertTrue(maps.isPinPopUp(), "Pin popup doesn't appear");
  }
  
  // IMAPT03
  @Test(groups = {"MercuryInteractiveMaps_003", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_003_ZoomByGesture() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
    PerformTouchAction touchAction = new PerformTouchAction(driver);
    InteractiveMapsMercuryComponentObject maps = new InteractiveMapsMercuryComponentObject(driver);
    maps.clickViewMapButton();
    maps.switchToMapFrame();
    Assertion.assertFalse(maps.isZoomInButtonEnabled(), "Zoom in button is enabled");
    int waitTime = 5000;
    touchAction.zoomInOutPointXY(50, 50, 50, 100, PerformTouchAction.ZOOM_WAY_OUT, waitTime);
    Assertion.assertTrue(maps.isZoomInButtonEnabled(), "Zoom in button is disabled");
    touchAction.zoomInOutPointXY(50, 50, 50, 100, PerformTouchAction.ZOOM_WAY_IN, waitTime);
    Assertion.assertFalse(maps.isZoomInButtonEnabled(), "Zoom in button is enabled");
  }

  // IMAPT04
  @Test(groups = {"MercuryInteractiveMaps_004", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_004_ZoomButtons() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
    InteractiveMapsMercuryComponentObject maps = new InteractiveMapsMercuryComponentObject(driver);
    maps.clickViewMapButton();
    maps.switchToMapFrame();
    Assertion.assertTrue(maps.isZoomButtonWorking("out"), "Zoom out doesn't work");
    Assertion.assertTrue(maps.isZoomButtonWorking("in"), "Zoom in doesn't work");
  }

  // IMAPT05
  @Test(groups = {"MercuryInteractiveMaps_005", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_005_FilterBoxCanBeExpanded() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
    InteractiveMapsMercuryComponentObject maps = new InteractiveMapsMercuryComponentObject(driver);
    maps.clickViewMapButton();
    maps.switchToMapFrame();
    maps.clickFilterBox();
    Assertion.assertTrue(maps.isFilterBoxWasExpanded(), "Filter box is collapsed");
  }

  // IMAPT06
  @Test(groups = {"MercuryInteractiveMaps_006", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_006_MapTitleInHeader() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
    InteractiveMapsMercuryComponentObject maps = new InteractiveMapsMercuryComponentObject(driver);
    maps.clickViewMapButton();
    Assertion.assertTrue(maps.isTextInMapTitleHeader(), "Map title header is empty");
  }
  
  // IMAPT07
  @Test(groups = {"MercuryInteractiveMaps_007", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_007_ScrollableFilterList() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
    PerformTouchAction touchAction = new PerformTouchAction(driver);
    InteractiveMapsMercuryComponentObject maps = new InteractiveMapsMercuryComponentObject(driver);
    maps.clickViewMapButton();
    maps.switchToMapFrame();
    Assertion.assertTrue(maps.isFilterListScrollable(touchAction),
                         "Scrolling in filter box doesn't work");
  }

  // IMAPT08
  @Test(groups = {"MercuryInteractiveMaps_008", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_008_CloseButtonWillCloseModal() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
    InteractiveMapsMercuryComponentObject maps = new InteractiveMapsMercuryComponentObject(driver);
    maps.clickViewMapButton();
    maps.clickCloseButton();
    Assertion.assertFalse(maps.isMapModalVisible(), "Map modal is visible");
  }

  // IMAPT09
  @Test(groups = {"MercuryInteractiveMaps_009", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_009_MapIdInLink() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
    InteractiveMapsMercuryComponentObject maps = new InteractiveMapsMercuryComponentObject(driver);
    maps.clickViewMapButton();
    Assertion.assertTrue(maps.isMapIdInUrl(), "Url doesn't contain map");
  }
}
