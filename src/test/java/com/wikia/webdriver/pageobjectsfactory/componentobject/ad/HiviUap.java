package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.Wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HiviUap {

  private final Wait wait;
  private final String slot;

  private static final String SLOT_SELECTOR = "#%s ";
  private static final String REPLAY_SELECTOR = ".replay-overlay";

  private final WikiaWebDriver driver;

  public HiviUap(WikiaWebDriver driver, String slot) {
    this.wait = new Wait(driver);
    this.driver = driver;
    this.slot = slot;
  }

  public void waitForVideoEnd() {
    usingAdFrame(() -> {
      wait.forElementVisible(By.cssSelector(String.format(REPLAY_SELECTOR, slot)), 60);
    });
  }

  private void usingAdFrame(Runnable f) {
    WebElement adIframe = wait.forElementPresent(By.cssSelector(
        String.format(SLOT_SELECTOR, slot) + " iframe[id^=\"google_ads\"]"
    ));
    driver.switchTo().frame(adIframe);
    f.run();
    driver.switchTo().defaultContent();
  }

}
