package com.wikia.webdriver.TestCases.InteractiveMapsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PalantirContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.PalantirComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;

/**
 * @author: Rodrigo Molinero Gomez
 * @author: Lukasz Jedrzejczak
 * @author: Lukasz Nowak
 * @ownership: Mobile Web
 * 
 *             Test for Palantir App (Shadow of Mordor) IM30: Set player
 *             position and verify if poi appear on the map IM31: Set player
 *             position out of map boundaries and verify error. Verify that pin
 *             does not appear on map IM32: Set player position and after that
 *             remove player position. Verify that pin disappear. IM33: Set huge
 *             zoom level and verify error IM34: Update map position and verify
 *             respond IM35: Set decimal zoom value and verify error. Check that
 *             pin does not appear on map
 * 
 */

public class PalantirTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = { "InteractiveMaps_030", "PalantirTests", "InteractiveMaps" })
	public void InteractiveMaps_030_PalantirSetPlayerCorrectPosition() {
		InteractiveMapsPageObject specialMap = new InteractiveMapsPageObject(driver);
		InteractiveMapPageObject selectedMap = specialMap.openMap(wikiURL, PalantirContent.PALANTIR_MAP);
		selectedMap.verifyMapOpened();
		PalantirComponentObject poi = new PalantirComponentObject(driver);
		PalantirContent handle = poi.setAndVerifyPlayerPosition(-40, -10, 3, true);
		poi.verifyCorrectPlayerPos(handle);
		poi.verifyPoiAppearOnMap();
	}

	@Test(groups = { "InteractiveMaps_031", "PalantirTests", "InteractiveMaps" })
	public void InteractiveMaps_031_PalantirSetPlayerPositionOutOfMap() {
		InteractiveMapsPageObject specialMap = new InteractiveMapsPageObject(driver);
		InteractiveMapPageObject selectedMap = specialMap.openMap(wikiURL, PalantirContent.PALANTIR_MAP);
		selectedMap.verifyMapOpened();
		PalantirComponentObject poi = new PalantirComponentObject(driver);
		PalantirContent handle = poi.setAndVerifyPlayerPosition(9300, 15000, 3, true);
		poi.verifyWrongPlayerPos(handle);
		poi.verifyPoiNotAppearOnMap();
	}

	@Test(groups = { "InteractiveMaps_032", "PalantirTests", "InteractiveMaps" })
	public void InteractiveMaps_032_PalantirSetAndRemovePlayerPosition() {
		InteractiveMapsPageObject specialMap = new InteractiveMapsPageObject(driver);
		InteractiveMapPageObject selectedMap = specialMap.openMap(wikiURL, PalantirContent.PALANTIR_MAP);
		selectedMap.verifyMapOpened();
		PalantirComponentObject poi = new PalantirComponentObject(driver);
		PalantirContent handle = poi.setAndVerifyPlayerPosition(-40, -10, 3, true);
		poi.verifyCorrectPlayerPos(handle);
		poi.verifyPoiAppearOnMap();
		handle = poi.deletePlayerPosition();
		poi.verifyPlayerPosDeleted(handle);
		poi.verifyPoiNotAppearOnMap();
	}

	@Test(groups = { "InteractiveMaps_033", "PalantirTests", "InteractiveMaps" })
	public void InteractiveMaps_033_PalantirSetHugeZoomVerifyError() {
		InteractiveMapsPageObject specialMap = new InteractiveMapsPageObject(driver);
		InteractiveMapPageObject selectedMap = specialMap.openMap(wikiURL, PalantirContent.PALANTIR_MAP);
		selectedMap.verifyMapOpened();
		PalantirComponentObject poi = new PalantirComponentObject(driver);
		PalantirContent handle = poi.setAndVerifyPlayerPosition(-40, -10, 3000, true);
		poi.verifyWrongZoomLevel(handle);
		poi.verifyPoiNotAppearOnMap();
	}

	@Test(groups = { "InteractiveMaps_034", "PalantirTests", "InteractiveMaps" })
	public void InteractiveMaps_034_PalantirUpdateMapPosition() {
		InteractiveMapsPageObject specialMap = new InteractiveMapsPageObject(driver);
		InteractiveMapPageObject selectedMap = specialMap.openMap(wikiURL, PalantirContent.PALANTIR_MAP);
		selectedMap.verifyMapOpened();
		PalantirComponentObject poi = new PalantirComponentObject(driver);
		PalantirContent handle = poi.setAndVerifyPlayerPosition(-40, -10, 3, true);
		poi.verifyCorrectPlayerPos(handle);
		handle = poi.updateMapPosition(-90, -10, 3);
		poi.verifyMapPositionUpdated(handle);
	}

	@Test(groups = { "InteractiveMaps_035", "PalantirTests", "InteractiveMaps" })
	public void InteractiveMaps_035_PalantirSetDecimalZoom() {
		InteractiveMapsPageObject specialMap = new InteractiveMapsPageObject(driver);
		InteractiveMapPageObject selectedMap = specialMap.openMap(wikiURL, PalantirContent.PALANTIR_MAP);
		selectedMap.verifyMapOpened();
		PalantirComponentObject poi = new PalantirComponentObject(driver);
		PalantirContent handle = poi.setAndVerifyPlayerPosition(-40, -10, 3.4, true);
		poi.verifyDecimalZoomLevel(handle);
		poi.verifyPoiNotAppearOnMap();
	}
}
