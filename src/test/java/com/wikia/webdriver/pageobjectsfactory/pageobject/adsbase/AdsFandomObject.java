package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.contentpatterns.AdSlot;

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
    return driver.findElement(By.cssSelector(slot.getId()));
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

    scrollTo(slot.getId());
    verifyAdVisibleInSlot(selector, findSlotElement(slot));
  }

  public WebElement getSlot(AdSlot slot) {
    String selector = slot.getId();

    if (isElementOnPage(By.cssSelector(selector))) {
      return driver.findElement(By.cssSelector(selector));
    }

    return null;
  }
}
