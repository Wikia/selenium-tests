package com.wikia.webdriver.pageobjectsfactory.componentobject.monetizationmodule;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author Saipetch Kongkatong
 */
public class MonetizationModuleComponentObject extends WikiBasePageObject {

  private static final String COOKIE_FROMSEARCH_NAME = "fromsearch";
  private static final String COOKIE_FROMSEARCH_VALUE = "1";
  private static final String ATTRIBUTE_NAME_SLOT = "data-mon-slot";
  private static final String ATTRIBUTE_NAME_MODULE_TYPE = "data-mon-type";
  private static final String ADSENSE_HEADER_VALUE = "advertisement";
  private static final String SLOT_IN_CONTENT = "in_content";
  private static final int NUM_OF_REDIRECT = 5;

  @FindBy(css = ".adsbygoogle.ad-responsive-ic")
  private WebElement adsenseInsInContent;
  @FindBy(css = ".adsbygoogle.ad-responsive")
  private WebElement adsenseInsOthers;
  @FindBy(css = ".ad-header")
  private WebElement adHeader;

  private By monetizationModuleContainer = By.cssSelector(".monetization-module");
  private By slotAboveTitle = By.cssSelector(".monetization-module[data-mon-slot='above_title']");
  private By slotBelowTitle = By.cssSelector(".monetization-module[data-mon-slot='below_title']");
  private By slotInContent = By.cssSelector(".monetization-module[data-mon-slot='in_content']");
  private By
      slotBelowCategory =
      By.cssSelector(".monetization-module[data-mon-slot='below_category']");
  private By slotAboveFooter = By.cssSelector(".monetization-module[data-mon-slot='above_footer']");
  private By adsenseContainer = By.cssSelector(".monetization-module[data-mon-type='adunit']");
  private By adsenseIns = By.cssSelector(".adsbygoogle");
  private By slotAboveTitleAdsense = By.cssSelector("#monetization-adunit-above_title");
  private By slotBelowTitleAdsense = By.cssSelector("#monetization-adunit-below_title");
  private By slotInContentAdsense = By.cssSelector("#monetization-adunit-in_content");
  private By slotBelowCategoryAdsense = By.cssSelector("#monetization-adunit-below_category");
  private By slotAboveFooterAdsense = By.cssSelector("#monetization-adunit-above_footer");
  private By MonetizationModuleListBy = By.cssSelector(".monetization-module");
  private By
      MonetizationModuleAdsenseListBy =
      By.cssSelector(".monetization-module[data-mon-type='adunit']");

  //Amazon
  private By amazonContainer = By.cssSelector(".monetization-module[data-mon-type='amazon_video']");
  private By
      MonetizationModuleAmazonListBy =
      By.cssSelector(".monetization-module[data-mon-type='amazon_video']");
  private By slotInContentAmazon = By.cssSelector("#monetization-amazon_video-in_content");
  private By slotBelowCategoryAmazon = By.cssSelector("#monetization-amazon_video-below_category");
  private By slotAboveFooterEcommerce = By.cssSelector("#monetization-ecommerce-above_footer");
  private By slotAboveTitleAmazon = By.cssSelector("#monetization-amazon-above_title");
  private By slotBelowTitleAmazon = By.cssSelector("#monetization-amazon-below_title");

  public MonetizationModuleComponentObject(WebDriver driver) {
    super(driver);
  }

  public void setCookieFromSearch() {
    setCookie(COOKIE_FROMSEARCH_NAME, COOKIE_FROMSEARCH_VALUE);
  }

  public void deleteCookieFromSearch() {
    deleteCookie(COOKIE_FROMSEARCH_NAME);
  }

  public void verifyMonetizationModuleShown() {
    waitForElementByElementLocatedBy(monetizationModuleContainer);
    Assertion.assertTrue(checkIfElementOnPage(monetizationModuleContainer));
    PageObjectLogging.log("verifyMonetizationModuleShown", "Monetization module is visible", true);
  }

  public void verifyMonetizationModuleNotShown() {
    waitForElementNotPresent(monetizationModuleContainer);
    PageObjectLogging
        .log("verifyMonetizationModuleNotShown", "Monetization module is not shown", true);
  }

  public void verifyAdsenseUnitShown() {
    waitForElementByElementLocatedBy(adsenseContainer);
    Assertion.assertTrue(checkIfElementOnPage(adsenseContainer));
    Assertion.assertTrue(checkIfElementOnPage(adsenseIns));
    PageObjectLogging.log("verifyAdsenseUnitShown", "Adsense unit is visible", true);
  }

