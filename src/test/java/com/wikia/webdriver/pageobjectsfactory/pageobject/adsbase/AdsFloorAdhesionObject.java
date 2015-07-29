package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdsFloorAdhesionObject extends AdsBaseObject {

  private final String FLOOR_ADHESION_CSS = "#ext-wikia-adEngine-template-footer";
  private final String
      FLOOR_ADHESION_AD_FRAME_CSS =
      "#ext-wikia-adEngine-template-footer .ad iframe";
  private final String FLOOR_ADHESION_IMAGE_IN_FRAME_CSS = "img";
  private final String FLOOR_ADHESION_CLOSE_CSS = "#ext-wikia-adEngine-template-footer .close";
  private final String WIKIA_BAR_CSS = "#WikiaBar";

  public AdsFloorAdhesionObject(WebDriver driver, String testedPage) {
    super(driver);
    getUrl(testedPage, true);
  }

  public void verifyFloorAdhesionPresent(String expectedSlotName, String expectedLineItemId,
                                         String expectedCreativeId) {
    verifyGptAdInSlot(expectedSlotName, expectedLineItemId, expectedCreativeId);
    waitForElementByCss(FLOOR_ADHESION_CSS);
    PageObjectLogging.log(
        "Check visibility of Floor Adhesion",
        "Floor Adhesion should be displayed",
        true
    );
  }

  public void verifyThereIsNoFloorAdhesion() {
    waitForElementNotVisibleByElement(driver.findElement(By.cssSelector(FLOOR_ADHESION_CSS)));
    PageObjectLogging.log(
        "Check visibility",
        "Clicking Floor Adhesion close button hides ad unit",
        true
    );
  }

  public AdsFloorAdhesionObject clickFloorAdhesion() {
    WebElement iframeAd = driver.findElement(By.cssSelector(FLOOR_ADHESION_AD_FRAME_CSS));
    driver.switchTo().frame(iframeAd);
    driver.findElement(By.cssSelector(FLOOR_ADHESION_IMAGE_IN_FRAME_CSS)).click();
    driver.switchTo().defaultContent();
    return this;
  }

  public AdsFloorAdhesionObject clickFloorAdhesionModalClose(
      String floorAdhesionModalCloseSelector
  ) {
    driver.findElement(By.cssSelector(floorAdhesionModalCloseSelector)).click();
    return this;
  }

  public AdsFloorAdhesionObject clickFloorAdhesionClose() {
    driver.findElement(By.cssSelector(FLOOR_ADHESION_CLOSE_CSS)).click();
    return this;
  }

  public void verifyModalOpened(String floorAdhesionModalSelector) {
    waitForElementByCss(floorAdhesionModalSelector);
    PageObjectLogging.log(
        "Check visibility",
        "Clicking Floor Adhesion opens light-box",
        true
    );
  }

  public void verifyThereIsNoModal(String floorAdhesionModalSelector) {
    waitForElementNotPresent(By.cssSelector(floorAdhesionModalSelector));
    PageObjectLogging.log(
        "Check visibility",
        "Clicking light-box close button hides light-box",
        true
    );
  }

  public void verifyThereIsNoWikiaBar(String browser) {
    if ("CHROMEMOBILEMERCURY".equalsIgnoreCase(browser)) {
      // Mercury does not have WikiaBar
      // There should be better way to verify skin - remove it after QAART-608 is done
      PageObjectLogging.log(
          "Check visibility of Wikia Bar",
          "It is Mercury skin with no Wikia Bar",
          true
      );
      return;
    }

    waitForElementNotVisibleByElement(driver.findElement(By.cssSelector(WIKIA_BAR_CSS)));
    PageObjectLogging.log(
        "Check visibility of Wikia Bar",
        "There should be no Wikia Bar when Floor Adhesion is visible",
        true
    );
  }
}
