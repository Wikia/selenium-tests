package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsComparison;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class AdsOoyalaObject extends AdsBaseObject {

  @FindBy(css = "div[id^='ooyalaplayer'] > .innerWrapper")
  private WebElement lightboxVideo;

  public AdsOoyalaObject(WebDriver driver, String page) {
    super(driver, page);
  }

  public void playVideo() {
    wait.forElementVisible(lightboxVideo);
    lightboxVideo.click();
  }

  public void verifyLightboxAd(Color lightboxAdColor, int adDurationSec) {
    verifyColorAd(lightboxVideo, lightboxAdColor, adDurationSec);
    PageObjectLogging.log("LightboxAd",
                          "Lightbox had " + lightboxAdColor + " during " + adDurationSec
                          + " seconds", true);
  }

  public void verifyLightboxVideo(Color lightboxVideoColor, int videoDurationSec) {
    verifyColorAd(lightboxVideo, lightboxVideoColor, videoDurationSec);
    PageObjectLogging.log("LightboxVideo",
                          "Lightbox had " + lightboxVideoColor + " during " + videoDurationSec
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
      restoreDeaultImplicitWait();
    }
  }
}