  public void verifyAdsenseUnitNotShown() {
    waitForElementNotPresent(adsenseContainer);
    waitForElementNotPresent(adsenseIns);
    PageObjectLogging.log("verifyAdsenseUnitNotShown", "Adsense unit is not shown", true);
  }

  public void verifyAdsenseUnitSlot() {
    List<WebElement> listWebElements = driver.findElements(MonetizationModuleAdsenseListBy);
    for (WebElement elem : listWebElements) {
      String slotName = elem.getAttribute(ATTRIBUTE_NAME_SLOT);
      switch (slotName) {
        case "above_title":
          verifyAdsenseUnitShownAboveTitle();
          break;
        case "below_title":
          verifyAdsenseUnitShownBelowTitle();
          break;
        case "in_content":
          verifyAdsenseUnitShownInContent();
          break;
        case "below_category":
          verifyAdsenseUnitShownBelowCategory();
          break;
        case "above_footer":
          verifyAdsenseUnitShownAboveFooter();
          break;
        default:
          PageObjectLogging
              .log("verifyAdsenseUnitSlot", "Invalid slot name (Name: " + slotName + ")", true);
          break;
      }
    }
  }

  public void verifyAdsenseUnitShownAboveTitle() {
    waitForElementByElementLocatedBy(slotAboveTitleAdsense);
    Assertion.assertTrue(checkIfElementOnPage(slotAboveTitleAdsense));
    Assertion.assertTrue(checkIfElementOnPage(adsenseInsOthers));
    PageObjectLogging
        .log("verifyAdsenseUnitShownAboveTitle", "Adsense unit is visible above the title", true);
  }

  public void verifyAdsenseUnitNotShownAboveTitle() {
    waitForElementNotPresent(slotAboveTitleAdsense);
    PageObjectLogging
        .log("verifyAdsenseUnitNotShownAboveTitle", "Adsense unit is not shown above the title",
             true);
  }

  public void verifyAdsenseUnitShownBelowTitle() {
    waitForElementByElementLocatedBy(slotBelowTitleAdsense);
    Assertion.assertTrue(checkIfElementOnPage(slotBelowTitleAdsense));
    Assertion.assertTrue(checkIfElementOnPage(adsenseInsOthers));
    PageObjectLogging
        .log("verifyAdsenseUnitShownBelowTitle", "Adsense unit is visible below the title", true);
  }

  public void verifyAdsenseUnitNotShownBelowTitle() {
    waitForElementNotPresent(slotBelowTitleAdsense);
    PageObjectLogging
        .log("verifyAdsenseUnitNotShownBelowTitle", "Adsense unit is not shown below the title",
             true);
  }

  public void verifyAdsenseUnitShownInContent() {
    waitForElementByElementLocatedBy(slotInContentAdsense);
    Assertion.assertTrue(checkIfElementOnPage(slotInContentAdsense));
    Assertion.assertTrue(checkIfElementOnPage(adsenseInsInContent));
    PageObjectLogging
        .log("verifyAdsenseUnitShownInContent", "Adsense unit is visible in content", true);
  }

  public void verifyAdsenseUnitNotShownInContent() {
    waitForElementNotPresent(slotInContentAdsense);
    PageObjectLogging
        .log("verifyAdsenseUnitNotShownInContent", "Adsense unit is not shown in content", true);
  }

  public void verifyAdsenseUnitShownBelowCategory() {
    waitForElementByElementLocatedBy(slotBelowCategoryAdsense);
    Assertion.assertTrue(checkIfElementOnPage(slotBelowCategoryAdsense));
    Assertion.assertTrue(checkIfElementOnPage(adsenseInsOthers));
    PageObjectLogging
        .log("verifyAdsenseUnitShownBelowCategory", "Adsense unit is visible below category", true);
  }

  public void verifyAdsenseUnitNotShownBelowCategory() {
    waitForElementNotPresent(slotBelowCategoryAdsense);
    PageObjectLogging
        .log("verifyAdsenseUnitNotShownBelowCategory", "Adsense unit is not shown below category",
             true);
  }

  public void verifyAdsenseUnitShownAboveFooter() {
    waitForElementByElementLocatedBy(slotAboveFooterAdsense);
    Assertion.assertTrue(checkIfElementOnPage(slotAboveFooterAdsense));
    Assertion.assertTrue(checkIfElementOnPage(adsenseInsOthers));
    PageObjectLogging
        .log("verifyAdsenseUnitShownAboveFooter", "Adsense unit is visible above footer", true);
  }

