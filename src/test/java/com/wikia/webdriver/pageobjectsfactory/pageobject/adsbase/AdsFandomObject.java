package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdsFandomObject extends AdsBaseObject {
  private final String FANDOM_FEED_SELECTOR = ".feed-container";

  public AdsFandomObject(WebDriver driver, String testedPage) {
    super(driver, testedPage);
  }

  public AdsFandomObject(WebDriver driver, String testedPage, Dimension resolution) {
    super(driver, testedPage, resolution);
  }

  private WebElement slots(String slotSelector) {
    return driver.findElement(By.cssSelector(AdsFandomContent.getSlotSelector(slotSelector)));
  }

  public void triggerOnScrollSlots() {
    jsActions.scrollToBottom();
  }

  public void verifySlot(String slotName) {
    String selector = AdsFandomContent.getSlotSelector(slotName);

    scrollToSlot(AdsFandomContent.getGptSlotSelector(slotName));
    verifyAdVisibleInSlot(selector, slots(slotName));
  }

  public void scrollTo(String cssSelector) {
    jsActions.scrollToElement(driver.findElement(By.cssSelector(cssSelector)));
  }

  public void scrollToFeed() {
    scrollTo(FANDOM_FEED_SELECTOR);
  }

  public void scrollToSlot(String slotId) {
    jsActions.scrollToElement(driver.findElement(By.id(slotId)));
  }

  public WebElement getSlot(String slotName) {
    String selector = AdsFandomContent.getSlotSelector(slotName);

    if (isElementOnPage(By.cssSelector(selector))) {
      return driver.findElement(By.cssSelector(selector));
    }

    return null;
  }
}
