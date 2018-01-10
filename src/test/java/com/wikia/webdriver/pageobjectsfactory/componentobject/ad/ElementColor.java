package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsComparison;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;

public class ElementColor {
  private WikiaWebDriver driver;

  public ElementColor(WikiaWebDriver driver) {
    this.driver = driver;
  }

  public void verifyMostFrequentColor(WebElement element, Color color) {
    verifyMostFrequentColor(element, color, 30);
  }

  public void verifyMostFrequentColor(WebElement element, Color color, int timeOutInSeconds) {
    AdsComparison adsComparison = new AdsComparison();
    WebDriverWait waitFor = new WebDriverWait(driver, timeOutInSeconds);

    waitFor.until(new ExpectedCondition<Object>() {
      @Override
      public Object apply(WebDriver webDriver) {
        return adsComparison.isMostFrequentColorValid(driver, element, color);
      }

      @Override
      public String toString() {
        return color + " is not most frequent on image";
      }
    });
  }
}
