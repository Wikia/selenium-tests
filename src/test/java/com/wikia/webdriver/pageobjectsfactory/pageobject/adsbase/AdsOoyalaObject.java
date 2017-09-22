package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.OoyalaPrerollAd;

import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.SoundMonitor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.time.Duration;

public class AdsOoyalaObject extends AdsBaseObject {

  private static final Color GREEN_OOYALA_4 = new Color(0, 255, 13);
  private static final Color GREEN_OOYALA_3 = new Color(20, 255, 13);
  private static final Color BLUE = new Color(0, 1, 253);
  private static final int AD_DURATION_SEC = 30;
  private static final int VIDEO_DURATION_SEC = 30;

  private static final String ARTICLE_VIDEO_PREROLL_SELECTOR = ".ooyala-article-video iframe[src*=imasdk]";
  private static final String ARTICLE_VIDEO_SELECTOR = ".bitdash-vc";
  private static final String ARTICLE_VIDEO_MOBILE_SELECTOR = ".ooyala-article-video > .innerWrapper > video";
  private static final String ARTICLE_VIDEO_WRAPPER_SELECTOR = ".article-featured-video__placeholder, #ooyala-article-video > .innerWrapper";
  private static final By PLAYER_SELECTOR = By.id("ooyala-article-video");
  private static final By AD_LAYER_SELECTOR = By.cssSelector(ARTICLE_VIDEO_PREROLL_SELECTOR);

  @FindBy(css = "div[id^='ooyalaplayer'] > .innerWrapper")
  private WebElement lightboxVideo;

  @FindBy(css = ARTICLE_VIDEO_WRAPPER_SELECTOR)
  private WebElement articleVideoWrapper;

  @FindBy(css = ".oo-volume.oo-control-bar-item")
  private WebElement volumeControlButton;

  public AdsOoyalaObject(WebDriver driver, String page) {
    super(driver, page);
  }

  public void playLightboxVideo() {
    wait.forElementVisible(lightboxVideo);
    lightboxVideo.click();
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
    wait.forElementPresent(By.cssSelector(ARTICLE_VIDEO_WRAPPER_SELECTOR));
  }

  public Boolean wasSoundHeard() {
    return SoundMonitor.wasSoundHeardOnPage(jsActions);
  }

  public void verifyArticleAd() {
    verifyFeaturedVideoElement(ARTICLE_VIDEO_PREROLL_SELECTOR, BLUE, AD_DURATION_SEC);
  }

  public void verifyArticleVideo() {
    verifyFeaturedVideoElement(ARTICLE_VIDEO_SELECTOR, GREEN_OOYALA_4, VIDEO_DURATION_SEC);
  }

  public void verifyMobileArticleVideo() {
    verifyFeaturedVideoElement(ARTICLE_VIDEO_MOBILE_SELECTOR, GREEN_OOYALA_3, VIDEO_DURATION_SEC);
  }

  public void verifyLightboxAd() {
    verifyColorAd(lightboxVideo, BLUE, AD_DURATION_SEC);
    logMessage(BLUE, AD_DURATION_SEC);
  }

  public void verifyLightboxVideo() {
    verifyColorAd(lightboxVideo, GREEN_OOYALA_3, VIDEO_DURATION_SEC);
    logMessage(GREEN_OOYALA_3, VIDEO_DURATION_SEC);
  }

  private void verifyFeaturedVideoElement(By selector, Color color, int duration) {
    wait.forElementVisible(selector, 30, 1000);
    scrollToPosition(ARTICLE_VIDEO_WRAPPER_SELECTOR);
    fixScrollPositionByNavbar();
    verifyColorAd(articleVideoWrapper, color, 5);
    logMessage(color, duration);
  }

  private void verifyColorAd(WebElement element, Color color, int duration) {
    OoyalaPrerollAd ooyala = new OoyalaPrerollAd(driver);

    ooyala.verifyColorAd(element, color, duration);
  }
  
  private void verifyFeaturedVideoElement(String selector, Color color, int duration) {
    verifyFeaturedVideoElement(By.cssSelector(selector), color, duration);
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
      PageObjectLogging.log("Error", e.getMessage(), false);
    }
  }

  public void waitForAdFinish(Duration videoDuration) {
    wait.forElementNotVisible(AD_LAYER_SELECTOR, videoDuration);
    wait.forElementVisible(By.cssSelector(".oo-state-screen"));
  }
}
