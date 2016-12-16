package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.contentpatterns.AdsContent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class VideoFanTakeover {
  private static final int VIDEO_LENGTH = 6000;
  private static final String VIDEO_IFRAME_SELECTOR = "#%s .video-ima-container iframe";
  private static final String MOBILE_VIDEO_SELECTOR = "#%s .video-ima-container video";
  private static final String VIDEO_CONTAINER_SELECTOR = "#%s .video-ima-container.hidden";
  private static final String UI_ELEMENT_SELECTOR = "#%s .overVideoLayer";
  private static final String FANDOM_URL = "http://www.wikia.com/fandom";
  private static final int PERCENT = 28;
  private static By playTriggerButtonSelector = By.id("button");
  private static By closeVideoButton = By.className("close-ad");
  private final Wait wait;
  private WikiaWebDriver driver;
  private WebElement iframe;
  private WebElement playTriggerButton;

  public VideoFanTakeover(WikiaWebDriver driver, String iframeId) {
    this.wait = new Wait(driver);
    this.driver = driver;
    setIframe(iframeId);
    setTriggerButton(driver);
  }

  private void setIframe(String iframeId) {
    By iframeSelector = By.id(iframeId);
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

  public void waitForVideoPlayerVisible(String slotName) {
    wait.forElementVisible(By.cssSelector(String.format(UI_ELEMENT_SELECTOR, slotName)));
  }

  public void waitForVideoPlayerHidden(String slotName) {
    wait.forElementNotVisible(By.cssSelector(String.format(UI_ELEMENT_SELECTOR, slotName)));
  }

  public void waitforAdToLoad() {
    runInAdFrame(() -> wait.forElementClickable(playTriggerButtonSelector));
  }

  public void waitForVideoEnd(String slotName) {
    wait.forElementPresent(By.cssSelector(String.format(VIDEO_CONTAINER_SELECTOR, slotName)), VIDEO_LENGTH);
  }

  public void clickOnAdImage(){
    runInAdFrame(() -> {
    Actions action = new Actions(driver);
    action.moveToElement(wait.forElementClickable(playTriggerButtonSelector), -20, -20).click().build().perform();
    });
  }

  public void waitForVideoStart(String slotName) { waitForVideoPlayerVisible(slotName); }

  public void clickOnVideoCloseButon() { wait.forElementVisible(closeVideoButton).click(); }

  public Double getCurrentVideoTimeOnDesktop(String slotName) {
    String result;

    driver.switchTo().frame(driver.findElement(By.cssSelector(String.format(VIDEO_IFRAME_SELECTOR, slotName))));
    result = driver.findElement(By.cssSelector("video")).getAttribute("currentTime");
    driver.switchTo().defaultContent();

    return Double.parseDouble(result);
  }

  public Double getCurrentVideoTimeOnMobile(String slotName) {
    String result;

    result = driver.findElement(By.cssSelector(String.format(MOBILE_VIDEO_SELECTOR, slotName))).getAttribute("currentTime");
    return Double.parseDouble(result);
  }

  public double getAdSlotHigh(String slotName) {
    return driver.findElement(By.cssSelector(AdsContent.getSlotSelector(slotName))).getSize().getHeight();
  }

  public double getAdVideoHigh(String slotName) {
    return driver.findElement(By.cssSelector(String.format(UI_ELEMENT_SELECTOR , slotName))).getSize().getHeight();
  }

  public void verifyFandomTabOpened(String tabUrl) {
    Assertion.assertEquals(tabUrl, FANDOM_URL);
  }

  public boolean isVideoAdBiggerThanImageAdOasis(double videoHeight, double imageHeight) {
    int percentResult = (int)Math.round(100-(100/(videoHeight/imageHeight)));
    if (percentResult == PERCENT) {
      return true;
    }
    return false;
  }

// Different way of checking slot sizes on mercury because of the very small difference between two slots sizes
  public boolean isVideoAdBiggerTahnImageAdMercury(double videoHeight, double imageHeight) {
    if (videoHeight > imageHeight) {
      return true;
    }
    return false;
  }

  public boolean isImageAdInCorrectSize(double imageHeight, String slotName) throws InterruptedException {
    long time = System.currentTimeMillis();
    long endTime = time+2000;
    while(time < endTime) {
      if (imageHeight == getAdSlotHigh(slotName)){
        return true;
      }
      Thread.sleep(200);
      time = System.currentTimeMillis();
    }
    return false;
  }

  public boolean isTimeProgressing(double quartileTime, double midTime) {
    if (quartileTime < midTime){
      return true;
    }
    return false;
  }
}
