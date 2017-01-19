package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

public class AutoplayVuap {

  private static final String PAUSE_CLASS_NAME = "pause-overlay";

  private static final String STOP_CLASS_NAME = "close-ad";

  private static final String CURRENT_TIME_CLASS_NAME = "current-time";

  private static final String PAUSE_BUTTON_SELECTOR_FORMAT = "#%s ." + PAUSE_CLASS_NAME;

  private static final String STOP_BUTTON_SELECTOR_FORMAT = "#%s ." + STOP_CLASS_NAME;

  private static final String CURRENT_TIME_SELECTOR_FORMAT = "#%s ." + CURRENT_TIME_CLASS_NAME;

  private final WikiaWebDriver driver;

  private final Wait wait;

  private final String slot;

  private final String iframeSelector;

  private boolean playing;

  public AutoplayVuap(WikiaWebDriver driver, String slot, String iframeSelector) {
    this.driver = driver;
    this.wait = new Wait(driver);

    this.slot = slot;
    this.iframeSelector = iframeSelector;
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

  public boolean isInvisible() {
    return usingVideoContext(video -> wait.forElementNotVisible(video));
  }

  public String findTitle() {
    return usingVideoContext(video -> video.getAttribute("title"));
  }

  private void clickElement(final String selector) {
    wait.forElementClickable(By.cssSelector(selector)).click();
  }

  private <T> T usingVideoContext(final Function<WebElement, T> fun) {
    return usingIframeContext(webDriver -> fun.apply(driver.findElement(By.tagName("video"))));
  }

  private <T> T usingIframeContext(final Function<WikiaWebDriver, T> fun) {
    final WebElement iframe = driver.findElement(By.cssSelector(iframeSelector));
    driver.switchTo().frame(iframe);
    final T result = fun.apply(driver);
    driver.switchTo().defaultContent();
    return result;
  }
}
