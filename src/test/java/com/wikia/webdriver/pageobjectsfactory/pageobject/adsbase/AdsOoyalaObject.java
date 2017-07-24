package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsComparison;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.SoundMonitor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AdsOoyalaObject extends AdsBaseObject {

  private static final Color GREEN = new Color(20, 255, 13);
  private static final Color BLUE = new Color(0, 1, 253);
  private static final int AD_DURATION_SEC = 30;
  private static final int VIDEO_DURATION_SEC = 30;

  private static final String ARTICLE_VIDEO_CLASS = "ooyala-article-video";
  private static final String ARTICLE_VIDEO_PREROLL_SELECTOR = ".ooyala-article-video iframe[src*=imasdk]";
  private static final String ARTICLE_VIDEO_SELECTOR = ".ooyala-article-video > .innerWrapper > video";
  private static final String ARTICLE_VIDEO_WRAPPER_SELECTOR = ".article-featured-video__placeholder, #ooyala-article-video > .innerWrapper";
  private static final String ARTICLE_VIDEO_CLICK_AREA_SELECTOR = ".article-featured-video__placeholder, #ooyala-article-video .oo-state-screen-selectable";
  private static final String MOBILE_ARTICLE_VIDEO_PLAY_ICON = ".article-featured-video__play-circle";
  private static final By PLAYER_SELECTOR = By.id("ooyala-article-video");
  private static final By AD_LAYER_SELECTOR = By.cssSelector(ARTICLE_VIDEO_PREROLL_SELECTOR);

  @FindBy(css = "div[id^='ooyalaplayer'] > .innerWrapper")
  private WebElement lightboxVideo;

  @FindBy(css = ARTICLE_VIDEO_WRAPPER_SELECTOR)
  private WebElement articleVideoWrapper;

  @FindBy(id = ARTICLE_VIDEO_CLASS)
  private WebElement articleVideo;

  @FindBy(css = ARTICLE_VIDEO_CLICK_AREA_SELECTOR)
  private WebElement articleVideoClickArea;

  @FindBy(css = MOBILE_ARTICLE_VIDEO_PLAY_ICON)
  private WebElement mobileArticleVideoPlayButton;

  @FindBy(css = ".oo-volume.oo-control-bar-item")
  private WebElement volumeControlButton;


  public AdsOoyalaObject(WebDriver driver, String page) {
    super(driver, page);
  }

  public void playLightboxVideo() {
    wait.forElementVisible(lightboxVideo);
    lightboxVideo.click();
  }

  public void playArticleVideo() {
    wait.forElementVisible(articleVideoWrapper);
    articleVideoClickArea.click();
  }

  public void playArticleVideoOnMobile() {
    wait.forElementVisible(mobileArticleVideoPlayButton);
    mobileArticleVideoPlayButton.click();
  }

  public void clickVolumeButton() {
    hoverPlayerToActivateUI();
    wait.forElementVisible(volumeControlButton);
    volumeControlButton.click();
  }

  private void hoverPlayerToActivateUI() {
    builder.moveToElement(driver.findElement(PLAYER_SELECTOR)).pause(500).perform();
  }

  public void verifyPlayerOnPage() {
    wait.forElementPresent(PLAYER_SELECTOR);
  }

  public Boolean wasSoundHeard() {
    return SoundMonitor.wasSoundHeardOnPage(jsActions);
  }

  public void verifyArticleAd() {
    verifyFeaturedVideoElement(ARTICLE_VIDEO_PREROLL_SELECTOR, BLUE, AD_DURATION_SEC);
  }

  public void verifyArticleVideo() {
    verifyFeaturedVideoElement(ARTICLE_VIDEO_SELECTOR, GREEN, VIDEO_DURATION_SEC);
  }

  public void verifyLightboxAd() {
    verifyColorAd(lightboxVideo, BLUE, AD_DURATION_SEC);
    logMessage(BLUE, AD_DURATION_SEC);
  }

  public void verifyLightboxVideo() {
    verifyColorAd(lightboxVideo, GREEN, VIDEO_DURATION_SEC);
    logMessage(GREEN, VIDEO_DURATION_SEC);
  }

  private void verifyFeaturedVideoElement(By selector, Color color, int duration) {
    wait.forElementVisible(selector, 30, 1000);
    scrollToPosition(ARTICLE_VIDEO_WRAPPER_SELECTOR);
    fixScrollPositionByNavbar();
    verifyColorAd(articleVideoWrapper, color, 5);
    logMessage(color, duration);
  }

  private void verifyFeaturedVideoElement(String selector, Color color, int duration) {
    verifyFeaturedVideoElement(By.cssSelector(selector), color, duration);
  }

  private void verifyColorAd(WebElement element, Color color, int durationSec) {
    AdsComparison adsComparison = new AdsComparison();
    waitForColorAds(element, color);
    adsComparison.verifyColorAd(element, color, durationSec, driver);
  }


  private void waitForColorAds(WebElement element, Color color) {
    changeImplicitWait(500, TimeUnit.MILLISECONDS);
    try {
      waitFor.until(CommonExpectedConditions
                        .elementToHaveColor(element, color,
                                            AdsComparison.IMAGES_THRESHOLD_PERCENT));
    } finally {
      restoreDefaultImplicitWait();
    }
  }

  private void logMessage(Color color, int duration) {
    PageObjectLogging.log("Video", "Video content had " + color + " during " + duration + " seconds", true);
  }

  public void waitForAdStartsPlaying() {
    wait.forElementVisible(AD_LAYER_SELECTOR);
  }

  public void allowToPlayVideoForSomeTime(Duration duration) {
    try {
      Thread.sleep(duration.toMillis());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void waitForAdFinish(Duration videoDuration) {
    wait.forElementNotVisible(AD_LAYER_SELECTOR, videoDuration);
    wait.forElementVisible(By.cssSelector(".oo-state-screen"));
  }
}
