package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.logging.Log;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdsFloorAdhesionObject extends AdsBaseObject {

  private static final String FLOOR_ADHESION_CSS = "#invisible_high_impact_2";
  private static final String FLOOR_ADHESION_CLOSE_CSS = "#invisible_high_impact_2 .button-close";
  private static final String WIKIA_BAR_CSS = "#WikiaBar";

  public AdsFloorAdhesionObject(WebDriver driver, String testedPage) {
    super();
    getUrl(testedPage, true);
  }

  public void verifyFloorAdhesionPresent(
      String expectedSlotName, String expectedLineItemId, String expectedCreativeId
  ) {
    verifyGptAdInSlot(expectedSlotName, expectedLineItemId, expectedCreativeId);
    wait.forElementVisible(By.cssSelector(FLOOR_ADHESION_CSS));
    Log.log("Check visibility of Floor Adhesion", "Floor Adhesion should be displayed", true);
  }

  public void verifyThereIsNoFloorAdhesion() {
    waitForElementNotVisibleByElement(driver.findElement(By.cssSelector(FLOOR_ADHESION_CSS)));
    Log.log("Check visibility", "Clicking Floor Adhesion close button hides ad unit", true);
  }

  public AdsFloorAdhesionObject clickFloorAdhesionClose() {
    driver.findElement(By.cssSelector(FLOOR_ADHESION_CLOSE_CSS)).click();
    return this;
  }

  public void verifyThereIsNoWikiaBar(String browser) {
    if (driver.isChromeMobile()) {
      // Mercury does not have WikiaBar
      Log.log("Check visibility of Wikia Bar", "It is Mercury skin with no Wikia Bar", true);
      return;
    }

    waitForElementNotVisibleByElement(driver.findElement(By.cssSelector(WIKIA_BAR_CSS)));
    Log.log(
        "Check visibility of Wikia Bar",
        "There should be no Wikia Bar when Floor Adhesion is visible",
        true
    );
  }
}
