package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.elemnt.Wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;

import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class AutoplayVuap {

  public static final Color COLOR_VUAP_VIDEO_AD = new Color(0, 1, 253);
  private static final String SLOT_SELECTOR_PREFIX = "#%s ";
  private static final String PAUSE_SELECTOR = ".pause-overlay";
  private static final String REPLAY_SELECTOR = ".replay-overlay";
  private static final String CURRENT_TIME_SELECTOR = ".current-time";
  private static final String SPEAKER_SELECTOR = ".volume-button.porvata-switchable-icon";
  private static final String CLOSE_BUTTON_SELECTOR = ".close-ad";
  private static final String AD_TNG_CLICK_AREA_2_SELECTOR = "#area2";
  private static final int PERCENTAGE_DIFFERENCE_BETWEEN_VIDEO_AND_IMAGE_AD = 28;
  // #TOP_LEADERBOARD .pause-overlay
  private static final String PAUSE_BUTTON_SELECTOR_FORMAT = SLOT_SELECTOR_PREFIX + PAUSE_SELECTOR;

  // #TOP_LEADERBOARD .replay-overlay
  private static final String REPLAY_BUTTON_SELECTOR_FORMAT = SLOT_SELECTOR_PREFIX
                                                              + REPLAY_SELECTOR;

  // #TOP_LEADERBOARD .current-time
  private static final String CURRENT_TIME_SELECTOR_FORMAT = SLOT_SELECTOR_PREFIX
                                                             + CURRENT_TIME_SELECTOR;

  // #TOP_LEADERBOARD .speaker
  private static final String SPEAKER_SELECTOR_FORMAT = SLOT_SELECTOR_PREFIX + SPEAKER_SELECTOR;

  // #TOP_LEADERBOARD .close-ad
  private static final String CLOSE_BUTTON_SELECTOR_FORMAT = SLOT_SELECTOR_PREFIX
                                                             + CLOSE_BUTTON_SELECTOR;

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
    wait.forElementClickable(pauseOverlaySelector);
    // hack to click pause overlay on mobile
    clickByJS(pauseOverlaySelector);
  }

  public void play() {
    if (isPausedWithOverlay()) {
      togglePause();
    } else if (!this.mobile) {
      clickOnArea(2);
    } else {
      clickOnArea(4);
    }
  }

  private boolean isPausedWithOverlay() {
    return isVideoPaused() && driver.findElement(pauseOverlaySelector).isDisplayed();
  }

  private Boolean isVideoPaused() {
    return isDesktop() ? isDesktopVideoPaused() : isMobileVideoPaused();
  }

  private boolean isDesktop() {
    return hasDesktopVideoElement();
  }

  private Boolean isMobileVideoPaused() {
    return Boolean.valueOf(driver.findElement(getVideoSelector()).getAttribute("paused"));
  }

  private Boolean isDesktopVideoPaused() {
    return Boolean.valueOf(usingImaBridge(webDriver -> {
      final JavascriptActions js = new JavascriptActions();

      return js.execute("document.querySelector('video') && document.querySelector('video').paused")
          .toString();
    }));
  }

  public void replay() {
    wait.forElementVisible(replayOverlaySelector).click();
  }

  public void close() {
    wait.forElementClickable(closeButtonSelector).click();
  }

  public void closeWithJS() {
    clickByJS(closeButtonSelector);
  }

  private void clickByJS(By selector) {
    new JavascriptActions(driver).execute("arguments[0].click();", driver.findElement(selector));
  }

  private void clickOnTopLeftCornerOfElement(WebElement element) {
    int y = element.getSize().height / 4;
    int x = element.getSize().width / 4;

    new Actions(driver).moveToElement(element, x, y).click().build().perform();
  }

  private void clickElementInsideAd(By selector) {
    usingAdFrame(() -> {
      // It need to be clicked on element top left corner, because our templates elements covers each other
      clickOnTopLeftCornerOfElement(driver.findElement(selector));
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

  public boolean isVisible() {
    return driver.findElement(getVideoSelector()).isDisplayed();
  }

  public boolean isMuted() {
    return wait.forElementVisible(speakerSelector).getAttribute("class").contains("is-on");
  }

  public boolean isUnmuted() {
    return !isMuted();
  }

  private String getTitleDesktop() {
    return usingVideoContext(video -> video.getAttribute("title"));
  }

  private String getTitleMobile() {
    return driver.findElement(getVideoSelector()).getAttribute("title");
  }

  public String findTitle() {
    return isDesktop() ? getTitleDesktop() : getTitleMobile();
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

  private void waitFor(final Function<AutoplayVuap, Boolean> predicate, final long timeout) {
    new FluentWait<>(this).withTimeout(timeout, TimeUnit.SECONDS)
        .pollingEvery(1, TimeUnit.SECONDS)
        .until(predicate);
  }

  private boolean isOverlayNoVisible() {
    return wait.forElementNotVisible(pauseOverlaySelector);
  }

  public boolean isVideoAdBiggerThanImageAd(double videoHeight, double imageHeight) {
    int percentResult = (int) Math.round(100 - (100 / (videoHeight / imageHeight)));
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

  public boolean hasVideoExpectedColor(Color expectedColor) {
    final WebElement pauseOverlayElement = driver.findElement(this.pauseOverlaySelector);
    return hasElementExpectedColor(pauseOverlayElement, expectedColor);
  }

  private boolean hasElementExpectedColor(WebElement element, Color expectedColor) {
    ElementColor elementColor = new ElementColor(driver);

    elementColor.verifyMostFrequentColor(element, expectedColor, 10);

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
    return isDesktop() ? getCurrentTimeDesktop() : getCurrentTimeMobile();
  }

  private Double getCurrentTimeMobile() {
    return Double.parseDouble(driver.findElement(getVideoSelector()).getAttribute("currentTime"));
  }

  private Double getCurrentTimeDesktop() {
    return Double.parseDouble(usingVideoContext(video -> video.getAttribute("currentTime")));
  }

  private boolean hasDesktopVideoElement() {
    return usingImaBridge(webDriver -> driver.findElements(By.cssSelector("video")).size() > 0);
  }

  private By getVideoSelector() {
    return By.cssSelector("#" + slot + " video");
  }
}
