package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.mobile;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.LOG;
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

    if ("CHROMEMOBILEMERCURY".equals(Configuration.getBrowser())) {
      verifyMercury();
    }

    LOG.logResult("", "Page screenshot", true, driver);
  }

  @Override
  protected void setWindowSizeAndroid() {
    try {
      driver.manage().window().setSize(new Dimension(360, 640));
    } catch (WebDriverException ex) {
      LOG.result(
          "ResizeWindowForMobile",
          "Resize window method not available - possibly running on real device",
          true
      );
    }
  }

  public void verifyMobileTopLeaderboard() {
    removeElementIfPresent(SMART_BANNER_SELECTOR); // Only works for WikiaMobile

    wait.forElementVisible(presentLeaderboard);
    waitForSlotExpanded(presentLeaderboard);

    if (!adsComparison.isAdVisible(presentLeaderboard, presentLeaderboardSelector, driver)) {
      extractGptInfo(presentLeaderboardSelector);

      if (isElementOnPage(By.cssSelector(CELTRA_MASK_SELECTOR))) {
        LOG.warning("Special ad", "Celtra");
        return;
      }

      if (isElementOnPage(By.cssSelector(FLITE_MASK_SELECTOR))) {
        LOG.warning("Special ad", "Flite");
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
    LOG.success("AdInSlot", "Ad is not found in slot as expected.");
  }

  public void verifyNoSlotPresent(String slotName) {
    if (isElementOnPage(By.cssSelector("#" + slotName))) {
      throw new NoSuchElementException("Slot is added to the page");
    }
    LOG.success("AdInSlot", "No slot found as expected");
  }

  public void verifySlotExpanded(String slotName) {
    scrollToSlotOnMobile(slotName);
    WebElement slot = driver.findElement(By.id(slotName));
    if (checkIfSlotExpanded(slot)) {
      LOG.success("AdInSlot", "Slot expanded as expecting");
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
    LOG.logResult("AdInSlot", "Ad found in slot", true, driver);
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

      LOG.logResult(
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
      LOG.warning(
          "mercuryNavigateToAnArticle()",
          "Could not find the link to: /wiki/" + articleLinkName
      );
    }
  }

  private void removeElementIfPresent(String cssSelector) {
    if (isElementOnPage(By.cssSelector(cssSelector))) {
      LOG.success("Removing element", cssSelector);
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
      wait.forElementVisible(By.cssSelector(MERCURY_ARTICLE_CONTAINER_SELECTOR));
    } catch (TimeoutException e) {
      LOG.warning("", "MERCURY FAILED TO LOAD");
      throw e;
    }
  }

  public void waitForSlot(String slotName){
    wait.forElementVisible(By.cssSelector("#" + slotName));
  }
}
