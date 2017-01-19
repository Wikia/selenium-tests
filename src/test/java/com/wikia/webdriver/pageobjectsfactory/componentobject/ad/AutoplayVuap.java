package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.google.common.base.Predicate;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import javax.annotation.Nullable;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class AutoplayVuap {

  private static final String SLOT_SELECTOR_PREFIX = "#%s .";

  private static final String PAUSE_CLASS_NAME = "pause-overlay";

  private static final String STOP_CLASS_NAME = "close-ad";

  private static final String CURRENT_TIME_CLASS_NAME = "current-time";

  private static final String PAUSE_BUTTON_SELECTOR_FORMAT = SLOT_SELECTOR_PREFIX + PAUSE_CLASS_NAME;

  private static final String STOP_BUTTON_SELECTOR_FORMAT = SLOT_SELECTOR_PREFIX + STOP_CLASS_NAME;

  private static final String CURRENT_TIME_SELECTOR_FORMAT = SLOT_SELECTOR_PREFIX + CURRENT_TIME_CLASS_NAME;

  private final WikiaWebDriver driver;

  private final Wait wait;

  private final String slot;

  private final String videoIframeSelector;

  private boolean playing;

  public AutoplayVuap(WikiaWebDriver driver, String slot, String videoIframeSelector) {
    this.driver = driver;
    this.wait = new Wait(driver);

    this.slot = slot;
    this.videoIframeSelector = videoIframeSelector;
    this.playing = true;
  }

  public void play() {
    if (!playing) {
      clickElement(String.format(PAUSE_BUTTON_SELECTOR_FORMAT, slot));
      playing = true;
    }
  }

  public void pause() {
    if (playing) {
      clickElement(String.format(PAUSE_BUTTON_SELECTOR_FORMAT, slot));
      playing = false;
    }
  }

  public void stop() {
    if (playing) {
      clickElement(String.format(STOP_BUTTON_SELECTOR_FORMAT, slot));
      playing = false;
    }
  }

  public void clickOnImage() {
    final WebElement iframe = driver.findElement(By.cssSelector("#" + slot + " .provider-container iframe"));
    driver.switchTo().frame(iframe);
    driver.findElement(By.id("background_left")).click();
    driver.switchTo().defaultContent();
  }

  public double getCurrentTime() {
    final String currentTime = usingVideoContext(video -> video.getAttribute("currentTime"));
    return Double.parseDouble(currentTime);
  }

  public double getIndicatorCurrentTime() {
    final String selector = String.format(CURRENT_TIME_SELECTOR_FORMAT, slot);
    final String value = driver.findElement(By.cssSelector(selector)).getCssValue("transition-duration");
    return Double.parseDouble(value.substring(0, value.length() - 1));
  }

  public boolean hasStarted() {
    return getCurrentTime() > 0;
  }

  public boolean isVisible() {
    return usingVideoIframeContext(webDriver -> webDriver.findElement(By.tagName("video")).isDisplayed());
  }

  public boolean isInvisible() {
    return usingVideoContext(video -> wait.forElementNotVisible(video));
  }

  public String findTitle() {
    return usingVideoContext(video -> video.getAttribute("title"));
  }

  public void waitForVideoToStart(final long timeout) {
    waitFor(AutoplayVuap::isVisible, timeout);
  }

  public void waitForVideoToEnd(final long timeout) {
    waitFor(AutoplayVuap::isInvisible, timeout);
  }

  private void clickElement(final String selector) {
    wait.forElementClickable(By.cssSelector(selector)).click();
  }

  private void waitFor(final Predicate<AutoplayVuap> predicate, final long timeout) {
    new FluentWait<AutoplayVuap>(this)
        .withTimeout(timeout, TimeUnit.SECONDS)
        .pollingEvery(1, TimeUnit.SECONDS)
        .until(predicate);
  }

  private <T> T usingVideoContext(final Function<WebElement, T> fun) {
    return usingVideoIframeContext(webDriver -> fun.apply(driver.findElement(By.tagName("video"))));
  }

  private <T> T usingVideoIframeContext(final Function<WikiaWebDriver, T> fun) {
    final WebElement iframe = driver.findElement(By.cssSelector(videoIframeSelector));
    driver.switchTo().frame(iframe);
    final T result = fun.apply(driver);
    driver.switchTo().defaultContent();
    return result;
  }
}
