package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.google.common.base.Predicate;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class AutoplayVuap {

  private static final String SLOT_SELECTOR_PREFIX = "#%s .";

  private static final String PAUSE_CLASS_NAME = "pause-overlay ";

  private static final String REPLAY_CLASS_NAME = "replay-overlay";

  private static final String CURRENT_TIME_CLASS_NAME = "current-time";

  private static final String SPEAKER_CLASS_NAME = "speaker";

  private static final String CLOSE_BUTTON_CLASS_NAME = "close-ad";

  private static final String AD_TNG_CLICK_AREA_2_SELECTOR = "#area2";

  private static final String AD_TNG_CLICK_AREA_4_SELECTOR = "#area4";

  private static final String AD_RESOLVED_STATE_IMAGE_SELECTOR = "#background2";

  private static final String AD_DEFAULT_STATE_IMAGE_SELECTOR = "#background_right";

  // #TOP_LEADERBOARD .pause-overlay
  private static final String PAUSE_BUTTON_SELECTOR_FORMAT = SLOT_SELECTOR_PREFIX + PAUSE_CLASS_NAME;

  // #TOP_LEADERBOARD .replay-overlay
  private static final String REPLAY_BUTTON_SELECTOR_FORMAT = SLOT_SELECTOR_PREFIX + REPLAY_CLASS_NAME;

  // #TOP_LEADERBOARD .current-time
  private static final String CURRENT_TIME_SELECTOR_FORMAT = SLOT_SELECTOR_PREFIX + CURRENT_TIME_CLASS_NAME;

  // #TOP_LEADERBOARD .speaker
  private static final String SPEAKER_SELECTOR_FORMAT = SLOT_SELECTOR_PREFIX + SPEAKER_CLASS_NAME;

  // #TOP_LEADERBOARD .close-ad
  private static final String CLSE_BUTTON_SELECTOR_FORMAT = SLOT_SELECTOR_PREFIX + CLOSE_BUTTON_CLASS_NAME;

  private static final int EXPECTED_PERCENTAGE_DIFFERENCE_IN_VIDEO_AD_HEIGHT = 40;

  private final WikiaWebDriver driver;

  private final Wait wait;

  private final String slot;

  private final String videoIframeSelector;

  private boolean playing;

  private boolean muted;

  public AutoplayVuap(WikiaWebDriver driver, String slot, String videoIframeSelector) {
    this.driver = driver;
    this.wait = new Wait(driver);

    this.slot = slot;
    this.videoIframeSelector = videoIframeSelector;
    this.playing = true;
    this.muted = true;
  }

  public void mute() {
    if (!muted) {
      clickElement(String.format(SPEAKER_SELECTOR_FORMAT, slot));
      muted = true;
    }
  }

  public void unmute() {
    if (muted) {
      clickElement(String.format(SPEAKER_SELECTOR_FORMAT, slot));
      muted = false;
    }
  }

  public void pause() {
    if (playing) {
      clickElement(String.format(PAUSE_BUTTON_SELECTOR_FORMAT, slot));
      playing = false;
    }
  }

  public void play() {
    if (!playing) {
      clickElement(String.format(PAUSE_BUTTON_SELECTOR_FORMAT, slot));
      playing = true;
    }
  }

  public void replay() {
      clickElement(String.format(REPLAY_BUTTON_SELECTOR_FORMAT, slot));
      muted = false;
      playing = true;
  }

  public void close() {
    findCloseButton().click();
  }

  public void clickOnDefaultStateAdImage() {
    clickOnAdImage(AD_DEFAULT_STATE_IMAGE_SELECTOR);
  }

  public void clickOnClickArea2() {
    clickOnAdImage(AD_TNG_CLICK_AREA_2_SELECTOR);
  }

  public void clickOnClickArea4() {
    clickOnAdImage(AD_TNG_CLICK_AREA_4_SELECTOR);
  }

  public void clickOnAdImageResolvedState() {
    clickOnAdImage(AD_RESOLVED_STATE_IMAGE_SELECTOR);
  }

  private void clickOnAdImage(String clickAreaSelector) {
    final WebElement iframe = driver.findElement(By.cssSelector("#" + slot + " .provider-container iframe"));
    driver.switchTo().frame(iframe);
    driver.findElement(By.cssSelector(clickAreaSelector)).click();
    driver.switchTo().defaultContent();
  }

  public double getCurrentTime() {
    final String currentTime = usingVideoContext(video -> video.getAttribute("currentTime"));
    return Double.parseDouble(currentTime);
  }

  public double getVideoHieghtWhilePaused() {
    if (!playing) {
      return driver.findElement(By.cssSelector(String.format(PAUSE_BUTTON_SELECTOR_FORMAT, slot))).getSize().getHeight();
    }
    return 0;
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

  public boolean isMuted() {
    return findSpeakerIcon().getAttribute("class").contains("mute");
  }

  public boolean isUnmuted() {
    return !isMuted();
  }

  public String findTitle() {
    return usingVideoContext(video -> video.getAttribute("title"));
  }

  public void waitForVideoToStart(final long timeout) {
    waitFor(AutoplayVuap::isVisible, timeout);
  }

  public void waitForVideoToEnd(final long timeout) {
    waitFor(AutoplayVuap::isOverlayVisible, timeout);
  }

  private void clickElement(final String selector) {
    wait.forElementVisible(By.cssSelector(selector)).click();
  }

  private WebElement findSpeakerIcon() {
    return wait.forElementClickable(By.cssSelector(String.format(SPEAKER_SELECTOR_FORMAT, slot)));
  }

  private WebElement findCloseButton() {
    return wait.forElementClickable(By.cssSelector(String.format(CLSE_BUTTON_SELECTOR_FORMAT, slot)));
  }

  private void waitFor(final Predicate<AutoplayVuap> predicate, final long timeout) {
    new FluentWait<>(this)
        .withTimeout(timeout, TimeUnit.SECONDS)
        .pollingEvery(1, TimeUnit.SECONDS)
        .until(predicate);
  }

  private boolean isOverlayVisible() {
    return driver.findElement(By.cssSelector(String.format(REPLAY_BUTTON_SELECTOR_FORMAT, slot))).isDisplayed();
  }

  public boolean isResolvedStateDisplayed(double defaultVideoHeight, double resolvedVideoHeight) {
    return EXPECTED_PERCENTAGE_DIFFERENCE_IN_VIDEO_AD_HEIGHT == getStatesPercentageDifference(defaultVideoHeight, resolvedVideoHeight);
  }

  private int getStatesPercentageDifference(double defaultVideoHeight, double resolvedVideoHeight) {
    return (int) Math.round(100 - (100 / (defaultVideoHeight / resolvedVideoHeight)));
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
