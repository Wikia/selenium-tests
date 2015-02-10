package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.InteractiveMapsMercuryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
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
    MercuryArticlePageObject article =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
    InteractiveMapsMercuryComponentObject maps = article.clickViewMapButton();
    maps.verifyMapModalIsVisible();
  }

  // IMAPT02
  @Test(groups = {"MercuryInteractiveMaps_002", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_002_PoiIsClickable() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject article =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
    InteractiveMapsMercuryComponentObject maps = article.clickViewMapButton();
    maps.verifyMapModalIsVisible();
    maps.clickPin();
    maps.verifyPinPopUpAppeared();
  }

  // IMAPT04
  @Test(groups = {"MercuryInteractiveMaps_004", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_004_ZoomButtons() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject article =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
    InteractiveMapsMercuryComponentObject maps = article.clickViewMapButton();
    maps.verifyMapModalIsVisible();
    String leaflet = maps.getMapLeafletSrc();
    maps.clickZoomOut(6);
    String newLeaflet = maps.getMapLeafletSrc();
    maps.verifyMapZoomChangedView(leaflet, newLeaflet);
    leaflet = maps.getMapLeafletSrc();
    maps.clickZoomIn(3);
    newLeaflet = maps.getMapLeafletSrc();
    maps.verifyMapZoomChangedView(leaflet, newLeaflet);
  }

  // IMAPT05
  @Test(groups = {"MercuryInteractiveMaps_005", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_005_FilterBoxCanBeExpanded() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject article =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
    InteractiveMapsMercuryComponentObject maps = article.clickViewMapButton();
    maps.verifyMapModalIsVisible();
    maps.clickFilterBox();
    maps.verifyFilterBoxWasExpanded();
  }

  // IMAPT06
  @Test(groups = {"MercuryInteractiveMaps_006", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_006_MapTitleInHeader() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject article =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
    InteractiveMapsMercuryComponentObject maps = article.clickViewMapButton();
    maps.verifyMapModalIsVisible();
    maps.verifyMapTitleInHeader();
  }

  // IMAPT08 - FAIL
  @Test(groups = {"MercuryInteractiveMaps_008", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_008_CloseButtonWillCloseModal() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject article =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
    InteractiveMapsMercuryComponentObject maps = article.clickViewMapButton();
    maps.verifyMapModalIsVisible();
    maps.clickCloseButton();
    maps.verifyMapModalIsNotVisible();
  }

  // IMAPT09
  @Test(groups = {"MercuryInteractiveMaps_009", "MercuryInteractiveMapsTests", "Mercury"})
  public void MercuryInteractiveMaps_009_MapIdInLink() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject article =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
    InteractiveMapsMercuryComponentObject maps = article.clickViewMapButton();
    maps.verifyMapModalIsVisible();
    maps.verifyMapIdInUrl();
  }
}
