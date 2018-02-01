package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdsFandomObject extends AdsBaseObject {
  public AdsFandomObject(WebDriver driver, String testedPage) {
    super(driver, testedPage);
  }

  public AdsFandomObject(WebDriver driver, String testedPage, Dimension resolution) {
    super(driver, testedPage, resolution);
  }

  private WebElement findSlotElement(String slotSelector) {
    return driver.findElement(By.cssSelector(AdsFandomContent.getSlotSelectorString(slotSelector)));
  }

  public void triggerOnScrollSlots() {
    jsActions.scrollToBottom();
  }

  public void verifySlot(String slotName) {
    String selector = AdsFandomContent.getSlotSelectorString(slotName);

    scrollTo(AdsFandomContent.getSlotSelector(slotName));
    verifyAdVisibleInSlot(selector, findSlotElement(slotName));
  }

  public WebElement getSlot(String slotName) {
    String selector = AdsFandomContent.getSlotSelectorString(slotName);

    if (isElementOnPage(By.cssSelector(selector))) {
      return driver.findElement(By.cssSelector(selector));
    }

    return null;
  }

  public By getIframeSelector(String slotName) {
    return By.cssSelector(String.format("iframe[id^='google_ads_iframe_/5441/wka.fandom/_article/ARTICLE_%s_0']", slotName));
  }

}
