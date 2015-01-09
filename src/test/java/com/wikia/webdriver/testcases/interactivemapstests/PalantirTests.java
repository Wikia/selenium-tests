package com.wikia.webdriver.testcases.interactivemapstests;

import com.wikia.webdriver.common.contentpatterns.PalantirContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.PalantirComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;
import org.testng.annotations.Test;

/**
 * @author: Rodrigo Molinero Gomez
 * @author: Lukasz Jedrzejczak
 * @author: Lukasz Nowak
 * @ownership: Mobile Web
 */

public class PalantirTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"PalantirTests_001", "PalantirTests", "InteractiveMaps"})
	public void PalantirTest_001_PalantirSetPlayerCorrectPosition() {
		InteractiveMapsPageObject specialMap = new InteractiveMapsPageObject(driver);
		InteractiveMapPageObject selectedMap = specialMap.openMap(wikiURL, PalantirContent.PALANTIR_MAP);
		selectedMap.verifyMapOpened();
		PalantirComponentObject poi = new PalantirComponentObject(driver);
		PalantirContent handle = poi.setAndVerifyPlayerPosition(-40, -10, 3, true);
		poi.verifyCorrectPlayerPos(handle);
		poi.verifyPoiAppearOnMap();
	}

	@Test(groups = {"PalantirTest_002", "PalantirTests", "InteractiveMaps"})
	public void PalantirTest_002_SetPlayerPositionOutOfMap() {
		InteractiveMapsPageObject specialMap = new InteractiveMapsPageObject(driver);
		InteractiveMapPageObject selectedMap = specialMap.openMap(wikiURL, PalantirContent.PALANTIR_MAP);
		selectedMap.verifyMapOpened();
		PalantirComponentObject poi = new PalantirComponentObject(driver);
		PalantirContent handle = poi.setAndVerifyPlayerPosition(9300, 15000, 3, true);
		poi.verifyWrongPlayerPos(handle);
		poi.verifyPoiNotAppearOnMap();
	}

	@Test(groups = {"PalantirTest_003", "PalantirTests", "InteractiveMaps"})
	public void PalantirTest_003_PalantirSetAndRemovePlayerPosition() {
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

	@Test(groups = {"PalantirTest_004", "PalantirTests", "InteractiveMaps"})
	public void PalantirTest_004_PalantirSetHugeZoomVerifyError() {
		InteractiveMapsPageObject specialMap = new InteractiveMapsPageObject(driver);
		InteractiveMapPageObject selectedMap = specialMap.openMap(wikiURL, PalantirContent.PALANTIR_MAP);
		selectedMap.verifyMapOpened();
		PalantirComponentObject poi = new PalantirComponentObject(driver);
		PalantirContent handle = poi.setAndVerifyPlayerPosition(-40, -10, 3000, true);
		poi.verifyWrongZoomLevel(handle);
		poi.verifyPoiNotAppearOnMap();
	}

	@Test(groups = {"PalantirTest_005", "PalantirTests", "InteractiveMaps"})
	public void PalantirTest_005_PalantirUpdateMapPosition() {
		InteractiveMapsPageObject specialMap = new InteractiveMapsPageObject(driver);
		InteractiveMapPageObject selectedMap = specialMap.openMap(wikiURL, PalantirContent.PALANTIR_MAP);
		selectedMap.verifyMapOpened();
		PalantirComponentObject poi = new PalantirComponentObject(driver);
		PalantirContent handle = poi.setAndVerifyPlayerPosition(-40, -10, 3, true);
		poi.verifyCorrectPlayerPos(handle);
		handle = poi.updateMapPosition(-90, -10, 3);
		poi.verifyMapPositionUpdated(handle);
	}

	@Test(groups = {"PalantirTest_006", "PalantirTests", "InteractiveMaps"})
	public void PalantirTest_006_PalantirSetDecimalZoom() {
		InteractiveMapsPageObject specialMap = new InteractiveMapsPageObject(driver);
		InteractiveMapPageObject selectedMap = specialMap.openMap(wikiURL, PalantirContent.PALANTIR_MAP);
		selectedMap.verifyMapOpened();
		PalantirComponentObject poi = new PalantirComponentObject(driver);
		PalantirContent handle = poi.setAndVerifyPlayerPosition(-40, -10, 3.4, true);
		poi.verifyDecimalZoomLevel(handle);
		poi.verifyPoiNotAppearOnMap();
	}
}
