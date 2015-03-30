package com.wikia.webdriver.pageobjectsfactory.componentobject.monetizationmodule;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
  private static final String SLOT_BELOW_CATEGORY = "below_category";
  private static final String SLOT_ABOVE_FOOTER = "above_footer";
  private static final int NUM_OF_REDIRECT = 5;
  private static final int SINGLE = 0;
  private static final int MULTI = 1;

  @FindBy(css = ".adsbygoogle.ad-responsive-ic")
  private WebElement adsenseInsInContent;
  @FindBy(css = ".adsbygoogle.ad-responsive")
  private WebElement adsenseInsOthers;
  @FindBy(css = ".ad-header")
  private WebElement adHeader;
  @FindBy(css = ".monetization-module")
  private WebElement monetizationModuleContainerElem;

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
  private By
      amazonContainerBy = By.cssSelector(".monetization-module[data-mon-type='amazon_video']");
  private By slotInContentAmazon = By.cssSelector("#monetization-amazon_video-in_content");
  private By slotAboveFooterAmazon = By.cssSelector("#monetization-amazon_video-above_footer");
  private By slotBelowCategoryAmazon = By.cssSelector("#monetization-amazon_video-below_category");
  private By slotAboveTitleAmazon = By.cssSelector("#monetization-amazon-above_title");
  private By slotBelowTitleAmazon = By.cssSelector("#monetization-amazon-below_title");
  private By primeProductPriceBy = By.cssSelector(".product-price");
  private By productThumbBy = By.cssSelector(".product-thumb");
  private By productThumbImgBy = By.cssSelector(".product-thumb img");
  private By moduleTitleBy = By.cssSelector(".module-title");

  //Ecommerce
  private By
      ecommerceContainerBy = By.cssSelector(".monetization-module[data-mon-type='ecommerce']");
  private By slotInContentEcommerceBy = By.cssSelector("#monetization-ecommerce-in_content");
  private By slotAboveFooterEcommerceBy = By.cssSelector("#monetization-ecommerce-above_footer");
  private By
      slotBelowCategoryEcommerceBy = By.cssSelector("#monetization-ecommerce-below_category");
  private By slotAboveTitleEcommerceBy = By.cssSelector("#monetization-ecommerce-above_title");
  private By slotBelowTitleEcommerceBy = By.cssSelector("#monetization-ecommerce-below_title");
  private By eCommerceMultipleProductBy = By.cssSelector(".ecommerce-multiple-product");
  private By eCommerceSingleProductBy = By.cssSelector(".ecommerce-single-product");

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
    waitForElementByBy(monetizationModuleContainer);
    Assertion.assertTrue(checkIfElementOnPage(monetizationModuleContainer));
    PageObjectLogging.log("verifyMonetizationModuleShown", "Monetization module is visible", true);
  }

  public void verifyMonetizationModuleNotShown() {
    waitForElementNotVisibleByElement(monetizationModuleContainerElem);
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
    waitForElementByElementLocatedBy(amazonContainerBy);
    Assertion.assertTrue(checkIfElementOnPage(amazonContainerBy));
    PageObjectLogging.log("verifyAmazonUnitShown", "Amazon unit is visible", true);
  }

  public void verifyAmazonUnitSlot() {
    List<WebElement> listWebElements = driver.findElements(amazonContainerBy);
    for (WebElement elem : listWebElements) {
      String slotName = elem.getAttribute(ATTRIBUTE_NAME_SLOT);
      switch (slotName) {
        case "in_content":
          PageObjectLogging
              .log("verifyAmazonUnitSlot", "Verifying Amazon for In Content slot", true);
          verifyAmazonUnitShown(slotInContentAmazon);
          verifyProductThumbInvisible(slotInContentAmazon);
          verifyProductThumbImageSize(slotInContentAmazon);
          break;
        case "below_category":
          PageObjectLogging.log(
              "verifyAmazonUnitSlot", "Verifying Amazon for Below Category slot", true);
          verifyAmazonUnitShown(slotBelowCategoryAmazon);
          verifyProductThumbVisible(slotBelowCategoryAmazon);
          verifyProductThumbImageSize(slotBelowCategoryAmazon);
          break;
        case "above_footer":
          PageObjectLogging.log(
              "verifyAmazonUnitSlot", "Verifying Amazon for Above Footer slot", true);
          verifyAmazonUnitShown(slotAboveFooterAmazon);
          verifyProductThumbVisible(slotAboveFooterAmazon);
          verifyProductThumbImageSize(slotAboveFooterAmazon);
          break;
        default:
          PageObjectLogging
              .log("verifyAmazonUnitSlot", "Invalid slot name (Name: " + slotName + ")", true);
          break;
      }
    }
  }

  public void verifyAmazonPrimeUnitSlot() {
    List<WebElement> listWebElements = driver.findElements(amazonContainerBy);
    for (WebElement elem : listWebElements) {
      String slotName = elem.getAttribute(ATTRIBUTE_NAME_SLOT);
      switch (slotName) {
        case "in_content":
          PageObjectLogging.log(
              "verifyAmazonPrimeUnitSlot", "Verifying Amazon Prime for In Content slot", true);
          verifyAmazonUnitShown(slotInContentAmazon);
          verifyAmazonPrimeShown(slotInContentAmazon);
          verifyProductThumbInvisible(slotInContentAmazon);
          break;
        case "below_category":
          PageObjectLogging.log(
              "verifyAmazonPrimeUnitSlot", "Verifying Amazon Prime for Below Category slot", true);
          verifyAmazonUnitShown(slotBelowCategoryAmazon);
          verifyAmazonPrimeShown(slotBelowCategoryAmazon);
          verifyProductThumbVisible(slotBelowCategoryAmazon);
          break;
        case "above_footer":
          PageObjectLogging.log(
              "verifyAmazonPrimeUnitSlot", "Verifying Amazon Prime for Above Footer slot", true);
          verifyAmazonUnitShown(slotAboveFooterAmazon);
          verifyAmazonPrimeShown(slotAboveFooterAmazon);
          verifyProductThumbVisible(slotAboveFooterAmazon);
          break;
        default:
          PageObjectLogging
              .log("verifyAmazonPrimeUnitSlot", "Invalid slot name (Name: " + slotName + ")", true);
          break;
      }
    }
  }

  private void verifyAmazonPrimeShown(By slotBy) {
    WebElement priceElem = findPriceElementFromSlot(slotBy);
    waitForElementVisibleByElement(priceElem);
    scrollToElement(priceElem);
    PageObjectLogging
        .log("verifyAmazonPrimeShown", "Amazon prime unit is visible", true);
  }

  private void verifyAmazonUnitShown(By slotBy) {
    waitForElementByElementLocatedBy(slotBy);
    Assertion.assertTrue(checkIfElementOnPage(slotBy));
    scrollToElement(slotBy);
    PageObjectLogging.log("verifyAmazonUnitShown", "Amazon unit is visible", true, driver);
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

  public void verifyAmazonUnitWidth(int expectedInContent, int expectedOthers) {
    WebElement elem =
        redirectUntilDesiredSlotShown(amazonContainerBy, SLOT_IN_CONTENT);
    String slotName = null;
    int width = -1;
    try {
      slotName = elem.getAttribute(ATTRIBUTE_NAME_SLOT);
      width = elem.getSize().width;
      PageObjectLogging.log("verifyAmazonUnitWidth",
                            "Verify the width of the AMAZON unit for " + slotName + " (width="
                            + width + ")", true, driver);
      Assertion.assertEquals(width, expectedInContent);
    } catch (NullPointerException e) {
      PageObjectLogging.log("verifyAmazonUnitWidth",
                            "Unable to find " + SLOT_IN_CONTENT + "after " + NUM_OF_REDIRECT
                            + " redirects", false, driver);
    }
  }

  public void verifyAmazonUnitNotShown() {
    waitForElementNotPresent(amazonContainerBy);
    PageObjectLogging.log("verifyAmazonUnitNotShown", "Amazon unit is not shown", true);
  }

  public void verifyEcommerceUnitNotShown() {
    waitForElementNotPresent(ecommerceContainerBy);
    PageObjectLogging.log("verifyEcommerceUnitNotShown", "Ecommerce unit is not shown", true);
  }

  public void verifyEcommerceUnitShown() {
    waitForElementByElementLocatedBy(ecommerceContainerBy);
    Assertion.assertTrue(checkIfElementOnPage(ecommerceContainerBy));
    PageObjectLogging.log("verifyEcommerceUnitShown", "Ecommerce unit is visible", true);
  }

  public void verifyEcommerceUnitSlot(int isMulti) {
    List<WebElement> listWebElements = driver.findElements(ecommerceContainerBy);
    for (WebElement elem : listWebElements) {
      String slotName = elem.getAttribute(ATTRIBUTE_NAME_SLOT);
      if (isMulti == MULTI) {
        switch (slotName) {
          case "in_content":
            PageObjectLogging.log(
                "verifyEcommerceUnitSlot", "Verifying Ecommerce Multi for In Content slot", true);
            verifyEcommerceUnitShown(slotInContentEcommerceBy, eCommerceMultipleProductBy);
            break;
          case "below_category":
            PageObjectLogging.log(
                "verifyEcommerceUnitSlot", "Verifying Ecommerce Multi for Below Category slot",
                true);
            verifyEcommerceUnitShown(slotBelowCategoryEcommerceBy, eCommerceMultipleProductBy);
            break;
          case "above_footer":
            PageObjectLogging.log(
                "verifyEcommerceUnitSlot", "Verifying Ecommerce Multi for Above Footer slot", true);
            verifyEcommerceUnitShown(slotAboveFooterEcommerceBy, eCommerceMultipleProductBy);
            break;
          default:
            PageObjectLogging
                .log("verifyEcommerceUnitSlot", "Invalid slot name (Name: " + slotName + ")", true);
            break;
        }
        verifyNumOfProductThumbVisible(eCommerceMultipleProductBy);
      } else {
        switch (slotName) {
          case "in_content":
            PageObjectLogging.log(
                "verifyEcommerceUnitSlot", "Verifying Ecommerce Single for In Content slot", true);
            verifyEcommerceUnitShown(slotInContentEcommerceBy, eCommerceSingleProductBy);
            break;
          case "below_category":
            PageObjectLogging.log(
                "verifyEcommerceUnitSlot", "Verifying Ecommerce Single for Below Category slot",
                true);
            verifyEcommerceUnitShown(slotBelowCategoryEcommerceBy, eCommerceSingleProductBy);
            break;
          case "above_footer":
            PageObjectLogging.log(
                "verifyEcommerceUnitSlot", "Verifying Ecommerce Single for Above Footer slot",
                true);
            verifyEcommerceUnitShown(slotAboveFooterEcommerceBy, eCommerceSingleProductBy);
            break;
          default:
            PageObjectLogging
                .log("verifyEcommerceUnitSlot", "Invalid slot name (Name: " + slotName + ")", true);
            break;
        }
        verifyNumOfProductThumbVisible(eCommerceSingleProductBy);
      }
    }
  }

  private void verifyNumOfProductThumbVisible(By numOfProductBy) {
    List<WebElement>
        thumbElemsList =
        driver.findElement(numOfProductBy).findElements(productThumbBy);
    if (numOfProductBy.equals(eCommerceMultipleProductBy)) {
      Assertion.assertTrue(thumbElemsList.size() > 1,
                           "Expecting more than 1 product thumb image. Found: "
                           + thumbElemsList.size());
    } else {
      Assertion.assertTrue(thumbElemsList.size() == 1,
                           "Expecting only 1 product thumb image. Found: "
                           + thumbElemsList.size());
    }
  }

  private void verifyEcommerceUnitShown(By slotBy, By numOfProductBy) {
    waitForElementByElementLocatedBy(slotBy);
    waitForElementByElementLocatedBy(numOfProductBy);
    scrollToElement(numOfProductBy);
    Assertion.assertTrue(checkIfElementOnPage(numOfProductBy));
    PageObjectLogging.log("verifyEcommerceUnitShown", "Ecommerce unit is visible", true, driver);
  }

  public void verifyEcommerceUnitNotShownAboveTitle() {
    waitForElementNotPresent(slotAboveTitleEcommerceBy);
    PageObjectLogging
        .log("verifyEcommerceUnitNotShownAboveTitle", "Ecommerce unit is not shown above the title",
             true);
  }

  public void verifyEcommerceUnitNotShownBelowTitle() {
    waitForElementNotPresent(slotBelowTitleEcommerceBy);
    PageObjectLogging
        .log("verifyEcommerceUnitNotShownBelowTitle", "Ecommerce unit is not shown below the title",
             true);
  }

  private WebElement redirectUntilDesiredSlotShown(By adSlotBy, String adSlotName) {
    int numOfRedirect = 0;
    WebElement foundElem = null;
    while (numOfRedirect < NUM_OF_REDIRECT) {
      List<WebElement> listWebElements = driver.findElements(adSlotBy);
      for (int i = 0; i < listWebElements.size(); i++) {
        String slotName = listWebElements.get(i).getAttribute(ATTRIBUTE_NAME_SLOT);
        if (slotName.equals(adSlotName)) {
          return listWebElements.get(i);
        }
      }
      redirectToAnotherRandomArticle();
      numOfRedirect++;
    }
    return foundElem;
  }

  private WebElement findPriceElementFromSlot(By slotBy) {
    return driver.findElement(slotBy).findElement(primeProductPriceBy);
  }

  private void verifyProductThumbInvisible(By slotBy) {
    waitForElementNotVisibleByElement(
        driver.findElement(slotBy).findElement(productThumbBy));
    PageObjectLogging
        .log("verifyProductThumbInvisible", "Product thumbnail is NOT visible in content", true);
  }

  private void verifyProductThumbVisible(By slotBy) {
    waitForElementVisibleByElement(
        driver.findElement(slotBy).findElement(productThumbBy));
    PageObjectLogging
        .log("verifyProductThumbInvisible", "Product thumbnail is visible in content", true);
  }

  private void verifyProductThumbImageSize(By slotBy) {
    WebElement thumbImg = driver.findElement(slotBy).findElement(productThumbImgBy);
    Dimension imgDimension = thumbImg.getSize();
    int imgHeight = imgDimension.getHeight();
    int imgWidth = imgDimension.getWidth();
    String maxHeightStr = thumbImg.getCssValue("max-height");
    String maxWidthStr = thumbImg.getCssValue("max-width");
    int maxHeight = Integer.parseInt(maxHeightStr.substring(0, maxHeightStr.indexOf("px")));
    int maxWidth = Integer.parseInt(maxWidthStr.substring(0, maxWidthStr.indexOf("px")));
    Assertion.assertTrue(imgHeight <= maxHeight,
                         "img height: " + imgHeight + "is bigger than max height: " + maxHeight);
    Assertion.assertTrue(imgWidth <= maxWidth,
                         "img width: " + imgWidth + " is bigger than max width: " + maxHeight);
  }
}
