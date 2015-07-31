package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * @author drets
 * @ownership AdEng
 */
public class AdsHopObject extends AdsBaseObject 

  private static final String POST_MESSAGE_SCRIPT_XPATH =
      "//script[contains(text(), 'parent.postMessage')]";

  private final static ImmutableMap<String, String> dfpSrc =
      new ImmutableMap.Builder<String, String>()
          .put("DirectGptMobile", "mobile")
          .put("RemnantGptMobile", "mobile_remnant")
          .build();

  public AdsHopObject(WebDriver driver, String page) {
    super(driver, page);
  }

  public AdsHopObject verifyClassHidden(String slotName, String src) {
    WebElement testedDiv = getTestedDiv(slotName, src);
    Assertion.assertEquals(testedDiv.getAttribute("class").trim(), "hidden");
    return this;
  }

  public AdsHopObject verifyPostMessage(String slotName, String src) {
    WebElement testedDiv = getTestedDiv(slotName, src);
    String iframeSelector = "iframe[id*='" + slotName + "_0']";
    WebElement iframe = testedDiv.findElement(By.cssSelector(iframeSelector));
    driver.switchTo().frame(iframe);
    WebElement postMessageScript = driver.findElement(By.xpath(POST_MESSAGE_SCRIPT_XPATH));
    Assertion
        .assertEquals(postMessageScript.getAttribute("innerHTML"), getPostMessagePattern(src));
    driver.switchTo().defaultContent();
    return this;
  }

  public AdsHopObject verifyLineItemIdsDiffer(String slotName) {
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
    return this;
  }

  private WebElement getTestedDiv(String slotName, String src) {
    String slotSelector = AdsContent.getSlotSelector(slotName);
    java.util.List<WebElement>
        divs =
        driver.findElements(By.cssSelector(slotSelector + " > div"));
    for (WebElement div : divs) {
      if (div.getAttribute("id").contains(src)) {
        return div.findElement(By.tagName("div"));
      }
    }
    throw new NoSuchElementException(slotName + " does not have the " + src + " div");
  }

  private String getPostMessagePattern(String src) {
    return "\nparent.postMessage('{\"AdEngine\":{\"status\":\"hop\",\"extra\":{\"source\":\""
           + dfpSrc.get(src) + "/LB\"}}}', '*');\n";
  }
}
