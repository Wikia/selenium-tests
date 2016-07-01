package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class AdsFandomObject extends AdsBaseObject {

  @FindBy(css = "div[id$='TOP_LEADERBOARD_0__container__']")
  protected WebElement topLeaderboardElement;

  @FindBy(css = "div[id$='BOTTOM_LEADERBOARD_0__container__']")
  protected WebElement bottomLeaderboardElement;

  @FindBy(css = "div[id$='TOP_BOXAD_0__container__']")
  protected WebElement topBoxadElement;

  @FindBy(css = "div[id$='INCONTENT_BOXAD_0__container__']")
  protected WebElement incontentBoxadElement;

  @FindBy(css = "div[id$='BOTTOM_BOXAD_0__container__']")
  protected WebElement bottomBoxadElement;

  protected final Map<String, WebElement> slots;

  public AdsFandomObject(WebDriver driver, String testedPage) {
    super(driver, testedPage);

    slots = new HashMap<>();
    slots.put(AdsFandomContent.TOP_LEADERBOARD, topLeaderboardElement);
    slots.put(AdsFandomContent.BOTTOM_LEADERBOARD, bottomLeaderboardElement);
    slots.put(AdsFandomContent.TOP_BOXAD, topBoxadElement);
    slots.put(AdsFandomContent.INCONTENT_BOXAD, incontentBoxadElement);
    slots.put(AdsFandomContent.BOTTOM_BOXAD, bottomBoxadElement);
  }

  public void triggerOnScrollSlots() {
    JavascriptExecutor js = driver;
    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
  }

  public long getLineItemId(String slotName) {
    JavascriptExecutor js = driver;
    try {
      return (long) js.executeScript(
          "var slots = googletag.getSlots(); for (var i = 0; i < slots.length; i++) { " +
          "if (slots[i].getTargeting('pos').indexOf('" + slotName + "') !== -1) { " +
          "return slots[i].getResponseInformation().lineItemId;" +
          "} }"
      );
    } catch (WebDriverException e) {
      PageObjectLogging.log("JSError", "Can not get line item id of " + slotName + ": " +
          e.getMessage(), false);
      return 0;
    }
  }

  public void verifySlot(String slotName) {
    String selector = AdsFandomContent.getSlotSelector(slotName);

    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(selector)));
    verifyAdVisibleInSlot(selector, slots.get(slotName));
  }

  public WebElement getSlot(String slotName) {
    String selector = AdsFandomContent.getSlotSelector(slotName);

    if (isElementOnPage(By.cssSelector(selector))) {
      return driver.findElement(By.cssSelector(selector));
    }

    return null;
  }
}