  public void verifyAdsenseUnitNotShownAboveFooter() {
    waitForElementNotPresent(slotAboveFooterAdsense);
    PageObjectLogging
        .log("verifyAdsenseUnitNotShownAboveFooter", "Adsense unit is not shown above footer",
             true);
  }

  public boolean verifyWindowWidth(int expectedWidth) {
    int windowWidth = getWindowSize().width;
    PageObjectLogging.log("verifyWindowWidth", "The width of the window is " + windowWidth, true);
    return (windowWidth == expectedWidth);
  }

  public void verifyAdsenseUnitWidth(int expectedInContent, int expectedOthers) {
    int width;
    List<WebElement> listWebElements = driver.findElements(MonetizationModuleAdsenseListBy);
    for (WebElement elem : listWebElements) {
      String slotName = elem.getAttribute(ATTRIBUTE_NAME_SLOT);
      width = elem.findElement(adsenseIns).getSize().width;
      if (slotName.equals(SLOT_IN_CONTENT)) {
        Assertion.assertEquals(width, expectedInContent);
      } else {
        Assertion.assertEquals(width, expectedOthers);
      }
      PageObjectLogging.log("verifyAdsenseUnitWidth",
                            "Verify the width of the adsense unit for " + slotName + " (width="
                            + width + ")", true);
    }
  }

  public void verifyAdsenseHeaderShown() {
    waitForElementByElement(adHeader);
    Assertion.assertTrue(checkIfElementOnPage(adHeader));
    Assertion.assertEquals(ADSENSE_HEADER_VALUE.toUpperCase(), adHeader.getText());
    PageObjectLogging
        .log("verifyAdsenseHeaderShown", "The header of adsense unit is visible", true);
  }

  public void verifyAmazonUnitShown() {
    waitForElementByElementLocatedBy(amazonContainer);
    Assertion.assertTrue(checkIfElementOnPage(amazonContainer));
    PageObjectLogging.log("verifyAmazonUnitShown", "Amazon unit is visible", true);
  }

  public void verifyAmazonUnitSlot() {
    List<WebElement> listWebElements = driver.findElements(MonetizationModuleAmazonListBy);
    for (WebElement elem : listWebElements) {
      String slotName = elem.getAttribute(ATTRIBUTE_NAME_SLOT);
      switch (slotName) {
        case "in_content":
          verifyAmazonUnitShownInContent();
          break;
        case "below_category":
          verifyAmazonUnitShownBelowCategory();
          break;
        case "above_footer":
          verifyAmazonUnitShownAboveFooter();
          break;
        default:
          PageObjectLogging
              .log("verifyAmazonUnitSlot", "Invalid slot name (Name: " + slotName + ")", true);
          break;
      }
    }
  }

  private void verifyAmazonUnitShownAboveFooter() {
    waitForElementByElementLocatedBy(slotAboveFooterEcommerce);
    Assertion.assertTrue(checkIfElementOnPage(slotAboveFooterEcommerce));
    PageObjectLogging
        .log("verifyAmazonUnitShownAboveFooter", "Amazon unit is visible above footer", true);
  }

  private void verifyAmazonUnitShownBelowCategory() {
    waitForElementByElementLocatedBy(slotBelowCategoryAmazon);
    Assertion.assertTrue(checkIfElementOnPage(slotBelowCategoryAmazon));
    PageObjectLogging
        .log("verifyAmazonUnitShownBelowCategory", "Amazon unit is visible below category", true);
  }

  private void verifyAmazonUnitShownInContent() {
    waitForElementByElementLocatedBy(slotInContentAmazon);
    Assertion.assertTrue(checkIfElementOnPage(slotInContentAmazon));
    PageObjectLogging
        .log("verifyAmazonUnitShownInContent", "Amazon unit is visible in content", true);
  }

  public void verifyAmazonUnitNotShownAboveTitle() {
    waitForElementNotPresent(slotAboveTitleAmazon);
    PageObjectLogging
        .log("verifyAdsenseUnitNotShownAboveTitle", "Adsense unit is not shown above the title",
             true);
  }

  public void verifyAmazonUnitNotShownBelowTitle() {
    waitForElementNotPresent(slotBelowTitleAmazon);
    PageObjectLogging
        .log("verifyAdsenseUnitNotShownBelowTitle", "Adsense unit is not shown below the title",
             true);
  }
}
