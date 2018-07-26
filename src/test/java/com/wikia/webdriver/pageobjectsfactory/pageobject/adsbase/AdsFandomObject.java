package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

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

  private WebElement findSlotElement(String slotSelector) {
    return driver.findElement(By.cssSelector(AdsFandomContent.getSlotSelectorString(slotSelector)));
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
    return By.cssSelector(AdsFandomContent.IFRAME_SLOT_SELECTORS.getOrDefault(slotName,
                                                                              getDefaultIframeSelector(
                                                                                  slotName)
    ));
  }

  private String getDefaultIframeSelector(String slotName) {
    return String.format("iframe[id^='google_ads_iframe_/5441/wka.fandom/_article/ARTICLE_%s_0']",
                         slotName
    );
  }
}
