package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.concurrent.TimeUnit;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.ContentPatterns.PalantirContent;

/**
 * @author Łukasz Nowak 
 */

public class PalantirComponentObject extends InteractiveMapPageObject {

	public PalantirComponentObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "iframe[name=wikia-interactive-map]")
	private WebElement mapFrame;
	@FindBy(css = "img[src*='player_location_marker.png']")
	private WebElement playerPoint;

	private PalantirContent getResponse(Object response, String methodName) {
		PalantirContent handle = null;
		try {			
			JSONObject json = new JSONObject(response.toString());
				handle = new PalantirContent(
							json.getString(PalantirContent.PONTO_MSG_SUCCESS),
							json.getString(PalantirContent.PONTO_MSG_RESPONSECODE),
							json.getString(PalantirContent.PONTO_MSG_MESSAGE)
						);
			PageObjectLogging.log(methodName, handle.getMessage(), true, driver);
		}catch (JSONException e) {
			PageObjectLogging.log(methodName, handle.getMessage(), false, driver);
		}
		return handle;		
	}

	public PalantirContent deletePlayerPosition() {
		waitForElementVisibleByElement(mapFrame);
		JavascriptExecutor jsexec = (JavascriptExecutor) driver;
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
		Object res = jsexec.executeAsyncScript(PalantirContent.PONTO_REMOVEPLAYER);
		return getResponse(res, "deletePlayerPosition");
	}

	public PalantirContent setAndVerifyPlayerPosition(double lat, double lng, double zoom, boolean centerMap) {
		waitForElementVisibleByElement(mapFrame);
		JavascriptExecutor jsexec = (JavascriptExecutor) driver;
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
		Object res = jsexec.executeAsyncScript(
					PalantirContent.PONTO_SETPLAYER,
					lat,
					lng,
					zoom,
					centerMap
					);
		return getResponse(res, "setAndVerifyPlayerPosition");
	}
	
	public PalantirContent updateMapPosition(double lat, double lng, int zoom) {
		waitForElementVisibleByElement(mapFrame);
		JavascriptExecutor jsexec = (JavascriptExecutor) driver;
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
		Object res = jsexec.executeAsyncScript(
					PalantirContent.PONTO_UPDATEPOSITION,
					lat,
					lng,
					zoom
					);
		return getResponse(res, "updateMapPosition");
	}

	public void verifyMapPositionUpdated(PalantirContent handle) {
		Assertion.assertEquals("true", handle.getSuccess());
		Assertion.assertEquals("200", handle.getResponseCode());
		Assertion.assertEquals(PalantirContent.PONTOMSG_MAPPOS_SUCCESS, handle.getMessage());
	}

	public void verifyCorrectPlayerPos(PalantirContent handle) {
		Assertion.assertEquals("true", handle.getSuccess());
		Assertion.assertEquals("200", handle.getResponseCode());
		Assertion.assertEquals(PalantirContent.PONTOMSG_PLAYER_SUCCESS, handle.getMessage());
	}

	public void verifyWrongPlayerPos(PalantirContent handle) {
		Assertion.assertEquals("false", handle.getSuccess());
		Assertion.assertEquals("422", handle.getResponseCode());
		Assertion.assertEquals(PalantirContent.PONTOMSG_MAP_OUTOFBOUNDARIES, handle.getMessage());
	}
	
	public void verifyWrongZoomLevel(PalantirContent handle) {
		Assertion.assertEquals("false", handle.getSuccess());
		Assertion.assertEquals("422", handle.getResponseCode());
		Assertion.assertEquals(PalantirContent.PONTOMSG_WRONG_ZOOM, handle.getMessage());
	}
	
	public void verifyDecimalZoomLevel(PalantirContent handle) {
		Assertion.assertEquals("false", handle.getSuccess());
		Assertion.assertEquals("422", handle.getResponseCode());
		Assertion.assertEquals(PalantirContent.PONTOMSG_WRONG_PARAMETER, handle.getMessage());
	}

	public void verifyPlayerPosDeleted(PalantirContent handle) {
		Assertion.assertEquals("true", handle.getSuccess());
		Assertion.assertEquals("200", handle.getResponseCode());
		Assertion.assertEquals(PalantirContent.PONTOMSG_REMOVEPLAYER, handle.getMessage());
	}

	public void verifyPoiAppearOnMap() {
		waitForElementVisibleByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		waitForElementVisibleByElement(playerPoint);
		Assertion.assertEquals(checkIfElementOnPage(playerPoint), true);
		driver.switchTo().defaultContent();
	}

	public void verifyPoiNotAppearOnMap() {
		waitForElementVisibleByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		Assertion.assertEquals(checkIfElementOnPage(playerPoint), false);
		driver.switchTo().defaultContent();
	}
}
