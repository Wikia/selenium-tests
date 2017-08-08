package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.google.common.base.Predicate;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class AutoplayVuap {

  private static final String SLOT_SELECTOR_PREFIX = "#%s .";
  private static final String PAUSE_CLASS_NAME = "pause-overlay";
  private static final String REPLAY_CLASS_NAME = "replay-overlay";
  private static final String CURRENT_TIME_CLASS_NAME = "current-time";
  private static final String SPEAKER_CLASS_NAME = "speaker";
  private static final String CLOSE_BUTTON_CLASS_NAME = "close-ad";
  private static final String AD_TNG_CLICK_AREA_2_SELECTOR = "#area2";
  private static final String AD_TNG_CLICK_AREA_4_SELECTOR = "#area4";
  private static final String AD_RESOLVED_STATE_IMAGE_SELECTOR = "#background2";
  private static final int PERCENTAGE_DIFFERENCE_BETWEEN_VIDEO_AND_IMAGE_AD = 28;

  // #TOP_LEADERBOARD .pause-overlay
  private static final String PAUSE_BUTTON_SELECTOR_FORMAT = SLOT_SELECTOR_PREFIX + PAUSE_CLASS_NAME;

  // #TOP_LEADERBOARD .replay-overlay
  private static final String REPLAY_BUTTON_SELECTOR_FORMAT = SLOT_SELECTOR_PREFIX + REPLAY_CLASS_NAME;

  // #TOP_LEADERBOARD .current-time
  private static final String CURRENT_TIME_SELECTOR_FORMAT = SLOT_SELECTOR_PREFIX + CURRENT_TIME_CLASS_NAME;

  // #TOP_LEADERBOARD .speaker
  private static final String SPEAKER_SELECTOR_FORMAT = SLOT_SELECTOR_PREFIX + SPEAKER_CLASS_NAME;

  // #TOP_LEADERBOARD .close-ad
  private static final String
      CLOSE_BUTTON_SELECTOR_FORMAT = SLOT_SELECTOR_PREFIX + CLOSE_BUTTON_CLASS_NAME;

  private static final int EXPECTED_PERCENTAGE_DIFFERENCE_IN_VIDEO_AD_HEIGHT = 40;

  private static final String URL_FIRSTQUARTILE = "ad_vast_point=firstquartile";
  private static final String URL_MIDPOINT = "ad_vast_point=midpoint";

  private final WikiaWebDriver driver;

  private final Wait wait;

  private final String slot;

  private final By imaBridgeSelector;
  private final By adIframeSelector;
  private final By speakerSelector;
  private final By pauseOverlaySelector;
  private final By closeButtonSelector;
  private final By progressBarSelector;
  private final By replayOverlaySelector;

  private boolean mobile;

  public AutoplayVuap(WikiaWebDriver driver, String slot, String adIframeId) {
    this(driver, slot, By.id(adIframeId), false);
  }

  public AutoplayVuap(WikiaWebDriver driver, String slot, By adIframeSelector, Boolean mobile) {
    this.driver = driver;
    this.wait = new Wait(driver);

    this.slot = slot;
    this.imaBridgeSelector = By.cssSelector("#" + slot + " .video-player iframe[src*='imasdk']");
    this.adIframeSelector = adIframeSelector;
    this.mobile = mobile;

    speakerSelector = By.cssSelector(String.format(SPEAKER_SELECTOR_FORMAT, slot));
    pauseOverlaySelector = By.cssSelector(String.format(PAUSE_BUTTON_SELECTOR_FORMAT, slot));
    closeButtonSelector = By.cssSelector(String.format(CLOSE_BUTTON_SELECTOR_FORMAT, slot));
    progressBarSelector = By.cssSelector(String.format(CURRENT_TIME_SELECTOR_FORMAT, slot));
    replayOverlaySelector = By.cssSelector(String.format(REPLAY_BUTTON_SELECTOR_FORMAT, slot));
  }

  public void mute() {
    if (!isMuted()) {
      wait.forElementClickable(speakerSelector).click();
    }
  }

  public void unmute() {
    if (isMuted()) {
      wait.forElementClickable(speakerSelector).click();
    }
  }

  public void togglePause() {
    wait.forElementClickable(pauseOverlaySelector).click();
  }

  public void play() {
    if (isPausedWithOverlay()) {
      togglePause();
    } else if( !this.mobile ) {
      clickOnArea(2);
    } else {
      clickOnArea(4);
    }
  }

  public void playVideoFor(Duration duration) {
    play();

    try {
      Thread.sleep(duration.toMillis());
    } catch (InterruptedException x) {
      // ignore this exception
    }

    togglePause();
  }

  private boolean isPausedWithOverlay() {
    return isVideoPaused() && driver.findElement(pauseOverlaySelector).isDisplayed();
  }

  private Boolean isVideoPaused() {
    return Boolean.valueOf(usingImaBridge(webDriver -> {
      final JavascriptActions js = new JavascriptActions();

      return js.execute("document.querySelector('video') && document.querySelector('video').paused").toString();
    }));
  }

  public void replay() {
    wait.forElementVisible(replayOverlaySelector).click();
  }

  public void close() {
    wait.forElementClickable(closeButtonSelector).click();
  }

  public void closeWithJS() {
    new JavascriptActions(driver).execute(
        "arguments[0].click();",
        driver.findElement(closeButtonSelector)
    );
  }

  public void clickOnClickArea2() {
    clickElementInsideAd(By.cssSelector(AD_TNG_CLICK_AREA_2_SELECTOR));
  }

  public void clickOnClickArea4() {
    clickElementInsideAd(By.cssSelector(AD_TNG_CLICK_AREA_4_SELECTOR));
  }

  public void clickOnAdImageResolvedState() {
    clickElementInsideAd(By.cssSelector(AD_RESOLVED_STATE_IMAGE_SELECTOR));
  }

  private void clickElementInsideAd(By selector) {
    usingAdFrame(() -> {
      JavascriptActions jsActions = new JavascriptActions(driver);
      // It need to be clicked by JS, because our templates elements covers each other
      // and there is no way to click it by just .click()
      jsActions.execute("arguments[0].click();", driver.findElement(selector));
    });
  }

  public double getVideoHeightWhilePaused() {
    return driver.findElement(pauseOverlaySelector).getSize().getHeight();
  }

  public double getAdSlotHeight() {
    waitForAdToLoad();
    return driver.findElement(By.id(slot)).getSize().getHeight();
  }

  public int getProgressBarWidth() {
    return driver.findElement(progressBarSelector).getSize().getWidth();
  }

  public boolean hasStarted() {
    return getCurrentTime() > 0;
  }

  public boolean isVisible() {
    return usingImaBridge(webDriver -> webDriver.findElement(By.tagName("video")).isDisplayed());
  }

  public boolean isMuted() {
    return wait.forElementVisible(speakerSelector).getAttribute("class").contains("mute");
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
    waitFor(AutoplayVuap::isOverlayNoVisible, timeout);
  }

  public void waitForAdToLoad() {
    usingAdFrame(() -> wait.forElementPresent(By.cssSelector(AD_TNG_CLICK_AREA_2_SELECTOR)));
  }

  private void waitFor(final Predicate<AutoplayVuap> predicate, final long timeout) {
    new FluentWait<>(this)
        .withTimeout(timeout, TimeUnit.SECONDS)
        .pollingEvery(1, TimeUnit.SECONDS)
        .until(predicate);
  }

  private boolean isOverlayNoVisible() {
    return wait.forElementNotVisible(pauseOverlaySelector);
  }

  public boolean isVideoAdBiggerThanImageAd(double videoHeight, double imageHeight) {
    int percentResult = (int)Math.round(100-(100/(videoHeight/imageHeight)));
    return percentResult == PERCENTAGE_DIFFERENCE_BETWEEN_VIDEO_AND_IMAGE_AD;
  }

  private <T> T usingVideoContext(final Function<WebElement, T> fun) {
    return usingImaBridge(webDriver -> fun.apply(driver.findElement(By.tagName("video"))));
  }

  private <T> T usingImaBridge(final Function<WikiaWebDriver, T> fun) {
    driver.switchTo().frame(driver.findElement(imaBridgeSelector));
    final T result = fun.apply(driver);
    driver.switchTo().defaultContent();
    return result;
  }

  private void usingAdFrame(Runnable f) {
    wait.forElementPresent(adIframeSelector);
    driver.switchTo().frame(driver.findElement(adIframeSelector));
    f.run();
    driver.switchTo().defaultContent();
  }

  public boolean isPauseLayerVisible() {
    wait.forElementVisible(pauseOverlaySelector);
    return true;
  }

  public boolean isPauseLayerNotVisible() {
    wait.forElementNotVisible(pauseOverlaySelector);
    return true;
  }

  public void clickOnArea(int area) {
    clickElementInsideAd(By.id("area" + area));
  }

  public void waitForVideoStart() {
    isPauseLayerVisible();
  }

  public void waitForVideoPlayerHidden() {
    isPauseLayerNotVisible();
  }

  public Double getCurrentTime() {
    String result;

    if (hasMobileVideoElement()) {
      result = usingVideoContext(video -> video.getAttribute("currentTime"));
    } else {
      result = driver.findElement(getMobileVideoSelector()).getAttribute("currentTime");
    }
    return Double.parseDouble(result);
  }

  private boolean hasMobileVideoElement() {
    return driver.findElements(getMobileVideoSelector()).size() == 0;
  }

  public void waitForFirstQuartile(NetworkTrafficInterceptor networkTrafficInterceptor) {
    wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);
  }

  public void waitForMidPoint(NetworkTrafficInterceptor networkTrafficInterceptor) {
    wait.forSuccessfulResponse(networkTrafficInterceptor, URL_MIDPOINT);
  }

  private By getMobileVideoSelector() {
    return By.cssSelector("#" + slot + " video");
  }
}
