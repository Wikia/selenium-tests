package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HiViUap {
  private By adIframe;
  private By fullScreenButton;
  private By imaIframe;
  private By pauseButton;
  private By replayButton;
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
    this.soundButton = By.cssSelector(slotSelector + ".volume-button");
    this.videoClickArea = By.cssSelector(slotSelector + ".toggle-ui-overlay");
    this.videoElement = By.cssSelector("video");
    this.videoPlayer = By.cssSelector(slotSelector + ".video-player");
    this.videoThumbnail = By.cssSelector(slotSelector + ".videoThumbnail");
  }

  public void waitForVideoStart() {
    iframeRunner.usingIframe(adIframe, () -> wait.forElementNotVisible(videoThumbnail));
  }

  public void waitForVideoEnd() {
    iframeRunner.usingIframe(adIframe, () -> wait.forElementVisible(replayButton, 60));
  }

  private void enableVideoToolbar() {
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

  public void clickFullscreenIcon() {
    enableVideoToolbar();
    wait.forElementClickable(fullScreenButton).click();
  }

  public int getVideoWidth() {
    return iframeRunner.usingIframeGet(imaIframe, () -> driver.findElement(videoElement).getSize().width);
  }
}
