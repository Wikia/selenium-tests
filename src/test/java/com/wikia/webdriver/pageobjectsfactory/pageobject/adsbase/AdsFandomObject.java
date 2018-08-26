package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.contentpatterns.AdSlot;
import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;

import org.openqa.selenium.*;

public class AdsFandomObject extends AdsBaseObject {

  private static final By MOBILE_NAVIGATION_BAR = By.cssSelector(".global-navigation-mobile");
  private static final By DESKTOP_NAVIGATION_BAR = By.cssSelector(".wds-global-navigation");

  public AdsFandomObject(WebDriver driver, String testedPage) {
    super(testedPage);
  }

  public AdsFandomObject(WebDriver driver, String testedPage, Dimension resolution) {
    super(driver, testedPage, resolution);
  }

  private WebElement findSlotElement(AdSlot slot) {
    return driver.findElement(By.cssSelector(AdsFandomContent.getSlotSelectorString(slot)));
  }

  @Override
  public void waitForPageLoad() {
    wait.forElementPresent(By.cssSelector("body"));
  }

  public void triggerOnScrollSlots() {
    jsActions.scrollToBottom();
  }

  public void fixScrollPositionByNavbarOnF2(boolean isMobile) {
    By element = isMobile ? MOBILE_NAVIGATION_BAR : DESKTOP_NAVIGATION_BAR;

    int navbarHeight = driver.findElement(element).getSize().getHeight();
    jsActions.scrollBy(0, -navbarHeight);
  }

  public void verifySlot(AdSlot slot) {
    String selector = slot.getId();

    scrollTo(AdsFandomContent.getSlotSelector(slot));
    verifyAdVisibleInSlot(selector, findSlotElement(slot));
  }

  public WebElement getSlot(AdSlot slot) {
    String selector = slot.getId();

    if (isElementOnPage(By.cssSelector(selector))) {
      return driver.findElement(By.cssSelector(selector));
    }

    return null;
  }

  public By getIframeSelector(AdSlot slot) {
    return By.cssSelector(AdsFandomContent.IFRAME_SLOT_SELECTORS.getOrDefault(slot,
                                                                              getDefaultIframeSelector(
                                                                                  slot.getId())
    ));
  }

  private String getDefaultIframeSelector(String slotName) {
    return String.format("iframe[id^='google_ads_iframe_/5441/wka.fandom/_article/ARTICLE_%s_0']",
                         slotName
    );
  }
}
