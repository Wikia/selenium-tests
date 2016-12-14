package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class VUAP {
  public static final double IMAGE_ASPECT_RATIO = 2.459;
  public static final double VIDEO_ASPECT_RATIO = 1.769;
  private static final int VIDEO_LENGTH = 6000;
  private static final By VIDEO_IFRAME_SELECTOR = By.cssSelector(".video-ima-container iframe");
  private static By videoContainerSelector = By.cssSelector(".video-ima-container.hidden");
  private static By playTriggerButtonSelector = By.id("button");
  private static By UIElementsSelector = By.className("overVideoLayer");
  private final Wait wait;
  private WikiaWebDriver driver;
  private WebElement iframe;
  private WebElement playTriggerButton;

  public VUAP(WikiaWebDriver driver, String platform, String slotName) {
    this.wait = new Wait(driver);
    this.driver = driver;
    setIframe(platform, slotName);
    setTriggerButton(driver);
  }

  private String getAdUnit(String platform, String slotName) {
    return "google_ads_iframe_/5441/wka.life/_project43//article/" + platform + "/" + slotName + "_0";
  }

  private void setIframe(String platform, String slotName) {
    By iframeSelector = By.id(getAdUnit(platform, slotName));
    wait.forElementPresent(iframeSelector);
    iframe = driver.findElement(iframeSelector);
  }

  private void setTriggerButton(WikiaWebDriver driver) {
    runInAdFrame(() -> {
      wait.forElementClickable(playTriggerButtonSelector);
      playTriggerButton = driver.findElement(playTriggerButtonSelector);
    });
  }

  public WebElement getIframe() {
    return iframe;
  }

  public void play() {
    runInAdFrame(() -> wait.forElementClickable(playTriggerButtonSelector).click());
  }

  public void pause() {
    driver.findElement(By.className("overVideoLayer")).click();
  }

  private interface Lambda {
    void run();
  }

  private void runInAdFrame(Lambda f) {
    driver.switchTo().frame(iframe);
    f.run();
    driver.switchTo().defaultContent();
  }

  public void waitForVideoPlayerVisible() {
    wait.forElementVisible(UIElementsSelector);
  }

  public void waitForVideoPlayerHidden() {
    wait.forElementNotVisible(UIElementsSelector);
  }

  public void waitForVideoEnd() { wait.forElementPresent(videoContainerSelector, VIDEO_LENGTH); }

  public void waitForVideoStart() { waitForVideoPlayerVisible(); }

  public Double getCurrentVideoTime() {
    String result;

    driver.switchTo().frame(driver.findElement(VIDEO_IFRAME_SELECTOR));
    result = driver.findElement(By.cssSelector("video")).getAttribute("currentTime");
    driver.switchTo().defaultContent();

    return Double.parseDouble(result);
  }

  public boolean isTimeProgressing(int quartileTime, int midTime) {
    if (quartileTime < midTime){
      return true;
    }
    return false;
  }
}
