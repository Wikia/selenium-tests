package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author drets
 * @ownership AdEng
 */
public class AdsHopObject extends AdsBaseObject {

  private static final String POST_MESSAGE_SCRIPT_XPATH =
      "//script[contains(text(), 'parent.postMessage')]";

  public AdsHopObject(WebDriver driver, String page) {
    super(driver, page);
  }

  public AdsHopObject verifyClassHidden(String slotName, int testedDivNumber) {
    WebElement testedDiv = getTestedDiv(slotName, testedDivNumber);
    Assertion.assertEquals(" hidden", testedDiv.getAttribute("class"));
    return this;
  }

  public AdsHopObject verifyPostMessage(String slotName, int testedDivNumber, String src) {
    WebElement testedDiv = getTestedDiv(slotName, testedDivNumber);
    String iframeSelector = "iframe[id*='" + slotName + "_0']";
    WebElement iframe = testedDiv.findElement(By.cssSelector(iframeSelector));
    driver.switchTo().frame(iframe);
    WebElement postMessageScript = driver.findElement(By.xpath(POST_MESSAGE_SCRIPT_XPATH));
    Assertion.assertEquals(getPostMessagePattern(src), postMessageScript.getAttribute("innerHTML"));
    driver.switchTo().defaultContent();
    return this;
  }

  public AdsHopObject verifyLineItemIdsDiffer(String slotName) {
    String lineItemIdAttribute = "data-gpt-line-item-id";
    String firstLineItemId = getTestedDiv(slotName, 1).getAttribute(lineItemIdAttribute);
    String secondLineItemId = getTestedDiv(slotName, 2).getAttribute(lineItemIdAttribute);
    Assertion.assertNotEquals(firstLineItemId, secondLineItemId);
    return this;
  }

  private WebElement getTestedDiv(String slotName, int testedDivNumber) {
    String slotSelector = AdsContent.getSlotSelector(slotName);
    java.util.List<WebElement> divs = driver.findElements(By.cssSelector(slotSelector + " > div"));
    if (testedDivNumber > divs.size()) {
      throw new IndexOutOfBoundsException(
          slotName + " slot has only " + String.valueOf(divs.size()) + " divs");
    }
    return divs.get(testedDivNumber - 1);
  }

  private String getPostMessagePattern(String src) {
    return "\nparent.postMessage('{\"AdEngine\":{\"status\":\"hop\",\"extra\":{\"source\":\"" + src
           + "/LB\"}}}', '*');\n";
  }
}
