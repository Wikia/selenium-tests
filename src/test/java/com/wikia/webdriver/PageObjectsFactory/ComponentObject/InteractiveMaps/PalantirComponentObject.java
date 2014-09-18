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

	public PalantirContent deletePlayerPosition() {
		waitForElementVisibleByElement(mapFrame);
		driver.switchTo().activeElement();

		JavascriptExecutor jsexec = (JavascriptExecutor) driver;
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
		Object res = jsexec.executeAsyncScript(PalantirContent.PONTO_REMOVEPLAYER);
		PalantirContent handle = new PalantirContent();
		try {
			JSONObject json = new JSONObject(res.toString());
			handle.success = json.getString(PalantirContent.PONTO_MSG_SUCCESS); 
			handle.responseCode = json.getString(PalantirContent.PONTO_MSG_RESPONSECODE); 
			handle.message = json.getString(PalantirContent.PONTO_MSG_MESSAGE);
			
			PageObjectLogging.log(
					"deletePlayerPosition", 
					handle.message, 
					true, 
					driver
			);
		}catch (JSONException e) {
			PageObjectLogging.log(
					"deletePlayerPosition", 
					"Player position was not deleted", 
					false, 
					driver
			);				
		}
		return handle;
	}

	public PalantirContent setAndVerifyPlayerPosition(double lat, double lng, double zoom, boolean centerMap) {
		waitForElementVisibleByElement(mapFrame);
		driver.switchTo().activeElement();
		
		JavascriptExecutor jsexec = (JavascriptExecutor) driver;
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
		Object res = jsexec.executeAsyncScript(
						PalantirContent.PONTO_SETPLAYER, 
						lat, 
						lng, 
						zoom, 
						centerMap
					);
		PalantirContent handle = new PalantirContent();
			try {
				JSONObject json = new JSONObject(res.toString());
				handle.success = json.getString(PalantirContent.PONTO_MSG_SUCCESS); 
				handle.responseCode = json.getString(PalantirContent.PONTO_MSG_RESPONSECODE); 
				handle.message = json.getString(PalantirContent.PONTO_MSG_MESSAGE);
				PageObjectLogging.log(
						"setAndVerifyPlayerPosition", 
						handle.message, 
						true, 
						driver
				);
			}catch (JSONException e) {
				PageObjectLogging.log(
						"setAndVerifyPlayerPosition", 
						"Position was not set", 
						false, 
						driver
				);
			}
		return handle;
	}
	
	public PalantirContent updateMapPosition(double lat, double lng, int zoom) {
		waitForElementVisibleByElement(mapFrame);
		driver.switchTo().activeElement();
		
		JavascriptExecutor jsexec = (JavascriptExecutor) driver;
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
		Object res = jsexec.executeAsyncScript(
						PalantirContent.PONTO_UPDATEPOSITION, 
						lat, 
						lng, 
						zoom
					);
		PalantirContent handle = new PalantirContent();
		try {
			JSONObject json = new JSONObject(res.toString());
			handle.success = json.getString(PalantirContent.PONTO_MSG_SUCCESS);
			handle.responseCode = json.getString(PalantirContent.PONTO_MSG_RESPONSECODE); 
			handle.message = json.getString(PalantirContent.PONTO_MSG_MESSAGE);
			PageObjectLogging.log(
					"updateMapPosition", 
					handle.message, 
					true
			);
		}catch (JSONException e) {
			PageObjectLogging.log(
					"updateMapPosition", 
					"Map position was not changed", 
					true);
		}
		return handle;
	}

	public void verifyMapPositionUpdated(String success, String responseCode, String msg) {
		Assertion.assertEquals("true", success);
		Assertion.assertEquals("200", responseCode);
		Assertion.assertEquals(PalantirContent.PONTOMSG_MAPPOS_SUCCESS, msg);
	}

	public void verifyCorrectPlayerPos(String success, String responseCode, String msg) {
		Assertion.assertEquals("true", success);
		Assertion.assertEquals("200", responseCode);
		Assertion.assertEquals(PalantirContent.PONTOMSG_PLAYER_SUCCESS, msg);
	}

	public void verifyWrongPlayerPos(String success, String responseCode, String msg) {
		Assertion.assertEquals("false", success);
		Assertion.assertEquals("422", responseCode);
		Assertion.assertEquals(PalantirContent.PONTOMSG_PLAYER_OUTOFBOUNDARIES, msg);
	}
	
	public void verifyWrongZoomLevel(String success, String responseCode, String msg) {
		Assertion.assertEquals("false", success);
		Assertion.assertEquals("422", responseCode);
		Assertion.assertEquals(PalantirContent.PONTOMSG_WRONG_ZOOM, msg);
	}
	
	public void verifyDecimalZoomLevel(String success, String responseCode, String msg) {
		Assertion.assertEquals("false", success);
		Assertion.assertEquals("422", responseCode);
		Assertion.assertEquals(PalantirContent.PONTOMSG_WRONG_PARAMETER, msg);
	}

	public void verifyPlayerPosDeleted(String success, String responseCode, String msg) {
		Assertion.assertEquals("true", success);
		Assertion.assertEquals("200", responseCode);
		Assertion.assertEquals(PalantirContent.PONTOMSG_REMOVEPLAYER, msg);
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
