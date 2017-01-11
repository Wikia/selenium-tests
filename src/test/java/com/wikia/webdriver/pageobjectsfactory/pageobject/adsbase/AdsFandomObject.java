package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
    jsActions.scrollToBottom();
  }

  public void verifySlot(String slotName) {
    String selector = AdsFandomContent.getSlotSelector(slotName);

    scrollToSlot(selector);
    verifyAdVisibleInSlot(selector, slots.get(slotName));
  }

  public void scrollToSlot(String slotSelector) {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(slotSelector)));
  }

  public WebElement getSlot(String slotName) {
    String selector = AdsFandomContent.getSlotSelector(slotName);

    if (isElementOnPage(By.cssSelector(selector))) {
      return driver.findElement(By.cssSelector(selector));
    }

    return null;
  }
}
