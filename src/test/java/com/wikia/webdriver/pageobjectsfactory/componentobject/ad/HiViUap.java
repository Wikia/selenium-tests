package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.elemnt.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HiViUap {
  private By adIframe;
  private By fullScreenButton;
  private By imaIframe;
  private By learnMore;
  private By mobileVideoElement;
  private By pauseButton;
  private By progressBar;
  private By replayButton;
  private By slotSelector;
  private By soundButton;
  private By videoClickArea;
  private By videoElement;
  private By videoPlayer;
  private By videoThumbnail;

  private final Wait wait;
  private final WikiaWebDriver driver;
  private final IframeRunner iframeRunner;

  public HiViUap(WikiaWebDriver driver, String slot) {
    this.wait = new Wait(driver);
    this.iframeRunner = new IframeRunner(driver);
    this.driver = driver;

    final String slotSelector = String.format("#%s ", slot);
    this.adIframe = By.cssSelector(slotSelector + " iframe[id^=\"google_ads\"]");
    this.fullScreenButton = By.cssSelector(slotSelector + ".toggle-fullscreen-button");
    this.imaIframe = By.cssSelector(slotSelector + ".video-player iframe");
    this.pauseButton = By.cssSelector(slotSelector + ".play-pause-button");
    this.replayButton = By.cssSelector(".replay-overlay");
    this.slotSelector = By.id(slot);
    this.soundButton = By.cssSelector(slotSelector + ".volume-button");
    this.videoClickArea = By.cssSelector(slotSelector + ".toggle-ui-overlay");
    this.videoElement = By.cssSelector("video");
    this.videoPlayer = By.cssSelector(slotSelector + ".video-player");
    this.videoThumbnail = By.cssSelector(slotSelector + ".videoThumbnail");
    this.progressBar = By.cssSelector(slotSelector + ".current-time");
    this.learnMore = By.cssSelector(slotSelector + ".learn-more");
    this.mobileVideoElement = By.cssSelector(slotSelector + "video");
  }

  public void waitForVideoStart() {
    iframeRunner.usingIframe(adIframe, () -> wait.forElementNotVisible(videoThumbnail));
  }

  public void waitForVideoEnd() {
    iframeRunner.usingIframe(adIframe, () -> wait.forElementVisible(replayButton, 60));
  }

  public void enableVideoToolbar() {
    WebElement videoPlayerElement = wait.forElementVisible(this.videoPlayer);
    Actions builder = new Actions(driver);
    builder.moveToElement(videoPlayerElement).perform();
  }

  public void clickVideo() {
    waitForVideoStart();
    wait.forElementClickable(videoClickArea).click();
  }

  public void togglePause() {
    enableVideoToolbar();
    wait.forElementClickable(pauseButton).click();
  }

  public void toggleSound() {
    enableVideoToolbar();
    wait.forElementClickable(soundButton).click();
  }

  public double getCurrentTime() {
    return Double.parseDouble(iframeRunner.usingIframeGet(imaIframe,
        () -> wait.forElementPresent(videoElement).getAttribute("currentTime")
    ));
  }

  public double getCurrentTimeMobile() {
    return Double.parseDouble(wait.forElementPresent(mobileVideoElement).getAttribute("currentTime"));
  }

  public void clickFullscreenIcon() {
    enableVideoToolbar();
    wait.forElementClickable(fullScreenButton).click();
  }

  public int getVideoWidth() {
    return iframeRunner.usingIframeGet(imaIframe, () -> driver.findElement(videoElement).getSize().width);
  }

  public int getVideoWidthMobile() {
    return driver.findElement(mobileVideoElement).getSize().width;
  }

  public boolean isMobileVideoElementVisible() {
    return driver.findElement(mobileVideoElement).isDisplayed();
  }

  public boolean isVideoElementVisible() {
    return iframeRunner.usingIframeGet(imaIframe, () -> driver.findElement(videoElement).isDisplayed());
  }

  public double getProgressBarWidth() {
    return driver.findElement(progressBar).getSize().getWidth();
  }

  public void clickReplayButton() {
    iframeRunner.usingIframe(adIframe, () -> wait.forElementClickable(replayButton).click());
  }

  public void clickAd() {
    iframeRunner.usingIframe(adIframe, () -> wait.forElementClickable(By.id("adContainer")).click());
  }

  public WebElement waitForAdLoaded() {
    return driver.findElement(slotSelector);
  }

  public void waitForAdLoaded() {
    iframeRunner.usingIframe(adIframe, () -> {
      JavascriptActions jsActions = new JavascriptActions(driver);
      jsActions.waitForJavaScriptTruthy("document.readyState === 'complete'");
      wait.forElementVisible(By.id("adContainer"));
    });
  }

  public void clickLearnMore() {
    wait.forElementClickable(learnMore).click();
  }
}
