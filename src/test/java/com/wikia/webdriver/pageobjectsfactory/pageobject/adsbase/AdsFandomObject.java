package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class AdsFandomObject extends AdsBaseObject {

  public AdsFandomObject(WebDriver driver, String testedPage) {
    super(driver, testedPage);
  }

  private WebElement slots(String slotSelector) {
    return driver.findElement(By.cssSelector(AdsFandomContent.getSlotSelector(slotSelector)));
  }

  public void triggerOnScrollSlots() {
    jsActions.scrollToBottom();
  }

  public void verifySlot(String slotName) {
    String selector = AdsFandomContent.getSlotSelector(slotName);

    scrollToSlot(AdsFandomContent.getGptSlotSelector(slotName), 60);
    verifyAdVisibleInSlot(selector, slots(slotName));
  }

  public void scrollToSlot(String slotSelector, int offset) {
    jsActions.scrollToElement(driver.findElement(By.id(slotSelector)), offset);
  }

  public WebElement getSlot(String slotName) {
    String selector = AdsFandomContent.getSlotSelector(slotName);

    if (isElementOnPage(By.cssSelector(selector))) {
      return driver.findElement(By.cssSelector(selector));
    }

    return null;
  }
}
