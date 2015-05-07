package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.mobile;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsComparison;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Bogna 'bognix' Knychala
 */
public class MobileAdsBaseObject extends AdsBaseObject {

  private static final String SMART_BANNER_SELECTOR = ".smartbanner.android";
  private static final String FLITE_MASK_SELECTOR = ".flite-mask";
  private static final String MERCURY_WIKI_TITLE_SELECTOR = ".wiki-title a";
  private static final String WIKI_LINK_SELECTOR = "a[href^='/wiki/']";
  private static final String MERCURY_LOADING_OVERLAY_SELECTOR = ".loading-overlay";

  private AdsComparison adsComparison;

  public MobileAdsBaseObject(WebDriver driver, String page) {
    super(driver, page);
    adsComparison = new AdsComparison();
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
    PageObjectLogging.log("GeoEdge", getCountry(), true);
    extractGptInfo(presentLeaderboardSelector);
    removeSmartBanner();
    if (checkIfElementOnPage(FLITE_MASK_SELECTOR)) {
      PageObjectLogging.log(
          "FliteAd", "Page contains the flite ad", true, driver
      );
      return;
    }
    if (!checkIfSlotExpanded(presentLeaderboard)) {
      throw new NoSuchElementException(
          String.format("Slot is not expanded - ad is not there; CSS selector: %s",
                        presentLeaderboardSelector)
      );
    }
    if (!adsComparison.isAdVisible(presentLeaderboard, presentLeaderboardSelector, driver)) {
      throw new NoSuchElementException(
          "Screenshots of element on/off look the same."
          + "Most probable ad is not present; CSS "
          + presentLeaderboardSelector
      );
    }
  }

  public void verifyNoAdInSlot(String slotName) {
    scrollToSlotOnMobile(slotName);
    WebElement slot = driver.findElement(By.id(slotName));
    waitForSlotCollapsed(slot);
    PageObjectLogging.log("AdInSlot", "Ad is not found in slot as expected.", true);
  }

  public void verifyNoSlotPresent(String slotName) {
    if (checkIfElementOnPage("#" + slotName)) {
      throw new NoSuchElementException("No slot found as expected (" + slotName + ")");
    }
    PageObjectLogging.log("AdInSlot", "Slot is added to the page", true);
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
      Assertion.assertStringContains(expectedImg, foundImg);
    } else {
      throw new NoSuchElementException("Slot is collapsed - should be expanded");
    }
    PageObjectLogging.log("AdInSlot", "Ad found in slot", true, driver);
  }

  /**
   * Scrolls to the main page link (to hide top bars) and clicks the link
   */
  public void mercuryNavigateToMainPage() {
    if (checkIfElementOnPage(MERCURY_WIKI_TITLE_SELECTOR)) {
      WebElement mainPageLink = driver.findElement(By.cssSelector(MERCURY_WIKI_TITLE_SELECTOR));
      scrollToElement(mainPageLink);

      PageObjectLogging.log(
        "mercuryNavigateToMainPage()",
        String.format(
          "Clicking main page link: %s (%s)",
          mainPageLink.getText(),
          mainPageLink.getAttribute("href")
        ),
        true,
        driver
      );

      mainPageLink.click();
    }
  }

  /**
   * Gets list of /wiki/* links on page and clicks the first valid
   */
  public void mercuryNavigateToAnArticle() {
    if (checkIfElementOnPage(WIKI_LINK_SELECTOR)) {
      String notArticlePattern = ".*\\/wiki\\/.*\\:.*";
      List<WebElement> links = driver.findElements(By.cssSelector(WIKI_LINK_SELECTOR));

      PageObjectLogging.log(
        "mercuryNavigateToAnArticle()",
        "Found " + links.size() + " internal links",
        true,
        driver
      );

      for(WebElement link : links) {
        String href = link.getAttribute("href");
        Boolean isArticle = !href.matches(notArticlePattern);
        Boolean isMainPage = href.equals(getMercuryMainPageHref());

        if (isArticle && !isMainPage) {
          PageObjectLogging.log(
            "mercuryNavigateToAnArticle()",
            String.format(
              "Link found, clicking: %s (%s)",
              link.getText(),
              link.getAttribute("href")
            ),
            true,
            driver
          );

          scrollToElement(link);
          link.click();
          return;
        }
      }
    } else {
      PageObjectLogging.logWarning("mercuryNavigateToAnArticle()", "No links on main page!");
    }
  }

  public void mercuryWaitForPageToLoad() {
    driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
    try {
      PageObjectLogging.log("mercuryWaitForPageToLoad", "Waiting till loaded...", true, driver);
      wait.until(
        ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(MERCURY_LOADING_OVERLAY_SELECTOR))
      );
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  private String getMercuryMainPageHref() {
    if (checkIfElementOnPage(MERCURY_WIKI_TITLE_SELECTOR)) {
      WebElement mainPageLink = driver.findElement(By.cssSelector(MERCURY_WIKI_TITLE_SELECTOR));
      return mainPageLink.getAttribute("href");
    }

    return "";
  }

  private void removeSmartBanner() {
    if (checkIfElementOnPage(SMART_BANNER_SELECTOR)) {
      WebElement smartBanner = driver.findElement(By.cssSelector(SMART_BANNER_SELECTOR));
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("$(arguments[0]).css('display', 'none')", smartBanner);
      waitForElementNotVisibleByElement(smartBanner);
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

}
