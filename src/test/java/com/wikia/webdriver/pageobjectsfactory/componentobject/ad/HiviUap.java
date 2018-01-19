package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.Wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.Callable;

public class HiviUap {

  private final Wait wait;
  private final String slot;

  private static final String SLOT_SELECTOR = "#%s ";
  private static final String REPLAY_SELECTOR = ".replay-overlay";
  private static final String VIDEO_CLICK_AREA_SELECTOR = ".toggle-ui-overlay";
  private static final String VIDEO_THUMBNAIL_SELECTOR = ".videoThumbnail";
  private static final String PAUSE_SELECTOR = ".play-pause-button";
  private static final String SOUND_SELECTOR = ".volume-button";
  private static final String VIDEO_PLAYER = ".video-player";

  private final WikiaWebDriver driver;

  public HiviUap(WikiaWebDriver driver, String slot) {
    this.wait = new Wait(driver);
    this.driver = driver;
    this.slot = slot;
  }

  public void waitForVideoStart() {
    usingAdFrame(() -> wait.forElementNotVisible(By.cssSelector(VIDEO_THUMBNAIL_SELECTOR)));
  }

  public void waitForVideoEnd() {
    usingAdFrame(() -> wait.forElementVisible(By.cssSelector(String.format(REPLAY_SELECTOR, slot)), 60));
  }

  private void usingAdFrame(Runnable f) {
    WebElement adIframe = wait.forElementPresent(By.cssSelector(
        String.format(SLOT_SELECTOR, slot) + " iframe[id^=\"google_ads\"]"
    ));
    driver.switchTo().frame(adIframe);
    f.run();
    driver.switchTo().defaultContent();
  }

  private <T> T usingImaFrame(final Callable<T> f) throws Exception {
    WebElement adIframe = wait.forElementPresent(By.cssSelector(
        String.format(SLOT_SELECTOR, slot) + VIDEO_PLAYER + " iframe"
    ));
    driver.switchTo().frame(adIframe);
    T result = f.call();
    driver.switchTo().defaultContent();
    return result;
  }

  private void enableVideoToolbar() {
    WebElement videoPlayer = wait.forElementVisible(By.cssSelector(String.format(SLOT_SELECTOR, slot) + VIDEO_PLAYER));
    Actions builder = new Actions(driver);
    builder.moveToElement(videoPlayer).perform();
  }

  public void clickVideo() {
    waitForVideoStart();
    wait.forElementClickable(By.cssSelector(String.format(SLOT_SELECTOR, slot) + VIDEO_CLICK_AREA_SELECTOR)).click();
  }

  public void togglePause() {
    enableVideoToolbar();
    wait.forElementClickable(By.cssSelector(String.format(SLOT_SELECTOR, slot) + PAUSE_SELECTOR)).click();
  }

  public void toggleSound() {
    enableVideoToolbar();
    wait.forElementClickable(By.cssSelector(String.format(SLOT_SELECTOR, slot) + SOUND_SELECTOR)).click();
  }

  public double getCurrentTime() throws Exception {
    return Double.parseDouble(usingImaFrame(
        () -> wait.forElementPresent(By.cssSelector("video")).getAttribute("currentTime")
    ));
  }
}
