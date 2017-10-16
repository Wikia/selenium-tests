package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.ElementColor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;

public class AdsJWPlayerObject extends AdsBaseObject {

  private static final Color COLOR_PREROLL = new Color(0, 0, 255);
  private static final Color COLOR_MIDROLL = new Color(138, 137, 138);
  private static final Color COLOR_POSTROLL = new Color(240, 71, 168);
  private static final Color COLOR_VIDEO = new Color(21, 255, 12);

  private static final String FEATURED_VIDEO_AD_SELECTOR = "#featured-video__player_ad";
  private static final String FEATURED_VIDEO_SELECTOR = "#featured-video__player";
  private static final By PLAYER_SELECTOR = By.cssSelector(FEATURED_VIDEO_SELECTOR);
  private static final By AD_LAYER_SELECTOR = By.cssSelector(FEATURED_VIDEO_AD_SELECTOR);

  @FindBy(css = FEATURED_VIDEO_SELECTOR)
  private WebElement articleVideoWrapper;

  public AdsJWPlayerObject(WebDriver driver, String page) {
    super(driver, page);
  }

  public void verifyPlayerOnPage() {
    wait.forElementPresent(PLAYER_SELECTOR);
  }

  public void waitForAdStartsPlaying() {
    wait.forElementVisible(AD_LAYER_SELECTOR);
  }

  public void verifyPreroll() {
    verifyFeaturedVideoElement(PLAYER_SELECTOR, COLOR_PREROLL, 20);
  }

  public void verifyMidroll() {
    verifyFeaturedVideoElement(PLAYER_SELECTOR, COLOR_MIDROLL, 10);
  }

  public void verifyPostroll() {
    verifyFeaturedVideoElement(PLAYER_SELECTOR, COLOR_POSTROLL, 10);
  }

  public void verifyFeaturedVideo() {
    verifyFeaturedVideoElement(PLAYER_SELECTOR, COLOR_VIDEO, 10);
  }

  private void verifyFeaturedVideoElement(By selector, Color color, int assertTime) {
    jsActions.scrollToElement(selector);
    verifyColor(articleVideoWrapper, color, assertTime);
    logMessage(color);
  }

  private void verifyColor(WebElement element, Color color, int duration) {
    ElementColor elementColor = new ElementColor(driver);

    elementColor.verifyColor(element, color, duration);
  }

  private void logMessage(Color color) {
    PageObjectLogging.log("Video", "Video content had " + color, true);
  }
}
