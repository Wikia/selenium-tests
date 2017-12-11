package com.wikia.webdriver.pageobjectsfactory.componentobject.featuredvideo;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FeaturedVideoMobileComponentObject extends WikiBasePageObject{

  private static final String AUTOPLAY_COOKIE = "featuredVideoAutoplay";

  @FindBy(css = ".jw-video")
  private WebElement featuredVideo;

  @FindBy(css = ".wikia-jw-settings-button")
  private WebElement settingsMenu;

  @FindBy(css = ".jw-title-primary")
  private WebElement primaryTitle;

  @FindBy(css = ".jw-flag-touch")
  private WebElement player;

  @FindBy(css = ".jw-controlbar")
  private WebElement controlbar;

  @FindBy(css = ".jw-display-icon-display")
  private WebElement playButton;

  @FindBy(css = ".jw-display-icon-display")
  private WebElement pauseButton;

  @FindBy(css = ".jw-state-playing")
  private WebElement playerStatePlaying;

  @FindBy(css = ".jw-state-paused")
  private WebElement playerStatePaused;

  @FindBy(css = ".wikia-jw-settings-button")
  private WebElement videoQualityButton;

  @FindBy(css = "#featured-video__player-videoAutoplayToggle")
  private WebElement autoplayToggle;

  @FindBy(css = ".jw-icon-tooltip.jw-icon-volume.jw-off")
  private WebElement volumeMuted;

  @FindBy(css = ".wikia-jw-settings__list .wikia-jw-settings__captions-button")
  private WebElement videoCaptionsButton;

  @FindBy(css = ".wikia-jw-settings__submenu")
  private List<WebElement> videoSettingsSubmenu;

  public FeaturedVideoMobileComponentObject setAutoplayCookie(boolean autoplay) {
    driver.manage().addCookie(new Cookie(
        AUTOPLAY_COOKIE,
        autoplay ? "1" : "0",
        String.format(".%s", Configuration.getEnvType().getWikiaDomain()),
        null,
        null
    ));

    return this;
  }

  public FeaturedVideoMobileComponentObject openWikiArticle(String articleName) {
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

  public FeaturedVideoMobileComponentObject activatePlayerOptions() {
    wait.forElementClickable(player);
    player.click();

    return this;
  }

  public FeaturedVideoMobileComponentObject clickPlay() {
    wait.forElementClickable(playButton);
    playButton.click();

    return this;
  }

  public FeaturedVideoMobileComponentObject clickPause() {
    wait.forElementClickable(pauseButton);
    pauseButton.click();

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


  public boolean isAutoplayOn() {

    return "true".equals(autoplayToggle.getAttribute("checked"));
  }

  public FeaturedVideoMobileComponentObject showControlBar() {
    wait.forElementClickable(player);

    return this;
  }

  public FeaturedVideoMobileComponentObject openSettingsMenu() {
    showControlBar();
    wait.forElementClickable(settingsMenu)
        .click();

    return this;
  }

  public FeaturedVideoMobileComponentObject openQualityMenu() {
    wait.forElementClickable(videoQualityButton)
        .click();

    return this;
  }

  public FeaturedVideoMobileComponentObject openCaptionsMenu() {
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
