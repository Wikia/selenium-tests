package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.ElementColor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;

public class AdsJWPlayerObject extends AdsBaseObject {

  private static final Color COLOR_PREROLL = new Color(0, 0, 255);
  private static final Color COLOR_MIDROLL = new Color(138, 137, 138);
  private static final Color COLOR_POSTROLL = new Color(240, 71, 168);
  private static final Color COLOR_VIDEO = new Color(21, 255, 12);

  private static final String FEATURED_VIDEO_AD_SELECTOR = "#featured-video__player_ad";
  private static final String FEATURED_VIDEO_MOVIE_SELECTOR = "#featured-video__player .jw-media video[src]";
  private static final String FEATURED_VIDEO_PLAYER_SELECTOR = "#featured-video__player";

  private static final By AD_SELECTOR = By.cssSelector(FEATURED_VIDEO_AD_SELECTOR);
  private static final By MOVIE_SELECTOR = By.cssSelector(FEATURED_VIDEO_MOVIE_SELECTOR);
  private static final By PLAYER_SELECTOR = By.cssSelector(FEATURED_VIDEO_PLAYER_SELECTOR);

  public AdsJWPlayerObject(WebDriver driver, String page) {
    super(driver, page);
  }

  public void verifyPlayerOnPage() {
    wait.forElementPresent(PLAYER_SELECTOR);
  }

  public void waitForAdPlaying() {
    wait.forElementVisible(AD_SELECTOR, 30);
  }

  public void waitForMoviePlaying() {
    wait.forElementVisible(MOVIE_SELECTOR, 30);
  }

  public void verifyPreroll() {
    waitForAdPlaying();
    verifyFeaturedVideoElementColor(PLAYER_SELECTOR, COLOR_PREROLL);
  }

  public void verifyMidroll() {
    waitForAdPlaying();
    verifyFeaturedVideoElementColor(PLAYER_SELECTOR, COLOR_MIDROLL);
  }

  public void verifyPostroll() {
    waitForAdPlaying();
    verifyFeaturedVideoElementColor(PLAYER_SELECTOR, COLOR_POSTROLL);
  }

  public void verifyFeaturedVideo() {
    waitForMoviePlaying();
    verifyFeaturedVideoElementColor(PLAYER_SELECTOR, COLOR_VIDEO);
  }

  private void verifyFeaturedVideoElementColor(By selector, Color color) {
    WebElement articleVideoWrapper = driver.findElement(selector);

    jsActions.scrollToElement(articleVideoWrapper);
    verifyColor(articleVideoWrapper, color);
  }

  private void verifyColor(WebElement element, Color color) {
    ElementColor elementColor = new ElementColor(driver);

    elementColor.verifyMostFrequentColor(element, color);
  }
}
