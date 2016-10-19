package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.mobile;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

public class MobileAdsBaseObject extends AdsBaseObject {

  private static final String MERCURY_ARTICLE_CONTAINER_SELECTOR = "#ember-container";

  public MobileAdsBaseObject(WikiaWebDriver driver, String page) {
    super(driver, page);

    if (driver.isChromeMobile()) {
      verifyMercury();
    }

    PageObjectLogging.log("", "Page screenshot", true, driver);
  }

  public void verifyNoAdInSlot(String slotName) {
    scrollToSlotOnMobile(slotName);
    WebElement slot = driver.findElement(By.id(slotName));
    waitForSlotCollapsed(slot);
    PageObjectLogging.log("AdInSlot", "Ad is not found in slot as expected.", true);
  }

  public void verifyNoSlotPresent(String slotName) {
    if (isElementOnPage(By.cssSelector("#" + slotName))) {
      throw new NoSuchElementException("Slot is added to the page");
    }
    PageObjectLogging.log("AdInSlot", "No slot found as expected", true);
  }

  public void verifySlotExpanded(String slotName) {
    scrollToSlotOnMobile(slotName);
    WebElement slot = driver.findElement(By.id(slotName));
    if (checkIfSlotExpanded(slot)) {
      PageObjectLogging.log("AdInSlot", "Slot expanded as expecting", true);
    } else {
      throw new NoSuchElementException("Slot is collapsed - should be expanded");
    }
  }

  public void verifyImgAdLoadedInSlot(String slotName, String expectedImg) {
    scrollToSlotOnMobile(slotName);
    WebElement slot = driver.findElement(By.id(slotName));
    if (checkIfSlotExpanded(slot)) {
      String foundImg = getSlotImageAd(slot);
      Assertion.assertStringContains(foundImg, expectedImg);
    } else {
      throw new NoSuchElementException("Slot is collapsed - should be expanded");
    }
    PageObjectLogging.log("AdInSlot", "Ad found in slot", true, driver);
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

      PageObjectLogging.log(
          "mercuryNavigateToAnArticle()",
          String.format(
              "Clicking: %s (%s)",
              link.getText(),
              link.getAttribute("href")
          ),
          true,
          driver
      );

      link.click();
    } else {
      PageObjectLogging.logWarning(
          "mercuryNavigateToAnArticle()",
          "Could not find the link to: /wiki/" + articleLinkName
      );
    }
  }

  private void scrollToSlotOnMobile(String slotName) {
    JavascriptExecutor js = driver;
    js.executeScript(
        "var element = document.getElementById(arguments[0]);" +
        "var elementY = element.offsetTop;" +
        "var elementH = element.offsetHeight;" +
        "window.scrollTo(0, elementY - elementH);",
        slotName
    );
  }

  private void verifyMercury() {
    try {
      wait.forElementVisible(By.cssSelector(MERCURY_ARTICLE_CONTAINER_SELECTOR));
    } catch (TimeoutException e) {
      PageObjectLogging.logWarning("", "MERCURY FAILED TO LOAD");
      throw e;
    }
  }

  public void waitForSlot(String slotName) {
    wait.forElementVisible(By.cssSelector("#" + slotName));
  }
}
