package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsComparison;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class ElementColor {
  private WikiaWebDriver driver;

  public ElementColor(WikiaWebDriver driver) {
    this.driver = driver;
  }

  public void verifyColor(WebElement element, Color color, int durationSec) {
    AdsComparison adsComparison = new AdsComparison();
    waitForColor(element, color);
    adsComparison.verifyColorAd(element, color, durationSec, driver);
  }

  private void waitForColor(WebElement element, Color color) {
    WebDriverWait waitFor = new WebDriverWait(driver, 15);
    driver.manage().timeouts().implicitlyWait(500, TimeUnit.MICROSECONDS);

    try {
      waitFor.until(CommonExpectedConditions
                        .elementToHaveColor(element, color,
                                            AdsComparison.IMAGES_THRESHOLD_PERCENT));
    } finally {
      driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
  }
}
