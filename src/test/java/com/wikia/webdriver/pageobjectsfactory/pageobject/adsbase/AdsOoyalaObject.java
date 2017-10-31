package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.ElementColor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;

public class AdsOoyalaObject extends AdsBaseObject {

  private static final Color GREEN_OOYALA_3 = new Color(20, 255, 13);
  private static final Color BLUE = new Color(0, 1, 253);
  private static final int AD_DURATION_SEC = 30;
  private static final int VIDEO_DURATION_SEC = 5;

  private static final String ARTICLE_VIDEO_PREROLL_SELECTOR = ".ooyala-article-video iframe[src*=imasdk]";
  private static final String ARTICLE_VIDEO_MOBILE_SELECTOR = ".ooyala-article-video > .innerWrapper > video";
  private static final String ARTICLE_VIDEO_WRAPPER_SELECTOR = ".article-featured-video__placeholder, #ooyala-article-video > .innerWrapper";
  private static final String ARTICLE_VIDEO_PLAY_BUTTON_SELECTOR = ".article-featured-video__play-circle";
  private static final By VIDEO_PLAY_BUTTON = By.cssSelector(ARTICLE_VIDEO_PLAY_BUTTON_SELECTOR);

  @FindBy(css = "div[id^='ooyalaplayer'] > .innerWrapper")
  private WebElement lightboxVideo;

  @FindBy(css = ARTICLE_VIDEO_WRAPPER_SELECTOR)
  private WebElement articleVideoWrapper;

  public AdsOoyalaObject(WebDriver driver, String page) {
    super(driver, page);
  }

  public void playFeaturedVideo() {
    wait.forElementVisible(VIDEO_PLAY_BUTTON).click();
  }

  public void verifyPlayerOnPage() {
    wait.forElementPresent(By.cssSelector(ARTICLE_VIDEO_WRAPPER_SELECTOR));
  }

  public void verifyArticleAd() {
    wait.forElementVisible(By.cssSelector(ARTICLE_VIDEO_PREROLL_SELECTOR), 30);
    verifyFeaturedVideoElement(BLUE, AD_DURATION_SEC);
  }

  public void verifyMobileArticleVideo() {
    wait.forElementVisible(By.cssSelector(ARTICLE_VIDEO_MOBILE_SELECTOR), 30);
    verifyFeaturedVideoElement(GREEN_OOYALA_3, VIDEO_DURATION_SEC);
  }

  public void verifyLightboxAd() {
    wait.forElementVisible(lightboxVideo);
    verifyColorAd(lightboxVideo, BLUE);
    logMessage(BLUE, AD_DURATION_SEC);
  }

  public void verifyLightboxVideo() {
    verifyColorAd(lightboxVideo, GREEN_OOYALA_3);
    logMessage(GREEN_OOYALA_3, VIDEO_DURATION_SEC);
  }

  private void verifyFeaturedVideoElement(Color color, int duration) {
    verifyColorAd(articleVideoWrapper, color);
    logMessage(color, duration);
  }

  private void verifyColorAd(WebElement element, Color color) {
    ElementColor ooyala = new ElementColor(driver);

    ooyala.verifyMostFrequentColor(element, color);
  }

  private void logMessage(Color color, int duration) {
    PageObjectLogging.log("Video", "Video content had " + color + " during " + duration + " seconds", true);
  }
}
