package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.mobile;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsComparison;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * Bogna 'bognix' Knychala
 */
public class MobileAdsBaseObject extends AdsBaseObject {

  private static final String SMART_BANNER_SELECTOR = ".android.smartbanner";
  private static final String FLITE_MASK_SELECTOR = ".flite-mask";
  private static final String CELTRA_MASK_SELECTOR = "body > div[style*=position][style*=z-index]" +
                                                     "[style*='left: 0'][style*='bottom: auto'][style*='right: auto']";
  private static final String MERCURY_ARTICLE_CONTAINER_SELECTOR = "#ember-container";

  private AdsComparison adsComparison;

  public MobileAdsBaseObject(WebDriver driver, String page) {
    super(driver, page);
    adsComparison = new AdsComparison();

    if (getBrowser().equals("CHROMEMOBILEMERCURY")) {
      verifyMercury();
    }

    PageObjectLogging.log("", "Page screenshot", true, driver);
  }

  @Override
  protected void setWindowSizeAndroid() {
    try {
      driver.manage().window().setSize(new Dimension(360, 640));
    } catch (WebDriverException ex) {
      PageObjectLogging.log(
          "ResizeWindowForMobile",
          "Resize window method not available - possibly running on real device",
          true
      );
    }
  }

  public void verifyMobileTopLeaderboard() {
    removeElementIfPresent(SMART_BANNER_SELECTOR); // Only works for WikiaMobile

    waitForSlotExpanded(presentLeaderboard);

    if (!adsComparison.isAdVisible(presentLeaderboard, presentLeaderboardSelector, driver)) {
      extractGptInfo(presentLeaderboardSelector);

      if (checkIfElementOnPage(CELTRA_MASK_SELECTOR)) {
        PageObjectLogging.logWarning("Special ad", "Celtra");
        return;
      }

      if (checkIfElementOnPage(FLITE_MASK_SELECTOR)) {
        PageObjectLogging.logWarning("Special ad", "Flite");
        return;
      }

      throw new NoSuchElementException(
          "No ad detected in selector: "
          + presentLeaderboardSelector
      );
    }

    extractGptInfo(presentLeaderboardSelector);
  }

  public void verifyNoAdInSlot(String slotName) {
    scrollToSlotOnMobile(slotName);
    WebElement slot = driver.findElement(By.id(slotName));
    waitForSlotCollapsed(slot);
    PageObjectLogging.log("AdInSlot", "Ad is not found in slot as expected.", true);
  }

  public void verifyNoSlotPresent(String slotName) {
    if (checkIfElementOnPage("#" + slotName)) {
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
    if (checkIfElementOnPage(articleLinkSelector)) {
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

      scrollToElement(link);
      link.click();
    } else {
      PageObjectLogging.logWarning(
          "mercuryNavigateToAnArticle()",
          "Could not find the link to: /wiki/" + articleLinkName
      );
    }
  }

  private void removeElementIfPresent(String cssSelector) {
    if (checkIfElementOnPage(cssSelector)) {
      PageObjectLogging.log("Removing element", cssSelector, true);
      WebElement element = driver.findElement(By.cssSelector(cssSelector));
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("$(arguments[0]).css('display', 'none')", element);
      waitForElementNotVisibleByElement(element);
    }
  }

  private void scrollToSlotOnMobile(String slotName) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript(
        "var elementY = document.getElementById(arguments[0]).offsetTop;" +
        "window.scrollTo(0, elementY);",
        slotName
    );
  }

  private void verifyMercury() {
    try {
      waitForElementByCss(MERCURY_ARTICLE_CONTAINER_SELECTOR);
    } catch (TimeoutException e) {
      PageObjectLogging.logWarning("", "MERCURY FAILED TO LOAD");
      throw e;
    }
  }
}
