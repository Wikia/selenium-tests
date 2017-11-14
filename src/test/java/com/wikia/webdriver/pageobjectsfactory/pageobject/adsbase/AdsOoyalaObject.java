package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.ElementColor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;

public class AdsOoyalaObject extends AdsBaseObject {

  private static final Color GREEN = new Color(20, 255, 13);
  private static final Color BLUE = new Color(0, 1, 253);
  private static final int AD_DURATION_SEC = 30;
  private static final int VIDEO_DURATION_SEC = 5;

  @FindBy(css = "div[id^='ooyalaplayer'] > .innerWrapper")
  private WebElement lightboxVideo;

  public AdsOoyalaObject(WebDriver driver, String page) {
    super(driver, page);
  }

  public void verifyLightboxAd() {
    wait.forElementVisible(lightboxVideo);
    verifyColorAd(lightboxVideo, BLUE);
    logMessage(BLUE, AD_DURATION_SEC);
  }

  public void verifyLightboxVideo() {
    verifyColorAd(lightboxVideo, GREEN);
    logMessage(GREEN, VIDEO_DURATION_SEC);
  }

  private void verifyColorAd(WebElement element, Color color) {
    ElementColor ooyala = new ElementColor(driver);

    ooyala.verifyMostFrequentColor(element, color);
  }

  private void logMessage(Color color, int duration) {
    PageObjectLogging.log("Video", "Video content had " + color + " during " + duration + " seconds", true);
  }
}
