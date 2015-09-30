package com.wikia.webdriver.pageobjectsfactory.componentobject.monetizationmodule;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 * @author Saipetch Kongkatong
 */
public class MonetizationModuleComponentObject extends WikiBasePageObject {

  private static final String COOKIE_FROMSEARCH_NAME = "fromsearch";
  private static final String COOKIE_FROMSEARCH_VALUE = "1";
  private static final String ATTRIBUTE_NAME_SLOT = "data-mon-slot";
  private static final String ADSENSE_HEADER_VALUE = "advertisement";
  private static final String SLOT_IN_CONTENT = "in_content";
  private static final int NUM_OF_REDIRECT = 5;
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
  private By slotAboveFooter = By.cssSelector(".monetization-module[data-mon-slot='above_footer']");
  private By adsenseContainer = By.cssSelector(".monetization-module[data-mon-type='adunit']");
  private By adsenseIns = By.cssSelector(".adsbygoogle");
  private By slotAboveTitleAdsense = By.cssSelector("#monetization-adunit-above_title");
  private By slotBelowTitleAdsense = By.cssSelector("#monetization-adunit-below_title");
  private By slotInContentAdsense = By.cssSelector("#monetization-adunit-in_content");
  private By slotBelowCategoryAdsense = By.cssSelector("#monetization-adunit-below_category");
  private By slotAboveFooterAdsense = By.cssSelector("#monetization-adunit-above_footer");
  private By MonetizationModuleAdsenseListBy = By
      .cssSelector(".monetization-module[data-mon-type='adunit']");

  // Amazon
  private By amazonContainerBy = By
      .cssSelector(".monetization-module[data-mon-type='amazon_video']");
  private By slotInContentAmazon = By.cssSelector("#monetization-amazon_video-in_content");
  private By slotAboveFooterAmazon = By.cssSelector("#monetization-amazon_video-above_footer");
  private By slotBelowCategoryAmazon = By.cssSelector("#monetization-amazon_video-below_category");
  private By slotAboveTitleAmazon = By.cssSelector("#monetization-amazon-above_title");
  private By slotBelowTitleAmazon = By.cssSelector("#monetization-amazon-below_title");
  private By primeProductPriceBy = By.cssSelector(".product-price");
  private By productThumbBy = By.cssSelector(".product-thumb");
  private By productThumbImgBy = By.cssSelector(".product-thumb img");

