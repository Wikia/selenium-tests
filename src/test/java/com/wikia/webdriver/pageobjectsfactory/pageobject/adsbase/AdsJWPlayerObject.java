package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.ElementColor;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.SoundMonitor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.time.Duration;

public class AdsJWPlayerObject extends AdsBaseObject {

  private static final Color COLOR_PREROLL = new Color(0, 1, 253);
  private static final Color COLOR_MIDROLL = new Color(137, 137, 137);
  private static final Color COLOR_POSTROLL = new Color(253, 93, 167);
  private static final Color COLOR_VIDEO = new Color(0, 255, 13);

  private static final String FEATURED_VIDEO_AD_SELECTOR = ".jwplayer .jw-plugin-googima";
  private static final String FEATURED_VIDEO_MOVIE_SELECTOR = ".jwplayer .jw-media video[src]";
  private static final String FEATURED_VIDEO_PLAYER_SELECTOR = ".jwplayer";

  private static final By AD_SELECTOR = By.cssSelector(FEATURED_VIDEO_AD_SELECTOR);
  private static final By MOVIE_SELECTOR = By.cssSelector(FEATURED_VIDEO_MOVIE_SELECTOR);
  private static final By PLAYER_SELECTOR = By.cssSelector(FEATURED_VIDEO_PLAYER_SELECTOR);
  private static final By VOLUME_BUTTON_SELECTOR = By.cssSelector(".jwplayer div.jw-icon.jw-icon-volume");

  public AdsJWPlayerObject(WebDriver driver, String page) {
    super(driver, page);
  }

  public void verifyPlayerOnPage() {
    wait.forElementPresent(PLAYER_SELECTOR);
  }

  public void waitForAdPlaying() {
    wait.forElementVisible(AD_SELECTOR, 30);
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

    elementColor.verifyMostFrequentColor(element, color, 15);
  }

  public Boolean wasSoundHeard() {
    return SoundMonitor.wasSoundHeardOnPage(jsActions);
  }

  public void clickVolumeButton() {
    hoverPlayerToActivateUI();
    driver.findElement(VOLUME_BUTTON_SELECTOR).click();
  }

  public void allowToPlayVideoForSomeTime(Duration duration) {
    try {
      Thread.sleep(duration.toMillis());
    } catch (InterruptedException e) {
      PageObjectLogging.log("Error", e.getMessage(), false);
    }
  }

  public void waitForAdFinish(Duration videoDuration) {
    wait.forElementNotVisible(AD_SELECTOR, videoDuration);
    wait.forElementVisible(MOVIE_SELECTOR);
  }

  public void scrollToPlayer() {
    scrollTo(driver.findElement(PLAYER_SELECTOR));
  }

  private void hoverPlayerToActivateUI() {
    builder.moveToElement(driver.findElement(PLAYER_SELECTOR)).pause(500).perform();
  }

  private void waitForMoviePlaying() {
    wait.forElementVisible(MOVIE_SELECTOR, 30);
  }
}
