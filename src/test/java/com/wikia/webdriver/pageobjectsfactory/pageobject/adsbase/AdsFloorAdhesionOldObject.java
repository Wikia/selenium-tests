package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.logging.Log;

import org.openqa.selenium.*;

public class AdsFloorAdhesionOldObject extends AdsBaseObject {

  private static final String FLOOR_ADHESION_CSS = "#ext-wikia-adEngine-template-floor, #invisible_high_impact_2";
  private static final String
      FLOOR_ADHESION_CLOSE_CSS
      = "#invisible_high_impact_2 .button-close";
  private static final String WIKIA_BAR_CSS = "#WikiaBar";
  private static final By FLOOR_ADHESION_CLOSE_SELECTOR = By.cssSelector(FLOOR_ADHESION_CLOSE_CSS);

  public AdsFloorAdhesionOldObject(WebDriver driver, String testedPage, Dimension resolution) {
    super(driver, testedPage, resolution);
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


  public AdsFloorAdhesionOldObject clickFloorAdhesionClose() {
    wait.forElementPresent(FLOOR_ADHESION_CLOSE_SELECTOR).click();
    return this;
  }

  public void verifyThereIsNoWikiaBar(Boolean isMobile) {
    if (isMobile) {
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
