package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.ElementColor;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.SoundMonitor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.time.Duration;

public class AdsJWPlayerObject {
  protected final Actions builder;
  protected final WikiaWebDriver driver;
  protected final JavascriptActions jsActions;
  protected final Wait wait;

  private static final Color COLOR_PREROLL = new Color(0, 1, 253);
  private static final Color COLOR_MIDROLL = new Color(137, 137, 137);
  private static final Color COLOR_POSTROLL = new Color(253, 93, 167);
  private static final Color COLOR_VIDEO = new Color(0, 255, 13);

  public static final String VIDEO_AD_SELECTOR = ".jwplayer .jw-plugin-googima";
  public static final String VIDEO_MOVIE_SELECTOR = ".jwplayer .jw-media video[src]";
  public static final String VIDEO_PLAYER_SELECTOR = ".jwplayer";

  public static final By AD_SELECTOR = By.cssSelector(VIDEO_AD_SELECTOR);
  public static final By MOVIE_SELECTOR = By.cssSelector(VIDEO_MOVIE_SELECTOR);
  public static final By PLAYER_SELECTOR = By.cssSelector(VIDEO_PLAYER_SELECTOR);
  public static final By VOLUME_BUTTON_SELECTOR = By.cssSelector(".jwplayer div.jw-icon.jw-icon-volume");

  public AdsJWPlayerObject(WikiaWebDriver driver) {
    this.builder = new Actions(driver);
    this.driver = driver;
    this.jsActions = new JavascriptActions(driver);
    this.wait = new Wait(driver);
  }

  public void verifyPlayerOnPage() {
    wait.forElementPresent(PLAYER_SELECTOR);
  }

  public void waitForAdPlaying() {
    Log.log("Info", "Waiting for video ad playing", true);
    wait.forElementVisible(AD_SELECTOR, 30);
  }

  public void waitForMoviePlaying() {
    Log.log("Info", "Waiting for video movie playing", true);
    wait.forElementVisible(MOVIE_SELECTOR, 15);
  }

  public void waitForAdFinish(Duration videoDuration) {
    Log.log("Info", "Waiting for ad finish", true);
    wait.forElementNotVisible(AD_SELECTOR, videoDuration);
  }

  public void verifyAllAdPositions() {
    verifyPlayerOnPage();
    verifyPreroll();
    verifyFeaturedVideo();
    verifyMidroll();
    verifyFeaturedVideo();
    verifyPostroll();
  }

  public void verifyPreroll() {
    waitForAdPlaying();
    Log.log("Info", "Waiting for blue preroll", true);
    verifyFeaturedVideoElementColor(PLAYER_SELECTOR, COLOR_PREROLL);
    waitForAdFinish(Duration.ofSeconds(30));
  }

  public void verifyMidroll() {
    waitForAdPlaying();
    Log.log("Info", "Waiting for grey midroll", true);
    verifyFeaturedVideoElementColor(PLAYER_SELECTOR, COLOR_MIDROLL);
    waitForAdFinish(Duration.ofSeconds(15));
  }

  public void verifyPostroll() {
    waitForAdPlaying();
    Log.log("Info", "Waiting for pink postroll", true);
    verifyFeaturedVideoElementColor(PLAYER_SELECTOR, COLOR_POSTROLL);
    waitForAdFinish(Duration.ofSeconds(15));
  }

  public void verifyFeaturedVideo() {
    waitForMoviePlaying();
    Log.log("Info", "Waiting for green movie", true);
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
      Log.log("Error", e.getMessage(), false);
    }
  }

  public void clickOnPlayer() {
    WebElement playButton = driver.findElement(PLAYER_SELECTOR);
    playButton.click();
  }

  private void hoverPlayerToActivateUI() {
    builder.moveToElement(driver.findElement(PLAYER_SELECTOR)).pause(500).perform();
  }
}
