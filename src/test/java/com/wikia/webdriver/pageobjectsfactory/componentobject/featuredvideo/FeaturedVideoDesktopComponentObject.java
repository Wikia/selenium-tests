package com.wikia.webdriver.pageobjectsfactory.componentobject.featuredvideo;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FeaturedVideoDesktopComponentObject extends WikiBasePageObject {

  private static final String AUTOPLAY_COOKIE = "featuredVideoAutoplay";

  @FindBy(css = ".featured-video")
  private WebElement featuredVideo;

  @FindBy(css = "div[button=\"wikiaSettings\"]")
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

  @FindBy(css = "li.wikia-jw-settings__captions-button")
  private WebElement videoCaptionsButton;

  @FindBy(css = ".wikia-jw-settings__submenu")
  private List<WebElement> videoSettingsSubmenu;

  @FindBy(css = ".featured-video__attribution-username")
  private WebElement attributionLink;

  @FindBy(css = ".featured-video__attribution-avatar")
  private WebElement attributionAvatar;

  public FeaturedVideoDesktopComponentObject setAutoplayCookie(boolean autoplay) {
    driver.manage().addCookie(new Cookie(
        AUTOPLAY_COOKIE,
        autoplay ? "1" : "0",
        String.format(".%s", Configuration.getEnvType().getDomain(driver.getCurrentUrl())),
        null,
        null
    ));

    return this;
  }

  public FeaturedVideoDesktopComponentObject openWikiArticle(String articleName) {
    this.openWikiPage(urlBuilder.getUrlForWikiPage(articleName) + "?noads=1");

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

  public FeaturedVideoDesktopComponentObject clickPlay() {
    wait.forElementClickable(player);
    player.click();

    return this;
  }

  public FeaturedVideoDesktopComponentObject clickPause() {
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

  public FeaturedVideoDesktopComponentObject showControlBar() {
    jsActions.mouseOver(player);
    wait.forElementClickable(controlbar);

    return this;
  }

  public FeaturedVideoDesktopComponentObject openSettingsMenu() {
    showControlBar();
    wait.forElementClickable(settingsMenu).click();

    return this;
  }

  public FeaturedVideoDesktopComponentObject openQualityMenu() {
    wait.forElementClickable(videoQualityButton).click();

    return this;
  }

  public FeaturedVideoDesktopComponentObject openCaptionsMenu() {
    wait.forElementClickable(videoCaptionsButton).click();

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

  public boolean isAttributionLinkVisible() {
    wait.forElementVisible(attributionLink);

    return attributionLink.isDisplayed();
  }

  public boolean isAttributionLinkNotVisible() {
    wait.forElementNotVisible(attributionLink);

    return true;
  }

  public boolean isAttributionAvatarVisible() {
    wait.forElementVisible(attributionAvatar);

    return attributionAvatar.isDisplayed();
  }
}

