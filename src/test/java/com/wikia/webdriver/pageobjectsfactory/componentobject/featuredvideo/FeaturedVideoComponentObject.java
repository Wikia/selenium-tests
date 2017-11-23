package com.wikia.webdriver.pageobjectsfactory.componentobject.featuredvideo;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FeaturedVideoComponentObject extends WikiBasePageObject {

  private static final String AUTOPLAY_COOKIE = "featuredVideoAutoplay";

  @FindBy(css = ".featured-video")
  private WebElement featuredVideo;

  @FindBy(css = ".wikia-jw-settings-button")
  private WebElement settingsMenu;

  @FindBy(css = ".jw-title-primary")
  private WebElement primaryTitle;

  @FindBy(css = ".jw-title-secondary")
  private WebElement secondaryTitle;

  @FindBy(css = "#featured-video__player")
  private WebElement player;

  @FindBy(css = ".jw-controlbar")
  private WebElement controlbar;

  @FindBy(css = ".jw-svg-icon-play")
  private WebElement controlbarPlayIcon;

  @FindBy(css = ".jw-svg-icon-pause")
  private WebElement controlbarPauseIcon;

  @FindBy(css = ".jw-state-playing")
  private WebElement playerStatePlaying;

  @FindBy(css = ".jw-state-paused")
  private WebElement playerStatePaused;

  @FindBy(css = ".video-feedback")
  private WebElement videoFeedback;

  @FindBy(css = "#featured-video__player-videoAutoplayToggle")
  private WebElement autoplayToggle;

  @FindBy(css = ".jw-icon-tooltip.jw-icon-volume.jw-off")
  private WebElement volumeMuted;

  @FindBy(css = ".wikia-jw-settings__quality-button")
  private WebElement videoQualityButton;

  @FindBy(css = ".wikia-jw-settings__list .wikia-jw-settings__captions-button")
  private WebElement videoCaptionsButton;

  @FindBy(css = ".wikia-jw-settings__submenu")
  private List<WebElement> videoSettingsSubmenu;

  public FeaturedVideoComponentObject setAutoplayCookie(boolean autoplay) {
    driver.manage().addCookie(new Cookie(
        AUTOPLAY_COOKIE,
        autoplay ? "1" : "0",
        String.format(".%s", Configuration.getEnvType().getWikiaDomain()),
        null,
        null
    ));

    return this;
  }

  public FeaturedVideoComponentObject openWikiArticle(String articleName) {
    this.openWikiPage(getWikiUrl() + articleName + "?noads=1");

    return this;
  }

  public boolean isFeaturedVideoDisplayed() {
    wait.forElementVisible(featuredVideo);

    return featuredVideo.isDisplayed();
  }

  public String getTitle() {
    wait.forElementVisible(primaryTitle);

    return primaryTitle.getText();
  }

  public String getSubtitle() {
    wait.forElementVisible(secondaryTitle);

    return secondaryTitle.getText();
  }

  public FeaturedVideoComponentObject clickPlay() {
    wait.forElementClickable(player);
    player.click();

    return this;
  }

  public FeaturedVideoComponentObject clickPause() {
    wait.forElementClickable(player);
    player.click();

    return this;
  }

  public boolean isVideoPlaying() {
    wait.forElementVisible(playerStatePlaying);

    return playerStatePlaying.isDisplayed();
  }

  public boolean isVideoPaused() {
    wait.forElementVisible(playerStatePaused);

    return playerStatePaused.isDisplayed();
  }


  public boolean isVideoFeedbackDisplayed() {
    wait.forElementVisible(videoFeedback);

    return videoFeedback.isDisplayed();
  }

  public boolean isVideoFeedbackNotDisplayed() {
    wait.forElementNotVisible(videoFeedback);

    return videoFeedback.isDisplayed();
  }

  public boolean isAutoplayOn() {
    return "true".equals(autoplayToggle.getAttribute("checked"));
  }

  public FeaturedVideoComponentObject showControlBar() {
    jsActions.mouseOver(player);
    wait.forElementClickable(controlbar);

    return this;
  }

  public FeaturedVideoComponentObject openSettingsMenu() {
    showControlBar();
    wait.forElementVisible(settingsMenu);
    scrollTo(settingsMenu);
    wait.forElementClickable(settingsMenu)
        .click();

    return this;
  }

  public FeaturedVideoComponentObject openQualityMenu() {
    wait.forElementVisible(videoQualityButton);
    scrollTo(videoQualityButton);
    wait.forElementClickable(videoQualityButton)
        .click();

    return this;
  }

  public FeaturedVideoComponentObject openCaptionsMenu() {
    wait.forElementVisible(videoCaptionsButton);
    scrollTo(videoCaptionsButton);
    wait.forElementClickable(videoCaptionsButton)
        .click();

    return this;
  }

  public boolean isVolumeMuted() {
    wait.forElementClickable(volumeMuted);
    return volumeMuted.isEnabled();
  }

  public boolean isQualityAvailable() {

    return wait.forTextInElement(videoSettingsSubmenu, 0, "Auto");

  }

  public boolean areCaptionsAvailable() {
    By last = By.xpath("//*[@data-track='0']");
    return wait.forTextInElement(last, "No captions");

  }

}

