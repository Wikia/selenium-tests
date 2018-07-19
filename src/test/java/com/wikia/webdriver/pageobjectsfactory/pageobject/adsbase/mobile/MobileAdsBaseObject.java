package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.mobile;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.*;

public class MobileAdsBaseObject extends AdsBaseObject {

  private static final String MERCURY_ARTICLE_CONTAINER_SELECTOR = "#ember-container";

  public MobileAdsBaseObject() {
    super();

    if (driver.isChromeMobile()) {
      verifyMobileWiki();
    }

    Log.log("", "Page screenshot", true, driver);
  }

  public MobileAdsBaseObject(String page) {
    super(page);

    if (driver.isChromeMobile()) {
      verifyMobileWiki();
    }

    Log.log("", "Page screenshot", true, driver);
  }

  public void verifyNoAdInSlot(String slotName) {
    scrollToSlotOnMobile(slotName);
    WebElement slot = driver.findElement(By.id(slotName));
    waitForSlotCollapsed(slot);
    Log.log("AdInSlot", "Ad is not found in slot as expected.", true);
  }

  public void verifyNoSlotPresent(String slotName) {
    if (isElementOnPage(By.cssSelector("#" + slotName))) {
      throw new NoSuchElementException("Slot is added to the page");
    }
    Log.log("AdInSlot", "No slot found as expected", true);
  }

  public void verifySlotExpanded(String slotName) {
    By slotById = By.id(slotName);
    WebElement slot = driver.findElement(slotById);

    scrollToSlotOnMobile(slotName);
    wait.forElementVisible(slotById);

    if (checkIfSlotExpanded(slot)) {
      Log.log("AdInSlot", "Slot (" + slotName + ") expanded as expecting", true);
    } else {
      throw new NoSuchElementException("Slot (" + slotName + ") is collapsed - should be expanded");
    }
  }

  public void verifyImgAdLoadedInSlot(String slotName, String expectedImg) {
    scrollToSlotOnMobile(slotName);
    WebElement slot = driver.findElement(By.id(slotName));
    if (checkIfSlotExpanded(slot)) {
      String foundImg = getSlotImageAd(slot);
      Assertion.assertStringContains(foundImg, expectedImg);
    } else {
      throw new NoSuchElementException("Slot (" + slotName + ") is collapsed - should be expanded");
    }
    Log.log("AdInSlot", "Ad found in slot", true, driver);
  }

  /**
   * Checks if link to /wiki/articleName exists on the page and clicks it
   *
   * @param articleLinkName part of link to another wiki article
   */
  public void mercuryNavigateToAnArticle(String articleLinkName) {
    String articleLinkSelector = String.format("a[href^='/wiki/%s']", articleLinkName);
    if (isElementOnPage(By.cssSelector(articleLinkSelector))) {
      WebElement link = driver.findElement(By.cssSelector(articleLinkSelector));

      Log.log("mercuryNavigateToAnArticle()",
              String.format("Clicking: %s (%s)", link.getText(), link.getAttribute("href")),
              true,
              driver
      );

      link.click();
    } else {
      Log.warning("mercuryNavigateToAnArticle()",
                  "Could not find the link to: /wiki/" + articleLinkName
      );
    }
  }

  private void scrollToSlotOnMobile(String slotName) {
    JavascriptExecutor js = driver;
    JavascriptActions jsActions = new JavascriptActions();
    WebElement slot = driver.findElement(By.id(slotName));
    js.executeScript(
        "var element = document.getElementById(arguments[0]);" + "element.scrollIntoView();",
        slotName
    );

    jsActions.scrollElementIntoViewPort(slot);
  }

  private void verifyMobileWiki() {
    try {
      wait.forElementVisible(By.cssSelector(MERCURY_ARTICLE_CONTAINER_SELECTOR));
    } catch (TimeoutException e) {
      Log.warning("", "MOBILE WIKI FAILED TO LOAD");
      throw e;
    }
  }

  public void waitForSlot(String slotName) {
    wait.forElementVisible(By.cssSelector("#" + slotName));
  }
}
