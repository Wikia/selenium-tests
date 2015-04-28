package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.wikia.webdriver.common.core.Assertion.assertStringContains;
import static org.testng.Assert.assertFalse;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class AdsEvolveObject extends AdsBaseObject {

  private static final String EVOLVE_SELECTOR = " script[src*=\"4403ad\"]";
  private static final String EVOLVE_DFP_URL = "ad.doubleclick.net/N4403/";

  public AdsEvolveObject(WebDriver driver, String page) {
    // INVISIBLE_SKIN works only with big resolution.
    super(driver, page, new Dimension(1366, 768));
  }

  public void verifyEvolveInSlot(String slotName) {
    String slotSelector = AdsContent.getSlotSelector(slotName);
    waitForElementPresenceByBy(By.cssSelector(slotSelector + EVOLVE_SELECTOR));
    PageObjectLogging.log("Evolve", slotSelector + " slot has Evolve.", true, driver);
  }

  public void verifyNoEvolveInSlot(String slotName) {
    String slotSelector = AdsContent.getSlotSelector(slotName);
    waitForElementNotPresent(slotSelector + EVOLVE_SELECTOR);
    PageObjectLogging.log("Evolve", slotSelector + " slot doesn't have Evolve.", true, driver);
  }

  public void verifyEvolveCall() {
    verifyEvolveInSlot(AdsContent.TOP_LB);
    verifyEvolveInSlot(AdsContent.MEDREC);
    verifyEvolveInSlot(AdsContent.LEFT_SKYSCRAPPER_2);
    verifyEvolveInSlot(AdsContent.INVISIBLE_SKIN);
    verifyNoEvolveInSlot(AdsContent.FLOATING_MEDREC);
    verifyNoEvolveInSlot(AdsContent.PREFOOTER_LEFT);
    verifyNoEvolveInSlot(AdsContent.PREFOOTER_RIGHT);
  }

  public void verifyEvolveHopInSlot(String slotName) {
    WebElement adSlot = driver.findElement(By.id(slotName));
    java.util.List<WebElement> providers = adSlot.findElements(By.tagName("div"));
    WebElement evolveDiv = providers.get(0);
    Boolean evolveIframePresent = adSlot.findElements(
        By.cssSelector("iframe[src*=\'" + EVOLVE_DFP_URL + "\']")).size() > 0;
    assertFalse(evolveIframePresent);
    WebElement evolveHopScript = evolveDiv.findElement(
        By.cssSelector("script[type='text/javascript']"));
    String evolveHopMessage = String.format("window.evolve_hop('%s')", slotName);
    assertStringContains(evolveHopMessage, evolveHopScript.getAttribute("src"));
  }
}
