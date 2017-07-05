package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsComparison;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class AdsOoyalaObject extends AdsBaseObject {

  private static final Color GREEN = new Color(4, 253, 6);
  private static final Color BLUE = new Color(4, 0, 254);
  private static final int AD_DURATION_SEC = 30;
  private static final int VIDEO_DURATION_SEC = 30;
  private static final String PATTERN_DFP_PREROLL =
      "^https://pubads.g.doubleclick\\.net\\/gampad\\/ads(.*)pos%3DFEATURED_VIDEO(.*)src%3Dpremium(.*)";

  private static final String ARTICLE_VIDEO_CLASS = "ooyala-article-video";
  private static final String ARTICLE_VIDEO_PREROLL_SELECTOR = ".ooyala-article-video iframe[src*=imasdk]";
  private static final String ARTICLE_VIDEO_WRAPPER_SELECTOR = ".article-featured-video__placeholder, #ooyala-article-video > .innerWrapper";
  private static final String ARTICLE_VIDEO_CLICK_AREA_SELECTOR = ".article-featured-video__placeholder, #ooyala-article-video .oo-state-screen-selectable";

  @FindBy(css = "div[id^='ooyalaplayer'] > .innerWrapper")
  private WebElement lightboxVideo;
  @FindBy(css = ARTICLE_VIDEO_WRAPPER_SELECTOR)
  private WebElement articleVideoWrapper;
  @FindBy(className = ARTICLE_VIDEO_CLASS)
  private WebElement articleVideo;
  @FindBy(css = ARTICLE_VIDEO_CLICK_AREA_SELECTOR)
  private WebElement articleVideoClickArea;

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

  public void verifyPlayerOnPage() {
    wait.forElementPresent(By.className(ARTICLE_VIDEO_CLASS));
  }

  public void verifyPreroll() {
    wait.forElementPresent(By.cssSelector(ARTICLE_VIDEO_PREROLL_SELECTOR));
  }

  public void verifyArticleAd() {
    scrollToPosition(ARTICLE_VIDEO_WRAPPER_SELECTOR);
    verifyColorAd(articleVideoWrapper, BLUE, AD_DURATION_SEC);
    PageObjectLogging.log("ArticleAd",
                          "Article had " + BLUE + " during " + AD_DURATION_SEC
                          + " seconds", true);
  }

  public void verifyArticleVideo() {
    scrollToPosition(ARTICLE_VIDEO_WRAPPER_SELECTOR);
    verifyColorAd(articleVideoWrapper, GREEN, VIDEO_DURATION_SEC);
    PageObjectLogging.log("ArticleAd",
                          "Article had " + GREEN + " during " + VIDEO_DURATION_SEC
                          + " seconds", true);
  }

  public void verifyLightboxAd() {
    verifyColorAd(lightboxVideo, BLUE, AD_DURATION_SEC);
    PageObjectLogging.log("LightboxAd",
                          "Lightbox had " + BLUE + " during " + AD_DURATION_SEC
                          + " seconds", true);
  }

  public void verifyLightboxVideo() {
    verifyColorAd(lightboxVideo, GREEN, VIDEO_DURATION_SEC);
    PageObjectLogging.log("LightboxVideo",
                          "Lightbox had " + GREEN + " during " + VIDEO_DURATION_SEC
                          + " seconds", true);
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
}
