package com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps;

import com.wikia.webdriver.common.contentpatterns.PalantirContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PalantirComponentObject extends InteractiveMapPageObject {

  @FindBy(css = "iframe[name=wikia-interactive-map]")
  private WebElement mapFrame;
  @FindBy(css = "img[src*='player_location_marker.png']")
  private WebElement playerPoint;

  public PalantirComponentObject(WebDriver driver) {
    super();
  }

  private PalantirContent getResponse(Object response, String methodName) {
    Map<String, String> map = (Map) response;
    PalantirContent handle = new PalantirContent(
        String.valueOf(map.get(PalantirContent.PONTO_MSG_SUCCESS)),
        String.valueOf(map.get(PalantirContent.PONTO_MSG_RESPONSECODE)),
        map.get(PalantirContent.PONTO_MSG_MESSAGE)
    );
    PageObjectLogging.log(methodName, handle.getMessage(), true, driver);
    return handle;
  }

  public PalantirContent deletePlayerPosition() {
    wait.forElementVisible(mapFrame);
    JavascriptExecutor jsexec = (JavascriptExecutor) driver;
    driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
    Object res = jsexec.executeAsyncScript(PalantirContent.PONTO_REMOVEPLAYER);
    return getResponse(res, "deletePlayerPosition");
  }

  public PalantirContent setAndVerifyPlayerPosition(double lat, double lng, double zoom,
                                                    boolean centerMap) {
    wait.forElementVisible(mapFrame);
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
    wait.forElementVisible(mapFrame);
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
    Assertion.assertTrue(handle.getSuccess());
    Assertion.assertEquals(handle.getResponseCode(), "200");
    Assertion.assertEquals(handle.getMessage(), PalantirContent.PONTOMSG_MAPPOS_SUCCESS);
  }

  public void verifyCorrectPlayerPos(PalantirContent handle) {
    Assertion.assertTrue(handle.getSuccess());
    Assertion.assertEquals(handle.getResponseCode(), "200");
    Assertion.assertEquals(handle.getMessage(), PalantirContent.PONTOMSG_PLAYER_SUCCESS);
  }

  public void verifyWrongPlayerPos(PalantirContent handle) {
    Assertion.assertFalse(handle.getSuccess());
    Assertion.assertEquals(handle.getResponseCode(), "422");
    Assertion.assertEquals(handle.getMessage(), PalantirContent.PONTOMSG_MAP_OUTOFBOUNDARIES);
  }

  public void verifyWrongZoomLevel(PalantirContent handle) {
    Assertion.assertFalse(handle.getSuccess());
    Assertion.assertEquals(handle.getResponseCode(), "422");
    Assertion.assertEquals(handle.getMessage(), PalantirContent.PONTOMSG_WRONG_ZOOM);
  }

  public void verifyDecimalZoomLevel(PalantirContent handle) {
    Assertion.assertFalse(handle.getSuccess());
    Assertion.assertEquals(handle.getResponseCode(), "422");
    Assertion.assertEquals(handle.getMessage(), PalantirContent.PONTOMSG_WRONG_PARAMETER);
  }

  public void verifyPlayerPosDeleted(PalantirContent handle) {
    Assertion.assertTrue(handle.getSuccess());
    Assertion.assertEquals(handle.getResponseCode(), "200");
    Assertion.assertEquals(handle.getMessage(), PalantirContent.PONTOMSG_REMOVEPLAYER);
  }

  public void verifyPoiAppearOnMap() {
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    wait.forElementVisible(playerPoint);
    Assertion.assertEquals(isElementOnPage(playerPoint), true);
    driver.switchTo().defaultContent();
  }

  public void verifyPoiNotAppearOnMap() {
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    Assertion.assertEquals(isElementOnPage(playerPoint), false);
    driver.switchTo().defaultContent();
  }
}