  // Ecommerce
  private By ecommerceContainerBy = By
      .cssSelector(".monetization-module[data-mon-type='ecommerce']");
  private By slotInContentEcommerceBy = By.cssSelector("#monetization-ecommerce-in_content");
  private By slotAboveFooterEcommerceBy = By.cssSelector("#monetization-ecommerce-above_footer");
  private By slotBelowCategoryEcommerceBy = By
      .cssSelector("#monetization-ecommerce-below_category");
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
    wait.forElementPresent(monetizationModuleContainer);
    Assertion.assertTrue(isElementOnPage(monetizationModuleContainer));
    LOG.success("verifyMonetizationModuleShown", "Monetization module is visible");
  }

  public void verifyMonetizationModuleNotShown() {
    waitForElementNotVisibleByElement(monetizationModuleContainerElem);
    LOG.result("verifyMonetizationModuleNotShown", "Monetization module is not shown", true);
  }

  public void verifyAdsenseUnitShown() {
    wait.forElementVisible(adsenseContainer);
    Assertion.assertTrue(isElementOnPage(adsenseContainer));
    Assertion.assertTrue(isElementOnPage(adsenseIns));
    LOG.success("verifyAdsenseUnitShown", "Adsense unit is visible");
  }

  public void verifyAdsenseUnitNotShown() {
    wait.forElementNotPresent(adsenseContainer);
    wait.forElementNotPresent(adsenseIns);
    LOG.success("verifyAdsenseUnitNotShown", "Adsense unit is not shown");
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
          LOG.success("verifyAdsenseUnitSlot", "Invalid slot name (Name: " + slotName + ")");
          break;
      }
    }
  }

  public void verifyAdsenseUnitShownAboveTitle() {
    wait.forElementVisible(slotAboveTitleAdsense);
    Assertion.assertTrue(isElementOnPage(slotAboveTitleAdsense));
    Assertion.assertTrue(isElementOnPage(adsenseInsOthers));
    LOG.success("verifyAdsenseUnitShownAboveTitle", "Adsense unit is visible above the title");
  }

  public void verifyAdsenseUnitNotShownAboveTitle() {
    wait.forElementNotPresent(slotAboveTitleAdsense);
    LOG.success("verifyAdsenseUnitNotShownAboveTitle", "Adsense unit is not shown above the title");
  }

  public void verifyAdsenseUnitShownBelowTitle() {
    wait.forElementVisible(slotBelowTitleAdsense);
    Assertion.assertTrue(isElementOnPage(slotBelowTitleAdsense));
    Assertion.assertTrue(isElementOnPage(adsenseInsOthers));
    LOG.success("verifyAdsenseUnitShownBelowTitle", "Adsense unit is visible below the title");
  }

  public void verifyAdsenseUnitNotShownBelowTitle() {
    wait.forElementNotPresent(slotBelowTitleAdsense);
    LOG.success("verifyAdsenseUnitNotShownBelowTitle", "Adsense unit is not shown below the title");
  }

  public void verifyAdsenseUnitShownInContent() {
    wait.forElementVisible(slotInContentAdsense);
    Assertion.assertTrue(isElementOnPage(slotInContentAdsense));
    Assertion.assertTrue(isElementOnPage(adsenseInsInContent));
    LOG.result("verifyAdsenseUnitShownInContent", "Adsense unit is visible in content", true);
  }

  public void verifyAdsenseUnitNotShownInContent() {
    wait.forElementNotPresent(slotInContentAdsense);
    LOG.success("verifyAdsenseUnitNotShownInContent", "Adsense unit is not shown in content");
  }

  public void verifyAdsenseUnitShownBelowCategory() {
    wait.forElementVisible(slotBelowCategoryAdsense);
    Assertion.assertTrue(isElementOnPage(slotBelowCategoryAdsense));
    Assertion.assertTrue(isElementOnPage(adsenseInsOthers));
    LOG.success("verifyAdsenseUnitShownBelowCategory", "Adsense unit is visible below category");
  }

  public void verifyAdsenseUnitNotShownBelowCategory() {
    wait.forElementNotPresent(slotBelowCategoryAdsense);
    LOG.success("verifyAdsenseUnitNotShownBelowCategory",
        "Adsense unit is not shown below category");
  }

  public void verifyAdsenseUnitShownAboveFooter() {
    wait.forElementVisible(slotAboveFooterAdsense);
    Assertion.assertTrue(isElementOnPage(slotAboveFooterAdsense));
    Assertion.assertTrue(isElementOnPage(adsenseInsOthers));
    LOG.success("verifyAdsenseUnitShownAboveFooter", "Adsense unit is visible above footer");
  }

  public void verifyAdsenseUnitNotShownAboveFooter() {
    wait.forElementNotPresent(slotAboveFooterAdsense);
    LOG.success("verifyAdsenseUnitNotShownAboveFooter", "Adsense unit is not shown above footer");
  }

  public boolean verifyWindowWidth(int expectedWidth) {
    int windowWidth = getWindowSize().width;
    LOG.success("verifyWindowWidth", "The width of the window is " + windowWidth);
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
      LOG.success("verifyAdsenseUnitWidth", "Verify the width of the adsense unit for " + slotName
          + " (width=" + width + ")");
    }
  }

  public void verifyAdsenseHeaderShown() {
    wait.forElementVisible(adHeader);
    Assertion.assertTrue(isElementOnPage(adHeader));
    Assertion.assertEquals(adHeader.getText(), ADSENSE_HEADER_VALUE.toUpperCase());
    LOG.result("verifyAdsenseHeaderShown", "The header of adsense unit is visible", true);
  }

  public void verifyAmazonUnitShown() {
    wait.forElementVisible(amazonContainerBy);
    Assertion.assertTrue(isElementOnPage(amazonContainerBy));
    LOG.success("verifyAmazonUnitShown", "Amazon unit is visible");
  }

  public void verifyAmazonUnitSlot() {
    List<WebElement> listWebElements = driver.findElements(amazonContainerBy);
    for (WebElement elem : listWebElements) {
      String slotName = elem.getAttribute(ATTRIBUTE_NAME_SLOT);
      switch (slotName) {
        case "in_content":
          LOG.result("verifyAmazonUnitSlot", "Verifying Amazon for In Content slot", true);
          verifyAmazonUnitShown(slotInContentAmazon);
          verifyProductThumbInvisible(slotInContentAmazon);
          verifyProductThumbImageSize(slotInContentAmazon);
          break;
        case "below_category":
          LOG.result("verifyAmazonUnitSlot", "Verifying Amazon for Below Category slot", true);
          verifyAmazonUnitShown(slotBelowCategoryAmazon);
          verifyProductThumbVisible(slotBelowCategoryAmazon);
          verifyProductThumbImageSize(slotBelowCategoryAmazon);
          break;
        case "above_footer":
          LOG.result("verifyAmazonUnitSlot", "Verifying Amazon for Above Footer slot", true);
          verifyAmazonUnitShown(slotAboveFooterAmazon);
          verifyProductThumbVisible(slotAboveFooterAmazon);
          verifyProductThumbImageSize(slotAboveFooterAmazon);
          break;
        default:
          LOG.success("verifyAmazonUnitSlot", "Invalid slot name (Name: " + slotName + ")");
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
          LOG.success("verifyAmazonPrimeUnitSlot", "Verifying Amazon Prime for In Content slot");
          verifyAmazonUnitShown(slotInContentAmazon);
          verifyAmazonPrimeShown(slotInContentAmazon);
          verifyProductThumbInvisible(slotInContentAmazon);
          break;
        case "below_category":
          LOG.success("verifyAmazonPrimeUnitSlot", "Verifying Amazon Prime for Below Category slot");
          verifyAmazonUnitShown(slotBelowCategoryAmazon);
          verifyAmazonPrimeShown(slotBelowCategoryAmazon);
          verifyProductThumbVisible(slotBelowCategoryAmazon);
          break;
        case "above_footer":
          LOG.success("verifyAmazonPrimeUnitSlot", "Verifying Amazon Prime for Above Footer slot");
          verifyAmazonUnitShown(slotAboveFooterAmazon);
          verifyAmazonPrimeShown(slotAboveFooterAmazon);
          verifyProductThumbVisible(slotAboveFooterAmazon);
          break;
        default:
          LOG.success("verifyAmazonPrimeUnitSlot", "Invalid slot name (Name: " + slotName + ")");
          break;
      }
    }
  }

  private void verifyAmazonPrimeShown(By slotBy) {
    WebElement priceElem = findPriceElementFromSlot(slotBy);
    wait.forElementVisible(priceElem);
    scrollToElement(priceElem);
    LOG.success("verifyAmazonPrimeShown", "Amazon prime unit is visible");
  }

  private void verifyAmazonUnitShown(By slotBy) {
    wait.forElementVisible(slotBy);
    Assertion.assertTrue(isElementOnPage(slotBy));
    scrollToElement(slotBy);
    LOG.success("verifyAmazonUnitShown", "Amazon unit is visible", true);
  }

  public void verifyAmazonUnitNotShownAboveTitle() {
    wait.forElementNotPresent(slotAboveTitleAmazon);
    LOG.success("verifyAdsenseUnitNotShownAboveTitle", "Adsense unit is not shown above the title");
  }

  public void verifyAmazonUnitNotShownBelowTitle() {
    wait.forElementNotPresent(slotBelowTitleAmazon);
    LOG.success("verifyAdsenseUnitNotShownBelowTitle", "Adsense unit is not shown below the title");
  }

  public void verifyAmazonUnitWidth(int expectedInContent, int expectedOthers) {
    WebElement elem = redirectUntilDesiredSlotShown(amazonContainerBy, SLOT_IN_CONTENT);
    String slotName = null;
    int width = -1;
    try {
      slotName = elem.getAttribute(ATTRIBUTE_NAME_SLOT);
      width = elem.getSize().width;
      LOG.success("verifyAmazonUnitWidth", "Verify the width of the AMAZON unit for " + slotName
          + " (width=" + width + ")", true);
      Assertion.assertEquals(width, expectedInContent);
    } catch (NullPointerException e) {
      LOG.error("verifyAmazonUnitWidth", "Unable to find " + SLOT_IN_CONTENT + "after "
          + NUM_OF_REDIRECT + " redirects");
    }
  }

  public void verifyAmazonUnitNotShown() {
    wait.forElementNotPresent(amazonContainerBy);
    LOG.success("verifyAmazonUnitNotShown", "Amazon unit is not shown");
  }

  public void verifyEcommerceUnitNotShown() {
    wait.forElementNotPresent(ecommerceContainerBy);
    LOG.success("verifyEcommerceUnitNotShown", "Ecommerce unit is not shown");
  }

  public void verifyEcommerceUnitShown() {
    wait.forElementVisible(ecommerceContainerBy);
    Assertion.assertTrue(isElementOnPage(ecommerceContainerBy));
    LOG.success("verifyEcommerceUnitShown", "Ecommerce unit is visible");
  }

  public void verifyEcommerceUnitSlot(int isMulti) {
    List<WebElement> listWebElements = driver.findElements(ecommerceContainerBy);
    for (WebElement elem : listWebElements) {
      String slotName = elem.getAttribute(ATTRIBUTE_NAME_SLOT);
      if (isMulti == MULTI) {
        switch (slotName) {
          case "in_content":
            LOG.success("verifyEcommerceUnitSlot", "Verifying Ecommerce Multi for In Content slot");
            verifyEcommerceUnitShown(slotInContentEcommerceBy, eCommerceMultipleProductBy);
            break;
          case "below_category":
            LOG.success("verifyEcommerceUnitSlot",
                "Verifying Ecommerce Multi for Below Category slot");
            verifyEcommerceUnitShown(slotBelowCategoryEcommerceBy, eCommerceMultipleProductBy);
            break;
          case "above_footer":
            LOG.success("verifyEcommerceUnitSlot",
                "Verifying Ecommerce Multi for Above Footer slot");
            verifyEcommerceUnitShown(slotAboveFooterEcommerceBy, eCommerceMultipleProductBy);
            break;
          default:
            LOG.success("verifyEcommerceUnitSlot", "Invalid slot name (Name: " + slotName + ")");
            break;
        }
        verifyNumOfProductThumbVisible(eCommerceMultipleProductBy);
      } else {
        switch (slotName) {
          case "in_content":
            LOG.success("verifyEcommerceUnitSlot", "Verifying Ecommerce Single for In Content slot");
            verifyEcommerceUnitShown(slotInContentEcommerceBy, eCommerceSingleProductBy);
            break;
          case "below_category":
            LOG.success("verifyEcommerceUnitSlot",
                "Verifying Ecommerce Single for Below Category slot");
            verifyEcommerceUnitShown(slotBelowCategoryEcommerceBy, eCommerceSingleProductBy);
            break;
          case "above_footer":
            LOG.success("verifyEcommerceUnitSlot",
                "Verifying Ecommerce Single for Above Footer slot");
            verifyEcommerceUnitShown(slotAboveFooterEcommerceBy, eCommerceSingleProductBy);
            break;
          default:
            LOG.success("verifyEcommerceUnitSlot", "Invalid slot name (Name: " + slotName + ")");
            break;
        }
        verifyNumOfProductThumbVisible(eCommerceSingleProductBy);
      }
    }
  }

  private void verifyNumOfProductThumbVisible(By numOfProductBy) {
    List<WebElement> thumbElemsList =
        driver.findElement(numOfProductBy).findElements(productThumbBy);
    if (numOfProductBy.equals(eCommerceMultipleProductBy)) {
      Assertion.assertTrue(thumbElemsList.size() > 1,
          "Expecting more than 1 product thumb image. Found: " + thumbElemsList.size());
    } else {
      Assertion.assertTrue(thumbElemsList.size() == 1,
          "Expecting only 1 product thumb image. Found: " + thumbElemsList.size());
    }
  }

  private void verifyEcommerceUnitShown(By slotBy, By numOfProductBy) {
    wait.forElementVisible(slotBy);
    wait.forElementVisible(numOfProductBy);
    scrollToElement(numOfProductBy);
    Assertion.assertTrue(isElementOnPage(numOfProductBy));
    LOG.success("verifyEcommerceUnitShown", "Ecommerce unit is visible", true);
  }

  public void verifyEcommerceUnitNotShownAboveTitle() {
    wait.forElementNotPresent(slotAboveTitleEcommerceBy);
    LOG.success("verifyEcommerceUnitNotShownAboveTitle",
        "Ecommerce unit is not shown above the title");
  }

  public void verifyEcommerceUnitNotShownBelowTitle() {
    wait.forElementNotPresent(slotBelowTitleEcommerceBy);
    LOG.success("verifyEcommerceUnitNotShownBelowTitle",
        "Ecommerce unit is not shown below the title");
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
    waitForElementNotVisibleByElement(driver.findElement(slotBy).findElement(productThumbBy));
    LOG.success("verifyProductThumbInvisible", "Product thumbnail is NOT visible in content");
  }

  private void verifyProductThumbVisible(By slotBy) {
    wait.forElementVisible(driver.findElement(slotBy).findElement(productThumbBy));
    LOG.result("verifyProductThumbInvisible", "Product thumbnail is visible in content", true);
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
    Assertion.assertTrue(imgHeight <= maxHeight, "img height: " + imgHeight
        + "is bigger than max height: " + maxHeight);
    Assertion.assertTrue(imgWidth <= maxWidth, "img width: " + imgWidth
        + " is bigger than max width: " + maxHeight);
  }
}
