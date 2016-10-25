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

  private static final Color GREEN = new Color(4, 253, 6);
  private static final Color BLUE = new Color(4, 0, 254);
  private static final int AD_DURATION_SEC = 30;
  private static final int VIDEO_DURATION_SEC = 30;

  @FindBy(css = "div[id^='ooyalaplayer'] > .innerWrapper")
  private WebElement lightboxVideo;

  public AdsOoyalaObject(WebDriver driver, String page) {
    super(driver, page);
  }

  public void playVideo() {
    wait.forElementVisible(lightboxVideo);
    lightboxVideo.click();
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
      restoreDeaultImplicitWait();
    }
  }
}
