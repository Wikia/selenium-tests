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
import com.wikia.webdriver.Common.ContentPatterns.InteractiveMapsContent;

/**
 * @author Łukasz Nowak (Dyktus)
 */

public class PalantirObject extends InteractiveMapPageObject {

	public PalantirObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "iframe[name=wikia-interactive-map]")
	private WebElement mapFrame;
	@FindBy(css = "img[src*='player_location_marker.png']")
	private WebElement playerPoint;

	public void deletePlayerPosition() {
		waitForElementVisibleByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		driver.switchTo().defaultContent();

		JavascriptExecutor jsexec = (JavascriptExecutor) driver;
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
		Object res = jsexec.executeAsyncScript(InteractiveMapsContent.PONTO_REMOVEPLAYER);

		try {
			JSONObject json = new JSONObject(res.toString());
			verifyPlayerPosDeleted(
					json.getString(InteractiveMapsContent.PONTO_MSG_SUCCESS), 
					json.getString(InteractiveMapsContent.PONTO_MSG_RESPONSECODE), 
					json.getString(InteractiveMapsContent.PONTO_MSG_MESSAGE)
			);
			PageObjectLogging.log("deletePlayerPosition", json.getString(InteractiveMapsContent.PONTO_MSG_MESSAGE), true, driver);
		}catch (JSONException e) {
			PageObjectLogging.log("deletePlayerPosition", "Player position was not deleted", false, driver);				
		}
	}

	public void setAndVerifyPlayerPosition(double lat, double lng, double zoom, boolean centerMap, boolean expectedResult) {
		waitForElementVisibleByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		driver.switchTo().defaultContent();

		JavascriptExecutor jsexec = (JavascriptExecutor) driver;
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
		Object res = jsexec.executeAsyncScript(
						InteractiveMapsContent.PONTO_SETPLAYER, 
						lat, 
						lng, 
						zoom, 
						centerMap
					);
		if (expectedResult) {
			try {
				JSONObject json = new JSONObject(res.toString());
				verifyCorrectPlayerPos(
						json.getString(InteractiveMapsContent.PONTO_MSG_SUCCESS), 
						json.getString(InteractiveMapsContent.PONTO_MSG_RESPONSECODE), 
						json.getString(InteractiveMapsContent.PONTO_MSG_MESSAGE)
				);
				PageObjectLogging.log(
						"setAndVerifyPlayerPosition", 
						json.getString(InteractiveMapsContent.PONTO_MSG_MESSAGE), 
						true, 
						driver
				);
			}catch (JSONException e) {
				PageObjectLogging.log("setAndVerifyPlayerPosition", "Position was not set", false, driver);
			}
		}else {
			try {
				JSONObject json = new JSONObject(res.toString());
				verifyWrongPlayerPos(
						json.getString(InteractiveMapsContent.PONTO_MSG_SUCCESS), 
						json.getString(InteractiveMapsContent.PONTO_MSG_RESPONSECODE), 
						json.getString(InteractiveMapsContent.PONTO_MSG_MESSAGE)
				);
				PageObjectLogging.log(
						"setAndVerifyPlayerPosition", 
						"Position was not set. Error message: "	+ json.getString(InteractiveMapsContent.PONTO_MSG_MESSAGE), 
						true, 
						driver
				);
			}catch (JSONException e) {
				PageObjectLogging.log(
						"setAndVerifyPlayerPosition", 
						"Position was set. It was not expected.", 
						false, 
						driver
				);
			}
		}
	}

	public void updateMapPosition(double lat, double lng, int zoom) {
		waitForElementVisibleByElement(mapFrame);
		driver.switchTo().frame(mapFrame);
		driver.switchTo().defaultContent();

		JavascriptExecutor jsexec = (JavascriptExecutor) driver;
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
		Object res = jsexec.executeAsyncScript(
						InteractiveMapsContent.PONTO_UPDATEPOSITION, 
						lat, 
						lng, 
						zoom
					);
		try {
			JSONObject json = new JSONObject(res.toString());
			verifyMapPositionUpdated(
					json.getString(InteractiveMapsContent.PONTO_MSG_SUCCESS), 
					json.getString(InteractiveMapsContent.PONTO_MSG_RESPONSECODE), 
					json.getString(InteractiveMapsContent.PONTO_MSG_MESSAGE)
			);
			PageObjectLogging.log(
					"updateMapPosition", 
					json.getString(InteractiveMapsContent.PONTO_MSG_MESSAGE), 
					true
			);
		}catch (JSONException e) {
			PageObjectLogging.log("updateMapPosition", "Map position was not changed", true);
		}
	}

	public void verifyMapPositionUpdated(String success, String responseCode, String msg) {
		Assertion.assertEquals("true", success);
		Assertion.assertEquals("200", responseCode);
		Assertion.assertEquals(msg.isEmpty(), false);
	}

	public void verifyCorrectPlayerPos(String success, String responseCode, String msg) {
		Assertion.assertEquals("true", success);
		Assertion.assertEquals("200", responseCode);
		Assertion.assertEquals(msg.isEmpty(), false);
	}

	public void verifyWrongPlayerPos(String success, String responseCode, String msg) {
		Assertion.assertEquals("false", success);
		Assertion.assertEquals("422", responseCode);
		Assertion.assertEquals(msg.isEmpty(), false);
	}

	public void verifyPlayerPosDeleted(String success, String responseCode, String msg) {
		Assertion.assertEquals("true", success);
		Assertion.assertEquals("200", responseCode);
		Assertion.assertEquals(msg.isEmpty(), false);
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
