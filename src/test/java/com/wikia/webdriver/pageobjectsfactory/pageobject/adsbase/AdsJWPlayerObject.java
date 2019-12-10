package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.ElementColor;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.SoundMonitor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.time.Duration;

public class AdsJWPlayerObject extends BasePageObject {

  public static final String VIDEO_AD_SELECTOR = ".jwplayer .jw-plugin-googima";
  public static final String VIDEO_MOVIE_SELECTOR = ".jwplayer .jw-media video[src]";
  public static final String VIDEO_PLAYER_SELECTOR = ".jwplayer";
  public static final By AD_SELECTOR = By.cssSelector(VIDEO_AD_SELECTOR);
  public static final By MOVIE_SELECTOR = By.cssSelector(VIDEO_MOVIE_SELECTOR);
  public static final By PLAYER_SELECTOR = By.cssSelector(VIDEO_PLAYER_SELECTOR);
  private static final Color COLOR_PREROLL = new Color(0, 1, 253);
  private static final Color COLOR_MIDROLL = new Color(137, 137, 137);
  private static final Color COLOR_POSTROLL = new Color(253, 93, 167);
  private static final Color COLOR_VIDEO = new Color(0, 255, 13);
  protected final Wait wait;
  @FindBy(css = ".jw-media")
  private WebElement playButton;

  @FindBy(css = ".jwplayer div.jw-icon.jw-icon-volume")
  private WebElement volumeButton;

  public AdsJWPlayerObject() {
    this.wait = new Wait(driver);
  }

  public void verifyPlayerOnPage() {
    wait.forElementPresent(PLAYER_SELECTOR);
  }

  public void waitForAdPlaying() {
    Log.log("Info", "Waiting for video ad playing", true);
    wait.forElementVisible(AD_SELECTOR, 45);
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

  public void clickOnPlayer() {
    builder.moveToElement(playButton)
        .pause(1000)
        .moveToElement(playButton, 150, 350)
        .click()
        .perform();
  }
}
