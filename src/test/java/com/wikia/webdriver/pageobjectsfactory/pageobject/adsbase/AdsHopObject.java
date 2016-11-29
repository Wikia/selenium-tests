package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AdsHopObject extends AdsBaseObject {

  private static final String POST_MESSAGE_SCRIPT_XPATH =
      "//script[contains(text(), 'parent.postMessage')]";
  private static final int AD_SUCCESS_TIMEOUT_SEC = 15;

  public AdsHopObject(WebDriver driver, String page) {
    super(driver, page);
  }

  public void verifyClassHidden(final String slotName, final String containerId) {
    new WebDriverWait(driver, AD_SUCCESS_TIMEOUT_SEC).until(new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver driver) {
        return getTestedContainer(slotName, containerId).getAttribute("class").contains("hidden");
      }

      @Override
      public String toString() {
        return containerId + " div doesn't have class=\"hidden\"";
      }
    });
  }

  public void verifyPostMessage(String slotName, String src, String extraParam) {
    WebElement testedDiv = getTestedDiv(slotName, src);
    String iframeSelector = "iframe[id*='" + slotName + "_0']";
    WebElement iframe = testedDiv.findElement(By.cssSelector(iframeSelector));
    driver.switchTo().frame(iframe);
    WebElement postMessageScript = driver.findElement(By.xpath(POST_MESSAGE_SCRIPT_XPATH));
    Assertion.assertEquals(postMessageScript.getAttribute("innerHTML"),
                           getPostMessagePattern(extraParam));
    driver.switchTo().defaultContent();
  }

  public void verifyLineItemIdsDiffer(String slotName) {
    String lineItemIdAttribute = "data-gpt-line-item-id";
    String slotSelector = AdsContent.getSlotSelector(slotName);
    java.util.List<WebElement>
        divs =
        driver.findElements(By.cssSelector(slotSelector + " > div"));
    ArrayList<String> lineItems = new ArrayList<>();
    for (WebElement div : divs) {
      String lineItem = div.findElement(By.tagName("div")).getAttribute(lineItemIdAttribute);
      lineItems.add(lineItem);
    }
    Set<String> lineItemsSet = new HashSet<>(lineItems);
    if (lineItemsSet.size() < lineItems.size()) {
      PageObjectLogging.log("Line item ids",
                            slotName + " slot has the divs with the same line item ids",
                            false);
    } else {
      PageObjectLogging.log("Line item ids",
                            slotName + " slot has the divs with the different line item ids",
                            true);
    }
  }

  private WebElement getTestedContainer(final String slotName, final String id) {
    final String slotSelector = AdsContent.getSlotSelector(slotName);
    return getTestedElement(slotSelector + " > div[id*='" + id + "']");
  }

  private WebElement getTestedDiv(final String slotName, final String src) {
    final String slotSelector = AdsContent.getSlotSelector(slotName);
    return getTestedElement(slotSelector + " > div > div[id*='" + src + "']");
  }

  private WebElement getTestedElement(final String elementSelector) {
    return new WebDriverWait(driver, AD_SUCCESS_TIMEOUT_SEC).until(
        new ExpectedCondition<WebElement>() {
          @Override
          public WebElement apply(WebDriver driver) {
            java.util.List<WebElement> elements = driver
                .findElements(By.cssSelector(elementSelector));
            return elements.isEmpty() ? null : elements.get(0);
          }

          @Override
          public String toString() {
            return "could not find element:" + elementSelector;
          }
        });
  }

  private String getPostMessagePattern(String extra) {
    return "\nparent.postMessage('{\"AdEngine\":{\"status\":\"hop\",\"extra\":{" +
           extra + "}}}', '*');\n";
  }
}
